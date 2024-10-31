package com.ryong.watchpet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.ryong.watchpet.services.HealthServicesRepository
import com.ryong.watchpet.ui.screens.MeasureDataScreen
import com.ryong.watchpet.ui.screens.NotSupportedScreen
import com.ryong.watchpet.viewmodels.MeasureDataViewModel
import com.ryong.watchpet.viewmodels.MeasureDataViewModelFactory
import com.ryong.watchpet.viewmodels.UiState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MeasureDataApp(
    healthServicesRepository: HealthServicesRepository
) {
    val viewModel: MeasureDataViewModel = viewModel(
        factory = MeasureDataViewModelFactory(
            healthServicesRepository = healthServicesRepository
        )
    )
    val enabled by viewModel.enabled.collectAsState()
    val hr by viewModel.hr
    val availability by viewModel.availability
    val uiState by viewModel.uiState

    if (uiState == UiState.Supported) {
        val permissionState = rememberPermissionState(
            permission = PERMISSION,
            onPermissionResult = { granted ->
                if (granted) viewModel.toggleEnabled()
            }
        )
        MeasureDataScreen(
            hr = hr,
            availability = availability,
            enabled = enabled,
            onButtonClick = { viewModel.toggleEnabled() },
            permissionState = permissionState
        )
    } else if (uiState == UiState.NotSupported) {
        NotSupportedScreen()
    }
}