package com.url.app.impl.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.url.app.config.AppMessage;
import com.url.app.interf.controller.GlobalExceptionController;
import com.url.app.interf.service.AppUserService;
import com.url.app.utility.AppCommon;
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
@Controller
public class GlobalExceptionControllerImpl implements GlobalExceptionController, ErrorController {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionControllerImpl.class);

	@Autowired
	private AppUserService appUserService;

	@Autowired
	private AppMessage appMessage;

	@RequestMapping(value = AppUrlView.PATH_ERROR)
	public ModelAndView incorrectPath(final HttpServletRequest request, final Exception e) {
		logger.error(AppLogMessage.INCORRECT_PATH_MSG, request.getRequestURL(), e.getMessage());

		final ModelAndView mav = new ModelAndView(errorPage());
		mav.addObject(AppResponseKey.EXCEPTION_MSG, e.getMessage());
		mav.addObject(AppResponseKey.EXCEPTION_HEADER, appMessage.exceptionHeader);
		mav.addObject(AppResponseKey.EXCEPTION_DESC, appMessage.exceptionDesc);

		return mav;
	}

	@Override
	public String getErrorPath() {
		return AppUrlView.PATH_ERROR;
	}

	@Override
	public ModelAndView handleNoHandlerException(final HttpServletRequest request, final NoHandlerFoundException e) {
		logger.error(AppLogMessage.NO_HANDLER_FOUND_EXCEPTION_MSG, request.getRequestURL(), e.getMessage());

		final ModelAndView mav = new ModelAndView(errorPage());
		mav.addObject(AppResponseKey.EXCEPTION_MSG, e.getMessage());
		mav.addObject(AppResponseKey.EXCEPTION_HEADER, appMessage.exceptionHeader);
		mav.addObject(AppResponseKey.EXCEPTION_DESC, appMessage.exceptionDesc);

		return mav;
	}

	@Override
	public ModelAndView handleMethodNotSupportedException(final HttpServletRequest request, final HttpRequestMethodNotSupportedException e) {
		logger.error(AppLogMessage.METHOD_NOT_SUPPORTED_EXCEPTION_MSG, request.getRequestURL(), e.getMessage());

		final ModelAndView mav = new ModelAndView(errorPage());
		mav.addObject(AppResponseKey.EXCEPTION_MSG, e.getMessage());
		mav.addObject(AppResponseKey.EXCEPTION_HEADER, appMessage.exceptionHeader);
		mav.addObject(AppResponseKey.EXCEPTION_DESC, appMessage.exceptionDesc2);

		return mav;
	}

	@Override
	public ModelAndView handleAllException(final HttpServletRequest request, final Exception e) {
		logger.error(AppLogMessage.GLOBAL_EXCEPTION_MSG + request.getRequestURL(), e);

		final ModelAndView mav = new ModelAndView(errorPage());
		mav.addObject(AppResponseKey.EXCEPTION_MSG, e.getMessage());
		mav.addObject(AppResponseKey.EXCEPTION_HEADER, appMessage.exceptionHeader2);
		mav.addObject(AppResponseKey.EXCEPTION_DESC, appMessage.exceptionDesc3);
		final StringWriter stringWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));
		mav.addObject(AppResponseKey.EXCEPTION_STACK, stringWriter.toString());

		return mav;
	}

	@Override
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