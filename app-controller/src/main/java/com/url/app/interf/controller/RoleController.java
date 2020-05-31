package com.url.app.interf.controller;

import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.url.app.utility.AppUrlView;

/**
 * Controller for role related actions.
 * 
 * @author Shekhar Shinde
 */
@RequestMapping(value = AppUrlView.PATH_ROOT_ROLE)
public interface RoleController {

	/**
	 * Roles Listing Screen.
	 */
	@GetMapping(value = AppUrlView.PATH_LIST)
	String list(HttpSession httpSess, ModelMap modelMap);

	/**
	 * Add role screen.
	 */
	@GetMapping(value = AppUrlView.PATH_ADD)
	String add(HttpSession httpSess, ModelMap modelMap);

	/**
	 * Action to get roleId on update role screen.
	 */
	@PostMapping(value = AppUrlView.PATH_UPDATE)
	String update(HttpSession httpSess, @RequestParam("linkId") String linkId);

	/**
	 * Update role screen.
	 */
	@GetMapping(value = AppUrlView.PATH_UPDATE)
	String updateScreen(HttpSession httpSess, ModelMap modelMap);
}