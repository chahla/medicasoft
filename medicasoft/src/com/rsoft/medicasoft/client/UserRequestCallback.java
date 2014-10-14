package com.rsoft.medicasoft.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.model.RequestDescriptor;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

public interface UserRequestCallback<T extends ModelBase> {
	public RequestDescriptor getRequestDescriptor();

	public void setRequestDescriptor(RequestDescriptor requestDescriptor);

	public Integer getPageSize();

	public PagingLoadConfig getLoadConfig();

	public AsyncCallback<PagingLoadResult<T>> getAsyncCallback();

	public void onSingleOperationSuccessed(T model);

	public void onMutilipleOperationsSuccessed(List<T> models);

	public void onSingleModelOperationFailed(T model, Throwable cause);

	public void onMultipleOperationsFailed(List<T> model, Throwable cause);

	public T getModel();

	public void setModel(T model);

	public ModelBase getRefModel();

	public void setRefModel(ModelBase model);

	public String getRefName();

	public void setRefName(String refName);

	public boolean isLov();

	public void setLov(boolean lov);
}
