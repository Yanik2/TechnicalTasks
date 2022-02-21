package com.yan.game.entity

import javax.persistence.*

@Entity
@Table(name = "user_table")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    @Column(name = "user_name")
    val name: String
)