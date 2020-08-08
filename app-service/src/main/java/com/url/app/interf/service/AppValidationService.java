package com.url.app.interf.service;

import java.util.Map;

import com.url.app.dto.entity.Module;
import com.url.app.dto.entity.Role;
import com.url.app.dto.entity.User;

/**
 * Service Layer of application for invoking validations.
 * Method declaration of business logic.
 * 
 * @author Shekhar Shinde
 */
public interface AppValidationService {

	/**
	 * Validate module object and return proper validation messages.
	 * 
	 * @param module the object to be validated.
	 * @return validation message.
	 */
	Map<String, String> validateModule(final Module module);

	/**
	 * Validate role object and return proper validation messages.
	 * 
	 * @param role the object to be validated.
	 * @return validation message.
	 */
	Map<String, String> validateRole(final Role role);

	/**
	 * Validate user object and return proper validation messages.
	 * 
	 * @param user the object to be validated.
	 * @return validation message.
	 */
	Map<String, String> validateUser(final User user);
}