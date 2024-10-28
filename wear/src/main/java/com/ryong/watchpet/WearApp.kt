package com.ryong.watchpet

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.tooling.preview.devices.WearDevices
import com.ryong.watchpet.ui.components.Layout
import com.ryong.watchpet.ui.components.Pet
import com.ryong.watchpet.ui.theme.WatchpetTheme
import com.ryong.watchpet.viewmodels.PetViewModel

@Composable
fun WearApp() {

    val petViewModel = PetViewModel()

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