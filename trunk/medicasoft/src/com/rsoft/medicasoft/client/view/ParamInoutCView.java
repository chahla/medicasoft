package com.rsoft.medicasoft.client.view;
/*
	Robelkend Templates Generator
*/
/*@Author=Jean Louidort*/
/*@Generation Date=Sat Oct 26 19:38:26 EDT 2013*/
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
import com.rsoft.medicasoft.client.presenter.ParamInoutPresenter;
import com.rsoft.medicasoft.client.toolsbar.EditableGridToolsBar;
import com.rsoft.medicasoft.client.view.viewinterface.IPresenter;
import com.rsoft.medicasoft.client.view.viewinterface.IWidget;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.FilterWrapper;
import com.rsoft.medicasoft.shared.SystemMessage;
import com.rsoft.medicasoft.shared.XFilter;
import com.rsoft.medicasoft.shared.model.RequestDescriptor;
import com.rsoft.medicasoft.shared.model.ParamInout;
import com.rsoft.medicasoft.shared.model.ParamInoutPropertiesAccess;
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



public class ParamInoutCView extends ViewGridBase<ParamInout> {
	private ParamInoutPresenter presenter;

	public interface Renderer extends ToolTipConfig.ToolTipRenderer<Object>,
			XTemplates {
		@Override
		@XTemplate(source = "help/paramInoutTemplate.html")
		public SafeHtml renderToolTip(Object data);
	}

	private Renderer renderer = GWT.create(Renderer.class);

	interface GxtUiBinder extends UiBinder<Widget, ParamInoutCView> {
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
		List<ParamInout> ParamInouts = grid.getSelectionModel()
				.getSelectedItems();
		for (ParamInout ParamInout : ParamInouts) {
			if (ParamInout.getEntityId() != null) {
				presenter.addModelToBeRemoved(ParamInout);
				ParamInout foundModel = listStore
						.findModelWithKey(listStore.getKeyProvider().getKey(
								ParamInout));
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
		List<ParamInout> ParamInouts = grid.getSelectionModel()
				.getSelectedItems();
		for (ParamInout ParamInout : ParamInouts) {
			ParamInout foundModel = listStore.findModelWithKey(listStore
					.getKeyProvider().getKey(ParamInout));
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
			RpcProxy<FilterPagingLoadConfig, PagingLoadResult<ParamInout>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<ParamInout>>() {
				@Override
				public void load(FilterPagingLoadConfig loadConfig,
						AsyncCallback<PagingLoadResult<ParamInout>> callback) {
					listStore.clear();
					listStore.commitChanges();
					FilterWrapper wrapper = new FilterWrapper(null);
					
        if (filters.getFilter("contenu") != null && filters.getFilter("contenu").getFilterConfig() != null && !filters.getFilter("contenu").getFilterConfig().isEmpty()){
          for(FilterConfig fc : filters.getFilter("contenu").getFilterConfig()) {
            if(fc.getValue() != null) {
              wrapper.addFilter(new XFilter(fc.getComparison(),"contenu", fc.getType(), fc.getValue()));
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
        if (filters.getFilter("description") != null && filters.getFilter("description").getFilterConfig() != null && !filters.getFilter("description").getFilterConfig().isEmpty()){
          for(FilterConfig fc : filters.getFilter("description").getFilterConfig()) {
            if(fc.getValue() != null) {
              wrapper.addFilter(new XFilter(fc.getComparison(),"description", fc.getType(), fc.getValue()));
            }
          }
        }
					presenter.setFilters(wrapper);
					UserRequestCallbackAdapter<ParamInout> urc = new UserRequestCallbackAdapter<ParamInout>();
					urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
					urc.setAsyncCallback(callback);
					urc.setLoadConfig(loadConfig);
					presenter.getEventBus().executeParamInout(ActionCommand.COLUMNAR_SEARCH, urc);
					showInfoBanner(!userHideBannerInfo);
				}
			};
			pagingLoader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<ParamInout>>(
					proxy) {
				protected FilterPagingLoadConfig newLoadConfig() {
					return new FilterPagingLoadConfigBean();
				}
			};
			pagingLoader.setRemoteSort(true);
			pagingLoader
					.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, ParamInout, PagingLoadResult<ParamInout>>(
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
		RpcProxy<FilterPagingLoadConfig, PagingLoadResult<ParamInout>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<ParamInout>>() {
			@Override
			public void load(FilterPagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<ParamInout>> callback) {
				UserRequestCallbackAdapter<ParamInout> urc = new UserRequestCallbackAdapter<ParamInout>() {
					@Override
					public void onSingleOperationSuccessed(ParamInout model) {
						hideProgressBar();
					}

					@Override
					public void onMultipleOperationsFailed(
							List<ParamInout> model, Throwable cause) {
						hideProgressBar();
					}
				};
				urc.setAsyncCallback(callback);
				urc.setLoadConfig(loadConfig);
				urc.setPageSize(toolsBar.getPageSize());
				urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
				presenter.getEventBus().executeParamInout(
						ActionCommand.RESET_GRID, urc);
			}
		};
		pagingLoader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<ParamInout>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		pagingLoader.setRemoteSort(true);
		pagingLoader
				.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, ParamInout, PagingLoadResult<ParamInout>>(
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

	private PagingLoader<FilterPagingLoadConfig, PagingLoadResult<ParamInout>> pagingLoader = null;
	private RowNumberer<ParamInout> numberer;

	@Override
	public Widget asWidget() {
		ENTITY_NAME = "ParamInout";
		ParamInoutPropertiesAccess propertiesAccess = GWT
				.create(ParamInoutPropertiesAccess.class);
		listStore = new ListStore<ParamInout>(propertiesAccess.key());
		IdentityValueProvider<ParamInout> identity = new IdentityValueProvider<ParamInout>();
		numberer = new RowNumberer<ParamInout>(identity);
		list = new ArrayList<ColumnConfig<ParamInout, ?>>();
		List<ColumnConfig<ParamInout, ?>> list = new ArrayList<ColumnConfig<ParamInout, ?>>();
		
        ColumnConfig<ParamInout, String> contenuColumn = new ColumnConfig<ParamInout, String>(propertiesAccess.contenu(), 5000, messages.contenu());
        list.add(contenuColumn);
        ColumnConfig<ParamInout, String> descriptionColumn = new ColumnConfig<ParamInout, String>(propertiesAccess.description(), 20, messages.description());
        list.add(descriptionColumn);
		ColumnConfig<ParamInout, String> createdByColumn = new ColumnConfig<ParamInout, String>(
				propertiesAccess.createdBy(), 30, "createdBy");
		list.add(createdByColumn);
		ColumnConfig<ParamInout, Date> createdOnColumn = new ColumnConfig<ParamInout, Date>(
				propertiesAccess.createdOn(), 10, "createdOn");
		list.add(createdOnColumn);
		ColumnConfig<ParamInout, String> updatedByColumn = new ColumnConfig<ParamInout, String>(
				propertiesAccess.updatedBy(), 30, "updatedBy");
		list.add(updatedByColumn);
		ColumnConfig<ParamInout, Date> updatedOnColumn = new ColumnConfig<ParamInout, Date>(
				propertiesAccess.updatedOn(), 10, "updatedOn");
		list.add(updatedOnColumn);		
		columnModel = new ColumnModel<ParamInout>(list);
	        widget = gxtUiBinder.createAndBindUi(this);
		panel.addDomHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				formUpdated();
			}
		}, KeyPressEvent.getType());
		showInfoBanner(bannerInfoIsShowed);
		// Apply editor to grid
		final GridEditing<ParamInout> editing = new GridInlineEditing<ParamInout>(
				grid);
		editing.addBeforeStartEditHandler(new BeforeStartEditHandler<ParamInout>() {
			@Override
			public void onBeforeStartEdit(BeforeStartEditEvent<ParamInout> event) {
				editing.completeEditing();
				int row = event.getEditCell().getRow();
				ParamInout model = listStore.get(row);
				model.setUpdating(true);
			}
		});
		editing.addCompleteEditHandler(new CompleteEditHandler<ParamInout>() {
			@Override
			public void onCompleteEdit(CompleteEditEvent<ParamInout> event) {
				int row = event.getEditCell().getRow();
				ParamInout model = listStore.get(row);
				model.setUpdating(false);
			}
		});
		
      
      TextField contenuField = new TextField();
      contenuField.addValidator(new MaxLengthValidator(5000));
      contenuField.setAllowBlank(false);
      editing.addEditor(contenuColumn, contenuField);

      
      TextField descriptionField = new TextField();
      descriptionField.addValidator(new MaxLengthValidator(20));
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
	    	config.setMouseOffset(new int[] {0, 0});
	    	config.setAnchor(Side.LEFT);
	    	config.setRenderer(renderer);
	    	config.setCloseable(true);
	    	config.setMaxWidth(415);
		//
		toolsBar.setToolTipConfig(config);
		toolBarArea.add(toolsBar);
		filters = new GridFilters<ParamInout>();
		listStore.addStoreRecordChangeHandler(new StoreRecordChangeHandler<ParamInout>() {
			@Override
			public void onRecordChange(
					StoreRecordChangeEvent<ParamInout> event) {
				ParamInout changedModel = event.getRecord().getModel();
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
		
        StringFilter<ParamInout> contenuFilter = new StringFilter<ParamInout>(propertiesAccess.contenu());
        filters.addFilter(contenuFilter);
        StringFilter<ParamInout> updatedByFilter = new StringFilter<ParamInout>(propertiesAccess.updatedBy());
        filters.addFilter(updatedByFilter);
        DateFilter<ParamInout> updatedOnFilter = new DateFilter<ParamInout>(propertiesAccess.updatedOn());
        filters.addFilter(updatedOnFilter);
        StringFilter<ParamInout> createdByFilter = new StringFilter<ParamInout>(propertiesAccess.createdBy());
        filters.addFilter(createdByFilter);
        DateFilter<ParamInout> createdOnFilter = new DateFilter<ParamInout>(propertiesAccess.createdOn());
        filters.addFilter(createdOnFilter);
        StringFilter<ParamInout> descriptionFilter = new StringFilter<ParamInout>(propertiesAccess.description());
        filters.addFilter(descriptionFilter);
		numberer.initPlugin(grid);
		grid.setLoadMask(true);
		grid.setColumnReordering(true);
		grid.getView().setStripeRows(true);
		grid.getView().setTrackMouseOver(false);
		grid.getView().setAutoFill(true);
		GridDropTarget<ParamInout> target = new GridDropTarget<ParamInout>(grid);
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
				ParamInout model = grid.getSelectionModel().getSelectedItem();
				ViewUtils.showAuditInfos(htmlMessage, model.getGlobalAuditInfo());
			}
		});
		grid.getView().setViewConfig(new GridViewConfig<ParamInout>() {
			@Override
			public String getColStyle(ParamInout model,
					ValueProvider<? super ParamInout, ?> valueProvider,
					int rowIndex, int colIndex) {
				return null;
			}

			@Override
			public String getRowStyle(ParamInout model, int rowIndex) {
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
		this.presenter = (ParamInoutPresenter) p;
	}

	@Override
	public IPresenter getPresenter() {
		return this.presenter;
	}

	@Override
	public void updateView(ParamInout model) {
		Info.display(messages.message(), messages.finished());
		if (box != null) {
			box.hide();
			box = null;
		}
	}

	@Override
	public void updateView(List<ParamInout> models) {
		try {
			if (models != null) {
				boolean allOperationsSuccess = true;
				for (ParamInout model : models) {
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
								ParamInout oldModel = listStore
										.findModelWithKey(listStore
												.getKeyProvider().getKey(model));
								if (oldModel != null) {
									oldModel.merge(model);
								}
							}
						} else {
							ParamInout oldModel = listStore
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
	public ParamInout updateModel(ParamInout model) {
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
	public void onDataChanged(ParamInout model, boolean reloadDetails,
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
			UserRequestCallbackAdapter<ParamInout> urc) {
		presenter.getEventBus().executeParamInout(command, urc);
	}

	@Override
	protected void setModelsToBePersisted(ListStore<ParamInout> listStore) {
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