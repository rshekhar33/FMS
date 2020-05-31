package com.url.app.interf.dao;

import java.util.List;

import com.url.app.dto.entity.Action;
import com.url.app.dto.entity.FacultySkillsetMng;
import com.url.app.dto.entity.UrlRolesBean;
import com.url.app.dto.entity.User;
import com.url.app.dto.entity.UserMng;

/**
 * Dao Layer of application.
 * Database related method declaration.
 * 
 * @author Shekhar Shinde
 */
public interface AppDao {

	/**
	 * Get all mapped actions with its associated roles.
	 * 
	 * @return list of actions and its privileged roles.
	 */
	List<UrlRolesBean> fetchUrlRoleIds();

	/**
	 * Get all action details.
	 * 
	 * @return list of actions with its details.
	 */
	List<Action> fetchActions();

	/**
	 * Get valid User entry from database with its roles.
	 * 
	 * @param userName the userName of User.
	 * @return populated instance of {@link User} with its roles if userName is valid or else returns null.
	 */
	User fetchUser(String userName);

	/**
	 * Update users successful login date.
	 * 
	 * @param userId the id of User.
	 * @return the number of entities updated.
	 */
	int userUpdateLastLoginSuccess(Integer userId);

	/**
	 * Update users last failure login date and increment login failure count.
	 * 
	 * @param userName the userName of User.
	 * @return the number of entities updated.
	 */
	int userUpdateLastLoginFailure(String userName);

	/**
	 * Get all user details.
	 * 
	 * @return list of users with its details.
	 */
	List<UserMng> fetchUsersListing();

	/**
	 * Get User with its roles from userId
	 * 
	 * @param userId the id of User.
	 * @return User from database.
	 */
	User fetchUserWithRoles(Integer userId);

	/**
	 * Get User with its modules(skillsets) from userId
	 * 
	 * @param userId the id of User
	 * @return User from database.
	 */
	User fetchUserWithModules(Integer userId);

	/**
	 * Get counter based on commonSettingsType from common settings table. After fetching counter it increments the counter for future use.
	 * 
	 * @param commonSettingsType the type of counter
	 * @return counter based on commonSettingsType.
	 */
	String generateNewCode(String commonSettingsType);

	/**
	 * Get all faculty skillset details.
	 * 
	 * @return list of faculty skillsets with its details.
	 */
	List<FacultySkillsetMng> fetchFacultySkillsetsListing();
}