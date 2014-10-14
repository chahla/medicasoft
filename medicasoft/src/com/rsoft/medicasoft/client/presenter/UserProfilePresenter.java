package com.rsoft.medicasoft.client.presenter;

/*
 Templates Generator
 */
/*@Author=Jean Louidort*/
/*@Generation Date=Tue Oct 08 19:46:11 EDT 2013*/
/*@Version=1.0*/
import java.util.ArrayList;
import java.util.Iterator;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mvp4g.client.annotation.Presenter;
import com.rsoft.medicasoft.client.UserRequestCallback;
import com.rsoft.medicasoft.client.view.UserProfileView;
import com.rsoft.medicasoft.client.view.viewinterface.IView;
import com.rsoft.medicasoft.client.view.viewinterface.IView.IPresenterBase;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.FilterWrapper;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.ModelException;
import com.rsoft.medicasoft.shared.SystemMessage;
import com.rsoft.medicasoft.shared.XFilter;
import com.rsoft.medicasoft.shared.i18n.I18NMessages;
import com.rsoft.medicasoft.shared.model.UserProfile;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.Store.Record;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

@Presenter(view = UserProfileView.class, multiple = true)
public class UserProfilePresenter extends
		PresenterBase<IView<UserProfile>, UserProfile> implements
		IPresenterBase {

	@Override
	public FilterWrapper buildFilters(ModelBase value) {
		UserProfile model = (UserProfile) value;
		FilterWrapper wrapper = new FilterWrapper();
		if (model != null) {
			if (model.getUserId() != null && !model.getUserId().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "userId", "string", model
						.getUserId().toString()));
			}
			if (model.getTitle() != null && !model.getTitle().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "title", "string", model
						.getTitle().toString()));
			}
			if (model.getFirstName() != null && !model.getFirstName().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "firstName", "string",
						model.getFirstName().toString()));
			}
			if (model.getLastName() != null && !model.getLastName().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "lastName", "string", model
						.getLastName().toString()));
			}
			if (model.getStatus() != null && !model.getStatus().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "status", "string", model
						.getStatus().toString()));
			}
			if (model.getSex() != null && !model.getSex().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "sex", "string", model
						.getSex().toString()));
			}
			if (model.getLanguage() != null && !model.getLanguage().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "language", "string", model
						.getLanguage().toString()));
			}
			if (model.getInstitutionId() != null) {
				wrapper.addFilter(new XFilter("eq", "institutionId", "string",
						model.getInstitutionId().toString()));
			}
			if (model.getUserGroupId() != null) {
				wrapper.addFilter(new XFilter("eq", "userGroupId", "string",
						model.getUserGroupId().toString()));
			}
			if (model.getPinCode() != null && !model.getPinCode().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "pinCode", "string", model
						.getPinCode().toString()));
			}
			if (model.getExpirationDate() != null) {
				wrapper.addFilter(new XFilter("eq", "expirationDate", "string",
						model.getExpirationDate().toString()));
			}
			if (model.getEmail() != null && !model.getEmail().isEmpty()) {
				wrapper.addFilter(new XFilter("eq", "email", "string", model
						.getEmail().toString()));
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
	protected void updateView(ArrayList<UserProfile> models) {
		view.updateView(models);
	}

	@Override
	protected ModelBase updateModel(ModelBase model) {
		return view.updateModel((UserProfile) model);
	}

	@Override
	protected void dataChanged(boolean reloadDetails,
			boolean setBtnsPersistVisible) {
		view.onDataChanged(model, reloadDetails, setBtnsPersistVisible);
	}

	@Override
	protected void initModel() {
		persistenceManagerPrefix = "UserProfile";
		if (model == null) {
			model = new UserProfile();
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
			UserProfile model = new UserProfile();
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
	public void setModelsToBePersisted(ListStore<UserProfile> listStore) {
		if (listStore != null) {
			ArrayList<UserProfile> models = new ArrayList<UserProfile>();
			Iterator<?> record = listStore.getModifiedRecords().iterator();
			while (record.hasNext()) {
				@SuppressWarnings("rawtypes")
				Record rec = (Record) record.next();
				rec.commit(true);
				UserProfile model = (UserProfile) rec.getModel();
				if ("INSERT".equalsIgnoreCase(model.getSTATUS())
						|| "MERGE".equalsIgnoreCase(model.getSTATUS())
						|| "REMOVE".equalsIgnoreCase(model.getSTATUS())) {
					try {
						if (!"REMOVE".equalsIgnoreCase(model.getSTATUS())) {
							model.validateModel();
						}
						models.add(model);
					} catch (ModelException ex) {
						UserProfile dummy = listStore
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
						UserProfile dummy = listStore
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

	public void onExecuteUserProfile(ActionCommand action,
			UserRequestCallback<ModelBase> callback) {
		this.performAction(action, callback);
	}
 }