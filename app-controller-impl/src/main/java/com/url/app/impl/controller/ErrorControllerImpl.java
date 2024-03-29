package com.url.app.impl.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.url.app.interf.controller.AppErrorController;
import com.url.app.interf.service.AppService;
import com.url.app.security.service.AppPrincipalUser;
import com.url.app.utility.AppCommon;
import com.url.app.utility.AppLogMessage;
import com.url.app.utility.AppResponseKey;
import com.url.app.utility.AppUrlView;

/**
 * Error Controller Implementation.
 * 
 * @author Shekhar Shinde
 */
@Controller
public class ErrorControllerImpl implements AppErrorController {
	private static final Logger logger = LoggerFactory.getLogger(ErrorControllerImpl.class);

	@Autowired
	private AppPrincipalUser appPrincipalUser;

	@Autowired
	private AppService appService;

	@Override
	public ModelAndView incorrectPath(final HttpServletRequest request) {
		final Object appExceptionInfoObj = request.getAttribute(AppResponseKey.APP_EXCEPTION_INFO);
		logger.error(AppLogMessage.GLOBAL_ERROR_MSG, appExceptionInfoObj);

		final ModelAndView mav = new ModelAndView(errorPage());
		mav.addObject(AppResponseKey.APP_EXCEPTION_INFO, appService.getAppExceptionInfo(appExceptionInfoObj));

		return mav;
	}

	public String errorPage() {
		return AppCommon.isPositiveInteger(appPrincipalUser.getPrincipalUserUserId()) ? AppUrlView.VIEW_APP_ERROR_PAGE : AppUrlView.VIEW_GLOBAL_ERROR_PAGE;
	}
}