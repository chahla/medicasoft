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
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.theme.base.client.menu.MenuBaseAppearance;

public class EasyMenuAppearance extends MenuBaseAppearance {

	public interface GrayMenuResources extends
			MenuBaseAppearance.MenuResources, ClientBundle {

		@ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.Vertical)
		ImageResource itemOver();

		@ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.Vertical)
		ImageResource menu();

		ImageResource miniBottom();

		ImageResource miniTop();

		@Source({ "com/sencha/gxt/theme/base/client/menu/Menu.css",
				"EasyMenu.css" })
		GrayMenuStyle style();

	}

	public interface GrayMenuStyle extends MenuStyle {
	}

	public EasyMenuAppearance() {
		this(GWT.<GrayMenuResources> create(GrayMenuResources.class), GWT
				.<BaseMenuTemplate> create(BaseMenuTemplate.class));
	}

	public EasyMenuAppearance(GrayMenuResources resources,
			BaseMenuTemplate template) {
		super(resources, template);
	}

}
