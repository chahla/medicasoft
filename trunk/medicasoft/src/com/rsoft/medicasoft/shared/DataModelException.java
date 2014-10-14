/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rsoft.medicasoft.shared;

/**
 * 
 * @author Jean Louidort
 */
public class DataModelException extends RSOFTException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataModelException() {
		super();
	}

	public DataModelException(String key, boolean logIt, String message) {
		super(key, logIt, message);
	}

	public DataModelException(String key, boolean logIt, String message,
			Throwable cause) {
		super(key, logIt, message, cause);
	}

	public DataModelException(String key, boolean logIt, Throwable cause) {
		super(key, logIt, cause);
	}

}