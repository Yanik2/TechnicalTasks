package com.test.movie.controller;

import com.test.movie.entity.Movie;
import com.test.movie.service.IMoviesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private IMoviesService movieService;

    public MovieController(IMoviesService movieService) {
        this.movieService = movieService;
    }
    @GetMapping
    public List<Movie> getMovies(@RequestParam Map<String, String> params) {
        return movieService.findAllMovies(params);
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable String id) {
        return movieService.findById(Integer.parseInt(id));
    }

    @PostMapping
    public void createMovie(@RequestBody Movie movie) {
        movieService.createMovie(movie);
    }

    @PutMapping
    public void updateMovie(@RequestBody Movie movie) {
        movieService.updateMovie(movie);
    }

    @DeleteMapping
    public void deleteMovie(@RequestBody Movie movie) {
       movieService.deleteMovie(movie);
    }

    @PostMapping("/switchJdbc")
    public void switchJdbc(@RequestParam boolean isJdbcEnabled) {
        movieService.switchJdbc(isJdbcEnabled);
    }
}
