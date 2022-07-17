package com.yan.game.service;

import com.yan.game.dao.GameDao;
import org.springframework.stereotype.Service;

@Service
public class GameDaoService {
    private GameDao gameDao;

    public GameDaoService(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public void setJdbcEnabled(Boolean isJdbcEnabled) {
        gameDao.setJdbcEnabled(isJdbcEnabled);
    }
}
