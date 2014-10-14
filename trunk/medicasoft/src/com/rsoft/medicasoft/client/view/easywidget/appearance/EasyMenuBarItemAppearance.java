/**
 * Sencha GXT 3.0.1 - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.rsoft.medicasoft.client.view.easywidget.appearance;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.sencha.gxt.theme.base.client.menu.MenuBarItemBaseAppearance;

public class EasyMenuBarItemAppearance extends MenuBarItemBaseAppearance {

	public interface GrayMenuBarItemResources extends MenuBarItemResources,
			ClientBundle {
		@Source({ "com/sencha/gxt/theme/base/client/menu/MenuBarItem.css",
				"EasyMenuBarItem.css" })
		GrayMenuBarItemStyle css();
	}

	public interface GrayMenuBarItemStyle extends MenuBarItemStyle {
	}

	public EasyMenuBarItemAppearance() {
		this(
				GWT.<GrayMenuBarItemResources> create(GrayMenuBarItemResources.class));
	}

	public EasyMenuBarItemAppearance(GrayMenuBarItemResources resources) {
		super(resources);
	}

}
