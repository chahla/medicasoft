package com.rsoft.medicasoft.client.presenter;

/*
 Templates Generator
 */
/*@Author=Jean Louidort*/
/*@Generation Date=Thu Sep 19 22:50:28 EDT 2013*/
/*@Version=1.0*/
import java.util.ArrayList;
import java.util.Iterator;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mvp4g.client.annotation.Presenter;
import com.rsoft.medicasoft.client.UserRequestCallback;
import com.rsoft.medicasoft.client.view.InstitutionView;
import com.rsoft.medicasoft.client.view.viewinterface.IView;
import com.rsoft.medicasoft.client.view.viewinterface.IView.IPresenterBase;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.FilterWrapper;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.ModelException;
import com.rsoft.medicasoft.shared.SystemMessage;
import com.rsoft.medicasoft.shared.XFilter;
import com.rsoft.medicasoft.shared.i18n.I18NMessages;
import com.rsoft.medicasoft.shared.model.Institution;
import com.sencha.gxt.data.shared.Store.Record;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

@Presenter(view = InstitutionView.class, multiple = true)
public class InstitutionPresenter extends
		PresenterBase<IView<Institution>, Institution> implements
		IPresenterBase {

	@Override
	public FilterWrapper buildFilters(ModelBase value) {
		Institution model = (Institution) value;
		FilterWrapper wrapper = new FilterWrapper();
		if (model != null) {
			if (model.getNomInstitution() != null
					&& !model.getNomInstitution().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "nomInstitution", "string",
						model.getNomInstitution().toString()));
			}
			if (model.getNomCommercialeInstitution() != null
					&& !model.getNomCommercialeInstitution().isEmpty()) {
				wrapper.addFilter(new XFilter("eq",
						"nomCommercialeInstitution", "string", model
								.getNomCommercialeInstitution().toString()));
			}
			if (model.getDateCreation() != null) {
				wrapper.addFilter(new XFilter("eq", "dateCreation", "string",
						model.getDateCreation().toString()));
			}
			if (model.getAdresse() != null && !model.getAdresse().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "adresse", "string", model
						.getAdresse().toString()));
			}
			if (model.getZone() != null) {
				wrapper.addFilter(new XFilter("eq", "zone", "string", model
						.getZone().toString()));
			}
			if (model.getCommune() != null) {
				wrapper.addFilter(new XFilter("eq", "commune", "string", model
						.getCommune().toString()));
			}
			if (model.getDepartement() != null) {
				wrapper.addFilter(new XFilter("eq", "departement", "string",
						model.getDepartement().toString()));
			}
			if (model.getPays() != null) {
				wrapper.addFilter(new XFilter("eq", "pays", "string", model
						.getPays().toString()));
			}
			if (model.getTelephone1() != null
					&& !model.getTelephone1().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "telephone1", "string",
						model.getTelephone1().toString()));
			}
			if (model.getTelephone2() != null
					&& !model.getTelephone2().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "telephone2", "string",
						model.getTelephone2().toString()));
			}
			if (model.getTelephone3() != null
					&& !model.getTelephone3().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "telephone3", "string",
						model.getTelephone3().toString()));
			}
			if (model.getCourriel() != null && !model.getCourriel().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "courriel", "string", model
						.getCourriel().toString()));
			}

			if (model.getNomReponsable() != null
					&& !model.getNomReponsable().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "nomReponsable", "string",
						model.getNomReponsable().toString()));
			}
			if (model.getPrenomResponsable() != null
					&& !model.getPrenomResponsable().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "prenomResponsable",
						"string", model.getPrenomResponsable().toString()));
			}
			if (model.getExtensionResponsable() != null
					&& !model.getExtensionResponsable().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "extensionResponsable",
						"string", model.getExtensionResponsable().toString()));
			}
			if (model.getCourrielResponsable() != null
					&& !model.getCourrielResponsable().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "courrielResponsable",
						"string", model.getCourrielResponsable().toString()));
			}
			if (model.getTitreResponsable() != null
					&& !model.getTitreResponsable().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "titreResponsable",
						"string", model.getTitreResponsable().toString()));
			}
			if (model.getSexeResponsable() != null
					&& !model.getSexeResponsable().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "sexeResponsable",
						"string", model.getSexeResponsable().toString()));
			}
			if (model.getModalitePaiement() != null
					&& !model.getModalitePaiement().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "modalitePaiement",
						"string", model.getModalitePaiement().toString()));
			}
			if (model.getTypePaiement() != null
					&& !model.getTypePaiement().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "typePaiement", "string",
						model.getTypePaiement().toString()));
			}
			if (model.getBalance() != null) {
				wrapper.addFilter(new XFilter("eq", "balance", "string", model
						.getBalance().toString()));
			}
			if (model.getDateBalance() != null) {
				wrapper.addFilter(new XFilter("eq", "dateBalance", "string",
						model.getDateBalance().toString()));
			}
			if (model.getStatut() != null && !model.getStatut().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "statut", "string", model
						.getStatut().toString()));
			}
			if (model.getDelaiGrace() != null) {
				wrapper.addFilter(new XFilter("eq", "delaiGrace", "string",
						model.getDelaiGrace().toString()));
			}
			if (model.getNomContact() != null
					&& !model.getNomContact().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "nomContact", "string",
						model.getNomContact().toString()));
			}
			if (model.getPrenomContact() != null
					&& !model.getPrenomContact().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "prenomContact", "string",
						model.getPrenomContact().toString()));
			}
			if (model.getCourrielContact() != null
					&& !model.getCourrielContact().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "courrielContact",
						"string", model.getCourrielContact().toString()));
			}
			if (model.getTelephoneContact() != null
					&& !model.getTelephoneContact().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "telephoneContact",
						"string", model.getTelephoneContact().toString()));
			}
			if (model.getTelephoneResponsable() != null
					&& !model.getTelephoneResponsable().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "telephoneResponsable",
						"string", model.getTelephoneResponsable().toString()));
			}
			if (model.getTitreContact() != null
					&& !model.getTitreContact().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "titreContact", "string",
						model.getTitreContact().toString()));
			}
			if (model.getCreatedBy() != null && !model.getCreatedBy().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "createdBy", "string",
						model.getCreatedBy().toString()));
			}
			if (model.getUpdatedOn() != null) {
				wrapper.addFilter(new XFilter("eq", "updatedOn", "string",
						model.getUpdatedOn().toString()));
			}
			if (model.getCreatedOn() != null) {
				wrapper.addFilter(new XFilter("eq", "createdOn", "string",
						model.getCreatedOn().toString()));
			}
			if (model.getUpdatedBy() != null && !model.getUpdatedBy().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "updatedBy", "string",
						model.getUpdatedBy().toString()));
			}
		}

		return wrapper;
	}

	protected void changeDetails() {
		view.onDataChanged(model, true, model != null && model.getKey() != null
				&& !model.getKey().trim().isEmpty());
	}

	@Override
	protected void updateView() {
		view.updateView(model);
	}

	@Override
	protected void updateView(ArrayList<Institution> models) {
		view.updateView(models);
	}

	@Override
	protected ModelBase updateModel(ModelBase model) {
		return view.updateModel((Institution) model);
	}

	@Override
	protected void dataChanged(boolean reloadDetails,
			boolean setBtnsPersistVisible) {
		view.onDataChanged(model, reloadDetails, setBtnsPersistVisible);
	}

	@Override
	protected void initModel() {
		persistenceManagerPrefix = "Institution";
		if (model == null) {
			model = new Institution();
		}
	}

	/*
	 * * Cette methode permet de vider les enregistrements, elle s'applique aux
	 * grilles
	 */
	public void onResetGridHandled(int nbBlankRows,
			PagingLoadConfig loadConfig,
			AsyncCallback<PagingLoadResult<ModelBase>> callback) {
		ArrayList<ModelBase> datas = new ArrayList<ModelBase>(20);
		for (int i = 0; i < nbBlankRows; i++) {
			Institution model = new Institution();
			model.setLineNo(i);
			datas.add(model);
		}
		int start = loadConfig.getOffset();
		int limit = datas.size();
		if (loadConfig.getLimit() > 0) {
			limit = Math.min(start + loadConfig.getLimit(), limit);
		}
		PagingLoadResult<ModelBase> pagingLoadResult = new PagingLoadResultBean<ModelBase>(
				datas, datas.size(), loadConfig.getOffset());
		callback.onSuccess(pagingLoadResult);
	}

	/**
	 * @param modelsToBePersisted
	 *            the modelsToBePersisted to set
	 */
	public void setModelsToBePersisted() {
		if (listStore != null) {
			ArrayList<Institution> models = new ArrayList<Institution>();
			Iterator<?> record = listStore.getModifiedRecords().iterator();
			while (record.hasNext()) {
				@SuppressWarnings("rawtypes")
				Record rec = (Record) record.next();
				rec.commit(true);
				Institution model = (Institution) rec.getModel();
				if ("INSERT".equalsIgnoreCase(model.getSTATUS())
						|| "MERGE".equalsIgnoreCase(model.getSTATUS())
						|| "REMOVE".equalsIgnoreCase(model.getSTATUS())) {
					try {
						if (!"REMOVE".equalsIgnoreCase(model.getSTATUS())) {
							model.validateModel();
						}
						models.add(model);
					} catch (ModelException ex) {
						Institution dummy = listStore
								.findModelWithKey(listStore.getKeyProvider()
										.getKey(model));
						dummy.merge(model);
						if ("MISSING_FIELDS".equalsIgnoreCase(ex.getCode())) {
							model.setErrorMessage(new SystemMessage(
									I18NMessages.getMessages()
											.fill_allRequiredFields()));
						} else {
							model.setErrorMessage(new SystemMessage(
									I18NMessages.getMessages()
											.unexpectedError_insertRecord()));
						}
					} catch (Exception ex) {
						Institution dummy = listStore
								.findModelWithKey(listStore.getKeyProvider()
										.getKey(model));
						dummy.merge(model);
						model.setErrorMessage(new SystemMessage(I18NMessages
								.getMessages().unexpectedError_insertRecord()));
					}
				}
			}
			setModelsToBePersisted(models);
		}
	}

	public void onExecuteInstitution(ActionCommand action,
			UserRequestCallback<ModelBase> callback) {

		this.performAction(action, callback);
	}
}