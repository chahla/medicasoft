package com.rsoft.medicasoft.client.lovs;

/*
 Templates Generator
 */

/*@Author=Jean Louidort*/
/*@Generation Date=Sat Nov 02 22:28:09 EDT 2013*/
/*@Version=1.0*/
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rsoft.medicasoft.shared.model.Zone;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.form.ValueBaseField;

public class LovZone extends Lov<Zone> {
	private LOVTemplate template = null;
	private Zone model = new Zone();
	static LabelProvider<Zone> labelProvider = new LabelProvider<Zone>() {
		@Override
		public String getLabel(Zone model) {
			return model.getNom();
		}
	};

	interface LOVTemplate extends XTemplates {
		@XTemplate("<div class='{style.item}'><h3><span>{model.nom} </span></h3></div>")
		SafeHtml render(Zone model, Style style);
	}

	private ILovZone iLovZone;

	@UiConstructor
	public LovZone(ILovZone iLovZone) {
		this(iLovZone, null);
	}

	public LovZone(ILovZone iLovZone,
			@SuppressWarnings("rawtypes") ValueBaseField[] fields) {
		super(null, labelProvider);
		this.setFields(fields);
		this.iLovZone = iLovZone;
		instantiate();
	}

	/**
	 * @return the model
	 */
	public Zone getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(Zone model) {
		this.model = model;
	}

	public interface ILovZone {
		public void loadZone(Zone model, PagingLoadConfig loadConfig,
				AsyncCallback<PagingLoadResult<Zone>> callback);
	}

	/**
	 * @return the iLovZone
	 */
	public ILovZone getILovZone() {
		return iLovZone;
	}

	/**
	 * @param iLovZone
	 *            the iLovZone to set
	 */
	public void setILovZone(ILovZone iLovZone) {
		this.iLovZone = iLovZone;
	}

	protected void instantiate() {
		template = GWT.create(LOVTemplate.class);
		// proxy
		proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<Zone>>() {
			@Override
			public void load(PagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<Zone>> callback) {
				Zone criteria = new Zone();
				iLovZone.loadZone(criteria, loadConfig, callback);
			}
		};
		super.instantiate();
		this.addBeforeSelectionHandler(new BeforeSelectionHandler<Zone>() {
			@Override
			public void onBeforeSelection(BeforeSelectionEvent<Zone> event) {
				event.cancel();
				model = getListView().getSelectionModel().getSelectedItem();
				setText(model.getNom());
				if (fields != null && fields.length > 0) {
					if (fields[0] != null) {
						if (model.getCommune() != null) {
							fields[0].setText(model.getCommune().getNom());
						}
					}
					/*
					 * //Other lov value to be setted if (fields.length > 1 &&
					 * fields[1] != null) { if (fields[1] != null) {
					 * fields[1].setText(model.getKeyValue1()); } } //...
					 */
				}
			}
		});

		this.getStore().addFilter(new Store.StoreFilter<Zone>() {
			@Override
			public boolean select(Store<Zone> store, Zone parent, Zone model) {
				if (model != null) {
					StringBuilder builder = new StringBuilder();
					if (model.getNom() != null) {
						builder.append(model.getNom().toUpperCase());
					}
					if (model.getCommune() != null
							&& model.getCommune().getNom() != null) {
						builder.append(model.getCommune().getNom()
								.toUpperCase());
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
	protected String getModelKey(Zone model) {
		if (model != null) {
			return model.getNom();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void setViewRenderer() {
		view = (ListView<Zone, Zone>) this.getListView();
		view.setCell(new AbstractCell<Zone>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Zone value, SafeHtmlBuilder sb) {
				sb.append(template.render(value, b.css()));
			}
		});
	}
}