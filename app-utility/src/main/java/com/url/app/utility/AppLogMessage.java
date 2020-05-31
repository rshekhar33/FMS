package com.url.app.utility;

/**
 * Log messages of application.
 * 
 * @author Shekhar Shinde
 */
public class AppLogMessage {

	private AppLogMessage() {
		throw new IllegalStateException("Utility class");
	}

	public static final String USER_ID_USER_NAME_URL_METHOD_TYPE_MSG = "UserId : {} : UserName : {} : Inside URL : '{}' : Method type : {}";
	public static final String USER_ID_TXT_MSG = "UserId : ";
	public static final String USER_NAME_TXT_MSG = " : UserName : ";
	public static final String ENTER_WITH_VALUES_TXT_MSG = " : Entered with values : (";
	public static final String IN_METHOD_TXT_MSG = ") : in method : ";
	public static final String EXITED_WITH_EXEC_TIME_TXT_MSG = " : Exited with execution time : ";
	public static final String RETURN_VALUE_TXT_MSG = " ms : with return value : ";
	public static final String FROM_METHOD_TXT_MSG = " : from method : ";
	public static final String COMMA_TXT_MSG = ", ";

	public static final String COURSE_TYPE_ID_MSG = "courseTypeId = {}";
	public static final String USER_ID_MODULE_ID_MSG = "userId = {}, moduleId = {}";

	public static final String INCORRECT_PATH_MSG = "Incorrect Path at Location : {} with Exception : {}";
	public static final String NO_HANDLER_FOUND_EXCEPTION_MSG = "Global NoHandlerFoundException at Location : {} with Exception : {}";
	public static final String METHOD_NOT_SUPPORTED_EXCEPTION_MSG = "Global HttpRequestMethodNotSupportedException at Location : {} with Exception : {}";
	public static final String GLOBAL_EXCEPTION_MSG = "Global Exception at Location : ";

	public static final String MODULE_ID_MSG = "moduleId = {}";
	public static final String ROLE_ID_MSG = "roleId = {}";
	public static final String USER_ID_MSG = "userId = {}";

	public static final String COURSE_TYPE_MSG = "courseType : {}";
	public static final String MODULE_MSG = "module : {}";
	public static final String ROLE_MSG = "role : {}";
	public static final String USER_MSG = "user : {}";

	public static final String ACTION_ROLE_MAPPING_MSG = "ActionRole Mapping from DB : {}";
	public static final String SKIPPED_ACTIONS_MSG = "Skipped actions : {}";
	public static final String APPLICATION_ACTIONS_MSG = "Application actions : {}";
	public static final String ACTION_ROLES_MSG = "Action and its associcated roles : {}";

	public static final String ALL_REQUEST_PARAMS_MSG = "allRequestParams : {}";

	public static final String USER_USERNAME_MSG = "User found with username : {}";

	public static final String PASSWORD_ENCRYPTED_MSG = "Password encrypted text : {}";

	public static final String FILTER_INVOCATION_URL_MSG = "FilterInvocation Url : {}";
	public static final String REQUEST_URL_AND_ROLES_MSG = "Request Url : '{}' and its Associated Roles : {}";

	public static final String AUTHENTICATION_FAILED_DUE_TO_MSG = "Authentication for userName = '{}' failed due to = {}";

	public static final String LOGIN_SUCCESS_WITH_ROLES_MSG = "Login Success. LoggedUser with roles = {}";
}