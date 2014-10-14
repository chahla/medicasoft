package com.rsoft.medicasoft.client.lovs;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.text.shared.SafeHtmlRenderer;
import com.rsoft.medicasoft.shared.StaticField;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.cell.core.client.form.TriggerFieldCell.TriggerFieldAppearance;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.event.BlurEvent;
import com.sencha.gxt.widget.core.client.event.BlurEvent.BlurHandler;
import com.sencha.gxt.widget.core.client.event.FocusEvent;
import com.sencha.gxt.widget.core.client.event.FocusEvent.FocusHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.ValueBaseField;

public abstract class Lov<T> extends ComboBox<T> {
	private int defaultPageSize = StaticField.COMBO_PAGE_SIZE;

	protected ValueBaseField<?>[] fields;

	public Lov(ComboBoxCell<T> cell) {
		super(cell);
	}

	public Lov(ListStore<T> store, LabelProvider<? super T> labelProvider,
			ListView<T, ?> listView, TriggerFieldAppearance appearance) {
		super(store, labelProvider, listView, appearance);
	}

	public Lov(ListStore<T> store, LabelProvider<? super T> labelProvider,
			ListView<T, ?> listView) {
		super(store, labelProvider, listView);
	}

	public Lov(ListStore<T> store, LabelProvider<? super T> labelProvider,
			SafeHtmlRenderer<T> renderer, TriggerFieldAppearance appearance) {
		super(store, labelProvider, renderer, appearance);
	}

	public Lov(ListStore<T> store, LabelProvider<? super T> labelProvider,
			SafeHtmlRenderer<T> renderer) {
		super(store, labelProvider, renderer);
	}

	public Lov(ListStore<T> store, LabelProvider<? super T> labelProvider,
			TriggerFieldAppearance appearance) {
		super(store, labelProvider, appearance);
	}

	public Lov(ListStore<T> store, LabelProvider<? super T> labelProvider) {
		super(store, labelProvider);
	}

	/**
	 * @return the pageSize
	 */
	public int getDefaultPageSize() {
		return defaultPageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setDefaultPageSize(int defaultPageSize) {
		this.defaultPageSize = defaultPageSize;
	}

	RpcProxy<PagingLoadConfig, PagingLoadResult<T>> proxy;
	PagingLoader<PagingLoadConfig, PagingLoadResult<T>> loader;
	ListStore<T> listStore;
	TextField nameField;
	Map<String, T> allItem = new LinkedHashMap<String, T>();
	int totalCount = 0;
	boolean isFocusedField = false;

	interface Bundle extends ClientBundle {
		@Source("ListOfValues.css")
		Style css();
	}

	interface Style extends CssResource {
		String item();
	}

	/**
	 * @return the fields
	 */
	public ValueBaseField<?>[] getFields() {
		return fields;
	}

	/**
	 * @param fields
	 *            the fields to set
	 */
	public void setFields(ValueBaseField<?>[] fields) {
		this.fields = fields;
	}

	protected abstract String getModelKey(T item);

	protected abstract void setViewRenderer();

	protected ListView<T, T> view = null;
	protected ModelKeyProvider<T> keyProvider = null;
	protected Bundle b = null;

	protected void instantiate() {
		b = GWT.create(Bundle.class);
		b.css().ensureInjected();
		keyProvider = new ModelKeyProvider<T>() {
			@Override
			public String getKey(T item) {
				return getModelKey(item);
			}
		};
		setViewRenderer();

		// store
		listStore = new ListStore<T>(keyProvider);
		// loader
		loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<T>>(proxy);
		loader.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, T, PagingLoadResult<T>>(
				listStore));
		loader.setRemoteSort(true);
		this.setStore(listStore);
		this.setLoader(loader);
		this.setDeferHeight(true);
		this.setWidth(150);
		this.setMinListWidth(400);
		this.setHideTrigger(true);
		this.setPageSize(defaultPageSize);
		this.getStore().setEnableFilters(true);
		this.setMinChars(0);
		this.setForceSelection(false);
		this.addBlurHandler(new BlurHandler() {
			@Override
			public void onBlur(BlurEvent event) {
				if (fields != null) {
					for (@SuppressWarnings("rawtypes")
					ValueBaseField field : fields) {
						if (field != null && field.getText().length() > 0
								&& getText() != null && getText().length() == 0)
							field.setText("");
					}
				}
				isFocusedField = false;
			}
		});

		this.addFocusHandler(new FocusHandler() {
			@Override
			public void onFocus(FocusEvent event) {
				isFocusedField = true;
			}
		});
	}
}
