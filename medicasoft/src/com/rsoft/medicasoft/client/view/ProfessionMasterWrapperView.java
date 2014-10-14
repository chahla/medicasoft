package com.rsoft.medicasoft.client.view;
/*
	Robelkend Templates Generator
*/
/*@Author=Jean Louidort*/
/*@Generation Date=Mon Nov 18 01:14:09 EST 2013*/
/*@Version=1.0*/
import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.client.view.viewinterface.IView;
import com.rsoft.medicasoft.shared.model.Profession;

public abstract class ProfessionMasterWrapperView extends
		ViewFormBase<Profession> {
	
	
	
	
	protected abstract void addDetail(Widget widget);

	protected abstract void addDetails(Widget widget, String title);

	protected void finalizeForm(ViewCallback callback, String title) {
		
	}

	protected void persistDetails() {
		
	}

	protected void resetDetails() {
		
	}

	protected void onChangeHeader(Profession model, boolean reset,
			boolean setPersistBtnsVisible) {
		
	}

	protected void onChangeHeader(Profession model) {
		onChangeHeader(model, true,
				model.getEntityId() != null && model.getEntityId() != null);
	}
}