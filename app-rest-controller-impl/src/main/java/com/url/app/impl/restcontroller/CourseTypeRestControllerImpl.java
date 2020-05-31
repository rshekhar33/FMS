package com.url.app.impl.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.url.app.dto.entity.CourseType;
import com.url.app.interf.restcontroller.CourseTypeRestController;
import com.url.app.interf.service.AppCourseTypeService;

/**
 * Rest Controller for course type related actions.
 * 
 * @author Shekhar Shinde
 */
@RestController
public class CourseTypeRestControllerImpl implements CourseTypeRestController {

	@Autowired
	private AppCourseTypeService appCourseTypeService;

	@Override
	public List<CourseType> fetchDetails() {
		return appCourseTypeService.fetchDetailsCourseTypes();
	}

	@Override
	public CourseType fetchData(final CourseType courseType) {
		return appCourseTypeService.fetchDataCourseType(courseType);
	}

	@Override
	public Map<String, String> validateSave(final CourseType courseType) {
		return appCourseTypeService.validateSaveCourseType(courseType);
	}

	@Override
	public Map<String, String> validateUpdateActivation(final CourseType courseType) {
		return appCourseTypeService.validateUpdateActivation(courseType);
	}
}