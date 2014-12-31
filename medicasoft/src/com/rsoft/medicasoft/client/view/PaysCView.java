package com.rsoft.medicasoft.client.view;

/*
 Robelkend Templates Generator
 */
/*@Author=Jean Louidort*/
/*@Generation Date=Mon Oct 14 22:30:48 EDT 2013*/
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
import com.rsoft.medicasoft.client.presenter.PaysPresenter;
import com.rsoft.medicasoft.client.toolsbar.EditableGridToolsBar;
import com.rsoft.medicasoft.client.view.viewinterface.IPresenter;
import com.rsoft.medicasoft.client.view.viewinterface.IWidget;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.FilterWrapper;
import com.rsoft.medicasoft.shared.SystemMessage;
import com.rsoft.medicasoft.shared.XFilter;
import com.rsoft.medicasoft.shared.model.Pays;
import com.rsoft.medicasoft.shared.model.PaysPropertiesAccess;
import com.rsoft.medicasoft.shared.model.RequestDescriptor;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.LayoutRegion;
import com.sencha.gxt.core.client.Style.Side;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.event.StoreRecordChangeEvent;
import com.sencha.gxt.data.shared.event.StoreRecordChangeEvent.StoreRecordChangeHandler;
import com.sencha.gxt.data.shared.loader.FilterConfig;
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

public class PaysCView extends ViewGridBase<Pays> {
	private PaysPresenter presenter;

	public interface Renderer extends ToolTipConfig.ToolTipRenderer<Object>,
			XTemplates {
		@Override
		@XTemplate(source = "help/paysTemplate.html")
		public SafeHtml renderToolTip(Object data);
	}

	private Renderer renderer = GWT.create(Renderer.class);

	interface GxtUiBinder extends UiBinder<Widget, PaysCView> {
	}

	@Override
	public void criteria() {
		if (presenter.isUpdatePending()
				&& !Window.confirm(messages.updatePendingContinue())) {
			return;
		}
		filters.clearFilters();
		resetForm();
	}

	@Override
	public void remove() {
		List<Pays> Payss = grid.getSelectionModel().getSelectedItems();
		for (Pays Pays : Payss) {
			if (Pays.getEntityId() != null) {
				presenter.addModelToBeRemoved(Pays);
				Pays foundModel = listStore.findModelWithKey(listStore
						.getKeyProvider().getKey(Pays));
				if (foundModel != null) {
					foundModel.setSTATUS("REMOVE");
				}
			}
		}
		listStore.commitChanges();
		grid.getView().refresh(false);
	}

	@Override
	public void ignore() {
		List<Pays> Payss = grid.getSelectionModel().getSelectedItems();
		for (Pays Pays : Payss) {
			Pays foundModel = listStore.findModelWithKey(listStore
					.getKeyProvider().getKey(Pays));
			if (foundModel != null) {
				foundModel.setSTATUS("IGNORE");
			}
		}
		listStore.commitChanges();
		grid.getView().refresh(false);
	}

	@Override
	public void loadDatas() {
		try {
			RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Pays>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Pays>>() {
				@Override
				public void load(FilterPagingLoadConfig loadConfig,
						AsyncCallback<PagingLoadResult<Pays>> callback) {
					listStore.clear();
					listStore.commitChanges();
					FilterWrapper wrapper = new FilterWrapper(null);

					if (filters.getFilter("nom") != null
							&& filters.getFilter("nom").getFilterConfig() != null
							&& !filters.getFilter("nom").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("nom")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "nom", fc.getType(),
										fc.getValue()));
							}
						}
					}
					if (filters.getFilter("createdOn") != null
							&& filters.getFilter("createdOn").getFilterConfig() != null
							&& !filters.getFilter("createdOn")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("createdOn")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "createdOn", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("updatedBy") != null
							&& filters.getFilter("updatedBy").getFilterConfig() != null
							&& !filters.getFilter("updatedBy")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("updatedBy")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "updatedBy", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("updatedOn") != null
							&& filters.getFilter("updatedOn").getFilterConfig() != null
							&& !filters.getFilter("updatedOn")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("updatedOn")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "updatedOn", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("createdBy") != null
							&& filters.getFilter("createdBy").getFilterConfig() != null
							&& !filters.getFilter("createdBy")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("createdBy")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "createdBy", fc
										.getType(), fc.getValue()));
							}
						}
					}
					presenter.setFilters(wrapper);
					UserRequestCallbackAdapter<Pays> urc = new UserRequestCallbackAdapter<Pays>();
					urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
					urc.setAsyncCallback(callback);
					urc.setLoadConfig(loadConfig);
					presenter.getEventBus().executePays(
							ActionCommand.COLUMNAR_SEARCH, urc);
					showInfoBanner(!userHideBannerInfo);
				}
			};
			pagingLoader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<Pays>>(
					proxy) {
				protected FilterPagingLoadConfig newLoadConfig() {
					return new FilterPagingLoadConfigBean();
				}
			};
			pagingLoader.setRemoteSort(true);
			pagingLoader
					.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, Pays, PagingLoadResult<Pays>>(
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
		RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Pays>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Pays>>() {
			@Override
			public void load(FilterPagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<Pays>> callback) {
				UserRequestCallbackAdapter<Pays> urc = new UserRequestCallbackAdapter<Pays>() {
					@Override
					public void onSingleOperationSuccessed(Pays model) {
						hideProgressBar();
					}

					@Override
					public void onMultipleOperationsFailed(List<Pays> model,
							Throwable cause) {
						hideProgressBar();
					}
				};
				urc.setAsyncCallback(callback);
				urc.setLoadConfig(loadConfig);
				urc.setPageSize(toolsBar.getPageSize());
				urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
				presenter.getEventBus().executePays(ActionCommand.RESET_GRID,
						urc);
			}
		};
		pagingLoader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<Pays>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		pagingLoader.setRemoteSort(true);
		pagingLoader
				.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, Pays, PagingLoadResult<Pays>>(
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

	private PagingLoader<FilterPagingLoadConfig, PagingLoadResult<Pays>> pagingLoader = null;
	private RowNumberer<Pays> numberer;

	@Override
	public Widget asWidget() {
		ENTITY_NAME = "Pays";
		PaysPropertiesAccess propertiesAccess = GWT
				.create(PaysPropertiesAccess.class);
		listStore = new ListStore<Pays>(propertiesAccess.key());
		IdentityValueProvider<Pays> identity = new IdentityValueProvider<Pays>();
		numberer = new RowNumberer<Pays>(identity);
		list = new ArrayList<ColumnConfig<Pays, ?>>();
		List<ColumnConfig<Pays, ?>> list = new ArrayList<ColumnConfig<Pays, ?>>();

		ColumnConfig<Pays, String> nomColumn = new ColumnConfig<Pays, String>(
				propertiesAccess.nom(), 100, messages.nom());
		list.add(nomColumn);
		ColumnConfig<Pays, String> createdByColumn = new ColumnConfig<Pays, String>(
				propertiesAccess.createdBy(), 30, "createdBy");
		list.add(createdByColumn);
		ColumnConfig<Pays, Date> createdOnColumn = new ColumnConfig<Pays, Date>(
				propertiesAccess.createdOn(), 10, "createdOn");
		list.add(createdOnColumn);
		ColumnConfig<Pays, String> updatedByColumn = new ColumnConfig<Pays, String>(
				propertiesAccess.updatedBy(), 30, "updatedBy");
		list.add(updatedByColumn);
		ColumnConfig<Pays, Date> updatedOnColumn = new ColumnConfig<Pays, Date>(
				propertiesAccess.updatedOn(), 10, "updatedOn");
		list.add(updatedOnColumn);
		columnModel = new ColumnModel<Pays>(list);
		widget = gxtUiBinder.createAndBindUi(this);

		panel.addDomHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				formUpdated();
			}
		}, KeyPressEvent.getType());
		showInfoBanner(bannerInfoIsShowed);
		// Apply editor to grid
		final GridEditing<Pays> editing = new GridInlineEditing<Pays>(grid);
		editing.addBeforeStartEditHandler(new BeforeStartEditHandler<Pays>() {
			@Override
			public void onBeforeStartEdit(BeforeStartEditEvent<Pays> event) {
				editing.completeEditing();
				int row = event.getEditCell().getRow();
				Pays model = listStore.get(row);
				model.setUpdating(true);
			}
		});
		editing.addCompleteEditHandler(new CompleteEditHandler<Pays>() {
			@Override
			public void onCompleteEdit(CompleteEditEvent<Pays> event) {
				int row = event.getEditCell().getRow();
				Pays model = listStore.get(row);
				model.setUpdating(false);
			}
		});

		TextField nomField = new TextField();
		nomField.addValidator(new MaxLengthValidator(100));
		nomField.setAllowBlank(false);
		editing.addEditor(nomColumn, nomField);

		toolBarArea.setHeaderVisible(false);
		toolsBar = new EditableGridToolsBar();
		toolsBar.setParamInoutSuffix("PAYS".toUpperCase());
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
		filters = new GridFilters<Pays>();
		listStore
				.addStoreRecordChangeHandler(new StoreRecordChangeHandler<Pays>() {
					@Override
					public void onRecordChange(
							StoreRecordChangeEvent<Pays> event) {
						Pays changedModel = event.getRecord().getModel();
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

		StringFilter<Pays> nomFilter = new StringFilter<Pays>(
				propertiesAccess.nom());
		filters.addFilter(nomFilter);
		DateFilter<Pays> createdOnFilter = new DateFilter<Pays>(
				propertiesAccess.createdOn());
		filters.addFilter(createdOnFilter);
		StringFilter<Pays> updatedByFilter = new StringFilter<Pays>(
				propertiesAccess.updatedBy());
		filters.addFilter(updatedByFilter);
		DateFilter<Pays> updatedOnFilter = new DateFilter<Pays>(
				propertiesAccess.updatedOn());
		filters.addFilter(updatedOnFilter);
		StringFilter<Pays> createdByFilter = new StringFilter<Pays>(
				propertiesAccess.createdBy());
		filters.addFilter(createdByFilter);
		numberer.initPlugin(grid);
		grid.setLoadMask(true);
		grid.setColumnReordering(true);
		grid.getView().setStripeRows(true);
		grid.getView().setTrackMouseOver(false);
		grid.getView().setAutoFill(true);
		GridDropTarget<Pays> target = new GridDropTarget<Pays>(grid);
		target.setAllowSelfAsSource(true);
		target.setFeedback(Feedback.INSERT);

		ViewUtils.unNotify(htmlMessage);
		grid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				Pays model = grid.getSelectionModel().getSelectedItem();
				ViewUtils.showAuditInfos(htmlMessage,
						model.getGlobalAuditInfo());
			}
		});
		grid.getView().setViewConfig(new GridViewConfig<Pays>() {
			@Override
			public String getColStyle(Pays model,
					ValueProvider<? super Pays, ?> valueProvider, int rowIndex,
					int colIndex) {
				return null;
			}

			@Override
			public String getRowStyle(Pays model, int rowIndex) {
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

	@Override
	public void setPresenter(IPresenter p) {
		this.presenter = (PaysPresenter) p;
	}

	@Override
	public IPresenter getPresenter() {
		return this.presenter;
	}

	@Override
	public void updateView(Pays model) {
		Info.display(messages.message(), messages.finished());
		if (box != null) {
			box.hide();
			box = null;
		}
	}

	@Override
	public void updateView(List<Pays> models) {
		try {
			if (models != null) {
				boolean allOperationsSuccess = true;
				for (Pays model : models) {
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
								Pays oldModel = listStore
										.findModelWithKey(listStore
												.getKeyProvider().getKey(model));
								if (oldModel != null) {
									oldModel.merge(model);
								}
							}
						} else {
							Pays oldModel = listStore
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

			if (box != null) {
				box.hide();
				box = null;
			}
		}
	}

	@Override
	public Pays updateModel(Pays model) {
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
	public void onDataChanged(Pays model, boolean reloadDetails,
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
			UserRequestCallbackAdapter<Pays> urc) {
		presenter.getEventBus().executePays(command, urc);
	}

	@Override
	protected void setModelsToBePersisted(ListStore<Pays> listStore) {
		presenter.setModelsToBePersisted(listStore);
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