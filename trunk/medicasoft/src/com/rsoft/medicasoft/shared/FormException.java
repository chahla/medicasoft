package com.rsoft.medicasoft.shared;

import java.io.Serializable;

public class FormException extends RSOFTException implements Serializable {
	private String code;
	private String parameters;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FormException() {
		super();
	}

	public FormException(String message) {
		super(message);
	}

	public FormException(String message, String code, String parameters) {
		super(message);
		this.code = code;
		this.parameters = parameters;
	}

	/**
	 * @return the parameters
	 */
	public String getParameters() {
		return parameters;
	}

	/**
	 * @param parameters
	 *            the parameters to set
	 */
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
}
