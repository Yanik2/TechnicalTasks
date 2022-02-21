package com.yan.game.entity

import javax.persistence.*

@Entity
@Table(name = "result_table")
class Result(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,
    @ManyToOne(targetEntity = User::class)
    val user: User,
    @ManyToOne
    val level: Level,
    @Column(name = "points")
    val points: Int
)
