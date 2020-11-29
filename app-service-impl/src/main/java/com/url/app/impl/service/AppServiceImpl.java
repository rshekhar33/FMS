package com.url.app.impl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.url.app.config.AppMessage;
import com.url.app.interf.dao.AppDao;
import com.url.app.interf.service.AppService;
import com.url.app.pojo.AppExceptionInfo;
import com.url.app.security.config.AppAuthorization;
import com.url.app.utility.AppConstant;

@Service(value = "appServiceImpl")
public class AppServiceImpl implements AppService {

	@Autowired
	private AppDao appDao;

	@Autowired
	private AppAuthorization appAuthorization;

	@Autowired
	private AppMessage appMessage;

	@Override
	@Transactional(readOnly = true)
	public void setUrlRoles() {
		appAuthorization.mapUrlToRole(appDao.fetchUrlRoleIds(), appDao.fetchActions());
	}

	@Override
	public AppExceptionInfo getAppExceptionInfo(Object appExceptionInfoObj) {
		AppExceptionInfo appExceptionInfo = null;
		if (appExceptionInfoObj instanceof AppExceptionInfo) {
			appExceptionInfo = (AppExceptionInfo) appExceptionInfoObj;
		} else {
			appExceptionInfo = new AppExceptionInfo();
			appExceptionInfo.setStatus(AppConstant.FAIL);
			appExceptionInfo.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			appExceptionInfo.setExceptionHeader(appMessage.exceptionHeader2);
			appExceptionInfo.setExceptionDesc(appMessage.exceptionDesc3);
		}
		
		return appExceptionInfo;
	}
}