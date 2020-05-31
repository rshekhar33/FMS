package com.url.app.securityservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.url.app.config.AppMessage;
import com.url.app.dto.LoggedUser;
import com.url.app.dto.User;
import com.url.app.interf.service.AppUserService;
import com.url.app.utility.AppLogMessage;

/**
 * Authentication service which returns UserDetails instance.
 * 
 * @author Shekhar Shinde
 */
@Service
public class AppUserDetailsService implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(AppUserDetailsService.class);

	@Autowired
	private AppUserService appUserService;

	@Autowired
	private AppMessage appMessage;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final User user = appUserService.fetchValidUser(username);
		if (user == null) {
			throw new UsernameNotFoundException(appMessage.userDoesNotExist);
		}

		logger.debug(AppLogMessage.USER_USERNAME_MSG, username);

		return new LoggedUser(user);
	}
}