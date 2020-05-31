package com.url.app.dto.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * The primary key class for the role_privilege_relation database table.
 */
@Embeddable
public class RolePrivilegeRelationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Role role;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Privilege privilege;

	public RolePrivilegeRelationPK() {
		super();
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	@Override
	public int hashCode() {
		return Objects.hash(privilege, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof RolePrivilegeRelationPK)) {
			return false;
		}
		RolePrivilegeRelationPK other = (RolePrivilegeRelationPK) obj;

		return Objects.equals(privilege, other.privilege) && Objects.equals(role, other.role);
	}
}