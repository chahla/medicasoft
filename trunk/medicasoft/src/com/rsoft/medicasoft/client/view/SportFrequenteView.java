package com.rsoft.medicasoft.client.view;
/*
	Robelkend Templates Generator
*/
/*@Author=Jean Louidort*/
/*@Generation Date=Mon Nov 18 01:14:05 EST 2013*/
/*@Version=1.0*/
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.sencha.gxt.widget.core.client.form.DateField;

import java.util.List;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.client.UserRequestCallbackAdapter;
import com.rsoft.medicasoft.client.presenter.PersistingCallback;
import com.rsoft.medicasoft.client.presenter.SportFrequentePresenter;
import com.rsoft.medicasoft.client.view.viewinterface.IPresenter;
import com.rsoft.medicasoft.client.view.viewinterface.IWidget;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.i18n.I18NMessages;
import com.rsoft.medicasoft.shared.model.SportPratique;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.MaxLengthValidator;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;
import com.rsoft.medicasoft.shared.StaticField;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;
import com.google.gwt.i18n.client.NumberFormat;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.DoublePropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.IntegerPropertyEditor;
import com.sencha.gxt.widget.core.client.form.DateTimePropertyEditor;
import com.rsoft.medicasoft.shared.model.RequestDescriptor;


public class SportFrequenteView extends SportFrequenteMasterWrapperView  {
	public interface Renderer extends ToolTipConfig.ToolTipRenderer<Object>,
			XTemplates {
		@Override
		@XTemplate(source = "help/sportFrequenteTemplate.html")
		public SafeHtml renderToolTip(Object data);
	}

	private Renderer renderer = GWT.create(Renderer.class);
	private static GxtUiBinder gxtUiBinder = GWT.create(GxtUiBinder.class);

	private SportFrequentePresenter presenter;

	interface GxtUiBinder extends UiBinder<Widget, SportFrequenteView> {
	}

	@Override
	protected void execute(ActionCommand command,
			UserRequestCallbackAdapter<SportPratique> urc) {
		presenter.getEventBus().executeSportFrequente(command, urc);
	}

	@Override
	public void setPresenter(IPresenter p) {
		this.presenter = (SportFrequentePresenter) p;
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
        NumberFormat inumberFormat = NumberFormat.getFormat(StaticField.INTEGER_FORMAT);
        @Ignore
        @UiField(provided = true)
        DateTimePropertyEditor datePropertyEditor = new DateTimePropertyEditor(DateTimeFormat.getFormat(StaticField.DATE_FORMAT));
        @UiField
        NumberField<Integer> sportFrequenteId;

        @UiField
        TextField nomSport;

        @UiField
        NumberField<Integer> frequence;

        @UiField
        TextField unite;

        @UiField
        NumberField<Integer> patientId;

        @UiField
        DateField dateDebut;

        @UiField
        DateField dateFin;


	public Widget asWidget() {
		ENTITY_NAME = "SportFrequente";
		
		widget = gxtUiBinder.createAndBindUi(this);
		buildForm();
		setEventBus(presenter.getEventBus());
		
        nomSport.addValidator(new MaxLengthValidator(60));

        unite.addValidator(new MaxLengthValidator(20));

		this.finalizeForm(callback, ENTITY_NAME);
		this.driver = GWT.create(SportFrequenteDriver.class);
		this.edit();
		return widget;
	}

	/*
		Cette methode permet de reinitialiser le driver model en vue de presenter le model à l'utilisateur.
	*/
	@Override
	public void updateView(SportPratique model) {
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
	public SportPratique updateModel(SportPratique model) {
		try {
			if (model == null) {
				model = new SportPratique();
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
		Cette methode est appelée apres une recherche, un click sur les boutons de navigation(suivant,dernier,premier,precedent).
	*/
	@Override
	public void onDataChanged(SportPratique model, boolean reloadDetails,
			boolean setBtnsPersistVisible) {
		this.onChangeHeader(model, reloadDetails, setBtnsPersistVisible);
	}

	/*
		Cette methode est appelée apres une reunitialisation reussie.
	*/
	@Override
	public void onFormCleared() {
		bannerInfoIsShowed = false;
		showInfoBanner(bannerInfoIsShowed);
		this.resetDetails();
	}

	/*
		Cette methode est appelée apres une insertion ou une modification reussie.
	*/
	@Override
	public void onPersistenceSuccessed() {
		this.persistDetails(); //Attente de correction
		if (box != null) {
			box.hide();
			box = null;
		}
	}

	/*
		Cette methode permet d'ajouter les details, chaque detail sera ajouté dans un nouveau onglet.
	*/
	@Override
	protected void addDetail(Widget widget) {
		southData.setMargins(new Margins(5, 5, 5, 5));
		southData.setCollapsible(true);
		southData.setSplit(true);
		mainContainer.setSouthWidget(widget, southData);
	}

	/*
		Cette methode permet d'ajouter les details avec un message comme titre,
		chaque detail sera ajouté dans un nouveau onglet.
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
		Cette methode permet d'affecter le callback de la vue,
		le callback permet d'ecouter les changements de la vue.
	*/
	@Override
	public void setViewCallback(ViewCallback callback) {
		this.callback = callback;
	}

	@Override
	public void updateView(List<SportPratique> models) {
		// TODO Auto-generated method stub
	}

	interface SportFrequenteDriver extends
			SimpleBeanEditorDriver<SportPratique, SportFrequenteView> {
	}

	/*
		Cette methode renvoie le model editor créé.
	*/
	@Override
	protected SimpleBeanEditorDriver<SportPratique, ? super ViewFormBase<SportPratique>> createDriver() {
		return GWT.create(SportFrequenteDriver.class);
	}

	/*
		Cette methode permet d'initialiser le driver editor à partir du model se trouvant 
		dans le presenter. pendant toute la duree de vie de la forme, le contenu de l'editeur
		sera le meme que le modele du presenteur.
	*/
	protected void edit() {
		// Initialize the driver with the top-level editor
		driver.initialize(this);
		// Copy the data in the object into the UInext
		driver.edit(presenter.getModel());
	}
	
}