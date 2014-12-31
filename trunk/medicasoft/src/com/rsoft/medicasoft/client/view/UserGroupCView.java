package com.rsoft.medicasoft.client.view;

/*
 Robelkend Templates Generator
 */
/*@Author=Jean Louidort*/
/*@Generation Date=Tue Jul 23 23:39:16 EDT 2013*/
/*@Version=1.0*/
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.client.UserRequestCallbackAdapter;
import com.rsoft.medicasoft.client.presenter.UserGroupPresenter;
import com.rsoft.medicasoft.client.toolsbar.EditableGridToolsBar;
import com.rsoft.medicasoft.client.view.viewinterface.IPresenter;
import com.rsoft.medicasoft.client.view.viewinterface.IWidget;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.FilterWrapper;
import com.rsoft.medicasoft.shared.SystemMessage;
import com.rsoft.medicasoft.shared.model.RequestDescriptor;
import com.rsoft.medicasoft.shared.model.UserGroup;
import com.rsoft.medicasoft.shared.model.UserGroupPropertiesAccess;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.LayoutRegion;
import com.sencha.gxt.core.client.Style.Side;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.event.StoreRecordChangeEvent;
import com.sencha.gxt.data.shared.event.StoreRecordChangeEvent.StoreRecordChangeHandler;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfigBean;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.GridDropTarget;
import com.sencha.gxt.widget.core.client.event.BeforeStartEditEvent;
import com.sencha.gxt.widget.core.client.event.BeforeStartEditEvent.BeforeStartEditHandler;
import com.sencha.gxt.widget.core.client.event.CellClickEvent;
import com.sencha.gxt.widget.core.client.event.CellClickEvent.CellClickHandler;
import com.sencha.gxt.widget.core.client.event.CompleteEditEvent;
import com.sencha.gxt.widget.core.client.event.CompleteEditEvent.CompleteEditHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.MaxLengthValidator;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.GridViewConfig;
import com.sencha.gxt.widget.core.client.grid.RowNumberer;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;
import com.sencha.gxt.widget.core.client.grid.filters.DateFilter;
import com.sencha.gxt.widget.core.client.grid.filters.GridFilters;
import com.sencha.gxt.widget.core.client.grid.filters.StringFilter;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;

public class UserGroupCView extends ViewGridBase<UserGroup> {
	private UserGroupPresenter presenter;

	public interface Renderer extends ToolTipConfig.ToolTipRenderer<Object>,
			XTemplates {
		@Override
		@XTemplate(source = "help/userGroupTemplate.html")
		public SafeHtml renderToolTip(Object data);
	}

	private Renderer renderer = GWT.create(Renderer.class);

	interface GxtUiBinder extends UiBinder<Widget, UserGroupCView> {
	}

	@Override
	public void remove() {
		List<UserGroup> UserGroups = grid.getSelectionModel()
				.getSelectedItems();
		for (UserGroup UserGroup : UserGroups) {
			if (UserGroup.getEntityId() != null) {
				presenter.addModelToBeRemoved(UserGroup);
				UserGroup foundModel = listStore.findModelWithKey(listStore
						.getKeyProvider().getKey(UserGroup));
				if (foundModel != null) {
					foundModel.setSTATUS("REMOVE");
				}
			}
		}
		listStore.commitChanges();
		grid.getView().refresh(false);
	}

	@Override
	public int rechordChanged(int record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void ignore() {
		List<UserGroup> UserGroups = grid.getSelectionModel()
				.getSelectedItems();
		for (UserGroup UserGroup : UserGroups) {
			UserGroup foundModel = listStore.findModelWithKey(listStore
					.getKeyProvider().getKey(UserGroup));
			if (foundModel != null) {
				foundModel.setSTATUS("IGNORE");
			}
		}
		listStore.commitChanges();
		grid.getView().refresh(false);
	}

	@Override
	public boolean stopCurrentAction() {
		if (!presenter.isSearchPending() && presenter.isUpdatePending()
				&& !Window.confirm(messages.updatePendingContinue())) {
			return true;
		}
		return false;
	}

	protected void loadDatas() {
		try {
			RpcProxy<FilterPagingLoadConfig, PagingLoadResult<UserGroup>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<UserGroup>>() {
				@Override
				public void load(FilterPagingLoadConfig loadConfig,
						AsyncCallback<PagingLoadResult<UserGroup>> callback) {
					listStore.clear();
					listStore.commitChanges();
					FilterWrapper wrapper = new FilterWrapper(null);
					/*
					 * if (filters.getFilter("groupId") != null &&
					 * filters.getFilter("groupId").getFilterConfig() != null &&
					 * !filters.getFilter("groupId").getFilterConfig()
					 * .isEmpty()) { List<String> pkPath = new
					 * ArrayList<String>(); pkPath.add("userGroupPk");
					 * pkPath.add("groupId"); for (FilterConfig fc :
					 * filters.getFilter("groupId") .getFilterConfig()) { if
					 * (fc.getValue() != null) { wrapper.addFilter(new
					 * XFilter(fc .getComparison(), "groupId", fc .getType(),
					 * fc.getValue(), pkPath)); } } } if
					 * (filters.getFilter("institutionId") != null &&
					 * filters.getFilter("institutionId") .getFilterConfig() !=
					 * null && !filters.getFilter("institutionId")
					 * .getFilterConfig().isEmpty()) { List<String> pkPath = new
					 * ArrayList<String>(); pkPath.add("userGroupPk");
					 * pkPath.add("institutionId"); for (FilterConfig fc :
					 * filters.getFilter( "institutionId").getFilterConfig()) {
					 * if (fc.getValue() != null) { wrapper.addFilter(new
					 * XFilter(fc .getComparison(), "institutionId", fc
					 * .getType(), fc.getValue(), pkPath)); } } } if
					 * (filters.getFilter("type") != null &&
					 * filters.getFilter("type").getFilterConfig() != null &&
					 * !filters.getFilter("type").getFilterConfig() .isEmpty())
					 * { for (FilterConfig fc : filters.getFilter("type")
					 * .getFilterConfig()) { if (fc.getValue() != null) {
					 * wrapper.addFilter(new XFilter(fc .getComparison(),
					 * "type", fc.getType(), fc.getValue())); } } } if
					 * (filters.getFilter("description") != null &&
					 * filters.getFilter("description") .getFilterConfig() !=
					 * null && !filters.getFilter("description")
					 * .getFilterConfig().isEmpty()) { for (FilterConfig fc :
					 * filters.getFilter("description") .getFilterConfig()) { if
					 * (fc.getValue() != null) { wrapper.addFilter(new
					 * XFilter(fc .getComparison(), "description", fc
					 * .getType(), fc.getValue())); } } } if
					 * (filters.getFilter("createdBy") != null &&
					 * filters.getFilter("createdBy").getFilterConfig() != null
					 * && !filters.getFilter("createdBy")
					 * .getFilterConfig().isEmpty()) { for (FilterConfig fc :
					 * filters.getFilter("createdBy") .getFilterConfig()) { if
					 * (fc.getValue() != null) { wrapper.addFilter(new
					 * XFilter(fc .getComparison(), "createdBy", fc .getType(),
					 * fc.getValue())); } } } if (filters.getFilter("createdOn")
					 * != null &&
					 * filters.getFilter("createdOn").getFilterConfig() != null
					 * && !filters.getFilter("createdOn")
					 * .getFilterConfig().isEmpty()) { for (FilterConfig fc :
					 * filters.getFilter("createdOn") .getFilterConfig()) { if
					 * (fc.getValue() != null) { wrapper.addFilter(new
					 * XFilter(fc .getComparison(), "createdOn", fc .getType(),
					 * fc.getValue())); } } } if (filters.getFilter("updatedBy")
					 * != null &&
					 * filters.getFilter("updatedBy").getFilterConfig() != null
					 * && !filters.getFilter("updatedBy")
					 * .getFilterConfig().isEmpty()) { for (FilterConfig fc :
					 * filters.getFilter("updatedBy") .getFilterConfig()) { if
					 * (fc.getValue() != null) { wrapper.addFilter(new
					 * XFilter(fc .getComparison(), "updatedBy", fc .getType(),
					 * fc.getValue())); } } } if (filters.getFilter("updatedOn")
					 * != null &&
					 * filters.getFilter("updatedOn").getFilterConfig() != null
					 * && !filters.getFilter("updatedOn")
					 * .getFilterConfig().isEmpty()) { for (FilterConfig fc :
					 * filters.getFilter("updatedOn") .getFilterConfig()) { if
					 * (fc.getValue() != null) { wrapper.addFilter(new
					 * XFilter(fc .getComparison(), "updatedOn", fc .getType(),
					 * fc.getValue())); } } }
					 */
					presenter.setFilters(wrapper);
					UserRequestCallbackAdapter<UserGroup> urc = new UserRequestCallbackAdapter<UserGroup>();
					urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
					urc.setAsyncCallback(callback);
					urc.setLoadConfig(loadConfig);
					presenter.getEventBus().executeUserGroup(
							ActionCommand.COLUMNAR_SEARCH, urc);
					showInfoBanner(!userHideBannerInfo);
				}
			};
			pagingLoader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<UserGroup>>(
					proxy) {
				protected FilterPagingLoadConfig newLoadConfig() {
					return new FilterPagingLoadConfigBean();
				}
			};
			pagingLoader.setRemoteSort(true);
			pagingLoader
					.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, UserGroup, PagingLoadResult<UserGroup>>(
							listStore));
			grid.setLoader(pagingLoader);

			new Timer() {
				@Override
				public void run() {
					pagingLoader.load();
				}
			}.schedule(100);
			pagingLoader.setRemoteSort(true);
			toolsBar.bind(pagingLoader);
		} catch (Exception ex) {
			Window.alert(messages.unexpectedError_searchRecords());
		}
	}

	protected void resetForm() {
		RpcProxy<FilterPagingLoadConfig, PagingLoadResult<UserGroup>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<UserGroup>>() {
			@Override
			public void load(FilterPagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<UserGroup>> callback) {
				UserRequestCallbackAdapter<UserGroup> urc = new UserRequestCallbackAdapter<UserGroup>() {
					@Override
					public void onSingleOperationSuccessed(UserGroup model) {
					}

					@Override
					public void onSingleModelOperationFailed(UserGroup model,
							Throwable cause) {
					}
				};
				urc.setAsyncCallback(callback);
				urc.setLoadConfig(loadConfig);
				urc.setPageSize(toolsBar.getPageSize());
				urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
				presenter.getEventBus().executeUserGroup(
						ActionCommand.RESET_GRID, urc);
			}
		};
		pagingLoader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<UserGroup>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		pagingLoader.setRemoteSort(true);
		pagingLoader
				.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, UserGroup, PagingLoadResult<UserGroup>>(
						listStore));
		grid.setLoader(pagingLoader);

		new Timer() {
			@Override
			public void run() {
				pagingLoader.load();
			}
		}.schedule(100);
		userHideBannerInfo = true;
		showInfoBanner(!userHideBannerInfo);
		pagingLoader.setRemoteSort(true);
		toolsBar.bind(pagingLoader);
		formReset();
	}

	private static GxtUiBinder gxtUiBinder = GWT.create(GxtUiBinder.class);

	private PagingLoader<FilterPagingLoadConfig, PagingLoadResult<UserGroup>> pagingLoader = null;
	private RowNumberer<UserGroup> numberer;
	private GridFilters<UserGroup> filters;
	private Widget widget;

	@Override
	public Widget asWidget() {
		ENTITY_NAME = "UserGroup";
		UserGroupPropertiesAccess propertiesAccess = GWT
				.create(UserGroupPropertiesAccess.class);
		listStore = new ListStore<UserGroup>(propertiesAccess.key());
		this.presenter.setListStore(listStore);
		IdentityValueProvider<UserGroup> identity = new IdentityValueProvider<UserGroup>();
		numberer = new RowNumberer<UserGroup>(identity);
		list = new ArrayList<ColumnConfig<UserGroup, ?>>();
		List<ColumnConfig<UserGroup, ?>> list = new ArrayList<ColumnConfig<UserGroup, ?>>();

		ColumnConfig<UserGroup, String> typeColumn = new ColumnConfig<UserGroup, String>(
				propertiesAccess.type(), 20, messages.type());
		list.add(typeColumn);
		ColumnConfig<UserGroup, String> descriptionColumn = new ColumnConfig<UserGroup, String>(
				propertiesAccess.description(), 100, messages.description());
		list.add(descriptionColumn);
		ColumnConfig<UserGroup, String> createdByColumn = new ColumnConfig<UserGroup, String>(
				propertiesAccess.createdBy(), 30, "createdBy");
		list.add(createdByColumn);
		ColumnConfig<UserGroup, Date> createdOnColumn = new ColumnConfig<UserGroup, Date>(
				propertiesAccess.createdOn(), 10, "createdOn");
		list.add(createdOnColumn);
		ColumnConfig<UserGroup, String> updatedByColumn = new ColumnConfig<UserGroup, String>(
				propertiesAccess.updatedBy(), 30, "updatedBy");
		list.add(updatedByColumn);
		ColumnConfig<UserGroup, Date> updatedOnColumn = new ColumnConfig<UserGroup, Date>(
				propertiesAccess.updatedOn(), 10, "updatedOn");
		list.add(updatedOnColumn);
		columnModel = new ColumnModel<UserGroup>(list);
		widget = gxtUiBinder.createAndBindUi(this);
		panel.addDomHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				formUpdated();
			}
		}, KeyPressEvent.getType());
		showInfoBanner(bannerInfoIsShowed);
		// Apply editor to grid
		final GridEditing<UserGroup> editing = new GridInlineEditing<UserGroup>(
				grid);
		editing.addBeforeStartEditHandler(new BeforeStartEditHandler<UserGroup>() {
			@Override
			public void onBeforeStartEdit(BeforeStartEditEvent<UserGroup> event) {
				editing.completeEditing();
				int row = event.getEditCell().getRow();
				UserGroup model = listStore.get(row);
				model.setUpdating(true);
			}
		});
		editing.addCompleteEditHandler(new CompleteEditHandler<UserGroup>() {
			@Override
			public void onCompleteEdit(CompleteEditEvent<UserGroup> event) {
				int row = event.getEditCell().getRow();
				UserGroup model = listStore.get(row);
				model.setUpdating(false);
			}
		});

		TextField typeField = new TextField();
		typeField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(typeColumn, typeField);

		TextField descriptionField = new TextField();
		descriptionField.addValidator(new MaxLengthValidator(100));
		descriptionField.setAllowBlank(false);
		editing.addEditor(descriptionColumn, descriptionField);

		toolBarArea.setHeaderVisible(false);
		toolsBar = new EditableGridToolsBar();
		toolsBar.getElement().getStyle().setProperty("borderBottom", "none");
		toolsBar.setPageSize(50);
		toolsBar.initialize(toolsBarAction);
		ToolTipConfig config = new ToolTipConfig();
		config = new ToolTipConfig();
		config.setBodyHtml("Prints the current document");
		config.setTitleHtml("Template Tip");
		config.setMouseOffset(new int[] { 0, 0 });
		config.setAnchor(Side.LEFT);
		config.setRenderer(renderer);
		config.setCloseable(true);
		config.setMaxWidth(415);
		//
		toolsBar.setToolTipConfig(config);
		toolBarArea.add(toolsBar);
		filters = new GridFilters<UserGroup>();
		listStore
				.addStoreRecordChangeHandler(new StoreRecordChangeHandler<UserGroup>() {
					@Override
					public void onRecordChange(
							StoreRecordChangeEvent<UserGroup> event) {
						UserGroup changedModel = event.getRecord().getModel();
						if (changedModel != null
								&& !"REMOVE".equalsIgnoreCase(changedModel
										.getSTATUS())) {
							if (changedModel.getEntityId() == null) {
								changedModel.setSTATUS("INSERT");
							} else {
								changedModel.setSTATUS("MERGE");
							}
						}
					}
				});

		filters.initPlugin(grid);
		// Add Filters

		StringFilter<UserGroup> typeFilter = new StringFilter<UserGroup>(
				propertiesAccess.type());
		filters.addFilter(typeFilter);
		StringFilter<UserGroup> descriptionFilter = new StringFilter<UserGroup>(
				propertiesAccess.description());
		filters.addFilter(descriptionFilter);
		StringFilter<UserGroup> createdByFilter = new StringFilter<UserGroup>(
				propertiesAccess.createdBy());
		filters.addFilter(createdByFilter);
		DateFilter<UserGroup> createdOnFilter = new DateFilter<UserGroup>(
				propertiesAccess.createdOn());
		filters.addFilter(createdOnFilter);
		StringFilter<UserGroup> updatedByFilter = new StringFilter<UserGroup>(
				propertiesAccess.updatedBy());
		filters.addFilter(updatedByFilter);
		DateFilter<UserGroup> updatedOnFilter = new DateFilter<UserGroup>(
				propertiesAccess.updatedOn());
		filters.addFilter(updatedOnFilter);
		numberer.initPlugin(grid);
		grid.setLoadMask(true);
		grid.setColumnReordering(true);
		grid.getView().setStripeRows(true);
		grid.getView().setTrackMouseOver(false);
		grid.getView().setAutoFill(true);
		GridDropTarget<UserGroup> target = new GridDropTarget<UserGroup>(grid);
		target.setAllowSelfAsSource(true);
		target.setFeedback(Feedback.INSERT);

		ViewUtils.unNotify(htmlMessage);
		grid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				UserGroup model = grid.getSelectionModel().getSelectedItem();
				ViewUtils.showAuditInfos(htmlMessage,
						model.getGlobalAuditInfo());
			}
		});
		grid.getView().setViewConfig(new GridViewConfig<UserGroup>() {
			@Override
			public String getColStyle(UserGroup model,
					ValueProvider<? super UserGroup, ?> valueProvider,
					int rowIndex, int colIndex) {
				return null;
			}

			@Override
			public String getRowStyle(UserGroup model, int rowIndex) {
				if (model.getErrorMessage() != null
						&& model.getErrorMessage().getMessage() != null
						&& !model.getErrorMessage().getMessage().trim()
								.isEmpty()) {
					return "errorGridRow";
				} else if ("REMOVE".equalsIgnoreCase(model.getSTATUS())) {
					return "removedGridRow";
				} else {
					return "gridRow";
				}
			}
		});
		return widget;
	}

	public IWidget getViewWidget() {
		return this;
	}

	@Override
	public Widget getWidget() {
		// TODO Auto-generated method stub
		return asWidget();
	}

	public EditableGridToolsBar toolsBar;

	@Override
	public void setPresenter(IPresenter p) {
		this.presenter = (UserGroupPresenter) p;
	}

	@Override
	public IPresenter getPresenter() {
		return this.presenter;
	}

	@Override
	public void updateView(UserGroup model) {
		Info.display(messages.message(), messages.finished());
		if (box != null) {
			box.hide();
			box = null;
		}
	}

	@Override
	public void updateView(List<UserGroup> models) {
		hideProgressBar();
		try {
			if (models != null) {
				boolean allOperationsSuccess = true;
				for (UserGroup model : models) {
					boolean success = true;
					if (model != null) {
						if (model.getErrorMessage() != null) {
							success = false;
							if (allOperationsSuccess) {
								allOperationsSuccess = false;
							}
						} else {
							success = true;
						}
						if ("REMOVE".equalsIgnoreCase(model.getSTATUS())) {
							if (success) {
								listStore.remove(model);
								presenter.removeModelToBeRemoved(model);
							} else {
								UserGroup oldModel = listStore
										.findModelWithKey(listStore
												.getKeyProvider().getKey(model));
								if (oldModel != null) {
									oldModel.merge(model);
								}
							}
						} else {
							UserGroup oldModel = listStore
									.findModelWithKey(listStore
											.getKeyProvider().getKey(model));
							if (success) {
								model.setErrorMessage(null);
								model.setSTATUS("IGNORE");
							}
							if (oldModel != null) {
								oldModel.merge(model);
							}
						}
					}
				}
				if (allOperationsSuccess) {
					formReset();
					presenter.clearModelsToBeRemoved();
					listStore.commitChanges();
				}
				grid.getView().refresh(false);
			}
		} catch (Exception ex) {
			ViewUtils.notify(htmlMessage, new SystemMessage(ex.getMessage()));
			Window.alert(ex.getMessage());
			ex.printStackTrace(System.out);
		} finally {
			Info.display(messages.message(), messages.finished());
		}
	}

	@Override
	public UserGroup updateModel(UserGroup model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void help() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBtnSaveVisible(boolean aFlag) {
		toolsBar.setSaveEnabled(aFlag);
		toolsBar.setRemoveEnabled(aFlag);
		toolsBar.setPersistSepEnabled(aFlag);
	}

	@Override
	public void onFormCleared() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onPersistenceSuccessed() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onDataChanged(UserGroup model, boolean reloadDetails,
			boolean setBtnsPersistVisible) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onActionFailure(Throwable caught, String message) {
		if (message != null) {
			Window.alert(message);
		} else {
			Window.alert(caught.getMessage() != null ? caught.getMessage()
					: caught.toString());
		}
		if (box != null) {
			box.hide();
			box = null;
		}
	}

	@Override
	public void showInfoBanner() {
		showInfoBanner(!bannerInfoIsShowed);
		userHideBannerInfo = bannerInfoIsShowed;
	}


	@Override
	public void setViewCallback(ViewCallback callback) {
		this.callback = callback;
	}

	@Override
	public void formReset() {
		presenter.removeUpdatedZone(widget.getTitle());
		callback.viewReset(presenter.getHeaderTitle() != null ? presenter
				.getHeaderTitle() : widget.getTitle());
		presenter.setUpdatePending(false);
	}

	@Override
	public void formUpdated() {
		if (!presenter.isUpdatePending()) {
			callback.viewChanged(presenter.getHeaderTitle() != null ? presenter
					.getHeaderTitle() : widget.getTitle());
		}
		presenter.setUpdatePending(true);
	}

	@Override
	protected void execute(ActionCommand command,
			UserRequestCallbackAdapter<UserGroup> urc) {
		presenter.getEventBus().executeUserGroup(command, urc);
	}

	@Override
	protected void setModelsToBePersisted(ListStore<UserGroup> listStore) {
		presenter.setModelsToBePersisted();
	}

	@Override
	protected boolean isUpdatePending() {
		return presenter.isUpdatePending();
	}

	@Override
	protected boolean isSearchPending() {
		return presenter.isSearchPending();
	}
}