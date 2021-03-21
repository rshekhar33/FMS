package com.url.app.impl.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.url.app.config.AppMessage;
import com.url.app.dto.entity.Role;
import com.url.app.dto.entity.User;
import com.url.app.dto.entity.UserMng;
import com.url.app.dto.entity.UserRoleRelation;
import com.url.app.dto.validation.AppUserValidationService;
import com.url.app.interf.dao.AppDao;
import com.url.app.interf.dao.UserRepository;
import com.url.app.interf.service.AppUserService;
import com.url.app.interf.service.AppValidationService;
import com.url.app.pojo.AppConcurrentHashMap;
import com.url.app.security.service.AppPrincipalUser;
import com.url.app.utility.AppCommon;
import com.url.app.utility.AppConstant;
import com.url.app.utility.AppLogMessage;
import com.url.app.utility.AppResponseKey;

/**
 * Service implementation of application for User.
 * Method implementation of business logic.
 * 
 * @author Shekhar Shinde
 */
@Service(value = "appUserServiceImpl")
public class AppUserServiceImpl implements AppUserService {
	private static final Logger logger = LoggerFactory.getLogger(AppUserServiceImpl.class);

	@Autowired
	private AppDao appDao;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AppMessage appMessage;

	@Autowired
	private AppPrincipalUser appPrincipalUser;

	@Autowired
	private AppUserValidationService appUserValidationService;

	@Autowired
	private AppValidationService appValidationService;

	@Override
	@Transactional(readOnly = true)
	public User fetchValidUser(final String userName) {
		return appDao.fetchUser(userName);
	}

	@Override
	@Transactional
	public void userUpdateLastLoginSuccess() {
		appDao.userUpdateLastLoginSuccess(appPrincipalUser.getPrincipalUserUserId());
	}

	@Override
	@Transactional
	public void userUpdateLastLoginFailure(final String userName) {
		appDao.userUpdateLastLoginFailure(userName);
	}

	@Override
	@Transactional
	public List<UserMng> fetchDetailsUsers() {
		return appDao.fetchUsersListing();
	}

	@Override
	@Transactional(readOnly = true)
	public User fetchDataUser(final User formUser) {
		User user = null;

		if (AppCommon.isPositiveInteger(formUser.getUserId())) {
			user = appDao.fetchUserWithRoles(formUser.getUserId());

			user.setRoles(user.getUserRoleRelations().stream().map(UserRoleRelation::getRole).map(Role::getRoleId).collect(Collectors.toList()));
		}

		return user;
	}

	@Override
	@Transactional
	public Map<String, Object> validateSaveUser(final User formUser) {
		logger.info(AppLogMessage.USER_MSG, formUser);

		String status = AppConstant.BLANK_STRING;
		String msg = AppConstant.BLANK_STRING;

		final Map<String, String> invalidData = appValidationService.validateUser(formUser);
		if (!invalidData.isEmpty()) {
			status = AppConstant.FAIL;
		}

		if (AppCommon.isEmpty(status)) {
			User user = new User();
			if (AppCommon.isPositiveInteger(formUser.getUserId())) {
				user = appDao.fetchUserWithRoles(formUser.getUserId());
			} else {
				user.setUserName(formUser.getUserName());
				user.setPassword(passwordEncoder.encode(AppConstant.USER_DEFAULT_PASS));
				user.setIsActive(AppConstant.ACTIVE);
			}
			user.setFirstName(formUser.getFirstName());
			user.setMiddleName(formUser.getMiddleName());
			user.setLastName(formUser.getLastName());
			user.setEmailId(formUser.getEmailId());
			user.setMobileNo(formUser.getMobileNo());

			final Set<UserRoleRelation> removedUserRoleRelations = new HashSet<>(user.getUserRoleRelations());
			for (final Integer roleId : formUser.getRoles()) {
				final Role role = new Role();
				role.setRoleId(roleId);

				final UserRoleRelation userRoleRelation = new UserRoleRelation();
				userRoleRelation.setRole(role);
				userRoleRelation.setIsActive(AppConstant.ACTIVE);

				user.addUserRoleRelation(userRoleRelation);
				removedUserRoleRelations.remove(userRoleRelation);
			}
			for (final UserRoleRelation userRoleRelation : removedUserRoleRelations) {
				user.removeUserRoleRelation(userRoleRelation);
			}

			userRepository.save(user);
			final Integer userId = user.getUserId();

			if (AppCommon.isPositiveInteger(userId)) {
				status = AppConstant.SUCCESS;
				msg = AppCommon.isPositiveInteger(formUser.getUserId()) ? appMessage.userUpdateSuccess : appMessage.userAddSuccess;
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
	public Map<String, String> validateUpdateActivation(final User formUser) {
		appUserValidationService.validateForActivate(formUser);

		String status = AppConstant.BLANK_STRING;
		String msg = AppConstant.BLANK_STRING;

		final User user = userRepository.getOne(formUser.getUserId());
		user.setIsActive(formUser.getIsActive());

		userRepository.save(user);
		final Integer userIdUpdate = user.getUserId();

		if (AppCommon.isPositiveInteger(userIdUpdate)) {
			status = AppConstant.SUCCESS;
			if (AppConstant.ACTIVE.equals(formUser.getIsActive())) {
				msg = appMessage.userActiveSuccess;
			} else if (AppConstant.INACTIVE.equals(formUser.getIsActive())) {
				msg = appMessage.userInactiveSuccess;
			}
		}

		final Map<String, String> json = new AppConcurrentHashMap<>();
		json.put(AppResponseKey.STATUS, status);
		json.put(AppResponseKey.MSG, msg);

		return json;
	}
}