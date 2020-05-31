package com.url.app.interf.service;

import java.util.List;
import java.util.Map;

import com.url.app.dto.Module;

/**
 * Service Layer of application for Module.
 * Method declaration of business logic.
 * 
 * @author Shekhar Shinde
 */
public interface AppModuleService {

	/**
	 * Fetch all module details.
	 * 
	 * @return modules details in json format.
	 */
	List<Module> fetchDetailsModules();

	/**
	 * Fetch module data on add module screen.
	 * 
	 * @param module contains the moduleId of module.
	 * @return module data in json format.
	 */
	Module fetchDataModule(Module module);

	/**
	 * Validates add/update module data.
	 * If data is valid then it adds or updates the module,
	 * if data is invalid then proper error messages are returned in JSON format.
	 * 
	 * @param module all the parameters of add/update screen.
	 * @return status as success if data is valid or else all the validation messages with status as failure in JSON.
	 */
	Map<String, String> validateSaveModule(Module module);

	/**
	 * Validates module activation data.
	 * If data is valid then it updates the module's isActive flag,
	 * if data is invalid then proper error messages are returned in JSON format.
	 * 
	 * @param module all the parameters of activation.
	 * @return status as success if data is valid or else all the validation messages with status as failure in JSON.
	 */
	Map<String, String> validateUpdateActivation(Module module);
}