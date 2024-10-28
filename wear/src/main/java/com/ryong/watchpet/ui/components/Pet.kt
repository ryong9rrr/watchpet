package com.ryong.watchpet.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import com.ryong.watchpet.ui.theme.WatchpetTheme
import com.ryong.watchpet.viewmodels.PetViewModel

@Composable
fun Pet(
    petViewModel: PetViewModel
){
    val petUiState by petViewModel.uiState.collectAsState()

    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        text = petUiState.state.name
    )
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
private fun PetPreview(){
    val petViewModel = PetViewModel()

    WatchpetTheme {
        Layout(
            children = {
                Pet(petViewModel = petViewModel)
            }
        )
    }
}
