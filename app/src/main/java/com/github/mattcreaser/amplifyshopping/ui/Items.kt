package com.github.mattcreaser.amplifyshopping.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.amplifyframework.datastore.generated.model.Item
import com.github.mattcreaser.amplifyshopping.R

@Composable
fun Item(item: Item) {
    Row {
        Text(text = item.label)
    }
}

@Composable
fun ItemList(listItems: List<Item>) {
    LazyColumn {
        items(items = listItems, key = { it.id }) { item ->
            Item(item)
        }
    }
}

@Composable
fun AddItemDialog(
    onDismiss: () -> Unit,
    onSave: (String) -> Unit
) {
    var label by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = { onSave(label) }) {
                Text(stringResource(R.string.action_save))
            }
        },
        title = {
            Text(stringResource(R.string.title_add_item))
        },
        text = {
            TextField(
                value = label,
                onValueChange = { label = it },
                label = { Text(stringResource(R.string.label_item_name)) }
            )
        }
    )
}