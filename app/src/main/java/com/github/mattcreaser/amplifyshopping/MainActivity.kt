package com.github.mattcreaser.amplifyshopping

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.mattcreaser.amplifyshopping.ui.LoadingScreen
import com.github.mattcreaser.amplifyshopping.ui.LoginScreen
import com.github.mattcreaser.amplifyshopping.ui.ShoppingListScreen
import com.github.mattcreaser.amplifyshopping.ui.theme.AmplifyShoppingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val loginViewModel = viewModel<LoginViewModel>()
            val loginState by loginViewModel.loginState.collectAsState(LoginLoading)
            AmplifyShoppingTheme {
                when (loginState) {
                    LoginLoading -> LoadingScreen()
                    LoginComplete -> ShoppingListScreen()
                    else -> LoginScreen()
                }
            }
        }
    }
}