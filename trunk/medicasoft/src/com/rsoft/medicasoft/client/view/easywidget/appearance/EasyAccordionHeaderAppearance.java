package com.rsoft.medicasoft.client.view.easywidget.appearance;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.sencha.gxt.theme.base.client.widget.HeaderDefaultAppearance;

public class EasyAccordionHeaderAppearance extends HeaderDefaultAppearance {

	public interface GrayAccordionHeaderStyle extends HeaderStyle {
		String header();

		String headerIcon();

		String headerHasIcon();

		String headerText();

		String headerBar();
	}

	public interface GrayAccordionHeaderResources extends HeaderResources {

		@Source({ "com/sencha/gxt/theme/base/client/widget/Header.css",
				"EasyHeader.css", "EasyAccordionHeader.css" })
		GrayAccordionHeaderStyle style();

		@Source("light-hd.gif")
		@ImageOptions(repeatStyle = RepeatStyle.Horizontal)
		ImageResource headerBackground();
	}

	public EasyAccordionHeaderAppearance() {
		super(
				GWT.<GrayAccordionHeaderResources> create(GrayAccordionHeaderResources.class));
	}

}
