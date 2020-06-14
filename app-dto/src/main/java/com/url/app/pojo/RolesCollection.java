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
		return "RolesCollection [roleList=" + roleList + ", roleArr=" + Arrays.toString(roleArr) + "]";
	}
}