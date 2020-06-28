package com.url.app.impl.service;

import java.util.Arrays;
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
import com.url.app.dto.entity.FacultySkillset;
import com.url.app.dto.entity.FacultySkillsetMng;
import com.url.app.dto.entity.Module;
import com.url.app.dto.entity.User;
import com.url.app.interf.dao.AppDao;
import com.url.app.interf.dao.ModuleRepository;
import com.url.app.interf.dao.UserRepository;
import com.url.app.interf.service.AppFacultySkillsetService;
import com.url.app.interf.service.AppUserService;
import com.url.app.pojo.AppConcurrentHashMap;
import com.url.app.utility.AppCommon;
import com.url.app.utility.AppConstant;
import com.url.app.utility.AppLogMessage;
import com.url.app.utility.AppResponseKey;

/**
 * Service implementation of application for Faculty Skillset.
 * Method implementation of business logic.
 * 
 * @author Shekhar Shinde
 */
@Service(value = "appFacultySkillsetServiceImpl")
public class AppFacultySkillsetServiceImpl implements AppFacultySkillsetService {
	private static final Logger logger = LoggerFactory.getLogger(AppFacultySkillsetServiceImpl.class);

	@Autowired
	private AppUserService appUserService;

	@Autowired
	private AppDao appDao;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private AppMessage appMessage;

	@Override
	@Transactional
	public List<FacultySkillsetMng> fetchDetailsFacultySkillsets() {
		return appDao.fetchFacultySkillsetsListing();
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> fetchDataFacultySkillset(final User formUser) {
		final Map<String, Object> json = new AppConcurrentHashMap<>();

		if (AppCommon.isPositiveInteger(formUser.getUserId())) {
			final User user = appDao.fetchUserWithModules(formUser.getUserId());
			json.put(AppResponseKey.USER, user);

			final List<Integer> userModuleIds = user.getFacultySkillsets().stream().map(FacultySkillset::getModule).map(Module::getModuleId).collect(Collectors.toList());
			json.put(AppResponseKey.USER_MODULE_IDS, userModuleIds);
		}

		final List<Module> modules = moduleRepository.findByIsActiveOrderByModuleId(AppConstant.ACTIVE);
		json.put(AppResponseKey.MODULES, modules);

		return json;
	}

	@Override
	@Transactional
	public Map<String, Object> validateSaveFacultySkillset(final Map<String, String> allRequestParams) {
		logger.debug(AppLogMessage.ALL_REQUEST_PARAMS_MSG, allRequestParams);
		final String hidUserIdStr = allRequestParams.getOrDefault("hidUserId", "0");
		final String modulesStr = allRequestParams.get("modulesStr");

		final Integer hidUserId = AppCommon.toInteger(hidUserIdStr);

		String status = AppConstant.BLANK_STRING;
		String msg = AppConstant.BLANK_STRING;

		final Map<String, String> invalidData = new AppConcurrentHashMap<>();
		String modulesError = null;

		if (AppCommon.isEmpty(modulesStr)) {
			status = AppConstant.FAIL;
			modulesError = appMessage.mandatoryFieldError;
		}
		invalidData.put(AppResponseKey.MODULES_ERROR, modulesError);

		if (AppCommon.isEmpty(status)) {
			final Integer loggedInUserId = appUserService.getPrincipalUserUserId();

			User user = new User();
			if (AppCommon.isPositiveInteger(hidUserId)) {
				user = userRepository.getOne(hidUserId);
			}
			user.setModifiedBy(loggedInUserId);

			final Set<FacultySkillset> removedFacultySkillsets = new HashSet<>(user.getFacultySkillsets());
			final List<Integer> moduleIdList = Arrays.asList(modulesStr.split(AppConstant.COMMA_STRING)).stream().map(Integer::parseInt).collect(Collectors.toList());
			for (Integer moduleId : moduleIdList) {
				final Module module = new Module();
				module.setModuleId(moduleId);

				final FacultySkillset facultySkillset = new FacultySkillset();
				facultySkillset.setModule(module);
				facultySkillset.setIsActive(AppConstant.ACTIVE);
				facultySkillset.setCreatedBy(loggedInUserId);
				facultySkillset.setModifiedBy(loggedInUserId);

				user.addFacultySkillset(facultySkillset);
				removedFacultySkillsets.remove(facultySkillset);
			}
			for (FacultySkillset facultySkillset : removedFacultySkillsets) {
				user.removeFacultySkillset(facultySkillset);
			}

			userRepository.save(user);
			final Integer userId = user.getUserId();

			if (AppCommon.isPositiveInteger(userId)) {
				status = AppConstant.SUCCESS;
				if (AppCommon.isPositiveInteger(hidUserId)) {
					msg = appMessage.facultyskillsetsUpdateSuccess;
				}
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
	public Map<String, Object> validateUpdateActivation(final Map<String, String> allRequestParams) {
		String status = AppConstant.BLANK_STRING;

		final Map<String, Object> json = new AppConcurrentHashMap<>();
		json.put(AppResponseKey.STATUS, status);

		return json;
	}
}