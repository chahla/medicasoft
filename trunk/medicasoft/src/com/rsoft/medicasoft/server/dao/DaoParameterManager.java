package com.rsoft.medicasoft.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.rsoft.medicasoft.server.ServerUtilities;

public class DaoParameterManager {
	@SuppressWarnings("unchecked")
	public static List<Object> getParameters(HttpSession session, String daoPrefix) {
		List<Object> parameters = new ArrayList<Object>();
		if ("UserGroup".equalsIgnoreCase(daoPrefix)) {
			Object attribute = (Object) session
					.getAttribute(ServerUtilities.MENU_NAMES_ATTRIBUTE_NAME);
			parameters.add(((List<String>) attribute));
		}
		return parameters;
	}
}
