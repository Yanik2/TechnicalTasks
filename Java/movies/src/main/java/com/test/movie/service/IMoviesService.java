package com.test.movie.service;

import com.test.movie.entity.Movie;

import java.util.List;
import java.util.Map;

public interface IMoviesService {
    Movie findById(int id);
    List<Movie> findAllMovies(Map<String, String> map);
    void createMovie(Movie movie);
    void updateMovie(Movie movie);
    void deleteMovie(Movie movie);

    void switchJdbc(boolean isJdbcEnabled);
}
