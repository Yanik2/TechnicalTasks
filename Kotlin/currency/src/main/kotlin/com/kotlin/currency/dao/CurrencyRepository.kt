package com.kotlin.currency.dao

import com.kotlin.currency.entities.Currency
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate


@Repository
interface CurrencyRepository: JpaRepository<Currency, Int> {
    fun findByCcy(ccy: String): List<Currency>
    fun findByDate(date: LocalDate): List<Currency>
    @Query(value = "SELECT * FROM CURRENCY WHERE DATE > ?1 AND DATE <= ?2 ORDER BY DATE",  nativeQuery = true)
    fun findByRange(startDate: String, endDate: String): List<Currency>
}