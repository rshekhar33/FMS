package com.url.app.utility;

import org.springframework.ui.ModelMap;

/**
 * CSS active class name Constants.
 * 
 * @author Shekhar Shinde
 */
public class AppCssActiveClass {

	private AppCssActiveClass() {
		throw new IllegalStateException("Utility class");
	}

	public static final String ACTIVE_CLASS = "active";
	public static final String MENU_OPEN_CLASS = "menu-open";

	public static final String DASHBOARD_ACTIVE_CLS = "dashboardActiveCls";

	public static final String USER_MENU_OPEN_CLS = "userMenuOpenCls";
	public static final String USERS_ACTIVE_CLS = "usersActiveCls";
	public static final String USER_CRUD_ACTIVE_CLS = "userCrudActiveCls";

	public static final String MODULE_MENU_OPEN_CLS = "moduleMenuOpenCls";
	public static final String MODULES_ACTIVE_CLS = "modulesActiveCls";
	public static final String MODULE_CRUD_ACTIVE_CLS = "moduleCrudActiveCls";

	public static final String COURSE_TYPE_MENU_OPEN_CLS = "courseTypeMenuOpenCls";
	public static final String COURSE_TYPES_ACTIVE_CLS = "courseTypesActiveCls";
	public static final String COURSE_TYPE_CRUD_ACTIVE_CLS = "courseTypeCrudActiveCls";

	public static final String ROLE_MENU_OPEN_CLS = "roleMenuOpenCls";
	public static final String ROLES_ACTIVE_CLS = "rolesActiveCls";
	public static final String ROLE_CRUD_ACTIVE_CLS = "roleCrudActiveCls";

	public static final String FACULTY_SKILLSET_MENU_OPEN_CLS = "facultySkillsetMenuOpenCls";
	public static final String FACULTY_SKILLSETS_ACTIVE_CLS = "facultySkillsetsActiveCls";
	public static final String FACULTY_SKILLSET_CRUD_ACTIVE_CLS = "facultySkillsetCrudActiveCls";

	public static final void addClass(final ModelMap modelMap, final String menuClsVar, final String submenuClsVar) {
		modelMap.addAttribute(menuClsVar, MENU_OPEN_CLASS);
		modelMap.addAttribute(submenuClsVar, ACTIVE_CLASS);
	}

	public static final void addClass(final ModelMap modelMap, final String menuClsVar) {
		modelMap.addAttribute(menuClsVar, ACTIVE_CLASS);
	}
}