package com.rsoft.medicasoft.client.view;

/*
 Robelkend Templates Generator
 */
/*@Author=Jean Louidort*/
/*@Generation Date=Sun Sep 15 23:50:48 EDT 2013*/
/*@Version=1.0*/
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.client.UserRequestCallbackAdapter;
import com.rsoft.medicasoft.client.presenter.UserGroupDetailPresenter;
import com.rsoft.medicasoft.client.toolsbar.EditableGridToolsBar;
import com.rsoft.medicasoft.client.view.viewinterface.IPresenter;
import com.rsoft.medicasoft.client.view.viewinterface.IWidget;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.FilterWrapper;
import com.rsoft.medicasoft.shared.SystemMessage;
import com.rsoft.medicasoft.shared.XFilter;
import com.rsoft.medicasoft.shared.model.RequestDescriptor;
import com.rsoft.medicasoft.shared.model.UserGroupDetail;
import com.rsoft.medicasoft.shared.model.UserGroupDetailPropertiesAccess;
import com.sencha.gxt.cell.core.client.form.CheckBoxCell;
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
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.GridViewConfig;
import com.sencha.gxt.widget.core.client.grid.HeaderGroupConfig;
import com.sencha.gxt.widget.core.client.grid.RowNumberer;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;
import com.sencha.gxt.widget.core.client.grid.filters.BooleanFilter;
import com.sencha.gxt.widget.core.client.grid.filters.DateFilter;
import com.sencha.gxt.widget.core.client.grid.filters.GridFilters;
import com.sencha.gxt.widget.core.client.grid.filters.StringFilter;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;

public class UserGroupDetailCView extends ViewGridBase<UserGroupDetail> {
	private UserGroupDetailPresenter presenter;

	public interface Renderer extends ToolTipConfig.ToolTipRenderer<Object>,
			XTemplates {
		@Override
		@XTemplate(source = "help/userGroupDetailTemplate.html")
		public SafeHtml renderToolTip(Object data);
	}

	private Renderer renderer = GWT.create(Renderer.class);

	interface GxtUiBinder extends UiBinder<Widget, UserGroupDetailCView> {
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
		List<UserGroupDetail> UserGroupDetails = grid.getSelectionModel()
				.getSelectedItems();
		for (UserGroupDetail userGroupDetail : UserGroupDetails) {
			userGroupDetail.setSTATUS("REMOVE");
		}
		listStore.commitChanges();
		grid.getView().refresh(false);
	}

	@Override
	public void ignore() {
		List<UserGroupDetail> UserGroupDetails = grid.getSelectionModel()
				.getSelectedItems();
		for (UserGroupDetail UserGroupDetail : UserGroupDetails) {
			UserGroupDetail foundModel = listStore.findModelWithKey(listStore
					.getKeyProvider().getKey(UserGroupDetail));
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
			RpcProxy<FilterPagingLoadConfig, PagingLoadResult<UserGroupDetail>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<UserGroupDetail>>() {
				@Override
				public void load(
						FilterPagingLoadConfig loadConfig,
						AsyncCallback<PagingLoadResult<UserGroupDetail>> callback) {
					listStore.clear();
					listStore.commitChanges();
					FilterWrapper wrapper = new FilterWrapper(null);

					if (filters.getFilter("userGroupId") != null
							&& filters.getFilter("userGroupId")
									.getFilterConfig() != null
							&& !filters.getFilter("userGroupId")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("userGroupId")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "userGroupId", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("formId") != null
							&& filters.getFilter("formId").getFilterConfig() != null
							&& !filters.getFilter("formId").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("formId")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "formId", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("canVisualize") != null
							&& filters.getFilter("canVisualize")
									.getFilterConfig() != null
							&& !filters.getFilter("canVisualize")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters
								.getFilter("canVisualize").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "canVisualize", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("canCreate") != null
							&& filters.getFilter("canCreate").getFilterConfig() != null
							&& !filters.getFilter("canCreate")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("canCreate")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "canCreate", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("canUpdate") != null
							&& filters.getFilter("canUpdate").getFilterConfig() != null
							&& !filters.getFilter("canUpdate")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("canUpdate")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "canUpdate", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("canRemove") != null
							&& filters.getFilter("canRemove").getFilterConfig() != null
							&& !filters.getFilter("canRemove")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("canRemove")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "canRemove", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("canValidate") != null
							&& filters.getFilter("canValidate")
									.getFilterConfig() != null
							&& !filters.getFilter("canValidate")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("canValidate")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "canValidate", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("canPost") != null
							&& filters.getFilter("canPost").getFilterConfig() != null
							&& !filters.getFilter("canPost").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("canPost")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "canPost", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("canCancel") != null
							&& filters.getFilter("canCancel").getFilterConfig() != null
							&& !filters.getFilter("canCancel")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("canCancel")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "canCancel", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("canPay") != null
							&& filters.getFilter("canPay").getFilterConfig() != null
							&& !filters.getFilter("canPay").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("canPay")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "canPay", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("canActivate") != null
							&& filters.getFilter("canActivate")
									.getFilterConfig() != null
							&& !filters.getFilter("canActivate")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("canActivate")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "canActivate", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("canDeactivate") != null
							&& filters.getFilter("canDeactivate")
									.getFilterConfig() != null
							&& !filters.getFilter("canDeactivate")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"canDeactivate").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "canDeactivate", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("canClose") != null
							&& filters.getFilter("canClose").getFilterConfig() != null
							&& !filters.getFilter("canClose").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("canClose")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "canClose", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("canFinalize") != null
							&& filters.getFilter("canFinalize")
									.getFilterConfig() != null
							&& !filters.getFilter("canFinalize")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("canFinalize")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "canFinalize", fc
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
					UserRequestCallbackAdapter<UserGroupDetail> urc = new UserRequestCallbackAdapter<UserGroupDetail>();
					urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
					urc.setAsyncCallback(callback);
					urc.setLoadConfig(loadConfig);
					presenter.getEventBus().executeUserGroupDetail(
							ActionCommand.COLUMNAR_SEARCH, urc);
					showInfoBanner(!userHideBannerInfo);
				}
			};
			pagingLoader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<UserGroupDetail>>(
					proxy) {
				protected FilterPagingLoadConfig newLoadConfig() {
					return new FilterPagingLoadConfigBean();
				}
			};
			pagingLoader.setRemoteSort(true);
			pagingLoader
					.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, UserGroupDetail, PagingLoadResult<UserGroupDetail>>(
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
		RpcProxy<FilterPagingLoadConfig, PagingLoadResult<UserGroupDetail>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<UserGroupDetail>>() {
			@Override
			public void load(FilterPagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<UserGroupDetail>> callback) {
				UserRequestCallbackAdapter<UserGroupDetail> urc = new UserRequestCallbackAdapter<UserGroupDetail>() {
					@Override
					public void onSingleOperationSuccessed(UserGroupDetail model) {
						hideProgressBar();
					}

					@Override
					public void onMultipleOperationsFailed(
							List<UserGroupDetail> model, Throwable cause) {
						hideProgressBar();
					}
				};
				urc.setAsyncCallback(callback);
				urc.setLoadConfig(loadConfig);
				urc.setPageSize(toolsBar.getPageSize());
				urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
				presenter.getEventBus().executeUserGroupDetail(
						ActionCommand.RESET_GRID, urc);
			}
		};
		pagingLoader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<UserGroupDetail>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		pagingLoader.setRemoteSort(true);
		pagingLoader
				.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, UserGroupDetail, PagingLoadResult<UserGroupDetail>>(
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

	private PagingLoader<FilterPagingLoadConfig, PagingLoadResult<UserGroupDetail>> pagingLoader = null;
	private RowNumberer<UserGroupDetail> numberer;


	@Override
	public Widget asWidget() {
		ENTITY_NAME = "UserGroupDetail";
		UserGroupDetailPropertiesAccess propertiesAccess = GWT
				.create(UserGroupDetailPropertiesAccess.class);
		listStore = new ListStore<UserGroupDetail>(propertiesAccess.key());
		this.presenter.setListStore(listStore);
		IdentityValueProvider<UserGroupDetail> identity = new IdentityValueProvider<UserGroupDetail>();
		numberer = new RowNumberer<UserGroupDetail>(identity);
		list = new ArrayList<ColumnConfig<UserGroupDetail, ?>>();
		List<ColumnConfig<UserGroupDetail, ?>> list = new ArrayList<ColumnConfig<UserGroupDetail, ?>>();

		ColumnConfig<UserGroupDetail, String> formIdColumn = new ColumnConfig<UserGroupDetail, String>(
				propertiesAccess.formId(), 30, messages.formId());
		list.add(formIdColumn);

		ColumnConfig<UserGroupDetail, Boolean> canVisualizeColumn = new ColumnConfig<UserGroupDetail, Boolean>(
				propertiesAccess.canVisualize(), 10, messages.canVisualize());
		canVisualizeColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		canVisualizeColumn.setCell(new CheckBoxCell());
		//canVisualizeColumn.setColumnStyle(SafeStylesUtils.fromTrustedString("padding: 2px 3px; align: center;"));
		list.add(canVisualizeColumn);
		ColumnConfig<UserGroupDetail, Boolean> canCreateColumn = new ColumnConfig<UserGroupDetail, Boolean>(
				propertiesAccess.canCreate(), 10, messages.canCreate());
		canCreateColumn.setAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		canCreateColumn.setCell(new CheckBoxCell());
		list.add(canCreateColumn);
		ColumnConfig<UserGroupDetail, Boolean> canUpdateColumn = new ColumnConfig<UserGroupDetail, Boolean>(
				propertiesAccess.canUpdate(), 10, messages.canUpdate());
		canUpdateColumn.setAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		canUpdateColumn.setCell(new CheckBoxCell());
		list.add(canUpdateColumn);
		ColumnConfig<UserGroupDetail, Boolean> canRemoveColumn = new ColumnConfig<UserGroupDetail, Boolean>(
				propertiesAccess.canRemove(), 10, messages.canRemove());
		canRemoveColumn.setAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		canRemoveColumn.setCell(new CheckBoxCell());
		list.add(canRemoveColumn);
		ColumnConfig<UserGroupDetail, Boolean> canValidateColumn = new ColumnConfig<UserGroupDetail, Boolean>(
				propertiesAccess.canValidate(), 10, messages.canValidate());
		canValidateColumn.setAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		canValidateColumn.setCell(new CheckBoxCell());
		list.add(canValidateColumn);
		ColumnConfig<UserGroupDetail, Boolean> canPostColumn = new ColumnConfig<UserGroupDetail, Boolean>(
				propertiesAccess.canPost(), 10, messages.canPost());
		canPostColumn.setAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		canPostColumn.setCell(new CheckBoxCell());
		list.add(canPostColumn);
		ColumnConfig<UserGroupDetail, Boolean> canCancelColumn = new ColumnConfig<UserGroupDetail, Boolean>(
				propertiesAccess.canCancel(), 10, messages.canCancel());
		canCancelColumn.setAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		canCancelColumn.setCell(new CheckBoxCell());
		list.add(canCancelColumn);
		ColumnConfig<UserGroupDetail, Boolean> canPayColumn = new ColumnConfig<UserGroupDetail, Boolean>(
				propertiesAccess.canPay(), 10, messages.canPay());
		canPayColumn.setAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		canPayColumn.setCell(new CheckBoxCell());
		list.add(canPayColumn);
		ColumnConfig<UserGroupDetail, Boolean> canActivateColumn = new ColumnConfig<UserGroupDetail, Boolean>(
				propertiesAccess.canActivate(), 10, messages.canActivate());
		canActivateColumn.setAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		canActivateColumn.setCell(new CheckBoxCell());
		list.add(canActivateColumn);
		ColumnConfig<UserGroupDetail, Boolean> canDeactivateColumn = new ColumnConfig<UserGroupDetail, Boolean>(
				propertiesAccess.canDeactivate(), 10, messages.canDeactivate());
		canDeactivateColumn.setAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		canDeactivateColumn.setCell(new CheckBoxCell());
		list.add(canDeactivateColumn);

		ColumnConfig<UserGroupDetail, Boolean> canCloseColumn = new ColumnConfig<UserGroupDetail, Boolean>(
				propertiesAccess.canClose(), 10, messages.canClose());
		canCloseColumn.setAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		canCloseColumn.setCell(new CheckBoxCell());
		list.add(canCloseColumn);
		ColumnConfig<UserGroupDetail, Boolean> canFinalizeColumn = new ColumnConfig<UserGroupDetail, Boolean>(
				propertiesAccess.canFinalize(), 10, messages.canFinalize());
		canFinalizeColumn.setAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		canFinalizeColumn.setCell(new CheckBoxCell());
		list.add(canFinalizeColumn);

		columnModel = new ColumnModel<UserGroupDetail>(list);
		//columnModel.addHeaderGroup(0, 1, new HeaderGroupConfig(messages.droitAccess(), 1, 2));

		columnModel.addHeaderGroup(0, 0, new HeaderGroupConfig("", 1, 1));
		columnModel.addHeaderGroup(0, 1, new HeaderGroupConfig(messages.droitAccess(), 1, 12));

		widget = gxtUiBinder.createAndBindUi(this);

		panel.addDomHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				formUpdated();
			}
		}, KeyPressEvent.getType());
		showInfoBanner(bannerInfoIsShowed);
		// Apply editor to grid
		final GridEditing<UserGroupDetail> editing = new GridInlineEditing<UserGroupDetail>(
				grid);
		editing.addBeforeStartEditHandler(new BeforeStartEditHandler<UserGroupDetail>() {
			@Override
			public void onBeforeStartEdit(
					BeforeStartEditEvent<UserGroupDetail> event) {
				editing.completeEditing();
				int row = event.getEditCell().getRow();
				UserGroupDetail model = listStore.get(row);
				model.setUpdating(true);
			}
		});

		editing.addCompleteEditHandler(new CompleteEditHandler<UserGroupDetail>() {
			@Override
			public void onCompleteEdit(CompleteEditEvent<UserGroupDetail> event) {
				int row = event.getEditCell().getRow();
				UserGroupDetail model = listStore.get(row);
				model.setUpdating(false);
			}
		});

		TextField formIdField = new TextField();
		formIdField.setEnabled(false);
		editing.addEditor(formIdColumn, formIdField);

		CheckBox canVisualizeField = new CheckBox();
		editing.addEditor(canVisualizeColumn, canVisualizeField);

		CheckBox canCreateField = new CheckBox();
		editing.addEditor(canCreateColumn, canCreateField);

		CheckBox canUpdateField = new CheckBox();
		editing.addEditor(canUpdateColumn, canUpdateField);

		CheckBox canRemoveField = new CheckBox();
		editing.addEditor(canRemoveColumn, canRemoveField);
		CheckBox canValidateField = new CheckBox();
		editing.addEditor(canValidateColumn, canValidateField);

		CheckBox canPostField = new CheckBox();
		editing.addEditor(canPostColumn, canPostField);

		CheckBox canCancelField = new CheckBox();
		editing.addEditor(canCancelColumn, canCancelField);

		CheckBox canPayField = new CheckBox();
		editing.addEditor(canPayColumn, canPayField);

		CheckBox canActivateField = new CheckBox();
		editing.addEditor(canActivateColumn, canActivateField);

		CheckBox canDeactivateField = new CheckBox();
		editing.addEditor(canDeactivateColumn, canDeactivateField);

		CheckBox canCloseField = new CheckBox();
		editing.addEditor(canDeactivateColumn, canCloseField);

		CheckBox canFinalizeField = new CheckBox();
		editing.addEditor(canFinalizeColumn, canFinalizeField);

		toolBarArea.setHeaderVisible(false);
		toolsBar = new EditableGridToolsBar();
		toolsBar.getElement().getStyle().setProperty("borderBottom", "none");
		toolsBar.setPageSize(2);
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
		toolsBar.setLoadingSepVisible(false);
		toolsBar.setHelpSepVisible(false);
		toolsBar.setResetVisible(false);
		toolsBar.setSaveVisible(false);
		toolsBar.setRemoveVisible(false);
		toolsBar.setLoadVisible(false);
		toolsBar.setExtractVisible(false);
		toolsBar.setPrintVisible(false);
		toolsBar.setHelpVisible(false);
		toolsBar.setExtractSepVisible(false);
		toolsBar.setPersistSepVisible(false);
		toolsBar.setIgnoreSepVisible(false);
		toolBarArea.add(toolsBar);
		filters = new GridFilters<UserGroupDetail>();
		listStore
				.addStoreRecordChangeHandler(new StoreRecordChangeHandler<UserGroupDetail>() {
					@Override
					public void onRecordChange(
							StoreRecordChangeEvent<UserGroupDetail> event) {
						UserGroupDetail changedModel = event.getRecord()
								.getModel();
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

		StringFilter<UserGroupDetail> formIdFilter = new StringFilter<UserGroupDetail>(
				propertiesAccess.formId());
		filters.addFilter(formIdFilter);
		BooleanFilter<UserGroupDetail> canVisualizeFilter = new BooleanFilter<UserGroupDetail>(
				propertiesAccess.canVisualize());
		filters.addFilter(canVisualizeFilter);
		BooleanFilter<UserGroupDetail> canCreateFilter = new BooleanFilter<UserGroupDetail>(
				propertiesAccess.canCreate());
		filters.addFilter(canCreateFilter);
		BooleanFilter<UserGroupDetail> canUpdateFilter = new BooleanFilter<UserGroupDetail>(
				propertiesAccess.canUpdate());
		filters.addFilter(canUpdateFilter);
		BooleanFilter<UserGroupDetail> canRemoveFilter = new BooleanFilter<UserGroupDetail>(
				propertiesAccess.canRemove());
		filters.addFilter(canRemoveFilter);
		BooleanFilter<UserGroupDetail> canValidateFilter = new BooleanFilter<UserGroupDetail>(
				propertiesAccess.canValidate());
		filters.addFilter(canValidateFilter);
		BooleanFilter<UserGroupDetail> canPostFilter = new BooleanFilter<UserGroupDetail>(
				propertiesAccess.canPost());
		filters.addFilter(canPostFilter);
		BooleanFilter<UserGroupDetail> canCancelFilter = new BooleanFilter<UserGroupDetail>(
				propertiesAccess.canCancel());
		filters.addFilter(canCancelFilter);
		BooleanFilter<UserGroupDetail> canPayFilter = new BooleanFilter<UserGroupDetail>(
				propertiesAccess.canPay());
		filters.addFilter(canPayFilter);
		BooleanFilter<UserGroupDetail> canActivateFilter = new BooleanFilter<UserGroupDetail>(
				propertiesAccess.canActivate());
		filters.addFilter(canActivateFilter);
		BooleanFilter<UserGroupDetail> canDeactivateFilter = new BooleanFilter<UserGroupDetail>(
				propertiesAccess.canDeactivate());
		filters.addFilter(canDeactivateFilter);
		BooleanFilter<UserGroupDetail> canCloseFilter = new BooleanFilter<UserGroupDetail>(
				propertiesAccess.canClose());
		filters.addFilter(canCloseFilter);
		BooleanFilter<UserGroupDetail> canFinalizeFilter = new BooleanFilter<UserGroupDetail>(
				propertiesAccess.canFinalize());
		filters.addFilter(canFinalizeFilter);
		StringFilter<UserGroupDetail> createdByFilter = new StringFilter<UserGroupDetail>(
				propertiesAccess.createdBy());
		filters.addFilter(createdByFilter);
		DateFilter<UserGroupDetail> createdOnFilter = new DateFilter<UserGroupDetail>(
				propertiesAccess.createdOn());
		filters.addFilter(createdOnFilter);
		StringFilter<UserGroupDetail> updatedByFilter = new StringFilter<UserGroupDetail>(
				propertiesAccess.updatedBy());
		filters.addFilter(updatedByFilter);
		DateFilter<UserGroupDetail> updatedOnFilter = new DateFilter<UserGroupDetail>(
				propertiesAccess.updatedOn());
		filters.addFilter(updatedOnFilter);
		// Filter rowscnFilter. Create it mannually if you need it
		numberer.initPlugin(grid);
		grid.setLoadMask(true);
		grid.setColumnReordering(true);
		grid.getView().setStripeRows(true);
		grid.getView().setTrackMouseOver(false);
		grid.getView().setAutoFill(true);
		GridDropTarget<UserGroupDetail> target = new GridDropTarget<UserGroupDetail>(
				grid);
		target.setAllowSelfAsSource(true);
		target.setFeedback(Feedback.INSERT);

		ViewUtils.unNotify(htmlMessage);
		grid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
//				UserGroupDetail model = grid.getSelectionModel()
//						.getSelectedItem();
//				ViewUtils.showAuditInfos(htmlMessage,
//						model.getGlobalAuditInfo());
			}
		});


		grid.getView().setViewConfig(new GridViewConfig<UserGroupDetail>() {
			@Override
			public String getColStyle(UserGroupDetail model,
					ValueProvider<? super UserGroupDetail, ?> valueProvider,
					int rowIndex, int colIndex) {

		          com.google.gwt.dom.client.Element parent = grid.getView().getCell(rowIndex, colIndex);

		          parent.setInnerHTML("Merci");
//		          if (parent != null) {
//		            parent = parent.getFirstChildElement();
//		            SafeHtmlBuilder sb = new SafeHtmlBuilder();
//		            cell.render(new Context(i, col, store.getKeyProvider().getKey(p)), column.getValueProvider().getValue(p),
//		                sb);
//		            parent.setInnerHTML(sb.toSafeHtml().asString());
//		          }

				return null;
			}

			@Override
			public String getRowStyle(UserGroupDetail model, int rowIndex) {

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
		this.presenter = (UserGroupDetailPresenter) p;
	}

	@Override
	public IPresenter getPresenter() {
		return this.presenter;
	}

	@Override
	public void updateView(UserGroupDetail model) {
		Info.display(messages.message(), messages.finished());
		if (box != null) {
			box.hide();
			box = null;
		}
	}

	@Override
	public void updateView(List<UserGroupDetail> models) {
		try {
			if (models != null) {
				boolean allOperationsSuccess = true;
				for (UserGroupDetail model : models) {
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
								UserGroupDetail oldModel = listStore
										.findModelWithKey(listStore
												.getKeyProvider().getKey(model));
								if (oldModel != null) {
									oldModel.merge(model);
								}
							}
						} else {
							UserGroupDetail oldModel = listStore
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
	public UserGroupDetail updateModel(UserGroupDetail model) {
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
	public void onDataChanged(UserGroupDetail model, boolean reloadDetails,
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
			UserRequestCallbackAdapter<UserGroupDetail> urc) {
		presenter.getEventBus().executeUserGroupDetail(command, urc);
	}

	@Override
	protected void setModelsToBePersisted(ListStore<UserGroupDetail> listStore) {
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