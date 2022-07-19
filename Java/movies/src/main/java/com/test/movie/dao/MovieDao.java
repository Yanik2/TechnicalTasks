package com.test.movie.dao;

import com.test.movie.dao.jdbctemplate.MovieJdbcTemplate;
import com.test.movie.entity.Movie;
import com.test.movie.repository.MovieRepository;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class MovieDao extends AbstractMovieDao {
    private MovieRepository movieRepository;
    private MovieJdbcTemplate movieJdbcTemplate;

    public MovieDao(MovieRepository movieRepository, MovieJdbcTemplate movieJdbcTemplate) {
        this.movieRepository = movieRepository;
        this.movieJdbcTemplate = movieJdbcTemplate;
    }

    public Movie findById(int id) {
        return isJdbcEnabled ? movieJdbcTemplate.findById(id) :
                movieRepository.findById(id).orElse(null);
    }

    public List<Movie> findAll(Map<String, String> map) {
        return isJdbcEnabled ? movieJdbcTemplate.findAll(map) : getAllMovies(map);
    }

    public void createMovie(Movie movie) {
        if(isJdbcEnabled) movieJdbcTemplate.createMovie(movie);
        else movieRepository.saveAndFlush(movie);
    }

    public void updateMovie(Movie movie) {
        if(isJdbcEnabled) movieJdbcTemplate.updateMovie(movie);
        else movieRepository.saveAndFlush(movie);
    }

    public void deleteMovie(Movie movie) {
        if(isJdbcEnabled) movieJdbcTemplate.deleteMovie(movie);
        else movieRepository.delete(movie);
    }

    private List<Movie> getAllMovies(Map<String, String> map) {
        Integer pageNumber = Integer.parseInt(map.get("pageNumber"));
        Integer pageSize = Integer.parseInt(map.get("pageSize"));
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Movie> movies = movieRepository.findAll(page);
        return movies.getContent();
    }
}
