package com.test.movie.service;

import com.test.movie.entity.Movie;

import java.util.List;
import java.util.Map;

public interface IMoviesService {
    Movie findById(int id);
    List<Movie> findAllMovies(Map<String, String> map);
    Movie createMovie(Movie movie);
    Movie updateMovie(Movie movie);
    Movie deleteMovie(Movie movie);
}
