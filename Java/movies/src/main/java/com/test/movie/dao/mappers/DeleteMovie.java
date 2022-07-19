package com.test.movie.dao.mappers;

import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class DeleteMovie extends SqlUpdate {
    public DeleteMovie(DataSource dataSource, String sql) {
        super(dataSource, sql);
        declareParameter(new SqlParameter("id", Types.INTEGER));
    }
}
