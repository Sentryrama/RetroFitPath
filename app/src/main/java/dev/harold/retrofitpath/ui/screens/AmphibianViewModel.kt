package dev.harold.retrofitpath.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import dev.harold.retrofitpath.AmphibiansApplication
import dev.harold.retrofitpath.data.AmphibianRepository
import dev.harold.retrofitpath.model.AmphibianInfo
import kotlinx.coroutines.launch

sealed interface AmphibianUiState {
    data class Success(val amphibians: List<AmphibianInfo>): AmphibianUiState
    object Error: AmphibianUiState
    object Loading: AmphibianUiState
}

class AmphibianViewModel(
    private val amphibianRepository: AmphibianRepository
): ViewModel() {
    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AmphibiansApplication)
                val amphibianRepository = application.container.amphibianRepository
                AmphibianViewModel(amphibianRepository)
            }
        }
    }

    var amphibianUiState: AmphibianUiState by mutableStateOf(AmphibianUiState.Loading)
        private set

    init {
        loadAmphibians()
    }

    fun loadAmphibians() {
        viewModelScope.launch {
            amphibianUiState = AmphibianUiState.Loading
            amphibianUiState = try {
                AmphibianUiState.Success(amphibianRepository.getAmphibians())
            } catch (e: Exception) {
                AmphibianUiState.Error
            }
        }

    }
}