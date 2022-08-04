package com.github.mattcreaser.amplifyshopping

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amplifyframework.auth.AuthException
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.kotlin.core.Amplify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

sealed interface LoginState
object LoginLoading : LoginState
object LoginRequired : LoginState
object LoginComplete : LoginState
sealed interface LoginError : LoginState
object LoginInvalid : LoginError
object LoginUnknownError : LoginError
object LoginInvalidPasswordError : LoginError


class LoginViewModel : ViewModel() {

    private val tag = LoginViewModel::class.simpleName

    private val _loggedIn = MutableStateFlow<LoginState>(LoginLoading)
    val loginState = _loggedIn.asStateFlow()

    init {
        viewModelScope.launch {
            val session = Amplify.Auth.fetchAuthSession()
            _loggedIn.value = when (session.isSignedIn) {
                true -> LoginComplete
                false -> LoginRequired
            }
        }
    }

    fun signIn(username: String, password: String) {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    Amplify.Auth.signIn(username = username, password = password)
                }
                if (result.isSignInComplete) {
                    _loggedIn.value = LoginComplete
                }
            } catch (e: AuthException) {
                Log.i(tag, "Could not sign in", e)
                // TODO : Emit error
            }
        }
    }

    fun signUp(username: String, password: String) {
        viewModelScope.launch {
            try {
                val options = AuthSignUpOptions.builder()
                    .userAttribute(AuthUserAttributeKey.preferredUsername(), username)
                    .build()
                val result = Amplify.Auth.signUp(username, password, options)
                if (result.isSignUpComplete) {
                    _loggedIn.value = LoginComplete
                }
            } catch (e: AuthException) {
                Log.i(tag, "Could not sign up", e)
                // TODO : Emit error
            }
        }
    }
}