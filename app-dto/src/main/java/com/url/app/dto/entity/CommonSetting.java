package com.url.app.dto.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.url.app.utility.AppSQL;

/**
 * The persistent class for the common_setting database table.
 */
@Entity
@Table(name = "common_setting")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NamedQuery(name = "CommonSetting.findAll", query = AppSQL.QRY_FIND_ALL_COMMON_SETTING)
public class CommonSetting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "common_setting_id", unique = true, nullable = false)
	private Integer commonSettingId;

	@Column(name = "order_number")
	private Integer orderNumber;

	@Column(nullable = false, length = 100)
	private String type;

	@Column(length = 100)
	private String value;

	public CommonSetting() {
		super();
	}

	public Integer getCommonSettingId() {
		return this.commonSettingId;
	}

	public void setCommonSettingId(Integer commonSettingId) {
		this.commonSettingId = commonSettingId;
	}

	public Integer getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(commonSettingId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CommonSetting)) {
			return false;
		}
		CommonSetting other = (CommonSetting) obj;

		return Objects.equals(this.getCommonSettingId(), other.getCommonSettingId());
	}
}