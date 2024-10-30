package com.ryong.watchpet.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import com.ryong.watchpet.ui.theme.Green500

@Composable fun Button(
    onClick: (() -> Unit)? = null,
    text: String,
    color: Color = Green500
){
    fun handleClick(){
        onClick?.invoke()
    }

    androidx.wear.compose.material.Button (
        onClick = ::handleClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color),
        modifier = Modifier.width(120.dp).height(36.dp),
        shape = RoundedCornerShape(4.dp),
    ) {
        Text(
            text = text
        )
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
private fun Button2Preview(){
    Layout(children = {
        Button(text = "Hello")
    })
}