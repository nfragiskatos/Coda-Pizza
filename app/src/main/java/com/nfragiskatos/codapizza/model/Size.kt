package com.nfragiskatos.codapizza.model

import androidx.annotation.StringRes
import com.nfragiskatos.codapizza.R

enum class Size(
    @StringRes val label: Int,
    val price: Double,
) {
    Small(R.string.size_small, 7.99),
    Medium(R.string.size_medium, 9.99),
    Large(R.string.size_Large, 12.99),
    ExtraLarge(R.string.size_Extra_large, 15.99)
}