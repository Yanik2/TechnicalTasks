package com.yan.game.dao;

import com.yan.game.dao.jdbctemplate.GameJdbcTemplate;
import com.yan.game.dao.jpa.LevelRepository;
import com.yan.game.dao.jpa.ResultRepository;
import com.yan.game.dao.jpa.UserRepository;
import com.yan.game.entity.Level;
import com.yan.game.entity.Result;
import com.yan.game.entity.User;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class GameDao {

    private GameJdbcTemplate gameJdbcTemplate;
    private UserRepository userRepository;
    private LevelRepository levelRepository;
    private ResultRepository resultRepository;

    public GameDao(GameJdbcTemplate gameJdbcTemplate,
                   UserRepository userRepository,
                   LevelRepository levelRepository,
                   ResultRepository resultRepository) {
        this.gameJdbcTemplate = gameJdbcTemplate;
        this.userRepository = userRepository;
        this.levelRepository = levelRepository;
        this.resultRepository = resultRepository;
    }

    private Boolean isJdbcEnabled = false;

    public Optional<User> findUserById(Long id) {
        return isJdbcEnabled ? gameJdbcTemplate.findUserById(id) : userRepository.findById(id);
    }

    public Optional<Level> findLevelById(Long id) {
        return isJdbcEnabled ? gameJdbcTemplate.findLevelById(id) : levelRepository.findById(id);
    }

    public void saveResult(Result result) {
        if(isJdbcEnabled) gameJdbcTemplate.saveResult(result);
        else resultRepository.save(result);
    }

    public void setJdbcEnabled(Boolean jdbcEnabled) {
        isJdbcEnabled = jdbcEnabled;
    }
}
