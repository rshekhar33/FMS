package com.url.app.interf.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.url.app.dto.entity.User;
import com.url.app.dto.entity.UserSkillsetMng;
import com.url.app.utility.AppUrlView;

/**
 * Rest Controller for user skillset related actions.
 * 
 * @author Shekhar Shinde
 */
@RequestMapping(value = AppUrlView.PATH_ROOT_USER_SKILLSET)
public interface UserSkillsetRestController {

	/**
	 * Fetch data of User Skillsets listing.
	 */
	@PostMapping(value = AppUrlView.PATH_FETCH_DETAILS, produces = MediaType.APPLICATION_JSON_VALUE)
	List<UserSkillsetMng> fetchDetails();

	/**
	 * Fetch data of on add/update user skillset screen.
	 */
	@PostMapping(value = AppUrlView.PATH_FETCH_DATA, produces = MediaType.APPLICATION_JSON_VALUE)
	User fetchData(@RequestBody User user);

	/**
	 * Validate and save data of on add/update user skillset screen.
	 */
	@PostMapping(value = AppUrlView.PATH_VALIDATE_SAVE, produces = MediaType.APPLICATION_JSON_VALUE)
	Map<String, Object> validateSave(@RequestBody User user);

	/**
	 * Validate and save data of on user skillset activation screen.
	 */
	@PostMapping(value = AppUrlView.PATH_ACTIVATION, produces = MediaType.APPLICATION_JSON_VALUE)
	Map<String, String> validateUpdateActivation(@RequestBody User user);
}