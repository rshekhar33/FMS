package com.url.app.impl.globalrestcontroller;

import java.util.Map;

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
import com.url.app.pojo.AppConcurrentHashMap;
import com.url.app.pojo.AppExceptionInfo;
import com.url.app.utility.AppConstant;
import com.url.app.utility.AppLogMessage;

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
	public AppExceptionInfo handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
		final Map<String, String> invalidData = new AppConcurrentHashMap<>();
		e.getBindingResult().getAllErrors().forEach(error -> {
			final String fieldName = ((FieldError) error).getField();
			final String errorMessage = error.getDefaultMessage();
			invalidData.put(fieldName, errorMessage);
		});
		final AppExceptionInfo appExceptionInfo = new AppExceptionInfo();
		appExceptionInfo.setStatus(AppConstant.FAIL);
		appExceptionInfo.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		appExceptionInfo.setInvalidData(invalidData);

		return appExceptionInfo;
	}

	@Override
	public AppExceptionInfo handleConstraintViolationException(final ConstraintViolationException e) {
		final Map<String, String> invalidData = new AppConcurrentHashMap<>();
		e.getConstraintViolations().forEach(error -> {
			final String fieldName = ((PathImpl) error.getPropertyPath()).getLeafNode().getName();
			final String errorMessage = error.getMessage();
			invalidData.put(fieldName, errorMessage);
		});
		final AppExceptionInfo appExceptionInfo = new AppExceptionInfo();
		appExceptionInfo.setStatus(AppConstant.FAIL);
		appExceptionInfo.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		appExceptionInfo.setInvalidData(invalidData);

		return appExceptionInfo;
	}

	@Override
	public AppExceptionInfo handleAllException(final HttpServletRequest request, final Exception e) {
		final String globalExceptionMsg = AppLogMessage.GLOBAL_EXCEPTION_MSG + request.getRequestURL();
		logger.error(globalExceptionMsg, e);

		final AppExceptionInfo appExceptionInfo = new AppExceptionInfo();
		appExceptionInfo.setStatus(AppConstant.FAIL);
		appExceptionInfo.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		appExceptionInfo.setExceptionMsg(e.getMessage());
		appExceptionInfo.setExceptionHeader(appMessage.exceptionHeader2);
		appExceptionInfo.setExceptionDesc(appMessage.exceptionDesc3);

		return appExceptionInfo;
	}
}