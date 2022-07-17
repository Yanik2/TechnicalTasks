package com.yan.game.dao.mappers;

import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertResult extends SqlUpdate {
    public InsertResult(DataSource dataSource, String sql) {
        super(dataSource, sql);
        declareParameter(new SqlParameter("userId", Types.INTEGER));
        declareParameter(new SqlParameter("levelId", Types.INTEGER));
        declareParameter(new SqlParameter("points", Types.INTEGER));
    }
}
