package com.nfragiskatos.codapizza.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.nfragiskatos.codapizza.model.Topping
import com.nfragiskatos.codapizza.model.ToppingPlacement

@Preview
@Composable
private fun ToppingCellPreview() {
    ToppingCell(
        topping = Topping.Pepperoni,
        placement = ToppingPlacement.Left,
        onClickTopping = {}
    )
}

@Composable
fun ToppingCell(
    topping: Topping,
    placement: ToppingPlacement?,
    onClickTopping: () -> Unit
) {
    Row {
        Checkbox(checked = true, onCheckedChange = {/* TODO */})
        Column {
            Text(text = stringResource(id = topping.toppingName))
            Text(text = "Whole Pizza")
        }
    }
}