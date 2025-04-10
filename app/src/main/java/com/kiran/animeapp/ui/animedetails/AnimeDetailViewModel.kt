package com.kiran.animeapp.ui.animedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiran.animeapp.data.model.AnimeDetailResponse
import com.kiran.animeapp.data.repository.MainRepository
import com.kiran.animeapp.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class AnimeDetailViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<AnimeDetailResponse>>(UiState.Loading)
    val uiState: StateFlow<UiState<AnimeDetailResponse>> = _uiState

    fun fetchAnimeDetails(animeId: Int) {
        viewModelScope.launch {
            mainRepository.getAnimeDetails(animeId)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collect { response ->
                    _uiState.value = UiState.Success(response)
                }
        }
    }
}