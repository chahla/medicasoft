package com.rsoft.medicasoft.shared;

import java.io.Serializable;
import java.util.ArrayList;

import com.rsoft.medicasoft.shared.menu.Menu;
import com.rsoft.medicasoft.shared.model.UserProfile;

public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private UserProfile profile;
	private ArrayList<Menu> menus;

	public UserProfile getUserProfile() {
		return profile;
	}

	public void setUserProfile(UserProfile profile) {
		this.profile = profile;
	}

	public ArrayList<Menu> getMenus() {
		return menus;
	}

	public void setMenus(ArrayList<Menu> menus) {
		this.menus = menus;
	}
}