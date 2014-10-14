package com.rsoft.medicasoft.client.lovs;
/*
	Templates Generator
*/

/*@Author=Jean Louidort*/
/*@Generation Date=Mon Oct 14 22:24:33 EDT 2013*/
/*@Version=1.0*/
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rsoft.medicasoft.shared.model.Pays;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.form.ValueBaseField;

public class LovPays extends Lov<Pays> {
	private LOVTemplate template = null;
	private Pays model = new Pays();
	static LabelProvider<Pays> labelProvider = new LabelProvider<Pays>() {
		@Override
		public String getLabel(Pays model) {
			return model.getNom();
		}
	};

	interface LOVTemplate extends XTemplates {
		@XTemplate("<div class='{style.item}'><h3><span>{model.nom} </span></h3></div>")
		SafeHtml render(Pays model, Style style);
	}
	
	private ILovPays iLovPays; 
	@UiConstructor
	public LovPays(ILovPays iLovPays) {
		this(iLovPays, null);
	}

	public LovPays(ILovPays iLovPays, @SuppressWarnings("rawtypes") ValueBaseField[] fields) {
		super(null, labelProvider);
		this.setFields(fields);
		this.iLovPays = iLovPays; 
		instantiate();
	}

	/**
	 * @return the model
	 */
	public Pays getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(Pays model) {
		this.model = model;
	}
	
	public interface ILovPays {
		public void loadPays(Pays model, PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<Pays>> callback);
	}

	/**
	 * @return the iLovPays
	 */
	public ILovPays getILovPays() {
		return iLovPays;
	}

	/**
	 * @param iLovPays the iLovPays to set
	 */
	public void setILovPays(ILovPays iLovPays) {
		this.iLovPays = iLovPays;
	}

	protected void instantiate() {
		template = GWT.create(LOVTemplate.class);
		// proxy
		proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<Pays>>() {
			@Override
			public void load(PagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<Pays>> callback) {
				Pays criteria = new Pays();
				iLovPays.loadPays(criteria, loadConfig, callback);
			}
		};
		super.instantiate();
		this.addBeforeSelectionHandler(new BeforeSelectionHandler<Pays>() {
			@Override
			public void onBeforeSelection(BeforeSelectionEvent<Pays> event) {
				event.cancel();
				model = getListView().getSelectionModel().getSelectedItem();
				setText(model.getNom());
				if (fields != null && fields.length > 0) {
					if (fields[0] != null) {
						fields[0].setText(model.getNom());
					}
					/*
					//Other lov value to be setted
					if (fields.length > 1 && fields[1] != null) {
						if (fields[1] != null) {
							fields[1].setText(model.getKeyValue1());
						}
					}
					//...
					*/
				}
			}
		});

		this.getStore().addFilter(new Store.StoreFilter<Pays>() {
			@Override
			public boolean select(Store<Pays> store, Pays parent,
					Pays model) {
				if (model != null) {
					StringBuilder builder = new StringBuilder();
					if (model.getNom() != null) {
						builder.append(model.getNom().toUpperCase());
					}
					if (model.getNom() != null) {
						builder.append(model.getNom().toUpperCase());
					}
					/*
					//Other values
					if (model.getKeyValue1() != null) {
						builder.append(model.getKeyValue1().toUpperCase());
					}
					//...
					*/
					int totalCount = loader.getTotalCount();
					if (getText() != null && getText().trim().length() > 0) {
						setPageSize(totalCount);
						if (builder.toString().contains(getText().trim().toUpperCase())) {
							setPageSize(getPageSize());
							return true;
						} else {
							setPageSize(totalCount);
							return false;
						}
					} else {
						setPageSize(getPageSize());
						return true;
					}
				}
				return false;
			}
		});
	}

	@Override
	protected String getModelKey(Pays model) {
		if (model != null) {
			return model.getNom();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void setViewRenderer() {
		view = (ListView<Pays, Pays>) this.getListView();
		view.setCell(new AbstractCell<Pays>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Pays value, SafeHtmlBuilder sb) {
				sb.append(template.render(value, b.css()));
			}
		});
	}
}