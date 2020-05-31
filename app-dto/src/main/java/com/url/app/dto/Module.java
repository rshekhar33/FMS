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
 * The persistent class for the module database table.
 */
@Entity
@Table(name = "module")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NamedQuery(name = "Module.findAll", query = AppSQL.QRY_FIND_ALL_MODULE)
public class Module implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "module_id", unique = true, nullable = false)
	@NotNull(groups = BasicActivateGroup.class, message = AppBasicValidationKey.UPDATE_FAILED_ERROR)
	@Positive(groups = BasicActivateGroup.class, message = AppBasicValidationKey.UPDATE_FAILED_ERROR)
	private Integer moduleId;

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

	@Column(name = "module_name", unique = true, nullable = false, length = 100)
	@NotBlank(groups = { BasicCreateGroup.class, BasicUpdateGroup.class }, message = AppBasicValidationKey.MANDATORY_FIELD_ERROR)
	@Pattern(groups = { BasicCreateGroup.class,
			BasicUpdateGroup.class }, regexp = AppConstant.REGEX_RESTRICTED_CHAR_3, message = AppBasicValidationKey.MODULE_MODULENAME_RESTRICTEDCHAR3_ERROR)
	@Size(groups = { BasicCreateGroup.class, BasicUpdateGroup.class }, max = 100, message = AppBasicValidationKey.LENGTH_ERROR)
	private String moduleName;

	//bi-directional many-to-one association to FacultySkillset
	@OneToMany(mappedBy = "id.module", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference(value = "module_facultySkillset")
	private Set<FacultySkillset> facultySkillsets = new HashSet<>(0);

	public Module() {
		super();
	}

	public Integer getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
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

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Set<FacultySkillset> getFacultySkillsets() {
		return this.facultySkillsets;
	}

	public void setFacultySkillsets(Set<FacultySkillset> facultySkillsets) {
		this.facultySkillsets = facultySkillsets;
	}

	public boolean addFacultySkillset(FacultySkillset facultySkillset) {
		facultySkillset.setModule(this);

		return getFacultySkillsets().add(facultySkillset);
	}

	public boolean removeFacultySkillset(FacultySkillset facultySkillset) {
		return getFacultySkillsets().remove(facultySkillset);
	}

	@Override
	public int hashCode() {
		return Objects.hash(moduleId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Module)) {
			return false;
		}
		Module other = (Module) obj;

		return Objects.equals(this.getModuleId(), other.getModuleId());
	}
}