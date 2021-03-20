package com.url.app.impl.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.url.app.dto.entity.UserSkillsetMng;
import com.url.app.dto.entity.User;
import com.url.app.interf.restcontroller.UserSkillsetRestController;
import com.url.app.interf.service.AppUserSkillsetService;

/**
 * Rest Controller for user skillset related actions.
 * 
 * @author Shekhar Shinde
 */
@RestController
public class UserSkillsetRestControllerImpl implements UserSkillsetRestController {

	@Autowired
	private AppUserSkillsetService appUserSkillsetService;

	@Override
	public List<UserSkillsetMng> fetchDetails() {
		return appUserSkillsetService.fetchDetailsUserSkillsets();
	}

	@Override
	public User fetchData(final User user) {
		return appUserSkillsetService.fetchDataUserSkillset(user);
	}

	@Override
	public Map<String, Object> validateSave(final User user) {
		return appUserSkillsetService.validateSaveUserSkillset(user);
	}

	@Override
	public Map<String, String> validateUpdateActivation(final User user) {
		return appUserSkillsetService.validateUpdateActivation(user);
	}
}