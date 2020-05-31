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
		String url = AppUrlView.REDIRECT_URL_HOME;
		if (principal == null) {
			url = AppUrlView.VIEW_LOGIN;
		}

		return url;
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
	public String accessDeniedPage() {
		return AppUrlView.VIEW_ACCESS_DENIED;
	}

	@Override
	public String invalidSessionPage() {
		return AppUrlView.VIEW_INVALID_SESSION;
	}

	@Override
	public String sessionExpiredPage() {
		return AppUrlView.VIEW_SESSION_EXPIRED;
	}
}