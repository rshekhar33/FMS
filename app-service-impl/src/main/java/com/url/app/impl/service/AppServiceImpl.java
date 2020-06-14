package com.url.app.impl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.url.app.interf.dao.AppDao;
import com.url.app.interf.service.AppService;
import com.url.app.security.config.AppAuthorization;

@Service(value = "appServiceImpl")
public class AppServiceImpl implements AppService {

	@Autowired
	private AppDao appDao;

	@Autowired
	private AppAuthorization appAuthorization;

	@Override
	@Transactional(readOnly = true)
	public void setUrlRoles() {
		appAuthorization.mapUrlToRole(appDao.fetchUrlRoleIds(), appDao.fetchActions());
	}
}