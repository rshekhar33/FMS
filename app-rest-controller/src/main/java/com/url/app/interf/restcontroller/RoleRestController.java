package com.url.app.interf.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.url.app.dto.entity.Role;
import com.url.app.utility.AppUrlView;

/**
 * Role Controller for role related actions.
 * 
 * @author Shekhar Shinde
 */
@RequestMapping(value = AppUrlView.PATH_ROOT_ROLE)
public interface RoleRestController {

	/**
	 * Fetch data of Roles Listing.
	 */
	@PostMapping(value = AppUrlView.PATH_FETCH_DETAILS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	List<Role> fetchDetails();

	/**
	 * Fetch data of Active Roles Listing.
	 */
	@PostMapping(value = AppUrlView.PATH_FETCH_ACTIVE_DETAILS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	List<Role> fetchActiveDetails();

	/**
	 * Fetch data of on add/update role screen.
	 */
	@PostMapping(value = AppUrlView.PATH_FETCH_DATA, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	Role fetchData(@RequestBody Role role);

	/**
	 * Validate and save data of on add/update role screen.
	 */
	@PostMapping(value = AppUrlView.PATH_VALIDATE_SAVE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	Map<String, Object> validateSave(@RequestBody Role role);

	/**
	 * Validate and save data of on role activation screen.
	 */
	@PostMapping(value = AppUrlView.PATH_ACTIVATION, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	Map<String, String> validateUpdateActivation(@RequestBody Role role);
}