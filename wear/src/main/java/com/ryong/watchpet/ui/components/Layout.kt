package com.ryong.watchpet.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.TimeText
import com.ryong.watchpet.ui.theme.BlackAlpha900

@Composable
fun Layout(
    children: @Composable () -> Unit,
    hasClock: Boolean = false
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BlackAlpha900),
        contentAlignment = Alignment.Center
    ) {
        if (hasClock) {
            TimeText()
        }

        children()
    }
}