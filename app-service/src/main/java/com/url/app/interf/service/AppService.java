package com.url.app.interf.service;

import com.url.app.pojo.AppExceptionInfo;

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

	/**
	 * Get the application exception object.
	 * 
	 * @param appExceptionInfoObj the exception object from request.
	 * @return application exception object.
	 */
	AppExceptionInfo getAppExceptionInfo(Object appExceptionInfoObj);
}