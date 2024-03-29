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
import com.sencha.gxt.theme.base.client.tree.TreeBaseAppearance;

public class EasyTreeAppearance extends TreeBaseAppearance {

	public interface GrayTreeResources extends TreeResources, ClientBundle {

		@Source({ "com/sencha/gxt/theme/base/client/tree/Tree.css",
				"TreeDefault.css" })
		TreeBaseStyle style();

	}

	public EasyTreeAppearance() {
		super((TreeResources) GWT.create(GrayTreeResources.class));
	}
}
