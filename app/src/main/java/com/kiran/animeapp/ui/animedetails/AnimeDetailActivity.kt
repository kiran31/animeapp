package com.kiran.animeapp.ui.animedetails

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.kiran.animeapp.AnimApplication
import com.kiran.animeapp.R
import com.kiran.animeapp.data.model.AnimeDetailResponse
import com.kiran.animeapp.databinding.ActivityAnimeDetailBinding
import com.kiran.animeapp.di.component.DaggerActivityComponent
import com.kiran.animeapp.di.module.ActivityModule
import com.kiran.animeapp.ui.base.UiState
import com.kiran.animeapp.utils.NetworkHelper
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.coroutines.launch
import javax.inject.Inject

class AnimeDetailActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: AnimeDetailViewModel
    @Inject
    lateinit var networkHelper: NetworkHelper
    private lateinit var binding: ActivityAnimeDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animeId = intent.getIntExtra(ANIME_ID, -1)
        if (animeId != -1) {
            setupObservers()
            viewModel.fetchAnimeDetails(animeId)
        }

    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        binding.animeTitle.text = "Loading..."
                    }

                    is UiState.Success -> {
                        displayAnimeDetails(uiState.data)
                    }

                    is UiState.Error -> {
                        binding.animeTitle.text = "Error: ${uiState.message}"
                    }
                }
            }
        }
    }

    private fun displayAnimeDetails(response: AnimeDetailResponse) {
        val anime = response.data
        if (anime?.trailer?.youtube_id != null) {
            binding.youtubePlayerView.visibility = View.VISIBLE
            binding.posterImage.visibility = View.GONE
            lifecycle.addObserver(binding.youtubePlayerView)
            binding.youtubePlayerView.addYouTubePlayerListener(object :
                AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.cueVideo(anime?.trailer?.youtube_id, 0f)
                }
            })
        } else {
            binding.youtubePlayerView.visibility = View.GONE
            binding.posterImage.visibility = View.VISIBLE
            Glide.with(this)
                .load(anime?.images?.jpg?.large_image_url)
                .into(binding.posterImage)
        }
        binding.animeTitle.text = anime?.title
        binding.genreContainer.removeAllViews()
        anime?.genres?.forEach { genre ->
            val chip = Chip(this)
            chip.text = genre.name
            chip.isClickable = false
            chip.isCheckable = false
            chip.setChipBackgroundColorResource(R.color.purple_200)
            chip.setTextColor(resources.getColor(android.R.color.white, theme))
            binding.genreContainer.addView(chip)
        }

        binding.animeSynopsis.text = anime?.synopsis
        binding.animeEpisodes.text = anime?.episodes.toString()
        binding.animeRating.text = "${anime?.score} (by ${anime?.scored_by} users)"
        binding.animeCast.text = "cast data not avialable in this response"
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as AnimApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }

    companion object {
        val ANIME_ID: String = "anime_id"
    }
}