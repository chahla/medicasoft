package com.rsoft.medicasoft.client.view;

/*
 Robelkend Templates Generator
 */
/*@Author=Jean Louidort*/
/*@Generation Date=Fri Sep 13 19:06:00 EDT 2013*/
/*@Version=1.0*/

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.client.UserRequestCallbackAdapter;
import com.rsoft.medicasoft.client.presenter.PersistingCallback;
import com.rsoft.medicasoft.client.presenter.UserGroupPresenter;
import com.rsoft.medicasoft.client.view.viewinterface.IPresenter;
import com.rsoft.medicasoft.client.view.viewinterface.IWidget;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.i18n.I18NMessages;
import com.rsoft.medicasoft.shared.model.UserGroup;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;

public class UserGroupView extends UserGroupMasterWrapperView {
	public interface Renderer extends ToolTipConfig.ToolTipRenderer<Object>,
			XTemplates {
		@Override
		@XTemplate(source = "help/userGroupTemplate.html")
		public SafeHtml renderToolTip(Object data);
	}

	private Renderer renderer = GWT.create(Renderer.class);
	private static GxtUiBinder gxtUiBinder = GWT.create(GxtUiBinder.class);

	private UserGroupPresenter presenter;

	interface GxtUiBinder extends UiBinder<Widget, UserGroupView> {
	}

	@Override
	protected void execute(ActionCommand command,
			UserRequestCallbackAdapter<UserGroup> urc) {
		presenter.getEventBus().executeUserGroup(command, urc);
	}

	@Override
	public void setPresenter(IPresenter p) {
		this.presenter = (UserGroupPresenter) p;
		if (this.presenter != null) {
			presenter.getEventBus();
			this.presenter.setPersitingCallback(persitingCallback);
		}
	}

	@Override
	public IPresenter getPresenter() {
		return this.presenter;
	}

	@Override
	public void formReset() {
		presenter.removeUpdatedZone(widget.getTitle());
		callback.viewReset(widget.getTitle());
		presenter.setUpdatePending(false);
		Info.display(messages.message(), messages.finished());
		if (box != null) {
			box.hide();
			box = null;
		}
	}

	@Override
	public void formUpdated() {
		if (!presenter.isUpdatePending() && !presenter.isSearchPending()) {
			callback.viewChanged(widget.getTitle());
			presenter.setUpdatePending(true);
			Info.display(messages.message(), messages.finished());
			if (box != null) {
				box.hide();
				box = null;
			}
		}
	}

	public void setSearchPending(boolean value) {
		presenter.setSearchPending(value);
	}

	@Override
	public void onActionFailure(Throwable caught, String message) {
		if (message != null) {
			Window.alert(message);
		} else {
			Window.alert(caught.getMessage() != null ? caught.getMessage()
					: caught.toString());
		}
		Info.display(messages.message(), messages.finished());
		if (box != null) {
			box.hide();
			box = null;
		}
	}

	@Override
	public Widget getWidget() {
		// TODO Auto-generated method stub
		return asWidget();
	}

	@Override
	public boolean stopCurrentAction() {
		if (!presenter.isSearchPending() && presenter.isUpdatePending()
				&& !Window.confirm(messages.updatePendingContinue())) {
			return true;
		}
		return false;
	}

	@Override
	public int rechordChanged(int record) {
		presenter.onChangeHandled(record);
		return presenter.getTotalRecords();
	}

	protected PersistingCallback persitingCallback = new PersistingCallback() {
		@Override
		public void onSuccess(Object source, String operation) {
			if ("merging".equalsIgnoreCase(operation)) {
				Window.alert(I18NMessages.getMessages().mergingSucess(
						presenter.getModel().getRowscn()));
			} else {
				Window.alert(I18NMessages.getMessages().insertionSuccess());
			}
		}
	};

	@UiField
	TextField description;

	@UiField
	ListBox type;

	public Widget asWidget() {

		southData = new BorderLayoutData(565);
		ENTITY_NAME = "UserGroup";
		widget = gxtUiBinder.createAndBindUi(this);
		description.setWidth(300);
		type.addItem("");
		type.addItem("Simple");
		type.addItem("Admin");
		type.setItemSelected(0, true);
		type.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				int index = type.getSelectedIndex();
				if (index != 0) {
					if (presenter.getModel() != null) {
						presenter.getModel().setType(type.getItemText(index));
					}
				}
			}
		});
		buildForm();
		this.setEventBus(presenter.getEventBus());
		this.finalizeForm(callback, ENTITY_NAME);
		this.driver = GWT.create(UserGroupDriver.class);
		this.edit();
		if (dataDetail != null && mainContainer != null) {
			final double ds = dataDetail.getSize();
			if (ds >= 50) {
				mainContainer.addResizeHandler(new ResizeHandler() {
					@Override
					public void onResize(ResizeEvent event) {
						if (ms == 0) {
							ms = mainContainer.getOffsetHeight(false);
							if (ms > 0) {
								pct = (ds * 100) / ms;
							}
						}
						if (ms > 0
								&& ms != mainContainer.getOffsetHeight(false)) {
							double nSize = (mainContainer
									.getOffsetHeight(false) * pct) / 100;
							if (nSize <= dataDetail.getMinSize()) {
								nSize = dataDetail.getMinSize();
							}
							if (nSize > dataDetail.getMaxSize()) {
								nSize = dataDetail.getMaxSize();
							}
							dataDetail.setSize(nSize);
						}
					}
				});
			}
		}
		return widget;
	}

	@Override
	public void updateView(UserGroup model) {
		try {
			if (model == null || model.getEntityId() == null) {
				driver.edit(model);
				entityId = null;
				resetDetails();
				ViewUtils.unNotify(htmlMessage);
				showInfoBanner(false);
				type.setSelectedIndex(0);
			} else {
				if ("admin".equalsIgnoreCase(model.getType())) {
					type.setSelectedIndex(2);
				} else if ("simple".equalsIgnoreCase(model.getType())) {
					type.setSelectedIndex(1);
				} else {
					type.setSelectedIndex(0);
				}
				driver.edit(model);
				refreshDetails();// Add form embeded object
				entityId = model.getEntityId();
				ViewUtils.showAuditInfos(htmlMessage,
						model.getGlobalAuditInfo());
				presenter.setSearchPending(false);
			}
			if (presenter != null) {
				formReset();
				toolsBar.onRecordChanged(presenter.getIndex() + 1,
						presenter.getTotalRecords());
			}
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			Window.alert(ex.getMessage() != null ? ex.getMessage() : ex
					.toString());
		}
	}

	@Override
	public UserGroup updateModel(UserGroup model) {
		try {
			if (model == null) {
				model = new UserGroup();
			}
			model.merge(model);
			model.setEntityId(entityId);
			model.setUpdating(false);
			return model;
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			Window.alert(ex.getMessage() != null ? ex.getMessage() : ex
					.toString());
		}
		return null;
	}

	@Override
	public void onDataChanged(UserGroup model, boolean reloadDetails,
			boolean setBtnsPersistVisible) {
		this.onChangeHeader(model, reloadDetails, setBtnsPersistVisible);
	}

	@Override
	public void onFormCleared() {
		bannerInfoIsShowed = false;
		showInfoBanner(bannerInfoIsShowed);
		this.type.setSelectedIndex(0);
		this.resetDetails();
	}

	@Override
	public void onPersistenceSuccessed() {
		// this.persistDetails(); // Attente de correction
		if (box != null) {
			box.hide();
			box = null;
		}
	}

	@Override
	protected void addDetail(Widget widget) {
		dataDetail.setSize(350);
		dataDetail.setMinSize(50);
		dataDetail.setMaxSize(350);
		mainContainer.setSouthWidget(widget, dataDetail);
	}

	@Override
	protected void addDetails(Widget widget, String title) {
		if (folder == null) {
			folder = new TabPanel();
			folder.setWidth(350);
			dataDetail.setSize(350);
			dataDetail.setMinSize(50);
			dataDetail.setMaxSize(350);
			mainContainer.setSouthWidget(folder, dataDetail);
		}
		folder.add(widget, title);
	}

	public IWidget getViewWidget() {
		return this;
	}

	public void setRenderer(ToolTipConfig config) {
		config.setRenderer(renderer);
	}

	@Override
	public void setViewCallback(ViewCallback callback) {
		this.callback = callback;
	}

	@Override
	public void updateView(List<UserGroup> models) {
		// TODO Auto-generated method stub
	}

	interface UserGroupDriver extends
			SimpleBeanEditorDriver<UserGroup, UserGroupView> {
	}

	@Override
	protected SimpleBeanEditorDriver<UserGroup, ? super ViewFormBase<UserGroup>> createDriver() {
		return GWT.create(UserGroupDriver.class);
	}

	protected void edit() {
		// Initialize the driver with the top-level editor
		driver.initialize(this);
		// Copy the data in the object into the UInext
		driver.edit(presenter.getModel());
	}
}