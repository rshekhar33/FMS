package com.url.app.interf.globalcontroller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Global Error Handler.
 * 
 * @author Shekhar Shinde
 */
public interface GlobalExceptionController {

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