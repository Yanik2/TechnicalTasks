package com.test.movie.service.impl;

import com.test.movie.dao.MovieDao;
import com.test.movie.entity.Movie;
import com.test.movie.service.IMoviesService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class MovieServiceImpl implements IMoviesService {
    private MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public Movie findById(int id) {
        return movieDao.findById(id);
    }

    public List<Movie> findAllMovies(Map<String, String> map) {
        return movieDao.findAll(map);
    }

    public void createMovie(Movie movie) {
        movieDao.createMovie(movie);
    }

    public void updateMovie(Movie movie) {
        movieDao.updateMovie(movie);
    }

    public void deleteMovie(Movie movie) {
        movieDao.deleteMovie(movie);
    }

    public void switchJdbc(boolean isJdbcEnable) {
        movieDao.setJdbcEnabled(isJdbcEnable);
    }

}
