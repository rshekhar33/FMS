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

	public static final String EXCEPTION_MSG = "exceptionMsg";
	public static final String EXCEPTION_HEADER = "exceptionHeader";
	public static final String EXCEPTION_DESC = "exceptionDesc";
	public static final String EXCEPTION_STACK = "exceptionStack";

	public static final String STATUS = "status";
	public static final String MSG = "msg";

	public static final String USER = "user";

	public static final String MODULE = "module";
	public static final String MODULES = "modules";
	public static final String USER_MODULE_IDS = "userModuleIds";
	public static final String MODULES_ERROR = "modulesError";

	public static final String COURSE_TYPE = "courseType";

	public static final String ROLE = "role";
	public static final String ROLES = "roles";
}