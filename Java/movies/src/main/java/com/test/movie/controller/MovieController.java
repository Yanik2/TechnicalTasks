package com.test.movie.controller;

import com.test.movie.entity.Movie;
import com.test.movie.service.IMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private IMoviesService movieService;

    @GetMapping
    public List<Movie> getMovies(@RequestParam Map<String, String> params) {
        return movieService.findAllMovies(params);
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable String id) {
        return movieService.findById(Integer.parseInt(id));
    }

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }

    @PutMapping
    public Movie updateMovie(@RequestBody Movie movie) {
        return movieService.updateMovie(movie);
    }

    @DeleteMapping
    public Movie deleteMovie(@RequestBody Movie movie) {
       return movieService.deleteMovie(movie);
    }
}
