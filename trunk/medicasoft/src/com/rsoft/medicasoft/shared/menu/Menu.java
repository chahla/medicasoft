package com.rsoft.medicasoft.shared.menu;

//import com.rsoft.medicasoft.shared.model.UserGroupDetail;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sencha.gxt.cell.core.client.ButtonCell.ButtonArrowAlign;
import com.sencha.gxt.cell.core.client.ButtonCell.ButtonScale;
import com.sencha.gxt.cell.core.client.ButtonCell.IconAlign;

public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer seq;
	private String menuId;
	private String menuName;
	private String menuInfo;
	private String menuFieldAction;
	private String imageName;
	private List<Menu> menuFields;
	private boolean accessible;
	private List<String> actionsDenied;
	// private List<UserGroupDetail> groupeUtilisateurDetails;
	private MenuCoordonate coordonate;
	private String menuParentName;
	private String availableActions;

	/*
	 * public static boolean authorizeAdd(List<UserGroupDetail> gds, String
	 * menuName) { if (gds == null || gds.isEmpty()) { return true; }
	 * UserGroupDetail gd = new UserGroupDetail(); gd.setPanel(menuName);
	 * gd.setCompareScreen(true); int index = gds.indexOf(gd); if (index >= 0) {
	 * UserGroupDetail groupeTrouve = gds.get(index); return
	 * "Y".equalsIgnoreCase(groupeTrouve.getVisualize()); } return false; }
	 * 
	 * public void setUserGroupDetail( List<UserGroupDetail>
	 * groupeUtilisateurDetails) { this.groupeUtilisateurDetails =
	 * groupeUtilisateurDetails; }
	 */

	public String getAvailableActions() {
		return availableActions;
	}

	public void setAvailableActions(String availableActions) {
		this.availableActions = availableActions;
	}

	public String getMenuParentName() {
		return menuParentName;
	}

	public void setMenuParentName(String menuParentName) {
		this.menuParentName = menuParentName;
	}

	/**
	 * @return the coordonate
	 */
	public MenuCoordonate getCoordonate() {
		return coordonate;
	}

	/**
	 * @param coordonate
	 *            the coordonate to set
	 */
	public void setCoordonate(MenuCoordonate coordonate) {
		this.coordonate = coordonate;
	}

	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * @param imageName
	 *            the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
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
	 * @return the actionsDenied
	 */
	public List<String> getActionsDenied() {
		return actionsDenied;
	}

	/**
	 * @param actionsDenied
	 *            the actionsDenied to set
	 */
	public void setActionsDenied(List<String> actionsDenied) {
		this.actionsDenied = actionsDenied;
	}

	/**
	 * @return the seq
	 */
	public Integer getSeq() {
		return seq;
	}

	/**
	 * @param seq
	 *            the seq to set
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	/**
	 * @return the menuId
	 */
	public String getMenuId() {
		return menuId;
	}

	/**
	 * @param menuId
	 *            the menuId to set
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	/**
	 * @return the menuName
	 */
	public String getMenuName() {
		return menuName;
	}

	/**
	 * @param menuName
	 *            the menuName to set
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	/**
	 * @return the menuInfo
	 */
	public String getMenuInfo() {
		return menuInfo;
	}

	/**
	 * @param menuInfo
	 *            the menuInfo to set
	 */
	public void setMenuInfo(String menuInfo) {
		this.menuInfo = menuInfo;
	}

	/**
	 * @return the menuFields
	 */
	public List<Menu> getMenuFields() {
		return menuFields;
	}

	public void addMenuField(Menu obj) {
		if (menuFields == null) {
			menuFields = new ArrayList<Menu>();
		}
		menuFields.add(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (menuName == null) {
			return super.toString();
		}
		return menuName;
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
				+ ((menuName == null) ? 0 : menuName.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Menu other = (Menu) obj;
		if (menuName == null) {
			if (other.menuName != null) {
				return false;
			}
		} else if (!menuName.equals(other.menuName)) {
			return false;
		}
		return true;
	}

	public static class MenuCoordonate implements Serializable {
		private static final long serialVersionUID = 1L;
		private Integer x;
		private Integer y;
		private Integer rowSpan;
		private Integer colSpan;
		private ButtonScale buttonScale;
		private IconAlign iconAlign;
		private ButtonArrowAlign buttonArrowAlign;

		/**
		 * @return the x
		 */
		public Integer getX() {
			return x;
		}

		/**
		 * @param x
		 *            the x to set
		 */
		public void setX(Integer x) {
			this.x = x;
		}

		/**
		 * @return the y
		 */
		public Integer getY() {
			return y;
		}

		/**
		 * @param y
		 *            the y to set
		 */
		public void setY(Integer y) {
			this.y = y;
		}

		/**
		 * @return the rowSpan
		 */
		public Integer getRowSpan() {
			return rowSpan;
		}

		/**
		 * @param rowSpan
		 *            the rowSpan to set
		 */
		public void setRowSpan(Integer rowSpan) {
			this.rowSpan = rowSpan;
		}

		/**
		 * @return the colSpan
		 */
		public Integer getColSpan() {
			return colSpan;
		}

		/**
		 * @param colSpan
		 *            the colSpan to set
		 */
		public void setColSpan(Integer colSpan) {
			this.colSpan = colSpan;
		}

		/**
		 * @return the buttonScale
		 */
		public ButtonScale getButtonScale() {
			return buttonScale;
		}

		/**
		 * @param buttonScale
		 *            the buttonScale to set
		 */
		public void setButtonScale(ButtonScale buttonScale) {
			this.buttonScale = buttonScale;
		}

		/**
		 * @return the iconAlign
		 */
		public IconAlign getIconAlign() {
			return iconAlign;
		}

		/**
		 * @param iconAlign
		 *            the iconAlign to set
		 */
		public void setIconAlign(IconAlign iconAlign) {
			this.iconAlign = iconAlign;
		}

		/**
		 * @return the buttonArrowAlign
		 */
		public ButtonArrowAlign getButtonArrowAlign() {
			return buttonArrowAlign;
		}

		/**
		 * @param buttonArrowAlign
		 *            the buttonArrowAlign to set
		 */
		public void setButtonArrowAlign(ButtonArrowAlign buttonArrowAlign) {
			this.buttonArrowAlign = buttonArrowAlign;
		}
	}
}
