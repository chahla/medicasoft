package com.rsoft.medicasoft.client.presenter;

/*
 Templates Generator
 */
/*@Author=Jean Louidort*/
/*@Generation Date=Mon Oct 14 22:30:48 EDT 2013*/
/*@Version=1.0*/
import java.util.ArrayList;
import java.util.Iterator;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mvp4g.client.annotation.Presenter;
import com.rsoft.medicasoft.client.UserRequestCallback;
import com.rsoft.medicasoft.client.view.PaysView;
import com.rsoft.medicasoft.client.view.viewinterface.IView;
import com.rsoft.medicasoft.client.view.viewinterface.IView.IPresenterBase;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.FilterWrapper;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.ModelException;
import com.rsoft.medicasoft.shared.SystemMessage;
import com.rsoft.medicasoft.shared.XFilter;
import com.rsoft.medicasoft.shared.i18n.I18NMessages;
import com.rsoft.medicasoft.shared.model.Pays;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.Store.Record;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

@Presenter(view = PaysView.class, multiple = true)
public class PaysPresenter extends PresenterBase<IView<Pays>, Pays> implements
		IPresenterBase {

	@Override
	public FilterWrapper buildFilters(ModelBase value) {
		Pays model = (Pays) value;
		FilterWrapper wrapper = new FilterWrapper();
		if (model != null) {
			if (model.getCode() != null && !model.getCode().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "code", "string", model
						.getCode().toString()));
			}
			if (model.getNom() != null && !model.getNom().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "nom", "string", model
						.getNom().toString()));
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
			if (model.getCreatedBy() != null && !model.getCreatedBy().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "createdBy", "string",
						model.getCreatedBy().toString()));
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
	protected void updateView(ArrayList<Pays> models) {
		view.updateView(models);
	}

	@Override
	protected ModelBase updateModel(ModelBase model) {
		return view.updateModel((Pays) model);
	}

	@Override
	protected void dataChanged(boolean reloadDetails,
			boolean setBtnsPersistVisible) {
		view.onDataChanged(model, reloadDetails, setBtnsPersistVisible);
	}

	@Override
	protected void initModel() {
		if (model == null) {
			model = new Pays();
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
			Pays model = new Pays();
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
	public void setModelsToBePersisted(ListStore<Pays> listStore) {
		if (listStore != null) {
			ArrayList<Pays> models = new ArrayList<Pays>();
			Iterator<?> record = listStore.getModifiedRecords().iterator();
			while (record.hasNext()) {
				@SuppressWarnings("rawtypes")
				Record rec = (Record) record.next();
				rec.commit(true);
				Pays model = (Pays) rec.getModel();
				if ("INSERT".equalsIgnoreCase(model.getSTATUS())
						|| "MERGE".equalsIgnoreCase(model.getSTATUS())
						|| "REMOVE".equalsIgnoreCase(model.getSTATUS())) {
					try {
						if (!"REMOVE".equalsIgnoreCase(model.getSTATUS())) {
							model.validateModel();
						}
						models.add(model);
					} catch (ModelException ex) {
						Pays dummy = listStore.findModelWithKey(listStore
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
						Pays dummy = listStore.findModelWithKey(listStore
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

	public void onExecutePays(ActionCommand action,
			UserRequestCallback<ModelBase> callback) {
		this.performAction(action, callback);
	}
}