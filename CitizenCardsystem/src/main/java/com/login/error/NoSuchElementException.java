package com.login.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchElementException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public NoSuchElementException(String msg) {
        super(msg);
    }
}
