package com.github.mattcreaser.amplifyshopping.ui

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.mattcreaser.amplifyshopping.R
import com.github.mattcreaser.amplifyshopping.ShoppingListViewModel

@Composable
fun ShoppingListScreen() {
    var addItemDialogVisible by remember { mutableStateOf(false) }
    val viewModel = viewModel<ShoppingListViewModel>()

    val selectedList by viewModel.selectedList.collectAsState(null)

    Scaffold(
        floatingActionButton = {
            AddItemButton(onClick = { addItemDialogVisible = true })
        },
        topBar = {
            TopAppBar {
                val lists by viewModel.lists.collectAsState(initial = emptyList())
                ShoppingListSelector(
                    lists = lists,
                    selectedList = selectedList,
                    onAddList = viewModel::saveList,
                    onSelectList = viewModel::selectList
                )
            }
        }
    ) {
        val items by viewModel.items.collectAsState(initial = emptyList())
        ItemList(listItems = items, onItemClick = { })

        if (addItemDialogVisible) {
            AddItemDialog(
                onDismiss = { addItemDialogVisible = false },
                onSave = { label, quantity, unit ->
                    addItemDialogVisible = false
                    viewModel.saveItem(label, quantity, unit)
                }
            )
        }
    }
}

@Composable
fun AddItemButton(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(
            painter = rememberVectorPainter(Icons.Filled.Add),
            contentDescription = stringResource(R.string.title_add_item)
        )
    }
}