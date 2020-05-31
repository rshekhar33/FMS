package com.url.app.interf.controller;

import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.url.app.utility.AppUrlView;

/**
 * Controller for faculty skillset related actions.
 * 
 * @author Shekhar Shinde
 */
@RequestMapping(value = AppUrlView.PATH_ROOT_FACULTY_SKILLSET)
public interface FacultySkillsetController {

	/**
	 * Faculty Skillsets Listing Screen.
	 */
	@GetMapping(value = AppUrlView.PATH_LIST)
	String list(HttpSession httpSess, ModelMap modelMap);

	/**
	 * Add faculty skillset screen.
	 */
	@GetMapping(value = AppUrlView.PATH_ADD)
	String add(HttpSession httpSess, ModelMap modelMap);

	/**
	 * Action to get facultySkillsetId on update faculty skillset screen.
	 */
	@PostMapping(value = AppUrlView.PATH_UPDATE)
	String update(HttpSession httpSess, @RequestParam("linkId") String linkId, @RequestParam("linkId2") String linkId2);

	/**
	 * Update faculty skillset screen.
	 */
	@GetMapping(value = AppUrlView.PATH_UPDATE)
	String updateScreen(HttpSession httpSess, ModelMap modelMap);
}