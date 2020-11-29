package com.url.app.interf.restcontroller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

import com.url.app.pojo.AppExceptionInfo;
import com.url.app.utility.AppUrlView;

/**
 * Error Rest Controller.
 * 
 * @author Shekhar Shinde
 */
public interface AppErrorRestController extends ErrorController {

	/**
	 * Application error handler for restful requests.
	 */
	@RequestMapping(value = AppUrlView.PATH_ERROR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	AppExceptionInfo incorrectPath(HttpServletRequest request);
}