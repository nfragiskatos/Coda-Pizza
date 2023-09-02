package com.nfragiskatos.codapizza.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pizza(
    val toppings: Map<Topping, ToppingPlacement> = emptyMap(),
    val size: Size = Size.Medium
) : Parcelable {
    val price: Double
        get() = size.price + toppings.asSequence()
            .sumOf { (_, toppingPlacement) ->
                when (toppingPlacement) {
                    ToppingPlacement.Left, ToppingPlacement.Right -> 0.5
                    ToppingPlacement.All -> 1.0
                }
            }

//    private val basePrice: Double
//        get() =
//            when (size) {
//                Size.Small -> 7.99
//                Size.Medium -> 9.99
//                Size.Large -> 12.99
//                Size.ExtraLarge -> 15.99
//            }


    fun withTopping(
        topping: Topping,
        placement: ToppingPlacement?
    ): Pizza {
        return copy(
            toppings = if (placement == null) {
                toppings - topping
            } else {
                toppings + (topping to placement)
            }
        )
    }

    fun withSize(size: Size): Pizza = copy(size = size)
}