package com.example.noteapp.feature_login.presentation.login.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier

@Composable
fun WelcomeSection(
    generalText: String,
    specificLoginText: String,
    specificSignUpText: String,
    currentPage: State<Int>
) {
    Column(
        modifier = Modifier
    ) {
        Text(
            text = generalText,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.headlineLarge
        )
        AnimatedContent(
            targetState = currentPage.value,
            transitionSpec = {
                addAnimation().using(
                    SizeTransform(clip = false)
                )
            }
        ) { targetCount ->
            Text(
                text = if (targetCount == 0) specificLoginText else if (targetCount == 1) specificSignUpText else specificLoginText,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.headlineLarge
            )
        }

    }

}

fun addAnimation(duration: Int = 500): ContentTransform {
    return (slideInVertically(animationSpec = tween(durationMillis = duration)) { height -> height } + fadeIn(
        animationSpec = tween(durationMillis = duration)
    )).togetherWith(slideOutVertically(animationSpec = tween(durationMillis = duration)) { height -> height } + fadeOut(
        animationSpec = tween(durationMillis = duration)
    ))
}