package com.ryong.watchpet.ui.screens.heartrate

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.ryong.watchpet.services.HealthServicesRepository
import com.ryong.watchpet.ui.components.Button
import com.ryong.watchpet.ui.components.Flex
import com.ryong.watchpet.viewmodels.HeartRateMeasureUiState
import com.ryong.watchpet.viewmodels.HeartRateMeasureViewModel
import com.ryong.watchpet.viewmodels.HeartRateMeasureViewModelFactory

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HeartRateScreen(
    healthServicesRepository: HealthServicesRepository
){
    val viewModel: HeartRateMeasureViewModel = viewModel(
        factory = HeartRateMeasureViewModelFactory(
            healthServicesRepository = healthServicesRepository
        )
    )

    val enabled by viewModel.enabled.collectAsState()
    val hr by viewModel.hr
    val availability by viewModel.availability
    val uiState by viewModel.uiState

    if (uiState == HeartRateMeasureUiState.Supported) {
        val permissionState = rememberPermissionState(
            permission = android.Manifest.permission.BODY_SENSORS,
            onPermissionResult = { granted ->
                if (granted) {
                    viewModel.toggleEnabled()
                }
            }
        )

        when (permissionState.status) {
            is PermissionStatus.Granted -> {
                HeartRateMeasureScreen(
                    hr = hr,
                    availability = availability,
                    enabled = enabled,
                    onButtonClick = { viewModel.toggleEnabled() },
                    permissionState = permissionState
                )
            }
            is PermissionStatus.Denied -> {
                LaunchedEffect (Unit) {
                    permissionState.launchPermissionRequest()
                }
                Flex {
                    Text(
                        text = "심박수 측정을 위해",
                        color = Color.White
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "권한을 허용해주세요",
                        color = Color.White
                    )
                    Spacer(Modifier.height(16.dp))
                    Button(
                        onClick = {
                            permissionState.launchPermissionRequest()
                        },
                        text = "권한 허용"
                    )
                }
            }
        }
    } else if (uiState == HeartRateMeasureUiState.NotSupported) {
        HeartRateMeasureNotSupportedScreen()
    }
}