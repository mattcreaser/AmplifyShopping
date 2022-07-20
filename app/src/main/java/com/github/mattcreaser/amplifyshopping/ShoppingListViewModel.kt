package com.github.mattcreaser.amplifyshopping

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amplifyframework.core.model.query.ObserveQueryOptions
import com.amplifyframework.datastore.generated.model.Item
import com.amplifyframework.datastore.generated.model.ShoppingList
import com.amplifyframework.kotlin.core.Amplify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class ShoppingListViewModel : ViewModel() {

    val items = flow {
        Amplify.DataStore.observeQuery(Item::class, ObserveQueryOptions()).collect {
            emit(it.items)
        }
    }

    val lists = flow {
        Amplify.DataStore.observeQuery(ShoppingList::class, ObserveQueryOptions()).collect {
            if (selectedList == null) {
                selectedList = it.items.firstOrNull()
            }
            emit(it.items)
        }
    }

    var selectedList by mutableStateOf<ShoppingList?>(null)

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

    fun saveList(label: String) {
        if (label.isNotBlank()) {
            viewModelScope.launch {
                val list = ShoppingList.Builder().label(label.trim()).build()
                Amplify.DataStore.save(list)
                selectedList = list
            }
        }
    }
}