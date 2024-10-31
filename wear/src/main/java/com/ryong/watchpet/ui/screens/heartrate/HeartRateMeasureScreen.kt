package com.ryong.watchpet.ui.screens.heartrate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.health.services.client.data.DataTypeAvailability
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.isGranted
import com.ryong.watchpet.R
import com.ryong.watchpet.ui.components.HrLabel
import com.ryong.watchpet.ui.theme.WatchpetTheme

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HeartRateMeasureScreen(
    hr: Double,
    availability: DataTypeAvailability,
    enabled: Boolean,
    onButtonClick: () -> Unit,
    permissionState: PermissionState
){

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HrLabel(
            hr = hr,
            availability = availability
        )
        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = {
                if (permissionState.status.isGranted) {
                    onButtonClick()
                } else {
                    permissionState.launchPermissionRequest()
                }
            }
        ) {
            val buttonTextId = if (enabled) {
                R.string.stop
            } else {
                R.string.start
            }
            Text(stringResource(buttonTextId))
        }
    }
}

@ExperimentalPermissionsApi
@Preview(
    device = Devices.WEAR_OS_SMALL_ROUND,
    showBackground = false,
    showSystemUi = true
)
@Composable
private fun HeartRateMeasureScreenPreview() {
    val permissionState = object : PermissionState {
        override val permission = "android.permission.ACTIVITY_RECOGNITION"
        override val status: PermissionStatus = PermissionStatus.Granted
        override fun launchPermissionRequest() {}
    }
    WatchpetTheme {
        HeartRateMeasureScreen(
            hr = 65.0,
            availability = DataTypeAvailability.AVAILABLE,
            enabled = false,
            onButtonClick = {},
            permissionState = permissionState
        )
    }
}