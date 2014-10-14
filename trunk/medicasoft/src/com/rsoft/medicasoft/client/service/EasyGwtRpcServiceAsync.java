package com.rsoft.medicasoft.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rsoft.medicasoft.shared.FilterWrapper;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.UserInfo;
import com.rsoft.medicasoft.shared.model.EntityKeyDescriptor;
import com.rsoft.medicasoft.shared.model.RequestDescriptor;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

public interface EasyGwtRpcServiceAsync {

	<T extends ModelBase> void activate(RequestDescriptor descpritor, T value,
			AsyncCallback<T> callback);

	<T extends ModelBase> void deactivate(RequestDescriptor descpritor,
			T value, AsyncCallback<T> callback);

	<T extends ModelBase> void extract(RequestDescriptor descpritor, T value,
			AsyncCallback<String> callback);

	<T extends ModelBase> void persist(RequestDescriptor descpritor, T value,
			AsyncCallback<T> callback);

	<T extends ModelBase> void persist(RequestDescriptor descpritor,
			ArrayList<T> values, AsyncCallback<ArrayList<T>> callback);

	<T extends ModelBase> void post(RequestDescriptor descpritor, T value,
			AsyncCallback<T> callback);

	<T extends ModelBase> void search(RequestDescriptor descpritor,
			FilterWrapper filters, AsyncCallback<ArrayList<T>> callback);

	<T extends ModelBase> void search(RequestDescriptor descpritor,
			PagingLoadConfig loadConfig, FilterWrapper filters,
			AsyncCallback<PagingLoadResult<T>> callback);

	<T extends ModelBase> void unvalidate(RequestDescriptor descpritor,
			T value, AsyncCallback<T> callback);

	<T extends ModelBase> void validate(RequestDescriptor descpritor, T value,
			AsyncCallback<T> callback);

	<T extends ModelBase> void persist(RequestDescriptor descpritor,
			EntityKeyDescriptor keyDescriptor, ArrayList<T> values,
			AsyncCallback<ArrayList<T>> callback);

	void loadUserInfos(AsyncCallback<UserInfo> callback);

	void persist(RequestDescriptor descpritor,
			EntityKeyDescriptor keyDescriptor, ModelBase value,
			AsyncCallback<ModelBase> callback);
}
