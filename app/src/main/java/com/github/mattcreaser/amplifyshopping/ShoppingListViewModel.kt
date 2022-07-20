package com.github.mattcreaser.amplifyshopping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amplifyframework.core.model.query.ObserveQueryOptions
import com.amplifyframework.datastore.generated.model.Item
import com.amplifyframework.kotlin.core.Amplify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class ShoppingListViewModel : ViewModel() {

    val items = flow {
        Amplify.DataStore.observeQuery(Item::class, ObserveQueryOptions()).collect {
            emit(it.items)
        }
    }

    fun saveItem(label: String) {
        viewModelScope.launch {
            val item = Item.Builder().label(label).build()
            Amplify.DataStore.save(item)
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            Amplify.DataStore.delete(item)
        }
    }
}