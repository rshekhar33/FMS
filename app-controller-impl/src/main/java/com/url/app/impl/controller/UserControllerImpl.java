package com.url.app.impl.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.url.app.interf.controller.UserController;
import com.url.app.utility.AppCommon;
import com.url.app.utility.AppCssActiveClass;
import com.url.app.utility.AppHttpSessionKey;
import com.url.app.utility.AppLogMessage;
import com.url.app.utility.AppUrlView;

/**
 * Controller for user related actions.
 * 
 * @author Shekhar Shinde
 */
@Controller
public class UserControllerImpl implements UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserControllerImpl.class);

	@Override
	public String list(final HttpSession httpSess, final ModelMap modelMap) {
		httpSess.removeAttribute(AppHttpSessionKey.HID_USER_ID);
		AppCssActiveClass.addClass(modelMap, AppCssActiveClass.USER_MENU_OPEN_CLS, AppCssActiveClass.USERS_ACTIVE_CLS);

		return AppUrlView.VIEW_USER_LIST;
	}

	@Override
	public String add(final HttpSession httpSess, final ModelMap modelMap) {
		httpSess.removeAttribute(AppHttpSessionKey.HID_USER_ID);
		AppCssActiveClass.addClass(modelMap, AppCssActiveClass.USER_MENU_OPEN_CLS, AppCssActiveClass.USER_CRUD_ACTIVE_CLS);

		return AppUrlView.VIEW_USER_CRUD;
	}

	@Override
	public String update(final HttpSession httpSess, final String linkId) {
		httpSess.setAttribute(AppHttpSessionKey.HID_USER_ID, linkId);

		return AppUrlView.REDIRECT_URL_USER_UPDATE;
	}

	@Override
	public String updateScreen(final HttpSession httpSess, final ModelMap modelMap) {
		final String userId = (String) httpSess.getAttribute(AppHttpSessionKey.HID_USER_ID);
		logger.debug(AppLogMessage.USER_ID_MSG, userId);

		String url = AppUrlView.REDIRECT_URL_USER_LIST;
		if (!AppCommon.isEmpty(userId)) {
			AppCssActiveClass.addClass(modelMap, AppCssActiveClass.USER_MENU_OPEN_CLS, AppCssActiveClass.USER_CRUD_ACTIVE_CLS);
			url = AppUrlView.VIEW_USER_CRUD;
		}

		return url;
	}
}