package com.rsoft.medicasoft.client.view;
/*
	Robelkend Templates Generator
*/
/*@Author=Jean Louidort*/
/*@Generation Date=Sat Nov 02 22:30:05 EDT 2013*/
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
import com.rsoft.medicasoft.client.presenter.ZonePresenter;
import com.rsoft.medicasoft.client.view.viewinterface.IPresenter;
import com.rsoft.medicasoft.client.view.viewinterface.IWidget;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.i18n.I18NMessages;
import com.rsoft.medicasoft.shared.model.Zone;
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

import com.rsoft.medicasoft.shared.model.Commune;

public class ZoneView extends ZoneMasterWrapperView  {
	public interface Renderer extends ToolTipConfig.ToolTipRenderer<Object>,
			XTemplates {
		@Override
		@XTemplate(source = "help/zoneTemplate.html")
		public SafeHtml renderToolTip(Object data);
	}

	private Renderer renderer = GWT.create(Renderer.class);
	private static GxtUiBinder gxtUiBinder = GWT.create(GxtUiBinder.class);

	private ZonePresenter presenter;

	interface GxtUiBinder extends UiBinder<Widget, ZoneView> {
	}

	@Override
	protected void execute(ActionCommand command,
			UserRequestCallbackAdapter<Zone> urc) {
		presenter.getEventBus().executeZone(command, urc);
	}

	@Override
	public void setPresenter(IPresenter p) {
		this.presenter = (ZonePresenter) p;
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
        TextField zoneId;

    @Ignore
    @UiField(provided = true)
    com.rsoft.medicasoft.client.lovs.LovCommune.ILovCommune iLovCommune2 = new com.rsoft.medicasoft.client.lovs.LovCommune.ILovCommune() {
    @Override
    public void loadCommune(com.rsoft.medicasoft.shared.model.Commune model, PagingLoadConfig loadConfig,
      AsyncCallback<PagingLoadResult<com.rsoft.medicasoft.shared.model.Commune>> callback) {
        UserRequestCallbackAdapter<Commune> urc = new UserRequestCallbackAdapter<Commune>();
        urc.setRequestDescriptor(new RequestDescriptor("Commune"));
        urc.setAsyncCallback(callback);
        urc.setLoadConfig(loadConfig);
        urc.setModel(model);
        presenter.getEventBus().executeZone(ActionCommand.COLUMNAR_SEARCH, urc);
      }
    };
    
    @UiField(provided = true)
    com.rsoft.medicasoft.client.lovs.LovCommune commune;
        
@UiField
        TextArea nom;


	public Widget asWidget() {
		ENTITY_NAME = "Zone";
		
    commune = new com.rsoft.medicasoft.client.lovs.LovCommune(iLovCommune2);
		widget = gxtUiBinder.createAndBindUi(this);
		buildForm();
		setEventBus(presenter.getEventBus());
		
        zoneId.addValidator(new MaxLengthValidator(20));

        commune.setEmptyText(messages.listOfValues(messages.communeId()));
        commune.setStyleName("lovField", false);
		this.finalizeForm(callback, ENTITY_NAME);
		this.driver = GWT.create(ZoneDriver.class);
		this.edit();
		return widget;
	}

	/*
		Cette methode permet de reinitialiser le driver model en vue de presenter le model � l'utilisateur.
	*/
	@Override
	public void updateView(Zone model) {
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
	public Zone updateModel(Zone model) {
		try {
			if (model == null) {
				model = new Zone();
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
		Cette methode est appel�e apres une recherche, un click sur les boutons de navigation(suivant,dernier,premier,precedent).
	*/
	@Override
	public void onDataChanged(Zone model, boolean reloadDetails,
			boolean setBtnsPersistVisible) {
		this.onChangeHeader(model, reloadDetails, setBtnsPersistVisible);
	}

	/*
		Cette methode est appel�e apres une reunitialisation reussie.
	*/
	@Override
	public void onFormCleared() {
		bannerInfoIsShowed = false;
		showInfoBanner(bannerInfoIsShowed);
		this.resetDetails();
	}

	/*
		Cette methode est appel�e apres une insertion ou une modification reussie.
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
		Cette methode permet d'ajouter les details, chaque detail sera ajout� dans un nouveau onglet.
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
		chaque detail sera ajout� dans un nouveau onglet.
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
	public void updateView(List<Zone> models) {
		// TODO Auto-generated method stub
	}

	interface ZoneDriver extends
			SimpleBeanEditorDriver<Zone, ZoneView> {
	}

	/*
		Cette methode renvoie le model editor cr��.
	*/
	@Override
	protected SimpleBeanEditorDriver<Zone, ? super ViewFormBase<Zone>> createDriver() {
		return GWT.create(ZoneDriver.class);
	}

	/*
		Cette methode permet d'initialiser le driver editor � partir du model se trouvant 
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