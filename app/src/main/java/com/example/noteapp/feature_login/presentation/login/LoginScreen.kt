package com.example.noteapp.feature_login.presentation.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.noteapp.R
import com.example.noteapp.feature_login.presentation.login.components.PagerContent
import com.example.noteapp.feature_login.presentation.login.components.WelcomeSection
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        val scope = rememberCoroutineScope()
        val pagerState = rememberPagerState { TabItems.entries.size }
        val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }

        Column(
            modifier = Modifier
                .weight(6f)
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            WelcomeSection(
                generalText = stringResource(id = R.string.welcome),
                specificLoginText = stringResource(id = R.string.welcome_login),
                specificSignUpText = stringResource(id = R.string.welcome_sign_up),
                selectedTabIndex
            )
        }

        Column(
            modifier = Modifier
                .weight(13f)
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(top = 8.dp)
        ) {
            PrimaryTabRow(
                modifier = Modifier.padding(horizontal = 24.dp),
                selectedTabIndex = selectedTabIndex.value,
                divider = {},
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                TabItems.entries.forEachIndexed { index, item ->
                    val title = stringResource(
                        id = when (item) {
                            TabItems.LOGIN -> R.string.login_title
                            TabItems.SIGN_UP -> R.string.sign_up_title
                        }
                    )
                    Tab(
                        selected = index == selectedTabIndex.value,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = { Text(text = title, style = MaterialTheme.typography.titleLarge) }
                    )
                }
            }
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                when (it) {
                    0 -> PagerContent(
                        emailText = stringResource(id = R.string.login_email),
                        passwordText = stringResource(id = R.string.login_password),
                        buttonText = stringResource(id = R.string.login_title),
                    )

                    1 -> PagerContent(
                        emailText = stringResource(id = R.string.sign_up_email),
                        passwordText = stringResource(id = R.string.sign_up_password),
                        buttonText = stringResource(id = R.string.sign_up_title),
                    )
                }

            }

        }
    }
}

enum class TabItems {
    LOGIN,
    SIGN_UP
}