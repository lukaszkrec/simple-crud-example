package com.example.crudexample;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    MovieRepository repository;


    @GetMapping
    public List<Movie> getALl() {
        return repository.getAllMovies();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable("id") int id) {
        return repository.getById(id);
    }

    @PostMapping
    public int add(@RequestBody List<Movie> movies) {
        return repository.save(movies);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable int id, @RequestBody Movie updatedMovie) {
        Movie movie = repository.getById(id);

        if (movie != null) {
            movie.setName(updatedMovie.getName());
            movie.setRating(updatedMovie.getRating());

            repository.update(movie);

            return 1;
        } else {
            return -1;
        }
    }

    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable int id, @RequestBody Movie updatedMovie) {
        Movie movie = repository.getById(id);

        if (movie != null) {
            if (updatedMovie.getName() != null) movie.setName(updatedMovie.getName());
            if (updatedMovie.getRating() > 0) movie.setRating(updatedMovie.getRating());
            repository.update(movie);

            return 1;
        } else {
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable int id) {
        return repository.delete(id);
    }

}
