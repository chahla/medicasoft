package com.rsoft.medicasoft.client.view.easywidget.appearance;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.theme.base.client.toolbar.PagingToolBarBaseAppearance;

public class EasyPagingToolBarAppearance extends PagingToolBarBaseAppearance {
	public interface GrayPagingToolBarResources extends PagingToolBarResources,
			ClientBundle {
		ImageResource first();

		ImageResource prev();

		ImageResource next();

		ImageResource last();

		ImageResource refresh();

		ImageResource loading();
	}

	public EasyPagingToolBarAppearance() {
		this(
				GWT.<GrayPagingToolBarResources> create(GrayPagingToolBarResources.class));
	}

	public EasyPagingToolBarAppearance(GrayPagingToolBarResources resources) {
		super(resources);
	}
}
