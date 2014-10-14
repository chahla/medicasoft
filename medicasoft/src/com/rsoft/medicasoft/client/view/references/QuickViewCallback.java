package com.rsoft.medicasoft.client.view.references;

import com.rsoft.medicasoft.shared.ModelBase;

public interface QuickViewCallback<T extends ModelBase> {
	public void persistSuccess(T model);
}
