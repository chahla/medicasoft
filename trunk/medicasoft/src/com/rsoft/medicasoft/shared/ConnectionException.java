package com.rsoft.medicasoft.shared;

public class ConnectionException extends RSOFTException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConnectionException() {
		super();
	}

	public ConnectionException(String key, boolean logIt, String message) {
		super(key, logIt, message);
	}

	public ConnectionException(String key, boolean logIt, String message,
			Throwable cause) {
		super(key, logIt, message, cause);
	}

	public ConnectionException(String key, boolean logIt, Throwable cause) {
		super(key, logIt, cause);
	}

}
