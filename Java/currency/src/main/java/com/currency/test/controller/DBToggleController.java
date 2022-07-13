package com.currency.test.controller;

import com.currency.test.service.DBToggleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DBToggleController {
    private DBToggleService dbToggleService;

    public DBToggleController(DBToggleService dbToggleService) {
        this.dbToggleService = dbToggleService;
    }

    @PostMapping
    public void switchDao(@RequestParam Boolean isJdbcEnabled) {
        dbToggleService.switchDataAccess(isJdbcEnabled);
    }
}
