package com.example.study.exception;

import java.util.HashMap;
import java.util.Map;

public class ParameterException extends Exception {

	private static final long serialVersionUID = 1L;

	Map<String, String> errorFields = new HashMap<String, String>();

	public Map<String, String> getErrorFields() {
		return errorFields;
	}

	public void setErrorFields(Map<String, String> errorFields) {
		this.errorFields = errorFields;
	}

	public void addErrorFields(String fieldName, String message) {
		errorFields.put(fieldName, message);
	}

	public boolean isErrorField() {
		return !errorFields.isEmpty();
	}
}
