package com.ryong.watchpet

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.tooling.preview.devices.WearDevices
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.ryong.watchpet.ui.components.Layout
import com.ryong.watchpet.ui.components.Pet
import com.ryong.watchpet.ui.theme.WatchpetTheme
import com.ryong.watchpet.viewmodels.PetViewModel

@SuppressLint("PermissionLaunchedDuringComposition")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WearApp() {

    val petViewModel = PetViewModel()

    val permissionState = rememberPermissionState(
        permission = android.Manifest.permission.BODY_SENSORS,
        onPermissionResult = { granted -> /* do something */ }
    )

    if (permissionState.status.isGranted) {
        // do something
        println("granted")
    } else {
        permissionState.launchPermissionRequest()
        println("not granted")
    }

    WatchpetTheme {
        Layout(
            children = {
                Pet(petViewModel = petViewModel)
            },
            hasClock = true
        )
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp()
}