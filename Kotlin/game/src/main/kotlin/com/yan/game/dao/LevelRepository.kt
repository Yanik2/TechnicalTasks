package com.yan.game.dao

import com.yan.game.entity.Level
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LevelRepository: JpaRepository<Level, Int> {
}