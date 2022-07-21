package com.github.mattcreaser.amplifyshopping.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import com.amplifyframework.datastore.generated.model.Item
import com.amplifyframework.datastore.generated.model.Quantity
import com.amplifyframework.datastore.generated.model.QuantityUnit
import com.github.mattcreaser.amplifyshopping.R
import com.github.mattcreaser.amplifyshopping.ui.theme.AppTheme
import java.math.RoundingMode
import java.text.DecimalFormat

val QuantityUnit.displayName
    @Composable
    @ReadOnlyComposable
    get() = this.name.toLowerCase(Locale.current)

val QuantityFormat = DecimalFormat("0.###").apply {
    roundingMode = RoundingMode.DOWN
}

val Quantity.displayValue
    @Composable
    @ReadOnlyComposable
    get() = when (unit) {
        null -> QuantityFormat.format(amount)
        else -> "${QuantityFormat.format(amount)} ${unit.displayName}"
    }

@Composable
fun Item(item: Item) {
    Column(modifier = Modifier.padding(AppTheme.dimens.grid_2)) {
        Text(text = item.label)
        item.quantity?.let {
            Text(it.displayValue, style = AppTheme.typography.caption)
        }
    }
}

@Composable
fun ItemList(listItems: List<Item>) {
    LazyColumn {
        items(items = listItems, key = { it.id }) { item ->
            Column {
                Item(item)
                Divider()
            }
        }
    }
}

@Composable
fun AddItemDialog(
    onDismiss: () -> Unit,
    onSave: (String, Float?, QuantityUnit?) -> Unit
) {
    var label by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var unit by remember { mutableStateOf<QuantityUnit?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = { onSave(label, quantity.toFloatOrNull(), unit) }
            ) {
                Text(stringResource(R.string.action_save))
            }
        },
        title = {
            Text(stringResource(R.string.title_add_item), style = AppTheme.typography.h6)
        },
        text = {
            Column {
                OutlinedTextField(
                    value = label,
                    onValueChange = { label = it },
                    label = { Text(stringResource(R.string.label_item_name)) },
                    singleLine = true
                )
                Row {
                    OutlinedTextField(
                        modifier = Modifier.weight(0.6f),
                        value = quantity,
                        onValueChange = {
                            quantity = when (it.toDoubleOrNull()) {
                                null -> quantity
                                else -> it
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        label = { Text(stringResource(R.string.label_quantity)) },
                        singleLine = true
                    )
                    UnitSelector(
                        selectedUnit = unit,
                        onUnitSelect = { unit = it },
                        modifier = Modifier
                            .weight(0.4f)
                            .padding(start = AppTheme.dimens.grid_1, top = AppTheme.dimens.grid_1)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UnitSelector(
    selectedUnit: QuantityUnit?,
    onUnitSelect: (QuantityUnit?) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    val options = QuantityUnit.values()

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedUnit?.displayName ?: "",
            onValueChange = { },
            trailingIcon = { TrailingIcon(expanded = expanded) }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            DropdownMenuItem(onClick = {
                onUnitSelect(null)
                expanded = false
            }) {
                Text("-")
            }
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        onUnitSelect(selectionOption)
                        expanded = false
                    }
                ) {
                    Text(text = selectionOption.displayName)
                }
            }
        }
    }
}