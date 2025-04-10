package com.kiran.animeapp.ui.topanime

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiran.animeapp.AnimApplication
import com.kiran.animeapp.R
import com.kiran.animeapp.data.model.AnimeResponse
import com.kiran.animeapp.data.model.Data
import com.kiran.animeapp.databinding.ActivityTopAnimeBinding
import com.kiran.animeapp.di.component.DaggerActivityComponent
import com.kiran.animeapp.di.module.ActivityModule
import com.kiran.animeapp.ui.base.UiState
import com.kiran.animeapp.utils.NetworkHelper
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopAnimeActivity : AppCompatActivity() {
    @Inject
    lateinit var animeListViewModel: AnimeListViewModel

    @Inject
    lateinit var adapter: TopAnimeAdapter
    @Inject
    lateinit var networkHelper: NetworkHelper

    private lateinit var binding: ActivityTopAnimeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityTopAnimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        val recyclerView = binding.rvAnimeList
        binding.rvAnimeList.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                animeListViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            renderList(it.data)
                            binding.rvAnimeList.visibility = View.VISIBLE
                        }
                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.rvAnimeList.visibility = View.GONE
                        }
                        is UiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@TopAnimeActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(animResponse: AnimeResponse) {
        val dataList = animResponse.data ?: emptyList()
        adapter.addData(dataList)
        adapter.notifyDataSetChanged()
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as AnimApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }
}