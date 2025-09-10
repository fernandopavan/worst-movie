package com.myorg.worst_movie.repository;

import com.myorg.worst_movie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAllByWinner(Boolean winner);

}

