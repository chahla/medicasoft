package com.rsoft.medicasoft.client.view;
/*
	Robelkend Templates Generator
*/
/*@Author=Jean Louidort*/
/*@Generation Date=Mon Nov 18 01:14:05 EST 2013*/
/*@Version=1.0*/
import com.google.gwt.i18n.client.DateTimeFormat;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.NumberField;

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
import com.rsoft.medicasoft.client.presenter.SportFrequentePresenter;
import com.rsoft.medicasoft.client.toolsbar.EditableGridToolsBar;
import com.rsoft.medicasoft.client.view.viewinterface.IPresenter;
import com.rsoft.medicasoft.client.view.viewinterface.IWidget;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.FilterWrapper;
import com.rsoft.medicasoft.shared.SystemMessage;
import com.rsoft.medicasoft.shared.XFilter;
import com.rsoft.medicasoft.shared.model.RequestDescriptor;
import com.rsoft.medicasoft.shared.model.SportPratique;
import com.rsoft.medicasoft.shared.model.SportPratiquePropertiesAccess;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.LayoutRegion;
import com.sencha.gxt.core.client.Style.Side;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.Converter;
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
import com.sencha.gxt.widget.core.client.form.ComboBox;
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
import com.sencha.gxt.widget.core.client.grid.filters.NumericFilter;
import com.sencha.gxt.widget.core.client.grid.filters.DateFilter;



public class SportFrequenteCView extends ViewGridBase<SportPratique> {
	private SportFrequentePresenter presenter;

	public interface Renderer extends ToolTipConfig.ToolTipRenderer<Object>,
			XTemplates {
		@Override
		@XTemplate(source = "help/sportFrequenteTemplate.html")
		public SafeHtml renderToolTip(Object data);
	}

	private Renderer renderer = GWT.create(Renderer.class);

	interface GxtUiBinder extends UiBinder<Widget, SportFrequenteCView> {
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
		List<SportPratique> SportFrequentes = grid.getSelectionModel()
				.getSelectedItems();
		for (SportPratique SportFrequente : SportFrequentes) {
			if (SportFrequente.getEntityId() != null) {
				presenter.addModelToBeRemoved(SportFrequente);
				SportPratique foundModel = listStore
						.findModelWithKey(listStore.getKeyProvider().getKey(
								SportFrequente));
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
		List<SportPratique> SportFrequentes = grid.getSelectionModel()
				.getSelectedItems();
		for (SportPratique SportFrequente : SportFrequentes) {
			SportPratique foundModel = listStore.findModelWithKey(listStore
					.getKeyProvider().getKey(SportFrequente));
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
			RpcProxy<FilterPagingLoadConfig, PagingLoadResult<SportPratique>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<SportPratique>>() {
				@Override
				public void load(FilterPagingLoadConfig loadConfig,
						AsyncCallback<PagingLoadResult<SportPratique>> callback) {
					listStore.clear();
					listStore.commitChanges();
					FilterWrapper wrapper = new FilterWrapper(null);

        if (filters.getFilter("sportFrequenteId") != null && filters.getFilter("sportFrequenteId").getFilterConfig() != null && !filters.getFilter("sportFrequenteId").getFilterConfig().isEmpty()){
          for(FilterConfig fc : filters.getFilter("sportFrequenteId").getFilterConfig()) {
            if(fc.getValue() != null) {
              wrapper.addFilter(new XFilter(fc.getComparison(),"sportFrequenteId", fc.getType(), fc.getValue()));
            }
          }
        }
        if (filters.getFilter("nomSport") != null && filters.getFilter("nomSport").getFilterConfig() != null && !filters.getFilter("nomSport").getFilterConfig().isEmpty()){
          for(FilterConfig fc : filters.getFilter("nomSport").getFilterConfig()) {
            if(fc.getValue() != null) {
              wrapper.addFilter(new XFilter(fc.getComparison(),"nomSport", fc.getType(), fc.getValue()));
            }
          }
        }
        if (filters.getFilter("frequence") != null && filters.getFilter("frequence").getFilterConfig() != null && !filters.getFilter("frequence").getFilterConfig().isEmpty()){
          for(FilterConfig fc : filters.getFilter("frequence").getFilterConfig()) {
            if(fc.getValue() != null) {
              wrapper.addFilter(new XFilter(fc.getComparison(),"frequence", fc.getType(), fc.getValue()));
            }
          }
        }
        if (filters.getFilter("unite") != null && filters.getFilter("unite").getFilterConfig() != null && !filters.getFilter("unite").getFilterConfig().isEmpty()){
          for(FilterConfig fc : filters.getFilter("unite").getFilterConfig()) {
            if(fc.getValue() != null) {
              wrapper.addFilter(new XFilter(fc.getComparison(),"unite", fc.getType(), fc.getValue()));
            }
          }
        }
        if (filters.getFilter("patientId") != null && filters.getFilter("patientId").getFilterConfig() != null && !filters.getFilter("patientId").getFilterConfig().isEmpty()){
          for(FilterConfig fc : filters.getFilter("patientId").getFilterConfig()) {
            if(fc.getValue() != null) {
              wrapper.addFilter(new XFilter(fc.getComparison(),"patientId", fc.getType(), fc.getValue()));
            }
          }
        }
        if (filters.getFilter("dateDebut") != null && filters.getFilter("dateDebut").getFilterConfig() != null && !filters.getFilter("dateDebut").getFilterConfig().isEmpty()){
          for(FilterConfig fc : filters.getFilter("dateDebut").getFilterConfig()) {
            if(fc.getValue() != null) {
              wrapper.addFilter(new XFilter(fc.getComparison(),"dateDebut", fc.getType(), fc.getValue()));
            }
          }
        }
        if (filters.getFilter("dateFin") != null && filters.getFilter("dateFin").getFilterConfig() != null && !filters.getFilter("dateFin").getFilterConfig().isEmpty()){
          for(FilterConfig fc : filters.getFilter("dateFin").getFilterConfig()) {
            if(fc.getValue() != null) {
              wrapper.addFilter(new XFilter(fc.getComparison(),"dateFin", fc.getType(), fc.getValue()));
            }
          }
        }
        if (filters.getFilter("createdBy") != null && filters.getFilter("createdBy").getFilterConfig() != null && !filters.getFilter("createdBy").getFilterConfig().isEmpty()){
          for(FilterConfig fc : filters.getFilter("createdBy").getFilterConfig()) {
            if(fc.getValue() != null) {
              wrapper.addFilter(new XFilter(fc.getComparison(),"createdBy", fc.getType(), fc.getValue()));
            }
          }
        }
        if (filters.getFilter("createdOn") != null && filters.getFilter("createdOn").getFilterConfig() != null && !filters.getFilter("createdOn").getFilterConfig().isEmpty()){
          for(FilterConfig fc : filters.getFilter("createdOn").getFilterConfig()) {
            if(fc.getValue() != null) {
              wrapper.addFilter(new XFilter(fc.getComparison(),"createdOn", fc.getType(), fc.getValue()));
            }
          }
        }
        if (filters.getFilter("updatedBy") != null && filters.getFilter("updatedBy").getFilterConfig() != null && !filters.getFilter("updatedBy").getFilterConfig().isEmpty()){
          for(FilterConfig fc : filters.getFilter("updatedBy").getFilterConfig()) {
            if(fc.getValue() != null) {
              wrapper.addFilter(new XFilter(fc.getComparison(),"updatedBy", fc.getType(), fc.getValue()));
            }
          }
        }
        if (filters.getFilter("updatedOn") != null && filters.getFilter("updatedOn").getFilterConfig() != null && !filters.getFilter("updatedOn").getFilterConfig().isEmpty()){
          for(FilterConfig fc : filters.getFilter("updatedOn").getFilterConfig()) {
            if(fc.getValue() != null) {
              wrapper.addFilter(new XFilter(fc.getComparison(),"updatedOn", fc.getType(), fc.getValue()));
            }
          }
        }
					presenter.setFilters(wrapper);
					UserRequestCallbackAdapter<SportPratique> urc = new UserRequestCallbackAdapter<SportPratique>();
					urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
					urc.setAsyncCallback(callback);
					urc.setLoadConfig(loadConfig);
					presenter.getEventBus().executeSportFrequente(ActionCommand.COLUMNAR_SEARCH, urc);
					showInfoBanner(!userHideBannerInfo);
				}
			};
			pagingLoader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<SportPratique>>(
					proxy) {
				protected FilterPagingLoadConfig newLoadConfig() {
					return new FilterPagingLoadConfigBean();
				}
			};
			pagingLoader.setRemoteSort(true);
			pagingLoader
					.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, SportPratique, PagingLoadResult<SportPratique>>(
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
		RpcProxy<FilterPagingLoadConfig, PagingLoadResult<SportPratique>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<SportPratique>>() {
			@Override
			public void load(FilterPagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<SportPratique>> callback) {
				UserRequestCallbackAdapter<SportPratique> urc = new UserRequestCallbackAdapter<SportPratique>() {
					@Override
					public void onSingleOperationSuccessed(SportPratique model) {
						hideProgressBar();
					}

					@Override
					public void onMultipleOperationsFailed(
							List<SportPratique> model, Throwable cause) {
						hideProgressBar();
					}
				};
				urc.setAsyncCallback(callback);
				urc.setLoadConfig(loadConfig);
				urc.setPageSize(toolsBar.getPageSize());
				urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
				presenter.getEventBus().executeSportFrequente(
						ActionCommand.RESET_GRID, urc);
			}
		};
		pagingLoader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<SportPratique>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		pagingLoader.setRemoteSort(true);
		pagingLoader
				.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, SportPratique, PagingLoadResult<SportPratique>>(
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

	private PagingLoader<FilterPagingLoadConfig, PagingLoadResult<SportPratique>> pagingLoader = null;
	private RowNumberer<SportPratique> numberer;

	@Override
	public Widget asWidget() {
		ENTITY_NAME = "SportFrequente";
		SportPratiquePropertiesAccess propertiesAccess = GWT
				.create(SportPratiquePropertiesAccess.class);
		listStore = new ListStore<SportPratique>(propertiesAccess.key());
		IdentityValueProvider<SportPratique> identity = new IdentityValueProvider<SportPratique>();
		numberer = new RowNumberer<SportPratique>(identity);
		list = new ArrayList<ColumnConfig<SportPratique, ?>>();
		List<ColumnConfig<SportPratique, ?>> list = new ArrayList<ColumnConfig<SportPratique, ?>>();

        ColumnConfig<SportPratique, Integer> sportFrequenteIdColumn = new ColumnConfig<SportPratique, Integer>(propertiesAccess.sportFrequenteId(), 10, messages.sportFrequenteId());
        list.add(sportFrequenteIdColumn);
        ColumnConfig<SportPratique, String> nomSportColumn = new ColumnConfig<SportPratique, String>(propertiesAccess.nomSport(), 60, messages.nomSport());
        list.add(nomSportColumn);
        ColumnConfig<SportPratique, Integer> frequenceColumn = new ColumnConfig<SportPratique, Integer>(propertiesAccess.frequence(), 10, messages.frequence());
        list.add(frequenceColumn);
        ColumnConfig<SportPratique, String> uniteColumn = new ColumnConfig<SportPratique, String>(propertiesAccess.unite(), 20, messages.unite());
        list.add(uniteColumn);
        ColumnConfig<SportPratique, Integer> patientIdColumn = new ColumnConfig<SportPratique, Integer>(propertiesAccess.patientId(), 10, messages.patientId());
        list.add(patientIdColumn);
        ColumnConfig<SportPratique, Date> dateDebutColumn = new ColumnConfig<SportPratique, Date>(propertiesAccess.dateDebut(), 10, messages.dateDebut());
        list.add(dateDebutColumn);
        ColumnConfig<SportPratique, Date> dateFinColumn = new ColumnConfig<SportPratique, Date>(propertiesAccess.dateFin(), 10, messages.dateFin());
        list.add(dateFinColumn);
		ColumnConfig<SportPratique, String> createdByColumn = new ColumnConfig<SportPratique, String>(
				propertiesAccess.createdBy(), 30, messages.createdBy());
		list.add(createdByColumn);
		ColumnConfig<SportPratique, Date> createdOnColumn = new ColumnConfig<SportPratique, Date>(
				propertiesAccess.createdOn(), 10, messages.createdOn());
		list.add(createdOnColumn);
		ColumnConfig<SportPratique, String> updatedByColumn = new ColumnConfig<SportPratique, String>(
				propertiesAccess.updatedBy(), 30, messages.updatedBy());
		list.add(updatedByColumn);
		ColumnConfig<SportPratique, Date> updatedOnColumn = new ColumnConfig<SportPratique, Date>(
				propertiesAccess.updatedOn(), 10, messages.updatedOn());
		list.add(updatedOnColumn);
		columnModel = new ColumnModel<SportPratique>(list);
	        widget = gxtUiBinder.createAndBindUi(this);
		panel.addDomHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				formUpdated();
			}
		}, KeyPressEvent.getType());
		showInfoBanner(bannerInfoIsShowed);
		// Apply editor to grid
		final GridEditing<SportPratique> editing = new GridInlineEditing<SportPratique>(
				grid);
		editing.addBeforeStartEditHandler(new BeforeStartEditHandler<SportPratique>() {
			@Override
			public void onBeforeStartEdit(BeforeStartEditEvent<SportPratique> event) {
				editing.completeEditing();
				int row = event.getEditCell().getRow();
				SportPratique model = listStore.get(row);
				model.setUpdating(true);
			}
		});
		editing.addCompleteEditHandler(new CompleteEditHandler<SportPratique>() {
			@Override
			public void onCompleteEdit(CompleteEditEvent<SportPratique> event) {
				int row = event.getEditCell().getRow();
				SportPratique model = listStore.get(row);
				model.setUpdating(false);
			}
		});

      NumberField<Integer> sportFrequenteIdField = new NumberField(integerPropertyEditor);
      sportFrequenteIdField.setAllowBlank(false);
      editing.addEditor(sportFrequenteIdColumn, sportFrequenteIdField);


      TextField nomSportField = new TextField();
      nomSportField.addValidator(new MaxLengthValidator(60));
      nomSportField.setAllowBlank(false);
      editing.addEditor(nomSportColumn, nomSportField);

      NumberField<Integer> frequenceField = new NumberField(integerPropertyEditor);      editing.addEditor(frequenceColumn, frequenceField);


      TextField uniteField = new TextField();
      uniteField.addValidator(new MaxLengthValidator(20));      editing.addEditor(uniteColumn, uniteField);

      NumberField<Integer> patientIdField = new NumberField(integerPropertyEditor);      editing.addEditor(patientIdColumn, patientIdField);

      DateField dateDebutField = new DateField(datePropertyEditor);      editing.addEditor(dateDebutColumn, dateDebutField);

      DateField dateFinField = new DateField(datePropertyEditor);      editing.addEditor(dateFinColumn, dateFinField);

		toolBarArea.setHeaderVisible(false);
		toolsBar = new EditableGridToolsBar();
		toolsBar.setParamInoutSuffix("SportFrequente");
		toolsBar.getElement().getStyle().setProperty("borderBottom", "none");
		toolsBar.setPageSize(50);
		toolsBar.initialize(toolsBarAction);
	    	ToolTipConfig config = new ToolTipConfig();
	    	config = new ToolTipConfig();
	    	config.setBodyHtml("Prints the current document");
	    	config.setTitleHtml("Template Tip");
	    	config.setMouseOffset(new int[] {0, 0});
	    	config.setAnchor(Side.LEFT);
	    	config.setRenderer(renderer);
	    	config.setCloseable(true);
	    	config.setMaxWidth(415);
		//
		toolsBar.setToolTipConfig(config);
		toolBarArea.add(toolsBar);
		filters = new GridFilters<SportPratique>();
		listStore.addStoreRecordChangeHandler(new StoreRecordChangeHandler<SportPratique>() {
			@Override
			public void onRecordChange(
					StoreRecordChangeEvent<SportPratique> event) {
				SportPratique changedModel = event.getRecord().getModel();
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
		//Add Filters

        NumericFilter<SportPratique,Integer> sportFrequenteIdFilter = new NumericFilter<SportPratique,Integer>(propertiesAccess.sportFrequenteId(), integerPropertyEditor);
        filters.addFilter(sportFrequenteIdFilter);
        StringFilter<SportPratique> nomSportFilter = new StringFilter<SportPratique>(propertiesAccess.nomSport());
        filters.addFilter(nomSportFilter);
        NumericFilter<SportPratique,Integer> frequenceFilter = new NumericFilter<SportPratique,Integer>(propertiesAccess.frequence(), integerPropertyEditor);
        filters.addFilter(frequenceFilter);
        StringFilter<SportPratique> uniteFilter = new StringFilter<SportPratique>(propertiesAccess.unite());
        filters.addFilter(uniteFilter);
        NumericFilter<SportPratique,Integer> patientIdFilter = new NumericFilter<SportPratique,Integer>(propertiesAccess.patientId(), integerPropertyEditor);
        filters.addFilter(patientIdFilter);
        DateFilter<SportPratique> dateDebutFilter = new DateFilter<SportPratique>(propertiesAccess.dateDebut());
        filters.addFilter(dateDebutFilter);
        DateFilter<SportPratique> dateFinFilter = new DateFilter<SportPratique>(propertiesAccess.dateFin());
        filters.addFilter(dateFinFilter);
        StringFilter<SportPratique> createdByFilter = new StringFilter<SportPratique>(propertiesAccess.createdBy());
        filters.addFilter(createdByFilter);
        DateFilter<SportPratique> createdOnFilter = new DateFilter<SportPratique>(propertiesAccess.createdOn());
        filters.addFilter(createdOnFilter);
        StringFilter<SportPratique> updatedByFilter = new StringFilter<SportPratique>(propertiesAccess.updatedBy());
        filters.addFilter(updatedByFilter);
        DateFilter<SportPratique> updatedOnFilter = new DateFilter<SportPratique>(propertiesAccess.updatedOn());
        filters.addFilter(updatedOnFilter);
		numberer.initPlugin(grid);
		grid.setLoadMask(true);
		grid.setColumnReordering(true);
		grid.getView().setStripeRows(true);
		grid.getView().setTrackMouseOver(false);
		grid.getView().setAutoFill(true);
		GridDropTarget<SportPratique> target = new GridDropTarget<SportPratique>(grid);
		target.setAllowSelfAsSource(true);
		target.setFeedback(Feedback.INSERT);

		ViewUtils.unNotify(htmlMessage);
		grid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				SportPratique model = grid.getSelectionModel().getSelectedItem();
				ViewUtils.showAuditInfos(htmlMessage, model.getGlobalAuditInfo());
			}
		});
		grid.getView().setViewConfig(new GridViewConfig<SportPratique>() {
			@Override
			public String getColStyle(SportPratique model,
					ValueProvider<? super SportPratique, ?> valueProvider,
					int rowIndex, int colIndex) {
				return null;
			}

			@Override
			public String getRowStyle(SportPratique model, int rowIndex) {
				if (model.getErrorMessage() != null && model.getErrorMessage().getMessage() != null
						&& !model.getErrorMessage().getMessage().trim().isEmpty()) {
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
		this.presenter = (SportFrequentePresenter) p;
	}

	@Override
	public IPresenter getPresenter() {
		return this.presenter;
	}

	@Override
	public void updateView(SportPratique model) {
		Info.display(messages.message(), messages.finished());
		if (box != null) {
			box.hide();
			box = null;
		}
	}

	@Override
	public void updateView(List<SportPratique> models) {
		try {
			if (models != null) {
				boolean allOperationsSuccess = true;
				for (SportPratique model : models) {
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
								SportPratique oldModel = listStore
										.findModelWithKey(listStore
												.getKeyProvider().getKey(model));
								if (oldModel != null) {
									oldModel.merge(model);
								}
							}
						} else {
							SportPratique oldModel = listStore
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
	public SportPratique updateModel(SportPratique model) {
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
	public void onDataChanged(SportPratique model, boolean reloadDetails,
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
			UserRequestCallbackAdapter<SportPratique> urc) {
		presenter.getEventBus().executeSportFrequente(command, urc);
	}

	@Override
	protected void setModelsToBePersisted(ListStore<SportPratique> listStore) {
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