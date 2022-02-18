package com.kotlin.currency.dto

import java.time.LocalDate

data class CurrencyDto(val ccy: String) {
    var buy: Double? = null
    var sale: Double? = null
    var date: LocalDate? = null
}
