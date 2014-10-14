package com.rsoft.medicasoft.client.view;

/*
 Robelkend Templates Generator
 */
/*@Author=Jean Louidort*/
/*@Generation Date=Thu Sep 19 22:50:28 EDT 2013*/
/*@Version=1.0*/

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.client.UserRequestCallbackAdapter;
import com.rsoft.medicasoft.client.presenter.AssurancePresenter;
import com.rsoft.medicasoft.client.presenter.PatientPresenter;
import com.rsoft.medicasoft.client.presenter.PersistingCallback;
import com.rsoft.medicasoft.client.view.viewinterface.IPresenter;
import com.rsoft.medicasoft.client.view.viewinterface.IView;
import com.rsoft.medicasoft.client.view.viewinterface.IWidget;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.StaticField;
import com.rsoft.medicasoft.shared.i18n.I18NMessages;
import com.rsoft.medicasoft.shared.model.Assurance;
import com.rsoft.medicasoft.shared.model.ComboPropertiesAccess;
import com.rsoft.medicasoft.shared.model.Item;
import com.rsoft.medicasoft.shared.model.Patient;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.button.ToggleButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.CardLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.DateTimePropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.IntegerPropertyEditor;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.MaxLengthValidator;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;

public class PatientView extends PatientMasterWrapperView {
	public interface Renderer extends ToolTipConfig.ToolTipRenderer<Object>,
			XTemplates {
		@Override
		@XTemplate(source = "help/patientTemplate.html")
		public SafeHtml renderToolTip(Object data);
	}

	private ComboPropertiesAccess comboProps = GWT
			.create(ComboPropertiesAccess.class);
	// L'utilisation de Item avec deux champs au lieu de String pour le combo
	// permet d'internationaliser les valeurs des combobox
	private ListStore<Item> religionItems = new ListStore<Item>(comboProps.ID());
	private ListStore<Item> sexeItems = new ListStore<Item>(comboProps.ID());

	private Renderer renderer = GWT.create(Renderer.class);
	private static GxtUiBinder gxtUiBinder = GWT.create(GxtUiBinder.class);

	private PatientPresenter presenter;

	interface GxtUiBinder extends UiBinder<Widget, PatientView> {
	}

	@Override
	protected void execute(ActionCommand command,
			UserRequestCallbackAdapter<Patient> urc) {
		presenter.getEventBus().executePatient(command, urc);
	}

	@Override
	public void setPresenter(IPresenter p) {
		this.presenter = (PatientPresenter) p;
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
	@Ignore
	@UiField(provided = true)
	NumberPropertyEditor<Integer> integerPropertyEditor = new IntegerPropertyEditor();
	@UiField(provided = true)
	NumberFormat inumberFormat = NumberFormat
			.getFormat(StaticField.INTEGER_FORMAT);
	@UiField
	TextField nom;

	@UiField
	TextField prenom;

	@UiField
	TextField statut;

	@UiField
	TextField dateNaissance;

	@UiField
	TextField lieuNaissance;

	@UiField
	DateField dateDeces;

	@UiField
	TextField lieuDeces;

	@UiField
	TextField heureNaissance;

	@UiField
	TextField heureDeces;

	@UiField
	TextArea causeDeces;

	@UiField
	TextField telephone1;

	@UiField
	TextField telephone2;

	@UiField
	TextField nomResponsable1;

	@UiField
	TextField prenomResponsable1;

	@UiField
	TextField courriel;

	@UiField
	TextField courrielResponsable1;

	@UiField
	TextField telephone1Responsable1;

	@UiField
	TextField telephone2Responsable1;

	@UiField
	TextField nomPere;

	@UiField
	TextField nomMere;

	@UiField
	TextField etatCivil;

	@UiField
	TextField nomEpoux;

	@UiField
	TextField prenomEpoux;

	@UiField
	TextField telephoneEpoux;

	@UiField
	TextField courrielEpoux;

	@UiField
	TextField prenomPere;

	@UiField
	TextField prenomMere;

	@UiField
	NumberField<Integer> pereId;

	@UiField
	NumberField<Integer> mereId;

	@UiField
	NumberField<Integer> epouxId;

	@UiField
	TextField nationalite;

	@UiField
	TextField paysResidence;

	@UiField
	TextField adresse;

	@UiField(provided = true)
	ComboBox<Item> sexeItem = new ComboBox<Item>(sexeItems,
			comboProps.description());

	@UiField
	TextField typeIdentication;

	@UiField
	TextField noIdentification;

	@UiField
	TextField paysIdentifcation;

	@UiField
	NumberField<Integer> nomEnfants;

	@UiField
	TextField lienParenteResponsable1;

	@UiField
	TextField telephonePere;

	@UiField
	TextField telephoneMere;

	@UiField
	TextField nomResponsable2;

	@UiField
	TextField prenomResponsable2;

	@UiField
	TextField courrielResponsable2;

	@UiField
	TextField telephone1Responsable2;

	@UiField
	TextField telephone2Responsable2;

	@UiField
	TextArea adresseResponsable1;

	@UiField
	TextArea adresseResponsable2;

	@UiField
	TextField taille;

	@UiField
	TextField poids;

	@UiField
	TextField zone;

	@UiField
	TextField commune;

	@UiField
	TextField departement;

	@UiField(provided = true)
	ComboBox<Item> religionItem = new ComboBox<Item>(religionItems,
			comboProps.description());
	@Ignore
	@UiField
	VerticalLayoutContainer assuranceContainer;
	@Ignore
	@UiField
	ToggleButton btnInfoPers;
	@Ignore
	@UiField
	ToggleButton btnInfoDeces;
	@Ignore
	@UiField
	ToggleButton btnInfoParent;
	@Ignore
	@UiField
	ToggleButton btnInfoResponsable;
	@Ignore
	@UiField
	ToggleButton btnAdresses;
	@Ignore
	@UiField
	ToggleButton btnAutresInfo;
	@Ignore
	@UiField
	ToggleButton btnDossier;
	@Ignore
	@UiField
	ToggleButton btnInfoSocioEcono;
	@Ignore
	@UiField
	ToggleButton btnAssurMedic;
	@Ignore
	@UiField
	CardLayoutContainer layout;
	@Ignore
	@UiField
	VBoxLayoutContainer buttonBox;
	@Ignore
	@UiField(provided = true)
	BorderLayoutData westData = new BorderLayoutData(230);

	private AssurancePresenter assurancePresenter;
	private IView<Assurance> assuranceView;

	
	public Widget asWidget() {
		ENTITY_NAME = "Patient";

		widget = gxtUiBinder.createAndBindUi(this);
		buildForm();

		religionItem.setExpanded(true);
		religionItem.setWidth(250);
		religionItem.setTypeAhead(true);
		religionItem.setTriggerAction(TriggerAction.ALL);
		religionItems.add(new Item("christianiste"));
		religionItems.add(new Item("christianisteAdventiste"));
		religionItems.add(new Item("christianisteMethodiste"));
		religionItems.add(new Item("christianisteAnglican"));
		religionItems.add(new Item("christianisteLutherien"));
		religionItems.add(new Item("christianistePentecotiste"));
		religionItems.add(new Item("christianisteBaptiste"));
		religionItems.add(new Item("christianisteTemoinJehovah"));
		religionItems.add(new Item("christianisteCatholique"));
		religionItems.add(new Item("judaiste"));

		religionItems.add(new Item("musulman"));
		religionItems.add(new Item("musulmanSunnite"));
		religionItems.add(new Item("musulmanChiite"));

		religionItems.add(new Item("boudhiste"));
		religionItems.add(new Item("vodouisant"));

		sexeItem.setExpanded(true);
		sexeItem.setTypeAhead(true);
		sexeItem.setTriggerAction(TriggerAction.ALL);
		sexeItems.add(new Item("masculin"));
		sexeItems.add(new Item("feminin"));
		this.setEventBus(presenter.getEventBus());

		ToggleGroup tgGroup = new ToggleGroup();

		tgGroup.add(btnInfoPers);

		tgGroup.add(btnInfoDeces);

		tgGroup.add(btnInfoParent);

		tgGroup.add(btnInfoResponsable);

		tgGroup.add(btnAdresses);

		tgGroup.add(btnAutresInfo);

		tgGroup.add(btnDossier);

		tgGroup.add(btnInfoSocioEcono);
		tgGroup.add(btnAssurMedic);
		nom.addValidator(new MaxLengthValidator(20));

		prenom.addValidator(new MaxLengthValidator(20));

		statut.addValidator(new MaxLengthValidator(20));

		dateNaissance.addValidator(new MaxLengthValidator(20));

		lieuNaissance.addValidator(new MaxLengthValidator(18));

		lieuDeces.addValidator(new MaxLengthValidator(20));

		heureNaissance.addValidator(new MaxLengthValidator(20));

		heureDeces.addValidator(new MaxLengthValidator(20));

		causeDeces.setSize("300", "200");

		telephone1.addValidator(new MaxLengthValidator(20));

		telephone2.addValidator(new MaxLengthValidator(20));

		nomResponsable1.addValidator(new MaxLengthValidator(20));

		prenomResponsable1.addValidator(new MaxLengthValidator(20));

		courriel.addValidator(new MaxLengthValidator(20));

		courrielResponsable1.addValidator(new MaxLengthValidator(20));

		telephone1Responsable1.addValidator(new MaxLengthValidator(20));

		telephone2Responsable1.addValidator(new MaxLengthValidator(20));

		nomPere.addValidator(new MaxLengthValidator(20));

		nomMere.addValidator(new MaxLengthValidator(20));

		etatCivil.addValidator(new MaxLengthValidator(20));

		nomEpoux.addValidator(new MaxLengthValidator(20));

		prenomEpoux.addValidator(new MaxLengthValidator(20));

		telephoneEpoux.addValidator(new MaxLengthValidator(20));

		courrielEpoux.addValidator(new MaxLengthValidator(20));

		prenomPere.addValidator(new MaxLengthValidator(20));

		prenomMere.addValidator(new MaxLengthValidator(20));

		nationalite.addValidator(new MaxLengthValidator(20));

		paysResidence.addValidator(new MaxLengthValidator(20));

		adresse.addValidator(new MaxLengthValidator(20));

		typeIdentication.addValidator(new MaxLengthValidator(20));

		noIdentification.addValidator(new MaxLengthValidator(20));

		paysIdentifcation.addValidator(new MaxLengthValidator(20));

		lienParenteResponsable1.addValidator(new MaxLengthValidator(20));

		telephonePere.addValidator(new MaxLengthValidator(20));

		telephoneMere.addValidator(new MaxLengthValidator(20));

		nomResponsable2.addValidator(new MaxLengthValidator(20));

		prenomResponsable2.addValidator(new MaxLengthValidator(20));

		courrielResponsable2.addValidator(new MaxLengthValidator(18));

		telephone1Responsable2.addValidator(new MaxLengthValidator(20));

		telephone2Responsable2.addValidator(new MaxLengthValidator(20));

		adresseResponsable1.setSize("190", "100");

		adresseResponsable2.setSize("190", "128");

		taille.addValidator(new MaxLengthValidator(20));

		poids.addValidator(new MaxLengthValidator(20));

		zone.addValidator(new MaxLengthValidator(20));

		commune.addValidator(new MaxLengthValidator(20));

		departement.addValidator(new MaxLengthValidator(20));

		// religion.addValidator(new MaxLengthValidator(60));
		assurancePresenter = eventBus.addHandler(AssurancePresenter.class);
		assuranceView = assurancePresenter.getView();
		assuranceView.setViewCallback(callback);
		assuranceView.setPresenter(assurancePresenter);
		assurancePresenter.setHeaderTitle(messages.assurance());
		Widget widgetAssurance = assuranceView.getWidget();
		widgetAssurance.setWidth("500");
		widgetAssurance.setHeight("400");
		assuranceContainer.add(widgetAssurance);
		this.finalizeForm(callback, ENTITY_NAME);
		this.driver = GWT.create(PatientDriver.class);
		this.edit();
		return widget;
	}

	@UiHandler({ "btnInfoPers", "btnAdresses", "btnInfoParent",
			"btnInfoResponsable", "btnInfoSocioEcono", "btnAssurMedic",
			"btnAutresInfo", "btnDossier", "btnInfoDeces" })
	public void buttonClicked(SelectEvent event) {
		ToggleButton button = (ToggleButton) event.getSource();

		int index = buttonBox.getWidgetIndex(button);
		layout.setActiveWidget(layout.getWidget(index + 1));
	}

	/*
	 * Cette methode permet de reinitialiser le driver model en vue de presenter
	 * le model à l'utilisateur.
	 */
	@Override
	public void updateView(Patient model) {
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
	public Patient updateModel(Patient model) {
		try {
			if (model == null) {
				model = new Patient();
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
	public void onDataChanged(Patient model, boolean reloadDetails,
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
	public void updateView(List<Patient> models) {
		// TODO Auto-generated method stub
	}

	interface PatientDriver extends
			SimpleBeanEditorDriver<Patient, PatientView> {
	}

	/*
	 * Cette methode renvoie le model editor créé.
	 */
	@Override
	protected SimpleBeanEditorDriver<Patient, ? super ViewFormBase<Patient>> createDriver() {
		return GWT.create(PatientDriver.class);
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

}