package com.rsoft.medicasoft.client.lovs;

/*
 Templates Generator
 */

/*@Author=Jean Louidort*/
/*@Generation Date=Tue Jul 23 23:11:26 EDT 2013*/
/*@Version=1.0*/
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rsoft.medicasoft.shared.model.UserGroup;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.form.ValueBaseField;

public class LovUserGroup extends Lov<UserGroup> {
	private LOVTemplate template = null;
	private UserGroup model = new UserGroup();
	static LabelProvider<UserGroup> labelProvider = new LabelProvider<UserGroup>() {
		@Override
		public String getLabel(UserGroup model) {
			return model.getDescription();//Ajout
		}
	};

	interface LOVTemplate extends XTemplates {
		@XTemplate("<div class='{style.item}'><h3><span>{model.type} {model.description} </span></h3></div>")
		SafeHtml render(UserGroup model, Style style);
	}

	private ILovUserGroup iLovUserGroup;

	@UiConstructor
	public LovUserGroup(ILovUserGroup iLovUserGroup) {
		this(iLovUserGroup, null);
	}

	public LovUserGroup(ILovUserGroup iLovUserGroup,
			@SuppressWarnings("rawtypes") ValueBaseField[] fields) {
		super(null, labelProvider);
		this.setFields(fields);
		this.iLovUserGroup = iLovUserGroup;
		instantiate();
	}

	/**
	 * @return the model
	 */
	public UserGroup getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(UserGroup model) {
		this.model = model;
	}

	public interface ILovUserGroup {
		public void loadUserGroup(UserGroup model, PagingLoadConfig loadConfig,
				AsyncCallback<PagingLoadResult<UserGroup>> callback);
	}

	/**
	 * @return the iLovUserGroup
	 */
	public ILovUserGroup getILovUserGroup() {
		return iLovUserGroup;
	}

	/**
	 * @param iLovUserGroup
	 *            the iLovUserGroup to set
	 */
	public void setILovUserGroup(ILovUserGroup iLovUserGroup) {
		this.iLovUserGroup = iLovUserGroup;
	}

	protected void instantiate() {
		template = GWT.create(LOVTemplate.class);
		// proxy
		proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<UserGroup>>() {
			@Override
			public void load(PagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<UserGroup>> callback) {
				UserGroup criteria = new UserGroup();
				iLovUserGroup.loadUserGroup(criteria, loadConfig, callback);
			}
		};
		super.instantiate();
		this.addBeforeSelectionHandler(new BeforeSelectionHandler<UserGroup>() {
			@Override
			public void onBeforeSelection(BeforeSelectionEvent<UserGroup> event) {
				event.cancel();
				model = getListView().getSelectionModel().getSelectedItem();
				setText(model.getDescription());
				if (fields != null && fields.length > 0) {
					if (fields[0] != null) {
						fields[0].setText(model.getDescription());
					}
					/*
					 * //Other lov value to be setted if (fields.length > 1 &&
					 * fields[1] != null) { if (fields[1] != null) {
					 * fields[1].setText(model.getKeyValue1()); } } //...
					 */
				}
			}
		});

		this.getStore().addFilter(new Store.StoreFilter<UserGroup>() {
			@Override
			public boolean select(Store<UserGroup> store, UserGroup parent,
					UserGroup model) {
				if (model != null) {
					StringBuilder builder = new StringBuilder();
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
	protected String getModelKey(UserGroup model) {
		if (model != null) {
			return model.getDescription();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void setViewRenderer() {
		view = (ListView<UserGroup, UserGroup>) this.getListView();
		view.setCell(new AbstractCell<UserGroup>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					UserGroup value, SafeHtmlBuilder sb) {
				sb.append(template.render(value, b.css()));
			}
		});
	}
}