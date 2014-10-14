package com.rsoft.medicasoft.shared;

public class SingleUserInfo {
	private static UserInfo userInfo;

	private SingleUserInfo() {
	}

	public static void updateUserInfo(UserInfo x) {
		userInfo = x;
	}

	public static void resetUserInfo() {
		userInfo = null;
	}

	public static UserInfo getUserInfo() {
		return userInfo;
	}
}
