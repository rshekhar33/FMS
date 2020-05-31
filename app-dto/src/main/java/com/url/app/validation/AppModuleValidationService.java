package com.url.app.validation;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.url.app.dto.Module;

@Service
@Validated
public class AppModuleValidationService {

	@Validated(value = ValidationCreateSequence.class)
	public void validateForCreate(@Valid Module module) {
		// Spring bean validation while creating new module
	}

	@Validated(value = { ValidationUpdateSequence.class })
	public void validateForUpdate(@Valid Module module) {
		// Spring bean validation while updating module
	}

	@Validated(value = { ValidationActivateSequence.class })
	public void validateForActivate(@Valid Module module) {
		// Spring bean validation while activating/deactivating module
	}
}