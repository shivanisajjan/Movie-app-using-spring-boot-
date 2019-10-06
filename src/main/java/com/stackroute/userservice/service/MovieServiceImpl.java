package com.stackroute.userservice.service;

import com.stackroute.userservice.exceptions.MovieExistsByIdGlobalException;
import com.stackroute.userservice.exceptions.MovieNotFoundGlobalException;
import com.stackroute.userservice.model.Movie;
import com.stackroute.userservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Profile("dev")
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public boolean saveMovie(Movie movie) throws MovieExistsByIdGlobalException{
        System.out.println("Used in development profile");
        if(this.movieRepository.existsById(movie.getId())){
            throw new MovieExistsByIdGlobalException();
        }
        Movie movie1=this.movieRepository.save(movie);
        if(movie1==null){
            throw new MovieExistsByIdGlobalException();
        }
        return true;
    }

    @Override
    public List<Movie> getallMovies() {
        System.out.println("Used in development profile");
        return this.movieRepository.findAll();
    }

    @Override
    public boolean update(Movie movie) {
        System.out.println("Used in development profile");
        try{
            this.movieRepository.save(movie);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteMovie(int id) throws MovieNotFoundGlobalException{
        System.out.println("Used in development profile");
        if(this.movieRepository.existsById(id)){
            this.movieRepository.deleteById(id);
            return true;
        }
        else{
            throw new MovieNotFoundGlobalException();
        }
    }


    @Override
    public List<Movie> getMoviesbyTitle(String title) throws MovieNotFoundGlobalException{
        System.out.println("Used in development profile");
        List<Movie> li=this.movieRepository.findBytitle(title);
        if(li.isEmpty()){
            throw new MovieNotFoundGlobalException();
        }
        return li;
    }
}
