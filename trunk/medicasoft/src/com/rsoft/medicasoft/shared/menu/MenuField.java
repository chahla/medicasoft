package com.rsoft.medicasoft.shared.menu;

import java.util.List;

public class MenuField {
	private String menuFieldId;
	private String menuFieldDescription;
	private String menuFieldInfo;
	private String menuFieldAction;
	private boolean accessible;
	private List<String> actionRights;
	/**
	 * @return the menuFieldId
	 */
	public String getMenuFieldId() {
		return menuFieldId;
	}

	/**
	 * @param menuFieldId
	 *            the menuFieldId to set
	 */
	public void setMenuFieldId(String menuFieldId) {
		this.menuFieldId = menuFieldId;
	}

	/**
	 * @return the menuFieldDescription
	 */
	public String getMenuFieldDescription() {
		return menuFieldDescription;
	}

	/**
	 * @param menuFieldDescription
	 *            the menuFieldDescription to set
	 */
	public void setMenuFieldDescription(String menuFieldDescription) {
		this.menuFieldDescription = menuFieldDescription;
	}

	/**
	 * @return the menuFieldInfo
	 */
	public String getMenuFieldInfo() {
		return menuFieldInfo;
	}

	/**
	 * @param menuFieldInfo
	 *            the menuFieldInfo to set
	 */
	public void setMenuFieldInfo(String menuFieldInfo) {
		this.menuFieldInfo = menuFieldInfo;
	}

	/**
	 * @return the menuFieldAction
	 */
	public String getMenuFieldAction() {
		return menuFieldAction;
	}

	/**
	 * @param menuFieldAction
	 *            the menuFieldAction to set
	 */
	public void setMenuFieldAction(String menuFieldAction) {
		this.menuFieldAction = menuFieldAction;
	}

	/**
	 * @return the accessible
	 */
	public boolean isAccessible() {
		return accessible;
	}

	/**
	 * @param accessible
	 *            the accessible to set
	 */
	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((menuFieldId == null) ? 0 : menuFieldId.hashCode());
		return result;
	}

	/**
	 * @return the actionRights
	 */
	public List<String> getActionRights() {
		return actionRights;
	}

	/**
	 * @param actionRights the actionRights to set
	 */
	public void setActionRights(List<String> actionRights) {
		this.actionRights = actionRights;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuField other = (MenuField) obj;
		if (menuFieldId == null) {
			if (other.menuFieldId != null)
				return false;
		} else if (!menuFieldId.equals(other.menuFieldId))
			return false;
		return true;
	}
}
