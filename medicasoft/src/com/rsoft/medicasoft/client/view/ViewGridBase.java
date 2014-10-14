package com.rsoft.medicasoft.client.view;

/*
 Robelkend Templates Generator
 */
/*@Author=Jean Louidort*/
/*@Generation Date=Tue Jul 23 23:39:16 EDT 2013*/
/*@Version=1.0*/
import java.util.List;

import com.google.gwt.editor.client.Editor.Ignore;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.client.UserRequestCallbackAdapter;
import com.rsoft.medicasoft.client.toolsbar.EditableGridToolsBar;
import com.rsoft.medicasoft.client.toolsbar.IToolsBarAction;
import com.rsoft.medicasoft.client.view.viewinterface.IView;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.StaticField;
import com.rsoft.medicasoft.shared.model.RequestDescriptor;
import com.sencha.gxt.core.client.Style.LayoutRegion;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.box.AutoProgressMessageBox;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.form.DateTimePropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.DoublePropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.IntegerPropertyEditor;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.RowNumberer;
import com.sencha.gxt.widget.core.client.grid.filters.GridFilters;

public abstract class ViewGridBase<T extends ModelBase> implements IView<T> {
	private boolean detail;
	protected String ENTITY_NAME = null;
	protected AutoProgressMessageBox box = null;
	protected ViewCallback callback;
	@Ignore
	@UiField(provided = true)
	com.rsoft.medicasoft.shared.i18n.II18NMessages messages = com.rsoft.medicasoft.shared.i18n.I18NMessages
			.getMessages();
	@Ignore
	@UiField(provided = true)
	IToolsBarAction toolsBarAction = this;
	public EditableGridToolsBar toolsBar;

	@Override
	public void persist() {
		setModelsToBePersisted(listStore);
		listStore.commitChanges();
		box = new AutoProgressMessageBox(messages.pleaseWait(),
				messages.savingProgress());
		box.setProgressText(messages.savingProgress());
		box.auto();
		box.show();
		UserRequestCallbackAdapter<T> urc = new UserRequestCallbackAdapter<T>();
		urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
		execute(ActionCommand.MULTIPLE, urc);
	}

	@Override
	public void first() {
		// TODO Auto-generated method stub
	}

	@Override
	public void last() {
		// TODO Auto-generated method stub

	}

	@Override
	public void next() {
		// TODO Auto-generated method stub

	}

	@Override
	public void previous() {
		// TODO Auto-generated method stub

	}

	@Override
	public void print() {
		// TODO Auto-generated method stub

	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

	@Override
	public void extract() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset() {
		resetForm();
		formReset();
	}

	@Override
	public void criteria() {
		if (isUpdatePending()
				&& !Window.confirm(messages.updatePendingContinue())) {
			return;
		}
		filters.clearFilters();
		resetForm();
	}

	@Override
	public void search() {
		if (isUpdatePending()
				&& !Window.confirm(messages.updatePendingContinue())) {
			return;
		}
		loadDatas();
	}

	@Override
	public void remove() {
	}

	@Override
	public int rechordChanged(int record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void ignore() {
	}

	@UiField
	ContentPanel toolBarArea;
	@UiField
	FramedPanel panel;
	@UiField
	HTML htmlMessage;
	@UiField(provided = true)
	MarginData outerData = new MarginData(0);
	@UiField(provided = true)
	BorderLayoutData northData = new BorderLayoutData(63);
	@UiField(provided = true)
	BorderLayoutData westData = new BorderLayoutData(350);
	@UiField(provided = true)
	MarginData centerData = new MarginData(0);
	@UiField(provided = true)
	BorderLayoutData eastData = new BorderLayoutData(350);
	@UiField(provided = true)
	BorderLayoutData southData = new BorderLayoutData(480);

	@UiField
	BorderLayoutContainer mainContainer;
	@UiField
	BorderLayoutContainer menuContainer;

	DateTimePropertyEditor datePropertyEditor = new DateTimePropertyEditor(
			DateTimeFormat.getFormat(StaticField.DATE_FORMAT));
	NumberPropertyEditor<Integer> integerPropertyEditor = new IntegerPropertyEditor();
	NumberFormat inumberFormat = NumberFormat
			.getFormat(StaticField.INTEGER_FORMAT);
	NumberPropertyEditor<Double> doublePropertyEditor = new DoublePropertyEditor();
	NumberFormat dnumberFormat = NumberFormat
			.getFormat(StaticField.DECIMAL_FORMAT);

	@UiField
	ColumnModel<T> columnModel;
	@UiField
	ListStore<T> listStore;
	@UiField
	Grid<T> grid;
	protected boolean bannerInfoIsShowed = false;
	protected boolean userHideBannerInfo = true;

	@UiFactory
	ColumnModel<T> createColumnModel() {
		return columnModel;
	}

	@UiFactory
	ListStore<T> createListStore() {
		return listStore;
	}

	@Override
	public boolean stopCurrentAction() {
		if (!isSearchPending() && isUpdatePending()
				&& !Window.confirm(messages.updatePendingContinue())) {
			return true;
		}
		return false;
	}

	protected PagingLoader<FilterPagingLoadConfig, PagingLoadResult<T>> pagingLoader = null;
	protected RowNumberer<T> numberer;
	protected List<ColumnConfig<T, ?>> list;
	protected GridFilters<T> filters;
	protected Widget widget;

	@Override
	public void help() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBtnSaveVisible(boolean aFlag) {
		toolsBar.setSaveEnabled(aFlag);
		toolsBar.setRemoveEnabled(aFlag);
		toolsBar.setPersistSepEnabled(aFlag);
	}

	@Override
	public void showInfoBanner() {
		showInfoBanner(!bannerInfoIsShowed);
		userHideBannerInfo = bannerInfoIsShowed;
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

	protected abstract void execute(ActionCommand command,
			UserRequestCallbackAdapter<T> urc);

	protected abstract void setModelsToBePersisted(ListStore<T> listStore);

	protected abstract boolean isUpdatePending();

	protected abstract void loadDatas();

	protected abstract void resetForm();

	protected abstract void formUpdated();

	protected abstract boolean isSearchPending();

	public abstract Widget asWidget();
}
