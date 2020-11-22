package com.url.app.dto.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.AssociationOverride;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.url.app.utility.AppSQL;

/**
 * The persistent class for the faculty_skillset database table.
 */
@Entity
@Table(name = "faculty_skillset")
@AssociationOverride(name = "id.module", joinColumns = @JoinColumn(name = "module_id"))
@AssociationOverride(name = "id.user", joinColumns = @JoinColumn(name = "user_id"))
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NamedQuery(name = "FacultySkillset.findAll", query = AppSQL.QRY_FIND_ALL_FACULTY_SKILLSET)
public class FacultySkillset extends ActiveBaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FacultySkillsetPK id = new FacultySkillsetPK();

	//bi-directional many-to-one association to Course
	@OneToMany(mappedBy = "facultySkillset", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference(value = "facultySkillset_course")
	private Set<Course> courses = new HashSet<>(0);

	public FacultySkillset() {
		super();
	}

	public FacultySkillsetPK getId() {
		return this.id;
	}

	public void setId(FacultySkillsetPK id) {
		this.id = id;
	}

	@Transient
	public Module getModule() {
		return getId().getModule();
	}

	public void setModule(Module module) {
		getId().setModule(module);
	}

	@Transient
	public User getUser() {
		return getId().getUser();
	}

	public void setUser(User user) {
		getId().setUser(user);
	}

	public Set<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public boolean addCourse(Course course) {
		course.setFacultySkillset(this);

		return getCourses().add(course);
	}

	public boolean removeCourse(Course course) {
		return getCourses().remove(course);
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
		if (!(obj instanceof FacultySkillset)) {
			return false;
		}
		FacultySkillset other = (FacultySkillset) obj;

		return Objects.equals(id, other.id);
	}
}