package com.rsoft.medicasoft.client.view;

/*
 Robelkend Templates Generator
 */
/*@Author=Jean Louidort*/
/*@Generation Date=Fri Sep 13 18:53:51 EDT 2013*/
/*@Version=1.0*/
import java.util.List;

import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.client.presenter.UserGroupDetailPresenter;
import com.rsoft.medicasoft.client.view.viewinterface.IView;
import com.rsoft.medicasoft.shared.model.UserGroup;
import com.rsoft.medicasoft.shared.model.UserGroupDetail;

public abstract class UserGroupMasterWrapperView extends
		ViewFormBase<UserGroup> {

	protected UserGroupDetailPresenter userGroupDetailPresenter;
	private IView<UserGroupDetail> userGroupDetailView;

	protected abstract void addDetail(Widget widget);

	protected abstract void addDetails(Widget widget, String title);

	protected void finalizeForm(ViewCallback callback, String title) {
		userGroupDetailPresenter = eventBus
				.addHandler(UserGroupDetailPresenter.class);
		userGroupDetailView = userGroupDetailPresenter.getView();
		userGroupDetailView.setViewCallback(callback);
		userGroupDetailView.setPresenter(userGroupDetailPresenter);
		userGroupDetailPresenter.setHeaderTitle(title);
		addDetail(userGroupDetailView.getWidget());
		userGroupDetailView.setBtnSaveVisible(false);
	}

	protected void refreshDetails() {
		userGroupDetailView.search();
	}

	protected void persistDetails() {
		userGroupDetailView.persist();
	}

	protected void resetDetails() {
		userGroupDetailView.reset();
	}

	protected void onChangeHeader(UserGroup model, boolean reset,
			boolean setPersistBtnsVisible) {
		userGroupDetailPresenter.onChangeHeaderUserGroupDetail(model);
		if (reset) {
			userGroupDetailView.reset();
		}
		userGroupDetailView.setBtnSaveVisible(setPersistBtnsVisible);
	}

	protected void onChangeHeader(UserGroup model) {
		onChangeHeader(model, true,
				model.getEntityId() != null && model.getEntityId() != null);
	}

	@Override
	public void finalizeModel(UserGroup value) {
		userGroupDetailPresenter.getListStore().commitChanges();
		List<UserGroupDetail> userGroupDetails = userGroupDetailPresenter
				.getListStore().getAll();
		for (UserGroupDetail detail : userGroupDetails) {
			if ("insert".equalsIgnoreCase(detail.getSTATUS())
					|| "merge".equalsIgnoreCase(detail.getSTATUS())) {
				value.addUserGroupDetail(detail);
			} else if ("remove".equalsIgnoreCase(detail.getSTATUS())) {
				value.removeUserGroupDetail(detail);
			}
		}
	}
}