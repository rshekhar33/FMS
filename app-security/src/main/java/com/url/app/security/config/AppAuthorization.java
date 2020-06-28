package com.url.app.security.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.url.app.dto.entity.Action;
import com.url.app.dto.entity.UrlRolesBean;
import com.url.app.pojo.AppConcurrentHashMap;
import com.url.app.pojo.RolesCollection;
import com.url.app.utility.AppLogMessage;

/**
 * Bean used for storing action and its associated roles in map with action as key and all its associated roles in a list as values.
 * 
 * @author Shekhar Shinde
 */
@Component
public class AppAuthorization {
	private static final Logger logger = LoggerFactory.getLogger(AppAuthorization.class);

	private Map<String, RolesCollection> actionRoles = new AppConcurrentHashMap<>();
	private List<String> applicationAuthSkipUrls = new ArrayList<>();
	private List<String> applicationUrls = new ArrayList<>();

	public Map<String, RolesCollection> getActionRoles() {
		return actionRoles;
	}

	public void setActionRoles(Map<String, RolesCollection> actionRoles) {
		this.actionRoles = actionRoles;
	}

	public List<String> getApplicationAuthSkipUrls() {
		return applicationAuthSkipUrls;
	}

	public void setApplicationAuthSkipUrls(List<String> applicationAuthSkipUrls) {
		this.applicationAuthSkipUrls = applicationAuthSkipUrls;
	}

	public List<String> getApplicationUrls() {
		return applicationUrls;
	}

	public void setApplicationUrls(List<String> applicationUrls) {
		this.applicationUrls = applicationUrls;
	}

	/**
	 * Returns all the roles which has access to the action.
	 * 
	 * @param action the url action whose roles are to be fetched.
	 * @return list of all roles which has access to the provided action.
	 */
	public List<String> getListOfRolesHavingAccessToAction(final String action) {
		final RolesCollection rolesCollection = actionRoles.get(action);
		return rolesCollection != null ? rolesCollection.getRoleList() : null;
	}

	/**
	 * Returns all the roles which has access to the action.
	 * 
	 * @param action the url action whose roles are to be fetched.
	 * @return array of all roles which has access to the provided action.
	 */
	public String[] getArrayOfRolesHavingAccessToAction(final String action) {
		final RolesCollection rolesCollection = actionRoles.get(action);
		return rolesCollection != null ? rolesCollection.getRoleArr() : null;
	}

	/**
	 * Check whether user has access to specified action url or does not have access.
	 * 
	 * @param action the url action whose access to user is to be checked.
	 * @return true if user is authorized to access else returns false.
	 */
	public boolean isAccessAllowed(final String action) {
		final List<String> roles = getListOfRolesHavingAccessToAction(action);

		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final Collection<? extends GrantedAuthority> userRoles = auth.getAuthorities();

		boolean isAccessAllowed = false;
		if (roles != null && !roles.isEmpty()) {
			for (final GrantedAuthority grantedAuthority : userRoles) {
				if (roles.contains(grantedAuthority.getAuthority())) {
					isAccessAllowed = true;
					break;
				}
			}
		}

		return isAccessAllowed;
	}

	/**
	 * Converts flat mapping into key value map with action as key and all its associated roles in a list as values.
	 * 
	 * @param actionRoleList flat mapping of action and roles from database.
	 */
	public void mapUrlToRole(final List<UrlRolesBean> actionRoleList, final List<Action> actions) {
		logger.debug(AppLogMessage.ACTION_ROLE_MAPPING_MSG, actionRoleList);

		applicationAuthSkipUrls.clear();
		applicationUrls.clear();
		actionRoles.clear();

		final Map<Integer, List<String>> actionsSkipMap = actions.stream()
				.collect(Collectors.groupingBy(Action::getIsSkip, Collectors.mapping(Action::getActionPath, Collectors.toList())));
		applicationAuthSkipUrls = actionsSkipMap.getOrDefault(1, new ArrayList<>());
		applicationUrls = actionsSkipMap.getOrDefault(0, new ArrayList<>()).stream().distinct().collect(Collectors.toList());

		for (final UrlRolesBean urlRolesBean : actionRoleList) {
			final String dbUrl = urlRolesBean.getUrl();
			actionRoles.computeIfAbsent(dbUrl, k -> new RolesCollection()).add(String.valueOf(urlRolesBean.getRoleId()));
		}

		actionRoles.forEach((k, v) -> v.setRoleArr(v.getRoleList().stream().toArray(String[]::new)));

		logger.debug(AppLogMessage.SKIPPED_ACTIONS_MSG, applicationAuthSkipUrls);
		logger.debug(AppLogMessage.APPLICATION_ACTIONS_MSG, applicationUrls);
		logger.debug(AppLogMessage.ACTION_ROLES_MSG, actionRoles);
	}
}