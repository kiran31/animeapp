package com.kiran.animeapp.ui.topanime

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kiran.animeapp.data.model.Data
import com.kiran.animeapp.databinding.ItemAnimeLayoutBinding
import com.kiran.animeapp.ui.animedetails.AnimeDetailActivity

class TopAnimeAdapter(private val list: ArrayList<Data>) :
    RecyclerView.Adapter<TopAnimeAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: ItemAnimeLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(anime: Data) {
            Glide.with(binding.animeImage.context)
                .load(anime.images.jpg.image_url)
                .into(binding.animeImage)
            binding.animeTitle.text = anime.title
            binding.scoreText.text = "Score: ${anime.score}"
            binding.animeEpisodes.text = "Ep: ${anime.episodes}"
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, AnimeDetailActivity::class.java)
                intent.putExtra(AnimeDetailActivity.ANIME_ID, anime.mal_id)
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemAnimeLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val anime = list[position]
        holder.bind(anime)
    }

    fun addData(animList: List<Data>) {
        list.addAll(animList)
        notifyDataSetChanged()
    }
}