package com.example.login.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun OtherSignInMethod(
    modifier: Modifier,
    iconText: String,
    onButtonClick: () -> Unit,
    icon: Int
) {
    Button(
        modifier = modifier,
        onClick = onButtonClick,
        shape = RoundedCornerShape(16.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = iconText,
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(style = MaterialTheme.typography.titleMedium, text = iconText)
    }
}

data class OtherSignInData(
    val modifier: Modifier,
    val iconText: String,
    val onButtonClick: () -> Unit,
    val icon: Int
)