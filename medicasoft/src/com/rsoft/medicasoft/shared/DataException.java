package com.rsoft.medicasoft.shared;

public class DataException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public DataException() {
		super();
	}

	public DataException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	public DataException(String message) {
		super(message);
		this.message = message;
	}

	public DataException(Throwable cause) {
		super(cause);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		String message = this.message;
		if (message == null) {
			message = super.getMessage();
		}
		if (message == null) {
			message = super.toString();
		}
		return message;
	}
}
