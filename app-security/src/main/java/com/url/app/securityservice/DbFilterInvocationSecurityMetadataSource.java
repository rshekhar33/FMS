package com.url.app.securityservice;

import java.util.Collection;
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

	private static final Collection<ConfigAttribute> CONFIG_ATTRIBUTES_DENIED = SecurityConfig.createList("DENIED");

	@Override
	public Collection<ConfigAttribute> getAttributes(final Object object) throws IllegalArgumentException {
		final FilterInvocation fi = (FilterInvocation) object;
		String url = fi.getRequestUrl();
		logger.debug(AppLogMessage.FILTER_INVOCATION_URL_MSG, url);

		final HttpServletRequest request = fi.getHttpRequest();

		if (request instanceof SecurityContextHolderAwareRequestWrapper) {
			url = request.getRequestURI().substring(request.getContextPath().length() + 1);
		}

		final List<String> roles = appAuthorization.getRolesHavingAccessToAction(url);
		logger.debug(AppLogMessage.REQUEST_URL_AND_ROLES_MSG, url, roles);

		Collection<ConfigAttribute> attributes = null;
		if (roles == null) {
			final List<String> applicationAuthSkipUrls = appAuthorization.getApplicationAuthSkipUrls();
			final List<String> applicationUrls = appAuthorization.getApplicationUrls();
			if (url != null && !applicationAuthSkipUrls.contains(url) && applicationUrls.contains(url)) {
				attributes = CONFIG_ATTRIBUTES_DENIED;
			}
		} else {
			attributes = SecurityConfig.createList(roles.stream().toArray(String[]::new));
		}

		return attributes;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
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