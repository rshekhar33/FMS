package com.url.app.interf.globalcontroller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Global Exception Handler.
 * 
 * @author Shekhar Shinde
 */
public interface GlobalExceptionHandler {

	/**
	 * NoHandlerFoundException
	 * 404 Not Found.
	 */
	@ExceptionHandler(value = NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public void handleNoHandlerException(HttpServletRequest request, HttpServletResponse response, NoHandlerFoundException e) throws IOException;

	/**
	 * MethodArgumentNotValidException
	 * 500 Internal Server Error.
	 */
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
	public void handleMethodNotSupportedException(HttpServletRequest request, HttpServletResponse response, HttpRequestMethodNotSupportedException e) throws IOException;
}