/**
 * Sencha GXT 3.0.1 - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.rsoft.medicasoft.client.view.easywidget.appearance;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.theme.base.client.grid.GridBaseAppearance;

public class EasyGridAppearance extends GridBaseAppearance {

	public interface GrayGridStyle extends GridStyle {

	}

	public interface GrayGridResources extends GridResources {

		@Source({ "com/sencha/gxt/theme/base/client/grid/Grid.css",
				"EasyGrid.css" })
		@Override
		GrayGridStyle css();
	}

	public EasyGridAppearance() {
		this(GWT.<GrayGridResources> create(GrayGridResources.class));
	}

	public EasyGridAppearance(GrayGridResources resources) {
		super(resources);
	}
}
