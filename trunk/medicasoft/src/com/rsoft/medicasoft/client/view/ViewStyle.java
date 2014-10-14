package com.rsoft.medicasoft.client.view;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface ViewStyle {
	interface Bundle extends ClientBundle {
		@Source("grid.css")
		Style css();
	}

	interface Style extends CssResource {
		String errorRow();
		String alternateRow();
	}
}
