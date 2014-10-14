package com.rsoft.medicasoft.client.view.easywidget.appearance;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.theme.base.client.panel.ContentPanelBaseAppearance;
import com.sencha.gxt.theme.base.client.widget.HeaderDefaultAppearance;

public class EasyContentPanelAppearance extends ContentPanelBaseAppearance {

	public interface GrayContentPanelResources extends ContentPanelResources {

		@Source({ "com/sencha/gxt/theme/base/client/panel/ContentPanel.css",
				"EasyContentPanel.css" })
		@Override
		GrayContentPanelStyle style();

	}

	public interface GrayContentPanelStyle extends ContentPanelStyle {

	}

	public EasyContentPanelAppearance() {
		super(
				GWT.<GrayContentPanelResources> create(GrayContentPanelResources.class),
				GWT.<ContentPanelTemplate> create(ContentPanelTemplate.class));
	}

	public EasyContentPanelAppearance(GrayContentPanelResources resources) {
		super(resources, GWT
				.<ContentPanelTemplate> create(ContentPanelTemplate.class));
	}

	@Override
	public HeaderDefaultAppearance getHeaderAppearance() {
		return new EasyHeaderAppearance();
	}
}