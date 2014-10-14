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
public class SystemMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	private MessageType messageType;
	private String message;

	public SystemMessage() {
	}

	public SystemMessage(MessageType messageType, String message) {
		this.setMessageType(messageType);
		this.setMessage(message);
	}

	public SystemMessage(String message) {
		this(null, message);
	}

	/**
	 * Get the value of messageType
	 * 
	 * @return the value of messageType
	 */
	public MessageType getMessageType() {
		return messageType;
	}

	/**
	 * Set the value of messageType
	 * 
	 * @param messageType
	 *            new value of messageType
	 */
	public void setMessageType(MessageType messageType) {
		if (messageType == null) {
			messageType = MessageType.error;
		} else {
			this.messageType = messageType;
		}
	}

	/**
	 * Get the value of message
	 * 
	 * @return the value of message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Set the value of message
	 * 
	 * @param message
	 *            new value of message
	 */
	public void setMessage(String message) {
		if (message == null) {
			throw new IllegalArgumentException(
					"ErrorMessage@Missing field message");
		}
		this.message = message;
	}
}