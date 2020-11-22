package com.url.app.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.url.app.security.util.SecurityUtil;
import com.url.app.utility.AppLogMessage;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	@Autowired
	private DaoAuthenticationProvider daoAuthenticationProvider;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		final String userName = authentication.getName();
		final String passwordEnc = authentication.getCredentials().toString();
		final String password = SecurityUtil.decrypt(passwordEnc);

		logger.debug(AppLogMessage.PASSWORD_ENCRYPTED_MSG, passwordEnc);

		final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName, password);

		return daoAuthenticationProvider.authenticate(usernamePasswordAuthenticationToken);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}