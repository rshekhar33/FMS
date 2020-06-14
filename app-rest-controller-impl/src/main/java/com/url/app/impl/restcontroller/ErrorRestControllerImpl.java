package com.url.app.impl.restcontroller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@SuppressWarnings("unchecked")
	@RequestMapping(value = AppUrlView.PATH_ERROR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> incorrectPath(final HttpServletRequest request) {
		Object appExceptionInfoObj = request.getAttribute(AppResponseKey.APP_EXCEPTION_INFO);
		logger.error(AppLogMessage.GLOBAL_ERROR_MSG, appExceptionInfoObj);

		Map<String, Object> json = null;
		if (appExceptionInfoObj instanceof Map<?, ?>) {
			json = (Map<String, Object>) appExceptionInfoObj;
		}

		return json;
	}

	@Override
	public String getErrorPath() {
		return null;
	}
}