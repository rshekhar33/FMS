package com.url.app.interf.restcontroller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Global Error Handler for API's.
 * 
 * @author Shekhar Shinde
 */
public interface GlobalExceptionRestController {

	/**
	 * NoHandlerFoundException
	 * 404 Not Found.
	 */
	@ExceptionHandler(value = NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	Map<String, Object> handleNoHandlerException(HttpServletRequest request, NoHandlerFoundException e);

	/**
	 * HttpRequestMethodNotSupportedException
	 * 405 Method Not Allowed.
	 */
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
	Map<String, Object> handleMethodNotSupportedException(HttpServletRequest request, HttpRequestMethodNotSupportedException e);

	/**
	 * MethodArgumentNotValidException
	 * 500 Internal Server Error.
	 */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e);

	/**
	 * ConstraintViolationException
	 * 500 Internal Server Error.
	 */
	@ExceptionHandler(value = ConstraintViolationException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	Map<String, String> handleConstraintViolationException(ConstraintViolationException e);

	/**
	 * Exception
	 * 500 Internal Server Error.
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	Map<String, Object> handleAllException(HttpServletRequest request, Exception e);
}