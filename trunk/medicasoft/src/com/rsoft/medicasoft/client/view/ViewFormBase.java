package com.rsoft.medicasoft.client.view;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.client.RSOFTEventBus;
import com.rsoft.medicasoft.client.UserRequestCallbackAdapter;
import com.rsoft.medicasoft.client.toolsbar.FormToolsBar;
import com.rsoft.medicasoft.client.toolsbar.IToolsBarAction;
import com.rsoft.medicasoft.client.view.viewinterface.IView;
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
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;

public abstract class ViewFormBase<T extends ModelBase> implements IView<T>,
		Editor<T> {
	private boolean detail;
	protected String ENTITY_NAME;
	protected FormToolsBar toolsBar;
	protected AutoProgressMessageBox box;
	protected ViewCallback callback;
	protected Long entityId;
	protected TabPanel folder; 
	
	protected String paramInoutSuffix;
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

	protected SimpleBeanEditorDriver<T, ? super ViewFormBase<T>> driver;

	protected abstract SimpleBeanEditorDriver<T, ? super ViewFormBase<T>> createDriver();

	protected void setEventBus(RSOFTEventBus eventBus) {
		this.eventBus = eventBus;
	}

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
	protected ContentPanel toolBarArea;

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
		finalizeModel(driver.flush());
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
				//hideProgressBar();
			}

			@Override
			public void onSingleModelOperationFailed(T model, Throwable cause) {
				//hideProgressBar();
			}
		};
		urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
		execute(command, urc);
	}

	@Override
	public void first() {
		if (stopCurrentAction()) {
			hideProgressBar();
			return;
		}
		box = new AutoProgressMessageBox(messages.pleaseWait(),
				messages.goingFirst());
		box.setProgressText(messages.goingFirst());
		box.auto();
		box.show();
		UserRequestCallbackAdapter<T> urc = new UserRequestCallbackAdapter<T>() {
			@Override
			public void onSingleOperationSuccessed(T model) {
				//hideProgressBar();
			}

			@Override
			public void onSingleModelOperationFailed(T model, Throwable cause) {
				//hideProgressBar();
			}
		};
		urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
		execute(ActionCommand.FIRST, urc);
	}

	@Override
	public void last() {
		if (stopCurrentAction()) {
			box.hide();
			box = null;
			return;
		}
		box = new AutoProgressMessageBox(messages.pleaseWait(),
				messages.goingLast());
		box.setProgressText(messages.goingLast());
		box.auto();
		box.show();
		UserRequestCallbackAdapter<T> urc = new UserRequestCallbackAdapter<T>() {
			@Override
			public void onSingleOperationSuccessed(T model) {
				//hideProgressBar();
			}

			@Override
			public void onSingleModelOperationFailed(T model, Throwable cause) {
				//hideProgressBar();
			}
		};
		urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
		execute(ActionCommand.LAST, urc);
	}

	@Override
	public void next() {
		if (stopCurrentAction()) {
			box.hide();
			box = null;
			return;
		}
		box = new AutoProgressMessageBox(messages.pleaseWait(),
				messages.goingNext());
		box.setProgressText(messages.goingNext());
		box.auto();
		box.show();
		UserRequestCallbackAdapter<T> urc = new UserRequestCallbackAdapter<T>() {
			@Override
			public void onSingleOperationSuccessed(T model) {
				//hideProgressBar();
			}

			@Override
			public void onSingleModelOperationFailed(T model, Throwable cause) {
				//hideProgressBar();
			}
		};
		urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
		execute(ActionCommand.NEXT, urc);
	}

	@Override
	public void previous() {
		if (stopCurrentAction()) {
			box.hide();
			box = null;
			return;
		}
		box = new AutoProgressMessageBox(messages.pleaseWait(),
				messages.goingPrevious());
		box.setProgressText(messages.goingPrevious());
		box.auto();
		box.show();
		UserRequestCallbackAdapter<T> urc = new UserRequestCallbackAdapter<T>() {
			@Override
			public void onSingleOperationSuccessed(T model) {
				//hideProgressBar();
			}

			@Override
			public void onSingleModelOperationFailed(T model, Throwable cause) {
				//hideProgressBar();
			}
		};
		urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
		execute(ActionCommand.PREVIOUS, urc);
	}

	@Override
	public void print() {
		box = new AutoProgressMessageBox(messages.pleaseWait(),
				messages.printingProgress());
		box.setProgressText(messages.printingProgress());
		box.auto();
		box.show();
		UserRequestCallbackAdapter<T> urc = new UserRequestCallbackAdapter<T>() {
			@Override
			public void onSingleOperationSuccessed(T model) {
				//hideProgressBar();
			}

			@Override
			public void onSingleModelOperationFailed(T model, Throwable cause) {
				//hideProgressBar();
			}
		};
		urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
		execute(ActionCommand.PRINT, urc);
	}

	@Override
	public void load() {
		box = new AutoProgressMessageBox(messages.pleaseWait(),
				messages.loadingProgress());
		box.setProgressText(messages.loadingProgress());
		box.auto();
		box.show();
		UserRequestCallbackAdapter<T> urc = new UserRequestCallbackAdapter<T>() {
			@Override
			public void onSingleOperationSuccessed(T model) {
				//hideProgressBar();
			}

			@Override
			public void onSingleModelOperationFailed(T model, Throwable cause) {
				//hideProgressBar();
			}
		};
		urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
		execute(ActionCommand.LOAD, urc);
	}

	@Override
	public void extract() {
		box = new AutoProgressMessageBox(messages.pleaseWait(),
				messages.extractingProgress());
		box.setProgressText(messages.extractingProgress());
		box.auto();
		box.show();
		UserRequestCallbackAdapter<T> urc = new UserRequestCallbackAdapter<T>() {
			@Override
			public void onSingleOperationSuccessed(T model) {
				//hideProgressBar();
			}

			@Override
			public void onSingleModelOperationFailed(T model, Throwable cause) {
				//hideProgressBar();
			}
		};
		urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
		execute(ActionCommand.EXTRACT, urc);
	}

	@Override
	public void reset() {
		if (stopCurrentAction()) {
			box.hide();
			box = null;
			return;
		}
		UserRequestCallbackAdapter<T> urc = new UserRequestCallbackAdapter<T>() {
			@Override
			public void onSingleOperationSuccessed(T model) {
				//hideProgressBar();
			}

			@Override
			public void onSingleModelOperationFailed(T model, Throwable cause) {
				//hideProgressBar();
			}
		};
		urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
		execute(ActionCommand.RESET, urc);
	}

	@Override
	public void criteria() {
		if (stopCurrentAction()) {
			box.hide();
			box = null;
			return;
		}

		setSearchPending(false);
		UserRequestCallbackAdapter<T> urc = new UserRequestCallbackAdapter<T>() {
			@Override
			public void onSingleOperationSuccessed(T model) {
				//hideProgressBar();
			}

			@Override
			public void onSingleModelOperationFailed(T model, Throwable cause) {
				//hideProgressBar();
			}
		};
		urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
		execute(ActionCommand.CRITERIA, urc);
	}

	@Override
	public void search() {
		if (stopCurrentAction()) {
			box.hide();
			box = null;
			return;
		}
		box = new AutoProgressMessageBox(messages.pleaseWait(),
				messages.searchingProgress());
		box.setProgressText(messages.searchingProgress());
		box.auto();
		box.show();
		setSearchPending(false);
		UserRequestCallbackAdapter<T> urc = new UserRequestCallbackAdapter<T>() {
			@Override
			public void onSingleOperationSuccessed(T model) {
				//hideProgressBar();
			}

			@Override
			public void onSingleModelOperationFailed(T model, Throwable cause) {
				//hideProgressBar();
			}
		};
		urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
		execute(ActionCommand.SEARCH, urc);
	}

	@Override
	public void remove() {
		finalizeModel(driver.flush());
		if (Window.confirm(I18NMessages.getMessages().removeConfirmation())) {
			box = new AutoProgressMessageBox(messages.pleaseWait(),
					messages.removingProgress());
			box.setProgressText(messages.removingProgress());
			box.auto();
			box.show();
			UserRequestCallbackAdapter<T> urc = new UserRequestCallbackAdapter<T>() {
				@Override
				public void onSingleOperationSuccessed(T model) {
					//hideProgressBar();
				}

				@Override
				public void onSingleModelOperationFailed(T model, Throwable cause) {
					//hideProgressBar();
				}
			};
			urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
			execute(ActionCommand.REMOVE, urc);
		}
	}

	public void showInfoBanner(boolean show) {
		if (!show) {
			mainContainer.hide(LayoutRegion.NORTH);
			menuContainer.hide(LayoutRegion.NORTH);
			northData.setSize(36);
			bannerInfoIsShowed = false;
			mainContainer.show(LayoutRegion.NORTH);
		} else {
			mainContainer.hide(LayoutRegion.NORTH);
			menuContainer.show(LayoutRegion.NORTH);
			northData.setSize(63);
			bannerInfoIsShowed = true;
			mainContainer.show(LayoutRegion.NORTH);
		}
	}

	@Override
	public void setBtnSaveVisible(boolean aFlag) {
		toolsBar.setSaveEnabled(aFlag);
		toolsBar.setRemoveEnabled(aFlag);
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
	public void setDetail(boolean detail) {
		this.detail = detail;
	}

	@Override
	public boolean isDetail() {
		return detail;
	}
	/*Si vous avez des modifications vous avez a faire dans le model courrant,
	 * redefinissez cette methode et faites y les modifications
	 * */
	public void finalizeModel(T currentModel) {
		
		
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
		htmlMessage.setSize("1000", "100");
		toolsBar = new FormToolsBar();
		toolsBar.setParamInoutSuffix(ENTITY_NAME);
		toolsBar.setPageSize(50);
		toolsBar.initialize(toolsBarAction);
		toolBarArea.setHeaderVisible(false);
		toolBarArea.add(toolsBar);
		ToolTipConfig config = new ToolTipConfig();
		config = new ToolTipConfig();
		config.setBodyHtml("Prints the current document");
		config.setTitleHtml("Template Tip");
		config.setMouseOffset(new int[] { 0, 0 });
		config.setAnchor(Side.LEFT);
		config.setCloseable(true);
		config.setMaxWidth(415);
		setRenderer(config);
		toolsBar.setToolTipConfig(config);
		toolBarArea.setHeaderVisible(false);
		toolBarArea.add(toolsBar);
	}
}
