package com.url.app.pojo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.ConfigAttribute;

/**
 * @author Shekhar Shinde
 */
public class RolesCollection {
	private List<String> roleList = new ArrayList<>();
	private List<ConfigAttribute> configAttributes;

	public RolesCollection() {
		super();
	}

	public List<String> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}

	public List<ConfigAttribute> getConfigAttributes() {
		return configAttributes;
	}

	public void setConfigAttributes(List<ConfigAttribute> configAttributes) {
		this.configAttributes = configAttributes;
	}

	public boolean add(String roleId) {
		return roleList.add(roleId);
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("RolesCollection [roleList=").append(roleList != null ? roleList.subList(0, Math.min(roleList.size(), maxLen)) : null).append("]");
		return builder.toString();
	}
}