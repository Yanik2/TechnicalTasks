package com.yan.game.controller;

import com.yan.game.service.GameDaoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DBToggleController {

    private GameDaoService gameDaoService;

    public DBToggleController(GameDaoService gameDaoService) {
        this.gameDaoService = gameDaoService;
    }
    @PostMapping("/enableJdbc")
    public void switchDaoToJdbcTemplate(@RequestParam Boolean enableJdbc) {
        gameDaoService.setJdbcEnabled(enableJdbc);
    }
}
