package com.url.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.url.app.security.handler.LoginFailureHandler;
import com.url.app.security.handler.LoginSuccessHandler;
import com.url.app.security.service.AppUserDetailsService;
import com.url.app.utility.AppUrlView;

/**
 * Application security beans.
 * 
 * @author Shekhar Shinde
 */
@Configuration
public class AppSecurityBean {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider(AppUserDetailsService appUserDetailsService, PasswordEncoder passwordEncoder) {
		final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(appUserDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder);

		return authProvider;
	}

	@Bean
	public LoginSuccessHandler loginSuccessHandlerWeb() {
		final LoginSuccessHandler loginSuccessHandler = new LoginSuccessHandler();
		loginSuccessHandler.setDefaultTargetUrl(AppUrlView.URL_LOGIN_SUCCESS);
		loginSuccessHandler.setUseReferer(true);

		return loginSuccessHandler;
	}

	@Bean
	public LoginFailureHandler loginFailureHandler() {
		final LoginFailureHandler loginFailureHandler = new LoginFailureHandler();
		loginFailureHandler.setDefaultFailureUrl(AppUrlView.URL_LOGIN_FAILURE);

		return loginFailureHandler;
	}
}