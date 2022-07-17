package com.yan.game.dao.jpa;

import com.yan.game.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelRepository extends JpaRepository<Level, Long> {
}
