package com.ryong.watchpet

import androidx.compose.runtime.Composable
import com.ryong.watchpet.services.HealthServicesRepository
import com.ryong.watchpet.ui.components.Layout
import com.ryong.watchpet.ui.screens.heartrate.HeartRateScreen
import com.ryong.watchpet.ui.theme.WatchpetTheme

@Composable
fun WearApp(
    healthServicesRepository: HealthServicesRepository
) {
    WatchpetTheme {
        Layout(
            children = {
                HeartRateScreen(
                    healthServicesRepository = healthServicesRepository
                )
            },
            hasClock = true
        )
    }
}