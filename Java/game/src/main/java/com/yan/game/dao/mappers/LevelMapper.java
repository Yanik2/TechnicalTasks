package com.yan.game.dao.mappers;

import com.yan.game.entity.Level;
import com.yan.game.entity.Result;
import com.yan.game.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class LevelMapper implements ResultSetExtractor<Level> {
    @Override
    public Level extractData(ResultSet rs) throws SQLException, DataAccessException {
        Level level = null;
        while(rs.next()) {
            if(level == null) {
                level = new Level();
                level.setId(rs.getLong("level_id"));
                level.setLevelname(rs.getString("levelname"));
                level.setResults(new ArrayList<>());
            }
            Result r = new Result();
            r.setId(rs.getLong("result_id"));
            r.setLevel(level);
            r.setPoints(rs.getLong("points"));
            User u = new User();
            u.setId(rs.getLong("user_id"));
            r.setUser(u);
            level.getResults().add(r);
        }
        return level;
    }
}
