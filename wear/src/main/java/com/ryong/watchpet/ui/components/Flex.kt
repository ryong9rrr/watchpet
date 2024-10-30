package com.ryong.watchpet.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices

@Composable
fun Flex(children: @Composable () -> Unit){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        children()
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
private fun FlexPreview(){
    Layout(children = {
        Flex {
            Text("Hello")
            Spacer(Modifier.height(8.dp))
            Text("World")
        }
    })
}