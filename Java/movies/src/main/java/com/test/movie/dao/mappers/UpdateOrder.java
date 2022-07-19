package com.test.movie.dao.mappers;

import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class UpdateOrder extends SqlUpdate {
    public UpdateOrder(DataSource dataSource, String sql) {
        super(dataSource, sql);
        declareParameter(new SqlParameter("id", Types.INTEGER));
        declareParameter(new SqlParameter("movieId", Types.INTEGER));
        declareParameter(new SqlParameter("time", Types.VARCHAR));
        setGeneratedKeysColumnNames("id");
        setReturnGeneratedKeys(true);
    }
}
