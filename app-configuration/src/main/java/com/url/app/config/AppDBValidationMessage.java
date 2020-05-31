package com.url.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.url.app.utility.AppConstant;

/**
 * DB validation messages of application.
 * 
 * @author Shekhar Shinde
 */
@Configuration
@PropertySource(value = AppConstant.SPRING_DB_VALIDATION_MSG_FILE_BASENAME)
public class AppDBValidationMessage {

	@Value("${user.username.exists.error}")
	public String userUsernameExistsError;

	@Value("${user.email.exists.error}")
	public String userEmailExistsError;

	@Value("${module.modulename.exists.error}")
	public String moduleModulenameExistsError;

	@Value("${role.rolename.exists.error}")
	public String roleRolenameExistsError;
}