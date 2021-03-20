package com.url.app.dto.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * The persistent class for the user skillset database table.
 */
@Entity
public class UserSkillsetMng implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id", unique = true, nullable = false)
	private Integer userId;

	@Column(name = "us_is_active")
	private Integer usIsActive;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "module_names")
	private String moduleNames;

	@Column(name = "user_is_active")
	private Integer userIsActive;

	@Column(name = "user_name", nullable = false, length = 50)
	private String userName;

	public UserSkillsetMng() {
		super();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUsIsActive() {
		return usIsActive;
	}

	public void setUsIsActive(Integer usIsActive) {
		this.usIsActive = usIsActive;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getModuleNames() {
		return moduleNames;
	}

	public void setModuleNames(String moduleNames) {
		this.moduleNames = moduleNames;
	}

	public Integer getUserIsActive() {
		return userIsActive;
	}

	public void setUserIsActive(Integer userIsActive) {
		this.userIsActive = userIsActive;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof UserSkillsetMng)) {
			return false;
		}
		UserSkillsetMng other = (UserSkillsetMng) obj;

		return Objects.equals(this.getUserId(), other.getUserId());
	}
}