package com.rsoft.medicasoft.client.view;

/*
 Robelkend Templates Generator
 */
/*@Author=Jean Louidort*/
/*@Generation Date=Sun Dec 01 17:08:16 EST 2013*/
/*@Version=1.0*/
import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.client.view.viewinterface.IView;
import com.rsoft.medicasoft.shared.model.Assurance;
import com.sencha.gxt.core.client.Style.LayoutRegion;
import com.sencha.gxt.widget.core.client.info.Info;

public abstract class AssuranceMasterWrapperView extends T<Assurance> {

	protected abstract void addDetail(Widget widget);

	protected abstract void addDetails(Widget widget, String title);

	protected void finalizeForm(ViewCallback callback, String title) {

	}

	protected void persistDetails() {

	}

	protected void resetDetails() {

	}

	protected void onChangeHeader(Assurance model, boolean reset,
			boolean setPersistBtnsVisible) {

	}

	protected void onChangeHeader(Assurance model) {
		onChangeHeader(model, true,
				model.getEntityId() != null && model.getEntityId() != null);
	}

	public void showInfoBanner(boolean show) {
		String html = htmlMessage.getHTML();
		if ((html == null || html.trim().isEmpty()) && show) {
			Info.display(messages.message(),
					messages.no_information_to_display());
		} else if (html != null && !html.trim().isEmpty()) {
			Info.display(messages.message(), html);
		}
		if (!show) {
			mainContainer.hide(LayoutRegion.NORTH);
			menuContainer.hide(LayoutRegion.NORTH);
			northData.setSize(0);
			bannerInfoIsShowed = false;
			mainContainer.show(LayoutRegion.NORTH);
		} else {
			mainContainer.hide(LayoutRegion.NORTH);
			menuContainer.show(LayoutRegion.NORTH);
			northData.setSize(36);
			bannerInfoIsShowed = true;
			mainContainer.show(LayoutRegion.NORTH);
		}
	}

}