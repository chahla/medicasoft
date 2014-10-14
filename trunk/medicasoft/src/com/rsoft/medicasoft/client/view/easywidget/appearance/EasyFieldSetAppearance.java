/**
 * Sencha GXT 3.0.1 - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.rsoft.medicasoft.client.view.easywidget.appearance;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.theme.base.client.field.FieldSetDefaultAppearance;

public class EasyFieldSetAppearance extends FieldSetDefaultAppearance {

	public interface GrayFieldSetResources extends FieldSetResources {

		@Override
		@Source({ "com/sencha/gxt/theme/base/client/field/FieldSet.css",
				"EasyFieldSet.css" })
		public GrayFieldSetStyle css();
	}

	public interface GrayFieldSetStyle extends FieldSetStyle {

	}

	public EasyFieldSetAppearance() {
		this(GWT.<GrayFieldSetResources> create(GrayFieldSetResources.class));
	}

	public EasyFieldSetAppearance(GrayFieldSetResources resources) {
		super(resources);
	}

}
