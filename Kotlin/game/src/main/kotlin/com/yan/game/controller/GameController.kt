package com.yan.game.controller

import com.yan.game.dto.InfoDto
import com.yan.game.responses.Response
import com.yan.game.service.ResultService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/game")
class GameController @Autowired constructor(val resultService: ResultService) {

    @GetMapping("/userinfo/{userId}")
    fun getUserInfo(@PathVariable userId: Int): List<InfoDto> {
        return resultService.getUserInfo(userId)
    }

    @GetMapping("/levelInfo/{levelId}")
    fun getLevelInfo(@PathVariable levelId: Int): List<InfoDto> {
        return resultService.getLevelInfo(levelId)
    }

    @PutMapping("/setInfo")
    fun setInfo(@RequestBody infoDto: InfoDto): Response {
        return resultService.setInfo(infoDto)
    }
}