package com.rsoft.medicasoft.client.lovs;
/*
	Templates Generator
*/

/*@Author=Jean Louidort*/
/*@Generation Date=Mon Oct 14 22:24:32 EDT 2013*/
/*@Version=1.0*/
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rsoft.medicasoft.shared.model.Commune;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.form.ValueBaseField;

public class LovCommune extends Lov<Commune> {
	private LOVTemplate template = null;
	private Commune model = new Commune();
	static LabelProvider<Commune> labelProvider = new LabelProvider<Commune>() {
		@Override
		public String getLabel(Commune model) {
			return model.getNom();
		}
	};

	interface LOVTemplate extends XTemplates {
		@XTemplate("<div class='{style.item}'><h3><span>{model.nom} </span></h3></div>")
		SafeHtml render(Commune model, Style style);
	}
	
	private ILovCommune iLovCommune;
	@UiConstructor
	public LovCommune(ILovCommune iLovCommune) {
		this(iLovCommune, null);
	}

	public LovCommune(ILovCommune iLovCommune, @SuppressWarnings("rawtypes") ValueBaseField[] fields) {
		super(null, labelProvider);
		this.setFields(fields);
		this.iLovCommune = iLovCommune; 
		instantiate();
	}

	/**
	 * @return the model
	 */
	public Commune getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(Commune model) {
		this.model = model;
	}
	
	public interface ILovCommune {
		public void loadCommune(Commune model, PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<Commune>> callback);
	}

	/**
	 * @return the iLovCommune
	 */
	public ILovCommune getILovCommune() {
		return iLovCommune;
	}

	/**
	 * @param iLovCommune the iLovCommune to set
	 */
	public void setILovCommune(ILovCommune iLovCommune) {
		this.iLovCommune = iLovCommune;
	}

	protected void instantiate() {
		template = GWT.create(LOVTemplate.class);
		// proxy
		proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<Commune>>() {
			@Override
			public void load(PagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<Commune>> callback) {
				Commune criteria = new Commune();
				iLovCommune.loadCommune(criteria, loadConfig, callback);
			}
		};
		super.instantiate();
		this.addBeforeSelectionHandler(new BeforeSelectionHandler<Commune>() {
			@Override
			public void onBeforeSelection(BeforeSelectionEvent<Commune> event) {
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

		this.getStore().addFilter(new Store.StoreFilter<Commune>() {
			@Override
			public boolean select(Store<Commune> store, Commune parent,
					Commune model) {
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
	protected String getModelKey(Commune model) {
		if (model != null) {
			return model.getNom();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void setViewRenderer() {
		view = (ListView<Commune, Commune>) this.getListView();
		view.setCell(new AbstractCell<Commune>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Commune value, SafeHtmlBuilder sb) {
				sb.append(template.render(value, b.css()));
			}
		});
	}
}