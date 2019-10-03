package com.stackroute.userservice.service;

import com.stackroute.userservice.exceptions.MovieExistsByIdException;
import com.stackroute.userservice.exceptions.MovieNotFoundException;
import com.stackroute.userservice.model.Movie;
import com.stackroute.userservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public boolean saveMovie(Movie movie) throws MovieExistsByIdException {
        if(this.movieRepository.existsById(movie.getId())){
            throw new MovieExistsByIdException("Movie already Exists");
        }
        Movie movie1=this.movieRepository.save(movie);
        if(movie1==null){
            throw new MovieExistsByIdException("Movie already Exists");
        }
        return true;
    }

    @Override
    public List<Movie> getallMovies() {
        return this.movieRepository.findAll();
    }

    @Override
    public boolean update(Movie movie) {
        try{
            this.movieRepository.save(movie);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteMovie(int id) throws MovieNotFoundException {
        if(this.movieRepository.existsById(id)){
            this.movieRepository.deleteById(id);
            return true;
        }
        else{
            throw new MovieNotFoundException("Movie not found");
        }
    }


    @Override
    public List<Movie> getMoviesbyTitle(String title) throws MovieNotFoundException{
        List<Movie> li=this.movieRepository.findBytitle(title);
        if(li.isEmpty()){
            throw new MovieNotFoundException("Movies Not found");
        }
        return li;
    }
}
