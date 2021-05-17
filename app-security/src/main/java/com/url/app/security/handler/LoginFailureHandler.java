package com.url.app.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.url.app.config.AppMessage;
import com.url.app.interf.service.AppUserService;
import com.url.app.utility.AppHttpSessionKey;
import com.url.app.utility.AppLogMessage;
import com.url.app.utility.AppUrlView;

/**
 * Login failure handler class.
 * 
 * @author Shekhar Shinde
 */
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	private static final Logger appLogger = LoggerFactory.getLogger(LoginFailureHandler.class);

	@Autowired
	private AppUserService appUserService;

	@Autowired
	private AppMessage appMessage;

	@Override
	public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException exception)
			throws IOException, ServletException {
		final String userName = request.getParameter(AppUrlView.PARAMETER_NAME_USERNAME);

		appUserService.userUpdateLastLoginFailure(userName);

		final String loginErrorMessage = (exception instanceof InternalAuthenticationServiceException) ? appMessage.loginError : exception.getMessage();

		appLogger.debug(AppLogMessage.AUTHENTICATION_FAILED_DUE_TO_MSG, userName, loginErrorMessage);

		request.getSession().setAttribute(AppHttpSessionKey.LOGIN_ERROR, loginErrorMessage);

		super.onAuthenticationFailure(request, response, exception);
	}
}