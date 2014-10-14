package com.rsoft.medicasoft.client.presenter;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.mvp4g.client.presenter.BasePresenter;
import com.rsoft.medicasoft.client.RSOFTEventBus;
import com.rsoft.medicasoft.client.UserRequestCallback;
import com.rsoft.medicasoft.client.service.EasyGwtRpcServiceAsync;
import com.rsoft.medicasoft.client.view.viewinterface.IPresenter;
import com.rsoft.medicasoft.client.view.viewinterface.IView;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.DataException;
import com.rsoft.medicasoft.shared.FilterWrapper;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.ModelException;
import com.rsoft.medicasoft.shared.XFilter;
import com.rsoft.medicasoft.shared.i18n.I18NMessages;
import com.rsoft.medicasoft.shared.model.EntityKeyDescriptor;
import com.rsoft.medicasoft.shared.model.RequestDescriptor;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

public abstract class PresenterBase<V extends IView<M>, M extends ModelBase>
		extends BasePresenter<V, RSOFTEventBus> implements IPresenter {
	protected RequestDescriptor requestDescriptor;
	protected String persistenceManagerPrefix;
	protected int index = 0;
	protected FilterWrapper filters;
	protected PersistingCallback persitingCallback;
	protected ArrayList<String> actionRights;
	protected M model;
	protected ArrayList<M> modelsToBePersisted;
	protected ArrayList<M> modelsToBeRemoved;
	protected ArrayList<M> models;
	protected UserRequestCallback<ModelBase> callback;
	private ArrayList<String> actionDenied = new ArrayList<String>();
	private boolean updatePending;
	private String headerTitle;
	private boolean searchPending;
	private ArrayList<String> zonesUpdatedList;
	protected EntityKeyDescriptor entityKeyDescriptor;
	@Inject
	protected EasyGwtRpcServiceAsync service;
	protected ListStore<M> listStore;

	public ListStore<M> getListStore() {
		return listStore;
	}

	public void setListStore(ListStore<M> listStore) {
		this.listStore = listStore;
	}

	public void addUpdatedZone(String title) {
		if (title != null) {
			if (zonesUpdatedList == null) {
				zonesUpdatedList = new ArrayList<String>();
			}
			if (!zonesUpdatedList.contains(title)) {
				zonesUpdatedList.add(title);
			}
		}
	}

	public void removeUpdatedZone(String title) {
		if (title != null && zonesUpdatedList != null) {
			zonesUpdatedList.remove(title);
		}
	}

	public boolean hasUpdatedZone() {
		return zonesUpdatedList != null && zonesUpdatedList.size() > 0;
	}

	/**
	 * @return the zonesUpdatedList
	 */
	public ArrayList<String> getZonesUpdatedList() {
		return zonesUpdatedList;
	}

	/**
	 * @param zonesUpdatedList
	 *            the zonesUpdatedList to set
	 */
	public void setZonesUpdatedList(ArrayList<String> zonesUpdatedList) {
		this.zonesUpdatedList = zonesUpdatedList;
	}

	/**
	 * @return the searchPending
	 */
	public boolean isSearchPending() {
		return searchPending;
	}

	/**
	 * @param searchPending
	 *            the searchPending to set
	 */
	public void setSearchPending(boolean searchPending) {
		this.searchPending = searchPending;
	}

	/**
	 * @return the headerTitle
	 */
	public String getHeaderTitle() {
		return headerTitle;
	}

	/**
	 * @param headerTitle
	 *            the headerTitle to set
	 */
	public void setHeaderTitle(String headerTitle) {
		this.headerTitle = headerTitle;
	}

	/**
	 * @return the actionRights
	 */
	public ArrayList<String> getActionDenied() {
		return actionDenied;
	}

	/**
	 * @return the updatePending
	 */
	public boolean isUpdatePending() {
		return updatePending;
	}

	/**
	 * @param updatePending
	 *            the updatePending to set
	 */
	public void setUpdatePending(boolean updatePending) {
		this.updatePending = updatePending;
	}

	/**
	 * @param actionRights
	 *            the actionRights to set
	 */
	public void setActionDenied(ArrayList<String> actionDenied) {
		this.actionDenied = actionDenied;
	}

	protected void beforeSubmit(final String action) throws DataException {
		if (this.actionDenied != null && this.actionDenied.contains(action)) {
			if ("insert".equalsIgnoreCase(action)) {
			}
			if ("merge".equalsIgnoreCase(action)) {
			}
			if ("vizualize".equalsIgnoreCase(action)) {
			}
			if ("remove".equalsIgnoreCase(action)) {
			}
			if ("post".equalsIgnoreCase(action)) {
			}
			if ("validate".equalsIgnoreCase(action)) {
			}
			if ("print".equalsIgnoreCase(action)) {
			}
			if ("void".equalsIgnoreCase(action)) {
			}
			if ("finalize".equalsIgnoreCase(action)) {
			}
			if ("generate".equalsIgnoreCase(action)) {
			}
		}
	}

	/**
	 * @return the actionRights
	 */
	public ArrayList<String> getActionRights() {
		return actionRights;
	}

	/**
	 * @param actionRights
	 *            the actionRights to set
	 */
	public void setActionRights(ArrayList<String> actionRights) {
		this.actionRights = actionRights;
	}

	/**
	 * @return the persitingCallback
	 */
	public PersistingCallback getPersitingCallback() {
		return persitingCallback;
	}

	/**
	 * @param persitingCallback
	 *            the persitingCallback to set
	 */
	public void setPersitingCallback(PersistingCallback persitingCallback) {
		this.persitingCallback = persitingCallback;
	}

	// Les cles d'entete, elles doivent toujours participer aux recherches comme
	// criteres
	/**
	 * @return the filters
	 */
	public FilterWrapper getFilters() {
		return filters;
	}

	/**
	 * @param filters
	 *            the filters to set
	 */
	public void setFilters(FilterWrapper filters) {
		this.filters = filters;
	}

	public M getModel() {
		this.initModel();
		return model;
	}

	/**
	 * @return the modelsToBeRemoved
	 */
	public ArrayList<M> getModelsToBeRemoved() {
		return modelsToBeRemoved;
	}

	/**
	 * @param modelsToBeRemoved
	 *            the modelsToBeRemoved to set
	 */
	public void setModelsToBeRemoved(ArrayList<M> modelsToBeRemoved) {
		this.modelsToBeRemoved = modelsToBeRemoved;
	}

	/**
	 * @param modelsToBeRemoved
	 *            the modelsToBeRemoved to set
	 */
	public void addModelToBeRemoved(M model) {
		if (this.modelsToBeRemoved == null) {
			this.modelsToBeRemoved = new ArrayList<M>();
		}
		this.modelsToBeRemoved.add(model);
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return the index
	 */
	public int getTotalRecords() {
		return models != null ? models.size() : 0;
	}

	@Override
	public void bind() {
	}

	public void onStart() {
	}

	@Override
	public void onFirstHandled() throws DataException {
		if (models == null || models.isEmpty()) {
			throw new DataException(I18NMessages.getMessages().noDataSelected());
		} else if (index == 0) {
			throw new DataException(I18NMessages.getMessages()
					.firstRecordAlreadyReached());
		} else {
			index = 0;
			this.model = models.get(index);
			updateView();
			changeDetails();
		}
	}

	@Override
	public void onLastHandled() throws DataException {
		if (models == null || models.isEmpty()) {
			throw new DataException(I18NMessages.getMessages().noDataSelected());
		} else if (index == (models.size() - 1)) {
			throw new DataException(I18NMessages.getMessages()
					.lastRecordReached());
		} else if (!models.isEmpty()) {
			index = models.size() - 1;
			this.model = models.get(index);
			updateView();
			changeDetails();
		}
	}

	@Override
	public void onNextHandled() throws DataException {
		if (models == null || models.isEmpty()) {
			throw new DataException(I18NMessages.getMessages().noDataSelected());
		} else if (index == (models.size() - 1)) {
			throw new DataException(I18NMessages.getMessages()
					.lastRecordReached());
		} else if (!models.isEmpty()) {
			this.model = models.get(++index);
			updateView();
			changeDetails();
		}
	}

	@Override
	public void onPreviousHandled() throws DataException {
		if (models == null || models.isEmpty()) {
			throw new DataException(I18NMessages.getMessages().noDataSelected());
		} else if (index == 0) {
			throw new DataException(I18NMessages.getMessages()
					.firstRecordAlreadyReached());
		} else if (!models.isEmpty()) {
			this.model = models.get(--index);
			updateView();
			changeDetails();
		}
	}

	/**
	 * @param modelsToBeRemoved
	 *            the modelsToBeRemoved to set
	 */
	public void clearModelsToBeRemoved() {
		if (this.modelsToBeRemoved != null) {
			this.modelsToBeRemoved.clear();
		}
	}

	/**
	 * @param modelsToBeRemoved
	 *            the modelsToBeRemoved to set
	 */
	public void removeModelToBeRemoved(ModelBase model) {
		if (this.modelsToBeRemoved != null) {
			this.modelsToBeRemoved.remove((ModelBase) model);
		}
	}

	/**
	 * @return the modelsToBePersisted
	 */
	public ArrayList<M> getModelsToBePersisted() {
		return modelsToBePersisted;
	}

	/**
	 * @param modelsToBePersisted
	 *            the modelsToBePersisted to set
	 */
	public void setModelsToBePersisted(ArrayList<M> modelsToBePersisted) {
		this.modelsToBePersisted = modelsToBePersisted;
	}

	@Override
	public void onLoadHandled() throws DataException {
		// TODO Auto-generated method stub
	}

	@Override
	public void onPrintHandled() throws DataException {
		// TODO Auto-generated method stub
	}

	@Override
	public void onExtractHandled() throws DataException {
		// TODO Auto-generated method stub
	}

	@Override
	public void onRefreshHandled() throws DataException {
		this.resetModel();
		this.model.setUpdating(true);
		updateView();
		index = 0;
	}

	@Override
	public void onCriteriaHandled() throws DataException {
		this.resetModel();
		updateView();
		index = 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onSearchHandled(ModelBase _model) throws DataException {
		if (service != null) {
			try {
				beforeSubmit("vizualize");
				if (filters == null) {
					filters = new FilterWrapper();
				}
				if (_model != null) {
				} else {
					model = (M) updateModel((ModelBase) model);
				}
				if (!beforeSearch(filters)) {
					view.onActionFailure(new DataException(I18NMessages
							.getMessages().feedHeaderFirst()), null);
					return;
				}

				service.search(requestDescriptor, filters,
						new AsyncCallback<ArrayList<ModelBase>>() {
							@Override
							public void onFailure(Throwable caught) {
								if (caught.toString().toLowerCase()
										.contains("connectionexception")) {
									getEventBus().logout();
								} else {
									view.onActionFailure(caught, null);
								}
							}

							@Override
							public void onSuccess(ArrayList<ModelBase> result) {
								if (models != null) {
									models.clear();
									models = null;
								}
								models = (ArrayList<M>) result;
								if (models != null && !models.isEmpty()) {
									index = 0;
									model = models.get(index);
									changeDetails();
									updateView();
								} else {
									index = -1;
									if (models != null) {
										models.clear();
									}
									if (modelsToBePersisted != null) {
										modelsToBePersisted.clear();
									}
									if (modelsToBeRemoved != null) {
										modelsToBeRemoved.clear();
									}
									view.onActionFailure(null, I18NMessages
											.getMessages().noDataFound());
								}
							}
						});
			} catch (Exception ex) {
				ex.printStackTrace(System.out);
				view.onActionFailure(null, (I18NMessages.getMessages()
						.unexpectedError_searchRecords()));
			}
		} else {
			view.onActionFailure(null, I18NMessages.getMessages()
					.internalErrorNoServiceFound());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onSaveHandled() throws DataException {
		if (service != null) {
			try {
				beforeSubmit("insert");
				initModel();
				model.setUpdating(true);
				model = (M) updateModel((ModelBase) model);
				if (model != null) {
					if (model.isUpdatePending()) {
						model.setSTATUS("INSERT");
						model.validateModel();
						service.persist(requestDescriptor, entityKeyDescriptor,
								model, new AsyncCallback<ModelBase>() {
									@Override
									public void onFailure(Throwable caught) {
										if (caught
												.toString()
												.toLowerCase()
												.contains("connectionexception")) {
											getEventBus().logout();
										} else {
											view.onActionFailure(caught, null);
										}
									}

									@Override
									public void onSuccess(ModelBase result) {
										if (persitingCallback != null) {
											persitingCallback.onSuccess(result,
													"insertion");
										}
										if (models == null) {
											models = (ArrayList<M>) new ArrayList<ModelBase>();
										}

										model = (M) result;
										updateView();
										model.setSTATUS("IGNORE");
										models.add(model);
										index = models.size() - 1;
										dataChanged(false, true);
										view.onPersistenceSuccessed();
									}
								});
					} else {
						dataChanged(false, true);
						view.onPersistenceSuccessed();
					}
				} else {
					throw new DataException(I18NMessages.getMessages()
							.unexpectedError_nullModel());
				}
			} catch (ModelException ex) {
				ex.printStackTrace(System.out);
				if ("MISSING_FIELDS".equalsIgnoreCase(ex.getCode())) {
					view.onActionFailure(new DataException(I18NMessages
							.getMessages().fill_allRequiredFields()), null);
					throw new DataException(I18NMessages.getMessages()
							.fill_allRequiredFields());

				} else {
					view.onActionFailure(new DataException(I18NMessages
							.getMessages().unexpectedError_insertRecord()),
							null);
					throw new DataException(I18NMessages.getMessages()
							.unexpectedError_insertRecord());
				}
			} catch (Exception ex) {
				ex.printStackTrace(System.out);
				view.onActionFailure(new DataException(I18NMessages
						.getMessages().unexpectedError_insertRecord()), null);
				throw new DataException(I18NMessages.getMessages()
						.unexpectedError_insertRecord());
			}
		} else {
			view.onActionFailure(new DataException(I18NMessages.getMessages()
					.unexpectedError_service_null()), null);
			throw new DataException(I18NMessages.getMessages()
					.unexpectedError_service_null());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onMergeHandled() throws DataException {
		if (service != null) {
			try {
				beforeSubmit("merge");
				initModel();
				model.setUpdating(true);
				model = (M) updateModel((ModelBase) model);
				if (model != null) {
					if (model.isUpdatePending()) {
						model.setSTATUS("MERGE");
						model.validateModel();
						service.persist(requestDescriptor, entityKeyDescriptor,
								model, new AsyncCallback<ModelBase>() {
									@Override
									public void onFailure(Throwable caught) {
										if (caught
												.toString()
												.toLowerCase()
												.contains("connectionexception")) {
											getEventBus().logout();
										} else {
											view.onActionFailure(caught, null);
										}
									}

									@Override
									public void onSuccess(ModelBase result) {
										if (persitingCallback != null) {
											persitingCallback.onSuccess(result,
													"merging");
										}
										model = (M) result;
										updateView();
										model.setSTATUS("IGNORE");
										if (model != null) {
											int position = models
													.indexOf((ModelBase) model);
											models.remove((ModelBase) model);
											models.add(position, model);
											dataChanged(false, true);
											view.onPersistenceSuccessed();
										}
									}
								});
					} else {
						dataChanged(false, true);
						view.onPersistenceSuccessed();
					}
				} else {
					throw new DataException(I18NMessages.getMessages()
							.unexpectedError_nullModel());
				}
			} catch (ModelException ex) {
				ex.printStackTrace(System.out);
				if ("MISSING_FIELDS".equalsIgnoreCase(ex.getCode())) {
					view.onActionFailure(new DataException(I18NMessages
							.getMessages().fill_allRequiredFields()), null);
					throw new DataException(I18NMessages.getMessages()
							.fill_allRequiredFields());
				} else {
					view.onActionFailure(new DataException(I18NMessages
							.getMessages().unexpectedError_modifyRecord()),
							null);
					throw new DataException(I18NMessages.getMessages()
							.unexpectedError_modifyRecord());
				}
			} catch (Exception ex) {
				ex.printStackTrace(System.out);
				view.onActionFailure(new DataException(I18NMessages
						.getMessages().unexpectedError_modifyRecord()), null);
				throw new DataException(I18NMessages.getMessages()
						.unexpectedError_modifyRecord());
			}
		} else {
			view.onActionFailure(new DataException(I18NMessages.getMessages()
					.unexpectedError_service_null()), null);
			throw new DataException(I18NMessages.getMessages()
					.unexpectedError_service_null());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onRemoveHandled() throws DataException {
		if (service != null) {
			try {
				beforeSubmit("remove");
				model = (M) updateModel((ModelBase) model);
				if (model != null && model.getEntityId() != null) {
					model.setSTATUS("REMOVE");
					service.persist(requestDescriptor, entityKeyDescriptor,
							model, new AsyncCallback<ModelBase>() {
								@Override
								public void onFailure(Throwable caught) {
									if (caught.toString().toLowerCase()
											.contains("connectionexception")) {
										getEventBus().logout();
									} else {
										view.onActionFailure(caught, null);
									}
								}

								@Override
								public void onSuccess(ModelBase result) {

									if (model != null) {
										models.remove((ModelBase) model);
										index = models.size() > (index + 1) ? index
												: models.size() - 1;
									}
									index = -1;
									model = null;
									dataChanged(false, false);
									updateView();
								}
							});
				} else {
					view.onActionFailure(new DataException(I18NMessages
							.getMessages().unexpectedError_nullModel()), null);
					throw new DataException(I18NMessages.getMessages()
							.unexpectedError_nullModel());
				}
			} catch (Exception ex) {
				ex.printStackTrace(System.out);
				view.onActionFailure(new DataException(I18NMessages
						.getMessages().unexpectedError_removeRecord()), null);
				throw new DataException(I18NMessages.getMessages()
						.unexpectedError_removeRecord());
			}
		} else {
			view.onActionFailure(new DataException(I18NMessages.getMessages()
					.unexpectedError_service_null()), null);
			throw new DataException(I18NMessages.getMessages()
					.unexpectedError_service_null());
		}
	}

	@Override
	public void onAddEmptyValuesHandled(PagingLoadConfig loadConfig) {
	}

	/*
	 * * Cette methode permet de rechercher des enregistrements, elle s'applique
	 * aux grilles
	 */

	public <T extends ModelBase> void onColumnarSeachHandled(
			PagingLoadConfig loadConfig,
			AsyncCallback<PagingLoadResult<T>> callback) throws DataException {
		service.search(requestDescriptor, loadConfig, filters, callback);
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
		service.search(requestDescriptor, loadConfig, filters, callback);
	}

	/*
	 * * Cette methode permet d'executer un batch: ensemble de models a
	 * enregistrer, modifier et/ou supprimer, elle s'applique au grille
	 */
	@Override
	public void onExecuteHandled() throws DataException {
		if (service != null) {
			try {
				beforeSubmit("insert");
				if (this.modelsToBeRemoved != null
						&& !this.modelsToBeRemoved.isEmpty()) {
					if (this.modelsToBePersisted == null
							|| this.modelsToBePersisted.isEmpty()) {
						this.modelsToBePersisted = new ArrayList<M>();
					}
					this.modelsToBePersisted.addAll(this.modelsToBeRemoved);
				}
				if (this.modelsToBePersisted != null
						&& !this.modelsToBePersisted.isEmpty()) {
					for (M model : modelsToBePersisted) {
						if (!beforePersist(model)) {
							view.onActionFailure(new DataException(I18NMessages
									.getMessages().feedHeaderFirst()), null);
						}
					}

					// service.per
					service.persist(requestDescriptor, entityKeyDescriptor,
							modelsToBePersisted,
							new AsyncCallback<ArrayList<M>>() {
								@Override
								public void onFailure(Throwable caught) {
									if (caught.toString().toLowerCase()
											.contains("connectionexception")) {
										getEventBus().logout();
									} else {
										updateView(null);
										view.onActionFailure(caught, null);
									}
								}

								@Override
								public void onSuccess(ArrayList<M> result) {
									updateView((ArrayList<M>) result);
									for (@SuppressWarnings("unused")
									ModelBase returnedModel : result) {
										modelsToBePersisted.remove(result);
									}
								}
							});
				} else {
					updateView(null);
				}
			} catch (Exception ex) {
				ex.printStackTrace(System.out);
				updateView(null);
				throw new DataException(I18NMessages.getMessages()
						.unexpectedError_insertRecord());
			}
		} else {
			updateView(null);
			throw new DataException(I18NMessages.getMessages()
					.unexpectedError_service_null());
		}
	}

	@Override
	public void onChangeHandled(Integer record) {
		if (index >= 0 && models != null && models.size() > index) {
			this.index = record - 1;
			this.model = models.get(index);
			updateView();
		}
	}

	protected void resetModel() {
		model = null;
		initModel();
	}

	protected abstract void dataChanged(boolean reloadDetails,
			boolean setBtnsPersistVisible);

	protected abstract void changeDetails();

	protected abstract ModelBase updateModel(ModelBase model);

	protected abstract void updateView();

	protected abstract void updateView(ArrayList<M> models);

	protected abstract void initModel();

	public abstract FilterWrapper buildFilters(ModelBase model);

	protected boolean beforePersist(M model) {
		return true;
	}

	protected boolean beforeSearch(FilterWrapper filters) {
		return true;
	}

	/**
	 * @param modelsToBePersisted
	 *            the modelsToBePersisted to set
	 */

	public void performAction(ActionCommand action,
			UserRequestCallback<ModelBase> callback) {
		if (callback == null) {
			return;
		}
		if (callback.getRequestDescriptor() == null) {
			return;
		}

		this.requestDescriptor = callback.getRequestDescriptor();

		this.requestDescriptor
				.setPersistenceManagerPrefix(persistenceManagerPrefix);

		this.callback = callback;
		try {
			switch (action) {
			case INSERT:
				this.onSaveHandled();
				callback.onSingleOperationSuccessed((ModelBase) model);
				break;
			case MERGE:
				this.onMergeHandled();
				callback.onSingleOperationSuccessed((ModelBase) model);
				break;
			case REMOVE:
				this.onRemoveHandled();
				callback.onSingleOperationSuccessed((ModelBase) model);
				break;
			case SEARCH:
				this.onSearchHandled((ModelBase) callback.getModel());
				callback.onSingleOperationSuccessed((ModelBase) model);
				break;
			case COLUMNAR_SEARCH:
				if (callback.getModel() == null) {
					this.onResetGridHandled(
							callback.getPageSize() != null ? callback
									.getPageSize() : 10, callback
									.getLoadConfig(), callback
									.getAsyncCallback());
				}
				if (callback.isLov()) {
					filters = new FilterWrapper();
					if (callback.getRefModel() != null) {
						filters.addFilter(new XFilter(callback.getRefName(),
								callback.getRefModel()));
					}
				}
				this.onSearchHandled((ModelBase) callback.getModel(),
						callback.getLoadConfig(), callback.getAsyncCallback());
				callback.onSingleOperationSuccessed((ModelBase) model);
				break;
			case RESET_GRID:
				if (callback.getModel() == null) {
					this.onResetGridHandled(callback.getPageSize(),
							callback.getLoadConfig(),
							callback.getAsyncCallback());
				}
				callback.onSingleOperationSuccessed((ModelBase) model);
				break;
			case MULTIPLE:
				this.onExecuteHandled();
				callback.onSingleOperationSuccessed((ModelBase) model);
				break;
			case EXTRACT:
				this.onExtractHandled();
				callback.onSingleOperationSuccessed((ModelBase) model);
				break;
			case RESET:
				this.onRefreshHandled();
				callback.onSingleOperationSuccessed((ModelBase) model);
				break;
			case LOAD:
				this.onLoadHandled();
				callback.onSingleOperationSuccessed((ModelBase) model);
				break;
			case PRINT:
				this.onPrintHandled();
				callback.onSingleOperationSuccessed((ModelBase) model);
				break;
			case FIRST:
				this.onFirstHandled();
				callback.onSingleOperationSuccessed((ModelBase) model);
				break;
			case NEXT:
				this.onNextHandled();
				callback.onSingleOperationSuccessed((ModelBase) model);
				break;
			case LAST:
				this.onLastHandled();
				callback.onSingleOperationSuccessed((ModelBase) model);
				break;
			case PREVIOUS:
				this.onPreviousHandled();
				callback.onSingleOperationSuccessed((ModelBase) model);
				break;
			}
		} catch (DataException e) {
			callback.onSingleModelOperationFailed((ModelBase) model, e);
			e.printStackTrace();
			return;
		}
		// Bout de code à verifier
		/*
		 * try { if (action.equals(ActionCommand.MULTIPLE)) {
		 * this.onPreviousHandled();
		 * callback.onSingleOperationSuccessed((ModelBase)model); } } catch
		 * (DataException e) { e.printStackTrace(); }
		 */
	}
}
