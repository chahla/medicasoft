package com.rsoft.medicasoft.client.presenter;

/*
 Templates Generator
 */
/*@Author=Jean Louidort*/
/*@Generation Date=Mon Nov 18 01:14:07 EST 2013*/
/*@Version=1.0*/
import java.util.ArrayList;
import java.util.Iterator;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mvp4g.client.annotation.Presenter;
import com.rsoft.medicasoft.client.UserRequestCallback;
import com.rsoft.medicasoft.client.view.AssuranceView;
import com.rsoft.medicasoft.client.view.viewinterface.IView;
import com.rsoft.medicasoft.client.view.viewinterface.IView.IPresenterBase;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.FilterWrapper;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.ModelException;
import com.rsoft.medicasoft.shared.SystemMessage;
import com.rsoft.medicasoft.shared.XFilter;
import com.rsoft.medicasoft.shared.i18n.I18NMessages;
import com.rsoft.medicasoft.shared.model.EntityKeyDescriptor;
import com.rsoft.medicasoft.shared.model.Emploi;
import com.rsoft.medicasoft.shared.model.Assurance;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.Store.Record;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

@Presenter(view = AssuranceView.class, multiple = true)
public class AssurancePresenter extends
		PresenterBase<IView<Assurance>, Assurance> implements IPresenterBase {
	private Emploi headerModel;

	@Override
	public FilterWrapper buildFilters(ModelBase value) {
		Assurance model = (Assurance) value;
		FilterWrapper wrapper = new FilterWrapper();
		if (model != null) {
			if (model.getTypeAssurance() != null
					&& !model.getTypeAssurance().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "typeAssurance", "string",
						model.getTypeAssurance().toString()));
			}
			if (model.getDescription() != null
					&& !model.getDescription().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "description", "string",
						model.getDescription().toString()));
			}
			if (model.getDateEffective() != null) {
				wrapper.addFilter(new XFilter("eq", "dateEffective", "string",
						model.getDateEffective().toString()));
			}
			if (model.getStatut() != null && !model.getStatut().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "statut", "string", model
						.getStatut().toString()));
			}
			if (model.getDateStatut() != null) {
				wrapper.addFilter(new XFilter("eq", "dateStatut", "string",
						model.getDateStatut().toString()));
			}
			if (model.getMontant() != null) {
				wrapper.addFilter(new XFilter("eq", "montant", "string", model
						.getMontant().toString()));
			}
			if (model.getPatientId() != null) {
				wrapper.addFilter(new XFilter("eq", "patientId", "string",
						model.getPatientId().toString()));
			}
			if (model.getAssureurId() != null) {
				wrapper.addFilter(new XFilter("eq", "assureurId", "string",
						model.getAssureurId().toString()));
			}
			if (model.getEmploiId() != null) {
				wrapper.addFilter(new XFilter("eq", "emploiId", "string", model
						.getEmploiId().toString()));
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

	/**
	 * @param Emploi
	 *            the Emploi to set
	 */
	public void onChangeHeaderAssurance(Emploi headerModel) {
		this.setHeaderModel(headerModel);
	}

	protected boolean beforePersist(Assurance model) {
		if (this.entityKeyDescriptor == null || this.headerModel == null) {
			return false;
		}
		return true;
	}

	protected boolean beforeSearch(FilterWrapper filters) {
		if (this.view.isDetail()
				&& (this.entityKeyDescriptor == null || this.headerModel == null)) {
			return false;
		}
		return true;
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
	protected void updateView(ArrayList<Assurance> models) {
		view.updateView(models);
	}

	@Override
	protected ModelBase updateModel(ModelBase model) {
		return view.updateModel((Assurance) model);
	}

	@Override
	protected void dataChanged(boolean reloadDetails,
			boolean setBtnsPersistVisible) {
		view.onDataChanged(model, reloadDetails, setBtnsPersistVisible);
	}

	@Override
	protected void initModel() {
		if (model == null) {
			model = new Assurance();
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
			Assurance model = new Assurance();
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
	public void setModelsToBePersisted(ListStore<Assurance> listStore) {
		if (listStore != null) {
			ArrayList<Assurance> models = new ArrayList<Assurance>();
			Iterator<?> record = listStore.getModifiedRecords().iterator();
			while (record.hasNext()) {
				@SuppressWarnings("rawtypes")
				Record rec = (Record) record.next();
				rec.commit(true);
				Assurance model = (Assurance) rec.getModel();
				beforePersist(model);
				if ("INSERT".equalsIgnoreCase(model.getSTATUS())
						|| "MERGE".equalsIgnoreCase(model.getSTATUS())
						|| "REMOVE".equalsIgnoreCase(model.getSTATUS())) {
					try {
						if (!"REMOVE".equalsIgnoreCase(model.getSTATUS())) {
							model.validateModel();
						}
						models.add(model);
					} catch (ModelException ex) {
						Assurance dummy = listStore.findModelWithKey(listStore
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
						Assurance dummy = listStore.findModelWithKey(listStore
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

	public void onExecuteAssurance(ActionCommand action,
			UserRequestCallback<ModelBase> callback) {
		this.performAction(action, callback);
	}

	public Emploi getHeaderModel() {
		return headerModel;
	}

	public void setHeaderModel(Emploi headerModel) {
		this.headerModel = headerModel;
		if (this.headerModel != null) {
			this.entityKeyDescriptor = null;
			this.entityKeyDescriptor = new EntityKeyDescriptor("Emploi",
					"Assurance", "setEmploiId", headerModel.getEntityId());
			this.entityKeyDescriptor.setRef(true);
		} else {
			this.entityKeyDescriptor = null;
		}
	}

	public void setFilters(FilterWrapper filters) {
		super.setFilters(filters);
		if (filters != null) {
			filters.addFilter(new XFilter("emploiId", headerModel));
		}
	}
}