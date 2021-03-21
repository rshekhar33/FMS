package com.url.app.impl.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.url.app.config.AppMessage;
import com.url.app.dto.entity.Role;
import com.url.app.dto.validation.AppRoleValidationService;
import com.url.app.interf.dao.RoleRepository;
import com.url.app.interf.service.AppRoleService;
import com.url.app.interf.service.AppValidationService;
import com.url.app.pojo.AppConcurrentHashMap;
import com.url.app.utility.AppCommon;
import com.url.app.utility.AppConstant;
import com.url.app.utility.AppLogMessage;
import com.url.app.utility.AppResponseKey;

/**
 * Service implementation of application for Role.
 * Method implementation of business logic.
 * 
 * @author Shekhar Shinde
 */
@Service(value = "appRoleServiceImpl")
public class AppRoleServiceImpl implements AppRoleService {
	private static final Logger logger = LoggerFactory.getLogger(AppRoleServiceImpl.class);

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AppMessage appMessage;

	@Autowired
	private AppRoleValidationService appRoleValidationService;

	@Autowired
	private AppValidationService appValidationService;

	@Override
	@Transactional(readOnly = true)
	public List<Role> fetchDetailsRoles() {
		return roleRepository.findAllByOrderByRoleId();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Role> fetchActiveDetailsRoles() {
		return roleRepository.findByIsActiveOrderByRoleId(AppConstant.ACTIVE);
	}

	@Override
	@Transactional(readOnly = true)
	public Role fetchDataRole(final Role formRole) {
		return AppCommon.isPositiveInteger(formRole.getRoleId()) ? roleRepository.getOne(formRole.getRoleId()) : null;
	}

	@Override
	@Transactional
	public Map<String, Object> validateSaveRole(final Role formRole) {
		logger.info(AppLogMessage.ROLE_MSG, formRole);

		String status = AppConstant.BLANK_STRING;
		String msg = AppConstant.BLANK_STRING;

		final Map<String, String> invalidData = appValidationService.validateRole(formRole);
		if (!invalidData.isEmpty()) {
			status = AppConstant.FAIL;
		}

		if (AppCommon.isEmpty(status)) {
			Role role = new Role();
			if (AppCommon.isPositiveInteger(formRole.getRoleId())) {
				role = roleRepository.getOne(formRole.getRoleId());
			} else {
				role.setIsActive(AppConstant.ACTIVE);
			}
			role.setRoleName(formRole.getRoleName());

			roleRepository.save(role);
			final Integer roleId = role.getRoleId();

			if (AppCommon.isPositiveInteger(roleId)) {
				status = AppConstant.SUCCESS;
				msg = AppCommon.isPositiveInteger(formRole.getRoleId()) ? appMessage.roleUpdateSuccess : appMessage.roleAddSuccess;
			}
		}

		final Map<String, Object> json = new AppConcurrentHashMap<>();
		json.put(AppResponseKey.STATUS, status);
		json.put(AppResponseKey.MSG, msg);
		json.put(AppResponseKey.INVALID_DATA, invalidData.isEmpty() ? null : invalidData);

		return json;
	}

	@Override
	@Transactional
	public Map<String, String> validateUpdateActivation(final Role formRole) {
		appRoleValidationService.validateForActivate(formRole);

		String status = AppConstant.BLANK_STRING;
		String msg = AppConstant.BLANK_STRING;

		final Role role = roleRepository.getOne(formRole.getRoleId());
		role.setIsActive(formRole.getIsActive());

		roleRepository.save(role);
		final Integer roleIdUpdate = role.getRoleId();

		if (AppCommon.isPositiveInteger(roleIdUpdate)) {
			status = AppConstant.SUCCESS;
			if (AppConstant.ACTIVE.equals(formRole.getIsActive())) {
				msg = appMessage.roleActiveSuccess;
			} else if (AppConstant.INACTIVE.equals(formRole.getIsActive())) {
				msg = appMessage.roleInactiveSuccess;
			}
		}

		final Map<String, String> json = new AppConcurrentHashMap<>();
		json.put(AppResponseKey.STATUS, status);
		json.put(AppResponseKey.MSG, msg);

		return json;
	}
}