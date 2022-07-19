package com.test.movie.dao;

public abstract class AbstractMovieDao {
    protected boolean isJdbcEnabled;

    public void setJdbcEnabled(boolean jdbcEnabled) {
        isJdbcEnabled = jdbcEnabled;
    }
}
