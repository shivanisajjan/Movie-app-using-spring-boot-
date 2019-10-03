package com.stackroute.userservice.controller;

import com.stackroute.userservice.exceptions.MovieExistsByIdException;
import com.stackroute.userservice.exceptions.MovieNotFoundException;
import com.stackroute.userservice.model.Movie;
import com.stackroute.userservice.service.MovieService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Api(value="Movie Services", description="All Operations of Movie app")
public class MovieController {
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    @ApiOperation(value = "Add a Movie")
    @PostMapping("movie")
    public ResponseEntity<?> saveMovie(@ApiParam(value = "Movie object store in database table", required = true) @Valid @RequestBody Movie movie){
        ResponseEntity responseEntity;
        try {
            movieService.saveMovie(movie);
            responseEntity = new ResponseEntity<List<Movie>>(this.movieService.getallMovies(), HttpStatus.CREATED);
        }
        catch (MovieExistsByIdException ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.OK);
            ex.printStackTrace();
        }
        return responseEntity;
    }

    @ApiOperation(value = "View a list of available Movies", response = Movie.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("movie")
    public List<Movie> getAllMovie(){
        return (this.movieService.getallMovies());
    }

    @ApiOperation(value = "Update a Movie")
    @PutMapping("movie")
    public ResponseEntity<?> updateMovie(@ApiParam(value = "Update Movie object", required = true) @Valid @RequestBody Movie movie){
        ResponseEntity responseEntity;
        try{
            movieService.update(movie);
            responseEntity=new ResponseEntity<List<Movie>>(this.movieService.getallMovies(), HttpStatus.OK);
        }
        catch (Exception e){
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


    @DeleteMapping("movie/{id}")
    @ApiOperation(value = "Delete an Movie")
    public ResponseEntity<?> deleteMovie(@ApiParam(value = "Movie Id from which Movie object will delete from database table", required = true) @PathVariable(value = "id") int id){
        ResponseEntity responseEntity;
        try{
            movieService.deleteMovie(id);
            responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.OK);
        }
        catch (Exception e){
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Get an Movie by Title")
    @GetMapping("movie/title/{title}")
    public ResponseEntity<?> getMovieByTitle(@ApiParam(value = "Movie title from which movie object will retrieve", required = true) @PathVariable(value = "title") String title){
        ResponseEntity responseEntity;
        try{
            responseEntity=new ResponseEntity<List<Movie>>(this.movieService.getMoviesbyTitle(title), HttpStatus.OK);
        }
        catch (MovieNotFoundException ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(), HttpStatus.OK);
        }
        return responseEntity;
    }
}
