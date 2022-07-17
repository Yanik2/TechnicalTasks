package com.yan.game.dao.jdbctemplate;

import static com.yan.game.utils.SqlConstants.INSERT_RESULT;
import static com.yan.game.utils.SqlConstants.SELECT_LEVEL_BY_ID_WITH_RESULTS;
import static com.yan.game.utils.SqlConstants.SELECT_USER_BY_ID_WITH_RESULTS;

import com.yan.game.dao.mappers.LevelMapper;
import com.yan.game.dao.mappers.InsertResult;
import com.yan.game.dao.mappers.UserMapper;
import com.yan.game.entity.Level;
import com.yan.game.entity.Result;
import com.yan.game.entity.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class GameJdbcTemplate {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private DataSource dataSource;
    private InsertResult insertResult;

    public GameJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        insertResult = new InsertResult(dataSource, INSERT_RESULT);
    }

    public Optional<User> findUserById(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", id);
        User u = jdbcTemplate.query(SELECT_USER_BY_ID_WITH_RESULTS, params, new UserMapper());
        return Optional.ofNullable(u);
    }

    public Optional<Level> findLevelById(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("levelId", id);
        Level l = jdbcTemplate.query(SELECT_LEVEL_BY_ID_WITH_RESULTS, params, new LevelMapper());
        return Optional.ofNullable(l);
    }

    public void saveResult(Result result) {
        Map<String, Long> params = new HashMap<>();
        params.put("userId", result.getUser().getId());
        params.put("levelId", result.getLevel().getId());
        params.put("points", result.getPoints());
        insertResult.updateByNamedParam(params);
    }
}
