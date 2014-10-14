package com.rsoft.medicasoft.client.view;

/*
 Robelkend Templates Generator
 */
/*@Author=Jean Louidort*/
/*@Generation Date=Tue Oct 08 19:46:11 EDT 2013*/
/*@Version=1.0*/
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.client.UserRequestCallbackAdapter;
import com.rsoft.medicasoft.client.presenter.PersistingCallback;
import com.rsoft.medicasoft.client.presenter.QuickUserGroupPresenter;
import com.rsoft.medicasoft.client.presenter.UserProfilePresenter;
import com.rsoft.medicasoft.client.view.references.QuickViewCallback;
import com.rsoft.medicasoft.client.view.viewinterface.IPresenter;
import com.rsoft.medicasoft.client.view.viewinterface.IQuickView;
import com.rsoft.medicasoft.client.view.viewinterface.IView;
import com.rsoft.medicasoft.client.view.viewinterface.IWidget;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.StaticField;
import com.rsoft.medicasoft.shared.i18n.I18NMessages;
import com.rsoft.medicasoft.shared.model.RequestDescriptor;
import com.rsoft.medicasoft.shared.model.UserGroup;
import com.rsoft.medicasoft.shared.model.UserProfile;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.box.AutoProgressMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.DateTimePropertyEditor;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.MaxLengthValidator;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;

public class UserProfileView extends UserProfileMasterWrapperView {
	public interface Renderer extends ToolTipConfig.ToolTipRenderer<Object>,
			XTemplates {
		@Override
		@XTemplate(source = "help/userProfileTemplate.html")
		public SafeHtml renderToolTip(Object data);
	}

	private Renderer renderer = GWT.create(Renderer.class);
	private static GxtUiBinder gxtUiBinder = GWT.create(GxtUiBinder.class);

	private UserProfilePresenter presenter;

	interface GxtUiBinder extends UiBinder<Widget, UserProfileView> {
	}

	@Override
	protected void execute(ActionCommand command,
			UserRequestCallbackAdapter<UserProfile> urc) {
		presenter.getEventBus().executeUserProfile(command, urc);
	}

	@Override
	public void setPresenter(IPresenter p) {
		this.presenter = (UserProfilePresenter) p;
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

	@Ignore
	@UiField(provided = true)
	DateTimePropertyEditor datePropertyEditor = new DateTimePropertyEditor(
			DateTimeFormat.getFormat(StaticField.DATE_FORMAT));
	@UiField
	TextField userId;

	@UiField
	TextField title;

	@UiField
	TextField firstName;

	@UiField
	TextField lastName;

	@UiField
	TextField status;

	@UiField
	ListBox sex;

	@UiField
	ListBox language;

	@Ignore
	@UiField
	TextButton btnActivate;

	@Ignore
	@UiField
	TextButton btnDeactivate;

	@Ignore
	@UiField
	TextButton btnResetPassword;

	@Ignore
	@UiField
	TextButton btnLoadUserGroup;

	@Ignore
	@UiField
	FieldLabel labelStatus;

	@Ignore
	@UiField(provided = true)
	com.rsoft.medicasoft.client.lovs.LovUserGroup.ILovUserGroup iLovUserGroup9 = new com.rsoft.medicasoft.client.lovs.LovUserGroup.ILovUserGroup() {
		@Override
		public void loadUserGroup(
				com.rsoft.medicasoft.shared.model.UserGroup model,
				PagingLoadConfig loadConfig,
				AsyncCallback<PagingLoadResult<com.rsoft.medicasoft.shared.model.UserGroup>> callback) {
			UserRequestCallbackAdapter<UserGroup> urc = new UserRequestCallbackAdapter<UserGroup>();
			urc.setRequestDescriptor(new RequestDescriptor("UserGroup"));
			urc.setAsyncCallback(callback);
			urc.setLoadConfig(loadConfig);
			urc.setModel(model);
			presenter.getEventBus().executeUserProfile(
					ActionCommand.COLUMNAR_SEARCH, urc);
		}
	};

	@UiField(provided = true)
	com.rsoft.medicasoft.client.lovs.LovUserGroup userGroup;

	@UiField
	PasswordField pinCode;
	@Ignore
	@UiField
	PasswordField pinCodeConfirmation;
	@UiField
	DateField expirationDate;

	@UiField
	TextField email;
	private com.sencha.gxt.widget.core.client.Window window;

	public Widget asWidget() {
		ENTITY_NAME = "UserProfile";

		userGroup = new com.rsoft.medicasoft.client.lovs.LovUserGroup(
				iLovUserGroup9);

		widget = gxtUiBinder.createAndBindUi(this);
		buildForm();

		btnLoadUserGroup.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				QuickUserGroupPresenter presenter = eventBus
						.addHandler(QuickUserGroupPresenter.class);
				IQuickView<UserGroup> v = (IQuickView<UserGroup>) presenter
						.getView();
				window = createReferenceWindow(v);
				v.setQuickViewCallback(new QuickViewCallback<UserGroup>() {
					@Override
					public void persistSuccess(UserGroup model) {
						userGroup.setValue(model);
						window.hide();
						window = null;
					}
				});
				v.setPresenter(presenter);
				window.show();
			}
		});

		btnResetPassword.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				if (presenter.getModel() != null) {
					resetPassword();
				}
			}
		});

		btnActivate.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				if (presenter.getModel() != null) {
					activate();
				}
			}
		});
		
		btnDeactivate.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				if (presenter.getModel() != null) {
					deactivate();
				}
			}
		});
		btnResetPassword.setEnabled(false);
		btnActivate.setEnabled(false);
		btnDeactivate.setEnabled(false);
		status.setEnabled(false);
		this.setEventBus(presenter.getEventBus());

		userId.addValidator(new MaxLengthValidator(20));

		title.addValidator(new MaxLengthValidator(20));

		firstName.addValidator(new MaxLengthValidator(50));

		lastName.addValidator(new MaxLengthValidator(40));

		status.addValidator(new MaxLengthValidator(20));

		language.addItem("");
		language.addItem("Kreyol");
		language.addItem("Francais");
		language.addItem("English");
		language.setItemSelected(0, true);
		language.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				int index = language.getSelectedIndex();
				if (index != 0) {
					if (presenter.getModel() != null) {
						presenter.getModel().setLanguage(
								language.getItemText(index));
					}
				}
			}
		});

		sex.addItem("");
		sex.addItem("Masculin");
		sex.addItem("Feminin");

		sex.setItemSelected(0, true);
		sex.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				int index = sex.getSelectedIndex();
				if (index != 0) {
					if (presenter.getModel() != null) {
						presenter.getModel().setSex(sex.getItemText(index));
					}
				}
			}
		});
		userGroup.setEmptyText(messages.listOfValues(messages.userGroupId()));
		userGroup.setStyleName("lovField", false);
		email.addValidator(new MaxLengthValidator(50));

		this.finalizeForm(callback, ENTITY_NAME);
		this.driver = GWT.create(UserProfileDriver.class);
		this.edit();
		return widget;
	}

	/*
	 * Cette methode permet de reinitialiser le driver model en vue de presenter
	 * le model à l'utilisateur.
	 */
	@Override
	public void updateView(UserProfile model) {
		try {
			if (model == null || model.getEntityId() == null) {
				driver.edit(presenter.getModel());
				entityId = null;
				this.resetDetails();
				ViewUtils.unNotify(htmlMessage);
				showInfoBanner(false);
				btnActivate.setEnabled(false);
				btnDeactivate.setEnabled(false);
				btnResetPassword.setEnabled(false);
				sex.setSelectedIndex(0);
				language.setSelectedIndex(0);
				pinCodeConfirmation.setText("");
			} else {
				if ("Feminin".equalsIgnoreCase(model.getSex())) {
					sex.setSelectedIndex(2);
				} else if ("Masculin".equalsIgnoreCase(model.getSex())) {
					sex.setSelectedIndex(1);
				} else {
					sex.setSelectedIndex(0);
				}
				if ("English".equalsIgnoreCase(model.getLanguage())) {
					language.setSelectedIndex(3);
				} else if ("Francais".equalsIgnoreCase(model.getLanguage())) {
					language.setSelectedIndex(2);
				} else if ("Kreyol".equalsIgnoreCase(model.getLanguage())) {
					language.setSelectedIndex(1);
				} else {
					language.setSelectedIndex(0);
				}
				btnActivate.setEnabled("INACTIF".equalsIgnoreCase(model
						.getStatus()));
				btnDeactivate.setEnabled("ACTIF".equalsIgnoreCase(model
						.getStatus()));
				btnResetPassword.setEnabled("ACTIF".equalsIgnoreCase(model
						.getStatus()));
				driver.edit(model);
				entityId = model.getEntityId();
				if (entityId != null) {
					status.setEnabled(false);
				}
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
	public UserProfile updateModel(UserProfile model) {
		try {
			if (model == null) {
				model = new UserProfile();
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
	public void onDataChanged(UserProfile model, boolean reloadDetails,
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
		this.persistDetails(); // Attente de correction
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

	public void setRenderer(ToolTipConfig config) {
		config.setRenderer(renderer);
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
	public void updateView(List<UserProfile> models) {
		// TODO Auto-generated method stub
	}

	interface UserProfileDriver extends
			SimpleBeanEditorDriver<UserProfile, UserProfileView> {
	}

	/*
	 * Cette methode renvoie le model editor créé.
	 */
	@Override
	protected SimpleBeanEditorDriver<UserProfile, ? super ViewFormBase<UserProfile>> createDriver() {
		return GWT.create(UserProfileDriver.class);
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
	public void persist() {

		if (presenter.getModel() != null
				|| pinCodeConfirmation.getText().equals(pinCode.getText())) {
			super.persist();
		} else {
			Window.alert(messages.passwordDoesNotMatch());
		}
	}

	@Override
	public void criteria() {
		super.criteria();
		status.setEnabled(true);
	}

	private void activate() {
		UserProfile model = driver.flush();
		model.setEXTSTATUS("ACTIVATE");
		box = new AutoProgressMessageBox(messages.pleaseWait(),
				messages.savingProgress());
		box.setProgressText(messages.savingProgress());
		box.auto();
		box.show();
		ActionCommand command = ActionCommand.MERGE;
		UserRequestCallbackAdapter<UserProfile> urc = new UserRequestCallbackAdapter<UserProfile>();
		urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
		execute(command, urc);
	}

	private void deactivate() {
		UserProfile model = driver.flush();
		model.setEXTSTATUS("DEACTIVATE");

		box = new AutoProgressMessageBox(messages.pleaseWait(),
				messages.savingProgress());
		box.setProgressText(messages.savingProgress());
		box.auto();
		box.show();
		ActionCommand command = ActionCommand.MERGE;
		UserRequestCallbackAdapter<UserProfile> urc = new UserRequestCallbackAdapter<UserProfile>();
		urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
		execute(command, urc);
	}

	private void resetPassword() {
		if (pinCodeConfirmation.getText().equals(pinCode.getText())) {
			UserProfile model = driver.flush();
			model.setEXTSTATUS("CHANGE_PASSWORD");

			box = new AutoProgressMessageBox(messages.pleaseWait(),
					messages.savingProgress());
			box.setProgressText(messages.savingProgress());
			box.auto();
			box.show();
			ActionCommand command = ActionCommand.MERGE;
			UserRequestCallbackAdapter<UserProfile> urc = new UserRequestCallbackAdapter<UserProfile>();
			urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
			execute(command, urc);
		} else {
			Window.alert(messages.passwordDoesNotMatch());
		}
	}

	private <V extends ModelBase> com.sencha.gxt.widget.core.client.Window createReferenceWindow(
			IView<V> view) {
		com.sencha.gxt.widget.core.client.Window w = new com.sencha.gxt.widget.core.client.Window();
		w.setHeadingText(messages.userGroupId());
		w.setTitle(messages.userGroupId());
		w.add(view);
		w.setShadow(true);
		w.setWidth(425);
		w.setHeight(130);
		return w;
	}
}