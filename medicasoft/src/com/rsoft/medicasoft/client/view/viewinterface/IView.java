package com.rsoft.medicasoft.client.view.viewinterface;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.mvp4g.client.view.ReverseViewInterface;
import com.rsoft.medicasoft.client.toolsbar.IToolsBarAction;
import com.rsoft.medicasoft.shared.ModelBase;

public interface IView<T extends ModelBase> extends IWidget, IsWidget, IToolsBarAction,
		ReverseViewInterface<IPresenter> {
	public interface IPresenterBase {
	}

	public void updateView(T model);

	public void updateView(List<T> models);

	public T updateModel(T model);

	public void onDataChanged(T model, boolean reloadDetails,
			boolean setBtnsPersistVisible);

	/***/
	public boolean stopCurrentAction();

	public void onFormCleared();

	public void onPersistenceSuccessed();

	public void onActionFailure(Throwable caught, String message);

	public void formReset();

	// public void formUpdated();

	public void setPresenter(IPresenter presenter);

	public IPresenter getPresenter();

	public boolean isDetail();
	
	public void setDetail(boolean detail);
}
