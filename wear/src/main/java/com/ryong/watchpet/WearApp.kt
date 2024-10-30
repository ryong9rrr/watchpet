package com.ryong.watchpet

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.tooling.preview.devices.WearDevices
import com.ryong.watchpet.ui.components.Layout
import com.ryong.watchpet.ui.theme.WatchpetTheme

@Composable
fun WearApp() {
    WatchpetTheme {
        Layout(
            children = {
                SensorPermissionScreen()
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