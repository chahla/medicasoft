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
import com.rsoft.medicasoft.client.presenter.AssurancePresenter;
import com.rsoft.medicasoft.client.toolsbar.EditableGridToolsBar;
import com.rsoft.medicasoft.client.view.viewinterface.IPresenter;
import com.rsoft.medicasoft.client.view.viewinterface.IWidget;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.FilterWrapper;
import com.rsoft.medicasoft.shared.SystemMessage;
import com.rsoft.medicasoft.shared.XFilter;
import com.rsoft.medicasoft.shared.model.Assurance;
import com.rsoft.medicasoft.shared.model.AssurancePropertiesAccess;
import com.rsoft.medicasoft.shared.model.Emploi;
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

public class AssuranceCView extends ViewGridBase<Assurance> {
	private AssurancePresenter presenter;

	public interface Renderer extends ToolTipConfig.ToolTipRenderer<Object>,
			XTemplates {
		@Override
		@XTemplate(source = "help/assuranceTemplate.html")
		public SafeHtml renderToolTip(Object data);
	}

	private Renderer renderer = GWT.create(Renderer.class);

	interface GxtUiBinder extends UiBinder<Widget, AssuranceCView> {
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
		List<Assurance> Assurances = grid.getSelectionModel()
				.getSelectedItems();
		for (Assurance Assurance : Assurances) {
			if (Assurance.getEntityId() != null) {
				presenter.addModelToBeRemoved(Assurance);
				Assurance foundModel = listStore.findModelWithKey(listStore
						.getKeyProvider().getKey(Assurance));
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
		List<Assurance> Assurances = grid.getSelectionModel()
				.getSelectedItems();
		for (Assurance Assurance : Assurances) {
			Assurance foundModel = listStore.findModelWithKey(listStore
					.getKeyProvider().getKey(Assurance));
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
			RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Assurance>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Assurance>>() {
				@Override
				public void load(FilterPagingLoadConfig loadConfig,
						AsyncCallback<PagingLoadResult<Assurance>> callback) {
					listStore.clear();
					listStore.commitChanges();
					FilterWrapper wrapper = new FilterWrapper(null);

					if (filters.getFilter("assuranceId") != null
							&& filters.getFilter("assuranceId")
									.getFilterConfig() != null
							&& !filters.getFilter("assuranceId")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("assuranceId")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "assuranceId", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("typeAssurance") != null
							&& filters.getFilter("typeAssurance")
									.getFilterConfig() != null
							&& !filters.getFilter("typeAssurance")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"typeAssurance").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "typeAssurance", fc
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
					if (filters.getFilter("dateEffective") != null
							&& filters.getFilter("dateEffective")
									.getFilterConfig() != null
							&& !filters.getFilter("dateEffective")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"dateEffective").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "dateEffective", fc
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
					if (filters.getFilter("dateStatut") != null
							&& filters.getFilter("dateStatut")
									.getFilterConfig() != null
							&& !filters.getFilter("dateStatut")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("dateStatut")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "dateStatut", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("montant") != null
							&& filters.getFilter("montant").getFilterConfig() != null
							&& !filters.getFilter("montant").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("montant")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "montant", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("patientId") != null
							&& filters.getFilter("patientId").getFilterConfig() != null
							&& !filters.getFilter("patientId")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("patientId")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "patientId", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("assureurId") != null
							&& filters.getFilter("assureurId")
									.getFilterConfig() != null
							&& !filters.getFilter("assureurId")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("assureurId")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "assureurId", fc
										.getType(), fc.getValue()));
							}
						}
					}
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
					UserRequestCallbackAdapter<Assurance> urc = new UserRequestCallbackAdapter<Assurance>();
					urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
					urc.setAsyncCallback(callback);
					urc.setLoadConfig(loadConfig);
					presenter.getEventBus().executeAssurance(
							ActionCommand.COLUMNAR_SEARCH, urc);
					showInfoBanner(!userHideBannerInfo);
				}
			};
			pagingLoader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<Assurance>>(
					proxy) {
				protected FilterPagingLoadConfig newLoadConfig() {
					return new FilterPagingLoadConfigBean();
				}
			};
			pagingLoader.setRemoteSort(true);
			pagingLoader
					.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, Assurance, PagingLoadResult<Assurance>>(
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
		RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Assurance>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Assurance>>() {
			@Override
			public void load(FilterPagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<Assurance>> callback) {
				UserRequestCallbackAdapter<Assurance> urc = new UserRequestCallbackAdapter<Assurance>() {
					@Override
					public void onSingleOperationSuccessed(Assurance model) {
						hideProgressBar();
					}

					@Override
					public void onMultipleOperationsFailed(
							List<Assurance> model, Throwable cause) {
						hideProgressBar();
					}
				};
				urc.setAsyncCallback(callback);
				urc.setLoadConfig(loadConfig);
				urc.setPageSize(toolsBar.getPageSize());
				urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
				presenter.getEventBus().executeAssurance(
						ActionCommand.RESET_GRID, urc);
			}
		};
		pagingLoader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<Assurance>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		pagingLoader.setRemoteSort(true);
		pagingLoader
				.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, Assurance, PagingLoadResult<Assurance>>(
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

	private PagingLoader<FilterPagingLoadConfig, PagingLoadResult<Assurance>> pagingLoader = null;
	private RowNumberer<Assurance> numberer;

	@Override
	public Widget asWidget() {
		ENTITY_NAME = "Assurance";
		AssurancePropertiesAccess propertiesAccess = GWT
				.create(AssurancePropertiesAccess.class);
		listStore = new ListStore<Assurance>(propertiesAccess.key());
		IdentityValueProvider<Assurance> identity = new IdentityValueProvider<Assurance>();
		numberer = new RowNumberer<Assurance>(identity);
		list = new ArrayList<ColumnConfig<Assurance, ?>>();
		List<ColumnConfig<Assurance, ?>> list = new ArrayList<ColumnConfig<Assurance, ?>>();

		ColumnConfig<Assurance, String> typeAssuranceColumn = new ColumnConfig<Assurance, String>(
				propertiesAccess.typeAssurance(), 20, messages.typeAssurance());
		list.add(typeAssuranceColumn);
		ColumnConfig<Assurance, String> descriptionColumn = new ColumnConfig<Assurance, String>(
				propertiesAccess.description(), 20, messages.description());
		list.add(descriptionColumn);
		ColumnConfig<Assurance, Date> dateEffectiveColumn = new ColumnConfig<Assurance, Date>(
				propertiesAccess.dateEffective(), 10, messages.dateEffective());
		list.add(dateEffectiveColumn);
		ColumnConfig<Assurance, String> statutColumn = new ColumnConfig<Assurance, String>(
				propertiesAccess.statut(), 20, messages.statut());
		list.add(statutColumn);
		ColumnConfig<Assurance, Date> dateStatutColumn = new ColumnConfig<Assurance, Date>(
				propertiesAccess.dateStatut(), 10, messages.dateStatut());
		list.add(dateStatutColumn);
		ColumnConfig<Assurance, Double> montantColumn = new ColumnConfig<Assurance, Double>(
				propertiesAccess.montant(), 38, messages.montant());
		list.add(montantColumn);
		ColumnConfig<Assurance, Integer> patientIdColumn = new ColumnConfig<Assurance, Integer>(
				propertiesAccess.patientId(), 10, messages.patientId());
		list.add(patientIdColumn);
		// ColumnConfig<Assurance, Institution> assureurIdColumn = new
		// ColumnConfig<Assurance, Institution>(propertiesAccess.institution(),
		// 10, messages.assureurId());
		// list.add(assureurIdColumn);
		// ColumnConfig<Assurance, Emploi> emploiIdColumn = new
		// ColumnConfig<Assurance, Emploi>(propertiesAccess.emploiId(), 10,
		// messages.emploiId());
		// list.add(emploiIdColumn);
		ColumnConfig<Assurance, String> createdByColumn = new ColumnConfig<Assurance, String>(
				propertiesAccess.createdBy(), 30, messages.createdBy());
		list.add(createdByColumn);
		ColumnConfig<Assurance, Date> createdOnColumn = new ColumnConfig<Assurance, Date>(
				propertiesAccess.createdOn(), 10, messages.createdOn());
		list.add(createdOnColumn);
		ColumnConfig<Assurance, String> updatedByColumn = new ColumnConfig<Assurance, String>(
				propertiesAccess.updatedBy(), 30, messages.updatedBy());
		list.add(updatedByColumn);
		ColumnConfig<Assurance, Date> updatedOnColumn = new ColumnConfig<Assurance, Date>(
				propertiesAccess.updatedOn(), 10, messages.updatedOn());
		list.add(updatedOnColumn);
		columnModel = new ColumnModel<Assurance>(list);
		widget = gxtUiBinder.createAndBindUi(this);
		panel.addDomHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				formUpdated();
			}
		}, KeyPressEvent.getType());
		showInfoBanner(bannerInfoIsShowed);
		// Apply editor to grid
		final GridEditing<Assurance> editing = new GridInlineEditing<Assurance>(
				grid);
		editing.addBeforeStartEditHandler(new BeforeStartEditHandler<Assurance>() {
			@Override
			public void onBeforeStartEdit(BeforeStartEditEvent<Assurance> event) {
				editing.completeEditing();
				int row = event.getEditCell().getRow();
				Assurance model = listStore.get(row);
				model.setUpdating(true);
			}
		});
		editing.addCompleteEditHandler(new CompleteEditHandler<Assurance>() {
			@Override
			public void onCompleteEdit(CompleteEditEvent<Assurance> event) {
				int row = event.getEditCell().getRow();
				Assurance model = listStore.get(row);
				model.setUpdating(false);
			}
		});

		TextField typeAssuranceField = new TextField();
		typeAssuranceField.addValidator(new MaxLengthValidator(20));
		typeAssuranceField.setAllowBlank(false);
		editing.addEditor(typeAssuranceColumn, typeAssuranceField);

		TextField descriptionField = new TextField();
		descriptionField.addValidator(new MaxLengthValidator(20));
		descriptionField.setAllowBlank(false);
		editing.addEditor(descriptionColumn, descriptionField);

		DateField dateEffectiveField = new DateField(datePropertyEditor);
		dateEffectiveField.setAllowBlank(false);
		editing.addEditor(dateEffectiveColumn, dateEffectiveField);

		TextField statutField = new TextField();
		statutField.addValidator(new MaxLengthValidator(20));
		statutField.setAllowBlank(false);
		editing.addEditor(statutColumn, statutField);

		DateField dateStatutField = new DateField(datePropertyEditor);
		dateStatutField.setAllowBlank(false);
		editing.addEditor(dateStatutColumn, dateStatutField);

		NumberField<Double> montantField = new NumberField(doublePropertyEditor);
		montantField.setAllowBlank(false);
		editing.addEditor(montantColumn, montantField);

		NumberField<Integer> patientIdField = new NumberField(
				integerPropertyEditor);
		editing.addEditor(patientIdColumn, patientIdField);

		com.rsoft.medicasoft.client.lovs.LovInstitution.ILovInstitution iLovInstitution9 = new com.rsoft.medicasoft.client.lovs.LovInstitution.ILovInstitution() {
			@Override
			public void loadInstitution(Institution model,
					PagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<Institution>> callback) {
				UserRequestCallbackAdapter<Institution> urc = new UserRequestCallbackAdapter<Institution>();
				urc.setRequestDescriptor(new RequestDescriptor("Institution"));
				urc.setAsyncCallback(callback);
				urc.setLoadConfig(loadConfig);
				urc.setModel(model);
				presenter.getEventBus().executeAssurance(
						ActionCommand.COLUMNAR_SEARCH, urc);
			}
		};
		// ComboBox<Institution> institution = new
		// com.rsoft.medicasoft.client.lovs.LovInstitution(
		// iLovInstitution9);
		// institution.setAllowBlank(false);
		// editing.addEditor(assureurIdColumn,
		// new Converter<Institution, Institution>() {
		// @Override
		// public Institution convertFieldValue(Institution object) {
		// return object;
		// }
		//
		// @Override
		// public Institution convertModelValue(Institution object) {
		// return object;
		// }
		// }, institution);

		com.rsoft.medicasoft.client.lovs.LovEmploi.ILovEmploi iLovEmploi10 = new com.rsoft.medicasoft.client.lovs.LovEmploi.ILovEmploi() {
			@Override
			public void loadEmploi(Emploi model, PagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<Emploi>> callback) {
				UserRequestCallbackAdapter<Emploi> urc = new UserRequestCallbackAdapter<Emploi>();
				urc.setRequestDescriptor(new RequestDescriptor("Emploi"));
				urc.setAsyncCallback(callback);
				urc.setLoadConfig(loadConfig);
				urc.setModel(model);
				presenter.getEventBus().executeAssurance(
						ActionCommand.COLUMNAR_SEARCH, urc);
			}
		};
		// ComboBox<Emploi> emploi = new
		// com.rsoft.medicasoft.client.lovs.LovEmploi(
		// iLovEmploi10);
		// editing.addEditor(emploiIdColumn, new Converter<Emploi, Emploi>() {
		// @Override
		// public Emploi convertFieldValue(Emploi object) {
		// return object;
		// }
		//
		// @Override
		// public Emploi convertModelValue(Emploi object) {
		// return object;
		// }
		// }, emploi);

		toolBarArea.setHeaderVisible(false);
		toolsBar = new EditableGridToolsBar();
		toolsBar.setParamInoutSuffix("Assurance");
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
		filters = new GridFilters<Assurance>();
		listStore
				.addStoreRecordChangeHandler(new StoreRecordChangeHandler<Assurance>() {
					@Override
					public void onRecordChange(
							StoreRecordChangeEvent<Assurance> event) {
						Assurance changedModel = event.getRecord().getModel();
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

		StringFilter<Assurance> typeAssuranceFilter = new StringFilter<Assurance>(
				propertiesAccess.typeAssurance());
		filters.addFilter(typeAssuranceFilter);
		StringFilter<Assurance> descriptionFilter = new StringFilter<Assurance>(
				propertiesAccess.description());
		filters.addFilter(descriptionFilter);
		DateFilter<Assurance> dateEffectiveFilter = new DateFilter<Assurance>(
				propertiesAccess.dateEffective());
		filters.addFilter(dateEffectiveFilter);
		StringFilter<Assurance> statutFilter = new StringFilter<Assurance>(
				propertiesAccess.statut());
		filters.addFilter(statutFilter);
		DateFilter<Assurance> dateStatutFilter = new DateFilter<Assurance>(
				propertiesAccess.dateStatut());
		filters.addFilter(dateStatutFilter);
		NumericFilter<Assurance, Double> montantFilter = new NumericFilter<Assurance, Double>(
				propertiesAccess.montant(), doublePropertyEditor);
		filters.addFilter(montantFilter);
		NumericFilter<Assurance, Integer> patientIdFilter = new NumericFilter<Assurance, Integer>(
				propertiesAccess.patientId(), integerPropertyEditor);
		filters.addFilter(patientIdFilter);
		StringFilter<Assurance> createdByFilter = new StringFilter<Assurance>(
				propertiesAccess.createdBy());
		filters.addFilter(createdByFilter);
		DateFilter<Assurance> createdOnFilter = new DateFilter<Assurance>(
				propertiesAccess.createdOn());
		filters.addFilter(createdOnFilter);
		StringFilter<Assurance> updatedByFilter = new StringFilter<Assurance>(
				propertiesAccess.updatedBy());
		filters.addFilter(updatedByFilter);
		DateFilter<Assurance> updatedOnFilter = new DateFilter<Assurance>(
				propertiesAccess.updatedOn());
		filters.addFilter(updatedOnFilter);
		numberer.initPlugin(grid);
		grid.setLoadMask(true);
		grid.setColumnReordering(true);
		grid.getView().setStripeRows(true);
		grid.getView().setTrackMouseOver(false);
		grid.getView().setAutoFill(true);
		GridDropTarget<Assurance> target = new GridDropTarget<Assurance>(grid);
		target.setAllowSelfAsSource(true);
		target.setFeedback(Feedback.INSERT);

		ViewUtils.unNotify(htmlMessage);
		grid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				Assurance model = grid.getSelectionModel().getSelectedItem();
				ViewUtils.showAuditInfos(htmlMessage,
						model.getGlobalAuditInfo());
			}
		});
		grid.getView().setViewConfig(new GridViewConfig<Assurance>() {
			@Override
			public String getColStyle(Assurance model,
					ValueProvider<? super Assurance, ?> valueProvider,
					int rowIndex, int colIndex) {
				return null;
			}

			@Override
			public String getRowStyle(Assurance model, int rowIndex) {
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
		this.presenter = (AssurancePresenter) p;
	}

	@Override
	public IPresenter getPresenter() {
		return this.presenter;
	}

	@Override
	public void updateView(Assurance model) {
		Info.display(messages.message(), messages.finished());
		if (box != null) {
			box.hide();
			box = null;
		}
	}

	@Override
	public void updateView(List<Assurance> models) {
		try {
			if (models != null) {
				boolean allOperationsSuccess = true;
				for (Assurance model : models) {
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
								Assurance oldModel = listStore
										.findModelWithKey(listStore
												.getKeyProvider().getKey(model));
								if (oldModel != null) {
									oldModel.merge(model);
								}
							}
						} else {
							Assurance oldModel = listStore
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
	public Assurance updateModel(Assurance model) {
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
	public void onDataChanged(Assurance model, boolean reloadDetails,
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
			UserRequestCallbackAdapter<Assurance> urc) {
		presenter.getEventBus().executeAssurance(command, urc);
	}

	@Override
	protected void setModelsToBePersisted(ListStore<Assurance> listStore) {
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