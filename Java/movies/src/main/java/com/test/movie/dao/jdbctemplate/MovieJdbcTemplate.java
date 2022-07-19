package com.test.movie.dao.jdbctemplate;

import static com.test.movie.util.SqlConstants.CREATE_MOVIE;
import static com.test.movie.util.SqlConstants.DELETE_MOVIE;
import static com.test.movie.util.SqlConstants.SELECT_ALL_WITH_OFFSET;
import static com.test.movie.util.SqlConstants.SELECT_MOVIE_BY_ID;
import static com.test.movie.util.SqlConstants.UPDATE_MOVIE;

import com.test.movie.dao.mappers.CreateMovie;
import com.test.movie.dao.mappers.DeleteMovie;
import com.test.movie.dao.mappers.UpdateMovie;
import com.test.movie.entity.Movie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MovieJdbcTemplate {
    private static final Logger logger = LoggerFactory.getLogger(MovieJdbcTemplate.class);
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private DataSource dataSource;

    public MovieJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                             DataSource dataSource) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.dataSource = dataSource;
    }
    public Movie findById(int id) {
        logger.info("[JDBC TEMPLATE] - find by id method");
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return namedParameterJdbcTemplate.queryForObject(SELECT_MOVIE_BY_ID, params, (rs, rowNum) -> {
            Movie m = new Movie();
            m.setId(rs.getInt("id"));
            m.setName(rs.getString("name"));
            m.setDirector(rs.getString("director"));
            return m;
        });
    }

    public List<Movie> findAll(Map<String, String> map) {
        logger.info("[JDBC TEMPLATE] - find all method");
        Map<String, Object> params = new HashMap<>();
        Integer limit = Integer.valueOf(map.get("pageSize"));
        Integer offset = Integer.valueOf(map.get("pageNumber")) * limit;
        params.put("limit", limit);
        params.put("offset", offset);
        return namedParameterJdbcTemplate.query(SELECT_ALL_WITH_OFFSET, params, (rs) -> {
            List<Movie> movies = new ArrayList<>();
            while(rs.next()) {
                Movie m = new Movie();
                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                m.setDirector(rs.getString("director"));
                movies.add(m);
            }
            return movies;
        });
    }

    public void createMovie(Movie movie) {
        logger.info("[JDBC TEMPLATE] - create movie method");
        CreateMovie cm = new CreateMovie(dataSource, CREATE_MOVIE);
        Map<String, Object> params = new HashMap<>();
        params.put("name", movie.getName());
        params.put("director", movie.getDirector());
        cm.updateByNamedParam(params);
    }
    public void updateMovie(Movie movie) {
        logger.info("[JDBC TEMPLATE] - update movie method");
        UpdateMovie um = new UpdateMovie(dataSource, UPDATE_MOVIE);
        Map<String, Object> params = new HashMap<>();
        params.put("id", movie.getId());
        params.put("name", movie.getName());
        params.put("director", movie.getDirector());
        um.updateByNamedParam(params);
    }
    public void deleteMovie(Movie movie) {
        logger.info("[JDBC TEMPLATE] - delete movie method");
        DeleteMovie dm = new DeleteMovie(dataSource, DELETE_MOVIE);
        Map<String, Object> params = new HashMap<>();
        params.put("id", movie.getId());
        dm.updateByNamedParam(params);
    }
}
