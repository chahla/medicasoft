
package com.rsoft.medicasoft.client.view.easywidget.appearance;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;

public class EasyHeaderFramedAppearance extends EasyHeaderAppearance {

	public interface GrayHeaderFramedStyle extends HeaderStyle {

	}

	public interface GrayFramedHeaderResources extends HeaderResources {

		@Source({ "com/sencha/gxt/theme/base/client/widget/Header.css",
				"EasyHeader.css", "EasyFramedHeader.css" })
		GrayHeaderFramedStyle style();

		@ImageOptions(repeatStyle = RepeatStyle.Horizontal)
		ImageResource headerBackground();
	}

	public EasyHeaderFramedAppearance() {
		this(
				GWT.<GrayFramedHeaderResources> create(GrayFramedHeaderResources.class),
				GWT.<Template> create(Template.class));
	}

	public EasyHeaderFramedAppearance(GrayFramedHeaderResources resources,
			Template template) {
		super(resources, template);
	}
}
