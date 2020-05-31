package com.url.app.interf.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.url.app.dto.entity.User;

@Repository(value = "userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

	/**
	 * Get count of userName from Users.
	 * 
	 * @param userName the userName whose count is to be fetched.
	 * @return count of users.
	 */
	Long countByUserName(String userName);

	/**
	 * Get count of emailId from Users.
	 * 
	 * @param emailId the emailId whose count is to be fetched.
	 * @return count of users.
	 */
	Long countByEmailId(String emailId);

	/**
	 * Get count of Users.
	 * 
	 * @param emailId the emailId whose count is to be fetched.
	 * @param userId exclude the records matching this userId.
	 * @return count of users.
	 */
	Long countByEmailIdAndUserIdNot(String emailId, Integer userId);
}