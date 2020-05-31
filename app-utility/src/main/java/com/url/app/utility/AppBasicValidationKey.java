package com.url.app.utility;

/**
 * Basic validation message keys of application.
 * 
 * @author Shekhar Shinde
 */
public class AppBasicValidationKey {

	private AppBasicValidationKey() {
		throw new IllegalStateException("Utility class");
	}

	public static final String UPDATE_FAILED_ERROR = "{update.failed.error}";
	public static final String MANDATORY_FIELD_ERROR = "{mandatory.field.error}";
	public static final String INVALID_EMAIL_ERROR = "{invalid.email.error}";
	public static final String LENGTH_ERROR = "{length.error}";
	public static final String ONLY_NUMBER_ERROR = "{only.number.error}";

	public static final String USER_FIRSTNAME_ONLYALPHABETS_ERROR = "{user.firstname.onlyalphabets.error}";
	public static final String USER_MIDDLENAME_ONLYALPHABETS_ERROR = "{user.middlename.onlyalphabets.error}";
	public static final String USER_LASTNAME_ONLYALPHABETS_ERROR = "{user.lastname.onlyalphabets.error}";
	public static final String USER_MOBILE_LENGTH_ERROR = "{user.mobile.length.error}";
	public static final String USER_USERNAME_RESTRICTEDCHAR2_ERROR = "{user.username.restrictedchar2.error}";

	public static final String COURSETYPE_COURSETYPENAME_RESTRICTEDCHAR3_ERROR = "{coursetype.coursetypename.restrictedchar3.error}";

	public static final String MODULE_MODULENAME_RESTRICTEDCHAR3_ERROR = "{module.modulename.restrictedchar3.error}";

	public static final String ROLE_ROLENAME_RESTRICTEDCHAR3_ERROR = "{role.rolename.restrictedchar3.error}";
}