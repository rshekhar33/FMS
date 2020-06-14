package com.url.app.impl.globalcontroller;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.url.app.config.AppMessage;
import com.url.app.interf.globalcontroller.GlobalExceptionHandler;
import com.url.app.utility.AppLogMessage;
import com.url.app.utility.AppResponseKey;

/**
 * Global Error Handler.
 * 
 * @author Shekhar Shinde
 */
@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalExceptionHandlerImpl implements GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerImpl.class);

	@Autowired
	private AppMessage appMessage;

	/**
	 * NoHandlerFoundException
	 * 404 Not Found.
	 */
	@Override
	public void handleNoHandlerException(final HttpServletRequest request, final HttpServletResponse response, final NoHandlerFoundException e) throws IOException {
		logger.error(AppLogMessage.NO_HANDLER_FOUND_EXCEPTION_MSG, request.getRequestURL(), e.getMessage());

		final Map<String, Object> exceptionInfo = new ConcurrentHashMap<>();
		exceptionInfo.put(AppResponseKey.STATUS, HttpStatus.NOT_FOUND.value());
		exceptionInfo.put(AppResponseKey.EXCEPTION_MSG, e.getMessage());
		exceptionInfo.put(AppResponseKey.EXCEPTION_HEADER, appMessage.exceptionHeader);
		exceptionInfo.put(AppResponseKey.EXCEPTION_DESC, appMessage.exceptionDesc);

		request.setAttribute(AppResponseKey.APP_EXCEPTION_INFO, exceptionInfo);

		response.sendError(HttpStatus.NOT_FOUND.value());
	}

	/**
	 * MethodArgumentNotValidException
	 * 500 Internal Server Error.
	 */
	@Override
	public void handleMethodNotSupportedException(final HttpServletRequest request, final HttpServletResponse response, final HttpRequestMethodNotSupportedException e)
			throws IOException {
		logger.error(AppLogMessage.METHOD_NOT_SUPPORTED_EXCEPTION_MSG, request.getRequestURL(), e.getMessage());

		final Map<String, Object> exceptionInfo = new ConcurrentHashMap<>();
		exceptionInfo.put(AppResponseKey.STATUS, HttpStatus.METHOD_NOT_ALLOWED.value());
		exceptionInfo.put(AppResponseKey.EXCEPTION_MSG, e.getMessage());
		exceptionInfo.put(AppResponseKey.EXCEPTION_HEADER, appMessage.exceptionHeader);
		exceptionInfo.put(AppResponseKey.EXCEPTION_DESC, appMessage.exceptionDesc2);

		request.setAttribute(AppResponseKey.APP_EXCEPTION_INFO, exceptionInfo);

		response.sendError(HttpStatus.METHOD_NOT_ALLOWED.value(), e.getMessage());
	}
}