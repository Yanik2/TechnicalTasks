package com.yan.game.entity

import javax.persistence.*

@Entity
@Table(name = "level_table")
class Level(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    @Column(name = "level_name")
    val name: String
)