package com.rsoft.medicasoft.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.model.RequestDescriptor;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

public class UserRequestCallbackAdapter<T extends ModelBase> implements
		UserRequestCallback<T> {
	private PagingLoadConfig loadConfig;
	private AsyncCallback<PagingLoadResult<T>> asyncCallback;
	private Integer pageSize;
	private T model;
	private ModelBase modelRef;
	private RequestDescriptor requestDescriptor;
	private boolean lov;
	private String refName;

	public RequestDescriptor getRequestDescriptor() {
		return requestDescriptor;
	}

	public void setRequestDescriptor(RequestDescriptor requestDescriptor) {
		this.requestDescriptor = requestDescriptor;
	}

	public boolean isLov() {
		return lov;
	}

	public void setLov(boolean lov) {
		this.lov = lov;
	}

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = (T) model;
	}

	public ModelBase getRefModel() {
		return modelRef;
	}

	public void setRefModel(ModelBase model) {
		this.modelRef = model;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public String getRefName() {
		return refName;
	}

	public void setRefName(String refName) {
		this.refName = refName;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public PagingLoadConfig getLoadConfig() {
		return loadConfig;
	}

	public void setLoadConfig(PagingLoadConfig loadConfig) {
		this.loadConfig = loadConfig;
	}

	public AsyncCallback<PagingLoadResult<T>> getAsyncCallback() {
		return asyncCallback;
	}

	public void setAsyncCallback(AsyncCallback<PagingLoadResult<T>> callback) {
		this.asyncCallback = callback;
	}

	@Override
	public void onSingleOperationSuccessed(T model) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onMutilipleOperationsSuccessed(List<T> models) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSingleModelOperationFailed(T model, Throwable cause) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onMultipleOperationsFailed(List<T> model, Throwable cause) {
		// TODO Auto-generated method stub
	}
}
