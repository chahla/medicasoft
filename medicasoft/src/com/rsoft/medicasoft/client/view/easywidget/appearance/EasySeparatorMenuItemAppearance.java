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
import com.sencha.gxt.theme.base.client.menu.SeparatorMenuItemBaseAppearance;

public class EasySeparatorMenuItemAppearance extends
		SeparatorMenuItemBaseAppearance {

	public interface GraySeparatorMenuItemResources extends ClientBundle,
			SeparatorMenuItemResources {

		@Source({
				"com/sencha/gxt/theme/base/client/menu/SeparatorMenuItem.css",
				"EasySeparatorMenuItem.css" })
		GraySeparatorMenuItemStyle style();

	}

	public interface GraySeparatorMenuItemStyle extends SeparatorMenuItemStyle {
	}

	public EasySeparatorMenuItemAppearance() {
		this(
				GWT.<GraySeparatorMenuItemResources> create(GraySeparatorMenuItemResources.class),
				GWT.<SeparatorMenuItemTemplate> create(SeparatorMenuItemTemplate.class));
	}

	public EasySeparatorMenuItemAppearance(
			GraySeparatorMenuItemResources resources,
			SeparatorMenuItemTemplate template) {
		super(resources, template);
	}

}
