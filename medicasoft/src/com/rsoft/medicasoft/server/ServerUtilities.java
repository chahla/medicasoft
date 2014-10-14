package com.rsoft.medicasoft.server;

import java.util.ArrayList;
import java.util.List;

import com.rsoft.medicasoft.shared.menu.Menu;
import com.rsoft.medicasoft.shared.xml.XMLMenu;
import com.rsoft.medicasoft.shared.xml.XMLRootMenu;
import com.rsoft.medicasoft.shared.xml.MenuField;

public class ServerUtilities {
	public static String MENU_NAMES_ATTRIBUTE_NAME = "menuNames";

	public static Menu buildMenu(XMLRootMenu xmlRootMenu, List<String> menuNames) {
		Menu menu = new Menu();
		menu.setAvailableActions("visualize");
		menu.setMenuId(xmlRootMenu.getId());
		menu.setMenuInfo(xmlRootMenu.getDescription() != null ? xmlRootMenu
				.getDescription() : xmlRootMenu.getId());
		menu.setMenuName(xmlRootMenu.getDescription() != null ? xmlRootMenu
				.getDescription() : xmlRootMenu.getId());
		menuNames.add(xmlRootMenu.getDescription() != null ? xmlRootMenu
				.getDescription() : xmlRootMenu.getId());
		ArrayList<MenuField> xmlMenuFields = xmlRootMenu.getFields();

		for (MenuField menuField : xmlMenuFields) {
			Menu field = new Menu();
			field.setAvailableActions(menuField.getAvailableActions());
			field.setMenuId(menuField.getFieldName());
			field.setMenuInfo(menuField.getFieldDescription());
			field.setMenuName(menuField.getFieldDescription());
			menuNames.add(menuField.getFieldDescription());
			menu.addMenuField(field);
		}
		List<XMLMenu> xmlMenuChildrens = xmlRootMenu.getChildren();
		if (xmlMenuChildrens != null) {
			for (XMLMenu xmlMenuChildren : xmlMenuChildrens) {
				buildMenu(xmlMenuChildren, menu, menuNames);
			}
		}
		return menu;
	}

	public static void buildMenu(XMLMenu xmlMenu, Menu menu,
			List<String> menuNames) {
		if (xmlMenu != null && menu != null) {
			Menu menuField = new Menu();
			menuField.setMenuId(xmlMenu.getId());
			menuField.setMenuInfo(xmlMenu.getDescription());
			menuField.setMenuName(xmlMenu.getDescription());
			menu.addMenuField(menuField);
			menuNames.add(xmlMenu.getDescription());
			List<XMLMenu> xmlMenuFields = xmlMenu.getChildren();
			for (XMLMenu xmlMenuField : xmlMenuFields) {
				buildMenu(xmlMenuField, menu, menuNames);
			}
		}
	}
}
