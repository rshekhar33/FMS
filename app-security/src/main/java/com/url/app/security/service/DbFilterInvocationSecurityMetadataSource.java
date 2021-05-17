package com.url.app.security.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Component;

import com.url.app.interf.service.AppService;
import com.url.app.security.config.AppAuthorization;
import com.url.app.utility.AppLogMessage;

/**
 * Spring Security filter MetadataSource.
 * 
 * @author Shekhar Shinde
 */
@Component
public class DbFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource, InitializingBean {
	private static final Logger logger = LoggerFactory.getLogger(DbFilterInvocationSecurityMetadataSource.class);

	@Autowired
	private AppService appService;

	@Autowired
	private AppAuthorization appAuthorization;

	private static final List<ConfigAttribute> CONFIG_ATTRIBUTES_DENIED = SecurityConfig.createList("DENIED");

	@Override
	public Collection<ConfigAttribute> getAttributes(final Object object) {
		final FilterInvocation fi = (FilterInvocation) object;
		final HttpServletRequest request = fi.getHttpRequest();

		final String url = (request instanceof SecurityContextHolderAwareRequestWrapper) ? request.getRequestURI().substring(request.getContextPath().length() + 1)
				: fi.getRequestUrl();

		List<ConfigAttribute> configAttributes = appAuthorization.getConfigAttributesOfRolesHavingAccessToAction(url);
		logger.debug(AppLogMessage.REQUEST_URL_AND_ROLES_MSG, url, configAttributes);

		if (configAttributes == null) {
			final List<String> applicationAuthSkipUrls = appAuthorization.getApplicationAuthSkipUrls();
			final List<String> applicationUrls = appAuthorization.getApplicationUrls();
			if (url != null && !applicationAuthSkipUrls.contains(url) && applicationUrls.contains(url)) {
				configAttributes = CONFIG_ATTRIBUTES_DENIED;
			}
		}

		return configAttributes;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return Collections.emptySet();
	}

	@Override
	public boolean supports(final Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		appService.setUrlRoles();
	}
}