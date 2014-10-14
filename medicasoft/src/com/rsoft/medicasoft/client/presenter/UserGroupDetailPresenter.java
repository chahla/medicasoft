package com.rsoft.medicasoft.client.presenter;

/*
 Templates Generator
 */
/*@Author=Jean Louidort*/
/*@Generation Date=Thu Sep 12 22:20:53 EDT 2013*/
/*@Version=1.0*/
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mvp4g.client.annotation.Presenter;
import com.rsoft.medicasoft.client.UserRequestCallback;
import com.rsoft.medicasoft.client.view.UserGroupDetailCView;
import com.rsoft.medicasoft.client.view.viewinterface.IView;
import com.rsoft.medicasoft.client.view.viewinterface.IView.IPresenterBase;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.DataException;
import com.rsoft.medicasoft.shared.FilterWrapper;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.ModelException;
import com.rsoft.medicasoft.shared.SystemMessage;
import com.rsoft.medicasoft.shared.XFilter;
import com.rsoft.medicasoft.shared.i18n.I18NMessages;
import com.rsoft.medicasoft.shared.model.EntityKeyDescriptor;
import com.rsoft.medicasoft.shared.model.UserGroup;
import com.rsoft.medicasoft.shared.model.UserGroupDetail;
import com.sencha.gxt.data.shared.Store.Record;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

@Presenter(view = UserGroupDetailCView.class, multiple = true)
public class UserGroupDetailPresenter extends
		PresenterBase<IView<UserGroupDetail>, UserGroupDetail> implements
		IPresenterBase {
	private UserGroup headerModel;

	@Override
	public FilterWrapper buildFilters(ModelBase value) {
		FilterWrapper wrapper = new FilterWrapper();
		return wrapper;
	}

	/**
	 * @param UserGroup
	 *            the UserGroup to set
	 */
	public void onChangeHeaderUserGroupDetail(UserGroup headerModel) {
		this.setHeaderModel(headerModel);
	}

	protected boolean beforePersist(UserGroupDetail model) {
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
	protected void updateView(ArrayList<UserGroupDetail> models) {
		view.updateView(models);
	}

	@Override
	protected ModelBase updateModel(ModelBase model) {
		return view.updateModel((UserGroupDetail) model);
	}

	@Override
	protected void dataChanged(boolean reloadDetails,
			boolean setBtnsPersistVisible) {
		view.onDataChanged(model, reloadDetails, setBtnsPersistVisible);
	}

	@Override
	protected void initModel() {
		if (model == null) {
			model = new UserGroupDetail();
		}
	}

	@Override
	public void onSearchHandled(ModelBase _model, PagingLoadConfig loadConfig,
			AsyncCallback<PagingLoadResult<ModelBase>> callback) {
		if (filters == null) {
			filters = new FilterWrapper();
		}
		if (_model == null) {
			boolean t = beforeSearch(filters);
			if (!t) {
				view.onActionFailure(new DataException(I18NMessages
						.getMessages().feedHeaderFirst()), null);
				return;
			}
		}
		List<UserGroupDetail> datas = this.headerModel.getUserGroupDetails();
		int start = loadConfig.getOffset();
		int limit = datas.size();
		if (loadConfig.getLimit() > 0) {
			limit = Math.min(start + loadConfig.getLimit(), limit);
		}
		PagingLoadResult<ModelBase> pagingLoadResult = new PagingLoadResultBean<ModelBase>(
				new ArrayList<ModelBase>(datas), datas.size(),
				loadConfig.getOffset());
		callback.onSuccess(pagingLoadResult);
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
			UserGroupDetail model = new UserGroupDetail();
			model.setLineNo(headerModel != null ? headerModel
					.getUserGroupDetails().size() + i : i); //Ajouter pour embeded model
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
			ArrayList<UserGroupDetail> models = new ArrayList<UserGroupDetail>();
			Iterator<?> record = listStore.getModifiedRecords().iterator();
			while (record.hasNext()) {
				@SuppressWarnings("rawtypes")
				Record rec = (Record) record.next();
				rec.commit(true);
				UserGroupDetail model = (UserGroupDetail) rec.getModel();
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
						UserGroupDetail dummy = listStore
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
						UserGroupDetail dummy = listStore
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

	public void onExecuteUserGroupDetail(ActionCommand action,
			UserRequestCallback<ModelBase> callback) {
		this.performAction(action, callback);
	}

	public UserGroup getHeaderModel() {
		return headerModel;
	}

	public void setHeaderModel(UserGroup headerModel) {
		this.headerModel = headerModel;
		if (this.headerModel != null) {
			this.entityKeyDescriptor = null;
			this.entityKeyDescriptor = new EntityKeyDescriptor("UserGroup",
					"UserGroupDetail", "setUserGroupId",
					headerModel.getEntityId());
			this.entityKeyDescriptor.setRef(true);
		} else {
			this.entityKeyDescriptor = null;
		}
	}

	public void setFilters(FilterWrapper filters) {
		super.setFilters(filters);
		if (filters != null) {
			filters.addFilter(new XFilter("userGroupId", headerModel));
		}
	}
}