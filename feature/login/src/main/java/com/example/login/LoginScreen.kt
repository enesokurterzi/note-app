package com.example.login

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.login.components.OtherSignInData
import com.example.login.components.PagerContent
import com.example.login.components.WelcomeSection
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    forwardDestination: String,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val resources = context.resources
    val emailState = viewModel.userEmail.value
    val passwordState = viewModel.userPassword.value
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState { TabItems.entries.size }
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }

    val snackBarHostState = remember { SnackbarHostState() }

    val navigateToNotes = {
        navController.navigate(forwardDestination) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }

    LaunchedEffect(key1 = true) {
        if (viewModel.checkCurrentUser()) navigateToNotes.invoke()
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is LoginViewModel.UiEvent.Login -> navigateToNotes.invoke()

                is LoginViewModel.UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(
                        message = with(event) {
                            when (message) {
                                is Int -> resources.getString(message)
                                is String -> message
                                else -> {
                                    message.toString()
                                }
                            }
                        }
                    )
                }

                is LoginViewModel.UiEvent.SignUp -> {
                    this.launch {
                        pagerState.animateScrollToPage(0)
                        snackBarHostState.showSnackbar(
                            message = resources.getString(R.string.sign_up_successful)
                        )
                    }
                }
            }
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
                .padding(paddingValues)
        ) {
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
                    currentPage = selectedTabIndex
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
                            text = {
                                Text(
                                    text = title,
                                    style = MaterialTheme.typography.titleLarge
                                )
                            }
                        )
                    }
                }
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) { page ->
                    when (page) {
                        0 -> {
                            PagerContent(
                                emailTitle = stringResource(id = R.string.login_email),
                                emailState = emailState,
                                passwordTitle = stringResource(id = R.string.login_password),
                                passwordState = passwordState,
                                buttonTitle = stringResource(id = R.string.login_title),
                                onEmailChange = {
                                    viewModel.onEvent(LoginEvent.EnteredEmail(it))
                                },
                                onPasswordChange = {
                                    viewModel.onEvent(LoginEvent.EnteredPassword(it))
                                },
                                onButtonClick = {
                                    viewModel.onEvent(LoginEvent.Login)
                                },
                                otherMethodList = listOf(
                                    OtherSignInData(
                                        modifier = Modifier.weight(1f),
                                        iconText = "Google",
                                        onButtonClick = {
                                            viewModel.onEvent(LoginEvent.SignInWithGoogle(context))
                                        },
                                        icon = R.drawable.google
                                    )
                                )
                            )
                        }


                        1 -> PagerContent(
                            emailTitle = stringResource(id = R.string.sign_up_email),
                            emailState = emailState,
                            passwordTitle = stringResource(id = R.string.sign_up_password),
                            passwordState = passwordState,
                            buttonTitle = stringResource(id = R.string.sign_up_title),
                            onEmailChange = {
                                viewModel.onEvent(LoginEvent.EnteredEmail(it))
                            },
                            onPasswordChange = {
                                viewModel.onEvent(LoginEvent.EnteredPassword(it))
                            },
                            onButtonClick = {
                                viewModel.onEvent(LoginEvent.SignUp)
                            }
                        )
                    }
                }

            }
        }

    }
}

enum class TabItems {
    LOGIN,
    SIGN_UP
}