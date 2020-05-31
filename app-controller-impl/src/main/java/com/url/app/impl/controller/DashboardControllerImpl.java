package com.url.app.impl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.url.app.interf.controller.DashboardController;
import com.url.app.utility.AppCssActiveClass;
import com.url.app.utility.AppUrlView;

/**
 * Controller for dashboard related actions.
 * 
 * @author Shekhar Shinde
 */
@Controller
public class DashboardControllerImpl implements DashboardController {

	@Override
	public String dashboard(final ModelMap modelMap) {
		AppCssActiveClass.addClass(modelMap, AppCssActiveClass.DASHBOARD_ACTIVE_CLS);

		return AppUrlView.VIEW_DASHBOARD;
	}
}