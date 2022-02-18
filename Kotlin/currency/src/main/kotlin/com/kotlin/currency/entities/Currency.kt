package com.kotlin.currency.entities

import java.time.LocalDate
import javax.persistence.*

@Entity
data class Currency(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    @Column(name = "ccy")
    val ccy: String,
    @Column(name = "buy")
    val buy: Double,
    @Column(name = "sale")
    val sale: Double,
    @Column(name = "date")
    val date: LocalDate
    )
