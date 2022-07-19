package com.test.movie.dao.mappers;

import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class CreateOrder extends SqlUpdate {
    public CreateOrder(DataSource dataSource, String sql) {
        super(dataSource, sql);
        declareParameter(new SqlParameter("movieId", Types.INTEGER));
        declareParameter(new SqlParameter("time", Types.VARCHAR));
        setGeneratedKeysColumnNames("id");
        setReturnGeneratedKeys(true);
    }
}
