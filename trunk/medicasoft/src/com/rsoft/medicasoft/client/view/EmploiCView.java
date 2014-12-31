package com.rsoft.medicasoft.client.view;

/*
 Robelkend Templates Generator
 */
/*@Author=Jean Louidort*/
/*@Generation Date=Mon Nov 18 01:14:07 EST 2013*/
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
import com.rsoft.medicasoft.client.presenter.EmploiPresenter;
import com.rsoft.medicasoft.client.toolsbar.EditableGridToolsBar;
import com.rsoft.medicasoft.client.view.viewinterface.IPresenter;
import com.rsoft.medicasoft.client.view.viewinterface.IWidget;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.FilterWrapper;
import com.rsoft.medicasoft.shared.SystemMessage;
import com.rsoft.medicasoft.shared.XFilter;
import com.rsoft.medicasoft.shared.model.Emploi;
import com.rsoft.medicasoft.shared.model.EmploiPropertiesAccess;
import com.rsoft.medicasoft.shared.model.Institution;
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
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
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
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.NumberField;
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
import com.sencha.gxt.widget.core.client.grid.filters.NumericFilter;
import com.sencha.gxt.widget.core.client.grid.filters.StringFilter;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;

public class EmploiCView extends ViewGridBase<Emploi> {
	private EmploiPresenter presenter;

	public interface Renderer extends ToolTipConfig.ToolTipRenderer<Object>,
			XTemplates {
		@Override
		@XTemplate(source = "help/emploiTemplate.html")
		public SafeHtml renderToolTip(Object data);
	}

	private Renderer renderer = GWT.create(Renderer.class);

	interface GxtUiBinder extends UiBinder<Widget, EmploiCView> {
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
		List<Emploi> Emplois = grid.getSelectionModel().getSelectedItems();
		for (Emploi Emploi : Emplois) {
			if (Emploi.getEntityId() != null) {
				presenter.addModelToBeRemoved(Emploi);
				Emploi foundModel = listStore.findModelWithKey(listStore
						.getKeyProvider().getKey(Emploi));
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
		List<Emploi> Emplois = grid.getSelectionModel().getSelectedItems();
		for (Emploi Emploi : Emplois) {
			Emploi foundModel = listStore.findModelWithKey(listStore
					.getKeyProvider().getKey(Emploi));
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
			RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Emploi>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Emploi>>() {
				@Override
				public void load(FilterPagingLoadConfig loadConfig,
						AsyncCallback<PagingLoadResult<Emploi>> callback) {
					listStore.clear();
					listStore.commitChanges();
					FilterWrapper wrapper = new FilterWrapper(null);

					if (filters.getFilter("emploiId") != null
							&& filters.getFilter("emploiId").getFilterConfig() != null
							&& !filters.getFilter("emploiId").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("emploiId")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "emploiId", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("nomPoste") != null
							&& filters.getFilter("nomPoste").getFilterConfig() != null
							&& !filters.getFilter("nomPoste").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("nomPoste")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "nomPoste", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("dateEmbauche") != null
							&& filters.getFilter("dateEmbauche")
									.getFilterConfig() != null
							&& !filters.getFilter("dateEmbauche")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters
								.getFilter("dateEmbauche").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "dateEmbauche", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("finEmbauche") != null
							&& filters.getFilter("finEmbauche")
									.getFilterConfig() != null
							&& !filters.getFilter("finEmbauche")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("finEmbauche")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "finEmbauche", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("monnaie") != null
							&& filters.getFilter("monnaie").getFilterConfig() != null
							&& !filters.getFilter("monnaie").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("monnaie")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "monnaie", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("salaire") != null
							&& filters.getFilter("salaire").getFilterConfig() != null
							&& !filters.getFilter("salaire").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("salaire")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "salaire", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("entrepriseId") != null
							&& filters.getFilter("entrepriseId")
									.getFilterConfig() != null
							&& !filters.getFilter("entrepriseId")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters
								.getFilter("entrepriseId").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "entrepriseId", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("statut") != null
							&& filters.getFilter("statut").getFilterConfig() != null
							&& !filters.getFilter("statut").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("statut")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "statut", fc
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
					UserRequestCallbackAdapter<Emploi> urc = new UserRequestCallbackAdapter<Emploi>();
					urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
					urc.setAsyncCallback(callback);
					urc.setLoadConfig(loadConfig);
					presenter.getEventBus().executeEmploi(
							ActionCommand.COLUMNAR_SEARCH, urc);
					showInfoBanner(!userHideBannerInfo);
				}
			};
			pagingLoader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<Emploi>>(
					proxy) {
				protected FilterPagingLoadConfig newLoadConfig() {
					return new FilterPagingLoadConfigBean();
				}
			};
			pagingLoader.setRemoteSort(true);
			pagingLoader
					.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, Emploi, PagingLoadResult<Emploi>>(
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
		RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Emploi>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Emploi>>() {
			@Override
			public void load(FilterPagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<Emploi>> callback) {
				UserRequestCallbackAdapter<Emploi> urc = new UserRequestCallbackAdapter<Emploi>() {
					@Override
					public void onSingleOperationSuccessed(Emploi model) {
						hideProgressBar();
					}

					@Override
					public void onMultipleOperationsFailed(List<Emploi> model,
							Throwable cause) {
						hideProgressBar();
					}
				};
				urc.setAsyncCallback(callback);
				urc.setLoadConfig(loadConfig);
				urc.setPageSize(toolsBar.getPageSize());
				urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
				presenter.getEventBus().executeEmploi(ActionCommand.RESET_GRID,
						urc);
			}
		};
		pagingLoader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<Emploi>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		pagingLoader.setRemoteSort(true);
		pagingLoader
				.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, Emploi, PagingLoadResult<Emploi>>(
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

	private PagingLoader<FilterPagingLoadConfig, PagingLoadResult<Emploi>> pagingLoader = null;
	private RowNumberer<Emploi> numberer;

	@Override
	public Widget asWidget() {
		ENTITY_NAME = "Emploi";
		EmploiPropertiesAccess propertiesAccess = GWT
				.create(EmploiPropertiesAccess.class);
		listStore = new ListStore<Emploi>(propertiesAccess.key());
		IdentityValueProvider<Emploi> identity = new IdentityValueProvider<Emploi>();
		numberer = new RowNumberer<Emploi>(identity);
		list = new ArrayList<ColumnConfig<Emploi, ?>>();
		List<ColumnConfig<Emploi, ?>> list = new ArrayList<ColumnConfig<Emploi, ?>>();
		ColumnConfig<Emploi, String> nomPosteColumn = new ColumnConfig<Emploi, String>(
				propertiesAccess.nomPoste(), 20, messages.nomPoste());
		list.add(nomPosteColumn);
		ColumnConfig<Emploi, Date> dateEmbaucheColumn = new ColumnConfig<Emploi, Date>(
				propertiesAccess.dateEmbauche(), 10, messages.dateEmbauche());
		list.add(dateEmbaucheColumn);
		ColumnConfig<Emploi, Date> finEmbaucheColumn = new ColumnConfig<Emploi, Date>(
				propertiesAccess.finEmbauche(), 10, messages.finEmbauche());
		list.add(finEmbaucheColumn);
		ColumnConfig<Emploi, String> monnaieColumn = new ColumnConfig<Emploi, String>(
				propertiesAccess.monnaie(), 3, messages.monnaie());
		list.add(monnaieColumn);
		ColumnConfig<Emploi, Double> salaireColumn = new ColumnConfig<Emploi, Double>(
				propertiesAccess.salaire(), 38, messages.salaire());
		list.add(salaireColumn);
//		ColumnConfig<Emploi, Institution> entrepriseIdColumn = new ColumnConfig<Emploi, Institution>(
//				propertiesAccess.institution(), 10, messages.entrepriseId());
//		list.add(entrepriseIdColumn);
		ColumnConfig<Emploi, String> statutColumn = new ColumnConfig<Emploi, String>(
				propertiesAccess.statut(), 20, messages.statut());
		list.add(statutColumn);
		ColumnConfig<Emploi, String> createdByColumn = new ColumnConfig<Emploi, String>(
				propertiesAccess.createdBy(), 30, messages.createdBy());
		list.add(createdByColumn);
		ColumnConfig<Emploi, Date> createdOnColumn = new ColumnConfig<Emploi, Date>(
				propertiesAccess.createdOn(), 10, messages.createdOn());
		list.add(createdOnColumn);
		ColumnConfig<Emploi, String> updatedByColumn = new ColumnConfig<Emploi, String>(
				propertiesAccess.updatedBy(), 30, messages.updatedBy());
		list.add(updatedByColumn);
		ColumnConfig<Emploi, Date> updatedOnColumn = new ColumnConfig<Emploi, Date>(
				propertiesAccess.updatedOn(), 10, messages.updatedOn());
		list.add(updatedOnColumn);
		columnModel = new ColumnModel<Emploi>(list);
		widget = gxtUiBinder.createAndBindUi(this);
		panel.addDomHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				formUpdated();
			}
		}, KeyPressEvent.getType());
		showInfoBanner(bannerInfoIsShowed);
		// Apply editor to grid
		final GridEditing<Emploi> editing = new GridInlineEditing<Emploi>(grid);
		editing.addBeforeStartEditHandler(new BeforeStartEditHandler<Emploi>() {
			@Override
			public void onBeforeStartEdit(BeforeStartEditEvent<Emploi> event) {
				editing.completeEditing();
				int row = event.getEditCell().getRow();
				Emploi model = listStore.get(row);
				model.setUpdating(true);
			}
		});
		editing.addCompleteEditHandler(new CompleteEditHandler<Emploi>() {
			@Override
			public void onCompleteEdit(CompleteEditEvent<Emploi> event) {
				int row = event.getEditCell().getRow();
				Emploi model = listStore.get(row);
				model.setUpdating(false);
			}
		});

		NumberField<Integer> emploiIdField = new NumberField(
				integerPropertyEditor);
		emploiIdField.setAllowBlank(false);

		TextField nomPosteField = new TextField();
		nomPosteField.addValidator(new MaxLengthValidator(20));
		nomPosteField.setAllowBlank(false);
		editing.addEditor(nomPosteColumn, nomPosteField);

		DateField dateEmbaucheField = new DateField(datePropertyEditor);
		editing.addEditor(dateEmbaucheColumn, dateEmbaucheField);

		DateField finEmbaucheField = new DateField(datePropertyEditor);
		editing.addEditor(finEmbaucheColumn, finEmbaucheField);

		TextField monnaieField = new TextField();
		monnaieField.addValidator(new MaxLengthValidator(3));
		monnaieField.setAllowBlank(false);
		editing.addEditor(monnaieColumn, monnaieField);

		NumberField<Double> salaireField = new NumberField(doublePropertyEditor);
		salaireField.setAllowBlank(false);
		editing.addEditor(salaireColumn, salaireField);

		com.rsoft.medicasoft.client.lovs.LovInstitution.ILovInstitution iLovInstitution7 = new com.rsoft.medicasoft.client.lovs.LovInstitution.ILovInstitution() {
			@Override
			public void loadInstitution(Institution model,
					PagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<Institution>> callback) {
				UserRequestCallbackAdapter<Institution> urc = new UserRequestCallbackAdapter<Institution>();
				urc.setRequestDescriptor(new RequestDescriptor("Institution"));
				urc.setAsyncCallback(callback);
				urc.setLoadConfig(loadConfig);
				urc.setModel(model);
				presenter.getEventBus().executeEmploi(
						ActionCommand.COLUMNAR_SEARCH, urc);
			}
		};
//		ComboBox<Institution> institution = new com.rsoft.medicasoft.client.lovs.LovInstitution(
//				iLovInstitution7);
//		institution.setAllowBlank(false);
//		editing.addEditor(entrepriseIdColumn,
//				new Converter<Institution, Institution>() {
//					@Override
//					public Institution convertFieldValue(Institution object) {
//						return object;
//					}
//
//					@Override
//					public Institution convertModelValue(Institution object) {
//						return object;
//					}
//				}, institution);

		TextField statutField = new TextField();
		statutField.addValidator(new MaxLengthValidator(20));
		statutField.setAllowBlank(false);
		editing.addEditor(statutColumn, statutField);

		toolBarArea.setHeaderVisible(false);
		toolsBar = new EditableGridToolsBar();
		toolsBar.setParamInoutSuffix("Emploi");
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
		filters = new GridFilters<Emploi>();
		listStore
				.addStoreRecordChangeHandler(new StoreRecordChangeHandler<Emploi>() {
					@Override
					public void onRecordChange(
							StoreRecordChangeEvent<Emploi> event) {
						Emploi changedModel = event.getRecord().getModel();
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

		StringFilter<Emploi> nomPosteFilter = new StringFilter<Emploi>(
				propertiesAccess.nomPoste());
		filters.addFilter(nomPosteFilter);
		DateFilter<Emploi> dateEmbaucheFilter = new DateFilter<Emploi>(
				propertiesAccess.dateEmbauche());
		filters.addFilter(dateEmbaucheFilter);
		DateFilter<Emploi> finEmbaucheFilter = new DateFilter<Emploi>(
				propertiesAccess.finEmbauche());
		filters.addFilter(finEmbaucheFilter);
		StringFilter<Emploi> monnaieFilter = new StringFilter<Emploi>(
				propertiesAccess.monnaie());
		filters.addFilter(monnaieFilter);
		NumericFilter<Emploi, Double> salaireFilter = new NumericFilter<Emploi, Double>(
				propertiesAccess.salaire(), doublePropertyEditor);
		filters.addFilter(salaireFilter);
		StringFilter<Emploi> statutFilter = new StringFilter<Emploi>(
				propertiesAccess.statut());
		filters.addFilter(statutFilter);
		DateFilter<Emploi> createdOnFilter = new DateFilter<Emploi>(
				propertiesAccess.createdOn());
		filters.addFilter(createdOnFilter);
		StringFilter<Emploi> updatedByFilter = new StringFilter<Emploi>(
				propertiesAccess.updatedBy());
		filters.addFilter(updatedByFilter);
		DateFilter<Emploi> updatedOnFilter = new DateFilter<Emploi>(
				propertiesAccess.updatedOn());
		filters.addFilter(updatedOnFilter);
		StringFilter<Emploi> createdByFilter = new StringFilter<Emploi>(
				propertiesAccess.createdBy());
		filters.addFilter(createdByFilter);
		numberer.initPlugin(grid);
		grid.setLoadMask(true);
		grid.setColumnReordering(true);
		grid.getView().setStripeRows(true);
		grid.getView().setTrackMouseOver(false);
		grid.getView().setAutoFill(true);
		GridDropTarget<Emploi> target = new GridDropTarget<Emploi>(grid);
		target.setAllowSelfAsSource(true);
		target.setFeedback(Feedback.INSERT);

		ViewUtils.unNotify(htmlMessage);
		grid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				Emploi model = grid.getSelectionModel().getSelectedItem();
				ViewUtils.showAuditInfos(htmlMessage,
						model.getGlobalAuditInfo());
			}
		});
		grid.getView().setViewConfig(new GridViewConfig<Emploi>() {
			@Override
			public String getColStyle(Emploi model,
					ValueProvider<? super Emploi, ?> valueProvider,
					int rowIndex, int colIndex) {
				return null;
			}

			@Override
			public String getRowStyle(Emploi model, int rowIndex) {
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
		this.presenter = (EmploiPresenter) p;
	}

	@Override
	public IPresenter getPresenter() {
		return this.presenter;
	}

	@Override
	public void updateView(Emploi model) {
		Info.display(messages.message(), messages.finished());
		if (box != null) {
			box.hide();
			box = null;
		}
	}

	@Override
	public void updateView(List<Emploi> models) {
		try {
			if (models != null) {
				boolean allOperationsSuccess = true;
				for (Emploi model : models) {
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
								Emploi oldModel = listStore
										.findModelWithKey(listStore
												.getKeyProvider().getKey(model));
								if (oldModel != null) {
									oldModel.merge(model);
								}
							}
						} else {
							Emploi oldModel = listStore
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
	public Emploi updateModel(Emploi model) {
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
	public void onDataChanged(Emploi model, boolean reloadDetails,
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
			UserRequestCallbackAdapter<Emploi> urc) {
		presenter.getEventBus().executeEmploi(command, urc);
	}

	@Override
	protected void setModelsToBePersisted(ListStore<Emploi> listStore) {
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