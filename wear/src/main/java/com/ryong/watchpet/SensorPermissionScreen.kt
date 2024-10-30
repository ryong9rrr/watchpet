package com.ryong.watchpet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.ryong.watchpet.ui.components.Pet

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SensorPermissionScreen() {
    val permissionState = rememberPermissionState(permission = android.Manifest.permission.BODY_SENSORS)

    when (permissionState.status) {
        is PermissionStatus.Granted -> {
            Pet()
        }
        is PermissionStatus.Denied -> {
            LaunchedEffect(Unit) {
                permissionState.launchPermissionRequest()
            }
            Text("권한 요청 중")
        }
    }
}