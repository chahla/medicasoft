/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rsoft.medicasoft.client;

import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.menu.Menu;
import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;
import com.mvp4g.client.annotation.Start;
import com.mvp4g.client.event.EventBus;
import com.rsoft.medicasoft.client.presenter.InstitutionPresenter;
import com.rsoft.medicasoft.client.presenter.PatientPresenter;
import com.rsoft.medicasoft.client.presenter.QuickUserGroupPresenter;
import com.rsoft.medicasoft.client.presenter.RootPresenter;
import com.rsoft.medicasoft.client.view.viewinterface.IWidget;
//<!-Automatically added importation do not remove or modify this line->
import com.rsoft.medicasoft.client.presenter.ProfessionPresenter;
      
import com.rsoft.medicasoft.client.presenter.ReligionPresenter;
      
import com.rsoft.medicasoft.client.presenter.EmploiPresenter;
      
import com.rsoft.medicasoft.client.presenter.AssurancePresenter;
      
import com.rsoft.medicasoft.client.presenter.SportFrequentePresenter;
      
import com.rsoft.medicasoft.client.presenter.ParamInoutPresenter;
      
import com.rsoft.medicasoft.client.presenter.PaysPresenter;
      
import com.rsoft.medicasoft.client.presenter.DepartementPresenter;
      
import com.rsoft.medicasoft.client.presenter.CommunePresenter;
      
import com.rsoft.medicasoft.client.presenter.ZonePresenter;
      
import com.rsoft.medicasoft.client.presenter.UserProfilePresenter;
      
import com.rsoft.medicasoft.client.presenter.UserGroupDetailPresenter;

import com.rsoft.medicasoft.client.presenter.UserGroupPresenter;

/**
 * 
 * @author Jean Louidort
 */
@Events(startPresenter = RootPresenter.class)
public interface RSOFTEventBus extends EventBus {

	@Event(handlers = RootPresenter.class)
	public void changeMiddlePanel(IWidget widget, Menu menu);

	@Event(handlers = RootPresenter.class)
	public void changeMainPanel(IWidget widget);

	@Event(handlers = RootPresenter.class)
	public void changeBottomPanel(IWidget widget);

	@Event(handlers = RootPresenter.class)
	public void logout();

	@Event(handlers = RootPresenter.class)
	public void initializeData();

	// <!-Begin User commands->

	
      
      
      
      
      
      
      
      
      
      
      // <!-Marked line, do not remove or modify this line->
      @Event(handlers = ProfessionPresenter.class)
      public <T extends ModelBase> void executeProfession(ActionCommand command, UserRequestCallback<T> callback);

      @Event(handlers = ReligionPresenter.class)
      public <T extends ModelBase> void executeReligion(ActionCommand command, UserRequestCallback<T> callback);

      @Event(handlers = EmploiPresenter.class)
      public <T extends ModelBase> void executeEmploi(ActionCommand command, UserRequestCallback<T> callback);

      @Event(handlers = AssurancePresenter.class)
      public <T extends ModelBase> void executeAssurance(ActionCommand command, UserRequestCallback<T> callback);

      @Event(handlers = SportFrequentePresenter.class)
      public <T extends ModelBase> void executeSportFrequente(ActionCommand command, UserRequestCallback<T> callback);

      @Event(handlers = ParamInoutPresenter.class)
      public <T extends ModelBase> void executeParamInout(ActionCommand command, UserRequestCallback<T> callback);

      @Event(handlers = PaysPresenter.class)
      public <T extends ModelBase> void executePays(ActionCommand command, UserRequestCallback<T> callback);

      @Event(handlers = DepartementPresenter.class)
      public <T extends ModelBase> void executeDepartement(ActionCommand command, UserRequestCallback<T> callback);

      @Event(handlers = CommunePresenter.class)
      public <T extends ModelBase> void executeCommune(ActionCommand command, UserRequestCallback<T> callback);

      @Event(handlers = ZonePresenter.class)
      public <T extends ModelBase> void executeZone(ActionCommand command, UserRequestCallback<T> callback);

      @Event(handlers = UserProfilePresenter.class)
      public <T extends ModelBase> void executeUserProfile(ActionCommand command, UserRequestCallback<T> callback);


	@Event(handlers = InstitutionPresenter.class)
	public <T extends ModelBase> void executeInstitution(ActionCommand command,
			UserRequestCallback<T> callback);

	@Event(handlers = PatientPresenter.class)
	public <T extends ModelBase> void executePatient(ActionCommand command,
			UserRequestCallback<T> callback);

	@Event(handlers = UserGroupDetailPresenter.class)
	public <T extends ModelBase> void executeUserGroupDetail(
			ActionCommand command, UserRequestCallback<T> callback);

	@Event(handlers = UserGroupPresenter.class)
	public <T extends ModelBase> void executeUserGroup(ActionCommand command,
			UserRequestCallback<T> callback);

	@Event(handlers = QuickUserGroupPresenter.class)
	public <T extends ModelBase> void executeQuickUserGroup(ActionCommand command,
			UserRequestCallback<T> callback);
	// <!-End User commands->
	@Start
	@Event(handlers = { RootPresenter.class })
	public void start();
}
