/**
 * Sencha GXT 3.0.1 - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.rsoft.medicasoft.client.view.easywidget.appearance;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.sencha.gxt.theme.base.client.widget.HeaderDefaultAppearance;

public class EasyHeaderAppearance extends HeaderDefaultAppearance {

	public interface GrayHeaderStyle extends HeaderStyle {
		String header();

		String headerIcon();

		String headerHasIcon();

		String headerText();

		String headerBar();
	}

	public interface GrayHeaderResources extends HeaderResources {

		@Source({ "com/sencha/gxt/theme/base/client/widget/Header.css",
				"EasyHeader.css" })
		GrayHeaderStyle style();

		@ImageOptions(repeatStyle = RepeatStyle.Horizontal)
		ImageResource headerBackground();
	}
 
	public EasyHeaderAppearance() {
		this(GWT.<GrayHeaderResources> create(GrayHeaderResources.class), GWT
				.<Template> create(Template.class));
	}

	public EasyHeaderAppearance(HeaderResources resources, Template template) {
		super(resources, template);
	}

}
