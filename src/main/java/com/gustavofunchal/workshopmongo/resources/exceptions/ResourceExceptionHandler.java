package com.gustavofunchal.workshopmongo.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.gustavofunchal.workshopmongo.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ObjectNotFoundException.class)
	public StandardError objectNotFound(ObjectNotFoundException e, WebRequest request) {
		return StandardError.builder()
				.timestamp(System.currentTimeMillis())
				.message(e.getMessage())
				.path(request.getDescription(false))
				.build();
	}

}
