package com.url.app.impl.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.url.app.dto.entity.User;
import com.url.app.dto.entity.UserMng;
import com.url.app.interf.restcontroller.UserRestController;
import com.url.app.interf.service.AppUserService;

/**
 * Rest Controller for user related actions.
 * 
 * @author Shekhar Shinde
 */
@RestController
public class UserRestControllerImpl implements UserRestController {

	@Autowired
	private AppUserService appUserService;

	@Override
	public List<UserMng> fetchDetails() {
		return appUserService.fetchDetailsUsers();
	}

	@Override
	public User fetchData(final User user) {
		return appUserService.fetchDataUser(user);
	}

	@Override
	public Map<String, String> validateSave(final User user) {
		return appUserService.validateSaveUser(user);
	}

	@Override
	public Map<String, String> validateUpdateActivation(final User user) {
		return appUserService.validateUpdateActivation(user);
	}
}