package com.url.app.dto.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * The primary key class for the faculty_skillset database table.
 */
@Embeddable
public class FacultySkillsetPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Module module;

	public FacultySkillsetPK() {
		super();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	@Override
	public int hashCode() {
		return Objects.hash(module, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof FacultySkillsetPK)) {
			return false;
		}
		FacultySkillsetPK other = (FacultySkillsetPK) obj;

		return Objects.equals(module, other.module) && Objects.equals(user, other.user);
	}
}