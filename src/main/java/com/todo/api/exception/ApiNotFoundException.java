package com.todo.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ApiNotFoundException extends ApiException {


	private static final long serialVersionUID = 1L;

	public ApiNotFoundException(String resource, String reference) {
		super(resource, reference, "Not found");
		
	}

}
