package com.yan.game.service.impl;

import com.yan.game.dao.GameDao;
import com.yan.game.dto.InfoDto;
import com.yan.game.entity.Level;
import com.yan.game.entity.Result;
import com.yan.game.entity.User;
import com.yan.game.exception.EntityAbsentException;
import com.yan.game.service.IResultService;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultServiceImpl implements IResultService {
    private static final Logger logger = LoggerFactory.getLogger(ResultServiceImpl.class);
    private GameDao gameDao;

    public ResultServiceImpl(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @Override
    public List<InfoDto> getUserInfo(Long id) {
        List<Result> results = gameDao.findUserById(id)
                .map(User::getResults)
                .orElse(Collections.emptyList());
        return getInfo(results);
    }

    @Override
    public List<InfoDto> getLevelInfo(Long id) {
        List<Result> results = gameDao.findLevelById(id)
                .map(Level::getResults)
                .orElse(Collections.emptyList());
        return getInfo(results);
    }

    private List<InfoDto> getInfo(List<Result> results) {
        return results.stream()
                .map(this::resultsToDto)
                .sorted((infoDto, t1) -> {
                    if (infoDto.getResult() != t1.getResult()) {
                        return infoDto.getResult() < t1.getResult() ? 1 : -1;
                    } else {
                        return infoDto.getUserId() > t1.getUserId() ? 1 : -1;
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public void setInfo(InfoDto infoDto) {
        User user = getUser(infoDto.getUserId());
        Level level = getLevel(infoDto.getLevelId());
        if(user == null || level == null)
            return;

        Result result = new Result();
        result.setUser(user);
        result.setLevel(level);
        result.setPoints(infoDto.getResult());
        gameDao.saveResult(result);
    }

    private User getUser(Long id) {
        User user = null;
        try {
            user = gameDao.findUserById(id)
                    .orElseThrow(() -> new EntityAbsentException("user with id: " + id));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return user;
    }

    private Level getLevel(Long id) {
        Level level = null;
        try {
            level = gameDao.findLevelById(id).
                    orElseThrow(() -> new EntityAbsentException("level with id: " + id));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return level;
    }

    private InfoDto resultsToDto(Result result) {
        InfoDto userInfoDto = new InfoDto();
        userInfoDto.setResult(result.getPoints());
        userInfoDto.setUserId(result.getUser().getId());
        userInfoDto.setLevelId(result.getLevel().getId());
        return userInfoDto;
    }
}
