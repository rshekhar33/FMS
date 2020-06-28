package com.url.app.interf.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.url.app.dto.entity.User;
import com.url.app.dto.entity.UserMng;
import com.url.app.utility.AppUrlView;

/**
 * Rest Controller for user related actions.
 * 
 * @author Shekhar Shinde
 */
@RequestMapping(value = AppUrlView.PATH_ROOT_USER)
public interface UserRestController {

	/**
	 * Fetch data of Users Listing.
	 */
	@PostMapping(value = AppUrlView.PATH_FETCH_DETAILS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	List<UserMng> fetchDetails();

	/**
	 * Fetch data of on add/update user screen.
	 */
	@PostMapping(value = AppUrlView.PATH_FETCH_DATA, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	User fetchData(@RequestBody User user);

	/**
	 * Validate and save data of on add/update user screen.
	 */
	@PostMapping(value = AppUrlView.PATH_VALIDATE_SAVE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	Map<String, Object> validateSave(@RequestBody User user);

	/**
	 * Validate and save data of on user activation screen.
	 */
	@PostMapping(value = AppUrlView.PATH_ACTIVATION, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	Map<String, String> validateUpdateActivation(@RequestBody User user);
}