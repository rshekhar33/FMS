package com.url.app.dto.validation;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.url.app.dto.entity.Role;

@Service
@Validated
public class AppRoleValidationService {

	@Validated(value = ValidationCreateSequence.class)
	public void validateForCreate(@Valid Role role) {
		// Spring bean validation while creating new role
	}

	@Validated(value = { ValidationUpdateSequence.class })
	public void validateForUpdate(@Valid Role role) {
		// Spring bean validation while updating role
	}

	@Validated(value = { ValidationActivateSequence.class })
	public void validateForActivate(@Valid Role role) {
		// Spring bean validation while activating/deactivating role
	}
}