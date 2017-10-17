package com.appiekap.movietheater.controller;

import com.appiekap.movietheater.exception.NotFoundException;
import com.appiekap.movietheater.model.Movie;
import com.appiekap.movietheater.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/movie/")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    /**
     * create movie
     * @param movie to be created.
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void create(@RequestBody Movie movie){
        this.movieRepository.save(movie);
    }

    /**
     * getAll movies
     * @return Iterable of movies.
     */
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Iterable<Movie> getAll()
    {
        return this.movieRepository.findAll();
    }

    /**
     * toggleWatched movie
     * @param id Id of the movie.
     */
    @RequestMapping(value = "watched/{id}", method = RequestMethod.GET)
    public void toggleWatched(@PathVariable long id){
        Movie movie = movieRepository.findOne(id);

        if (movie == null) throw new NotFoundException();

        // Flip da shit.
        movie.setWatched(!movie.isWatched());

        movieRepository.save(movie);
    }

    /**
     * delete movie
     * @param id Id of the movie.
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {

        Movie movie = movieRepository.findOne(id);

        if (movie == null) throw new NotFoundException();

        movieRepository.delete(movie);
    }
}