package com.url.app.impl.globalrestcontroller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.url.app.config.AppMessage;
import com.url.app.interf.globalrestcontroller.GlobalExceptionRestController;
import com.url.app.utility.AppConstant;
import com.url.app.utility.AppLogMessage;
import com.url.app.utility.AppResponseKey;

/**
 * Global Error Handler for API's.
 * 
 * @author Shekhar Shinde
 */
@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionRestControllerImpl implements GlobalExceptionRestController {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionRestControllerImpl.class);

	@Autowired
	private AppMessage appMessage;

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