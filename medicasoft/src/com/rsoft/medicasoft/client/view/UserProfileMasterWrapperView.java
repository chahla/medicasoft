package com.rsoft.medicasoft.client.view;
/*
	Robelkend Templates Generator
*/
/*@Author=Jean Louidort*/
/*@Generation Date=Tue Oct 08 19:46:11 EDT 2013*/
/*@Version=1.0*/
import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.shared.model.UserProfile;

public abstract class UserProfileMasterWrapperView extends
		ViewFormBase<UserProfile> {
	
	
	
	
	protected abstract void addDetail(Widget widget);

	protected abstract void addDetails(Widget widget, String title);

	protected void finalizeForm(ViewCallback callback, String title) {
		
	}

	protected void persistDetails() {
		
	}

	protected void resetDetails() {
		
	}

	protected void onChangeHeader(UserProfile model, boolean reset,
			boolean setPersistBtnsVisible) {
		
	}

	protected void onChangeHeader(UserProfile model) {
		onChangeHeader(model, true,
				model.getEntityId() != null && model.getEntityId() != null);
	}
}