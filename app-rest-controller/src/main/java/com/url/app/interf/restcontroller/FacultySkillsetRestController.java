package com.url.app.interf.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.url.app.dto.entity.FacultySkillsetMng;
import com.url.app.dto.entity.User;
import com.url.app.utility.AppUrlView;

/**
 * Rest Controller for faculty skillset related actions.
 * 
 * @author Shekhar Shinde
 */
@RequestMapping(value = AppUrlView.PATH_ROOT_FACULTY_SKILLSET)
public interface FacultySkillsetRestController {

	/**
	 * Fetch data of Faculty Skillsets listing.
	 */
	@PostMapping(value = AppUrlView.PATH_FETCH_DETAILS, produces = MediaType.APPLICATION_JSON_VALUE)
	List<FacultySkillsetMng> fetchDetails();

	/**
	 * Fetch data of on add/update faculty skillset screen.
	 */
	@PostMapping(value = AppUrlView.PATH_FETCH_DATA, produces = MediaType.APPLICATION_JSON_VALUE)
	Map<String, Object> fetchData(@RequestBody User user);

	/**
	 * Validate and save data of on add/update faculty skillset screen.
	 */
	@PostMapping(value = AppUrlView.PATH_VALIDATE_SAVE, produces = MediaType.APPLICATION_JSON_VALUE)
	Map<String, String> validateSave(@RequestParam Map<String, String> allRequestParams);

	/**
	 * Validate and save data of on faculty skillset activation screen.
	 */
	@PostMapping(value = AppUrlView.PATH_ACTIVATION, produces = MediaType.APPLICATION_JSON_VALUE)
	Map<String, Object> validateUpdateActivation(@RequestParam Map<String, String> allRequestParams);
}