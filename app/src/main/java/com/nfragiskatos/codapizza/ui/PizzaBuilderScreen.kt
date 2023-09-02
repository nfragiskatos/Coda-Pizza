package com.nfragiskatos.codapizza.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nfragiskatos.codapizza.R
import com.nfragiskatos.codapizza.model.Pizza
import com.nfragiskatos.codapizza.model.Size
import com.nfragiskatos.codapizza.model.Topping
import java.text.NumberFormat

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun PizzaBuilderScreen(
    modifier: Modifier = Modifier
) {
    var pizza by rememberSaveable {
        mutableStateOf(Pizza())
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    Column(modifier = modifier.fillMaxWidth()) {


        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier
                .fillMaxWidth()

        ) {
            TextField(
                readOnly = true,
                value = stringResource(id = pizza.size.label),
                onValueChange = {},
                label = {
                    Text(
                        "Size"
                    )
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = modifier.fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = modifier.fillMaxWidth()
            ) {
                Size.values().forEach { size ->
                    DropdownMenuItem(onClick = {
                        pizza = pizza.withSize(size)
                        expanded = false
                    }) {
                        Text(text = stringResource(id = size.label))
                    }
                }
            }
        }

        ToppingsList(
            pizza = pizza,
            onEditPizza = { pizza = it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = true)
        )

        OrderButton(
            pizza = pizza,
            modifier = Modifier
                .fillMaxWidth()
                .padding(64.dp)
        )

    }
}

@Composable
private fun ToppingsList(
    pizza: Pizza,
    onEditPizza: (Pizza) -> Unit,
    modifier: Modifier = Modifier
) {

    var toppingBeingAdded by rememberSaveable {
        mutableStateOf<Topping?>(null)
    }

    toppingBeingAdded?.let { topping: Topping ->
        ToppingPlacementDialog(
            topping = topping,
            onSetToppingPlacement = { placement ->
                onEditPizza(pizza.withTopping(topping = topping, placement = placement))
            },
            onDismissRequest = { toppingBeingAdded = null }
        )
    }


    LazyColumn(modifier = modifier) {
        items(Topping.values()) { topping ->
            ToppingCell(
                topping = topping,
                placement = pizza.toppings[topping],
                onClickTopping = {
                    toppingBeingAdded = topping
                }
            )
        }
    }
}

@Composable
private fun OrderButton(
    pizza: Pizza,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        onClick = { /*TODO*/ }
    ) {
        val currencyFormatter = remember {
            NumberFormat.getCurrencyInstance()
        }
        val price = currencyFormatter.format(pizza.price)
        Text(
            text = stringResource(
                id = R.string.place_order_button,
                price
            ).toUpperCase(Locale.current)
        )
    }
}