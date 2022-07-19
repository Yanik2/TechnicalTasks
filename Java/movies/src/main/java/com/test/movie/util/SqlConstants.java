package com.test.movie.util;

public class SqlConstants {
    public static final String SELECT_MOVIE_BY_ID = "select id, name, director from movie " +
            "where id = :id";
    public static final String SELECT_ALL_WITH_OFFSET = "select id, name, director from movie " +
            "limit :limit offset :offset";
    public static final String CREATE_MOVIE = "insert into movie(name, director) " +
            "values(:name, :director)";
    public static final String UPDATE_MOVIE = "update movie set name = :name, director = :director " +
            "where id = :id";
    public static final String DELETE_MOVIE = "delete from movie where id = :id";
    public static final String CREATE_ORDER = "insert into order_table(movie_id, time) " +
            "values(:movieId, :time)";
    public static final String UPDATE_ORDER = "update order_table set movie_id = :movieId, time = :time " +
            "where id = :id";
    public static final String DELETE_ORDER = "delete from order_table where id = :id";
    public static final String SELECT_ALL_ORDERS_WITH_OFFSET = "select o.id, o.time, o.movie_id, m.id as movieId, " +
            "m.name, m.director from order_table as o join movie as m on o.movie_id = m.id " +
            "limit :limit offset :offset";
    public static final String SELECT_ORDER_BY_ID = "select o.id, o.time, o.movie_id, m.id as movieId, " +
            "m.name, m.director from order_table as o join movie as m on o.movie_id = m.id " +
            "where o.id = :id";
}
