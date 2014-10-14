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
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.theme.base.client.toolbar.ToolBarBaseAppearance;

public class EasyToolBarAppearance extends ToolBarBaseAppearance {

	public interface GrayToolBarResources extends ClientBundle {
		@Source({ "com/sencha/gxt/theme/base/client/toolbar/ToolBarBase.css",
				"EasyToolBar.css" })
		GrayToolBarStyle style();

		@ImageOptions(repeatStyle = RepeatStyle.Horizontal)
		ImageResource background();

	}

	public interface GrayToolBarStyle extends ToolBarBaseStyle, CssResource {

	}

	private final GrayToolBarStyle style;
	private final GrayToolBarResources resources;

	public EasyToolBarAppearance() {
		this(GWT.<GrayToolBarResources> create(GrayToolBarResources.class));
	}

	public EasyToolBarAppearance(GrayToolBarResources resources) {
		this.resources = resources;
		this.style = this.resources.style();

		StyleInjectorHelper.ensureInjected(style, true);
	}

	@Override
	public String toolBarClassName() {
		return style.toolBar();
	}

}
