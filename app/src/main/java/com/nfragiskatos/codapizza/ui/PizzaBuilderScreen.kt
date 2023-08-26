package com.nfragiskatos.codapizza.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nfragiskatos.codapizza.R
import com.nfragiskatos.codapizza.model.Pizza
import com.nfragiskatos.codapizza.model.Topping
import com.nfragiskatos.codapizza.model.ToppingPlacement

private var pizza by mutableStateOf(Pizza())

@Preview
@Composable
fun PizzaBuilderScreen(
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier) {
        ToppingsList(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = true)
        )
        OrderButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(64.dp)
        )

    }
}

@Composable
private fun ToppingsList(
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(Topping.values()) { topping ->
            ToppingCell(
                topping = topping,
                placement = pizza.toppings[topping],
                onClickTopping = {
                    val isOnPizza = pizza.toppings[topping] != null
                    pizza = pizza.withTopping(
                        topping = topping,
                        placement = if (isOnPizza) {
                            null
                        } else {
                            ToppingPlacement.All
                        }
                    )
                }
            )
        }
    }
}

@Composable
private fun OrderButton(
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        onClick = { /*TODO*/ }
    ) {
        Text(
            text = stringResource(id = R.string.place_order_button).toUpperCase(Locale.current)
        )
    }
}