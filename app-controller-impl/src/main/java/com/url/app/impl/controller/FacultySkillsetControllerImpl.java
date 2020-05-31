package com.url.app.impl.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.url.app.interf.controller.FacultySkillsetController;
import com.url.app.utility.AppCommon;
import com.url.app.utility.AppCssActiveClass;
import com.url.app.utility.AppHttpSessionKey;
import com.url.app.utility.AppLogMessage;
import com.url.app.utility.AppUrlView;

/**
 * Controller for faculty skillset related actions.
 * 
 * @author Shekhar Shinde
 */
@Controller
public class FacultySkillsetControllerImpl implements FacultySkillsetController {
	private static final Logger logger = LoggerFactory.getLogger(FacultySkillsetControllerImpl.class);

	@Override
	public String list(final HttpSession httpSess, final ModelMap modelMap) {
		httpSess.removeAttribute(AppHttpSessionKey.HID_USER_ID);
		httpSess.removeAttribute(AppHttpSessionKey.HID_MODULE_ID);
		AppCssActiveClass.addClass(modelMap, AppCssActiveClass.FACULTY_SKILLSET_MENU_OPEN_CLS, AppCssActiveClass.FACULTY_SKILLSETS_ACTIVE_CLS);

		return AppUrlView.VIEW_FACULTY_SKILLSET_LIST;
	}

	@Override
	public String add(final HttpSession httpSess, final ModelMap modelMap) {
		httpSess.removeAttribute(AppHttpSessionKey.HID_USER_ID);
		httpSess.removeAttribute(AppHttpSessionKey.HID_MODULE_ID);
		AppCssActiveClass.addClass(modelMap, AppCssActiveClass.FACULTY_SKILLSET_MENU_OPEN_CLS, AppCssActiveClass.FACULTY_SKILLSET_CRUD_ACTIVE_CLS);

		return AppUrlView.VIEW_FACULTY_SKILLSET_CRUD;
	}

	@Override
	public String update(final HttpSession httpSess, final String linkId, final String linkId2) {
		httpSess.setAttribute(AppHttpSessionKey.HID_USER_ID, linkId);
		httpSess.setAttribute(AppHttpSessionKey.HID_MODULE_ID, linkId2);

		return AppUrlView.REDIRECT_URL_FACULTY_SKILLSET_UPDATE;
	}

	@Override
	public String updateScreen(final HttpSession httpSess, final ModelMap modelMap) {
		final String userId = (String) httpSess.getAttribute(AppHttpSessionKey.HID_USER_ID);
		final String moduleId = (String) httpSess.getAttribute(AppHttpSessionKey.HID_MODULE_ID);
		logger.debug(AppLogMessage.USER_ID_MODULE_ID_MSG, userId, moduleId);

		String url = AppUrlView.REDIRECT_URL_FACULTY_SKILLSET_LIST;
		if (!AppCommon.isEmpty(userId) && !AppCommon.isEmpty(moduleId)) {
			AppCssActiveClass.addClass(modelMap, AppCssActiveClass.FACULTY_SKILLSET_MENU_OPEN_CLS, AppCssActiveClass.FACULTY_SKILLSET_CRUD_ACTIVE_CLS);
			url = AppUrlView.VIEW_FACULTY_SKILLSET_CRUD;
		}

		return url;
	}
}