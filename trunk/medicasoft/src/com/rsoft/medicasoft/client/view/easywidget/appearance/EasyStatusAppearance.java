
package com.rsoft.medicasoft.client.view.easywidget.appearance;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.theme.base.client.status.StatusBaseAppearance;

public class EasyStatusAppearance extends StatusBaseAppearance {

	public interface GrayStatusResources extends
			StatusBaseAppearance.StatusResources, ClientBundle {

		@Override
		@Source({ "com/sencha/gxt/theme/base/client/status/Status.css",
				"EasyStatus.css" })
		StatusStyle style();

		@Override
		@Source("com/sencha/gxt/theme/base/client/grid/loading.gif")
		ImageResource loading();

	}

	public EasyStatusAppearance() {
		super(GWT.<StatusResources> create(GrayStatusResources.class), GWT
				.<Template> create(Template.class));
	}

	public EasyStatusAppearance(GrayStatusResources resources, Template template) {
		super(resources, template);
	}

}
