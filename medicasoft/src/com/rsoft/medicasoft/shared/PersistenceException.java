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
public class PersistenceException extends RSOFTException implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersistenceException() {
		super();
	}

	public PersistenceException(String message) {
		super(null, false, message);
	}

	public PersistenceException(String message, Throwable cause, boolean logIt) {
		super(null, logIt, message);
	}

	public PersistenceException(String key, boolean logIt, String message) {
		super(key, logIt, message);
	}

	public PersistenceException(String key, boolean logIt, String message,
			Throwable cause) {
		super(key, logIt, message, cause);
	}

	public PersistenceException(String key, boolean logIt, Throwable cause) {
		super(key, logIt, cause);
	}
}
