package com.github.mattcreaser.amplifyshopping

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.amplifyframework.AmplifyException
import com.amplifyframework.datastore.AWSDataStorePlugin
import com.amplifyframework.kotlin.core.Amplify
import com.github.mattcreaser.amplifyshopping.ui.ShoppingListScreen
import com.github.mattcreaser.amplifyshopping.ui.theme.AmplifyShoppingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            //Amplify.addPlugin(AWSApiPlugin()) // UNCOMMENT this line once backend is deployed
            Amplify.addPlugin(AWSDataStorePlugin())
            Amplify.configure(applicationContext)
            Log.i("Amplify", "Initialized Amplify")
        } catch (e: AmplifyException) {
            Log.e("Amplify", "Could not initialize Amplify", e)
        }

        setContent {
            AmplifyShoppingTheme {
                ShoppingListScreen()
            }
        }
    }
}