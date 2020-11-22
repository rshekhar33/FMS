package com.url.app.dto.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.AssociationOverride;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.url.app.utility.AppSQL;

/**
 * The persistent class for the user_role_relation database table.
 */
@Entity
@Table(name = "user_role_relation")
@AssociationOverride(name = "id.role", joinColumns = @JoinColumn(name = "role_id"))
@AssociationOverride(name = "id.user", joinColumns = @JoinColumn(name = "user_id"))
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NamedQuery(name = "UserRoleRelation.findAll", query = AppSQL.QRY_FIND_ALL_USER_ROLE_RELATION)
public class UserRoleRelation extends ActiveBaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserRoleRelationPK id = new UserRoleRelationPK();

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