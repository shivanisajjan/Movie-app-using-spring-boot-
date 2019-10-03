package com.stackroute.userservice.service;

import com.stackroute.userservice.model.Movie;
import com.stackroute.userservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    public boolean saveMovie(Movie movie) {
        try{
            this.movieRepository.save(movie);
            return true;
        }
        catch (Exception e){
            return false;
        }
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
    public boolean deleteMovie(int id) {
        try{
            this.movieRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }


    @Override
    public List<Movie> getMoviesbyTitle(String title) {
        List<Movie> li=this.movieRepository.findBytitle(title);
        for (Movie m:li
             ) {
            System.out.println(m.getId());

        }
        return li;
    }
}
