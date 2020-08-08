package com.url.app.dto.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.url.app.utility.AppSQL;

/**
 * The persistent class for the role_privilege_relation database table.
 */
@Entity
@Table(name = "role_privilege_relation")
@AssociationOverrides({ @AssociationOverride(name = "id.privilege", joinColumns = @JoinColumn(name = "privilege_id")),
		@AssociationOverride(name = "id.role", joinColumns = @JoinColumn(name = "role_id")) })
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NamedQuery(name = "RolePrivilegeRelation.findAll", query = AppSQL.QRY_FIND_ALL_ROLE_PRIVILEGE_RELATION)
public class RolePrivilegeRelation extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RolePrivilegeRelationPK id = new RolePrivilegeRelationPK();

	@Column(name = "is_active", nullable = false)
	private Integer isActive;

	public RolePrivilegeRelation() {
		super();
	}

	public RolePrivilegeRelationPK getId() {
		return this.id;
	}

	public void setId(RolePrivilegeRelationPK id) {
		this.id = id;
	}

	@Transient
	public Privilege getPrivilege() {
		return getId().getPrivilege();
	}

	public void setPrivilege(Privilege privilege) {
		getId().setPrivilege(privilege);
	}

	@Transient
	public Role getRole() {
		return getId().getRole();
	}

	public void setRole(Role role) {
		getId().setRole(role);
	}

	public Integer getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
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
		if (!(obj instanceof RolePrivilegeRelation)) {
			return false;
		}
		RolePrivilegeRelation other = (RolePrivilegeRelation) obj;

		return Objects.equals(id, other.id);
	}
}