package com.url.app.interf.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.url.app.utility.AppUrlView;

/**
 * Error Rest Controller.
 * 
 * @author Shekhar Shinde
 */
public interface AppErrorController extends ErrorController {

	/**
	 * Application error handler.
	 */
	@RequestMapping(value = AppUrlView.PATH_ERROR)
	ModelAndView incorrectPath(HttpServletRequest request);
}