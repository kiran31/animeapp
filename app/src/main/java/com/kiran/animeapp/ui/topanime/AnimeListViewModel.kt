package com.kiran.animeapp.ui.topanime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiran.animeapp.data.model.AnimeResponse
import com.kiran.animeapp.data.model.Data
import com.kiran.animeapp.data.repository.MainRepository
import com.kiran.animeapp.ui.base.UiState
import com.kiran.animeapp.utils.NetworkHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class AnimeListViewModel(private val mainRepository: MainRepository,
                         private val networkHelper: NetworkHelper) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<AnimeResponse>>(UiState.Loading)

    val uiState: StateFlow<UiState<AnimeResponse>> = _uiState

    init {
        getAnimes()
    }

    private fun getAnimes() {
        if (networkHelper.isNetworkAvailable()) {
            viewModelScope.launch {
                mainRepository.getTopAnimes()
                    .catch { e ->
                        _uiState.value = UiState.Error(e.toString())
                    }.collect {
                        _uiState.value = UiState.Success(it)
                    }
            }
        } else {
            _uiState.value = UiState.Error("No internet connection available")
        }
    }



}