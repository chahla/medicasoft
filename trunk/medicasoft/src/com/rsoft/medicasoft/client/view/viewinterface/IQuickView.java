package com.rsoft.medicasoft.client.view.viewinterface;

import com.rsoft.medicasoft.client.view.references.QuickViewCallback;
import com.rsoft.medicasoft.shared.ModelBase;

public interface IQuickView<T extends ModelBase> extends IView<T> {
	void setQuickViewCallback(QuickViewCallback<T> callback);
}
