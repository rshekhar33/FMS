package com.url.app.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.url.app.dto.entity.User;
import com.url.app.pojo.LoggedUser;

/**
 * Principal user service.
 * 
 * @author Shekhar Shinde
 */
@Service
public class AppPrincipalUser {
	private static final Logger logger = LoggerFactory.getLogger(AppPrincipalUser.class);

	/**
	 * Get loggedUser from spring security principal.
	 * 
	 * @return loggedUser from spring security principal.
	 */
	public LoggedUser getPrincipal() {
		LoggedUser loggedUser = null;
		try {
			final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (principal instanceof LoggedUser) {
				loggedUser = (LoggedUser) principal;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return loggedUser;
	}

	/**
	 * Get User of LoggedUser from spring security principal.
	 * 
	 * @return User of LoggedUser from spring security principal.
	 */
	public User getPrincipalUser() {
		User user = null;
		final LoggedUser loggedUser = getPrincipal();
		if (loggedUser != null) {
			user = loggedUser.getUser();
		}

		return user;
	}

	/**
	 * Get userId of LoggedUser from spring security principal.
	 * 
	 * @return userId of User of LoggedUser from spring security principal.
	 */
	public Integer getPrincipalUserUserId() {
		Integer userId = null;
		final User user = getPrincipalUser();
		if (user != null) {
			userId = user.getUserId();
		}

		return userId;
	}
}