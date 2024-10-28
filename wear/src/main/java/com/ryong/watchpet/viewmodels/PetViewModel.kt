package com.ryong.watchpet.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PetViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(PetUiState())
    val uiState: StateFlow<PetUiState> = _uiState.asStateFlow()

    fun setStanding(){
        _uiState.update { prevState ->
            prevState.copy(
                state = PetState.Standing
            )
        }
    }

    fun setWalking(){
        _uiState.update { prevState ->
            prevState.copy(
                state = PetState.Walking
            )
        }
    }

    fun setRunning(){
        _uiState.update { prevState ->
            prevState.copy(
                state = PetState.Running
            )
        }
    }
}

data class PetUiState(
    val state: PetState = PetState.Standing
)

enum class PetState {
    Standing, Walking, Running
}