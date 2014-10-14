package com.rsoft.medicasoft.client.view.references;

/*
 Robelkend Templates Generator
 */
/*@Author=Jean Louidort*/
/*@Generation Date=Sun Oct 13 17:25:52 EDT 2013*/
/*@Version=1.0*/
import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.client.view.ViewCallback;
import com.rsoft.medicasoft.shared.model.UserGroup;

public abstract class UserGroupMasterWrapperView extends
		QuickViewFormBase<UserGroup> {

	protected abstract void addDetail(Widget widget);

	protected abstract void addDetails(Widget widget, String title);

	protected void finalizeForm(ViewCallback callback, String title) {

	}

	@Override
	public void setDetail(boolean detail) {
	}

	@Override
	public boolean isDetail() {
		return false;
	}

	protected void persistDetails() {

	}

	protected void resetDetails() {

	}

	protected void onChangeHeader(UserGroup model, boolean reset,
			boolean setPersistBtnsVisible) {

	}

	protected void onChangeHeader(UserGroup model) {
		onChangeHeader(model, true,
				model.getEntityId() != null && model.getEntityId() != null);
	}
}