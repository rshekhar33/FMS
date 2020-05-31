package com.url.app.utility;

/**
 * HttpSession Key Constants.
 * 
 * @author Shekhar Shinde
 */
public class AppHttpSessionKey {

	private AppHttpSessionKey() {
		throw new IllegalStateException("Utility class");
	}

	public static final String LOGIN_ERROR = "loginError";
	public static final String HID_USER_ID = "hidUserId";
	public static final String HID_ROLE_ID = "hidRoleId";
	public static final String HID_MODULE_ID = "hidModuleId";
	public static final String HID_COURSE_TYPE_ID = "hidCourseTypeId";
}