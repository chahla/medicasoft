/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rsoft.medicasoft.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.client.presenter.RootPresenter;
import com.rsoft.medicasoft.client.presenter.RootPresenter.IRootTemplateView;
import com.rsoft.medicasoft.client.toolsbar.MainBarPane;
import com.rsoft.medicasoft.client.view.viewinterface.IWidget;
import com.rsoft.medicasoft.shared.menu.Menu;
import com.sencha.gxt.state.client.BorderLayoutStateHandler;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer.ExpandMode;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.event.BeforeCloseEvent;
import com.sencha.gxt.widget.core.client.event.BeforeCloseEvent.BeforeCloseHandler;

public class MainView implements IsWidget, IRootTemplateView {

	private Integer delai = 180000;

	private Timer delaiInactiviteTimer;

	private HandlerRegistration handlerRegistration;

	private Timer timer = null;

	private final ViewCallback viewCallback = new ViewCallback() {
		@Override
		public void viewChanged(String title) {
		}

		@Override
		public void viewReset(String title) {
		}
	};

	public class MenuActionCallBack {

		public void showPage(Menu menu) {
			// Fonction Modifier
			// Window.alert("");
			// AutoProgressMessageBox box = new
			// AutoProgressMessageBox(messages.pleaseWait(),
			// messages.pleaseWait());
			// box.setProgressText(messages.pleaseWait());
			// box.auto();
			// box.show();
			// box = null;
			presenter.menuClicked(menu);
		}
	}

	interface MyUiBinder extends UiBinder<Component, MainView> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	@UiField
	ContentPanel souththPane;
	@UiField
	ContentPanel middlePane;
	@UiField(provided = true)
	BorderLayoutContainer con = new BorderLayoutContainer() {
		{
			monitorWindowResize = true;
		}

		@Override
		protected void onWindowResize(int width, int height) {
			setPixelSize(width, height);
		}
	};
	@UiField
	ContentPanel westPane;
	@UiField
	ContentPanel northPane;
	private TabPanel menuTabPanel;
	private TabPanel tabPanel;
	private int index = -1;
	com.rsoft.medicasoft.shared.i18n.II18NMessages messages = com.rsoft.medicasoft.shared.i18n.I18NMessages
			.getMessages();

	@Override
	public void setPresenter(RootPresenter p) {
		this.presenter = p;
	}

	private void addTab(Widget widget, String title) {
		if (widget != null) {
			TabItemConfig c;
			index++;
			if (index == 0) {

				tabPanel.add(widget, new TabItemConfig(title, false));

			} else {
				tabPanel.add(widget, new TabItemConfig(title, true));
				tabPanel.setActiveWidget(widget);
				BeforeCloseHandler handler = new BeforeCloseHandler() {
					@Override
					public void onBeforeClose(BeforeCloseEvent event) {
					}
				};
				tabPanel.addBeforeCloseHandler(handler);
			}
		}
	}

	private MenuActionCallBack mnActionCallback = new MenuActionCallBack();
	private RootPresenter presenter;
	private AccordionLayoutContainer menuPanel = new AccordionLayoutContainer();

	public RootPresenter getPresenter() {
		return this.presenter;
	}

	public MainView() {
		tabPanel = new TabPanel();
		tabPanel.setBodyBorder(false);
		tabPanel.setAnimScroll(true);
		tabPanel.setTabScroll(true);
		tabPanel.setPixelSize(Window.getClientWidth(), Window.getClientHeight());
		menuTabPanel = new TabPanel();
		menuTabPanel.setLayoutData(new MarginData(0, 0, 0, 0));
		menuTabPanel.setBodyBorder(false);
		menuTabPanel.setAnimScroll(true);
		menuTabPanel.setTabScroll(true);
		menuTabPanel.setCloseContextMenu(true);
	}

	public Widget asWidget() {
		Widget widget = uiBinder.createAndBindUi(this);

		con.setLayoutData(new MarginData(0, 0, 0, 0));
		con.setBorders(false);
		con.setResize(true);
		con.setShadow(false);
		con.setDeferHeight(false);
		con.setStyleName("padding_no");
		con.setStateful(true);
		con.setStateId("mainLayout");
		con.setPixelSize(Window.getClientWidth(), Window.getClientHeight());
		BorderLayoutStateHandler state = new BorderLayoutStateHandler(con);
		//state.loadState();
		menuPanel.setExpandMode(ExpandMode.SINGLE_FILL);

		HTML footer = new HTML("");
		HTML banner = new HTML("kerbson");
		Label label_1 = new Label("");
		banner.setStyleName("keepsen");
		// banner.
		String path = "imageLogo/default_logo.png";
		banner.setHTML("<div class='principal'><div class='logo'>"
				+ new Image(path)
				+ "</div> <div class='nom_institution'><h1>Votre Institution en ligne</h1></div> <div class='nom_login'>"
				+ "");

		footer.setHTML("<p class='footer'>Footer</p>");
		northPane.add(banner);
		middlePane.add(tabPanel);
		middlePane.setStyleName("marge");
		// if (Utilities.userProfile == null) {
		// //presenter.loadUserProfile();
		// }
		initializeData();
		return widget;
	}

	@Override
	public void addMainPanel(IWidget widget) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addBottomPanel(IWidget widget) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addMiddlePanel(final IWidget widget, final String title) {
		try {
			widget.setViewCallback(viewCallback);
			addTab(widget.getWidget(), title);
		} catch (Exception e) {
		} finally {
		}
		presenter.marquerAccess(title);
	}

	private void buildMenu() {
		for (Menu mn : presenter.getUserMenus()) {
			MenuBloc menuBloc = new MenuBloc(mnActionCallback, mn);
			MainBarPane mnBar = new MainBarPane(mnActionCallback, mn);
			try {
				menuPanel.add(menuBloc);
				if (menuTabPanel != null && mn != null && mnBar != null) {
					menuTabPanel.add(mnBar, new TabItemConfig(mn.getMenuName(),
							false));
				}
			} catch (Exception ex) {
				ex.printStackTrace(System.out);
			}
		}
		westPane.add(menuPanel);
		/*
		 * if (delai != null) { delaiInactiviteTimer = new Timer() {
		 * 
		 * @Override public void run() { handlerRegistration.removeHandler();
		 * delaiInactiviteTimer.cancel(); logout(); } };
		 * 
		 * delaiInactiviteTimer.schedule(delai);
		 * 
		 * NativePreviewHandler handler = new NativePreviewHandler() {
		 * 
		 * @Override public void onPreviewNativeEvent(NativePreviewEvent event)
		 * { delaiInactiviteTimer.schedule(delai); } }; handlerRegistration =
		 * Event.addNativePreviewHandler(handler); }
		 */
	}

	@Override
	public void logout() {
		presenter.getEventBus().logout();
	}

	@Override
	public void initializeData() {
		presenter.getEventBus().initializeData();
	}

	@Override
	public void drawMenu() {
		/*
		 * if (!Window.Location.getQueryString().contains("locale=") &&
		 * SingleUserInfo.getUserInfo() != null &&
		 * SingleUserInfo.getUserInfo().getUserProfile() != null &&
		 * !SingleUserInfo.getUserInfo().getUserProfile().getLanguage()
		 * .trim().isEmpty()) { if (GWT.isProdMode()) {
		 * Window.Location.replace(GWT.getHostPageBaseURL() + "?locale=" +
		 * SingleUserInfo.getUserInfo().getUserProfile()
		 * .getLanguage().toLowerCase()); } else {
		 * Window.Location.replace(GWT.getHostPageBaseURL() +
		 * "medicasoft.html?gwt.codesvr=127.0.0.1:9997&locale=" +
		 * SingleUserInfo.getUserInfo().getUserProfile()
		 * .getLanguage().toLowerCase()); } }
		 */
		buildMenu();
	}
}
