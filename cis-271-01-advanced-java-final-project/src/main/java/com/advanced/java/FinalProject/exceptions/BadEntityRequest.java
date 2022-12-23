package com.advanced.java.FinalProject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (HttpStatus.BAD_REQUEST)
public class BadEntityRequest extends Exception {
    public BadEntityRequest(String message) {

        super(message);
        System.out.println(message);

    }
}
