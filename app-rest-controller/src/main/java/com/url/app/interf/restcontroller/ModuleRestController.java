package com.url.app.interf.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.url.app.dto.entity.Module;
import com.url.app.utility.AppUrlView;

/**
 * Module Controller for module related actions.
 * 
 * @author Shekhar Shinde
 */
@RequestMapping(value = AppUrlView.PATH_ROOT_MODULE)
public interface ModuleRestController {

	/**
	 * Fetch data of Modules Listing.
	 */
	@PostMapping(value = AppUrlView.PATH_FETCH_DETAILS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	List<Module> fetchDetails();

	/**
	 * Fetch data of Active Modules Listing.
	 */
	@PostMapping(value = AppUrlView.PATH_FETCH_ACTIVE_DETAILS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	List<Module> fetchActiveDetails();

	/**
	 * Fetch data of on add/update module screen.
	 */
	@PostMapping(value = AppUrlView.PATH_FETCH_DATA, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	Module fetchData(@RequestBody Module module);

	/**
	 * Validate and save data of on add/update module screen.
	 */
	@PostMapping(value = AppUrlView.PATH_VALIDATE_SAVE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	Map<String, Object> validateSave(@RequestBody Module module);

	/**
	 * Validate and save data of on module activation screen.
	 */
	@PostMapping(value = AppUrlView.PATH_ACTIVATION, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	Map<String, String> validateUpdateActivation(@RequestBody Module module);
}