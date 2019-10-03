package com.stackroute.userservice.service;

import com.stackroute.userservice.exceptions.MovieExistsByIdException;
import com.stackroute.userservice.exceptions.MovieNotFoundException;
import com.stackroute.userservice.model.Movie;
import java.util.List;

public interface MovieService {

    public boolean saveMovie(Movie movie) throws MovieExistsByIdException;
    public List<Movie> getallMovies();
    public boolean update(Movie movie);
    public boolean deleteMovie(int id) throws MovieNotFoundException;
    public List<Movie> getMoviesbyTitle(String title) throws MovieNotFoundException;
}
