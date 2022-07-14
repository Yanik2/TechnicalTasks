package com.yan.game.service.impl;

import com.yan.game.dao.LevelRepository;
import com.yan.game.dao.ResultRepository;
import com.yan.game.dao.UserRepository;
import com.yan.game.dto.InfoDto;
import com.yan.game.entity.Level;
import com.yan.game.entity.Result;
import com.yan.game.entity.User;
import com.yan.game.service.IResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultServiceImpl implements IResultService {
    private ResultRepository resultRepository;
    private UserRepository userRepository;
    private LevelRepository levelRepository;

    public ResultServiceImpl(ResultRepository resultRepository,
                             UserRepository userRepository,
                             LevelRepository levelRepository) {
        this.resultRepository = resultRepository;
        this.levelRepository = levelRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<InfoDto> getUserInfo(Integer id) {
        return userRepository.findById(id)
                .map(User::getResults)
                .get().stream()
                .map(this::resultsToDto)
                .sorted((o1, o2) -> o1.getResult() != o2.getResult() ? o2.getResult() - o1.getResult() : o1.getLevelId() - o2.getLevelId())
                .collect(Collectors.toList());
    }

    @Override
    public List<InfoDto> getLevelInfo(Integer id) {
        return levelRepository.findById(id)
                .map(Level::getResults)
                .get().stream()
                .map(this::resultsToDto)
                .sorted((o1, o2) -> o1.getResult() != o2.getResult() ? o2.getResult() - o1.getResult() : o1.getUserId() - o2.getUserId())
                .collect(Collectors.toList());
    }

    @Override
    public void setInfo(InfoDto infoDto) {
        User user = userRepository.findById(infoDto.getUserId()).orElse(null);
        Level level = levelRepository.findById(infoDto.getLevelId()).orElse(null);
        Result result = new Result();
        result.setUser(user);
        result.setLevel(level);
        result.setPoints(infoDto.getResult());
        resultRepository.save(result);
    }


    private InfoDto resultsToDto(Result result) {
        InfoDto userInfoDto = new InfoDto();
        userInfoDto.setResult(result.getPoints());
        userInfoDto.setUserId(result.getUser().getId());
        userInfoDto.setLevelId(result.getLevel().getId());
        return userInfoDto;
    }
}
