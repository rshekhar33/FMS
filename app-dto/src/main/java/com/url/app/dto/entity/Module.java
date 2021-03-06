package com.url.app.dto.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.url.app.dto.validation.BasicActivateGroup;
import com.url.app.dto.validation.BasicCreateGroup;
import com.url.app.dto.validation.BasicUpdateGroup;
import com.url.app.utility.AppBasicValidationKey;
import com.url.app.utility.AppConstant;
import com.url.app.utility.AppSQL;

/**
 * The persistent class for the module database table.
 */
@Entity
@Table(name = "module")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NamedQuery(name = "Module.findAll", query = AppSQL.QRY_FIND_ALL_MODULE)
public class Module extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "module_id", unique = true, nullable = false)
	@NotNull(groups = BasicActivateGroup.class, message = AppBasicValidationKey.UPDATE_FAILED_ERROR)
	@Positive(groups = BasicActivateGroup.class, message = AppBasicValidationKey.UPDATE_FAILED_ERROR)
	private Integer moduleId;

	@Column(name = "is_active", nullable = false)
	@NotNull(groups = BasicActivateGroup.class, message = AppBasicValidationKey.UPDATE_FAILED_ERROR)
	@Min(groups = BasicActivateGroup.class, value = 0, message = AppBasicValidationKey.UPDATE_FAILED_ERROR)
	@Max(groups = BasicActivateGroup.class, value = 1, message = AppBasicValidationKey.UPDATE_FAILED_ERROR)
	private Integer isActive;

	@Column(name = "module_name", unique = true, nullable = false, length = 100)
	@NotBlank(groups = { BasicCreateGroup.class, BasicUpdateGroup.class }, message = AppBasicValidationKey.MANDATORY_FIELD_ERROR)
	@Pattern(groups = { BasicCreateGroup.class,
			BasicUpdateGroup.class }, regexp = AppConstant.REGEX_RESTRICTED_CHAR_3, message = AppBasicValidationKey.MODULE_MODULENAME_RESTRICTEDCHAR3_ERROR)
	@Size(groups = { BasicCreateGroup.class, BasicUpdateGroup.class }, max = 100, message = AppBasicValidationKey.LENGTH_ERROR)
	private String moduleName;

	//bi-directional many-to-one association to UserSkillset
	@OneToMany(mappedBy = "id.module", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference(value = "module_userSkillset")
	private Set<UserSkillset> userSkillsets = new HashSet<>(0);

	public Module() {
		super();
	}

	public Integer getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Set<UserSkillset> getUserSkillsets() {
		return this.userSkillsets;
	}

	public void setUserSkillsets(Set<UserSkillset> userSkillsets) {
		this.userSkillsets = userSkillsets;
	}

	public boolean addUserSkillset(UserSkillset userSkillset) {
		userSkillset.setModule(this);

		return getUserSkillsets().add(userSkillset);
	}

	public boolean removeUserSkillset(UserSkillset userSkillset) {
		return getUserSkillsets().remove(userSkillset);
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