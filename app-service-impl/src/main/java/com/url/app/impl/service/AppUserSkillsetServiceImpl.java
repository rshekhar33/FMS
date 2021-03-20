package com.url.app.impl.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.url.app.config.AppMessage;
import com.url.app.dto.entity.Module;
import com.url.app.dto.entity.User;
import com.url.app.dto.entity.UserSkillset;
import com.url.app.dto.entity.UserSkillsetMng;
import com.url.app.interf.dao.AppDao;
import com.url.app.interf.dao.UserRepository;
import com.url.app.interf.service.AppUserSkillsetService;
import com.url.app.pojo.AppConcurrentHashMap;
import com.url.app.security.service.AppPrincipalUser;
import com.url.app.utility.AppCommon;
import com.url.app.utility.AppConstant;
import com.url.app.utility.AppLogMessage;
import com.url.app.utility.AppResponseKey;

/**
 * Service implementation of application for User Skillset.
 * Method implementation of business logic.
 * 
 * @author Shekhar Shinde
 */
@Service(value = "appUserSkillsetServiceImpl")
public class AppUserSkillsetServiceImpl implements AppUserSkillsetService {
	private static final Logger logger = LoggerFactory.getLogger(AppUserSkillsetServiceImpl.class);

	@Autowired
	private AppDao appDao;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AppMessage appMessage;

	@Autowired
	private AppPrincipalUser appPrincipalUser;

	@Override
	@Transactional
	public List<UserSkillsetMng> fetchDetailsUserSkillsets() {
		return appDao.fetchUserSkillsetsListing();
	}

	@Override
	@Transactional(readOnly = true)
	public User fetchDataUserSkillset(final User formUser) {
		User user = null;

		if (AppCommon.isPositiveInteger(formUser.getUserId())) {
			user = appDao.fetchUserWithModules(formUser.getUserId());

			user.setModules(user.getUserSkillsets().stream().map(UserSkillset::getModule).map(Module::getModuleId).collect(Collectors.toList()));
		}

		return user;
	}

	@Override
	@Transactional
	public Map<String, Object> validateSaveUserSkillset(final User formUser) {
		logger.debug(AppLogMessage.USER_MSG, formUser);

		String status = AppConstant.BLANK_STRING;
		String msg = AppConstant.BLANK_STRING;

		User user = new User();
		if (AppCommon.isPositiveInteger(formUser.getUserId())) {
			user = appDao.fetchUserWithModules(formUser.getUserId());
		}

		final Set<UserSkillset> removedUserSkillsets = new HashSet<>(user.getUserSkillsets());
		if (!AppCommon.isEmpty(formUser.getModules())) {
			for (final Integer moduleId : formUser.getModules()) {
				final Module module = new Module();
				module.setModuleId(moduleId);

				final UserSkillset userSkillset = new UserSkillset();
				userSkillset.setModule(module);
				userSkillset.setIsActive(AppConstant.ACTIVE);

				user.addUserSkillset(userSkillset);
				removedUserSkillsets.remove(userSkillset);
			}
		}
		for (UserSkillset userSkillset : removedUserSkillsets) {
			user.removeUserSkillset(userSkillset);
		}

		userRepository.save(user);
		final Integer userId = user.getUserId();

		if (AppCommon.isPositiveInteger(userId)) {
			status = AppConstant.SUCCESS;
			if (AppCommon.isPositiveInteger(formUser.getUserId())) {
				msg = appMessage.userskillsetsUpdateSuccess;
			}
		}

		final Map<String, Object> json = new AppConcurrentHashMap<>();
		json.put(AppResponseKey.STATUS, status);
		json.put(AppResponseKey.MSG, msg);

		return json;
	}

	@Override
	@Transactional
	public Map<String, String> validateUpdateActivation(final User formUser) {
		String status = AppConstant.BLANK_STRING;
		String msg = AppConstant.BLANK_STRING;

		formUser.setModifiedBy(appPrincipalUser.getPrincipalUserUserId());

		final int updateCnt = appDao.userSkillsetsUpdateIsActive(formUser);
		if (AppCommon.isPositiveInteger(updateCnt)) {
			status = AppConstant.SUCCESS;
			if (AppConstant.ACTIVE.equals(formUser.getIsActive())) {
				msg = appMessage.userskillsetsActiveSuccess;
			} else if (AppConstant.INACTIVE.equals(formUser.getIsActive())) {
				msg = appMessage.userskillsetsInactiveSuccess;
			}
		}

		final Map<String, String> json = new AppConcurrentHashMap<>();
		json.put(AppResponseKey.STATUS, status);
		json.put(AppResponseKey.MSG, msg);

		return json;
	}
}