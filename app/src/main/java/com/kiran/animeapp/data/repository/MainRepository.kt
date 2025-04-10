package com.kiran.animeapp.data.repository

import com.kiran.animeapp.data.api.NetworkService
import com.kiran.animeapp.data.model.AnimeDetailResponse
import com.kiran.animeapp.data.model.AnimeResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository@Inject constructor(private val networkService: NetworkService) {

    fun getTopAnimes(): Flow<AnimeResponse> {
        return flow {
            emit(networkService.getTopAnime())
        }.map {
            it
        }
    }

    fun getAnimeDetails(animeId: Int): Flow<AnimeDetailResponse> {
        return flow {
            emit(networkService.getAnimeDetails(animeId))
        }
    }

}