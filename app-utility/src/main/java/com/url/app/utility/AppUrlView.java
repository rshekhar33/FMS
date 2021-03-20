package com.url.app.utility;

/**
 * Application's URL and view names
 * 
 * @author Shekhar Shinde
 */
public class AppUrlView {

	private AppUrlView() {
		throw new IllegalStateException("Utility class");
	}

	public static final String LOGIN = "login";
	public static final String SIGN_UP = "signUp";
	public static final String ACCESS_DENIED = "accessDenied";
	public static final String INVALID_SESSION = "invalidSession";
	public static final String SESSION_EXPIRED = "sessionExpired";
	public static final String DASHBOARD = "dashboard";
	public static final String USER = "user";
	public static final String MODULE = "module";
	public static final String COURSE_TYPE = "courseType";
	public static final String ROLE = "role";
	public static final String USER_SKILLSET = "userSkillset";
	public static final String SEPARATOR = "/";

	public static final String ALL_URL_ANT_PATTERN = "/**";

	public static final String REDIRECT = "redirect:/";

	/* URL root paths */
	public static final String PATH_ROOT_DASHBOARD = DASHBOARD;
	public static final String PATH_ROOT_USER = USER;
	public static final String PATH_ROOT_MODULE = MODULE;
	public static final String PATH_ROOT_COURSE_TYPE = COURSE_TYPE;
	public static final String PATH_ROOT_ROLE = ROLE;
	public static final String PATH_ROOT_USER_SKILLSET = USER_SKILLSET;

	/* URL paths */
	public static final String PATH_ROOT = SEPARATOR;
	public static final String PATH_LOGIN = LOGIN;
	public static final String PATH_LOGIN_CHECK = "loginCheck";
	public static final String PATH_LOGOUT = "logout";
	public static final String PATH_HOME = "home";
	public static final String PATH_SIGN_UP = SIGN_UP;
	public static final String PATH_ACCESS_DENIED = ACCESS_DENIED;
	public static final String PATH_INVALID_SESSION = INVALID_SESSION;
	public static final String PATH_SESSION_EXPIRED = SESSION_EXPIRED;
	public static final String PATH_ERROR = "error";

	public static final String PATH_LIST = "list";
	public static final String PATH_ADD = "add";
	public static final String PATH_UPDATE = "update";
	public static final String PATH_ACTIVATION = "activation";
	public static final String PATH_FETCH_DETAILS = "fetchDetails";
	public static final String PATH_FETCH_ACTIVE_DETAILS = "fetchActiveDetails";
	public static final String PATH_FETCH_DATA = "fetchData";
	public static final String PATH_VALIDATE_SAVE = "validateSave";

	public static final String PATH_DASHBOARD = DASHBOARD;

	/* URL full paths */
	public static final String URL_LOGIN = PATH_ROOT + PATH_LOGIN;
	public static final String URL_LOGOUT = PATH_ROOT + PATH_LOGOUT;
	public static final String URL_LOGIN_SUCCESS = PATH_ROOT + PATH_HOME;
	public static final String URL_LOGIN_FAILURE = PATH_ROOT + PATH_LOGIN + "?" + PATH_ERROR;
	public static final String URL_ACCESS_DENIED = PATH_ROOT + PATH_ACCESS_DENIED;
	public static final String URL_INVALID_SESSION = PATH_ROOT + PATH_INVALID_SESSION;
	public static final String URL_SESSION_EXPIRED = PATH_ROOT + PATH_SESSION_EXPIRED;

	/* URL redirect paths */
	public static final String REDIRECT_URL_HOME = REDIRECT + PATH_HOME;
	public static final String REDIRECT_URL_DASHBOARD = REDIRECT + PATH_ROOT_DASHBOARD + SEPARATOR + PATH_DASHBOARD;

	public static final String REDIRECT_URL_USER_LIST = REDIRECT + PATH_ROOT_USER + SEPARATOR + PATH_LIST;
	public static final String REDIRECT_URL_USER_UPDATE = REDIRECT + PATH_ROOT_USER + SEPARATOR + PATH_UPDATE;

	public static final String REDIRECT_URL_ROLE_LIST = REDIRECT + PATH_ROOT_ROLE + SEPARATOR + PATH_LIST;
	public static final String REDIRECT_URL_ROLE_UPDATE = REDIRECT + PATH_ROOT_ROLE + SEPARATOR + PATH_UPDATE;

	public static final String REDIRECT_URL_MODULE_LIST = REDIRECT + PATH_ROOT_MODULE + SEPARATOR + PATH_LIST;
	public static final String REDIRECT_URL_MODULE_UPDATE = REDIRECT + PATH_ROOT_MODULE + SEPARATOR + PATH_UPDATE;

	public static final String REDIRECT_URL_COURSE_TYPE_LIST = REDIRECT + PATH_ROOT_COURSE_TYPE + SEPARATOR + PATH_LIST;
	public static final String REDIRECT_URL_COURSE_TYPE_UPDATE = REDIRECT + PATH_ROOT_COURSE_TYPE + SEPARATOR + PATH_UPDATE;

	public static final String REDIRECT_URL_USER_SKILLSET_LIST = REDIRECT + PATH_ROOT_USER_SKILLSET + SEPARATOR + PATH_LIST;
	public static final String REDIRECT_URL_USER_SKILLSET_UPDATE = REDIRECT + PATH_ROOT_USER_SKILLSET + SEPARATOR + PATH_UPDATE;

	/* View root paths */
	public static final String VIEW_ROOT_LOGIN_SIGN_UP = "loginSignUp/";
	public static final String VIEW_ROOT_OTHER = "other/";
	public static final String VIEW_ROOT_DASHBOARD = DASHBOARD + SEPARATOR;
	public static final String VIEW_ROOT_USER = USER + SEPARATOR;
	public static final String VIEW_ROOT_MODULE = MODULE + SEPARATOR;
	public static final String VIEW_ROOT_COURSE_TYPE = COURSE_TYPE + SEPARATOR;
	public static final String VIEW_ROOT_ROLE = ROLE + SEPARATOR;
	public static final String VIEW_ROOT_USER_SKILLSET = USER_SKILLSET + SEPARATOR;

	/* View paths */
	public static final String VIEW_LIST = "list";
	public static final String VIEW_CRUD = "crud";

	public static final String VIEW_LOGIN = VIEW_ROOT_LOGIN_SIGN_UP + LOGIN;
	public static final String VIEW_SIGN_UP = VIEW_ROOT_LOGIN_SIGN_UP + SIGN_UP;

	public static final String VIEW_ACCESS_DENIED = VIEW_ROOT_OTHER + ACCESS_DENIED;
	public static final String VIEW_APP_ERROR_PAGE = VIEW_ROOT_OTHER + "appErrorPage";
	public static final String VIEW_GLOBAL_ERROR_PAGE = VIEW_ROOT_OTHER + "globalErrorPage";
	public static final String VIEW_INVALID_SESSION = VIEW_ROOT_OTHER + INVALID_SESSION;
	public static final String VIEW_SESSION_EXPIRED = VIEW_ROOT_OTHER + SESSION_EXPIRED;

	public static final String VIEW_DASHBOARD = VIEW_ROOT_DASHBOARD + DASHBOARD;

	public static final String VIEW_USER_LIST = VIEW_ROOT_USER + VIEW_LIST;
	public static final String VIEW_USER_CRUD = VIEW_ROOT_USER + VIEW_CRUD;

	public static final String VIEW_MODULE_LIST = VIEW_ROOT_MODULE + VIEW_LIST;
	public static final String VIEW_MODULE_CRUD = VIEW_ROOT_MODULE + VIEW_CRUD;

	public static final String VIEW_COURSE_TYPE_LIST = VIEW_ROOT_COURSE_TYPE + VIEW_LIST;
	public static final String VIEW_COURSE_TYPE_CRUD = VIEW_ROOT_COURSE_TYPE + VIEW_CRUD;

	public static final String VIEW_ROLE_LIST = VIEW_ROOT_ROLE + VIEW_LIST;
	public static final String VIEW_ROLE_CRUD = VIEW_ROOT_ROLE + VIEW_CRUD;

	public static final String VIEW_USER_SKILLSET_LIST = VIEW_ROOT_USER_SKILLSET + VIEW_LIST;
	public static final String VIEW_USER_SKILLSET_CRUD = VIEW_ROOT_USER_SKILLSET + VIEW_CRUD;

	/* Parameters */
	public static final String PARAMETER_NAME_USERNAME = "userName";
	public static final String PARAMETER_NAME_PASSWORD = "passwordEnc";
	public static final String PARAMETER_NAME_REMEMBER_ME = "remember";

	/* Cookie name */
	public static final String COOKIE_NAME_REMEMBER_ME = "rememberMeLogin";
	public static final String COOKIE_NAME_JSESSIONID = "JSESSIONID";
}