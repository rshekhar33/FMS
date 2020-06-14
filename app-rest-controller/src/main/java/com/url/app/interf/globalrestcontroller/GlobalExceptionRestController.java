package com.url.app.interf.globalrestcontroller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Global Error Handler for API's.
 * 
 * @author Shekhar Shinde
 */
public interface GlobalExceptionRestController {

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