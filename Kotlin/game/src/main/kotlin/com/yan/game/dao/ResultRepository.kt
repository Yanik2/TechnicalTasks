package com.yan.game.dao

import com.yan.game.entity.Result as Score
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ResultRepository: JpaRepository<Score, Int> {
    fun findByUserId(userId: Int): List<Score>
    fun findByLevelId(levelId: Int): List<Score>
}