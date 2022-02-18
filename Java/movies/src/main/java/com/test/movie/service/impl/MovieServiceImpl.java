package com.test.movie.service.impl;

import com.test.movie.entity.Movie;
import com.test.movie.repository.MovieRepository;
import com.test.movie.service.IMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MovieServiceImpl implements IMoviesService {
    @Autowired
    private MovieRepository movieRepository;

    public static Specification<Movie> getMovies(String name, String director) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> preds = new ArrayList<>();
            if(name != null)
                preds.add(criteriaBuilder.equal(root.get("name"), name));
            if(director != null)
                preds.add(criteriaBuilder.equal(root.get("director"), director));

            return criteriaBuilder.and(preds.toArray(new Predicate[0]));
        };
    }

    public Movie findById(int id) {
        return movieRepository.findById(id).orElse(null);
    }

    public List<Movie> findAllMovies(Map<String, String> map) {
        Integer pageNumber = Integer.parseInt(map.get("pageNumber"));
        Integer pageSize = Integer.parseInt(map.get("pageSize"));
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Movie> movies = movieRepository.findAll(getMovies(map.get("name"), map.get("director")), page);
        return movies.getContent();
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.saveAndFlush(movie);
    }

    public Movie updateMovie(Movie movie) {
        return movieRepository.saveAndFlush(movie);
    }

    public Movie deleteMovie(Movie movie) {
        movieRepository.delete(movie);
        return movie;
    }

}
