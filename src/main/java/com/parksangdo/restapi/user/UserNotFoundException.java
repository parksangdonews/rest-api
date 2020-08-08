package com.parksangdo.restapi.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



// HTTP Status Code
// 2xx = OK
// 4xx = Client Error.
// 5xx = Server Error.
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
