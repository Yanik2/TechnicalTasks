package com.yan.game.dao.mappers;

import com.yan.game.entity.Level;
import com.yan.game.entity.Result;
import com.yan.game.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class UserMapper implements ResultSetExtractor<User> {
    @Override
    public User extractData(ResultSet rs) throws SQLException, DataAccessException {
        User user = null;
        while(rs.next()) {
            if(user == null) {
                user = new User();
                user.setId(rs.getLong("user_id"));
                user.setUsername(rs.getString("username"));
                user.setResults(new ArrayList<>());
            }
            Result r = new Result();
            r.setId(rs.getLong("result_id"));
            r.setPoints(rs.getLong("points"));
            r.setUser(user);
            Level l = new Level();
            l.setId(rs.getLong("level_id"));
            r.setLevel(l);
            user.getResults().add(r);
        }
        return user;
    }
}
