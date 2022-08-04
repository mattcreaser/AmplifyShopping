package com.github.mattcreaser.amplifyshopping.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.mattcreaser.amplifyshopping.LoginViewModel
import com.github.mattcreaser.amplifyshopping.R
import com.github.mattcreaser.amplifyshopping.ui.theme.AppTheme

@Composable
fun LoginScreen() {
    val viewModel = viewModel<LoginViewModel>()
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(AppTheme.dimens.grid_2)
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            var showSignUp by remember { mutableStateOf(false) }
            Crossfade(targetState = showSignUp) { onCreate ->
                if (onCreate) {
                    LoginSignUp(
                        modifier = Modifier.fillMaxWidth(),
                        onSubmit = { username, password ->
                            viewModel.signUp(username, password)
                        }
                    )
                } else {
                    LoginSignIn(
                        modifier = Modifier.fillMaxWidth(),
                        onSubmit = { username, password ->
                            viewModel.signIn(username, password)
                        },
                        onClickSignUp = { showSignUp = true }
                    )
                }
            }


        }
    }
}

@Composable
fun LoginSignIn(
    onSubmit: (String, String) -> Unit,
    onClickSignUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(modifier = modifier) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(stringResource(R.string.label_username)) }
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.label_password)) },
            visualTransformation = PasswordVisualTransformation()
        )
        Button(onClick = { onSubmit(username, password) }) {
            Text(text = stringResource(R.string.login_signin))
        }
        OutlinedButton(onClick = { onClickSignUp() }) {
            Text(text = stringResource(R.string.login_signup))
        }
    }
}

@Composable
fun LoginSignUp(
    onSubmit: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(modifier = modifier) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(stringResource(R.string.label_username)) }
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.label_password)) },
            visualTransformation = PasswordVisualTransformation()
        )
        Button(onClick = { onSubmit(username, password) }) {
            Text(text = stringResource(R.string.login_signup))
        }
    }
}