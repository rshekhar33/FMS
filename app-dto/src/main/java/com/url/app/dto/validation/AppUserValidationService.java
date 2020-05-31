package com.url.app.dto.validation;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.url.app.dto.entity.User;

@Service
@Validated
public class AppUserValidationService {

	@Validated(value = ValidationCreateSequence.class)
	public void validateForCreate(@Valid User user) {
		// Spring bean validation while creating new user
	}

	@Validated(value = { ValidationUpdateSequence.class })
	public void validateForUpdate(@Valid User user) {
		// Spring bean validation while updating user
	}

	@Validated(value = { ValidationActivateSequence.class })
	public void validateForActivate(@Valid User user) {
		// Spring bean validation while activating/deactivating user
	}
}