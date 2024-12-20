package com.ryong.watchpet.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.health.services.client.data.DataTypeAvailability
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ryong.watchpet.services.HealthServicesRepository
import com.ryong.watchpet.services.MeasureMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.launch

class HeartRateMeasureViewModel(
    private val healthServicesRepository: HealthServicesRepository
): ViewModel() {
    val enabled: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val hr: MutableState<Double> = mutableStateOf(0.0)
    val availability: MutableState<DataTypeAvailability> =
        mutableStateOf(DataTypeAvailability.UNKNOWN)

    val uiState: MutableState<HeartRateMeasureUiState> = mutableStateOf(HeartRateMeasureUiState.Startup)

    init {
        viewModelScope.launch {
            val supported = healthServicesRepository.hasHeartRateCapability()
            uiState.value = if (supported) {
                HeartRateMeasureUiState.Supported
            } else {
                HeartRateMeasureUiState.NotSupported
            }
        }

        viewModelScope.launch {
            enabled.collect {
                if (it) {
                    healthServicesRepository.heartRateMeasureFlow()
                        .takeWhile { enabled.value }
                        .collect { measureMessage ->
                            when (measureMessage) {
                                is MeasureMessage.MeasureData -> {
                                    hr.value = measureMessage.data.last().value
                                }
                                is MeasureMessage.MeasureAvailability -> {
                                    availability.value = measureMessage.availability
                                }
                            }
                        }
                }
            }
        }
    }

    fun toggleEnabled() {
        enabled.value = !enabled.value
        if (!enabled.value) {
            availability.value = DataTypeAvailability.UNKNOWN
        }
    }
}

class HeartRateMeasureViewModelFactory(
    private val healthServicesRepository: HealthServicesRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HeartRateMeasureViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HeartRateMeasureViewModel(
                healthServicesRepository = healthServicesRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

sealed class HeartRateMeasureUiState {
    object Startup : HeartRateMeasureUiState()
    object NotSupported : HeartRateMeasureUiState()
    object Supported : HeartRateMeasureUiState()
}