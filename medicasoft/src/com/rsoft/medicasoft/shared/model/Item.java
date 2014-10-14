package com.rsoft.medicasoft.shared.model;

import java.io.Serializable;

import com.rsoft.medicasoft.shared.i18n.I18NMessages;

public class Item implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String description;
	private String ID;

	public Item() {

	}

	public Item(String ID) {
		this.setID(ID);
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
		if (ID != null && !ID.trim().isEmpty()) {
			setDescription(I18NMessages.getMessages().unknownDescription(ID));
		} else {
			setDescription(null);
		}
	}

	public String getDescription() {
		try {
			return description != null ? description
					: ID != null ? I18NMessages.getMessages()
							.unknownDescription(ID) : null;
		} catch (Exception e) {
			// Donothing
		}
		return null;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return getDescription();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}
}