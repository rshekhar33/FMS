package com.url.app.interf.service;

/**
 * Service Layer of application.
 * Method declaration of business logic.
 * 
 * @author Shekhar Shinde
 */
public interface AppService {

	/**
	 * Get url & roles mapping from database and set this mapping in a map.
	 */
	void setUrlRoles();
}