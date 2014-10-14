package com.rsoft.medicasoft.client.view;
/*
	Robelkend Templates Generator
*/
/*@Author=Jean Louidort*/
/*@Generation Date=Sat Oct 26 19:38:26 EDT 2013*/
/*@Version=1.0*/
import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.client.view.viewinterface.IView;
import com.rsoft.medicasoft.shared.model.ParamInout;

public abstract class ParamInoutMasterWrapperView extends
		ViewFormBase<ParamInout> {
	
	
	
	
	protected abstract void addDetail(Widget widget);

	protected abstract void addDetails(Widget widget, String title);

	protected void finalizeForm(ViewCallback callback, String title) {
		
	}

	protected void persistDetails() {
		
	}

	protected void resetDetails() {
		
	}

	protected void onChangeHeader(ParamInout model, boolean reset,
			boolean setPersistBtnsVisible) {
		
	}

	protected void onChangeHeader(ParamInout model) {
		onChangeHeader(model, true,
				model.getEntityId() != null && model.getEntityId() != null);
	}
}