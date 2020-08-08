package com.url.app.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Shekhar Shinde
 */
public class RolesCollection {
	private List<String> roleList = new ArrayList<>();
	private String[] roleArr = new String[0];

	public RolesCollection() {
		super();
	}

	public List<String> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}

	public String[] getRoleArr() {
		return roleArr;
	}

	public void setRoleArr(String[] roleArr) {
		this.roleArr = roleArr;
	}

	public <T> T[] toArray(T[] a) {
		return roleList.toArray(a);
	}

	public boolean add(String e) {
		return roleList.add(e);
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("RolesCollection [roleList=").append(roleList != null ? roleList.subList(0, Math.min(roleList.size(), maxLen)) : null).append(", roleArr=")
				.append(roleArr != null ? Arrays.asList(roleArr).subList(0, Math.min(roleArr.length, maxLen)) : null).append("]");
		return builder.toString();
	}
}