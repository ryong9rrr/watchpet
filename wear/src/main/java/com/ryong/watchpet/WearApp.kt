package com.ryong.watchpet

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.ryong.watchpet.ui.components.Button
import com.ryong.watchpet.ui.components.Flex
import com.ryong.watchpet.ui.components.Layout
import com.ryong.watchpet.ui.theme.WatchpetTheme

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WearApp() {
    val permissionState = rememberPermissionState(permission = android.Manifest.permission.BODY_SENSORS)

    WatchpetTheme {
        Layout(
            children = {
                when (permissionState.status) {
                    is PermissionStatus.Granted -> {
                        WatchPet()
                    }
                    is PermissionStatus.Denied -> {
                        LaunchedEffect(Unit) {
                            permissionState.launchPermissionRequest()
                        }
                        Flex {
                            Text("심박수 측정을 위해")
                            Spacer(Modifier.height(4.dp))
                            Text("권한을 허용해주세요")
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
            },
            hasClock = true
        )
    }
}