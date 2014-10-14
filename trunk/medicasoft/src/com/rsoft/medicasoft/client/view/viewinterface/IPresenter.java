package com.rsoft.medicasoft.client.view.viewinterface;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rsoft.medicasoft.shared.DataException;
import com.rsoft.medicasoft.shared.ModelBase;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

public interface IPresenter {
	public void onSaveHandled() throws DataException;
	public void onMergeHandled() throws DataException;
	public void onRemoveHandled() throws DataException;
	public void onFirstHandled() throws DataException;
	public void onLastHandled() throws DataException;
	public void onNextHandled() throws DataException;
	public void onPreviousHandled() throws DataException;
	public void onPrintHandled() throws DataException;
	public void onLoadHandled() throws DataException;
	public void onExtractHandled() throws DataException;
	public void onRefreshHandled() throws DataException;
	public void onCriteriaHandled() throws DataException;
	public void onSearchHandled(ModelBase _model) throws DataException;
	public void onChangeHandled(Integer record) throws DataException;
	public void onResetGridHandled(int nbBlankRows,
			PagingLoadConfig loadConfig,
			AsyncCallback<PagingLoadResult<ModelBase>> callback);
	public void onExecuteHandled() throws DataException;
	public void onAddEmptyValuesHandled(PagingLoadConfig loadConfig);
	public <T extends ModelBase> void onColumnarSeachHandled(PagingLoadConfig loadConfig,
			AsyncCallback<PagingLoadResult<T>> callback)
			throws DataException;
	public void onSearchHandled(ModelBase _model, PagingLoadConfig loadConfig,
			AsyncCallback<PagingLoadResult<ModelBase>> callback);
}
