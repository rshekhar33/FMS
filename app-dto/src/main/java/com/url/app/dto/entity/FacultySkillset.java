package com.url.app.dto.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.url.app.utility.AppSQL;

/**
 * The persistent class for the faculty_skillset database table.
 */
@Entity
@Table(name = "faculty_skillset")
@EntityListeners(AuditingEntityListener.class)
@AssociationOverrides({ @AssociationOverride(name = "id.module", joinColumns = @JoinColumn(name = "module_id")),
		@AssociationOverride(name = "id.user", joinColumns = @JoinColumn(name = "user_id")) })
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NamedQuery(name = "FacultySkillset.findAll", query = AppSQL.QRY_FIND_ALL_FACULTY_SKILLSET)
public class FacultySkillset implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FacultySkillsetPK id = new FacultySkillsetPK();

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