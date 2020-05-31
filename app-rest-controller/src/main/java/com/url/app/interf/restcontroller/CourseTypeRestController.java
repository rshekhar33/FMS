package com.url.app.interf.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.url.app.dto.entity.CourseType;
import com.url.app.utility.AppUrlView;

@RequestMapping(value = AppUrlView.PATH_ROOT_COURSE_TYPE)
public interface CourseTypeRestController {

	/**
	 * Fetch data of Course Types listing.
	 */
	@PostMapping(value = AppUrlView.PATH_FETCH_DETAILS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	List<CourseType> fetchDetails();

	/**
	 * Fetch data of on add/update course type screen.
	 */
	@PostMapping(value = AppUrlView.PATH_FETCH_DATA, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	CourseType fetchData(@RequestBody CourseType courseType);

	/**
	 * Validate and save data of on add/update course type screen.
	 */
	@PostMapping(value = AppUrlView.PATH_VALIDATE_SAVE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	Map<String, String> validateSave(@RequestBody CourseType courseType);

	/**
	 * Validate and save data of on course type activation screen.
	 */
	@PostMapping(value = AppUrlView.PATH_ACTIVATION, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	Map<String, String> validateUpdateActivation(@RequestBody CourseType courseType);
}