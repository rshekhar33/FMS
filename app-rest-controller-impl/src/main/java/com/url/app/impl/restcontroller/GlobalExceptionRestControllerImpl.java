package com.url.app.impl.restcontroller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.url.app.config.AppMessage;
import com.url.app.interf.restcontroller.GlobalExceptionRestController;
import com.url.app.utility.AppConstant;
import com.url.app.utility.AppLogMessage;
import com.url.app.utility.AppResponseKey;
import com.url.app.utility.AppUrlView;

/**
 * Global Error Handler for API's.
 * 
 * @author Shekhar Shinde
 */
@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestController
public class GlobalExceptionRestControllerImpl implements GlobalExceptionRestController, ErrorController {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionRestControllerImpl.class);

	@Autowired
	private AppMessage appMessage;

	@RequestMapping(value = AppUrlView.PATH_ERROR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> incorrectPath(final HttpServletRequest request, final Exception e) {
		logger.error(AppLogMessage.INCORRECT_PATH_MSG, request.getRequestURL(), e.getMessage());

		final Map<String, Object> json = new ConcurrentHashMap<>();
		json.put(AppResponseKey.STATUS, HttpStatus.NOT_FOUND.value());
		json.put(AppResponseKey.EXCEPTION_HEADER, appMessage.exceptionHeader);
		json.put(AppResponseKey.EXCEPTION_DESC, appMessage.exceptionDesc);

		return json;
	}

	@Override
	public String getErrorPath() {
		return AppUrlView.PATH_ERROR;
	}

	@Override
	public Map<String, Object> handleNoHandlerException(final HttpServletRequest request, final NoHandlerFoundException e) {
		logger.error(AppLogMessage.NO_HANDLER_FOUND_EXCEPTION_MSG, request.getRequestURL(), e.getMessage());

		final Map<String, Object> json = new ConcurrentHashMap<>();
		json.put(AppResponseKey.STATUS, HttpStatus.NOT_FOUND.value());
		json.put(AppResponseKey.EXCEPTION_HEADER, appMessage.exceptionHeader);
		json.put(AppResponseKey.EXCEPTION_DESC, appMessage.exceptionDesc);

		return json;
	}

	@Override
	public Map<String, Object> handleMethodNotSupportedException(final HttpServletRequest request, final HttpRequestMethodNotSupportedException e) {
		logger.error(AppLogMessage.METHOD_NOT_SUPPORTED_EXCEPTION_MSG, request.getRequestURL(), e.getMessage());

		final Map<String, Object> json = new ConcurrentHashMap<>();
		json.put(AppResponseKey.STATUS, HttpStatus.METHOD_NOT_ALLOWED.value());
		json.put(AppResponseKey.EXCEPTION_HEADER, appMessage.exceptionHeader);
		json.put(AppResponseKey.EXCEPTION_DESC, appMessage.exceptionDesc2);

		return json;
	}

	@Override
	public Map<String, String> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
		final Map<String, String> json = new ConcurrentHashMap<>();
		json.put(AppResponseKey.STATUS, AppConstant.FAIL);
		e.getBindingResult().getAllErrors().forEach(error -> {
			final String fieldName = ((FieldError) error).getField();
			final String errorMessage = error.getDefaultMessage();
			json.put(fieldName, errorMessage);
		});

		return json;
	}

	@Override
	public Map<String, String> handleConstraintViolationException(final ConstraintViolationException e) {
		final Map<String, String> json = new ConcurrentHashMap<>();
		json.put(AppResponseKey.STATUS, AppConstant.FAIL);
		e.getConstraintViolations().forEach(error -> {
			final String fieldName = ((PathImpl) error.getPropertyPath()).getLeafNode().getName();
			final String errorMessage = error.getMessage();
			json.put(fieldName, errorMessage);
		});

		return json;
	}

	@Override
	public Map<String, Object> handleAllException(final HttpServletRequest request, final Exception e) {
		logger.error(AppLogMessage.GLOBAL_EXCEPTION_MSG + request.getRequestURL(), e);

		final Map<String, Object> json = new ConcurrentHashMap<>();
		json.put(AppResponseKey.STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value());
		json.put(AppResponseKey.EXCEPTION_HEADER, appMessage.exceptionHeader2);
		json.put(AppResponseKey.EXCEPTION_DESC, appMessage.exceptionDesc3);

		return json;
	}
}