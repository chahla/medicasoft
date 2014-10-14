package com.rsoft.medicasoft.client.presenter;

/*
 Templates Generator
 */
/*@Author=Jean Louidort*/
/*@Generation Date=Sat Oct 26 19:38:26 EDT 2013*/
/*@Version=1.0*/
import java.util.ArrayList;
import java.util.Iterator;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mvp4g.client.annotation.Presenter;
import com.rsoft.medicasoft.client.UserRequestCallback;
import com.rsoft.medicasoft.client.view.ParamInoutView;
import com.rsoft.medicasoft.client.view.viewinterface.IView;
import com.rsoft.medicasoft.client.view.viewinterface.IView.IPresenterBase;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.FilterWrapper;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.ModelException;
import com.rsoft.medicasoft.shared.SystemMessage;
import com.rsoft.medicasoft.shared.XFilter;
import com.rsoft.medicasoft.shared.i18n.I18NMessages;
import com.rsoft.medicasoft.shared.model.ParamInout;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.Store.Record;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

@Presenter(view = ParamInoutView.class, multiple = true)
public class ParamInoutPresenter extends
		PresenterBase<IView<ParamInout>, ParamInout> implements IPresenterBase {

	@Override
	public FilterWrapper buildFilters(ModelBase value) {
		ParamInout model = (ParamInout) value;
		FilterWrapper wrapper = new FilterWrapper();
		if (model != null) {
			if (model.getContenu() != null && !model.getContenu().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "contenu", "string", model
						.getContenu().toString()));
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
			if (model.getCreatedOn() != null) {
				wrapper.addFilter(new XFilter("eq", "createdOn", "string",
						model.getCreatedOn().toString()));
			}
			if (model.getDescription() != null
					&& !model.getDescription().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "description", "string",
						model.getDescription().toString()));
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
	protected void updateView(ArrayList<ParamInout> models) {
		view.updateView(models);
	}

	@Override
	protected ModelBase updateModel(ModelBase model) {
		return view.updateModel((ParamInout) model);
	}

	@Override
	protected void dataChanged(boolean reloadDetails,
			boolean setBtnsPersistVisible) {
		view.onDataChanged(model, reloadDetails, setBtnsPersistVisible);
	}

	@Override
	protected void initModel() {
		if (model == null) {
			model = new ParamInout();
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
			ParamInout model = new ParamInout();
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
	public void setModelsToBePersisted(ListStore<ParamInout> listStore) {
		if (listStore != null) {
			ArrayList<ParamInout> models = new ArrayList<ParamInout>();
			Iterator<?> record = listStore.getModifiedRecords().iterator();
			while (record.hasNext()) {
				@SuppressWarnings("rawtypes")
				Record rec = (Record) record.next();
				rec.commit(true);
				ParamInout model = (ParamInout) rec.getModel();
				if ("INSERT".equalsIgnoreCase(model.getSTATUS())
						|| "MERGE".equalsIgnoreCase(model.getSTATUS())
						|| "REMOVE".equalsIgnoreCase(model.getSTATUS())) {
					try {
						if (!"REMOVE".equalsIgnoreCase(model.getSTATUS())) {
							model.validateModel();
						}
						models.add(model);
					} catch (ModelException ex) {
						ParamInout dummy = listStore.findModelWithKey(listStore
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
						ParamInout dummy = listStore.findModelWithKey(listStore
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

	public void onExecuteParamInout(ActionCommand action,
			UserRequestCallback<ModelBase> callback) {
		this.performAction(action, callback);
	}
}