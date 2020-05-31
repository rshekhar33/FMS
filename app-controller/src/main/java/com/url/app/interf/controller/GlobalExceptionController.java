package com.url.app.interf.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Global Error Handler.
 * 
 * @author Shekhar Shinde
 */
public interface GlobalExceptionController {

	/**
	 * NoHandlerFoundException
	 * 404 Not Found.
	 */
	@ExceptionHandler(value = NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	ModelAndView handleNoHandlerException(HttpServletRequest request, NoHandlerFoundException e);

	/**
	 * HttpRequestMethodNotSupportedException
	 * 405 Method Not Allowed.
	 */
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
	ModelAndView handleMethodNotSupportedException(HttpServletRequest request, HttpRequestMethodNotSupportedException e);

	/**
	 * Exception
	 * 500 Internal Server Error.
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	ModelAndView handleAllException(HttpServletRequest request, Exception e);

	/**
	 * Return error page based on user login status.
	 * @return the view name.
	 */
	String errorPage();
}