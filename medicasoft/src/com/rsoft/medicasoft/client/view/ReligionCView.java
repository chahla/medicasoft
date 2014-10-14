package com.rsoft.medicasoft.client.view;

/*
 Robelkend Templates Generator
 */
/*@Author=Jean Louidort*/
/*@Generation Date=Mon Nov 18 01:14:08 EST 2013*/
/*@Version=1.0*/
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.safecss.shared.SafeStylesUtils;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.client.UserRequestCallbackAdapter;
import com.rsoft.medicasoft.client.presenter.ReligionPresenter;
import com.rsoft.medicasoft.client.toolsbar.EditableGridToolsBar;
import com.rsoft.medicasoft.client.view.viewinterface.IPresenter;
import com.rsoft.medicasoft.client.view.viewinterface.IWidget;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.FilterWrapper;
import com.rsoft.medicasoft.shared.SystemMessage;
import com.rsoft.medicasoft.shared.XFilter;
import com.rsoft.medicasoft.shared.model.Religion;
import com.rsoft.medicasoft.shared.model.ReligionPropertiesAccess;
import com.rsoft.medicasoft.shared.model.RequestDescriptor;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.LayoutRegion;
import com.sencha.gxt.core.client.Style.Side;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
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

public class ReligionCView extends ViewGridBase<Religion> {
	private ReligionPresenter presenter;

	public interface Renderer extends ToolTipConfig.ToolTipRenderer<Object>,
			XTemplates {
		@Override
		@XTemplate(source = "help/religionTemplate.html")
		public SafeHtml renderToolTip(Object data);
	}

	private Renderer renderer = GWT.create(Renderer.class);

	interface GxtUiBinder extends UiBinder<Widget, ReligionCView> {
	}

	private ListStore<String> classificationItems = new ListStore<String>(
			new ModelKeyProvider<String>() {
				@Override
				public String getKey(String item) {
					return item;
				}
			});

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
		List<Religion> Religions = grid.getSelectionModel().getSelectedItems();
		for (Religion Religion : Religions) {
			if (Religion.getEntityId() != null) {
				presenter.addModelToBeRemoved(Religion);
				Religion foundModel = listStore.findModelWithKey(listStore
						.getKeyProvider().getKey(Religion));
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
		List<Religion> Religions = grid.getSelectionModel().getSelectedItems();
		for (Religion Religion : Religions) {
			Religion foundModel = listStore.findModelWithKey(listStore
					.getKeyProvider().getKey(Religion));
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
			RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Religion>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Religion>>() {
				@Override
				public void load(FilterPagingLoadConfig loadConfig,
						AsyncCallback<PagingLoadResult<Religion>> callback) {
					listStore.clear();
					listStore.commitChanges();
					FilterWrapper wrapper = new FilterWrapper(null);

					if (filters.getFilter("religionId") != null
							&& filters.getFilter("religionId")
									.getFilterConfig() != null
							&& !filters.getFilter("religionId")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("religionId")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "religionId", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("description") != null
							&& filters.getFilter("description")
									.getFilterConfig() != null
							&& !filters.getFilter("description")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("description")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "description", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("classification") != null
							&& filters.getFilter("classification")
									.getFilterConfig() != null
							&& !filters.getFilter("classification")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"classification").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "classification", fc
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
					presenter.setFilters(wrapper);
					UserRequestCallbackAdapter<Religion> urc = new UserRequestCallbackAdapter<Religion>();
					urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
					urc.setAsyncCallback(callback);
					urc.setLoadConfig(loadConfig);
					presenter.getEventBus().executeReligion(
							ActionCommand.COLUMNAR_SEARCH, urc);
					showInfoBanner(!userHideBannerInfo);
				}
			};
			pagingLoader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<Religion>>(
					proxy) {
				protected FilterPagingLoadConfig newLoadConfig() {
					return new FilterPagingLoadConfigBean();
				}
			};
			pagingLoader.setRemoteSort(true);
			pagingLoader
					.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, Religion, PagingLoadResult<Religion>>(
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
		RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Religion>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Religion>>() {
			@Override
			public void load(FilterPagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<Religion>> callback) {
				UserRequestCallbackAdapter<Religion> urc = new UserRequestCallbackAdapter<Religion>() {
					@Override
					public void onSingleOperationSuccessed(Religion model) {
						hideProgressBar();
					}

					@Override
					public void onMultipleOperationsFailed(
							List<Religion> model, Throwable cause) {
						hideProgressBar();
					}
				};
				urc.setAsyncCallback(callback);
				urc.setLoadConfig(loadConfig);
				urc.setPageSize(toolsBar.getPageSize());
				urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
				presenter.getEventBus().executeReligion(
						ActionCommand.RESET_GRID, urc);
			}
		};
		pagingLoader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<Religion>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		pagingLoader.setRemoteSort(true);
		pagingLoader
				.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, Religion, PagingLoadResult<Religion>>(
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

	private PagingLoader<FilterPagingLoadConfig, PagingLoadResult<Religion>> pagingLoader = null;
	private RowNumberer<Religion> numberer;

	@Override
	public Widget asWidget() {
		ENTITY_NAME = "Religion";
		ReligionPropertiesAccess propertiesAccess = GWT
				.create(ReligionPropertiesAccess.class);
		listStore = new ListStore<Religion>(propertiesAccess.key());
		IdentityValueProvider<Religion> identity = new IdentityValueProvider<Religion>();
		numberer = new RowNumberer<Religion>(identity);
		list = new ArrayList<ColumnConfig<Religion, ?>>();
		List<ColumnConfig<Religion, ?>> list = new ArrayList<ColumnConfig<Religion, ?>>();

		ColumnConfig<Religion, String> descriptionColumn = new ColumnConfig<Religion, String>(
				propertiesAccess.description(), 60, messages.description());
		list.add(descriptionColumn);
		ColumnConfig<Religion, String> classificationColumn = new ColumnConfig<Religion, String>(
				propertiesAccess.classification(), 20,
				messages.classification());
		list.add(classificationColumn);
		ColumnConfig<Religion, String> createdByColumn = new ColumnConfig<Religion, String>(
				propertiesAccess.createdBy(), 30, messages.createdBy());
		list.add(createdByColumn);
		ColumnConfig<Religion, Date> createdOnColumn = new ColumnConfig<Religion, Date>(
				propertiesAccess.createdOn(), 10, messages.createdOn());
		list.add(createdOnColumn);
		ColumnConfig<Religion, String> updatedByColumn = new ColumnConfig<Religion, String>(
				propertiesAccess.updatedBy(), 30, messages.updatedBy());
		list.add(updatedByColumn);
		ColumnConfig<Religion, Date> updatedOnColumn = new ColumnConfig<Religion, Date>(
				propertiesAccess.updatedOn(), 10, messages.updatedOn());
		list.add(updatedOnColumn);
		columnModel = new ColumnModel<Religion>(list);
		widget = gxtUiBinder.createAndBindUi(this);
		panel.addDomHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				formUpdated();
			}
		}, KeyPressEvent.getType());
		showInfoBanner(bannerInfoIsShowed);
		// Apply editor to grid
		final GridEditing<Religion> editing = new GridInlineEditing<Religion>(
				grid);
		editing.addBeforeStartEditHandler(new BeforeStartEditHandler<Religion>() {
			@Override
			public void onBeforeStartEdit(BeforeStartEditEvent<Religion> event) {
				editing.completeEditing();
				int row = event.getEditCell().getRow();
				Religion model = listStore.get(row);
				model.setUpdating(true);
			}
		});
		editing.addCompleteEditHandler(new CompleteEditHandler<Religion>() {
			@Override
			public void onCompleteEdit(CompleteEditEvent<Religion> event) {
				int row = event.getEditCell().getRow();
				Religion model = listStore.get(row);
				model.setUpdating(false);
			}
		});
		TextField descriptionField = new TextField();
		descriptionField.addValidator(new MaxLengthValidator(60));
		descriptionField.setAllowBlank(false);
		editing.addEditor(descriptionColumn, descriptionField);

		classificationItems.add("Christianisme");
		classificationItems.add("Judaisme");
		classificationItems.add("Islamisme");
		classificationItems.add("boudhisme");
		classificationItems.add("Animisme");

		classificationColumn.setColumnTextStyle(SafeStylesUtils
				.fromTrustedString("padding: 2px 3px;"));

		ComboBoxCell<String> classificationField = new ComboBoxCell<String>(
				classificationItems, new LabelProvider<String>() {
					@Override
					public String getLabel(String item) {
						return item;
					}
				});
		classificationField.setWidth(110);
		classificationField.setTriggerAction(TriggerAction.ALL);
		classificationField.setForceSelection(true);

		classificationColumn.setCell(classificationField);
		classificationField.setWidth(110);

		// TextField classificationField = new TextField();
		// classificationField.addValidator(new MaxLengthValidator(20));
		// classificationField.setAllowBlank(false);
		// editing.addEditor(classificationColumn, classificationField);

		toolBarArea.setHeaderVisible(false);
		toolsBar = new EditableGridToolsBar();
		toolsBar.setParamInoutSuffix("Religion");
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
		filters = new GridFilters<Religion>();
		listStore
				.addStoreRecordChangeHandler(new StoreRecordChangeHandler<Religion>() {
					@Override
					public void onRecordChange(
							StoreRecordChangeEvent<Religion> event) {
						Religion changedModel = event.getRecord().getModel();
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

		StringFilter<Religion> descriptionFilter = new StringFilter<Religion>(
				propertiesAccess.description());
		filters.addFilter(descriptionFilter);
		StringFilter<Religion> classificationFilter = new StringFilter<Religion>(
				propertiesAccess.classification());
		filters.addFilter(classificationFilter);
		StringFilter<Religion> createdByFilter = new StringFilter<Religion>(
				propertiesAccess.createdBy());
		filters.addFilter(createdByFilter);
		DateFilter<Religion> createdOnFilter = new DateFilter<Religion>(
				propertiesAccess.createdOn());
		filters.addFilter(createdOnFilter);
		StringFilter<Religion> updatedByFilter = new StringFilter<Religion>(
				propertiesAccess.updatedBy());
		filters.addFilter(updatedByFilter);
		DateFilter<Religion> updatedOnFilter = new DateFilter<Religion>(
				propertiesAccess.updatedOn());
		filters.addFilter(updatedOnFilter);
		numberer.initPlugin(grid);
		grid.setLoadMask(true);
		grid.setColumnReordering(true);
		grid.getView().setStripeRows(true);
		grid.getView().setTrackMouseOver(false);
		grid.getView().setAutoFill(true);
		GridDropTarget<Religion> target = new GridDropTarget<Religion>(grid);
		target.setAllowSelfAsSource(true);
		target.setFeedback(Feedback.INSERT);
		htmlMessage.getElement().getStyle().setBackgroundColor("#F6F983");
		htmlMessage.getElement().getStyle().setBorderColor("#2106C2");
		htmlMessage.getElement().getStyle().setBorderWidth(3, Unit.PX);
		htmlMessage.getElement().getStyle().setColor("#2106C2");
		htmlMessage.getElement().getStyle().setBorderColor("#4F81BD");
		ViewUtils.unNotify(htmlMessage);
		grid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				Religion model = grid.getSelectionModel().getSelectedItem();
				ViewUtils.showAuditInfos(htmlMessage,
						model.getGlobalAuditInfo());
			}
		});
		grid.getView().setViewConfig(new GridViewConfig<Religion>() {
			@Override
			public String getColStyle(Religion model,
					ValueProvider<? super Religion, ?> valueProvider,
					int rowIndex, int colIndex) {
				return null;
			}

			@Override
			public String getRowStyle(Religion model, int rowIndex) {
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
		this.presenter = (ReligionPresenter) p;
	}

	@Override
	public IPresenter getPresenter() {
		return this.presenter;
	}

	@Override
	public void updateView(Religion model) {
		Info.display(messages.message(), messages.finished());
		if (box != null) {
			box.hide();
			box = null;
		}
	}

	@Override
	public void updateView(List<Religion> models) {
		try {
			if (models != null) {
				boolean allOperationsSuccess = true;
				for (Religion model : models) {
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
								Religion oldModel = listStore
										.findModelWithKey(listStore
												.getKeyProvider().getKey(model));
								if (oldModel != null) {
									oldModel.merge(model);
								}
							}
						} else {
							Religion oldModel = listStore
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
	public Religion updateModel(Religion model) {
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
	public void onDataChanged(Religion model, boolean reloadDetails,
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

	public void showInfoBanner(boolean show) {
		if (!show) {
			mainContainer.hide(LayoutRegion.NORTH);
			menuContainer.hide(LayoutRegion.NORTH);
			northData.setSize(36);
			bannerInfoIsShowed = false;
			mainContainer.show(LayoutRegion.NORTH);
		} else {
			mainContainer.hide(LayoutRegion.NORTH);
			menuContainer.show(LayoutRegion.NORTH);
			northData.setSize(63);
			bannerInfoIsShowed = true;
			mainContainer.show(LayoutRegion.NORTH);
		}
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
			UserRequestCallbackAdapter<Religion> urc) {
		presenter.getEventBus().executeReligion(command, urc);
	}

	@Override
	protected void setModelsToBePersisted(ListStore<Religion> listStore) {
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