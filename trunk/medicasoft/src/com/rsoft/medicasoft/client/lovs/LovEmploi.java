package com.rsoft.medicasoft.client.lovs;

/*
 Templates Generator
 */

/*@Author=Jean Louidort*/
/*@Generation Date=Mon Nov 18 01:14:07 EST 2013*/
/*@Version=1.0*/
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rsoft.medicasoft.shared.model.Emploi;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.form.ValueBaseField;

public class LovEmploi extends Lov<Emploi> {
	private LOVTemplate template = null;
	private Emploi model = new Emploi();
	static LabelProvider<Emploi> labelProvider = new LabelProvider<Emploi>() {
		@Override
		public String getLabel(Emploi model) {
			return model.getNomPoste();
		}
	};

	interface LOVTemplate extends XTemplates {
		@XTemplate("<div class='{style.item}'><h3><span>{model.nomPoste} </span></h3></div>")
		SafeHtml render(Emploi model, Style style);
	}

	private ILovEmploi iLovEmploi;

	@UiConstructor
	public LovEmploi(ILovEmploi iLovEmploi) {
		this(iLovEmploi, null);
	}

	public LovEmploi(ILovEmploi iLovEmploi,
			@SuppressWarnings("rawtypes") ValueBaseField[] fields) {
		super(null, labelProvider);
		this.setFields(fields);
		this.iLovEmploi = iLovEmploi;
		instantiate();
	}

	/**
	 * @return the model
	 */
	public Emploi getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(Emploi model) {
		this.model = model;
	}

	public interface ILovEmploi {
		public void loadEmploi(Emploi model, PagingLoadConfig loadConfig,
				AsyncCallback<PagingLoadResult<Emploi>> callback);
	}

	/**
	 * @return the iLovEmploi
	 */
	public ILovEmploi getILovEmploi() {
		return iLovEmploi;
	}

	/**
	 * @param iLovEmploi
	 *            the iLovEmploi to set
	 */
	public void setILovEmploi(ILovEmploi iLovEmploi) {
		this.iLovEmploi = iLovEmploi;
	}

	protected void instantiate() {
		template = GWT.create(LOVTemplate.class);
		// proxy
		proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<Emploi>>() {
			@Override
			public void load(PagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<Emploi>> callback) {
				Emploi criteria = new Emploi();
				iLovEmploi.loadEmploi(criteria, loadConfig, callback);
			}
		};
		super.instantiate();
		this.addBeforeSelectionHandler(new BeforeSelectionHandler<Emploi>() {
			@Override
			public void onBeforeSelection(BeforeSelectionEvent<Emploi> event) {
				event.cancel();
				model = getListView().getSelectionModel().getSelectedItem();
				setText(model.getNomPoste());
				if (fields != null && fields.length > 0) {
					if (fields[0] != null) {
						fields[0].setText(model.getNomPoste());
					}
					/*
					 * //Other lov value to be setted if (fields.length > 1 &&
					 * fields[1] != null) { if (fields[1] != null) {
					 * fields[1].setText(model.getKeyValue1()); } } //...
					 */
				}
			}
		});

		this.getStore().addFilter(new Store.StoreFilter<Emploi>() {
			@Override
			public boolean select(Store<Emploi> store, Emploi parent,
					Emploi model) {
				if (model != null) {
					StringBuilder builder = new StringBuilder();
					if (model.getNomPoste() != null) {
						builder.append(model.getNomPoste().toUpperCase());
					}
					if (model.getNomPoste() != null) {
						builder.append(model.getNomPoste().toUpperCase());
					}
					/*
					 * //Other values if (model.getKeyValue1() != null) {
					 * builder.append(model.getKeyValue1().toUpperCase()); }
					 * //...
					 */
					int totalCount = loader.getTotalCount();
					if (getText() != null && getText().trim().length() > 0) {
						setPageSize(totalCount);
						if (builder.toString().contains(
								getText().trim().toUpperCase())) {
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
	protected String getModelKey(Emploi model) {
		if (model != null) {
			return model.getNomPoste();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void setViewRenderer() {
		view = (ListView<Emploi, Emploi>) this.getListView();
		view.setCell(new AbstractCell<Emploi>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Emploi value, SafeHtmlBuilder sb) {
				sb.append(template.render(value, b.css()));
			}
		});
	}
}