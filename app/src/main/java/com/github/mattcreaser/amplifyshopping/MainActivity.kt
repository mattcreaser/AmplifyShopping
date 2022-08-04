package com.github.mattcreaser.amplifyshopping

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.datastore.AWSDataStorePlugin
import com.amplifyframework.kotlin.core.Amplify
import com.github.mattcreaser.amplifyshopping.ui.LoadingScreen
import com.github.mattcreaser.amplifyshopping.ui.LoginScreen
import com.github.mattcreaser.amplifyshopping.ui.ShoppingListScreen
import com.github.mattcreaser.amplifyshopping.ui.theme.AmplifyShoppingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            //Amplify.addPlugin(AWSApiPlugin()) // UNCOMMENT this line once backend is deployed
            Amplify.addPlugin(AWSDataStorePlugin())
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.configure(applicationContext)
            Log.i("Amplify", "Initialized Amplify")
        } catch (e: AmplifyException) {
            Log.e("Amplify", "Could not initialize Amplify", e)
        }

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