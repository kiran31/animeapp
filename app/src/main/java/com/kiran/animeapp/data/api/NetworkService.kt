package com.kiran.animeapp.data.api

import com.kiran.animeapp.data.model.AnimeDetailResponse
import com.kiran.animeapp.data.model.AnimeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET("top/anime")
    suspend fun getTopAnime(): AnimeResponse

    @GET("anime/{anime_id}")
    suspend fun getAnimeDetails(@Path("anime_id") animeId: Int): AnimeDetailResponse

}