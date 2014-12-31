package com.rsoft.medicasoft.client.view;

/*
 Robelkend Templates Generator
 */
/*@Author=Jean Louidort*/
/*@Generation Date=Sun Dec 01 17:08:17 EST 2013*/
/*@Version=1.0*/
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.client.UserRequestCallbackAdapter;
import com.rsoft.medicasoft.client.presenter.AssurancePresenter;
import com.rsoft.medicasoft.client.presenter.PersistingCallback;
import com.rsoft.medicasoft.client.view.viewinterface.IPresenter;
import com.rsoft.medicasoft.client.view.viewinterface.IWidget;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.StaticField;
import com.rsoft.medicasoft.shared.i18n.I18NMessages;
import com.rsoft.medicasoft.shared.model.Assurance;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.form.DateTimePropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.DoublePropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.IntegerPropertyEditor;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;

public class AssuranceView extends AssuranceMasterWrapperView {
	public interface Renderer extends ToolTipConfig.ToolTipRenderer<Object>,
			XTemplates {
		@Override
		@XTemplate(source = "help/assuranceTemplate.html")
		public SafeHtml renderToolTip(Object data);
	}

	private Renderer renderer = GWT.create(Renderer.class);
	private static GxtUiBinder gxtUiBinder = GWT.create(GxtUiBinder.class);

	private AssurancePresenter presenter;

	interface GxtUiBinder extends UiBinder<Widget, AssuranceView> {
	}

	@Override
	protected void execute(ActionCommand command,
			UserRequestCallbackAdapter<Assurance> urc) {
		presenter.getEventBus().executeAssurance(command, urc);
	}

	@Override
	public void setPresenter(IPresenter p) {
		this.presenter = (AssurancePresenter) p;
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

	
	@UiField(provided = true)
	NumberPropertyEditor<Integer> integerPropertyEditor = new IntegerPropertyEditor();
	@UiField(provided = true)
	NumberFormat inumberFormat = NumberFormat
			.getFormat(StaticField.INTEGER_FORMAT);
	
	@UiField(provided = true)
	DateTimePropertyEditor datePropertyEditor = new DateTimePropertyEditor(
			DateTimeFormat.getFormat(StaticField.DATE_FORMAT));
	
	@UiField(provided = true)
	NumberPropertyEditor<Double> doublePropertyEditor = new DoublePropertyEditor();
	@UiField(provided = true)
	NumberFormat dnumberFormat = NumberFormat
			.getFormat(StaticField.DECIMAL_FORMAT);
//	@UiField
//	TextField typeAssurance;
//
//	@UiField
//	TextField description;
//
//	@UiField
//	DateField dateEffective;
//
//	@UiField
//	TextField statut;
//
//	@UiField
//	DateField dateStatut;
//
//	@UiField
//	NumberField<Double> montant;

//	
//	@UiField(provided = true)
//	com.rsoft.medicasoft.client.lovs.LovEmploi.ILovEmploi iLovEmploi10 = new com.rsoft.medicasoft.client.lovs.LovEmploi.ILovEmploi() {
//		@Override
//		public void loadEmploi(
//				com.rsoft.medicasoft.shared.model.Emploi model,
//				PagingLoadConfig loadConfig,
//				AsyncCallback<PagingLoadResult<com.rsoft.medicasoft.shared.model.Emploi>> callback) {
//			UserRequestCallbackAdapter<Emploi> urc = new UserRequestCallbackAdapter<Emploi>();
//			urc.setRequestDescriptor(new RequestDescriptor("Emploi"));
//			urc.setAsyncCallback(callback);
//			urc.setLoadConfig(loadConfig);
//			urc.setModel(model);
//			urc.setLov(true);
//			presenter.getEventBus().executeAssurance(
//					ActionCommand.COLUMNAR_SEARCH, urc);
//		}
//	};
//
//	@UiField(provided = true)
//	com.rsoft.medicasoft.client.lovs.LovEmploi emploi;
//
	public Widget asWidget() {
		ENTITY_NAME = "Assurance";

//		emploi = new com.rsoft.medicasoft.client.lovs.LovEmploi(iLovEmploi10);
		widget = gxtUiBinder.createAndBindUi(this);
		buildForm();
		setEventBus(presenter.getEventBus());

//		typeAssurance.addValidator(new MaxLengthValidator(20));
//
//		description.addValidator(new MaxLengthValidator(20));
//
//		statut.addValidator(new MaxLengthValidator(20));
//
//		emploi.setEmptyText(messages.listOfValues(messages.emploiId()));
//		emploi.setStyleName("lovField", false);
		this.finalizeForm(callback, ENTITY_NAME);
		northData.setSize(0);
		toolBarArea.setVisible(false);
		toolsBar.setVisible(false);
		this.edit();
		return widget;
	}

	/*
	 * Cette methode permet de reinitialiser le driver model en vue de presenter
	 * le model à l'utilisateur.
	 */
	@Override
	public void updateView(Assurance model) {
		try {
			if (model == null || model.getEntityId() == null) {
				entityId = null;
				this.resetDetails();
				ViewUtils.unNotify(htmlMessage);
				showInfoBanner(false);
			} else {
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
	public Assurance updateModel(Assurance model) {
		try {
			if (model == null) {
				model = new Assurance();
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
	public void onDataChanged(Assurance model, boolean reloadDetails,
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
	public void updateView(List<Assurance> models) {
		// TODO Auto-generated method stub
	}

	/*
	 * Cette methode renvoie le model editor créé.
	 */
	/*
	 * Cette methode permet d'initialiser le driver editor à partir du model se
	 * trouvant dans le presenter. pendant toute la duree de vie de la forme, le
	 * contenu de l'editeur sera le meme que le modele du presenteur.
	 */
	protected void edit() {
	}

}