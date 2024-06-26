package com.example.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_case.user.UserUseCases
import com.example.model.GetCredentialException
import com.example.model.InvalidEmailException
import com.example.model.InvalidPasswordException
import com.example.model.NotVerifiedEmailException
import com.example.model.User
import com.google.firebase.auth.FirebaseAuthException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {

    private val _userEmail = mutableStateOf(UserTextFieldState())
    val userEmail: State<UserTextFieldState>
        get() = _userEmail

    private val _userPassword = mutableStateOf(UserTextFieldState())
    val userPassword: State<UserTextFieldState>
        get() = _userPassword

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow: SharedFlow<UiEvent>
        get() = _eventFlow.asSharedFlow()

    fun checkCurrentUser(): Boolean {
        userUseCases.getUserUseCase()?.let { return true }
        return false
    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnteredEmail -> {
                _userEmail.value = _userEmail.value.copy(
                    isErrorVisible = false,
                    text = event.value
                )
            }

            is LoginEvent.EnteredPassword -> {
                _userPassword.value = _userPassword.value.copy(
                    isErrorVisible = false,
                    text = event.value
                )
            }

            LoginEvent.Login -> {
                viewModelScope.launch {
                    try {
                        userUseCases.loginUseCase(
                            user = User(
                                email = userEmail.value.text,
                                password = userPassword.value.text
                            )
                        )
                        _eventFlow.emit(UiEvent.Login)
                    } catch (e: NotVerifiedEmailException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                R.string.login_error_verified
                            )
                        )
                    } catch (e: InvalidEmailException) {
                        _userEmail.value = _userEmail.value.copy(
                            isErrorVisible = true,
                            error = R.string.sign_up_error_invalid_email
                        )
                    } catch (e: InvalidPasswordException) {
                        _userPassword.value = _userPassword.value.copy(
                            isErrorVisible = true,
                            error = R.string.sign_up_error_invalid_password
                        )
                    } catch (e: Exception) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: R.string.login_error_alternative
                            )
                        )
                    }
                }
            }

            LoginEvent.SignUp -> {
                viewModelScope.launch {
                    try {
                        userUseCases.signUpUseCase(
                            user = User(
                                email = userEmail.value.text,
                                password = userPassword.value.text
                            )
                        )
                        _eventFlow.emit(UiEvent.SignUp)
                    } catch (e: InvalidEmailException) {
                        _userEmail.value = _userEmail.value.copy(
                            isErrorVisible = true,
                            error = R.string.sign_up_error_invalid_email
                        )
                    } catch (e: InvalidPasswordException) {
                        _userPassword.value = _userPassword.value.copy(
                            isErrorVisible = true,
                            error = R.string.sign_up_error_invalid_password
                        )
                    } catch (e: FirebaseAuthException) {
                        if (e.errorCode == "ERROR_EMAIL_ALREADY_IN_USE") {
                            _eventFlow.emit(
                                UiEvent.ShowSnackBar(
                                    message = R.string.sign_up_error_email_already_in_use
                                )
                            )
                        }
                    } catch (e: Exception) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: R.string.login_error_alternative
                            )
                        )
                    }
                }
            }

            is LoginEvent.SignInWithGoogle -> {
                viewModelScope.launch {
                    try {
                        userUseCases.signInWithGoogleUseCase(event.context)
                        _eventFlow.emit(UiEvent.Login)
                    } catch (_: CancellationException) {
                    } catch (e: GetCredentialException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = R.string.sign_up_error_google_credential
                            )
                        )
                    } catch (e: Exception) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: R.string.login_error_alternative
                            )
                        )
                    }
                }

            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: Any) : UiEvent()
        data object Login : UiEvent()
        data object SignUp : UiEvent()
    }

}