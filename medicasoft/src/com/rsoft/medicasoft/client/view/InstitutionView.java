package com.rsoft.medicasoft.client.view;

/*
 Robelkend Templates Generator
 */
/*@Author=Jean Louidort*/
/*@Generation Date=Sat Nov 02 22:30:05 EDT 2013*/
/*@Version=1.0*/
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.client.UserRequestCallbackAdapter;
import com.rsoft.medicasoft.client.presenter.InstitutionPresenter;
import com.rsoft.medicasoft.client.presenter.PersistingCallback;
import com.rsoft.medicasoft.client.view.viewinterface.IPresenter;
import com.rsoft.medicasoft.client.view.viewinterface.IWidget;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.StaticField;
import com.rsoft.medicasoft.shared.i18n.I18NMessages;
import com.rsoft.medicasoft.shared.model.Commune;
import com.rsoft.medicasoft.shared.model.Departement;
import com.rsoft.medicasoft.shared.model.Institution;
import com.rsoft.medicasoft.shared.model.Pays;
import com.rsoft.medicasoft.shared.model.RequestDescriptor;
import com.rsoft.medicasoft.shared.model.Zone;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.box.AutoProgressMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.DateTimePropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.IntegerPropertyEditor;
import com.sencha.gxt.widget.core.client.form.Radio;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.MaxLengthValidator;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;

public class InstitutionView extends InstitutionMasterWrapperView {
	public interface Renderer extends ToolTipConfig.ToolTipRenderer<Object>,
			XTemplates {
		@Override
		@XTemplate(source = "help/institutionTemplate.html")
		public SafeHtml renderToolTip(Object data);
	}

	private Renderer renderer = GWT.create(Renderer.class);
	private static GxtUiBinder gxtUiBinder = GWT.create(GxtUiBinder.class);

	private InstitutionPresenter presenter;

	interface GxtUiBinder extends UiBinder<Widget, InstitutionView> {
	}

	@Override
	protected void execute(ActionCommand command,
			UserRequestCallbackAdapter<Institution> urc) {
		presenter.getEventBus().executeInstitution(command, urc);
	}

	@Override
	public void setPresenter(IPresenter p) {
		this.presenter = (InstitutionPresenter) p;
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
	NumberPropertyEditor<Integer> integerPropertyEditor = new IntegerPropertyEditor();
	@UiField(provided = true)
	NumberFormat inumberFormat = NumberFormat
			.getFormat(StaticField.INTEGER_FORMAT);
	@Ignore
	@UiField(provided = true)
	DateTimePropertyEditor datePropertyEditor = new DateTimePropertyEditor(
			DateTimeFormat.getFormat(StaticField.DATE_FORMAT));

	@UiField
	TextField nomInstitution;

	@UiField
	TextField nomCommercialeInstitution;

	@UiField
	DateField dateCreation;

	@UiField
	TextField adresse;

	@Ignore
	@UiField(provided = true)
	com.rsoft.medicasoft.client.lovs.LovZone.ILovZone iLovZone6 = new com.rsoft.medicasoft.client.lovs.LovZone.ILovZone() {
		@Override
		public void loadZone(
				com.rsoft.medicasoft.shared.model.Zone model,
				PagingLoadConfig loadConfig,
				AsyncCallback<PagingLoadResult<com.rsoft.medicasoft.shared.model.Zone>> callback) {
			if (commune.getValue() != null) {
				UserRequestCallbackAdapter<Zone> urc = new UserRequestCallbackAdapter<Zone>();
				urc.setRequestDescriptor(new RequestDescriptor("Zone"));
				urc.setAsyncCallback(callback);
				urc.setLoadConfig(loadConfig);
				urc.setModel(model);
				urc.setLov(true);
				urc.setRefName("communeId");
				urc.setRefModel(commune.getValue());
				presenter.getEventBus().executeInstitution(
						ActionCommand.COLUMNAR_SEARCH, urc);
			} else {
				Window.alert(messages.selectParentFirst(messages.commune()));
			}
		}
	};

	@UiField(provided = true)
	com.rsoft.medicasoft.client.lovs.LovZone zone;
	@Ignore
	@UiField(provided = true)
	com.rsoft.medicasoft.client.lovs.LovCommune.ILovCommune iLovCommune7 = new com.rsoft.medicasoft.client.lovs.LovCommune.ILovCommune() {
		@Override
		public void loadCommune(
				com.rsoft.medicasoft.shared.model.Commune model,
				PagingLoadConfig loadConfig,
				AsyncCallback<PagingLoadResult<com.rsoft.medicasoft.shared.model.Commune>> callback) {
			if (departement.getValue() != null) {
				UserRequestCallbackAdapter<Commune> urc = new UserRequestCallbackAdapter<Commune>();
				urc.setRequestDescriptor(new RequestDescriptor("Commune"));
				urc.setAsyncCallback(callback);
				urc.setLoadConfig(loadConfig);
				urc.setModel(model);
				urc.setLov(true);
				urc.setRefName("departementId");
				urc.setRefModel(departement.getValue());
				presenter.getEventBus().executeInstitution(
						ActionCommand.COLUMNAR_SEARCH, urc);
			} else {
				Window.alert(messages.selectParentFirst(messages.departement()));
			}
		}
	};

	@UiField(provided = true)
	com.rsoft.medicasoft.client.lovs.LovCommune commune;
	@Ignore
	@UiField(provided = true)
	com.rsoft.medicasoft.client.lovs.LovDepartement.ILovDepartement iLovDepartement8 = new com.rsoft.medicasoft.client.lovs.LovDepartement.ILovDepartement() {
		@Override
		public void loadDepartement(
				com.rsoft.medicasoft.shared.model.Departement model,
				PagingLoadConfig loadConfig,
				AsyncCallback<PagingLoadResult<com.rsoft.medicasoft.shared.model.Departement>> callback) {
			if (pays.getValue() != null) {
				UserRequestCallbackAdapter<Departement> urc = new UserRequestCallbackAdapter<Departement>();
				urc.setRequestDescriptor(new RequestDescriptor("Departement"));
				urc.setAsyncCallback(callback);
				urc.setLoadConfig(loadConfig);
				urc.setModel(model);
				urc.setLov(true);
				urc.setRefName("paysId");
				urc.setRefModel(pays.getValue());
				presenter.getEventBus().executeInstitution(
						ActionCommand.COLUMNAR_SEARCH, urc);
			} else {
				Window.alert(messages.selectParentFirst(messages.pays()));
			}
		}
	};

	@UiField(provided = true)
	com.rsoft.medicasoft.client.lovs.LovDepartement departement;
	@Ignore
	@UiField(provided = true)
	com.rsoft.medicasoft.client.lovs.LovPays.ILovPays iLovPays9 = new com.rsoft.medicasoft.client.lovs.LovPays.ILovPays() {
		@Override
		public void loadPays(
				com.rsoft.medicasoft.shared.model.Pays model,
				PagingLoadConfig loadConfig,
				AsyncCallback<PagingLoadResult<com.rsoft.medicasoft.shared.model.Pays>> callback) {
			UserRequestCallbackAdapter<Pays> urc = new UserRequestCallbackAdapter<Pays>();
			urc.setRequestDescriptor(new RequestDescriptor("Pays"));
			urc.setAsyncCallback(callback);
			urc.setLoadConfig(loadConfig);
			urc.setLov(true);
			urc.setModel(model);
			presenter.getEventBus().executeInstitution(
					ActionCommand.COLUMNAR_SEARCH, urc);
		}
	};

	@UiField(provided = true)
	com.rsoft.medicasoft.client.lovs.LovPays pays;
	@UiField
	TextField telephone1;

	@UiField
	TextField telephone2;

	@UiField
	TextField telephone3;

	@UiField
	TextField courriel;

	@UiField
	TextField nomReponsable;

	@UiField
	TextField prenomResponsable;

	@UiField
	TextField extensionResponsable;

	@UiField
	TextField courrielResponsable;

	@UiField
	TextField titreResponsable;

	@UiField
	TextField sexeResponsable;

	@UiField
	TextField modalitePaiement;

	@UiField
	TextField typePaiement;

	@UiField
	NumberField<Integer> balance;

	@UiField
	DateField dateBalance;

	@UiField
	TextField statut;

	@UiField
	NumberField<Integer> delaiGrace;

	@UiField
	TextField nomContact;

	@UiField
	TextField prenomContact;

	@UiField
	TextField courrielContact;

	@UiField
	TextField telephoneContact;

	@UiField
	TextField telephoneResponsable;

	@UiField
	TextField titreContact;
	@Ignore
	@UiField
	AccordionLayoutContainer accordion;

	@Ignore
	@UiField
	TextButton btnActivate;

	@Ignore
	@UiField
	TextButton btnDeactivate;

	@Ignore
	@UiField
	Radio radioClinique;
	@Ignore
	@UiField
	Radio radioCentreSante;
	@Ignore
	@UiField
	Radio radioHopital;
	@Ignore
	@UiField
	CheckBox checkPharmacie;
	@Ignore
	@UiField
	CheckBox checkLaboratoire;
	@Ignore
	@UiField
	Radio radioGouvernement;
	@Ignore
	@UiField
	Radio radioEtatCivil;
	@Ignore
	@UiField
	Radio radioArchives;
	@Ignore
	@UiField
	Radio radioEmployeur;
	@Ignore
	@UiField
	Radio radioAssureur;
	@Ignore
	@UiField
	Radio radioOng;

	public Widget asWidget() {
		ENTITY_NAME = "Institution";

		zone = new com.rsoft.medicasoft.client.lovs.LovZone(iLovZone6);
		departement = new com.rsoft.medicasoft.client.lovs.LovDepartement(
				iLovDepartement8);
		pays = new com.rsoft.medicasoft.client.lovs.LovPays(iLovPays9);
		commune = new com.rsoft.medicasoft.client.lovs.LovCommune(iLovCommune7);
		widget = gxtUiBinder.createAndBindUi(this);
		accordion.setActiveWidget((ContentPanel) accordion.getWidget(0));
		accordion.setWidth("350");
		accordion.setHeight("460");
		statut.setEnabled(false);
		buildForm();
		panel.setBorders(false);
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
		btnActivate.setEnabled(false);
		btnDeactivate.setEnabled(false);

		pays.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				departement.setValue(null, true);
				commune.setValue(null, true);
				zone.setValue(null, true);
			}
		});

		departement.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				commune.setValue(null, true);
				zone.setValue(null, true);
			}
		});

		commune.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				zone.setValue(null, true);
			}
		});

		ToggleGroup toggle1 = new ToggleGroup();
		toggle1.add(radioClinique);
		toggle1.add(radioCentreSante);
		toggle1.add(radioHopital);
		toggle1.add(radioGouvernement);
		toggle1.add(radioEtatCivil);
		toggle1.add(radioArchives);
		toggle1.add(radioEmployeur);
		toggle1.add(radioAssureur);
		toggle1.add(radioOng);
 		
		ToggleGroup toggle2 = new ToggleGroup();
		toggle2.add(checkPharmacie);
		toggle2.add(radioGouvernement);
		toggle2.add(radioEtatCivil);
		toggle2.add(radioArchives);
		toggle2.add(radioEmployeur);
		toggle2.add(radioAssureur);
		toggle2.add(radioOng);
		
		ToggleGroup toggle3 = new ToggleGroup();
		toggle3.add(checkLaboratoire);
		toggle3.add(radioGouvernement);
		toggle3.add(radioEtatCivil);
		toggle3.add(radioArchives);
		toggle3.add(radioEmployeur);
		toggle3.add(radioAssureur);
		toggle3.add(radioOng);
		setEventBus(presenter.getEventBus());

		nomInstitution.addValidator(new MaxLengthValidator(20));

		nomCommercialeInstitution.addValidator(new MaxLengthValidator(20));

		adresse.addValidator(new MaxLengthValidator(18));

		zone.setEmptyText(messages.listOfValues(messages.zone()));
		zone.setStyleName("lovField", false);
		commune.setEmptyText(messages.listOfValues(messages.commune()));
		commune.setStyleName("lovField", false);
		departement.setEmptyText(messages.listOfValues(messages.departement()));
		departement.setStyleName("lovField", false);
		pays.setEmptyText(messages.listOfValues(messages.pays()));
		pays.setStyleName("lovField", false);
		telephone1.addValidator(new MaxLengthValidator(20));

		telephone2.addValidator(new MaxLengthValidator(20));

		telephone3.addValidator(new MaxLengthValidator(20));

		courriel.addValidator(new MaxLengthValidator(20));

		nomReponsable.addValidator(new MaxLengthValidator(18));

		prenomResponsable.addValidator(new MaxLengthValidator(20));

		extensionResponsable.addValidator(new MaxLengthValidator(20));

		courrielResponsable.addValidator(new MaxLengthValidator(20));

		titreResponsable.addValidator(new MaxLengthValidator(20));

		sexeResponsable.addValidator(new MaxLengthValidator(20));

		modalitePaiement.addValidator(new MaxLengthValidator(20));

		typePaiement.addValidator(new MaxLengthValidator(20));

		statut.addValidator(new MaxLengthValidator(20));

		nomContact.addValidator(new MaxLengthValidator(20));

		prenomContact.addValidator(new MaxLengthValidator(20));

		courrielContact.addValidator(new MaxLengthValidator(20));

		telephoneContact.addValidator(new MaxLengthValidator(20));

		telephoneResponsable.addValidator(new MaxLengthValidator(20));

		titreContact.addValidator(new MaxLengthValidator(20));

		this.finalizeForm(callback, ENTITY_NAME);
		this.driver = GWT.create(InstitutionDriver.class);
		this.edit();
		return widget;
	}

	/*
	 * Cette methode permet de reinitialiser le driver model en vue de presenter
	 * le model � l'utilisateur.
	 */
	@Override
	public void updateView(Institution model) {
		try {
			if (model == null || model.getEntityId() == null) {
				driver.edit(presenter.getModel());
				entityId = null;
				this.resetDetails();
				ViewUtils.unNotify(htmlMessage);
				showInfoBanner(false);
				btnActivate.setEnabled(false);
				btnDeactivate.setEnabled(false);

				radioClinique.setValue(false);
				radioCentreSante.setValue(false);
				radioHopital.setValue(false);
				checkPharmacie.setValue(false);
				checkLaboratoire.setValue(false);
				radioGouvernement.setValue(false);
				radioEtatCivil.setValue(false);
				radioArchives.setValue(false);
				radioEmployeur.setValue(false);
				radioAssureur.setValue(false);
				radioOng.setValue(false);
			} else {
				radioClinique.setValue(model.isTypeInstitutionDefined(radioClinique.getName()));
				radioCentreSante.setValue(model.isTypeInstitutionDefined(radioCentreSante.getName()));
				radioHopital.setValue(model.isTypeInstitutionDefined(radioHopital.getName()));
				checkPharmacie.setValue(model.isTypeInstitutionDefined(checkPharmacie.getName()));
				checkLaboratoire.setValue(model.isTypeInstitutionDefined(checkLaboratoire.getName()));
				radioGouvernement.setValue(model.isTypeInstitutionDefined(radioGouvernement.getName()));
				radioEtatCivil.setValue(model.isTypeInstitutionDefined(radioEtatCivil.getName()));
				radioArchives.setValue(model.isTypeInstitutionDefined(radioArchives.getName()));
				radioEmployeur.setValue(model.isTypeInstitutionDefined(radioEmployeur.getName()));
				radioAssureur.setValue(model.isTypeInstitutionDefined(radioAssureur.getName()));
				radioOng.setValue(model.isTypeInstitutionDefined(radioOng.getName()));
				driver.edit(model);
				entityId = model.getEntityId();
				btnActivate.setEnabled("INACTIF".equalsIgnoreCase(model
						.getStatut())
						|| "NOUVEAU".equalsIgnoreCase(model.getStatut()));
				btnDeactivate.setEnabled("ACTIF".equalsIgnoreCase(model
						.getStatut()));
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
	public Institution updateModel(Institution model) {
		try {
			if (model == null) {
				model = new Institution();
			}
			model.merge(model);
			model.setEntityId(entityId);
			model.setUpdating(false);
			model.addTypeInstitution(radioClinique.getName(),
					radioClinique.getValue());
			model.addTypeInstitution(radioCentreSante.getName(),
					radioCentreSante.getValue());
			model.addTypeInstitution(radioHopital.getName(),
					radioHopital.getValue());
			model.addTypeInstitution(checkPharmacie.getName(),
					checkPharmacie.getValue());
			model.addTypeInstitution(checkLaboratoire.getName(),
					checkLaboratoire.getValue());
			model.addTypeInstitution(radioGouvernement.getName(),
					radioGouvernement.getValue());
			model.addTypeInstitution(radioEtatCivil.getName(),
					radioEtatCivil.getValue());
			model.addTypeInstitution(radioArchives.getName(),
					radioArchives.getValue());
			model.addTypeInstitution(radioEmployeur.getName(),
					radioEmployeur.getValue());
			model.addTypeInstitution(radioAssureur.getName(),
					radioAssureur.getValue());
			model.addTypeInstitution(radioOng.getName(), radioOng.getValue());
			return model;
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			Window.alert(ex.getMessage() != null ? ex.getMessage() : ex
					.toString());
		}
		return null;
	}

	private void activate() {
		Institution model = driver.flush();
		model.setEXTSTATUS("ACTIVATE");
		box = new AutoProgressMessageBox(messages.pleaseWait(),
				messages.savingProgress());
		box.setProgressText(messages.savingProgress());
		box.auto();
		box.show();
		ActionCommand command = ActionCommand.MERGE;
		UserRequestCallbackAdapter<Institution> urc = new UserRequestCallbackAdapter<Institution>();
		urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
		execute(command, urc);
	}

	private void deactivate() {
		Institution model = driver.flush();
		model.setEXTSTATUS("DEACTIVATE");

		box = new AutoProgressMessageBox(messages.pleaseWait(),
				messages.savingProgress());
		box.setProgressText(messages.savingProgress());
		box.auto();
		box.show();
		ActionCommand command = ActionCommand.MERGE;
		UserRequestCallbackAdapter<Institution> urc = new UserRequestCallbackAdapter<Institution>();
		urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
		execute(command, urc);
	}

	/*
	 * Cette methode est appel�e apres une recherche, un click sur les boutons
	 * de navigation(suivant,dernier,premier,precedent).
	 */
	@Override
	public void onDataChanged(Institution model, boolean reloadDetails,
			boolean setBtnsPersistVisible) {
		this.onChangeHeader(model, reloadDetails, setBtnsPersistVisible);
	}

	/*
	 * Cette methode est appel�e apres une reunitialisation reussie.
	 */
	@Override
	public void onFormCleared() {
		bannerInfoIsShowed = false;
		showInfoBanner(bannerInfoIsShowed);
		this.resetDetails();
	}

	/*
	 * Cette methode est appel�e apres une insertion ou une modification
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
	 * Cette methode permet d'ajouter les details, chaque detail sera ajout�
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
	 * chaque detail sera ajout� dans un nouveau onglet.
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
	public void updateView(List<Institution> models) {
		// TODO Auto-generated method stub
	}

	interface InstitutionDriver extends
			SimpleBeanEditorDriver<Institution, InstitutionView> {
	}

	/*
	 * Cette methode renvoie le model editor cr��.
	 */
	@Override
	protected SimpleBeanEditorDriver<Institution, ? super ViewFormBase<Institution>> createDriver() {
		return GWT.create(InstitutionDriver.class);
	}

	/*
	 * Cette methode permet d'initialiser le driver editor � partir du model
	 * se trouvant dans le presenter. pendant toute la duree de vie de la forme,
	 * le contenu de l'editeur sera le meme que le modele du presenteur.
	 */
	protected void edit() {
		// Initialize the driver with the top-level editor
		driver.initialize(this);
		// Copy the data in the object into the UInext
		driver.edit(presenter.getModel());
	}

}