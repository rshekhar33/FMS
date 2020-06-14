package com.url.app.impl.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.url.app.interf.service.AppUserService;
import com.url.app.utility.AppCommon;
import com.url.app.utility.AppLogMessage;
import com.url.app.utility.AppResponseKey;
import com.url.app.utility.AppUrlView;

/**
 * Error Controller
 * 
 * @author Shekhar Shinde
 */
@Controller
public class ErrorControllerImpl implements ErrorController {
	private static final Logger logger = LoggerFactory.getLogger(ErrorControllerImpl.class);

	@Autowired
	private AppUserService appUserService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = AppUrlView.PATH_ERROR)
	public ModelAndView incorrectPath(final HttpServletRequest request, final Exception e) {
		Object appExceptionInfoObj = request.getAttribute(AppResponseKey.APP_EXCEPTION_INFO);
		logger.error(AppLogMessage.GLOBAL_ERROR_MSG, appExceptionInfoObj);

		Map<String, Object> exceptionInfo = null;
		if (appExceptionInfoObj instanceof Map<?, ?>) {
			exceptionInfo = (Map<String, Object>) appExceptionInfoObj;
		}

		final ModelAndView mav = new ModelAndView(errorPage());
		mav.addAllObjects(exceptionInfo);

		return mav;
	}

	@Override
	public String getErrorPath() {
		return null;
	}

	public String errorPage() {
		String view = AppUrlView.VIEW_GLOBAL_ERROR_PAGE;
		try {
			final Integer userId = appUserService.getPrincipalUserUserId();
			if (AppCommon.isPositiveInteger(userId)) {
				view = AppUrlView.VIEW_APP_ERROR_PAGE;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return view;
	}
}