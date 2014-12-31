package com.rsoft.medicasoft.client.view;

/*
 Robelkend Templates Generator
 */
/*@Author=Jean Louidort*/
/*@Generation Date=Mon Oct 14 22:30:48 EDT 2013*/
/*@Version=1.0*/
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.client.UserRequestCallbackAdapter;
import com.rsoft.medicasoft.client.presenter.PaysPresenter;
import com.rsoft.medicasoft.client.presenter.PersistingCallback;
import com.rsoft.medicasoft.client.view.viewinterface.IPresenter;
import com.rsoft.medicasoft.client.view.viewinterface.IWidget;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.StaticField;
import com.rsoft.medicasoft.shared.i18n.I18NMessages;
import com.rsoft.medicasoft.shared.model.Pays;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.resources.ThemeStyles;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.IntegerPropertyEditor;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.MaxLengthValidator;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;

public class PaysView extends PaysMasterWrapperView {
	public interface Renderer extends ToolTipConfig.ToolTipRenderer<Object>,
			XTemplates {
		@Override
		@XTemplate(source = "help/paysTemplate.html")
		public SafeHtml renderToolTip(Object data);
	}

	private Renderer renderer = GWT.create(Renderer.class);
	private static GxtUiBinder gxtUiBinder = GWT.create(GxtUiBinder.class);

	private PaysPresenter presenter;

	interface GxtUiBinder extends UiBinder<Widget, PaysView> {
	}

	@Override
	protected void execute(ActionCommand command,
			UserRequestCallbackAdapter<Pays> urc) {
		presenter.getEventBus().executePays(command, urc);
	}

	@Override
	public void setPresenter(IPresenter p) {
		this.presenter = (PaysPresenter) p;
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

	@Ignore
	@UiField
	NumberField<Long> id;
	@UiField
	TextField code;
	@UiField
	TextField nom;

	public Widget asWidget() {
		ENTITY_NAME = "Pays";
		southData = new BorderLayoutData(550);
		widget = gxtUiBinder.createAndBindUi(this);
		code.addValidator(new MaxLengthValidator(3));
		nom.addValidator(new MaxLengthValidator(100));
		id.setEnabled(false);
		buildForm();
		this.setEventBus(presenter.getEventBus());
		this.finalizeForm(callback, ENTITY_NAME);
		this.driver = GWT.create(PaysDriver.class);
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

	/*
	 * Cette methode permet de reinitialiser le driver model en vue de presenter
	 * le model � l'utilisateur.
	 */
	@Override
	public void updateView(Pays model) {
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
			id.setValue(entityId);
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			Window.alert(ex.getMessage() != null ? ex.getMessage() : ex
					.toString());
		}
	}

	@Override
	public Pays updateModel(Pays model) {
		try {
			if (model == null) {
				model = new Pays();
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
	 * Cette methode est appel�e apres une recherche, un click sur les boutons
	 * de navigation(suivant,dernier,premier,precedent).
	 */
	@Override
	public void onDataChanged(Pays model, boolean reloadDetails,
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

	private HorizontalLayoutContainer detailsContainer = new HorizontalLayoutContainer();

	@Override
	protected void addDetails(Widget widget, String title) {
		if (detailsContainer == null) {
			detailsContainer = new HorizontalLayoutContainer();
		}
		widget.setWidth("33.33%");

		widget.getElement().getStyle().setProperty("whiteSpace", "nowrap");
		widget.addStyleName(ThemeStyles.getStyle().border());
		widget.addStyleName("pad-text white-bg");

		detailsContainer.add(widget, new HorizontalLayoutData(.334d, 1d,
				new Margins(0, 1, 0, 1)));
		dataDetail.setSize(350);
		dataDetail.setMinSize(50);
		dataDetail.setMaxSize(350);
		mainContainer.setSouthWidget(detailsContainer, dataDetail);
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
	public void updateView(List<Pays> models) {
		// TODO Auto-generated method stub
	}

	interface PaysDriver extends SimpleBeanEditorDriver<Pays, PaysView> {
	}

	/*
	 * Cette methode renvoie le model editor cr��.
	 */
	@Override
	protected SimpleBeanEditorDriver<Pays, ? super ViewFormBase<Pays>> createDriver() {
		return GWT.create(PaysDriver.class);
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