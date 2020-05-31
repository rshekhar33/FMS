package com.url.app.dto;

/**
 * @author Shekhar Shinde
 */
public class UrlRolesBean {
	private String url;
	private Integer roleId;

	public UrlRolesBean() {
		super();
	}

	public UrlRolesBean(String url, Integer roleId) {
		super();
		this.url = url;
		this.roleId = roleId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UrlRolesBean [url=").append(url).append(", roleId=").append(roleId).append("]");
		return builder.toString();
	}
}