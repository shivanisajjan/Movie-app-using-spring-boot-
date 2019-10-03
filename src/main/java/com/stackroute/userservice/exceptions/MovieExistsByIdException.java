package com.stackroute.userservice.exceptions;

public class MovieExistsByIdException extends Exception {
    private String message;

    public MovieExistsByIdException(){}
    public MovieExistsByIdException(String message) {
        super(message);
        this.message = message;
    }


}
