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
import com.rsoft.medicasoft.client.view.PatientView;
import com.rsoft.medicasoft.client.view.viewinterface.IView;
import com.rsoft.medicasoft.client.view.viewinterface.IView.IPresenterBase;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.FilterWrapper;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.ModelException;
import com.rsoft.medicasoft.shared.SystemMessage;
import com.rsoft.medicasoft.shared.XFilter;
import com.rsoft.medicasoft.shared.i18n.I18NMessages;
import com.rsoft.medicasoft.shared.model.Patient;
import com.sencha.gxt.data.shared.Store.Record;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

@Presenter(view = PatientView.class, multiple = true)
public class PatientPresenter extends PresenterBase<IView<Patient>, Patient>
		implements IPresenterBase {

	@Override
	public FilterWrapper buildFilters(ModelBase value) {
		Patient model = (Patient) value;
		FilterWrapper wrapper = new FilterWrapper();
		if (model != null) {
			if (model.getNom() != null && !model.getNom().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "nom", "string", model
						.getNom().toString()));
			}
			if (model.getReligion() != null && !model.getReligion().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "religion", "string", model
						.getReligion().toString()));
			}
			if (model.getPrenom() != null && !model.getPrenom().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "prenom", "string", model
						.getPrenom().toString()));
			}
			if (model.getStatut() != null && !model.getStatut().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "statut", "string", model
						.getStatut().toString()));
			}
			if (model.getDateNaissance() != null
					&& !model.getDateNaissance().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "dateNaissance", "string",
						model.getDateNaissance().toString()));
			}
			if (model.getLieuNaissance() != null
					&& !model.getLieuNaissance().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "lieuNaissance", "string",
						model.getLieuNaissance().toString()));
			}
			if (model.getDateDeces() != null) {
				wrapper.addFilter(new XFilter("eq", "dateDeces", "string",
						model.getDateDeces().toString()));
			}
			if (model.getLieuDeces() != null && !model.getLieuDeces().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "lieuDeces", "string",
						model.getLieuDeces().toString()));
			}
			if (model.getHeureNaissance() != null
					&& !model.getHeureNaissance().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "heureNaissance", "string",
						model.getHeureNaissance().toString()));
			}
			if (model.getHeureDeces() != null
					&& !model.getHeureDeces().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "heureDeces", "string",
						model.getHeureDeces().toString()));
			}
			if (model.getCauseDeces() != null
					&& !model.getCauseDeces().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "causeDeces", "string",
						model.getCauseDeces().toString()));
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
			if (model.getNomResponsable1() != null
					&& !model.getNomResponsable1().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "nomResponsable1",
						"string", model.getNomResponsable1().toString()));
			}
			if (model.getPrenomResponsable1() != null
					&& !model.getPrenomResponsable1().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "prenomResponsable1",
						"string", model.getPrenomResponsable1().toString()));
			}
			if (model.getCourriel() != null && !model.getCourriel().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "courriel", "string", model
						.getCourriel().toString()));
			}
			if (model.getCourrielResponsable1() != null
					&& !model.getCourrielResponsable1().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "courrielResponsable1",
						"string", model.getCourrielResponsable1().toString()));
			}
			if (model.getTelephone1Responsable1() != null
					&& !model.getTelephone1Responsable1().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "telephone1Responsable1",
						"string", model.getTelephone1Responsable1().toString()));
			}
			if (model.getTelephone2Responsable1() != null
					&& !model.getTelephone2Responsable1().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "telephone2Responsable1",
						"string", model.getTelephone2Responsable1().toString()));
			}
			if (model.getNomPere() != null && !model.getNomPere().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "nomPere", "string", model
						.getNomPere().toString()));
			}
			if (model.getNomMere() != null && !model.getNomMere().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "nomMere", "string", model
						.getNomMere().toString()));
			}
			if (model.getEtatCivil() != null && !model.getEtatCivil().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "etatCivil", "string",
						model.getEtatCivil().toString()));
			}
			if (model.getNomEpoux() != null && !model.getNomEpoux().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "nomEpoux", "string", model
						.getNomEpoux().toString()));
			}
			if (model.getPrenomEpoux() != null
					&& !model.getPrenomEpoux().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "prenomEpoux", "string",
						model.getPrenomEpoux().toString()));
			}
			if (model.getTelephoneEpoux() != null
					&& !model.getTelephoneEpoux().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "telephoneEpoux", "string",
						model.getTelephoneEpoux().toString()));
			}
			if (model.getCourrielEpoux() != null
					&& !model.getCourrielEpoux().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "courrielEpoux", "string",
						model.getCourrielEpoux().toString()));
			}
			if (model.getPrenomPere() != null
					&& !model.getPrenomPere().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "prenomPere", "string",
						model.getPrenomPere().toString()));
			}
			if (model.getPrenomMere() != null
					&& !model.getPrenomMere().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "prenomMere", "string",
						model.getPrenomMere().toString()));
			}
			if (model.getPereId() != null) {
				wrapper.addFilter(new XFilter("eq", "pereId", "string", model
						.getPereId().toString()));
			}
			if (model.getMereId() != null) {
				wrapper.addFilter(new XFilter("eq", "mereId", "string", model
						.getMereId().toString()));
			}
			if (model.getEpouxId() != null) {
				wrapper.addFilter(new XFilter("eq", "epouxId", "string", model
						.getEpouxId().toString()));
			}
			if (model.getNationalite() != null
					&& !model.getNationalite().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "nationalite", "string",
						model.getNationalite().toString()));
			}
			if (model.getPaysResidence() != null
					&& !model.getPaysResidence().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "paysResidence", "string",
						model.getPaysResidence().toString()));
			}
			if (model.getAdresse() != null && !model.getAdresse().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "adresse", "string", model
						.getAdresse().toString()));
			}
			if (model.getSexe() != null && !model.getSexe().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "sexe", "string", model
						.getSexe().toString()));
			}
			if (model.getTypeIdentication() != null
					&& !model.getTypeIdentication().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "typeIdentication",
						"string", model.getTypeIdentication().toString()));
			}
			if (model.getNoIdentification() != null
					&& !model.getNoIdentification().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "noIdentification",
						"string", model.getNoIdentification().toString()));
			}
			if (model.getPaysIdentifcation() != null
					&& !model.getPaysIdentifcation().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "paysIdentifcation",
						"string", model.getPaysIdentifcation().toString()));
			}
			if (model.getNomEnfants() != null) {
				wrapper.addFilter(new XFilter("eq", "nomEnfants", "string",
						model.getNomEnfants().toString()));
			}
			if (model.getLienParenteResponsable1() != null
					&& !model.getLienParenteResponsable1().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "lienParenteResponsable1",
						"string", model.getLienParenteResponsable1().toString()));
			}
			if (model.getTelephonePere() != null
					&& !model.getTelephonePere().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "telephonePere", "string",
						model.getTelephonePere().toString()));
			}
			if (model.getTelephoneMere() != null
					&& !model.getTelephoneMere().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "telephoneMere", "string",
						model.getTelephoneMere().toString()));
			}
			if (model.getNomResponsable2() != null
					&& !model.getNomResponsable2().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "nomResponsable2",
						"string", model.getNomResponsable2().toString()));
			}
			if (model.getPrenomResponsable2() != null
					&& !model.getPrenomResponsable2().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "prenomResponsable2",
						"string", model.getPrenomResponsable2().toString()));
			}
			if (model.getCourrielResponsable2() != null
					&& !model.getCourrielResponsable2().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "courrielResponsable2",
						"string", model.getCourrielResponsable2().toString()));
			}
			if (model.getTelephone1Responsable2() != null
					&& !model.getTelephone1Responsable2().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "telephone1Responsable2",
						"string", model.getTelephone1Responsable2().toString()));
			}
			if (model.getTelephone2Responsable2() != null
					&& !model.getTelephone2Responsable2().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "telephone2Responsable2",
						"string", model.getTelephone2Responsable2().toString()));
			}
			if (model.getAdresseResponsable1() != null
					&& !model.getAdresseResponsable1().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "adresseResponsable1",
						"string", model.getAdresseResponsable1().toString()));
			}
			if (model.getAdresseResponsable2() != null
					&& !model.getAdresseResponsable2().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "adresseResponsable2",
						"string", model.getAdresseResponsable2().toString()));
			}
			if (model.getTaille() != null && !model.getTaille().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "taille", "string", model
						.getTaille().toString()));
			}
			if (model.getPoids() != null && !model.getPoids().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "poids", "string", model
						.getPoids().toString()));
			}
			if (model.getZone() != null && !model.getZone().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "zone", "string", model
						.getZone().toString()));
			}
			if (model.getCommune() != null && !model.getCommune().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "commune", "string", model
						.getCommune().toString()));
			}
			if (model.getDepartement() != null
					&& !model.getDepartement().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "departement", "string",
						model.getDepartement().toString()));
			}
			if (model.getCreatedBy() != null && !model.getCreatedBy().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "createdBy", "string",
						model.getCreatedBy().toString()));
			}
			if (model.getCreatedOn() != null) {
				wrapper.addFilter(new XFilter("eq", "createdOn", "string",
						model.getCreatedOn().toString()));
			}
			if (model.getUpdatedBy() != null && !model.getUpdatedBy().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "updatedBy", "string",
						model.getUpdatedBy().toString()));
			}
			if (model.getUpdatedOn() != null) {
				wrapper.addFilter(new XFilter("eq", "updatedOn", "string",
						model.getUpdatedOn().toString()));
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
	protected void updateView(ArrayList<Patient> models) {
		view.updateView(models);
	}

	@Override
	protected ModelBase updateModel(ModelBase model) {
		return view.updateModel((Patient) model);
	}

	@Override
	protected void dataChanged(boolean reloadDetails,
			boolean setBtnsPersistVisible) {
		view.onDataChanged(model, reloadDetails, setBtnsPersistVisible);
	}

	@Override
	protected void initModel() {
		if (model == null) {
			model = new Patient();
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
			Patient model = new Patient();
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
			ArrayList<Patient> models = new ArrayList<Patient>();
			Iterator<?> record = listStore.getModifiedRecords().iterator();
			while (record.hasNext()) {
				@SuppressWarnings("rawtypes")
				Record rec = (Record) record.next();
				rec.commit(true);
				Patient model = (Patient) rec.getModel();
				if ("INSERT".equalsIgnoreCase(model.getSTATUS())
						|| "MERGE".equalsIgnoreCase(model.getSTATUS())
						|| "REMOVE".equalsIgnoreCase(model.getSTATUS())) {
					try {
						if (!"REMOVE".equalsIgnoreCase(model.getSTATUS())) {
							model.validateModel();
						}
						models.add(model);
					} catch (ModelException ex) {
						Patient dummy = listStore.findModelWithKey(listStore
								.getKeyProvider().getKey(model));
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
						Patient dummy = listStore.findModelWithKey(listStore
								.getKeyProvider().getKey(model));
						dummy.merge(model);
						model.setErrorMessage(new SystemMessage(I18NMessages
								.getMessages().unexpectedError_insertRecord()));
					}
				}
			}
			setModelsToBePersisted(models);
		}
	}

	public void onExecutePatient(ActionCommand action,
			UserRequestCallback<ModelBase> callback) {
		this.performAction(action, callback);
	}
}