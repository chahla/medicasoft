package com.rsoft.medicasoft.client.view.easywidget.appearance;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.theme.base.client.status.BoxStatusBaseAppearance;
import com.sencha.gxt.widget.core.client.Status.BoxStatusAppearance;

public class EasyBoxStatusAppearance extends BoxStatusBaseAppearance implements
		BoxStatusAppearance {

	public interface GrayBoxStatusStyle extends BoxStatusStyle {

		String status();

		String statusIcon();

		String statusText();

		String statusBox();

	}

	public interface GrayBoxStatusResources extends BoxStatusResources,
			ClientBundle {

		@Override
		@Source({ "com/sencha/gxt/theme/base/client/status/Status.css",
				"EasyBoxStatus.css" })
		GrayBoxStatusStyle style();

		@Override
		@Source("com/sencha/gxt/theme/base/client/grid/loading.gif")
		ImageResource loading();

	}

	public EasyBoxStatusAppearance() {
		this(GWT.<GrayBoxStatusResources> create(GrayBoxStatusResources.class),
				GWT.<BoxTemplate> create(BoxTemplate.class));
	}

	public EasyBoxStatusAppearance(GrayBoxStatusResources resources,
			BoxTemplate template) {
		super(resources, template);
	}

}
