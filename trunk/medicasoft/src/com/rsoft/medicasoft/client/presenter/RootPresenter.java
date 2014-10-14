/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rsoft.medicasoft.client.presenter;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;
import com.rsoft.medicasoft.client.RSOFTEventBus;
import com.rsoft.medicasoft.client.Utilities;
import com.rsoft.medicasoft.client.service.EasyGwtRpcServiceAsync;
import com.rsoft.medicasoft.client.view.MainView;
import com.rsoft.medicasoft.client.view.viewinterface.IView;
import com.rsoft.medicasoft.client.view.viewinterface.IWidget;
import com.rsoft.medicasoft.shared.UserInfo;
import com.rsoft.medicasoft.shared.i18n.I18NMessages;
import com.rsoft.medicasoft.shared.menu.Menu;
import com.rsoft.medicasoft.shared.model.Institution;
import com.rsoft.medicasoft.shared.model.ParamInout;
import com.rsoft.medicasoft.shared.model.Patient;
import com.rsoft.medicasoft.shared.model.Pays;
import com.rsoft.medicasoft.shared.model.Profession;
import com.rsoft.medicasoft.shared.model.Religion;
import com.rsoft.medicasoft.shared.model.SportPratique;
import com.rsoft.medicasoft.shared.model.UserGroup;
import com.rsoft.medicasoft.shared.model.UserGroupDetail;
import com.rsoft.medicasoft.shared.model.UserProfile;
import com.sencha.gxt.widget.core.client.box.AutoProgressMessageBox;
import com.sencha.gxt.widget.core.client.info.Info;

@Presenter(view = MainView.class)
public class RootPresenter extends
		BasePresenter<RootPresenter.IRootTemplateView, RSOFTEventBus> {
	@Inject
	protected EasyGwtRpcServiceAsync service;

	@SuppressWarnings("unused")
	private String language = "en";
	private List<Menu> userMenus;

	public interface IRootTemplateView {
		void addMainPanel(IWidget widget);

		void addBottomPanel(IWidget widget);

		void addMiddlePanel(IWidget widget, String title);

		void setPresenter(RootPresenter p);

		void logout();

		void initializeData();

		void drawMenu();
	}

	/**
	 * @return the userMenus
	 */
	public List<Menu> getUserMenus() {
		if (userMenus == null) {
			userMenus = new ArrayList<Menu>();
		}
		return userMenus;
	}

	/**
	 * @param userMenus
	 *            the userMenus to set
	 */
	public void setUserMenus(List<Menu> userMenus) {
		this.userMenus = userMenus;
	}

	public void onChangeMiddlePanel(IWidget widgetq, Menu menu) {
		if (menu != null) {
			// List<String> actionsDenied = menu.getActionsDenied();

			// <!-Marked line, do not remove or modify this line->

			if (menu != null && "Profession".equalsIgnoreCase(menu.getMenuId())) {
				ProfessionPresenter presenter = eventBus
						.addHandler(ProfessionPresenter.class);
				IView<Profession> v = presenter.getView();
				v.setPresenter(presenter);
				view.addMiddlePanel(v, menu.getMenuName());
			}

			if (menu != null && "Religion".equalsIgnoreCase(menu.getMenuId())) {
				ReligionPresenter presenter = eventBus
						.addHandler(ReligionPresenter.class);
				IView<Religion> v = presenter.getView();
				v.setPresenter(presenter);
				view.addMiddlePanel(v, menu.getMenuName());
			}

			if (menu != null
					&& "SportFrequente".equalsIgnoreCase(menu.getMenuId())) {
				SportFrequentePresenter presenter = eventBus
						.addHandler(SportFrequentePresenter.class);
				IView<SportPratique> v = presenter.getView();
				v.setPresenter(presenter);
				view.addMiddlePanel(v, menu.getMenuName());
			}

			if (menu != null && "ParamInout".equalsIgnoreCase(menu.getMenuId())) {
				ParamInoutPresenter presenter = eventBus
						.addHandler(ParamInoutPresenter.class);
				IView<ParamInout> v = presenter.getView();
				v.setPresenter(presenter);
				view.addMiddlePanel(v, menu.getMenuName());
			}

			if (menu != null && "Pays".equalsIgnoreCase(menu.getMenuId())) {
				PaysPresenter presenter = eventBus
						.addHandler(PaysPresenter.class);
				IView<Pays> v = presenter.getView();
				v.setPresenter(presenter);
				view.addMiddlePanel(v, menu.getMenuName());
			}

			if (menu != null
					&& "UserProfile".equalsIgnoreCase(menu.getMenuId())) {
				UserProfilePresenter presenter = eventBus
						.addHandler(UserProfilePresenter.class);
				IView<UserProfile> v = presenter.getView();
				v.setPresenter(presenter);
				view.addMiddlePanel(v, menu.getMenuName());
			}

			if (menu != null && "Patient".equalsIgnoreCase(menu.getMenuId())) {
				PatientPresenter presenter = eventBus
						.addHandler(PatientPresenter.class);
				IView<Patient> v = presenter.getView();
				v.setPresenter(presenter);
				view.addMiddlePanel(v, menu.getMenuName());
			}
			if (menu != null
					&& "Institution".equalsIgnoreCase(menu.getMenuId())) {
				InstitutionPresenter presenter = eventBus
						.addHandler(InstitutionPresenter.class);
				IView<Institution> v = presenter.getView();
				v.setPresenter(presenter);
				view.addMiddlePanel(v, menu.getMenuName());
			}

			if (menu != null && "UserGroup".equalsIgnoreCase(menu.getMenuId())) {
				UserGroupPresenter presenter = eventBus
						.addHandler(UserGroupPresenter.class);
				IView<UserGroup> v = presenter.getView();
				v.setPresenter(presenter);
				view.addMiddlePanel(v, menu.getMenuName());
			}
			if (menu != null
					&& "UserGroupDetail".equalsIgnoreCase(menu.getMenuId())) {
				UserGroupDetailPresenter presenter = eventBus
						.addHandler(UserGroupDetailPresenter.class);
				IView<UserGroupDetail> v = presenter.getView();
				v.setPresenter(presenter);
				view.addMiddlePanel(v, menu.getMenuName());
			}
		}
	}

	@SuppressWarnings("unused")
	private void callLoginPage() {
		Info.display(I18NMessages.getMessages().message(), I18NMessages
				.getMessages().redirectToLoginPage());
		Window.Location.reload();
	}

	public void onLogout() {
		int timeOut = 10000;
		final AutoProgressMessageBox box = new AutoProgressMessageBox(
				I18NMessages.getMessages().message(), I18NMessages
						.getMessages().redirectToLoginPage());
		box.setProgressText(I18NMessages.getMessages().attendsSecondes(
				(int) (timeOut / 1000)));
		box.auto();
		box.show();
		final Timer t = new Timer() {
			@Override
			public void run() {
				// serviceUserParam.resetSession(new AsyncCallback<Void>() {
				// @Override
				// public void onFailure(Throwable caught) {
				// box.hide();
				// if (Utilities.userProfile != null &&
				// !Utilities.userProfile.getLanguage().isEmpty()) {
				// language = Utilities.userProfile.getLanguage();
				// }
				// Utilities.userProfile = null;
				// callLoginPage();
				// }
				//
				// @Override
				// public void onSuccess(Void result) {
				// Utilities.userProfile = null;
				// box.hide();
				// callLoginPage();
				// }
				// });
			}
		};
		t.schedule(timeOut);
	}

	public void onInitializeData() {
		Utilities.userInfo = null;
		loadUserInfo();
	}

	public void onChangeBottomPanel(IWidget widget) {
		view.addBottomPanel(widget);
	}

	public void onChangeMainPanel(IWidget widget) {
	}

	public void menuClicked(String menuName) {
		Menu m = new Menu();
		m.setMenuName(menuName);
		menuClicked(m);
	}

	public void menuClicked(Menu menu) {
		eventBus.changeMiddlePanel(null, menu);
	}

	public void loadUserInfo() {
		service.loadUserInfos(new AsyncCallback<UserInfo>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage() != null ? caught.getMessage()
						: caught.toString());
			}

			@Override
			public void onSuccess(UserInfo result) {
				Utilities.userInfo = result;
				setUserMenus(result.getMenus());
				view.drawMenu();
			}
		});
	}

	@Override
	public void bind() {
	}

	public void onStart() {
		view.setPresenter(this);
	}

	public void marquerAccess(String ecran) {
	}
}
