package com.rsoft.medicasoft.client.view;

/*
 Robelkend Templates Generator
 */
/*@Author=Jean Louidort*/
/*@Generation Date=Sun Sep 15 13:53:22 EDT 2013*/
/*@Version=1.0*/
import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.shared.model.UserGroupDetail;

public abstract class UserGroupDetailMasterWrapperView extends
		ViewFormBase<UserGroupDetail> {

	protected abstract void addDetail(Widget widget);

	protected abstract void addDetails(Widget widget, String title);

	protected void finalizeForm(ViewCallback callback, String title) {

	}

	protected void persistDetails() {

	}

	protected void resetDetails() {

	}

	protected void onChangeHeader(UserGroupDetail model, boolean reset,
			boolean setPersistBtnsVisible) {

	}

	protected void onChangeHeader(UserGroupDetail model) {
		onChangeHeader(model, true,
				model.getEntityId() != null && model.getEntityId() != null);
	}
}