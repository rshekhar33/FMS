package com.url.app.interf.service;

import java.util.List;
import java.util.Map;

import com.url.app.dto.entity.FacultySkillsetMng;
import com.url.app.dto.entity.User;

/**
 * Service Layer of application for FacultySkillset.
 * Method declaration of business logic.
 * 
 * @author Shekhar Shinde
 */
public interface AppFacultySkillsetService {

	/**
	 * Fetch all facultySkillset details.
	 * 
	 * @return facultySkillsets details in json format.
	 */
	List<FacultySkillsetMng> fetchDetailsFacultySkillsets();

	/**
	 * Fetch facultySkillset data in case facultySkillsetId is sent else data required on add facultySkillset screen.
	 * 
	 * @param user contains the userId of user.
	 * @return facultySkillset data in json format.
	 */
	Map<String, Object> fetchDataFacultySkillset(User user);

	/**
	 * Validates add/update facultySkillset data.
	 * If data is valid then it adds or updates the facultySkillset,
	 * if data is invalid then proper error messages are returned in JSON format.
	 * 
	 * @param allRequestParams all the parameters of add/update screen.
	 * @return status as success if data is valid or else all the validation messages with status as failure in JSON.
	 */
	Map<String, String> validateSaveFacultySkillset(Map<String, String> allRequestParams);

	/**
	 * Validates facultySkillset activation data.
	 * If data is valid then it updates the facultySkillset's isActive flag,
	 * if data is invalid then proper error messages are returned in JSON format.
	 * 
	 * @param allRequestParams all the parameters of activation.
	 * @return status as success if data is valid or else all the validation messages with status as failure in JSON.
	 */
	Map<String, Object> validateUpdateActivation(Map<String, String> allRequestParams);
}