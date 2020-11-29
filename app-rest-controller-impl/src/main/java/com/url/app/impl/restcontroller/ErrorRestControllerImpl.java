package com.url.app.impl.restcontroller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.url.app.interf.restcontroller.AppErrorRestController;
import com.url.app.interf.service.AppService;
import com.url.app.pojo.AppExceptionInfo;
import com.url.app.utility.AppLogMessage;
import com.url.app.utility.AppResponseKey;

/**
 * Error Rest Controller Implementation.
 * 
 * @author Shekhar Shinde
 */
@RestController
public class ErrorRestControllerImpl implements AppErrorRestController {
	private static final Logger logger = LoggerFactory.getLogger(ErrorRestControllerImpl.class);

	@Autowired
	private AppService appService;

	@Override
	public AppExceptionInfo incorrectPath(final HttpServletRequest request) {
		final Object appExceptionInfoObj = request.getAttribute(AppResponseKey.APP_EXCEPTION_INFO);
		logger.error(AppLogMessage.GLOBAL_ERROR_MSG, appExceptionInfoObj);

		return appService.getAppExceptionInfo(appExceptionInfoObj);
	}

	@Override
	public String getErrorPath() {
		return null;
	}
}