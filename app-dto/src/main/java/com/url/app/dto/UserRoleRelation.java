package com.url.app.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.url.app.utility.AppSQL;

/**
 * The persistent class for the user_role_relation database table.
 */
@Entity
@Table(name = "user_role_relation")
@EntityListeners(AuditingEntityListener.class)
@AssociationOverrides({ @AssociationOverride(name = "id.role", joinColumns = @JoinColumn(name = "role_id")),
		@AssociationOverride(name = "id.user", joinColumns = @JoinColumn(name = "user_id")) })
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NamedQuery(name = "UserRoleRelation.findAll", query = AppSQL.QRY_FIND_ALL_USER_ROLE_RELATION)
public class UserRoleRelation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserRoleRelationPK id = new UserRoleRelationPK();

	@Column(name = "created_by", updatable = false, nullable = false)
	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", updatable = false, nullable = false)
	@CreatedDate
	private Date createdDate;

	@Column(name = "is_active", nullable = false)
	private Integer isActive;

	@Column(name = "modified_by", nullable = false)
	private Integer modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date", nullable = false)
	@LastModifiedDate
	private Date modifiedDate;

	public UserRoleRelation() {
		super();
	}

	public UserRoleRelationPK getId() {
		return this.id;
	}

	public void setId(UserRoleRelationPK id) {
		this.id = id;
	}

	@Transient
	public Role getRole() {
		return getId().getRole();
	}

	public void setRole(Role role) {
		getId().setRole(role);
	}

	@Transient
	public User getUser() {
		return getId().getUser();
	}

	public void setUser(User user) {
		getId().setUser(user);
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof UserRoleRelation)) {
			return false;
		}
		UserRoleRelation other = (UserRoleRelation) obj;

		return Objects.equals(id, other.id);
	}
}