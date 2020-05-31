package com.url.app.impl.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.url.app.dto.entity.Module;
import com.url.app.interf.restcontroller.ModuleRestController;
import com.url.app.interf.service.AppModuleService;

/**
 * Module Controller for module related actions.
 * 
 * @author Shekhar Shinde
 */
@RestController
public class ModuleRestControllerImpl implements ModuleRestController {

	@Autowired
	private AppModuleService appModuleService;

	@Override
	public List<Module> fetchDetails() {
		return appModuleService.fetchDetailsModules();
	}

	@Override
	public Module fetchData(final Module module) {
		return appModuleService.fetchDataModule(module);
	}

	@Override
	public Map<String, String> validateSave(final Module module) {
		return appModuleService.validateSaveModule(module);
	}

	@Override
	public Map<String, String> validateUpdateActivation(final Module module) {
		return appModuleService.validateUpdateActivation(module);
	}
}