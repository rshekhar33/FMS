package com.url.app.impl.controller;

import java.security.Principal;
import java.util.Locale;

import org.springframework.stereotype.Controller;

import com.url.app.interf.controller.LoginController;
import com.url.app.utility.AppUrlView;

/**
 * Handles requests for the application Login/Registration/Access Denied/Invalid Session/Expired Sessions page.
 * 
 * @author Shekhar Shinde
 */
@Controller
public class LoginControllerImpl implements LoginController {

	@Override
	public String index(final Locale locale, final Principal principal) {
		return (principal == null) ? AppUrlView.VIEW_LOGIN : AppUrlView.REDIRECT_URL_HOME;
	}

	@Override
	public String home() {
		return AppUrlView.REDIRECT_URL_DASHBOARD;
	}

	@Override
	public String signUp() {
		return AppUrlView.VIEW_SIGN_UP;
	}

	@Override
	public String accessDenied() {
		return AppUrlView.VIEW_ACCESS_DENIED;
	}

	@Override
	public String invalidSession() {
		return AppUrlView.VIEW_INVALID_SESSION;
	}

	@Override
	public String sessionExpired() {
		return AppUrlView.VIEW_SESSION_EXPIRED;
	}
}