package com.rsoft.medicasoft.client.view;
/*
	Robelkend Templates Generator
*/
/*@Author=Jean Louidort*/
/*@Generation Date=Mon Nov 18 01:14:05 EST 2013*/
/*@Version=1.0*/
import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.client.view.viewinterface.IView;
import com.rsoft.medicasoft.shared.model.SportPratique;

public abstract class SportFrequenteMasterWrapperView extends
		ViewFormBase<SportPratique> {
	
	
	
	
	protected abstract void addDetail(Widget widget);

	protected abstract void addDetails(Widget widget, String title);

	protected void finalizeForm(ViewCallback callback, String title) {
		
	}

	protected void persistDetails() {
		
	}

	protected void resetDetails() {
		
	}

	protected void onChangeHeader(SportPratique model, boolean reset,
			boolean setPersistBtnsVisible) {
		
	}

	protected void onChangeHeader(SportPratique model) {
		onChangeHeader(model, true,
				model.getEntityId() != null && model.getEntityId() != null);
	}
}