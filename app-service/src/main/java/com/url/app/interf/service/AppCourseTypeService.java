package com.url.app.interf.service;

import java.util.List;
import java.util.Map;

import com.url.app.dto.entity.CourseType;

/**
 * Service Layer of application for Course Type.
 * Method declaration of business logic.
 * 
 * @author Shekhar Shinde
 */
public interface AppCourseTypeService {

	/**
	 * Fetch all course type details.
	 * 
	 * @return course type details in json format.
	 */
	List<CourseType> fetchDetailsCourseTypes();

	/**
	 * Fetch course type data on add course type screen.
	 * 
	 * @param courseType contains the courseTypeId of course type.
	 * @return course type data in json format.
	 */
	CourseType fetchDataCourseType(CourseType courseType);

	/**
	 * Validates add/update course type data.
	 * If data is valid then it adds or updates the course type,
	 * if data is invalid then proper error messages are returned in JSON format.
	 * 
	 * @param courseType all the parameters of add/update screen.
	 * @return status as success if data is valid or else all the validation messages with status as failure in JSON.
	 */
	Map<String, String> validateSaveCourseType(CourseType courseType);

	/**
	 * Validates course type activation data.
	 * If data is valid then it updates the course type's isActive flag,
	 * if data is invalid then proper error messages are returned in JSON format.
	 * 
	 * @param courseType all the parameters of activation.
	 * @return status as success if data is valid or else all the validation messages with status as failure in JSON.
	 */
	Map<String, String> validateUpdateActivation(CourseType courseType);
}