package com.url.app.interf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.url.app.dto.entity.Role;

@Repository(value = "roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {

	/**
	 * Get all roles data.
	 * 
	 * @return list of roles.
	 */
	List<Role> findAllByOrderByRoleId();

	/**
	 * Get all roles data.
	 * 
	 * @param isActive roles to be fetched based on this status.
	 * @return list of roles.
	 */
	List<Role> findByIsActiveOrderByRoleId(Integer isActive);

	/**
	 * Get count of roleName from Roles.
	 * 
	 * @param roleName the roleName whose count is to be fetched.
	 * @return count of roles.
	 */
	Long countByRoleName(String roleName);

	/**
	 * Get count of roleName from Roles.
	 * 
	 * @param roleName the roleName whose count is to be fetched.
	 * @param roleId exclude the records matching this roleId.
	 * @return count of roles.
	 */
	Long countByRoleNameAndRoleIdNot(String roleName, Integer roleId);
}