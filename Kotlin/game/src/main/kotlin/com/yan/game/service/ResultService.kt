package com.yan.game.service

import com.yan.game.dto.InfoDto
import com.yan.game.responses.Response

interface ResultService {
    fun getUserInfo(userId: Int): List<InfoDto>
    fun getLevelInfo(levelId: Int): List<InfoDto>
    fun setInfo(infoDto: InfoDto): Response
}