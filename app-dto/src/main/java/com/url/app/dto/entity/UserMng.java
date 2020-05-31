package com.url.app.dto.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * The persistent class for the user database table.
 */
@Entity
public class UserMng implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id", unique = true, nullable = false)
	private Integer userId;

	@Column(name = "email_id", nullable = false, length = 100)
	private String emailId;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "is_active")
	private Integer isActive;

	@Column(name = "mobile_no", length = 20)
	private String mobileNo;

	@Column(name = "user_name", nullable = false, length = 50)
	private String userName;

	@Column(name = "role_name")
	private String roleName;

	public UserMng() {
		super();
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
		if (!(obj instanceof UserMng)) {
			return false;
		}
		UserMng other = (UserMng) obj;

		return Objects.equals(this.getUserId(), other.getUserId());
	}
}