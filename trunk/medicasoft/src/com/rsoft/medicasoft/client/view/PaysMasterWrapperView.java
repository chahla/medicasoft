package com.rsoft.medicasoft.client.view;

/*
 Robelkend Templates Generator
 */
/*@Author=Jean Louidort*/
/*@Generation Date=Mon Oct 14 22:30:48 EDT 2013*/
/*@Version=1.0*/
import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.client.view.viewinterface.IView;
import com.rsoft.medicasoft.shared.model.Commune;
import com.rsoft.medicasoft.shared.model.Pays;
import com.rsoft.medicasoft.shared.model.Zone;

import com.rsoft.medicasoft.client.presenter.CommunePresenter;
import com.rsoft.medicasoft.client.presenter.DepartementPresenter;
import com.rsoft.medicasoft.client.presenter.ZonePresenter;
import com.rsoft.medicasoft.shared.model.Departement;

public abstract class PaysMasterWrapperView extends ViewFormBase<Pays> {

	private DepartementPresenter departementPresenter;
	private IView<Departement> departementView;

	private CommunePresenter communePresenter;
	private IView<Commune> communeView;

	private ZonePresenter zonePresenter;
	private IView<Zone> zoneView;

	protected abstract void addDetail(Widget widget);

	protected abstract void addDetails(Widget widget, String title);

	protected void finalizeForm(ViewCallback callback, String title) {

		departementPresenter = eventBus.addHandler(DepartementPresenter.class);
		departementView = departementPresenter.getView();
		departementView.setViewCallback(callback);
		departementView.setPresenter(departementPresenter);
		departementPresenter.setHeaderTitle(title);
		addDetails(departementView.getWidget(), null);
		departementView.setBtnSaveVisible(false);
		departementView.setDetail(true);

		communePresenter = eventBus.addHandler(CommunePresenter.class);
		communeView = communePresenter.getView();
		communeView.setViewCallback(callback);
		communeView.setPresenter(communePresenter);
		communePresenter.setHeaderTitle(title);
		addDetails(communeView.getWidget(), null);
		communeView.setBtnSaveVisible(false);
		communeView.setDetail(true);

		departementPresenter.setCommunePresenter(communePresenter);

		zonePresenter = eventBus.addHandler(ZonePresenter.class);
		zoneView = zonePresenter.getView();
		zoneView.setViewCallback(callback);
		zoneView.setPresenter(zonePresenter);
		zonePresenter.setHeaderTitle(title);
		addDetails(zoneView.getWidget(), null);
		zoneView.setBtnSaveVisible(false);
		zoneView.setDetail(true);
		communePresenter.setZonePresenter(zonePresenter);
	}

	protected void persistDetails() {
		departementView.persist();
		communeView.persist();
		zoneView.persist();
	}

	protected void resetDetails() {
		departementView.reset();
		communeView.reset();
		zoneView.reset();
	}

	protected void onChangeHeader(Pays model, boolean reset,
			boolean setPersistBtnsVisible) {
		departementPresenter.onChangeHeaderDepartement(model);
		if (reset) {
			departementView.reset();
		}
		departementView.setBtnSaveVisible(setPersistBtnsVisible);
	}

	protected void onChangeHeader(Pays model) {
		onChangeHeader(model, true,
				model.getEntityId() != null && model.getEntityId() != null);
	}
}