package com.rsoft.medicasoft.client.view;
/*
	Robelkend Templates Generator
*/
/*@Author=Jean Louidort*/
/*@Generation Date=Thu Sep 19 22:50:27 EDT 2013*/
/*@Version=1.0*/
import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.shared.model.Institution;

public abstract class InstitutionMasterWrapperView extends
		ViewFormBase<Institution> {
	
	
	
	
	protected abstract void addDetail(Widget widget);

	protected abstract void addDetails(Widget widget, String title);

	protected void finalizeForm(ViewCallback callback, String title) {
		
	}

	protected void persistDetails() {
		
	}

	protected void resetDetails() {
		
	}

	protected void onChangeHeader(Institution model, boolean reset,
			boolean setPersistBtnsVisible) {
		
	}

	protected void onChangeHeader(Institution model) {
		onChangeHeader(model, true,
				model.getEntityId() != null && model.getEntityId() != null);
	}
}