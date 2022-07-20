package com.github.mattcreaser.amplifyshopping.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.amplifyframework.datastore.generated.model.ShoppingList
import com.github.mattcreaser.amplifyshopping.R

@Composable
fun ShoppingList(list: ShoppingList, onClick: () -> Unit) {
    Text(list.label, modifier = Modifier.clickable { onClick() })
}

@Composable
fun ShoppingListSelector(
    lists: List<ShoppingList>,
    selectedList: ShoppingList?,
    onSelectList: (ShoppingList) -> Unit,
    onAddList: (String) -> Unit
) {
    var dropdownExpanded by remember { mutableStateOf(false) }

    Row(modifier = Modifier.clickable { dropdownExpanded = !dropdownExpanded }) {
        Text(text = selectedList?.label ?: "")
        val rotation by animateFloatAsState(targetValue = if (dropdownExpanded) 180f else 0f)
        Icon(
            painter = rememberVectorPainter(Icons.Default.ArrowDropDown),
            contentDescription = "",
            modifier = Modifier.rotate(rotation)
        )
    }

    DropdownMenu(
        modifier = Modifier.padding(horizontal = 16.dp),
        expanded = dropdownExpanded,
        onDismissRequest = { dropdownExpanded = false }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .verticalScroll(rememberScrollState())
        ) {
            for (list in lists) {
                ShoppingList(
                    list = list,
                    onClick = {
                        dropdownExpanded = false
                        onSelectList(list)
                    }
                )
            }

            var newListName by remember { mutableStateOf("") }
            Row {
                OutlinedTextField(
                    value = newListName,
                    onValueChange = { newListName = it },
                    label = { Text(stringResource(R.string.label_add_list)) },
                    trailingIcon = {
                        Icon(
                            painter = rememberVectorPainter(Icons.Default.Add),
                            contentDescription = stringResource(R.string.label_add_list),
                            modifier = Modifier.clickable {
                                if (newListName.isNotBlank()) {
                                    dropdownExpanded = false
                                    onAddList(newListName)
                                }
                            }
                        )
                    }
                )
            }

        }
    }
}