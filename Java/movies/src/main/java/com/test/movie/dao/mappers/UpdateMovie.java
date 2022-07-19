package com.test.movie.dao.mappers;

import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class UpdateMovie extends SqlUpdate {
    public UpdateMovie(DataSource dataSource, String sql) {
        super(dataSource, sql);
        declareParameter(new SqlParameter("id", Types.INTEGER));
        declareParameter(new SqlParameter("name", Types.VARCHAR));
        declareParameter(new SqlParameter("director", Types.VARCHAR));
    }
}
