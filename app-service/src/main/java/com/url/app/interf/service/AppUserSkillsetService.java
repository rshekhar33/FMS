package com.url.app.interf.service;

import java.util.List;
import java.util.Map;

import com.url.app.dto.entity.User;
import com.url.app.dto.entity.UserSkillsetMng;

/**
 * Service Layer of application for UserSkillset.
 * Method declaration of business logic.
 * 
 * @author Shekhar Shinde
 */
public interface AppUserSkillsetService {

	/**
	 * Fetch all userSkillset details.
	 * 
	 * @return userSkillsets details in json format.
	 */
	List<UserSkillsetMng> fetchDetailsUserSkillsets();

	/**
	 * Fetch userSkillset data on add user screen.
	 * 
	 * @param user contains the userId of user.
	 * @return userSkillset data in json format.
	 */
	User fetchDataUserSkillset(User user);

	/**
	 * Validates add/update userSkillset data.
	 * If data is valid then it adds or updates the userSkillset,
	 * if data is invalid then proper error messages are returned in JSON format.
	 * 
	 * @param user all the parameters of update screen.
	 * @return status as success if data is valid or else all the validation messages with status as failure in JSON.
	 */
	Map<String, Object> validateSaveUserSkillset(User user);

	/**
	 * Validates userSkillset activation data.
	 * If data is valid then it updates the userSkillset's isActive flag,
	 * if data is invalid then proper error messages are returned in JSON format.
	 * 
	 * @param user all the parameters of activation.
	 * @return status as success if data is valid or else all the validation messages with status as failure in JSON.
	 */
	Map<String, String> validateUpdateActivation(User user);
}