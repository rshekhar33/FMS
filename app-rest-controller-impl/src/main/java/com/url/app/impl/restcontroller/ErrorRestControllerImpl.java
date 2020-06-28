package com.url.app.impl.restcontroller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.url.app.config.AppMessage;
import com.url.app.pojo.AppExceptionInfo;
import com.url.app.utility.AppConstant;
import com.url.app.utility.AppLogMessage;
import com.url.app.utility.AppResponseKey;
import com.url.app.utility.AppUrlView;

/**
 * Error Controller
 * 
 * @author Shekhar Shinde
 */
@RestController
public class ErrorRestControllerImpl implements ErrorController {
	private static final Logger logger = LoggerFactory.getLogger(ErrorRestControllerImpl.class);

	@Autowired
	private AppMessage appMessage;

	@RequestMapping(value = AppUrlView.PATH_ERROR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public AppExceptionInfo incorrectPath(final HttpServletRequest request) {
		final Object appExceptionInfoObj = request.getAttribute(AppResponseKey.APP_EXCEPTION_INFO);
		logger.error(AppLogMessage.GLOBAL_ERROR_MSG, appExceptionInfoObj);

		AppExceptionInfo appExceptionInfo = null;
		if (appExceptionInfoObj instanceof AppExceptionInfo) {
			appExceptionInfo = (AppExceptionInfo) appExceptionInfoObj;
		} else {
			appExceptionInfo = new AppExceptionInfo();
			appExceptionInfo.setStatus(AppConstant.FAIL);
			appExceptionInfo.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			appExceptionInfo.setExceptionHeader(appMessage.exceptionHeader2);
			appExceptionInfo.setExceptionDesc(appMessage.exceptionDesc3);
		}

		return appExceptionInfo;
	}

	@Override
	public String getErrorPath() {
		return null;
	}
}