package com.url.app.dto.validation;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.url.app.dto.entity.CourseType;

@Service
@Validated
public class AppCourseTypeValidationService {

	@Validated(value = ValidationCreateSequence.class)
	public void validateForCreate(@Valid CourseType courseType) {
		// Spring bean validation while creating new courseType
	}

	@Validated(value = ValidationUpdateSequence.class)
	public void validateForUpdate(@Valid CourseType courseType) {
		// Spring bean validation while updating courseType
	}

	@Validated(value = ValidationActivateSequence.class)
	public void validateForActivate(@Valid CourseType courseType) {
		// Spring bean validation while activating/deactivating courseType
	}
}