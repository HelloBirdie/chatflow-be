package com.hellobirdie.chatflow.dto;
import java.lang.RuntimeException;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDto {
    private String error;
    private List<String> details;

    public class IncorrectPasswordException extends RuntimeException {

        public IncorrectPasswordException() {
            super("Old password is not correct:");
        }
    }
    
    public class PasswordNotConfirmedException extends RuntimeException {
    
        public PasswordNotConfirmedException() {
            super("New password is not confirmed");
        }
    }

    public class UserNotFoundException extends RuntimeException {

        public UserNotFoundException() {
            super("User not found");
        }
    }
}


