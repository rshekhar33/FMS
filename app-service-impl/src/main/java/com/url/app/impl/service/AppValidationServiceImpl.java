package com.url.app.impl.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.url.app.config.AppDBValidationMessage;
import com.url.app.dto.entity.Module;
import com.url.app.dto.entity.Role;
import com.url.app.dto.entity.User;
import com.url.app.dto.validation.AppModuleValidationService;
import com.url.app.dto.validation.AppRoleValidationService;
import com.url.app.dto.validation.AppUserValidationService;
import com.url.app.interf.dao.ModuleRepository;
import com.url.app.interf.dao.RoleRepository;
import com.url.app.interf.dao.UserRepository;
import com.url.app.interf.service.AppValidationService;
import com.url.app.pojo.AppConcurrentHashMap;
import com.url.app.utility.AppCommon;
import com.url.app.utility.AppResponseKey;

/**
 * Service implementation of application for invoking validations.
 * Method implementation of business logic.
 * 
 * @author Shekhar Shinde
 */
@Service(value = "appValidationServiceImpl")
public class AppValidationServiceImpl implements AppValidationService {

	@Autowired
	private AppDBValidationMessage appDBValidationMessage;

	@Autowired
	private AppModuleValidationService appModuleValidationService;

	@Autowired
	private AppRoleValidationService appRoleValidationService;

	@Autowired
	private AppUserValidationService appUserValidationService;

	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Map<String, String> validateModule(final Module module) {
		final Map<String, String> invalidData = new AppConcurrentHashMap<>();
		String moduleNameError = null;

		if (AppCommon.isPositiveInteger(module.getModuleId())) {
			appModuleValidationService.validateForUpdate(module);

			final Long moduleNameCount = moduleRepository.countByModuleNameAndModuleIdNot(module.getModuleName(), module.getModuleId());
			if (moduleNameCount > 0) {
				moduleNameError = appDBValidationMessage.moduleModulenameExistsError;
			}
		} else {
			appModuleValidationService.validateForCreate(module);

			final Long moduleNameCount = moduleRepository.countByModuleName(module.getModuleName());
			if (moduleNameCount > 0) {
				moduleNameError = appDBValidationMessage.moduleModulenameExistsError;
			}
		}
		invalidData.put(AppResponseKey.MODULE_NAME, moduleNameError);

		return invalidData;
	}

	@Override
	public Map<String, String> validateRole(Role role) {
		final Map<String, String> invalidData = new AppConcurrentHashMap<>();
		String roleNameError = null;

		if (AppCommon.isPositiveInteger(role.getRoleId())) {
			appRoleValidationService.validateForUpdate(role);

			final Long roleNameCount = roleRepository.countByRoleNameAndRoleIdNot(role.getRoleName(), role.getRoleId());
			if (roleNameCount > 0) {
				roleNameError = appDBValidationMessage.roleRolenameExistsError;
			}
		} else {
			appRoleValidationService.validateForCreate(role);

			final Long roleNameCount = roleRepository.countByRoleName(role.getRoleName());
			if (roleNameCount > 0) {
				roleNameError = appDBValidationMessage.roleRolenameExistsError;
			}
		}
		invalidData.put(AppResponseKey.ROLE_NAME, roleNameError);

		return invalidData;
	}

	@Override
	public Map<String, String> validateUser(User user) {
		final Map<String, String> invalidData = new AppConcurrentHashMap<>();
		String userNameError = null;
		String emailIdError = null;

		if (AppCommon.isPositiveInteger(user.getUserId())) {
			appUserValidationService.validateForUpdate(user);

			final Long emailIdCount = userRepository.countByEmailIdAndUserIdNot(user.getEmailId(), user.getUserId());
			if (emailIdCount > 0) {
				emailIdError = appDBValidationMessage.userEmailExistsError;
			}
		} else {
			appUserValidationService.validateForCreate(user);

			final Long userNameCount = userRepository.countByUserName(user.getUserName());
			if (userNameCount > 0) {
				userNameError = appDBValidationMessage.userUsernameExistsError;
			}
			final Long emailIdCount = userRepository.countByEmailId(user.getEmailId());
			if (emailIdCount > 0) {
				emailIdError = appDBValidationMessage.userEmailExistsError;
			}
		}
		invalidData.put(AppResponseKey.USER_NAME, userNameError);
		invalidData.put(AppResponseKey.EMAIL_ID, emailIdError);

		return invalidData;
	}
}