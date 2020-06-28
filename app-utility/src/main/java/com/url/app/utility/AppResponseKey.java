package com.url.app.utility;

/**
 * Application Response Key Constants.
 * 
 * @author Shekhar Shinde
 */
public class AppResponseKey {

	private AppResponseKey() {
		throw new IllegalStateException("Utility class");
	}

	public static final String APP_EXCEPTION_INFO = "appExceptionInfo";

	public static final String INVALID_DATA = "invalidData";

	public static final String STATUS = "status";
	public static final String MSG = "msg";

	public static final String USER = "user";
	public static final String USER_NAME = "userName";
	public static final String EMAIL_ID = "emailId";

	public static final String MODULES = "modules";
	public static final String USER_MODULE_IDS = "userModuleIds";
	public static final String MODULE_NAME = "moduleName";
	public static final String MODULES_ERROR = "modulesError";

	public static final String ROLE_NAME = "roleName";
}