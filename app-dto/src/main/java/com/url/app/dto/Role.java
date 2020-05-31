package com.url.app.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.url.app.utility.AppBasicValidationKey;
import com.url.app.utility.AppConstant;
import com.url.app.utility.AppSQL;
import com.url.app.validation.BasicActivateGroup;
import com.url.app.validation.BasicCreateGroup;
import com.url.app.validation.BasicUpdateGroup;

/**
 * The persistent class for the role database table.
 */
@Entity
@Table(name = "role")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NamedQuery(name = "Role.findAll", query = AppSQL.QRY_FIND_ALL_ROLE)
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id", unique = true, nullable = false)
	@NotNull(groups = BasicActivateGroup.class, message = AppBasicValidationKey.UPDATE_FAILED_ERROR)
	@Positive(groups = BasicActivateGroup.class, message = AppBasicValidationKey.UPDATE_FAILED_ERROR)
	private Integer roleId;

	@Column(name = "created_by", updatable = false, nullable = false)
	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", updatable = false, nullable = false)
	@CreatedDate
	private Date createdDate;

	@Column(name = "is_active", nullable = false)
	@NotNull(groups = BasicActivateGroup.class, message = AppBasicValidationKey.UPDATE_FAILED_ERROR)
	@Min(groups = BasicActivateGroup.class, value = 0, message = AppBasicValidationKey.UPDATE_FAILED_ERROR)
	@Max(groups = BasicActivateGroup.class, value = 1, message = AppBasicValidationKey.UPDATE_FAILED_ERROR)
	private Integer isActive;

	@Column(name = "modified_by", nullable = false)
	private Integer modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date", nullable = false)
	@LastModifiedDate
	private Date modifiedDate;

	@Column(name = "role_name", unique = true, nullable = false, length = 50)
	@NotBlank(groups = { BasicCreateGroup.class, BasicUpdateGroup.class }, message = AppBasicValidationKey.MANDATORY_FIELD_ERROR)
	@Pattern(groups = { BasicCreateGroup.class,
			BasicUpdateGroup.class }, regexp = AppConstant.REGEX_RESTRICTED_CHAR_3, message = AppBasicValidationKey.ROLE_ROLENAME_RESTRICTEDCHAR3_ERROR)
	@Size(groups = { BasicCreateGroup.class, BasicUpdateGroup.class }, max = 50, message = AppBasicValidationKey.LENGTH_ERROR)
	private String roleName;

	//bi-directional many-to-one association to RolePrivilegeRelation
	@OneToMany(mappedBy = "id.role", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference(value = "role_rolePrivilegeRelation")
	private Set<RolePrivilegeRelation> rolePrivilegeRelations = new HashSet<>(0);

	//bi-directional many-to-one association to UserRoleRelation
	@OneToMany(mappedBy = "id.role", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference(value = "role_userRoleRelation")
	private Set<UserRoleRelation> userRoleRelations = new HashSet<>(0);

	public Role() {
		super();
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<RolePrivilegeRelation> getRolePrivilegeRelations() {
		return this.rolePrivilegeRelations;
	}

	public void setRolePrivilegeRelations(Set<RolePrivilegeRelation> rolePrivilegeRelations) {
		this.rolePrivilegeRelations = rolePrivilegeRelations;
	}

	public boolean addRolePrivilegeRelation(RolePrivilegeRelation rolePrivilegeRelation) {
		rolePrivilegeRelation.setRole(this);

		return getRolePrivilegeRelations().add(rolePrivilegeRelation);
	}

	public boolean removeRolePrivilegeRelation(RolePrivilegeRelation rolePrivilegeRelation) {
		return getRolePrivilegeRelations().remove(rolePrivilegeRelation);
	}

	public Set<UserRoleRelation> getUserRoleRelations() {
		return this.userRoleRelations;
	}

	public void setUserRoleRelations(Set<UserRoleRelation> userRoleRelations) {
		this.userRoleRelations = userRoleRelations;
	}

	public boolean addUserRoleRelation(UserRoleRelation userRoleRelation) {
		userRoleRelation.setRole(this);

		return getUserRoleRelations().add(userRoleRelation);
	}

	public boolean removeUserRoleRelation(UserRoleRelation userRoleRelation) {
		return getUserRoleRelations().remove(userRoleRelation);
	}

	@Override
	public int hashCode() {
		return Objects.hash(roleId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Role)) {
			return false;
		}
		Role other = (Role) obj;

		return Objects.equals(this.getRoleId(), other.getRoleId());
	}
}