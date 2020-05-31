package com.url.app.interf.controller;

import java.security.Principal;
import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.url.app.utility.AppUrlView;

/**
 * Handles requests for the application Login/Registration/Access Denied/Invalid Session/Expired Sessions page.
 * 
 * @author Shekhar Shinde
 */
public interface LoginController {

	/**
	 * Index/Login Page of application.
	 */
	@GetMapping(value = { AppUrlView.PATH_ROOT, AppUrlView.PATH_LOGIN })
	String index(Locale locale, Principal principal);

	/**
	 * HomePage/Dashboard of Application.
	 */
	@GetMapping(value = AppUrlView.PATH_HOME)
	String home();

	/**
	 * SignUp/Registration URL for Users.
	 */
	@GetMapping(value = AppUrlView.PATH_SIGN_UP)
	String signUp();

	/**
	 * View when access is denied.
	 * 
	 * @return name of the view which is to be rendered when access is denied.
	 */
	@RequestMapping(value = AppUrlView.PATH_ACCESS_DENIED, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	String accessDeniedPage();

	/**
	 * View when application is accessed using invalid session id.
	 * 
	 * @return name of the view which is to be rendered when session is invalid.
	 */
	@RequestMapping(value = AppUrlView.PATH_INVALID_SESSION, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	String invalidSessionPage();

	/**
	 * View when session has expired.
	 * 
	 * @return name of the view which is to be rendered when session has expired.
	 */
	@RequestMapping(value = AppUrlView.PATH_SESSION_EXPIRED, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	String sessionExpiredPage();
}