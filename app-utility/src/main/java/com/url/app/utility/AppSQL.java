package com.url.app.utility;

public class AppSQL {

	private AppSQL() {
		throw new IllegalStateException("Utility class");
	}

	public static final String FROM_USER = "from User u ";

	public static final String ORDER_BY_USER_ID_ASC = "order by u.userId asc";

	public static final String PARAMETER_USER_ID = "userId";

	public static final String PARAMETER_USER_NAME = "userName";

	public static final String PARAMETER_TYPE = "type";

	//@formatter:off
	/* sql queries */
	public static final String QRY_FIND_ALL_ACTION = "SELECT a FROM Action a";

	public static final String QRY_FIND_ALL_COMMON_SETTING = "SELECT c FROM CommonSetting c";

	public static final String QRY_FIND_ALL_COURSE = "SELECT c FROM Course c";

	public static final String QRY_FIND_ALL_COURSE_TYPE = "SELECT c FROM CourseType c";

	public static final String QRY_FIND_ALL_USER_SKILLSET = "SELECT u FROM UserSkillset u";

	public static final String QRY_FIND_ALL_FEEDBACK_ANSWER = "SELECT f FROM FeedbackAnswer f";

	public static final String QRY_FIND_ALL_FEEDBACK_QUESTION = "SELECT f FROM FeedbackQuestion f";

	public static final String QRY_FIND_ALL_MODULE = "SELECT m FROM Module m";

	public static final String QRY_FIND_ALL_PRIVILEGE = "SELECT p FROM Privilege p";

	public static final String QRY_FIND_ALL_ROLE = "SELECT r FROM Role r";

	public static final String QRY_FIND_ALL_ROLE_PRIVILEGE_RELATION = "SELECT r FROM RolePrivilegeRelation r";

	public static final String QRY_FIND_ALL_USER = "SELECT u FROM User u";

	public static final String QRY_FIND_ALL_USER_ROLE_RELATION = "SELECT u FROM UserRoleRelation u";

	public static final String QRY_SELECT_URL_ROLE_ID = "select distinct new com.url.app.dto.entity.UrlRolesBean(a.actionPath, r.roleId) "
			+ "from RolePrivilegeRelation rpr "
			+ "inner join rpr.id.role r "
			+ "inner join rpr.id.privilege p "
			+ "inner join p.actions a "
			+ "where rpr.isActive=1 and r.isActive=1 and p.isActive=1 and a.isSkip=0 and a.isActive=1"
			+ "order by a.actionPath asc";

	public static final String QRY_SELECT_ACTIONS = "from Action a "
			+ "order by a.isSkip asc, a.actionPath asc";

	public static final String QRY_SELECT_USER_ROLES_BY_USERNAME = FROM_USER
			+ "join fetch u.userRoleRelations urr "
			+ "where u.userName=:userName "
			+ ORDER_BY_USER_ID_ASC;

	public static final String QRY_UPDATE_USER_LAST_SUCCESS_LOGIN_DATE = "update User "
			+ "set failedAttemptCnt=:failedAttemptCnt, "
			+ "lastSuccessfulLoginDate=:lastSuccessfulLoginDate "
			+ "where userId=:userId";

	public static final String QRY_UPDATE_USER_LAST_FAILED_LOGIN_DATE = "update User "
			+ "set failedAttemptCnt=failedAttemptCnt+1, "
			+ "lastFailedLoginDate=:lastFailedLoginDate "
			+ "where userName=:userName";

	public static final String QRY_SELECT_USER_ROLES_BY_USERID = FROM_USER
			+ "left join fetch u.userRoleRelations urr "
			+ "left join fetch urr.id.role r "
			+ "where u.userId=:userId "
			+ ORDER_BY_USER_ID_ASC;

	public static final String QRY_SELECT_USER_SKILLSETS_BY_USERID = FROM_USER
			+ "left join fetch u.userSkillsets us "
			+ "left join fetch us.id.module m "
			+ "where u.userId=:userId "
			+ ORDER_BY_USER_ID_ASC;

	public static final String QRY_UPDATE_USER_SKILLSETS_IS_ACTIVE = "update UserSkillset us "
			+ "set us.isActive=:isActive, "
			+ "us.modifiedBy=:modifiedBy, "
			+ "us.modifiedDate=:modifiedDate "
			+ "where us.id.user.userId=:userId";

	public static final String QRY_SELECT_COMMON_SETTING_VALUE = "select cs.value "
			+ "from CommonSetting cs "
			+ "where cs.type=:type "
			+ "order by cs.orderNumber asc";

	public static final String QRY_UPDATE_COMMON_SETTING_VALUE = "update CommonSetting "
			+ "set value=value+1 "
			+ "where type=:type";

	/* pl/sql Procedures */
	public static final String PROC_ALL_USERS = "get_all_users";

	public static final String PROC_USER_SKILLSETS = "get_user_skillsets";
	//@formatter:on
}