package com.stackroute.userservice.service;

import com.stackroute.userservice.model.Movie;
import java.util.List;

public interface MovieService {

    public boolean saveMovie(Movie movie);
    public List<Movie> getallMovies();
    public boolean update(Movie movie);
    public boolean deleteMovie(int id);
}
