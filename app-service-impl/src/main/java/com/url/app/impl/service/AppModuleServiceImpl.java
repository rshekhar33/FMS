package com.url.app.impl.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.url.app.config.AppMessage;
import com.url.app.dto.entity.Module;
import com.url.app.dto.validation.AppModuleValidationService;
import com.url.app.interf.dao.ModuleRepository;
import com.url.app.interf.service.AppModuleService;
import com.url.app.interf.service.AppValidationService;
import com.url.app.pojo.AppConcurrentHashMap;
import com.url.app.utility.AppCommon;
import com.url.app.utility.AppConstant;
import com.url.app.utility.AppLogMessage;
import com.url.app.utility.AppResponseKey;

/**
 * Service implementation of application for Module.
 * Method implementation of business logic.
 * 
 * @author Shekhar Shinde
 */
@Service(value = "appModuleServiceImpl")
public class AppModuleServiceImpl implements AppModuleService {
	private static final Logger logger = LoggerFactory.getLogger(AppModuleServiceImpl.class);

	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private AppMessage appMessage;

	@Autowired
	private AppModuleValidationService appModuleValidationService;

	@Autowired
	private AppValidationService appValidationService;

	@Override
	@Transactional(readOnly = true)
	public List<Module> fetchDetailsModules() {
		return moduleRepository.findAllByOrderByModuleId();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Module> fetchActiveDetailsModules() {
		return moduleRepository.findByIsActiveOrderByModuleId(AppConstant.ACTIVE);
	}

	@Override
	@Transactional(readOnly = true)
	public Module fetchDataModule(final Module formModule) {
		return AppCommon.isPositiveInteger(formModule.getModuleId()) ? moduleRepository.getOne(formModule.getModuleId()) : null;
	}

	@Override
	@Transactional
	public Map<String, Object> validateSaveModule(final Module formModule) {
		logger.info(AppLogMessage.MODULE_MSG, formModule);

		String status = AppConstant.BLANK_STRING;
		String msg = AppConstant.BLANK_STRING;

		final Map<String, String> invalidData = appValidationService.validateModule(formModule);
		if (!invalidData.isEmpty()) {
			status = AppConstant.FAIL;
		}

		if (AppCommon.isEmpty(status)) {
			Module module = new Module();
			if (AppCommon.isPositiveInteger(formModule.getModuleId())) {
				module = moduleRepository.getOne(formModule.getModuleId());
			} else {
				module.setIsActive(AppConstant.ACTIVE);
			}
			module.setModuleName(formModule.getModuleName());

			moduleRepository.save(module);
			final Integer moduleId = module.getModuleId();

			if (AppCommon.isPositiveInteger(moduleId)) {
				status = AppConstant.SUCCESS;
				msg = AppCommon.isPositiveInteger(formModule.getModuleId()) ? appMessage.moduleUpdateSuccess : appMessage.moduleAddSuccess;
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
	public Map<String, String> validateUpdateActivation(final Module formModule) {
		appModuleValidationService.validateForActivate(formModule);

		String status = AppConstant.BLANK_STRING;
		String msg = AppConstant.BLANK_STRING;

		final Module module = moduleRepository.getOne(formModule.getModuleId());
		module.setIsActive(formModule.getIsActive());

		moduleRepository.save(module);
		final Integer moduleIdUpdate = module.getModuleId();

		if (AppCommon.isPositiveInteger(moduleIdUpdate)) {
			status = AppConstant.SUCCESS;
			if (AppConstant.ACTIVE.equals(formModule.getIsActive())) {
				msg = appMessage.moduleActiveSuccess;
			} else if (AppConstant.INACTIVE.equals(formModule.getIsActive())) {
				msg = appMessage.moduleInactiveSuccess;
			}
		}

		final Map<String, String> json = new AppConcurrentHashMap<>();
		json.put(AppResponseKey.STATUS, status);
		json.put(AppResponseKey.MSG, msg);

		return json;
	}
}