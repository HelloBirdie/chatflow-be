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

        public IncorrectPasswordException(String temp_pwd) {
            super("Old password is not correct:"+temp_pwd);
        }
    }
    
    public class PasswordNotConfirmedException extends RuntimeException {
    
        public PasswordNotConfirmedException() {
            super("New password is not confirmed");
        }
    }
}


