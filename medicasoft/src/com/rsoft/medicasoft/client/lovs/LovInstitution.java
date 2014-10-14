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
import com.rsoft.medicasoft.shared.model.Institution;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.form.ValueBaseField;

public class LovInstitution extends Lov<Institution> {
	private LOVTemplate template = null;
	private Institution model = new Institution();
	static LabelProvider<Institution> labelProvider = new LabelProvider<Institution>() {
		@Override
		public String getLabel(Institution model) {
			return model.getNomInstitution();
		}
	};

	interface LOVTemplate extends XTemplates {
		@XTemplate("<div class='{style.item}'><h3><span>{model.nomInstitution} </span></h3></div>")
		SafeHtml render(Institution model, Style style);
	}
	
	private ILovInstitution iLovInstitution;
	@UiConstructor
	public LovInstitution(ILovInstitution iLovInstitution) {
		this(iLovInstitution, null);
	}

	public LovInstitution(ILovInstitution iLovInstitution, @SuppressWarnings("rawtypes") ValueBaseField[] fields) {
		super(null, labelProvider);
		this.setFields(fields);
		this.iLovInstitution = iLovInstitution; 
		instantiate();
	}

	/**
	 * @return the model
	 */
	public Institution getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(Institution model) {
		this.model = model;
	}
	
	public interface ILovInstitution {
		public void loadInstitution(Institution model, PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<Institution>> callback);
	}

	/**
	 * @return the iLovInstitution
	 */
	public ILovInstitution getILovInstitution() {
		return iLovInstitution;
	}

	/**
	 * @param iLovInstitution the iLovInstitution to set
	 */
	public void setILovInstitution(ILovInstitution iLovInstitution) {
		this.iLovInstitution = iLovInstitution;
	}

	protected void instantiate() {
		template = GWT.create(LOVTemplate.class);
		// proxy
		proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<Institution>>() {
			@Override
			public void load(PagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<Institution>> callback) {
				Institution criteria = new Institution();
				iLovInstitution.loadInstitution(criteria, loadConfig, callback);
			}
		};
		super.instantiate();
		this.addBeforeSelectionHandler(new BeforeSelectionHandler<Institution>() {
			@Override
			public void onBeforeSelection(BeforeSelectionEvent<Institution> event) {
				event.cancel();
				model = getListView().getSelectionModel().getSelectedItem();
				setText(model.getNomInstitution());
				if (fields != null && fields.length > 0) {
					if (fields[0] != null) {
						fields[0].setText(model.getNomInstitution());
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

		this.getStore().addFilter(new Store.StoreFilter<Institution>() {
			@Override
			public boolean select(Store<Institution> store, Institution parent,
					Institution model) {
				if (model != null) {
					StringBuilder builder = new StringBuilder();
					if (model.getNomInstitution() != null) {
						builder.append(model.getNomInstitution().toUpperCase());
					}
					if (model.getNomInstitution() != null) {
						builder.append(model.getNomInstitution().toUpperCase());
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
	protected String getModelKey(Institution model) {
		if (model != null) {
			return model.getNomInstitution();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void setViewRenderer() {
		view = (ListView<Institution, Institution>) this.getListView();
		view.setCell(new AbstractCell<Institution>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Institution value, SafeHtmlBuilder sb) {
				sb.append(template.render(value, b.css()));
			}
		});
	}
}