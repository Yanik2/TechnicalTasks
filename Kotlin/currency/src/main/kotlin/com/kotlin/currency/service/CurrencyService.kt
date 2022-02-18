package com.kotlin.currency.service

import com.kotlin.currency.dao.CurrencyRepository
import com.kotlin.currency.dto.CurrencyDto
import com.kotlin.currency.entities.Currency
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.time.LocalDate

const val API_URI: String = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5"

@Service
class CurrencyService @Autowired constructor(val restTemplate: RestTemplate) {

    @Autowired
    lateinit var currencyRepository: CurrencyRepository

    fun currencies(): List<CurrencyDto>? {
        return restTemplate.getForObject(API_URI, Array<CurrencyDto>::class.java)
            ?.map { currencyDto: CurrencyDto ->
            currencyDto.date = LocalDate.now()
            currencyDto
        }?.toList()
    }

    fun codes(): List<String>? {
        return restTemplate.getForObject(API_URI, Array<CurrencyDto>::class.java)
            ?.map { c: CurrencyDto -> c.ccy }
    }
    fun getCurrenciesByCcy(ccy: String): List<CurrencyDto> {
        return currencyRepository.findByCcy(ccy).map(::toDto)
    }
    fun getCurrenciesByDate(date: String): List<CurrencyDto> {
        return currencyRepository.findByDate(LocalDate.parse(date))
            .map(::toDto)
    }
    fun getCurrenciesDateRange(start: String, end: String): Map<String, List<CurrencyDto>> {
        return currencyRepository.findByRange(start, end)
            .map(::toDto)
            .groupBy { c -> c.date.toString() }
    }

    private fun toDto(currency: Currency): CurrencyDto {
        var dto = CurrencyDto(currency.ccy)
        dto.buy = currency.buy
        dto.sale = currency.sale
        dto.date = currency.date
        return dto
    }
}