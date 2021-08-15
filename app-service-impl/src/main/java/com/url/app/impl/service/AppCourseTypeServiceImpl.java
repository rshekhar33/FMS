package com.url.app.impl.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.url.app.config.AppMessage;
import com.url.app.dto.entity.CourseType;
import com.url.app.dto.validation.AppCourseTypeValidationService;
import com.url.app.interf.dao.AppDao;
import com.url.app.interf.dao.CourseTypeRepository;
import com.url.app.interf.service.AppCourseTypeService;
import com.url.app.pojo.AppConcurrentHashMap;
import com.url.app.utility.AppCommon;
import com.url.app.utility.AppConstant;
import com.url.app.utility.AppLogMessage;
import com.url.app.utility.AppResponseKey;

/**
 * Service implementation of application for Course Type.
 * Method implementation of business logic.
 * 
 * @author Shekhar Shinde
 */
@Service(value = "appCourseTypeServiceImpl")
public class AppCourseTypeServiceImpl implements AppCourseTypeService {
	private static final Logger logger = LoggerFactory.getLogger(AppCourseTypeServiceImpl.class);

	@Autowired
	private AppDao appDao;

	@Autowired
	private CourseTypeRepository courseTypeRepository;

	@Autowired
	private AppMessage appMessage;

	@Autowired
	private AppCourseTypeValidationService appCourseTypeValidationService;

	@Override
	@Transactional(readOnly = true)
	public List<CourseType> fetchDetailsCourseTypes() {
		return courseTypeRepository.findAllByOrderByCourseTypeId();
	}

	@Override
	@Transactional(readOnly = true)
	public CourseType fetchDataCourseType(final CourseType formCourseType) {
		return AppCommon.isPositiveInteger(formCourseType.getCourseTypeId()) ? courseTypeRepository.getById(formCourseType.getCourseTypeId()) : null;
	}

	@Override
	@Transactional
	public Map<String, String> validateSaveCourseType(final CourseType formCourseType) {
		logger.info(AppLogMessage.COURSE_TYPE_MSG, formCourseType);

		if (AppCommon.isPositiveInteger(formCourseType.getCourseTypeId())) {
			appCourseTypeValidationService.validateForUpdate(formCourseType);
		} else {
			appCourseTypeValidationService.validateForCreate(formCourseType);
		}

		String status = AppConstant.BLANK_STRING;
		String msg = AppConstant.BLANK_STRING;

		CourseType courseType = new CourseType();
		if (AppCommon.isPositiveInteger(formCourseType.getCourseTypeId())) {
			courseType = courseTypeRepository.getById(formCourseType.getCourseTypeId());
		} else {
			final String courseTypeCode = AppConstant.COURSE_TYPE_CODE_PREFIX + appDao.generateNewCode(AppConstant.CS_TYPE_COURSE_TYPE_CODE_COUNTER);
			courseType.setCourseTypeCode(courseTypeCode);
			courseType.setIsActive(AppConstant.ACTIVE);
		}
		courseType.setCourseTypeName(formCourseType.getCourseTypeName());
		courseType.setNoOfDays(formCourseType.getNoOfDays());

		courseTypeRepository.save(courseType);
		final Integer courseTypeId = courseType.getCourseTypeId();

		if (AppCommon.isPositiveInteger(courseTypeId)) {
			status = AppConstant.SUCCESS;
			msg = AppCommon.isPositiveInteger(formCourseType.getCourseTypeId()) ? appMessage.coursetypeUpdateSuccess : appMessage.coursetypeAddSuccess;
		}

		final Map<String, String> json = new AppConcurrentHashMap<>();
		json.put(AppResponseKey.STATUS, status);
		json.put(AppResponseKey.MSG, msg);

		return json;
	}

	@Override
	@Transactional
	public Map<String, String> validateUpdateActivation(final CourseType formCourseType) {
		appCourseTypeValidationService.validateForActivate(formCourseType);

		String status = AppConstant.BLANK_STRING;
		String msg = AppConstant.BLANK_STRING;

		final CourseType courseType = courseTypeRepository.getById(formCourseType.getCourseTypeId());
		courseType.setIsActive(formCourseType.getIsActive());

		courseTypeRepository.save(courseType);
		final Integer courseTypeIdUpdate = courseType.getCourseTypeId();

		if (AppCommon.isPositiveInteger(courseTypeIdUpdate)) {
			status = AppConstant.SUCCESS;
			if (AppConstant.ACTIVE.equals(formCourseType.getIsActive())) {
				msg = appMessage.coursetypeActiveSuccess;
			} else if (AppConstant.INACTIVE.equals(formCourseType.getIsActive())) {
				msg = appMessage.coursetypeInactiveSuccess;
			}
		}

		final Map<String, String> json = new AppConcurrentHashMap<>();
		json.put(AppResponseKey.STATUS, status);
		json.put(AppResponseKey.MSG, msg);

		return json;
	}
}