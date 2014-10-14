package com.rsoft.medicasoft.client.view.references;

/*
 Robelkend Templates Generator
 */
/*@Author=Jean Louidort*/
/*@Generation Date=Sun Oct 13 17:25:52 EDT 2013*/
/*@Version=1.0*/
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.client.UserRequestCallbackAdapter;
import com.rsoft.medicasoft.client.presenter.PersistingCallback;
import com.rsoft.medicasoft.client.presenter.QuickUserGroupPresenter;
import com.rsoft.medicasoft.client.view.ViewCallback;
import com.rsoft.medicasoft.client.view.ViewUtils;
import com.rsoft.medicasoft.client.view.viewinterface.IPresenter;
import com.rsoft.medicasoft.client.view.viewinterface.IWidget;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.i18n.I18NMessages;
import com.rsoft.medicasoft.shared.model.UserGroup;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;

public class QuickUserGroupView extends UserGroupMasterWrapperView {
	private static GxtUiBinder gxtUiBinder = GWT.create(GxtUiBinder.class);

	private QuickUserGroupPresenter presenter;

	interface GxtUiBinder extends UiBinder<Widget, QuickUserGroupView> {
	}

	@Override
	protected void execute(ActionCommand command,
			UserRequestCallbackAdapter<UserGroup> urc) {
		presenter.getEventBus().executeQuickUserGroup(command, urc);
	}

	@Override
	public void setPresenter(IPresenter p) {
		this.presenter = (QuickUserGroupPresenter) p;
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
		if (callback != null) {
			callback.viewReset(widget.getTitle());
		}
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
			if (callback != null) {
				callback.viewChanged(widget.getTitle());
			}
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
			if (source != null && quickViewCallback != null
					&& ((UserGroup) source).getEntityId() != null) {
				quickViewCallback.persistSuccess(((UserGroup) source));
			}
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
		return widget;
	}

	/*
	 * Cette methode permet de reinitialiser le driver model en vue de presenter
	 * le model à l'utilisateur.
	 */
	@Override
	public void updateView(UserGroup model) {
		try {
			if (model == null || model.getEntityId() == null) {
				driver.edit(presenter.getModel());
				entityId = null;
				this.resetDetails();
				ViewUtils.unNotify(htmlMessage);
				showInfoBanner(false);
			} else {
				driver.edit(model);
				entityId = model.getEntityId();
				ViewUtils.showAuditInfos(htmlMessage,
						model.getGlobalAuditInfo());
				presenter.setSearchPending(false);
			}
			if (presenter != null) {
				formReset();
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

	/*
	 * Cette methode est appelée apres une recherche, un click sur les boutons
	 * de navigation(suivant,dernier,premier,precedent).
	 */
	@Override
	public void onDataChanged(UserGroup model, boolean reloadDetails,
			boolean setBtnsPersistVisible) {
		this.onChangeHeader(model, reloadDetails, setBtnsPersistVisible);
	}

	/*
	 * Cette methode est appelée apres une reunitialisation reussie.
	 */
	@Override
	public void onFormCleared() {
		bannerInfoIsShowed = false;
		showInfoBanner(bannerInfoIsShowed);
		this.resetDetails();
	}

	/*
	 * Cette methode est appelée apres une insertion ou une modification
	 * reussie.
	 */
	@Override
	public void onPersistenceSuccessed() {
		if (box != null) {
			box.hide();
			box = null;
		}
	}

	/*
	 * Cette methode permet d'ajouter les details, chaque detail sera ajouté
	 * dans un nouveau onglet.
	 */
	@Override
	protected void addDetail(Widget widget) {
		southData.setMargins(new Margins(5, 5, 5, 5));
		southData.setCollapsible(true);
		southData.setSplit(true);
		mainContainer.setSouthWidget(widget, southData);
	}

	/*
	 * Cette methode permet d'ajouter les details avec un message comme titre,
	 * chaque detail sera ajouté dans un nouveau onglet.
	 */
	@Override
	protected void addDetails(Widget widget, String title) {
		if (folder == null) {
			folder = new TabPanel();
			folder.setWidth(450);
			mainContainer.setSouthWidget(folder, southData);
		}
		folder.add(widget, title);
	}

	public IWidget getViewWidget() {
		return this;
	}

	/*
	 * Cette methode permet d'affecter le callback de la vue, le callback permet
	 * d'ecouter les changements de la vue.
	 */
	@Override
	public void setViewCallback(ViewCallback callback) {
		this.callback = callback;
	}

	@Override
	public void updateView(List<UserGroup> models) {
		// TODO Auto-generated method stub
	}

	interface UserGroupDriver extends
			SimpleBeanEditorDriver<UserGroup, QuickUserGroupView> {
	}

	/*
	 * Cette methode renvoie le model editor créé.
	 */
	@Override
	protected SimpleBeanEditorDriver<UserGroup, ? super QuickViewFormBase<UserGroup>> createDriver() {
		return GWT.create(UserGroupDriver.class);
	}

	/*
	 * Cette methode permet d'initialiser le driver editor à partir du model se
	 * trouvant dans le presenter. pendant toute la duree de vie de la forme, le
	 * contenu de l'editeur sera le meme que le modele du presenteur.
	 */
	protected void edit() {
		// Initialize the driver with the top-level editor
		driver.initialize(this);
		// Copy the data in the object into the UInext
		driver.edit(presenter.getModel());
	}

	@Override
	public void setRenderer(ToolTipConfig config) {
		// TODO Auto-generated method stub

	}
}