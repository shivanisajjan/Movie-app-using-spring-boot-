package com.stackroute.userservice.controller;

import com.stackroute.userservice.model.Movie;
import com.stackroute.userservice.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class MovieController {
    private MovieService movieService;
    private ResponseEntity responseEntity;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    @PostMapping("movie")
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie){
        try{
            movieService.saveMovie(movie);
            responseEntity=new ResponseEntity<List<Movie>>(this.movieService.getallMovies(), HttpStatus.CREATED);
        }
        catch (Exception e){
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @GetMapping("movie")
    public ResponseEntity<?> getAllMovie(){
        return new ResponseEntity<List<Movie>>(this.movieService.getallMovies(), HttpStatus.OK);
    }
    @PutMapping("movie")
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie){
        try{
            movieService.update(movie);
            responseEntity=new ResponseEntity<List<Movie>>(this.movieService.getallMovies(), HttpStatus.OK);
        }
        catch (Exception e){
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @DeleteMapping("movie")
    public ResponseEntity<?> deleteMovie(@RequestBody int id){
        try{
            movieService.deleteMovie(id);
            responseEntity=new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
        }
        catch (Exception e){
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @GetMapping("movie/title")
    public ResponseEntity<?> getMovieByTitle(@RequestBody String title){
        return new ResponseEntity<List<Movie>>(this.movieService.getMoviesbyTitle(title), HttpStatus.OK);
    }
}
