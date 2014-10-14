package com.rsoft.medicasoft.client.view.easywidget.appearance;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.theme.base.client.container.BorderLayoutBaseAppearance;

public class EasyBorderLayoutAppearance extends BorderLayoutBaseAppearance {

	public interface GrayBorderLayoutResources extends BorderLayoutResources {
		@Override
		@Source({
				"com/sencha/gxt/theme/base/client/container/BorderLayout.css",
				"EasyBorderLayout.css" })
		public GrayBorderLayoutStyle css();
	}

	public interface GrayBorderLayoutStyle extends BorderLayoutStyle {

	}

	public EasyBorderLayoutAppearance() {
		super(
				GWT.<GrayBorderLayoutResources> create(GrayBorderLayoutResources.class));
	}

}
