package com.url.app.impl.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.url.app.interf.controller.CourseTypeController;
import com.url.app.utility.AppCommon;
import com.url.app.utility.AppCssActiveClass;
import com.url.app.utility.AppHttpSessionKey;
import com.url.app.utility.AppLogMessage;
import com.url.app.utility.AppUrlView;

/**
 * Controller for course type related actions.
 * 
 * @author Shekhar Shinde
 */
@Controller
public class CourseTypeControllerImpl implements CourseTypeController {
	private static final Logger logger = LoggerFactory.getLogger(CourseTypeControllerImpl.class);

	@Override
	public String list(final HttpSession httpSess, final ModelMap modelMap) {
		httpSess.removeAttribute(AppHttpSessionKey.HID_COURSE_TYPE_ID);
		AppCssActiveClass.addClass(modelMap, AppCssActiveClass.COURSE_TYPE_MENU_OPEN_CLS, AppCssActiveClass.COURSE_TYPES_ACTIVE_CLS);

		return AppUrlView.VIEW_COURSE_TYPE_LIST;
	}

	@Override
	public String add(final HttpSession httpSess, final ModelMap modelMap) {
		httpSess.removeAttribute(AppHttpSessionKey.HID_COURSE_TYPE_ID);
		AppCssActiveClass.addClass(modelMap, AppCssActiveClass.COURSE_TYPE_MENU_OPEN_CLS, AppCssActiveClass.COURSE_TYPE_CRUD_ACTIVE_CLS);

		return AppUrlView.VIEW_COURSE_TYPE_CRUD;
	}

	@Override
	public String update(final HttpSession httpSess, final String linkId) {
		httpSess.setAttribute(AppHttpSessionKey.HID_COURSE_TYPE_ID, linkId);

		return AppUrlView.REDIRECT_URL_COURSE_TYPE_UPDATE;
	}

	@Override
	public String updateScreen(final HttpSession httpSess, final ModelMap modelMap) {
		final String courseTypeId = (String) httpSess.getAttribute(AppHttpSessionKey.HID_COURSE_TYPE_ID);
		logger.debug(AppLogMessage.COURSE_TYPE_ID_MSG, courseTypeId);

		String url = AppUrlView.REDIRECT_URL_COURSE_TYPE_LIST;
		if (!AppCommon.isEmpty(courseTypeId)) {
			AppCssActiveClass.addClass(modelMap, AppCssActiveClass.COURSE_TYPE_MENU_OPEN_CLS, AppCssActiveClass.COURSE_TYPE_CRUD_ACTIVE_CLS);
			url = AppUrlView.VIEW_COURSE_TYPE_CRUD;
		}

		return url;
	}
}