package com.appiekap.movietheater.repository;

import com.appiekap.movietheater.model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {

}