package com.rsoft.medicasoft.client.view.references;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.client.RSOFTEventBus;
import com.rsoft.medicasoft.client.UserRequestCallbackAdapter;
import com.rsoft.medicasoft.client.toolsbar.IToolsBarAction;
import com.rsoft.medicasoft.client.view.ViewCallback;
import com.rsoft.medicasoft.client.view.viewinterface.IQuickView;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.i18n.I18NMessages;
import com.rsoft.medicasoft.shared.model.RequestDescriptor;
import com.sencha.gxt.core.client.Style.LayoutRegion;
import com.sencha.gxt.core.client.Style.Side;
import com.sencha.gxt.core.client.resources.ThemeStyles;
import com.sencha.gxt.core.client.resources.ThemeStyles.Styles;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.box.AutoProgressMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;

public abstract class QuickViewFormBase<T extends ModelBase> implements
		IQuickView<T>, Editor<T> {
	protected String ENTITY_NAME;
	protected AutoProgressMessageBox box;
	protected ViewCallback callback;
	protected QuickViewCallback<T> quickViewCallback;
	protected Long entityId;
	protected TabPanel folder;

	@Ignore
	@UiField(provided = true)
	protected com.rsoft.medicasoft.shared.i18n.II18NMessages messages = I18NMessages
			.getMessages();
	@Ignore
	@UiField(provided = true)
	protected IToolsBarAction toolsBarAction = this;
	@UiField
	protected BorderLayoutContainer mainContainer;
	protected RSOFTEventBus eventBus;

	protected SimpleBeanEditorDriver<T, ? super QuickViewFormBase<T>> driver;

	protected abstract SimpleBeanEditorDriver<T, ? super QuickViewFormBase<T>> createDriver();

	protected void setEventBus(RSOFTEventBus eventBus) {
		this.eventBus = eventBus;
	}

	@Ignore
	TextButton save;
	@Ignore
	@UiField
	protected HTML htmlMessage;
	@Ignore
	@UiField(provided = true)
	protected MarginData outerData = new MarginData(0);
	@Ignore
	@UiField(provided = true)
	protected BorderLayoutData northData = new BorderLayoutData(63);
	@Ignore
	@UiField(provided = true)
	protected BorderLayoutData westData = new BorderLayoutData(350);
	@Ignore
	@UiField(provided = true)
	protected MarginData centerData = new MarginData(0);
	@Ignore
	@UiField(provided = true)
	protected BorderLayoutData eastData = new BorderLayoutData(350);
	@Ignore
	@UiField(provided = true)
	protected BorderLayoutData southData = new BorderLayoutData(480);
	@Ignore
	@UiField
	protected BorderLayoutContainer menuContainer;
	@Ignore
	@UiField(provided = true)
	protected Styles themeStyles = ThemeStyles.getStyle();

	protected boolean bannerInfoIsShowed = false;
	protected boolean userHideBannerInfo = true;
	protected Widget widget;

	@Override
	public void ignore() {
		// TODO Auto-generated method stub
	}

	@Ignore
	@UiField
	protected ContentPanel panel;

	@Ignore
	protected FormPanel mainForm = new FormPanel();

	protected abstract void execute(ActionCommand command,
			UserRequestCallbackAdapter<T> urc);

	public abstract boolean stopCurrentAction();

	@Override
	public void persist() {
		T model = driver.flush();
		if (model != null) {
			((ModelBase) model).setUpdatePending(true);
		}
		finalizeModel(model);
		box = new AutoProgressMessageBox(messages.pleaseWait(),
				messages.savingProgress());
		box.setProgressText(messages.savingProgress());
		box.auto();
		box.show();
		ActionCommand command = ActionCommand.INSERT;

		if (entityId != null) {
			command = ActionCommand.MERGE;
		}

		UserRequestCallbackAdapter<T> urc = new UserRequestCallbackAdapter<T>() {
			@Override
			public void onSingleOperationSuccessed(T model) {
				hideProgressBar();
			}

			@Override
			public void onSingleModelOperationFailed(T model, Throwable cause) {
				hideProgressBar();
			}
		};
		urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
		execute(command, urc);
	}

	@Override
	public void first() {
	}

	@Override
	public void last() {
	}

	@Override
	public void next() {
	}

	@Override
	public void previous() {
	}

	@Override
	public void print() {
	}

	@Override
	public void load() {
	}

	@Override
	public void extract() {
	}

	@Override
	public void reset() {
	}

	@Override
	public void criteria() {
	}

	@Override
	public void search() {
	}

	@Override
	public void remove() {
	}

	public void showInfoBanner(boolean show) {
		if (!show) {
			mainContainer.hide(LayoutRegion.NORTH);
			menuContainer.hide(LayoutRegion.NORTH);
			northData.setSize(0);
			bannerInfoIsShowed = false;
			mainContainer.show(LayoutRegion.NORTH);
		} else {
			mainContainer.hide(LayoutRegion.NORTH);
			menuContainer.show(LayoutRegion.NORTH);
			northData.setSize(36);
			bannerInfoIsShowed = true;
			mainContainer.show(LayoutRegion.NORTH);
		}
	}

	@Override
	public void setBtnSaveVisible(boolean aFlag) {
	}

	@Override
	public void showInfoBanner() {
		showInfoBanner(!bannerInfoIsShowed);
		userHideBannerInfo = bannerInfoIsShowed;
	}

	@Override
	public void help() {
	}

	protected void hideProgressBar() {
		if (box != null) {
			box.hide();
			box = null;
		}
	}

	@Override
	public boolean isDetail() {
		return false;
	}

	/*
	 * Si vous avez des modifications vous avez a faire dans le model courrant,
	 * redefinissez cette methode et faites y les modifications
	 */
	public void finalizeModel(T currentModel) {

	}

	public void setQuickViewCallback(QuickViewCallback<T> quickViewCallback) {
		this.quickViewCallback = quickViewCallback;
	}

	public abstract void setRenderer(ToolTipConfig config);

	public abstract void formUpdated();

	public abstract Widget asWidget();

	public abstract void setSearchPending(boolean value);

	public void buildForm() {
		panel.addDomHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				formUpdated();
			}
		}, KeyPressEvent.getType());
		showInfoBanner(bannerInfoIsShowed);
		mainContainer.setResize(true);
		southData.setMargins(new Margins(5, 5, 5, 5));
		southData.setCollapsible(true);
		southData.setCollapseMini(true);
		southData.setCollapsed(true);
		southData.setFloatable(true);
		southData.setSplit(true);

		eastData.setMargins(new Margins(5, 5, 5, 5));
		eastData.setCollapsible(true);
		eastData.setCollapseMini(true);
		eastData.setCollapsed(true);
		eastData.setFloatable(true);
		eastData.setSplit(true);

		northData.setMargins(new Margins(0, 0, 0, 0));
		northData.setCollapsible(true);
		northData.setCollapseMini(true);
		northData.setCollapsed(true);
		northData.setFloatable(true);
		northData.setSplit(true);
		htmlMessage.getElement().getStyle().setBackgroundColor("#F6F983");
		htmlMessage.getElement().getStyle().setBorderColor("#2106C2");
		htmlMessage.getElement().getStyle().setBorderWidth(3, Unit.PX);
		htmlMessage.getElement().getStyle().setColor("#2106C2");
		htmlMessage.getElement().getStyle().setBorderColor("#4F81BD");
		ToolTipConfig config = new ToolTipConfig();
		config = new ToolTipConfig();
		config.setBodyHtml("Prints the current document");
		config.setTitleHtml("Template Tip");
		config.setMouseOffset(new int[] { 0, 0 });
		config.setAnchor(Side.LEFT);
		config.setCloseable(true);
		config.setMaxWidth(415);
		setRenderer(config);

		save = new TextButton(messages.persist());

		save.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				persist();
			}
		});
		panel.addButton(save);
	}
}
