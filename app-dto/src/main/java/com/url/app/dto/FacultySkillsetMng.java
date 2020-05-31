package com.url.app.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * The persistent class for the faculty skillset database table.
 */
@Entity
public class FacultySkillsetMng implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id", unique = true, nullable = false)
	private Integer userId;

	@Column(name = "is_active")
	private Integer isActive;

	@Column(name = "module_name")
	private String moduleName;

	public FacultySkillsetMng() {
		super();
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
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
		if (!(obj instanceof FacultySkillsetMng)) {
			return false;
		}
		FacultySkillsetMng other = (FacultySkillsetMng) obj;

		return Objects.equals(this.getUserId(), other.getUserId());
	}
}