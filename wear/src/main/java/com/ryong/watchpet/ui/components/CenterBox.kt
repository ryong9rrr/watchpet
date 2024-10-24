package com.ryong.watchpet.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ryong.watchpet.ui.theme.BlackAlpha900

@Composable
fun CenterBox(
    children: @Composable () -> Unit, // 다른 jetpack compose 컴포넌트를 인자로 받음
    backgroundColor: Color = BlackAlpha900 // backgroundColor는 optional이며 기본값을 BlackAlpha900으로 지정
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        children()
    }
}