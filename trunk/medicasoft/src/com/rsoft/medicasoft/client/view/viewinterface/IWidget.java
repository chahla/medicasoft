package com.rsoft.medicasoft.client.view.viewinterface;

import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.client.view.ViewCallback;

public interface IWidget {
	public Widget getWidget();
	public void setViewCallback(ViewCallback callback);
}
