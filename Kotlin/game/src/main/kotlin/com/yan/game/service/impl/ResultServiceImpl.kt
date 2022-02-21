package com.yan.game.service.impl

import com.yan.game.dao.LevelRepository
import com.yan.game.entity.Result as Score
import com.yan.game.dao.ResultRepository
import com.yan.game.dao.UserRepository
import com.yan.game.dto.InfoDto
import com.yan.game.responses.FailureResponse
import com.yan.game.responses.Response
import com.yan.game.responses.SuccessResponse
import com.yan.game.service.ResultService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ResultServiceImpl @Autowired constructor(
    private val resultRepository: ResultRepository,
    private val userRepository: UserRepository,
    private val levelRepository: LevelRepository
) : ResultService {

    override fun getUserInfo(userId: Int): List<InfoDto> {
        return resultRepository.findByUserId(userId)
            .map(::toDto)
    }

    override fun getLevelInfo(levelId: Int): List<InfoDto> {
        return resultRepository.findByLevelId(levelId)
            .map(::toDto)
    }

    override fun setInfo(infoDto: InfoDto): Response {
        if(!checkRequest(infoDto))
            return FailureResponse()
        val user = userRepository.findById(infoDto.userId!!)
        val level = levelRepository.findById(infoDto.levelId!!)
        val points = infoDto.points
        val result = Score(null, user.get(), level.get(), points ?: -1)
        resultRepository.saveAndFlush(result)
        return SuccessResponse()
    }

    private fun checkRequest(infoDto: InfoDto): Boolean {
        return infoDto.userId != null &&
                infoDto.levelId != null &&
                infoDto.points != null
    }

    fun toDto(result: Score): InfoDto {
        var dto = InfoDto()
        dto.userId = result.user.id
        dto.levelId = result.level.id
        dto.points = result.points
        return dto
    }
}