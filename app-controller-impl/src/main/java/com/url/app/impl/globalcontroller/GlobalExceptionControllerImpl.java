package com.url.app.impl.globalcontroller;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import com.url.app.config.AppMessage;
import com.url.app.interf.globalcontroller.GlobalExceptionController;
import com.url.app.pojo.AppExceptionInfo;
import com.url.app.security.service.AppPrincipalUser;
import com.url.app.utility.AppCommon;
import com.url.app.utility.AppConstant;
import com.url.app.utility.AppLogMessage;
import com.url.app.utility.AppResponseKey;
import com.url.app.utility.AppUrlView;

/**
 * Global Error Handler.
 * 
 * @author Shekhar Shinde
 */
@ControllerAdvice(annotations = Controller.class)
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalExceptionControllerImpl implements GlobalExceptionController {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionControllerImpl.class);

	@Autowired
	private AppMessage appMessage;

	@Autowired
	private AppPrincipalUser appPrincipalUser;

	@Override
	public ModelAndView handleAllException(final HttpServletRequest request, final Exception e) {
		final String globalExceptionMsg = AppLogMessage.GLOBAL_EXCEPTION_MSG + request.getRequestURL();
		logger.error(globalExceptionMsg, e);

		final StringWriter stringWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));

		final AppExceptionInfo appExceptionInfo = new AppExceptionInfo();
		appExceptionInfo.setStatus(AppConstant.FAIL);
		appExceptionInfo.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		appExceptionInfo.setExceptionMsg(e.getMessage());
		appExceptionInfo.setExceptionHeader(appMessage.exceptionHeader2);
		appExceptionInfo.setExceptionDesc(appMessage.exceptionDesc3);
		appExceptionInfo.setExceptionStack(stringWriter.toString());

		final ModelAndView modelAndView = new ModelAndView(errorPage());
		modelAndView.addObject(AppResponseKey.APP_EXCEPTION_INFO, appExceptionInfo);

		return modelAndView;
	}

	@Override
	public String errorPage() {
		return AppCommon.isPositiveInteger(appPrincipalUser.getPrincipalUserUserId()) ? AppUrlView.VIEW_APP_ERROR_PAGE : AppUrlView.VIEW_GLOBAL_ERROR_PAGE;
	}
}