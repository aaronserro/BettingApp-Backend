package com.betting.backend.User;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long ID) {
        super("User with ID:" + ID +" is not found");

    }



}
