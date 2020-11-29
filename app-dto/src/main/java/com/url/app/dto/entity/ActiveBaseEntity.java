package com.url.app.dto.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Base entity with is_active property along with other common properties.
 * 
 * @author Shekhar Shinde
 */
@MappedSuperclass
public class ActiveBaseEntity extends BaseEntity {
	@Column(name = "is_active", nullable = false)
	private Integer isActive;

	public Integer getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
}