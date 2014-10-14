/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rsoft.medicasoft.shared;

import java.io.Serializable;

/**
 * 
 * @author Jean Louidort
 */
public class RSOFTException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected RSOFTException() {
	}

	public RSOFTException(String message) {
		super(message);
	}

	public RSOFTException(boolean logIt, String message) {
		super(message);
	}

	public RSOFTException(String key, boolean logIt, String message) {
		super(message);
	}

	public RSOFTException(String key, boolean logIt, String message,
			Throwable cause) {
		super(message, cause);
	}

	public RSOFTException(String key, boolean logIt, Throwable cause) {
		super(cause);
	}

	public RSOFTException(boolean logIt, Throwable cause, String message) {
		super(message, cause);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

	@Override
	public String getLocalizedMessage() {
		return super.getLocalizedMessage();
	}
}
