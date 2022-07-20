package com.github.mattcreaser.amplifyshopping.ui

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
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

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { addItemDialogVisible = true }) {
                Icon(
                    painter = rememberVectorPainter(Icons.Filled.Add),
                    contentDescription = stringResource(R.string.title_add_item)
                )
            }
        }
    ) {
        val items by viewModel.items.collectAsState(initial = emptyList())
        ItemList(listItems = items)

        if (addItemDialogVisible) {
            AddItemDialog(
                onDismiss = { addItemDialogVisible = false },
                onSave = {
                    addItemDialogVisible = false
                    viewModel.saveItem(it)
                }
            )
        }
    }
}