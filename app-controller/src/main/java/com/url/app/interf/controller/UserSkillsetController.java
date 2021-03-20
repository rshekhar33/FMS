package com.url.app.interf.controller;

import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.url.app.utility.AppUrlView;

/**
 * Controller for user skillset related actions.
 * 
 * @author Shekhar Shinde
 */
@RequestMapping(value = AppUrlView.PATH_ROOT_USER_SKILLSET)
public interface UserSkillsetController {

	/**
	 * User Skillsets Listing Screen.
	 */
	@GetMapping(value = AppUrlView.PATH_LIST)
	String list(HttpSession httpSess, ModelMap modelMap);

	/**
	 * Action to get userId on update user skillset screen.
	 */
	@PostMapping(value = AppUrlView.PATH_UPDATE)
	String update(HttpSession httpSess, @RequestParam("linkId") String linkId);

	/**
	 * Update user skillset screen.
	 */
	@GetMapping(value = AppUrlView.PATH_UPDATE)
	String updateScreen(HttpSession httpSess, ModelMap modelMap);
}