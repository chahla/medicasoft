package com.rsoft.medicasoft.client.view.easywidget.appearance;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.rsoft.medicasoft.client.view.easywidget.appearance.EasyMenuItemAppearance.GrayMenuItemResources;
import com.sencha.gxt.theme.base.client.menu.CheckMenuItemBaseAppearance;

public class EasyCheckMenuItemAppearance extends CheckMenuItemBaseAppearance {

	public interface GrayCheckMenuItemResources extends CheckMenuItemResources,
			GrayMenuItemResources, ClientBundle {

		@Source({ "com/sencha/gxt/theme/base/client/menu/CheckMenuItem.css",
				"EasyCheckMenuItem.css" })
		GrayCheckMenuItemStyle checkStyle();

	}

	public interface GrayCheckMenuItemStyle extends CheckMenuItemStyle {
	}

	public EasyCheckMenuItemAppearance() {
		this(
				GWT.<GrayCheckMenuItemResources> create(GrayCheckMenuItemResources.class),
				GWT.<MenuItemTemplate> create(MenuItemTemplate.class));
	}

	public EasyCheckMenuItemAppearance(GrayCheckMenuItemResources resources,
			MenuItemTemplate template) {
		super(resources, template);
	}

}
