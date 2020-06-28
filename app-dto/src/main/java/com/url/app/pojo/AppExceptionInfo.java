package com.url.app.pojo;

import java.util.Map;

/**
 * Application exception response object.
 * 
 * @author SHEKHAR
 */
public class AppExceptionInfo {
	private String status;
	private Integer statusCode;
	private String exceptionMsg;
	private String exceptionHeader;
	private String exceptionDesc;
	private String exceptionStack;
	private Map<String, String> invalidData;

	public AppExceptionInfo() {
		super();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	public String getExceptionHeader() {
		return exceptionHeader;
	}

	public void setExceptionHeader(String exceptionHeader) {
		this.exceptionHeader = exceptionHeader;
	}

	public String getExceptionDesc() {
		return exceptionDesc;
	}

	public void setExceptionDesc(String exceptionDesc) {
		this.exceptionDesc = exceptionDesc;
	}

	public String getExceptionStack() {
		return exceptionStack;
	}

	public void setExceptionStack(String exceptionStack) {
		this.exceptionStack = exceptionStack;
	}

	public Map<String, String> getInvalidData() {
		return invalidData;
	}

	public void setInvalidData(Map<String, String> invalidData) {
		this.invalidData = invalidData;
	}

	@Override
	public String toString() {
		return "AppExceptionInfo [status=" + status + ", statusCode=" + statusCode + ", exceptionMsg=" + exceptionMsg + ", exceptionHeader=" + exceptionHeader
				+ ", exceptionDesc=" + exceptionDesc + ", exceptionStack=" + exceptionStack + ", invalidData=" + invalidData + "]";
	}
}