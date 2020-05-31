package com.url.app.interf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.url.app.dto.Module;

@Repository(value = "moduleRepository")
public interface ModuleRepository extends JpaRepository<Module, Integer> {

	/**
	 * Get all modules data.
	 * 
	 * @return list of modules.
	 */
	List<Module> findAllByOrderByModuleId();

	/**
	 * Get all modules data.
	 * 
	 * @param isActive modules to be fetched based on this status.
	 * @return list of modules.
	 */
	List<Module> findByIsActiveOrderByModuleId(Integer isActive);

	/**
	 * Get count of moduleName from Modules.
	 * 
	 * @param moduleName the moduleName whose count is to be fetched.
	 * @return count of modules.
	 */
	Long countByModuleName(String moduleName);

	/**
	 * Get count of moduleName from Modules.
	 * 
	 * @param moduleName the moduleName whose count is to be fetched.
	 * @param moduleId exclude the records matching this moduleId.
	 * @return count of modules.
	 */
	Long countByModuleNameAndModuleIdNot(String moduleName, Integer moduleId);
}