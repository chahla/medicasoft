package com.rsoft.medicasoft.client.view;

/*
 Robelkend Templates Generator
 */
/*@Author=Jean Louidort*/
/*@Generation Date=Mon Oct 14 22:30:48 EDT 2013*/
/*@Version=1.0*/
import java.util.ArrayList;
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
import com.rsoft.medicasoft.client.presenter.DepartementPresenter;
import com.rsoft.medicasoft.client.toolsbar.EditableGridToolsBar;
import com.rsoft.medicasoft.client.view.viewinterface.IPresenter;
import com.rsoft.medicasoft.client.view.viewinterface.IWidget;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.FilterWrapper;
import com.rsoft.medicasoft.shared.SystemMessage;
import com.rsoft.medicasoft.shared.XFilter;
import com.rsoft.medicasoft.shared.model.Departement;
import com.rsoft.medicasoft.shared.model.DepartementPropertiesAccess;
import com.rsoft.medicasoft.shared.model.RequestDescriptor;
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
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;
import com.sencha.gxt.widget.core.client.grid.filters.GridFilters;
import com.sencha.gxt.widget.core.client.grid.filters.StringFilter;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;

public class DepartementCView extends ViewGridBase<Departement> {
	private DepartementPresenter presenter;

	public interface Renderer extends ToolTipConfig.ToolTipRenderer<Object>,
			XTemplates {
		@Override
		@XTemplate(source = "help/departementTemplate.html")
		public SafeHtml renderToolTip(Object data);
	}

	private Renderer renderer = GWT.create(Renderer.class);

	interface GxtUiBinder extends UiBinder<Widget, DepartementCView> {
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
		List<Departement> Departements = grid.getSelectionModel()
				.getSelectedItems();
		for (Departement Departement : Departements) {
			if (Departement.getEntityId() != null) {
				presenter.addModelToBeRemoved(Departement);
				Departement foundModel = listStore.findModelWithKey(listStore
						.getKeyProvider().getKey(Departement));
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
		List<Departement> Departements = grid.getSelectionModel()
				.getSelectedItems();
		for (Departement Departement : Departements) {
			Departement foundModel = listStore.findModelWithKey(listStore
					.getKeyProvider().getKey(Departement));
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
			RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Departement>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Departement>>() {
				@Override
				public void load(FilterPagingLoadConfig loadConfig,
						AsyncCallback<PagingLoadResult<Departement>> callback) {
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
					if (filters.getFilter("paysId") != null
							&& filters.getFilter("paysId").getFilterConfig() != null
							&& !filters.getFilter("paysId").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("paysId")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "paysId", fc
										.getType(), fc.getValue()));
							}
						}
					}
					presenter.setFilters(wrapper);
					UserRequestCallbackAdapter<Departement> urc = new UserRequestCallbackAdapter<Departement>();
					urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
					urc.setAsyncCallback(callback);
					urc.setLoadConfig(loadConfig);
					presenter.getEventBus().executeDepartement(
							ActionCommand.COLUMNAR_SEARCH, urc);
					showInfoBanner(!userHideBannerInfo);
				}
			};
			pagingLoader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<Departement>>(
					proxy) {
				protected FilterPagingLoadConfig newLoadConfig() {
					return new FilterPagingLoadConfigBean();
				}
			};
			pagingLoader.setRemoteSort(true);
			pagingLoader
					.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, Departement, PagingLoadResult<Departement>>(
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
		RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Departement>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Departement>>() {
			@Override
			public void load(FilterPagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<Departement>> callback) {
				UserRequestCallbackAdapter<Departement> urc = new UserRequestCallbackAdapter<Departement>() {
					@Override
					public void onSingleOperationSuccessed(Departement model) {
						hideProgressBar();
					}

					@Override
					public void onMultipleOperationsFailed(
							List<Departement> model, Throwable cause) {
						hideProgressBar();
					}
				};
				urc.setAsyncCallback(callback);
				urc.setLoadConfig(loadConfig);
				urc.setPageSize(toolsBar.getPageSize());
				urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
				presenter.getEventBus().executeDepartement(
						ActionCommand.RESET_GRID, urc);
			}
		};
		pagingLoader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<Departement>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		pagingLoader.setRemoteSort(true);
		pagingLoader
				.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, Departement, PagingLoadResult<Departement>>(
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

	private PagingLoader<FilterPagingLoadConfig, PagingLoadResult<Departement>> pagingLoader = null;

	private Long entityId;

	@Override
	public Widget asWidget() {

		ENTITY_NAME = "Departement";
		DepartementPropertiesAccess propertiesAccess = GWT
				.create(DepartementPropertiesAccess.class);
		listStore = new ListStore<Departement>(propertiesAccess.key());
		list = new ArrayList<ColumnConfig<Departement, ?>>();
		List<ColumnConfig<Departement, ?>> list = new ArrayList<ColumnConfig<Departement, ?>>();


		ColumnConfig<Departement, Long> entityIdColumn = new ColumnConfig<Departement, Long>(
				propertiesAccess.entityId(), 30, messages.id());
		list.add(entityIdColumn);

		ColumnConfig<Departement, String> nomColumn = new ColumnConfig<Departement, String>(
				propertiesAccess.nom(), 100, messages.nom());
		list.add(nomColumn);
		columnModel = new ColumnModel<Departement>(list);
		widget = gxtUiBinder.createAndBindUi(this);
		panel.addDomHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				formUpdated();
			}
		}, KeyPressEvent.getType());
		showInfoBanner(bannerInfoIsShowed);
		// Apply editor to grid
		final GridEditing<Departement> editing = new GridInlineEditing<Departement>(
				grid);
		editing.addBeforeStartEditHandler(new BeforeStartEditHandler<Departement>() {
			@Override
			public void onBeforeStartEdit(
					BeforeStartEditEvent<Departement> event) {
				editing.completeEditing();
				int row = event.getEditCell().getRow();
				Departement model = listStore.get(row);
				model.setUpdating(true);
			}
		});
		editing.addCompleteEditHandler(new CompleteEditHandler<Departement>() {
			@Override
			public void onCompleteEdit(CompleteEditEvent<Departement> event) {
				int row = event.getEditCell().getRow();
				Departement model = listStore.get(row);
				model.setUpdating(false);
			}
		});

		TextField nomField = new TextField();
		nomField.addValidator(new MaxLengthValidator(100));
		nomField.setAllowBlank(false);
		editing.addEditor(nomColumn, nomField);

		toolBarArea.setHeaderVisible(false);
		toolsBar = new EditableGridToolsBar();
		toolsBar.setParamInoutSuffix(ENTITY_NAME);
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
		filters = new GridFilters<Departement>();
		listStore
				.addStoreRecordChangeHandler(new StoreRecordChangeHandler<Departement>() {
					@Override
					public void onRecordChange(
							StoreRecordChangeEvent<Departement> event) {
						Departement changedModel = event.getRecord().getModel();
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

		StringFilter<Departement> nomFilter = new StringFilter<Departement>(
				propertiesAccess.nom());
		filters.addFilter(nomFilter);
		grid.setLoadMask(true);
		grid.setColumnReordering(true);
		grid.getView().setStripeRows(true);
		grid.getView().setTrackMouseOver(false);
		grid.getView().setAutoFill(true);
		GridDropTarget<Departement> target = new GridDropTarget<Departement>(
				grid);
		target.setAllowSelfAsSource(true);
		target.setFeedback(Feedback.INSERT);

		ViewUtils.unNotify(htmlMessage);
		grid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				Departement model = grid.getSelectionModel().getSelectedItem();
				if (model.getEntityId() != entityId) {
					if (presenter.getCommunePresenter() != null) {
						if (model.getEntityId() != null) {
							presenter.getCommunePresenter().setHeaderModel(
									model);
						} else {
							presenter.getCommunePresenter()
									.setHeaderModel(null);
						}
						presenter.getCommunePresenter().getView().reset();
					}

				}
				entityId = model.getEntityId();
				ViewUtils.showAuditInfos(htmlMessage,
						model.getGlobalAuditInfo());
			}
		});
		grid.getView().setViewConfig(new GridViewConfig<Departement>() {
			@Override
			public String getColStyle(Departement model,
					ValueProvider<? super Departement, ?> valueProvider,
					int rowIndex, int colIndex) {
				return null;
			}

			@Override
			public String getRowStyle(Departement model, int rowIndex) {
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
		panel.setHeadingText(messages.noHeaderSelected(messages.pays()));

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
		this.presenter = (DepartementPresenter) p;
	}

	@Override
	public IPresenter getPresenter() {
		return this.presenter;
	}

	@Override
	public void updateView(Departement model) {
		Info.display(messages.message(), messages.finished());
		if (box != null) {
			box.hide();
			box = null;
		}
	}

	@Override
	public void updateView(List<Departement> models) {
		try {
			if (models != null) {
				boolean allOperationsSuccess = true;
				for (Departement model : models) {
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
								Departement oldModel = listStore
										.findModelWithKey(listStore
												.getKeyProvider().getKey(model));
								if (oldModel != null) {
									oldModel.merge(model);
								}
							}
						} else {
							Departement oldModel = listStore
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
	public Departement updateModel(Departement model) {
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
	public void onDataChanged(Departement model, boolean reloadDetails,
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
		if (presenter.getCommunePresenter() != null) {
			presenter.getCommunePresenter().setHeaderModel(null);
			presenter.getCommunePresenter().getView().reset();
		}
		if (presenter.getHeaderModel() != null) {
			panel.setHeadingText(messages.pays() + " ("
					+ presenter.getHeaderModel().getNom() + ")");
		} else {
			panel.setHeadingText(messages.pays() + " ("
					+ messages.noHeaderSelected(messages.pays()) + ")");
		}

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
			UserRequestCallbackAdapter<Departement> urc) {
		presenter.getEventBus().executeDepartement(command, urc);
	}

	@Override
	protected void setModelsToBePersisted(ListStore<Departement> listStore) {
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