package com.yan.game.controller;

import com.yan.game.dto.InfoDto;
import com.yan.game.service.IResultService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    private IResultService resultService;

    public GameController(IResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping("/userinfo/{userId}")
    public List<InfoDto> getUserInfo(@PathVariable Long userId) {
        return resultService.getUserInfo(userId);
    }

    @GetMapping("/levelinfo/{levelId}")
    public List<InfoDto> getLevelInfo(@PathVariable Long levelId) {
        return resultService.getLevelInfo(levelId);
    }

    @PutMapping("/setinfo")
    public InfoDto setInfo(@RequestBody InfoDto infoDto) {
        resultService.setInfo(infoDto);
        return infoDto;
    }
}
