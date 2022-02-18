package com.kotlin.currency.controller

import com.kotlin.currency.dto.CurrencyDto
import com.kotlin.currency.service.CurrencyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/currency")
class CurrencyController @Autowired constructor(val currencyService: CurrencyService) {

    @GetMapping("/")
    fun currency(): List<CurrencyDto>? = currencyService.currencies()

    @GetMapping("/codes")
    fun codes(): List<String>? = currencyService.codes()

    @GetMapping("/historical/byCcy")
    fun historicalCurrenciesByCcy(@RequestParam ccy: String): List<CurrencyDto> = currencyService.getCurrenciesByCcy(ccy)

    @GetMapping("/historical/byDate")
    fun historicalByDate(@RequestParam date: String): List<CurrencyDto> = currencyService.getCurrenciesByDate(date)

    @GetMapping("/historical/dateRange")
    fun historicalDateRange(@RequestParam startDate: String, @RequestParam endDate: String): Map<String, List<CurrencyDto>> =
        currencyService.getCurrenciesDateRange(startDate, endDate)
}