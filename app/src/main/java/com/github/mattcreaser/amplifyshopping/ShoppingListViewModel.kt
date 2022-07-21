package com.github.mattcreaser.amplifyshopping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amplifyframework.core.model.query.ObserveQueryOptions
import com.amplifyframework.datastore.generated.model.Item
import com.amplifyframework.datastore.generated.model.Quantity
import com.amplifyframework.datastore.generated.model.QuantityUnit
import com.amplifyframework.datastore.generated.model.ShoppingList
import com.amplifyframework.kotlin.core.Amplify
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ShoppingListViewModel : ViewModel() {

    val lists = flow {
        Amplify.DataStore.observeQuery(ShoppingList::class, ObserveQueryOptions()).collect {
            if (_selectedList.value == null) {
                _selectedList.value = it.items.firstOrNull()
            }
            emit(it.items)
        }
    }

    private val _selectedList = MutableStateFlow<ShoppingList?>(null)
    val selectedList = _selectedList.asStateFlow()

    val items = _selectedList.flatMapLatest { currentList ->
        val listId = currentList?.id
        if (listId != null) {
            val predicate = Item.SHOPPINGLIST_ID.eq(listId)
            val options = ObserveQueryOptions(predicate, null)
            Amplify.DataStore.observeQuery(Item::class, options).map { it.items }
        } else {
            emptyFlow()
        }
    }

    fun saveItem(label: String, amount: Float?, unit: QuantityUnit?) {
        val listId = selectedList.value?.id
        if (listId != null) {
            viewModelScope.launch {
                val builder = Item.Builder()
                    .label(label)
                    .shoppinglistId(listId)

                if (amount != null) {
                    val quantity = Quantity.Builder()
                        .amount(amount.toDouble())
                        .unit(unit)
                        .build()
                    builder.quantity(quantity)
                }

                val item = builder.build()
                Amplify.DataStore.save(item)
            }
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
                _selectedList.value = list
            }
        }
    }

    fun selectList(list: ShoppingList) {
        _selectedList.value = list
    }
}