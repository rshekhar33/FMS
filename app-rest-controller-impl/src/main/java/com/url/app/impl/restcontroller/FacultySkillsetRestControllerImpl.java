package com.url.app.impl.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.url.app.dto.FacultySkillsetMng;
import com.url.app.dto.User;
import com.url.app.interf.restcontroller.FacultySkillsetRestController;
import com.url.app.interf.service.AppFacultySkillsetService;

/**
 * Rest Controller for faculty skillset related actions.
 * 
 * @author Shekhar Shinde
 */
@RestController
public class FacultySkillsetRestControllerImpl implements FacultySkillsetRestController {

	@Autowired
	private AppFacultySkillsetService appFacultySkillsetService;

	@Override
	public List<FacultySkillsetMng> fetchDetails() {
		return appFacultySkillsetService.fetchDetailsFacultySkillsets();
	}

	@Override
	public Map<String, Object> fetchData(final User user) {
		return appFacultySkillsetService.fetchDataFacultySkillset(user);
	}

	@Override
	public Map<String, String> validateSave(final Map<String, String> allRequestParams) {
		return appFacultySkillsetService.validateSaveFacultySkillset(allRequestParams);
	}

	@Override
	public Map<String, Object> validateUpdateActivation(final Map<String, String> allRequestParams) {
		return appFacultySkillsetService.validateUpdateActivation(allRequestParams);
	}
}