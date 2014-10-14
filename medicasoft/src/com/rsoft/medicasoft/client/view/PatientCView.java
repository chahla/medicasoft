package com.rsoft.medicasoft.client.view;

/*
 Robelkend Templates Generator
 */
/*@Author=Jean Louidort*/
/*@Generation Date=Thu Sep 19 22:50:28 EDT 2013*/
/*@Version=1.0*/
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.rsoft.medicasoft.client.UserRequestCallbackAdapter;
import com.rsoft.medicasoft.client.presenter.PatientPresenter;
import com.rsoft.medicasoft.client.toolsbar.EditableGridToolsBar;
import com.rsoft.medicasoft.client.view.viewinterface.IPresenter;
import com.rsoft.medicasoft.client.view.viewinterface.IWidget;
import com.rsoft.medicasoft.shared.ActionCommand;
import com.rsoft.medicasoft.shared.FilterWrapper;
import com.rsoft.medicasoft.shared.SystemMessage;
import com.rsoft.medicasoft.shared.XFilter;
import com.rsoft.medicasoft.shared.model.Patient;
import com.rsoft.medicasoft.shared.model.PatientPropertiesAccess;
import com.rsoft.medicasoft.shared.model.RequestDescriptor;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.LayoutRegion;
import com.sencha.gxt.core.client.Style.Side;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.event.StoreRecordChangeEvent;
import com.sencha.gxt.data.shared.event.StoreRecordChangeEvent.StoreRecordChangeHandler;
import com.sencha.gxt.data.shared.loader.FilterConfig;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfigBean;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.GridDropTarget;
import com.sencha.gxt.widget.core.client.event.BeforeStartEditEvent;
import com.sencha.gxt.widget.core.client.event.BeforeStartEditEvent.BeforeStartEditHandler;
import com.sencha.gxt.widget.core.client.event.CellClickEvent;
import com.sencha.gxt.widget.core.client.event.CellClickEvent.CellClickHandler;
import com.sencha.gxt.widget.core.client.event.CompleteEditEvent;
import com.sencha.gxt.widget.core.client.event.CompleteEditEvent.CompleteEditHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.MaxLengthValidator;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.GridViewConfig;
import com.sencha.gxt.widget.core.client.grid.RowNumberer;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;
import com.sencha.gxt.widget.core.client.grid.filters.DateFilter;
import com.sencha.gxt.widget.core.client.grid.filters.GridFilters;
import com.sencha.gxt.widget.core.client.grid.filters.NumericFilter;
import com.sencha.gxt.widget.core.client.grid.filters.StringFilter;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;

public class PatientCView extends ViewGridBase<Patient> {
	private PatientPresenter presenter;

	public interface Renderer extends ToolTipConfig.ToolTipRenderer<Object>,
			XTemplates {
		@Override
		@XTemplate(source = "help/patientTemplate.html")
		public SafeHtml renderToolTip(Object data);
	}

	private Renderer renderer = GWT.create(Renderer.class);

	interface GxtUiBinder extends UiBinder<Widget, PatientCView> {
	}

	@Override
	public void criteria() {
		if (presenter.isUpdatePending()
				&& !Window.confirm(messages.updatePendingContinue())) {
			return;
		}
		filters.clearFilters();
		resetForm();
	}

	@Override
	public void remove() {
		List<Patient> Patients = grid.getSelectionModel().getSelectedItems();
		for (Patient Patient : Patients) {
			if (Patient.getEntityId() != null) {
				presenter.addModelToBeRemoved(Patient);
				Patient foundModel = listStore.findModelWithKey(listStore
						.getKeyProvider().getKey(Patient));
				if (foundModel != null) {
					foundModel.setSTATUS("REMOVE");
				}
			}
		}
		listStore.commitChanges();
		grid.getView().refresh(false);
	}

	@Override
	public void ignore() {
		List<Patient> Patients = grid.getSelectionModel().getSelectedItems();
		for (Patient Patient : Patients) {
			Patient foundModel = listStore.findModelWithKey(listStore
					.getKeyProvider().getKey(Patient));
			if (foundModel != null) {
				foundModel.setSTATUS("IGNORE");
			}
		}
		listStore.commitChanges();
		grid.getView().refresh(false);
	}

	@Override
	public void loadDatas() {
		try {
			RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Patient>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Patient>>() {
				@Override
				public void load(FilterPagingLoadConfig loadConfig,
						AsyncCallback<PagingLoadResult<Patient>> callback) {
					listStore.clear();
					listStore.commitChanges();
					FilterWrapper wrapper = new FilterWrapper(null);

					if (filters.getFilter("nom") != null
							&& filters.getFilter("nom").getFilterConfig() != null
							&& !filters.getFilter("nom").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("nom")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "nom", fc.getType(),
										fc.getValue()));
							}
						}
					}
					if (filters.getFilter("prenom") != null
							&& filters.getFilter("prenom").getFilterConfig() != null
							&& !filters.getFilter("prenom").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("prenom")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "prenom", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("statut") != null
							&& filters.getFilter("statut").getFilterConfig() != null
							&& !filters.getFilter("statut").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("statut")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "statut", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("dateNaissance") != null
							&& filters.getFilter("dateNaissance")
									.getFilterConfig() != null
							&& !filters.getFilter("dateNaissance")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"dateNaissance").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "dateNaissance", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("lieuNaissance") != null
							&& filters.getFilter("lieuNaissance")
									.getFilterConfig() != null
							&& !filters.getFilter("lieuNaissance")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"lieuNaissance").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "lieuNaissance", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("dateDeces") != null
							&& filters.getFilter("dateDeces").getFilterConfig() != null
							&& !filters.getFilter("dateDeces")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("dateDeces")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "dateDeces", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("lieuDeces") != null
							&& filters.getFilter("lieuDeces").getFilterConfig() != null
							&& !filters.getFilter("lieuDeces")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("lieuDeces")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "lieuDeces", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("heureNaissance") != null
							&& filters.getFilter("heureNaissance")
									.getFilterConfig() != null
							&& !filters.getFilter("heureNaissance")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"heureNaissance").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "heureNaissance", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("heureDeces") != null
							&& filters.getFilter("heureDeces")
									.getFilterConfig() != null
							&& !filters.getFilter("heureDeces")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("heureDeces")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "heureDeces", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("causeDeces") != null
							&& filters.getFilter("causeDeces")
									.getFilterConfig() != null
							&& !filters.getFilter("causeDeces")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("causeDeces")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "causeDeces", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("telephone1") != null
							&& filters.getFilter("telephone1")
									.getFilterConfig() != null
							&& !filters.getFilter("telephone1")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("telephone1")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "telephone1", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("telephone2") != null
							&& filters.getFilter("telephone2")
									.getFilterConfig() != null
							&& !filters.getFilter("telephone2")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("telephone2")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "telephone2", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("nomResponsable1") != null
							&& filters.getFilter("nomResponsable1")
									.getFilterConfig() != null
							&& !filters.getFilter("nomResponsable1")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"nomResponsable1").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "nomResponsable1", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("prenomResponsable1") != null
							&& filters.getFilter("prenomResponsable1")
									.getFilterConfig() != null
							&& !filters.getFilter("prenomResponsable1")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"prenomResponsable1").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "prenomResponsable1",
										fc.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("courriel") != null
							&& filters.getFilter("courriel").getFilterConfig() != null
							&& !filters.getFilter("courriel").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("courriel")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "courriel", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("courrielResponsable1") != null
							&& filters.getFilter("courrielResponsable1")
									.getFilterConfig() != null
							&& !filters.getFilter("courrielResponsable1")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"courrielResponsable1").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(),
										"courrielResponsable1", fc.getType(),
										fc.getValue()));
							}
						}
					}
					if (filters.getFilter("telephone1Responsable1") != null
							&& filters.getFilter("telephone1Responsable1")
									.getFilterConfig() != null
							&& !filters.getFilter("telephone1Responsable1")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"telephone1Responsable1").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(),
										"telephone1Responsable1", fc.getType(),
										fc.getValue()));
							}
						}
					}
					if (filters.getFilter("telephone2Responsable1") != null
							&& filters.getFilter("telephone2Responsable1")
									.getFilterConfig() != null
							&& !filters.getFilter("telephone2Responsable1")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"telephone2Responsable1").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(),
										"telephone2Responsable1", fc.getType(),
										fc.getValue()));
							}
						}
					}
					if (filters.getFilter("nomPere") != null
							&& filters.getFilter("nomPere").getFilterConfig() != null
							&& !filters.getFilter("nomPere").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("nomPere")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "nomPere", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("nomMere") != null
							&& filters.getFilter("nomMere").getFilterConfig() != null
							&& !filters.getFilter("nomMere").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("nomMere")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "nomMere", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("etatCivil") != null
							&& filters.getFilter("etatCivil").getFilterConfig() != null
							&& !filters.getFilter("etatCivil")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("etatCivil")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "etatCivil", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("nomEpoux") != null
							&& filters.getFilter("nomEpoux").getFilterConfig() != null
							&& !filters.getFilter("nomEpoux").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("nomEpoux")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "nomEpoux", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("prenomEpoux") != null
							&& filters.getFilter("prenomEpoux")
									.getFilterConfig() != null
							&& !filters.getFilter("prenomEpoux")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("prenomEpoux")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "prenomEpoux", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("telephoneEpoux") != null
							&& filters.getFilter("telephoneEpoux")
									.getFilterConfig() != null
							&& !filters.getFilter("telephoneEpoux")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"telephoneEpoux").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "telephoneEpoux", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("courrielEpoux") != null
							&& filters.getFilter("courrielEpoux")
									.getFilterConfig() != null
							&& !filters.getFilter("courrielEpoux")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"courrielEpoux").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "courrielEpoux", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("prenomPere") != null
							&& filters.getFilter("prenomPere")
									.getFilterConfig() != null
							&& !filters.getFilter("prenomPere")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("prenomPere")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "prenomPere", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("prenomMere") != null
							&& filters.getFilter("prenomMere")
									.getFilterConfig() != null
							&& !filters.getFilter("prenomMere")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("prenomMere")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "prenomMere", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("pereId") != null
							&& filters.getFilter("pereId").getFilterConfig() != null
							&& !filters.getFilter("pereId").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("pereId")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "pereId", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("mereId") != null
							&& filters.getFilter("mereId").getFilterConfig() != null
							&& !filters.getFilter("mereId").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("mereId")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "mereId", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("epouxId") != null
							&& filters.getFilter("epouxId").getFilterConfig() != null
							&& !filters.getFilter("epouxId").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("epouxId")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "epouxId", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("nationalite") != null
							&& filters.getFilter("nationalite")
									.getFilterConfig() != null
							&& !filters.getFilter("nationalite")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("nationalite")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "nationalite", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("paysResidence") != null
							&& filters.getFilter("paysResidence")
									.getFilterConfig() != null
							&& !filters.getFilter("paysResidence")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"paysResidence").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "paysResidence", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("adresse") != null
							&& filters.getFilter("adresse").getFilterConfig() != null
							&& !filters.getFilter("adresse").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("adresse")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "adresse", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("sexe") != null
							&& filters.getFilter("sexe").getFilterConfig() != null
							&& !filters.getFilter("sexe").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("sexe")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "sexe", fc.getType(),
										fc.getValue()));
							}
						}
					}
					if (filters.getFilter("typeIdentication") != null
							&& filters.getFilter("typeIdentication")
									.getFilterConfig() != null
							&& !filters.getFilter("typeIdentication")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"typeIdentication").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "typeIdentication",
										fc.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("noIdentification") != null
							&& filters.getFilter("noIdentification")
									.getFilterConfig() != null
							&& !filters.getFilter("noIdentification")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"noIdentification").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "noIdentification",
										fc.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("paysIdentifcation") != null
							&& filters.getFilter("paysIdentifcation")
									.getFilterConfig() != null
							&& !filters.getFilter("paysIdentifcation")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"paysIdentifcation").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "paysIdentifcation",
										fc.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("nomEnfants") != null
							&& filters.getFilter("nomEnfants")
									.getFilterConfig() != null
							&& !filters.getFilter("nomEnfants")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("nomEnfants")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "nomEnfants", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("lienParenteResponsable1") != null
							&& filters.getFilter("lienParenteResponsable1")
									.getFilterConfig() != null
							&& !filters.getFilter("lienParenteResponsable1")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"lienParenteResponsable1").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(),
										"lienParenteResponsable1",
										fc.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("telephonePere") != null
							&& filters.getFilter("telephonePere")
									.getFilterConfig() != null
							&& !filters.getFilter("telephonePere")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"telephonePere").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "telephonePere", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("telephoneMere") != null
							&& filters.getFilter("telephoneMere")
									.getFilterConfig() != null
							&& !filters.getFilter("telephoneMere")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"telephoneMere").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "telephoneMere", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("nomResponsable2") != null
							&& filters.getFilter("nomResponsable2")
									.getFilterConfig() != null
							&& !filters.getFilter("nomResponsable2")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"nomResponsable2").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "nomResponsable2", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("prenomResponsable2") != null
							&& filters.getFilter("prenomResponsable2")
									.getFilterConfig() != null
							&& !filters.getFilter("prenomResponsable2")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"prenomResponsable2").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "prenomResponsable2",
										fc.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("courrielResponsable2") != null
							&& filters.getFilter("courrielResponsable2")
									.getFilterConfig() != null
							&& !filters.getFilter("courrielResponsable2")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"courrielResponsable2").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(),
										"courrielResponsable2", fc.getType(),
										fc.getValue()));
							}
						}
					}
					if (filters.getFilter("telephone1Responsable2") != null
							&& filters.getFilter("telephone1Responsable2")
									.getFilterConfig() != null
							&& !filters.getFilter("telephone1Responsable2")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"telephone1Responsable2").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(),
										"telephone1Responsable2", fc.getType(),
										fc.getValue()));
							}
						}
					}
					if (filters.getFilter("telephone2Responsable2") != null
							&& filters.getFilter("telephone2Responsable2")
									.getFilterConfig() != null
							&& !filters.getFilter("telephone2Responsable2")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"telephone2Responsable2").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(),
										"telephone2Responsable2", fc.getType(),
										fc.getValue()));
							}
						}
					}
					if (filters.getFilter("adresseResponsable1") != null
							&& filters.getFilter("adresseResponsable1")
									.getFilterConfig() != null
							&& !filters.getFilter("adresseResponsable1")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"adresseResponsable1").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(),
										"adresseResponsable1", fc.getType(), fc
												.getValue()));
							}
						}
					}
					if (filters.getFilter("adresseResponsable2") != null
							&& filters.getFilter("adresseResponsable2")
									.getFilterConfig() != null
							&& !filters.getFilter("adresseResponsable2")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter(
								"adresseResponsable2").getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(),
										"adresseResponsable2", fc.getType(), fc
												.getValue()));
							}
						}
					}
					if (filters.getFilter("taille") != null
							&& filters.getFilter("taille").getFilterConfig() != null
							&& !filters.getFilter("taille").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("taille")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "taille", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("poids") != null
							&& filters.getFilter("poids").getFilterConfig() != null
							&& !filters.getFilter("poids").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("poids")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "poids",
										fc.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("zone") != null
							&& filters.getFilter("zone").getFilterConfig() != null
							&& !filters.getFilter("zone").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("zone")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "zone", fc.getType(),
										fc.getValue()));
							}
						}
					}
					if (filters.getFilter("commune") != null
							&& filters.getFilter("commune").getFilterConfig() != null
							&& !filters.getFilter("commune").getFilterConfig()
									.isEmpty()) {
						for (FilterConfig fc : filters.getFilter("commune")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "commune", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("departement") != null
							&& filters.getFilter("departement")
									.getFilterConfig() != null
							&& !filters.getFilter("departement")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("departement")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "departement", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("createdBy") != null
							&& filters.getFilter("createdBy").getFilterConfig() != null
							&& !filters.getFilter("createdBy")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("createdBy")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "createdBy", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("createdOn") != null
							&& filters.getFilter("createdOn").getFilterConfig() != null
							&& !filters.getFilter("createdOn")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("createdOn")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "createdOn", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("updatedBy") != null
							&& filters.getFilter("updatedBy").getFilterConfig() != null
							&& !filters.getFilter("updatedBy")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("updatedBy")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "updatedBy", fc
										.getType(), fc.getValue()));
							}
						}
					}
					if (filters.getFilter("updatedOn") != null
							&& filters.getFilter("updatedOn").getFilterConfig() != null
							&& !filters.getFilter("updatedOn")
									.getFilterConfig().isEmpty()) {
						for (FilterConfig fc : filters.getFilter("updatedOn")
								.getFilterConfig()) {
							if (fc.getValue() != null) {
								wrapper.addFilter(new XFilter(fc
										.getComparison(), "updatedOn", fc
										.getType(), fc.getValue()));
							}
						}
					}
					presenter.setFilters(wrapper);
					UserRequestCallbackAdapter<Patient> urc = new UserRequestCallbackAdapter<Patient>();
					urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
					urc.setAsyncCallback(callback);
					urc.setLoadConfig(loadConfig);
					presenter.getEventBus().executePatient(
							ActionCommand.COLUMNAR_SEARCH, urc);
					showInfoBanner(!userHideBannerInfo);
				}
			};
			pagingLoader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<Patient>>(
					proxy) {
				protected FilterPagingLoadConfig newLoadConfig() {
					return new FilterPagingLoadConfigBean();
				}
			};
			pagingLoader.setRemoteSort(true);
			pagingLoader
					.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, Patient, PagingLoadResult<Patient>>(
							listStore));
			grid.setLoader(pagingLoader);

			new Timer() {
				@Override
				public void run() {
					pagingLoader.load();
				}
			}.schedule(100);
			pagingLoader.setRemoteSort(true);
			toolsBar.bind(pagingLoader);
		} catch (Exception ex) {
			Window.alert(messages.unexpectedError_searchRecords());
		}
	}

	protected void resetForm() {
		RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Patient>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Patient>>() {
			@Override
			public void load(FilterPagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<Patient>> callback) {
				UserRequestCallbackAdapter<Patient> urc = new UserRequestCallbackAdapter<Patient>() {
					@Override
					public void onSingleOperationSuccessed(Patient model) {
						hideProgressBar();
					}

					@Override
					public void onMultipleOperationsFailed(List<Patient> model,
							Throwable cause) {
						hideProgressBar();
					}
				};
				urc.setAsyncCallback(callback);
				urc.setLoadConfig(loadConfig);
				urc.setPageSize(toolsBar.getPageSize());
				urc.setRequestDescriptor(new RequestDescriptor(ENTITY_NAME));
				presenter.getEventBus().executePatient(
						ActionCommand.RESET_GRID, urc);
			}
		};
		pagingLoader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<Patient>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		pagingLoader.setRemoteSort(true);
		pagingLoader
				.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, Patient, PagingLoadResult<Patient>>(
						listStore));
		grid.setLoader(pagingLoader);

		new Timer() {
			@Override
			public void run() {
				pagingLoader.load();
			}
		}.schedule(100);
		userHideBannerInfo = true;
		showInfoBanner(!userHideBannerInfo);
		pagingLoader.setRemoteSort(true);
		toolsBar.bind(pagingLoader);
		formReset();
	}

	private static GxtUiBinder gxtUiBinder = GWT.create(GxtUiBinder.class);

	private PagingLoader<FilterPagingLoadConfig, PagingLoadResult<Patient>> pagingLoader = null;
	private RowNumberer<Patient> numberer;

	@Override
	public Widget asWidget() {
		ENTITY_NAME = "Patient";
		PatientPropertiesAccess propertiesAccess = GWT
				.create(PatientPropertiesAccess.class);
		listStore = new ListStore<Patient>(propertiesAccess.key());
		this.presenter.setListStore(listStore);
		IdentityValueProvider<Patient> identity = new IdentityValueProvider<Patient>();
		numberer = new RowNumberer<Patient>(identity);
		list = new ArrayList<ColumnConfig<Patient, ?>>();
		List<ColumnConfig<Patient, ?>> list = new ArrayList<ColumnConfig<Patient, ?>>();

		ColumnConfig<Patient, String> nomColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.nom(), 20, messages.nom());
		list.add(nomColumn);
		ColumnConfig<Patient, String> prenomColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.prenom(), 20, messages.prenom());
		list.add(prenomColumn);
		ColumnConfig<Patient, String> statutColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.statut(), 20, messages.statut());
		list.add(statutColumn);
		ColumnConfig<Patient, String> dateNaissanceColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.dateNaissance(), 20, messages.dateNaissance());
		list.add(dateNaissanceColumn);
		ColumnConfig<Patient, String> lieuNaissanceColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.lieuNaissance(), 18, messages.lieuNaissance());
		list.add(lieuNaissanceColumn);
		ColumnConfig<Patient, Date> dateDecesColumn = new ColumnConfig<Patient, Date>(
				propertiesAccess.dateDeces(), 10, messages.dateDeces());
		list.add(dateDecesColumn);
		ColumnConfig<Patient, String> lieuDecesColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.lieuDeces(), 20, messages.lieuDeces());
		list.add(lieuDecesColumn);
		ColumnConfig<Patient, String> heureNaissanceColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.heureNaissance(), 20,
				messages.heureNaissance());
		list.add(heureNaissanceColumn);
		ColumnConfig<Patient, String> heureDecesColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.heureDeces(), 20, messages.heureDeces());
		list.add(heureDecesColumn);
		ColumnConfig<Patient, String> causeDecesColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.causeDeces(), 20, messages.causeDeces());
		list.add(causeDecesColumn);
		ColumnConfig<Patient, String> telephone1Column = new ColumnConfig<Patient, String>(
				propertiesAccess.telephone1(), 20, messages.telephone1());
		list.add(telephone1Column);
		ColumnConfig<Patient, String> telephone2Column = new ColumnConfig<Patient, String>(
				propertiesAccess.telephone2(), 20, messages.telephone2());
		list.add(telephone2Column);
		ColumnConfig<Patient, String> nomResponsable1Column = new ColumnConfig<Patient, String>(
				propertiesAccess.nomResponsable1(), 20,
				messages.nomResponsable1());
		list.add(nomResponsable1Column);
		ColumnConfig<Patient, String> prenomResponsable1Column = new ColumnConfig<Patient, String>(
				propertiesAccess.prenomResponsable1(), 20,
				messages.prenomResponsable1());
		list.add(prenomResponsable1Column);
		ColumnConfig<Patient, String> courrielColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.courriel(), 20, messages.courriel());
		list.add(courrielColumn);
		ColumnConfig<Patient, String> courrielResponsable1Column = new ColumnConfig<Patient, String>(
				propertiesAccess.courrielResponsable1(), 20,
				messages.courrielResponsable1());
		list.add(courrielResponsable1Column);
		ColumnConfig<Patient, String> telephone1Responsable1Column = new ColumnConfig<Patient, String>(
				propertiesAccess.telephone1Responsable1(), 20,
				messages.telephone1Responsable1());
		list.add(telephone1Responsable1Column);
		ColumnConfig<Patient, String> telephone2Responsable1Column = new ColumnConfig<Patient, String>(
				propertiesAccess.telephone2Responsable1(), 20,
				messages.telephone2Responsable1());
		list.add(telephone2Responsable1Column);
		ColumnConfig<Patient, String> nomPereColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.nomPere(), 20, messages.nomPere());
		list.add(nomPereColumn);
		ColumnConfig<Patient, String> nomMereColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.nomMere(), 20, messages.nomMere());
		list.add(nomMereColumn);
		ColumnConfig<Patient, String> etatCivilColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.etatCivil(), 20, messages.etatCivil());
		list.add(etatCivilColumn);
		ColumnConfig<Patient, String> nomEpouxColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.nomEpoux(), 20, messages.nomEpoux());
		list.add(nomEpouxColumn);
		ColumnConfig<Patient, String> prenomEpouxColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.prenomEpoux(), 20, messages.prenomEpoux());
		list.add(prenomEpouxColumn);
		ColumnConfig<Patient, String> telephoneEpouxColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.telephoneEpoux(), 20,
				messages.telephoneEpoux());
		list.add(telephoneEpouxColumn);
		ColumnConfig<Patient, String> courrielEpouxColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.courrielEpoux(), 20, messages.courrielEpoux());
		list.add(courrielEpouxColumn);
		ColumnConfig<Patient, String> prenomPereColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.prenomPere(), 20, messages.prenomPere());
		list.add(prenomPereColumn);
		ColumnConfig<Patient, String> prenomMereColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.prenomMere(), 20, messages.prenomMere());
		list.add(prenomMereColumn);
		ColumnConfig<Patient, Integer> pereIdColumn = new ColumnConfig<Patient, Integer>(
				propertiesAccess.pereId(), 10, messages.pereId());
		list.add(pereIdColumn);
		ColumnConfig<Patient, Integer> mereIdColumn = new ColumnConfig<Patient, Integer>(
				propertiesAccess.mereId(), 10, messages.mereId());
		list.add(mereIdColumn);
		ColumnConfig<Patient, Integer> epouxIdColumn = new ColumnConfig<Patient, Integer>(
				propertiesAccess.epouxId(), 10, messages.epouxId());
		list.add(epouxIdColumn);
		ColumnConfig<Patient, String> nationaliteColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.nationalite(), 20, messages.nationalite());
		list.add(nationaliteColumn);
		ColumnConfig<Patient, String> paysResidenceColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.paysResidence(), 20, messages.paysResidence());
		list.add(paysResidenceColumn);
		ColumnConfig<Patient, String> adresseColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.adresse(), 20, messages.adresse());
		list.add(adresseColumn);
		ColumnConfig<Patient, String> sexeColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.sexe(), 20, messages.sexe());
		list.add(sexeColumn);
		ColumnConfig<Patient, String> typeIdenticationColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.typeIdentication(), 20,
				messages.typeIdentication());
		list.add(typeIdenticationColumn);
		ColumnConfig<Patient, String> noIdentificationColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.noIdentification(), 20,
				messages.noIdentification());
		list.add(noIdentificationColumn);
		ColumnConfig<Patient, String> paysIdentifcationColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.paysIdentifcation(), 20,
				messages.paysIdentifcation());
		list.add(paysIdentifcationColumn);
		ColumnConfig<Patient, Integer> nomEnfantsColumn = new ColumnConfig<Patient, Integer>(
				propertiesAccess.nomEnfants(), 10, messages.nomEnfants());
		list.add(nomEnfantsColumn);
		ColumnConfig<Patient, String> lienParenteResponsable1Column = new ColumnConfig<Patient, String>(
				propertiesAccess.lienParenteResponsable1(), 20,
				messages.lienParenteResponsable1());
		list.add(lienParenteResponsable1Column);
		ColumnConfig<Patient, String> telephonePereColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.telephonePere(), 20, messages.telephonePere());
		list.add(telephonePereColumn);
		ColumnConfig<Patient, String> telephoneMereColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.telephoneMere(), 20, messages.telephoneMere());
		list.add(telephoneMereColumn);
		ColumnConfig<Patient, String> nomResponsable2Column = new ColumnConfig<Patient, String>(
				propertiesAccess.nomResponsable2(), 20,
				messages.nomResponsable2());
		list.add(nomResponsable2Column);
		ColumnConfig<Patient, String> prenomResponsable2Column = new ColumnConfig<Patient, String>(
				propertiesAccess.prenomResponsable2(), 20,
				messages.prenomResponsable2());
		list.add(prenomResponsable2Column);
		ColumnConfig<Patient, String> courrielResponsable2Column = new ColumnConfig<Patient, String>(
				propertiesAccess.courrielResponsable2(), 18,
				messages.courrielResponsable2());
		list.add(courrielResponsable2Column);
		ColumnConfig<Patient, String> telephone1Responsable2Column = new ColumnConfig<Patient, String>(
				propertiesAccess.telephone1Responsable2(), 20,
				messages.telephone1Responsable2());
		list.add(telephone1Responsable2Column);
		ColumnConfig<Patient, String> telephone2Responsable2Column = new ColumnConfig<Patient, String>(
				propertiesAccess.telephone2Responsable2(), 20,
				messages.telephone2Responsable2());
		list.add(telephone2Responsable2Column);
		ColumnConfig<Patient, String> adresseResponsable1Column = new ColumnConfig<Patient, String>(
				propertiesAccess.adresseResponsable1(), 20,
				messages.adresseResponsable1());
		list.add(adresseResponsable1Column);
		ColumnConfig<Patient, String> adresseResponsable2Column = new ColumnConfig<Patient, String>(
				propertiesAccess.adresseResponsable2(), 20,
				messages.adresseResponsable2());
		list.add(adresseResponsable2Column);
		ColumnConfig<Patient, String> tailleColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.taille(), 20, messages.taille());
		list.add(tailleColumn);
		ColumnConfig<Patient, String> poidsColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.poids(), 20, messages.poids());
		list.add(poidsColumn);
		ColumnConfig<Patient, String> zoneColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.zone(), 20, messages.zone());
		list.add(zoneColumn);
		ColumnConfig<Patient, String> communeColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.commune(), 20, messages.commune());
		list.add(communeColumn);
		ColumnConfig<Patient, String> departementColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.departement(), 20, messages.departement());
		list.add(departementColumn);
		ColumnConfig<Patient, String> createdByColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.createdBy(), 30, "createdBy");
		list.add(createdByColumn);
		ColumnConfig<Patient, Date> createdOnColumn = new ColumnConfig<Patient, Date>(
				propertiesAccess.createdOn(), 10, "createdOn");
		list.add(createdOnColumn);
		ColumnConfig<Patient, String> updatedByColumn = new ColumnConfig<Patient, String>(
				propertiesAccess.updatedBy(), 30, "updatedBy");
		list.add(updatedByColumn);
		ColumnConfig<Patient, Date> updatedOnColumn = new ColumnConfig<Patient, Date>(
				propertiesAccess.updatedOn(), 10, "updatedOn");
		list.add(updatedOnColumn);
		columnModel = new ColumnModel<Patient>(list);
		widget = gxtUiBinder.createAndBindUi(this);
		panel.addDomHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				formUpdated();
			}
		}, KeyPressEvent.getType());
		showInfoBanner(bannerInfoIsShowed);
		// Apply editor to grid
		final GridEditing<Patient> editing = new GridInlineEditing<Patient>(
				grid);
		editing.addBeforeStartEditHandler(new BeforeStartEditHandler<Patient>() {
			@Override
			public void onBeforeStartEdit(BeforeStartEditEvent<Patient> event) {
				editing.completeEditing();
				int row = event.getEditCell().getRow();
				Patient model = listStore.get(row);
				model.setUpdating(true);
			}
		});
		editing.addCompleteEditHandler(new CompleteEditHandler<Patient>() {
			@Override
			public void onCompleteEdit(CompleteEditEvent<Patient> event) {
				int row = event.getEditCell().getRow();
				Patient model = listStore.get(row);
				model.setUpdating(false);
			}
		});

		TextField nomField = new TextField();
		nomField.addValidator(new MaxLengthValidator(20));
		nomField.setAllowBlank(false);
		editing.addEditor(nomColumn, nomField);

		TextField prenomField = new TextField();
		prenomField.addValidator(new MaxLengthValidator(20));
		prenomField.setAllowBlank(false);
		editing.addEditor(prenomColumn, prenomField);

		TextField statutField = new TextField();
		statutField.addValidator(new MaxLengthValidator(20));
		statutField.setAllowBlank(false);
		editing.addEditor(statutColumn, statutField);

		TextField dateNaissanceField = new TextField();
		dateNaissanceField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(dateNaissanceColumn, dateNaissanceField);

		TextField lieuNaissanceField = new TextField();
		lieuNaissanceField.addValidator(new MaxLengthValidator(18));
		editing.addEditor(lieuNaissanceColumn, lieuNaissanceField);

		DateField dateDecesField = new DateField(datePropertyEditor);
		editing.addEditor(dateDecesColumn, dateDecesField);

		TextField lieuDecesField = new TextField();
		lieuDecesField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(lieuDecesColumn, lieuDecesField);

		TextField heureNaissanceField = new TextField();
		heureNaissanceField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(heureNaissanceColumn, heureNaissanceField);

		TextField heureDecesField = new TextField();
		heureDecesField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(heureDecesColumn, heureDecesField);

		TextField causeDecesField = new TextField();
		causeDecesField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(causeDecesColumn, causeDecesField);

		TextField telephone1Field = new TextField();
		telephone1Field.addValidator(new MaxLengthValidator(20));
		editing.addEditor(telephone1Column, telephone1Field);

		TextField telephone2Field = new TextField();
		telephone2Field.addValidator(new MaxLengthValidator(20));
		editing.addEditor(telephone2Column, telephone2Field);

		TextField nomResponsable1Field = new TextField();
		nomResponsable1Field.addValidator(new MaxLengthValidator(20));
		editing.addEditor(nomResponsable1Column, nomResponsable1Field);

		TextField prenomResponsable1Field = new TextField();
		prenomResponsable1Field.addValidator(new MaxLengthValidator(20));
		editing.addEditor(prenomResponsable1Column, prenomResponsable1Field);

		TextField courrielField = new TextField();
		courrielField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(courrielColumn, courrielField);

		TextField courrielResponsable1Field = new TextField();
		courrielResponsable1Field.addValidator(new MaxLengthValidator(20));
		editing.addEditor(courrielResponsable1Column, courrielResponsable1Field);

		TextField telephone1Responsable1Field = new TextField();
		telephone1Responsable1Field.addValidator(new MaxLengthValidator(20));
		editing.addEditor(telephone1Responsable1Column,
				telephone1Responsable1Field);

		TextField telephone2Responsable1Field = new TextField();
		telephone2Responsable1Field.addValidator(new MaxLengthValidator(20));
		editing.addEditor(telephone2Responsable1Column,
				telephone2Responsable1Field);

		TextField nomPereField = new TextField();
		nomPereField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(nomPereColumn, nomPereField);

		TextField nomMereField = new TextField();
		nomMereField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(nomMereColumn, nomMereField);

		TextField etatCivilField = new TextField();
		etatCivilField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(etatCivilColumn, etatCivilField);

		TextField nomEpouxField = new TextField();
		nomEpouxField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(nomEpouxColumn, nomEpouxField);

		TextField prenomEpouxField = new TextField();
		prenomEpouxField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(prenomEpouxColumn, prenomEpouxField);

		TextField telephoneEpouxField = new TextField();
		telephoneEpouxField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(telephoneEpouxColumn, telephoneEpouxField);

		TextField courrielEpouxField = new TextField();
		courrielEpouxField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(courrielEpouxColumn, courrielEpouxField);

		TextField prenomPereField = new TextField();
		prenomPereField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(prenomPereColumn, prenomPereField);

		TextField prenomMereField = new TextField();
		prenomMereField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(prenomMereColumn, prenomMereField);

		NumberField pereIdField = new NumberField(integerPropertyEditor);
		editing.addEditor(pereIdColumn, pereIdField);

		NumberField mereIdField = new NumberField(integerPropertyEditor);
		editing.addEditor(mereIdColumn, mereIdField);

		NumberField epouxIdField = new NumberField(integerPropertyEditor);
		editing.addEditor(epouxIdColumn, epouxIdField);

		TextField nationaliteField = new TextField();
		nationaliteField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(nationaliteColumn, nationaliteField);

		TextField paysResidenceField = new TextField();
		paysResidenceField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(paysResidenceColumn, paysResidenceField);

		TextField adresseField = new TextField();
		adresseField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(adresseColumn, adresseField);

		TextField sexeField = new TextField();
		sexeField.addValidator(new MaxLengthValidator(20));
		sexeField.setAllowBlank(false);
		editing.addEditor(sexeColumn, sexeField);

		TextField typeIdenticationField = new TextField();
		typeIdenticationField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(typeIdenticationColumn, typeIdenticationField);

		TextField noIdentificationField = new TextField();
		noIdentificationField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(noIdentificationColumn, noIdentificationField);

		TextField paysIdentifcationField = new TextField();
		paysIdentifcationField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(paysIdentifcationColumn, paysIdentifcationField);

		NumberField nomEnfantsField = new NumberField(integerPropertyEditor);
		editing.addEditor(nomEnfantsColumn, nomEnfantsField);

		TextField lienParenteResponsable1Field = new TextField();
		lienParenteResponsable1Field.addValidator(new MaxLengthValidator(20));
		editing.addEditor(lienParenteResponsable1Column,
				lienParenteResponsable1Field);

		TextField telephonePereField = new TextField();
		telephonePereField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(telephonePereColumn, telephonePereField);

		TextField telephoneMereField = new TextField();
		telephoneMereField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(telephoneMereColumn, telephoneMereField);

		TextField nomResponsable2Field = new TextField();
		nomResponsable2Field.addValidator(new MaxLengthValidator(20));
		editing.addEditor(nomResponsable2Column, nomResponsable2Field);

		TextField prenomResponsable2Field = new TextField();
		prenomResponsable2Field.addValidator(new MaxLengthValidator(20));
		editing.addEditor(prenomResponsable2Column, prenomResponsable2Field);

		TextField courrielResponsable2Field = new TextField();
		courrielResponsable2Field.addValidator(new MaxLengthValidator(18));
		editing.addEditor(courrielResponsable2Column, courrielResponsable2Field);

		TextField telephone1Responsable2Field = new TextField();
		telephone1Responsable2Field.addValidator(new MaxLengthValidator(20));
		editing.addEditor(telephone1Responsable2Column,
				telephone1Responsable2Field);

		TextField telephone2Responsable2Field = new TextField();
		telephone2Responsable2Field.addValidator(new MaxLengthValidator(20));
		editing.addEditor(telephone2Responsable2Column,
				telephone2Responsable2Field);

		TextField adresseResponsable1Field = new TextField();
		adresseResponsable1Field.addValidator(new MaxLengthValidator(20));
		editing.addEditor(adresseResponsable1Column, adresseResponsable1Field);

		TextField adresseResponsable2Field = new TextField();
		adresseResponsable2Field.addValidator(new MaxLengthValidator(20));
		editing.addEditor(adresseResponsable2Column, adresseResponsable2Field);

		TextField tailleField = new TextField();
		tailleField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(tailleColumn, tailleField);

		TextField poidsField = new TextField();
		poidsField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(poidsColumn, poidsField);

		TextField zoneField = new TextField();
		zoneField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(zoneColumn, zoneField);

		TextField communeField = new TextField();
		communeField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(communeColumn, communeField);

		TextField departementField = new TextField();
		departementField.addValidator(new MaxLengthValidator(20));
		editing.addEditor(departementColumn, departementField);

		toolBarArea.setHeaderVisible(false);
		toolsBar = new EditableGridToolsBar();
		toolsBar.getElement().getStyle().setProperty("borderBottom", "none");
		toolsBar.setPageSize(50);
		toolsBar.initialize(toolsBarAction);
		ToolTipConfig config = new ToolTipConfig();
		config = new ToolTipConfig();
		config.setBodyHtml("Prints the current document");
		config.setTitleHtml("Template Tip");
		config.setMouseOffset(new int[] { 0, 0 });
		config.setAnchor(Side.LEFT);
		config.setRenderer(renderer);
		config.setCloseable(true);
		config.setMaxWidth(415);
		//
		toolsBar.setToolTipConfig(config);
		toolBarArea.add(toolsBar);
		filters = new GridFilters<Patient>();
		listStore
				.addStoreRecordChangeHandler(new StoreRecordChangeHandler<Patient>() {
					@Override
					public void onRecordChange(
							StoreRecordChangeEvent<Patient> event) {
						Patient changedModel = event.getRecord().getModel();
						if (changedModel != null
								&& !"REMOVE".equalsIgnoreCase(changedModel
										.getSTATUS())) {
							if (changedModel.getEntityId() == null) {
								changedModel.setSTATUS("INSERT");
							} else {
								changedModel.setSTATUS("MERGE");
							}
						}
					}
				});

		filters.initPlugin(grid);
		// Add Filters

		StringFilter<Patient> nomFilter = new StringFilter<Patient>(
				propertiesAccess.nom());
		filters.addFilter(nomFilter);
		StringFilter<Patient> prenomFilter = new StringFilter<Patient>(
				propertiesAccess.prenom());
		filters.addFilter(prenomFilter);
		StringFilter<Patient> statutFilter = new StringFilter<Patient>(
				propertiesAccess.statut());
		filters.addFilter(statutFilter);
		StringFilter<Patient> dateNaissanceFilter = new StringFilter<Patient>(
				propertiesAccess.dateNaissance());
		filters.addFilter(dateNaissanceFilter);
		StringFilter<Patient> lieuNaissanceFilter = new StringFilter<Patient>(
				propertiesAccess.lieuNaissance());
		filters.addFilter(lieuNaissanceFilter);
		DateFilter<Patient> dateDecesFilter = new DateFilter<Patient>(
				propertiesAccess.dateDeces());
		filters.addFilter(dateDecesFilter);
		StringFilter<Patient> lieuDecesFilter = new StringFilter<Patient>(
				propertiesAccess.lieuDeces());
		filters.addFilter(lieuDecesFilter);
		StringFilter<Patient> heureNaissanceFilter = new StringFilter<Patient>(
				propertiesAccess.heureNaissance());
		filters.addFilter(heureNaissanceFilter);
		StringFilter<Patient> heureDecesFilter = new StringFilter<Patient>(
				propertiesAccess.heureDeces());
		filters.addFilter(heureDecesFilter);
		StringFilter<Patient> causeDecesFilter = new StringFilter<Patient>(
				propertiesAccess.causeDeces());
		filters.addFilter(causeDecesFilter);
		StringFilter<Patient> telephone1Filter = new StringFilter<Patient>(
				propertiesAccess.telephone1());
		filters.addFilter(telephone1Filter);
		StringFilter<Patient> telephone2Filter = new StringFilter<Patient>(
				propertiesAccess.telephone2());
		filters.addFilter(telephone2Filter);
		StringFilter<Patient> nomResponsable1Filter = new StringFilter<Patient>(
				propertiesAccess.nomResponsable1());
		filters.addFilter(nomResponsable1Filter);
		StringFilter<Patient> prenomResponsable1Filter = new StringFilter<Patient>(
				propertiesAccess.prenomResponsable1());
		filters.addFilter(prenomResponsable1Filter);
		StringFilter<Patient> courrielFilter = new StringFilter<Patient>(
				propertiesAccess.courriel());
		filters.addFilter(courrielFilter);
		StringFilter<Patient> courrielResponsable1Filter = new StringFilter<Patient>(
				propertiesAccess.courrielResponsable1());
		filters.addFilter(courrielResponsable1Filter);
		StringFilter<Patient> telephone1Responsable1Filter = new StringFilter<Patient>(
				propertiesAccess.telephone1Responsable1());
		filters.addFilter(telephone1Responsable1Filter);
		StringFilter<Patient> telephone2Responsable1Filter = new StringFilter<Patient>(
				propertiesAccess.telephone2Responsable1());
		filters.addFilter(telephone2Responsable1Filter);
		StringFilter<Patient> nomPereFilter = new StringFilter<Patient>(
				propertiesAccess.nomPere());
		filters.addFilter(nomPereFilter);
		StringFilter<Patient> nomMereFilter = new StringFilter<Patient>(
				propertiesAccess.nomMere());
		filters.addFilter(nomMereFilter);
		StringFilter<Patient> etatCivilFilter = new StringFilter<Patient>(
				propertiesAccess.etatCivil());
		filters.addFilter(etatCivilFilter);
		StringFilter<Patient> nomEpouxFilter = new StringFilter<Patient>(
				propertiesAccess.nomEpoux());
		filters.addFilter(nomEpouxFilter);
		StringFilter<Patient> prenomEpouxFilter = new StringFilter<Patient>(
				propertiesAccess.prenomEpoux());
		filters.addFilter(prenomEpouxFilter);
		StringFilter<Patient> telephoneEpouxFilter = new StringFilter<Patient>(
				propertiesAccess.telephoneEpoux());
		filters.addFilter(telephoneEpouxFilter);
		StringFilter<Patient> courrielEpouxFilter = new StringFilter<Patient>(
				propertiesAccess.courrielEpoux());
		filters.addFilter(courrielEpouxFilter);
		StringFilter<Patient> prenomPereFilter = new StringFilter<Patient>(
				propertiesAccess.prenomPere());
		filters.addFilter(prenomPereFilter);
		StringFilter<Patient> prenomMereFilter = new StringFilter<Patient>(
				propertiesAccess.prenomMere());
		filters.addFilter(prenomMereFilter);
		NumericFilter<Patient, Integer> pereIdFilter = new NumericFilter<Patient, Integer>(
				propertiesAccess.pereId(), integerPropertyEditor);
		filters.addFilter(pereIdFilter);
		NumericFilter<Patient, Integer> mereIdFilter = new NumericFilter<Patient, Integer>(
				propertiesAccess.mereId(), integerPropertyEditor);
		filters.addFilter(mereIdFilter);
		NumericFilter<Patient, Integer> epouxIdFilter = new NumericFilter<Patient, Integer>(
				propertiesAccess.epouxId(), integerPropertyEditor);
		filters.addFilter(epouxIdFilter);
		StringFilter<Patient> nationaliteFilter = new StringFilter<Patient>(
				propertiesAccess.nationalite());
		filters.addFilter(nationaliteFilter);
		StringFilter<Patient> paysResidenceFilter = new StringFilter<Patient>(
				propertiesAccess.paysResidence());
		filters.addFilter(paysResidenceFilter);
		StringFilter<Patient> adresseFilter = new StringFilter<Patient>(
				propertiesAccess.adresse());
		filters.addFilter(adresseFilter);
		StringFilter<Patient> sexeFilter = new StringFilter<Patient>(
				propertiesAccess.sexe());
		filters.addFilter(sexeFilter);
		StringFilter<Patient> typeIdenticationFilter = new StringFilter<Patient>(
				propertiesAccess.typeIdentication());
		filters.addFilter(typeIdenticationFilter);
		StringFilter<Patient> noIdentificationFilter = new StringFilter<Patient>(
				propertiesAccess.noIdentification());
		filters.addFilter(noIdentificationFilter);
		StringFilter<Patient> paysIdentifcationFilter = new StringFilter<Patient>(
				propertiesAccess.paysIdentifcation());
		filters.addFilter(paysIdentifcationFilter);
		NumericFilter<Patient, Integer> nomEnfantsFilter = new NumericFilter<Patient, Integer>(
				propertiesAccess.nomEnfants(), integerPropertyEditor);
		filters.addFilter(nomEnfantsFilter);
		StringFilter<Patient> lienParenteResponsable1Filter = new StringFilter<Patient>(
				propertiesAccess.lienParenteResponsable1());
		filters.addFilter(lienParenteResponsable1Filter);
		StringFilter<Patient> telephonePereFilter = new StringFilter<Patient>(
				propertiesAccess.telephonePere());
		filters.addFilter(telephonePereFilter);
		StringFilter<Patient> telephoneMereFilter = new StringFilter<Patient>(
				propertiesAccess.telephoneMere());
		filters.addFilter(telephoneMereFilter);
		StringFilter<Patient> nomResponsable2Filter = new StringFilter<Patient>(
				propertiesAccess.nomResponsable2());
		filters.addFilter(nomResponsable2Filter);
		StringFilter<Patient> prenomResponsable2Filter = new StringFilter<Patient>(
				propertiesAccess.prenomResponsable2());
		filters.addFilter(prenomResponsable2Filter);
		StringFilter<Patient> courrielResponsable2Filter = new StringFilter<Patient>(
				propertiesAccess.courrielResponsable2());
		filters.addFilter(courrielResponsable2Filter);
		StringFilter<Patient> telephone1Responsable2Filter = new StringFilter<Patient>(
				propertiesAccess.telephone1Responsable2());
		filters.addFilter(telephone1Responsable2Filter);
		StringFilter<Patient> telephone2Responsable2Filter = new StringFilter<Patient>(
				propertiesAccess.telephone2Responsable2());
		filters.addFilter(telephone2Responsable2Filter);
		StringFilter<Patient> adresseResponsable1Filter = new StringFilter<Patient>(
				propertiesAccess.adresseResponsable1());
		filters.addFilter(adresseResponsable1Filter);
		StringFilter<Patient> adresseResponsable2Filter = new StringFilter<Patient>(
				propertiesAccess.adresseResponsable2());
		filters.addFilter(adresseResponsable2Filter);
		StringFilter<Patient> tailleFilter = new StringFilter<Patient>(
				propertiesAccess.taille());
		filters.addFilter(tailleFilter);
		StringFilter<Patient> poidsFilter = new StringFilter<Patient>(
				propertiesAccess.poids());
		filters.addFilter(poidsFilter);
		StringFilter<Patient> zoneFilter = new StringFilter<Patient>(
				propertiesAccess.zone());
		filters.addFilter(zoneFilter);
		StringFilter<Patient> communeFilter = new StringFilter<Patient>(
				propertiesAccess.commune());
		filters.addFilter(communeFilter);
		StringFilter<Patient> departementFilter = new StringFilter<Patient>(
				propertiesAccess.departement());
		filters.addFilter(departementFilter);
		StringFilter<Patient> createdByFilter = new StringFilter<Patient>(
				propertiesAccess.createdBy());
		filters.addFilter(createdByFilter);
		DateFilter<Patient> createdOnFilter = new DateFilter<Patient>(
				propertiesAccess.createdOn());
		filters.addFilter(createdOnFilter);
		StringFilter<Patient> updatedByFilter = new StringFilter<Patient>(
				propertiesAccess.updatedBy());
		filters.addFilter(updatedByFilter);
		DateFilter<Patient> updatedOnFilter = new DateFilter<Patient>(
				propertiesAccess.updatedOn());
		filters.addFilter(updatedOnFilter);
		numberer.initPlugin(grid);
		grid.setLoadMask(true);
		grid.setColumnReordering(true);
		grid.getView().setStripeRows(true);
		grid.getView().setTrackMouseOver(false);
		grid.getView().setAutoFill(true);
		GridDropTarget<Patient> target = new GridDropTarget<Patient>(grid);
		target.setAllowSelfAsSource(true);
		target.setFeedback(Feedback.INSERT);
		htmlMessage.getElement().getStyle().setBackgroundColor("#F6F983");
		htmlMessage.getElement().getStyle().setBorderColor("#2106C2");
		htmlMessage.getElement().getStyle().setBorderWidth(3, Unit.PX);
		htmlMessage.getElement().getStyle().setColor("#2106C2");
		htmlMessage.getElement().getStyle().setBorderColor("#4F81BD");
		ViewUtils.unNotify(htmlMessage);
		grid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				Patient model = grid.getSelectionModel().getSelectedItem();
				ViewUtils.showAuditInfos(htmlMessage,
						model.getGlobalAuditInfo());
			}
		});
		grid.getView().setViewConfig(new GridViewConfig<Patient>() {
			@Override
			public String getColStyle(Patient model,
					ValueProvider<? super Patient, ?> valueProvider,
					int rowIndex, int colIndex) {
				return null;
			}

			@Override
			public String getRowStyle(Patient model, int rowIndex) {
				if (model.getErrorMessage() != null
						&& model.getErrorMessage().getMessage() != null
						&& !model.getErrorMessage().getMessage().trim()
								.isEmpty()) {
					return "errorGridRow";
				} else if ("REMOVE".equalsIgnoreCase(model.getSTATUS())) {
					return "removedGridRow";
				} else {
					return "gridRow";
				}
			}
		});
		return widget;
	}

	public IWidget getViewWidget() {
		return this;
	}

	@Override
	public Widget getWidget() {
		// TODO Auto-generated method stub
		return asWidget();
	}

	@Override
	public void setPresenter(IPresenter p) {
		this.presenter = (PatientPresenter) p;
	}

	@Override
	public IPresenter getPresenter() {
		return this.presenter;
	}

	@Override
	public void updateView(Patient model) {
		Info.display(messages.message(), messages.finished());
		if (box != null) {
			box.hide();
			box = null;
		}
	}

	@Override
	public void updateView(List<Patient> models) {
		try {
			if (models != null) {
				boolean allOperationsSuccess = true;
				for (Patient model : models) {
					boolean success = true;
					if (model != null) {
						if (model.getErrorMessage() != null) {
							success = false;
							if (allOperationsSuccess) {
								allOperationsSuccess = false;
							}
						} else {
							success = true;
						}
						if ("REMOVE".equalsIgnoreCase(model.getSTATUS())) {
							if (success) {
								listStore.remove(model);
								presenter.removeModelToBeRemoved(model);
							} else {
								Patient oldModel = listStore
										.findModelWithKey(listStore
												.getKeyProvider().getKey(model));
								if (oldModel != null) {
									oldModel.merge(model);
								}
							}
						} else {
							Patient oldModel = listStore
									.findModelWithKey(listStore
											.getKeyProvider().getKey(model));
							if (success) {
								model.setErrorMessage(null);
								model.setSTATUS("IGNORE");
							}
							if (oldModel != null) {
								oldModel.merge(model);
							}
						}
					}
				}
				if (allOperationsSuccess) {
					formReset();
					presenter.clearModelsToBeRemoved();
					listStore.commitChanges();
				}
				grid.getView().refresh(false);
			}
		} catch (Exception ex) {
			ViewUtils.notify(htmlMessage, new SystemMessage(ex.getMessage()));
			Window.alert(ex.getMessage());
			ex.printStackTrace(System.out);
		} finally {
			Info.display(messages.message(), messages.finished());

			if (box != null) {
				box.hide();
				box = null;
			}
		}
	}

	@Override
	public Patient updateModel(Patient model) {
		// TODO Auto-generated method stub
		return null;
	}

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
	public void onFormCleared() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onPersistenceSuccessed() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onDataChanged(Patient model, boolean reloadDetails,
			boolean setBtnsPersistVisible) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onActionFailure(Throwable caught, String message) {
		if (message != null) {
			Window.alert(message);
		} else {
			Window.alert(caught.getMessage() != null ? caught.getMessage()
					: caught.toString());
		}
		if (box != null) {
			box.hide();
			box = null;
		}
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

	@Override
	public void setViewCallback(ViewCallback callback) {
		this.callback = callback;
	}

	@Override
	public void formReset() {
		presenter.removeUpdatedZone(widget.getTitle());
		callback.viewReset(presenter.getHeaderTitle() != null ? presenter
				.getHeaderTitle() : widget.getTitle());
		presenter.setUpdatePending(false);
	}

	@Override
	public void formUpdated() {
		if (!presenter.isUpdatePending()) {
			callback.viewChanged(presenter.getHeaderTitle() != null ? presenter
					.getHeaderTitle() : widget.getTitle());
		}
		presenter.setUpdatePending(true);
	}

	@Override
	protected void execute(ActionCommand command,
			UserRequestCallbackAdapter<Patient> urc) {
		presenter.getEventBus().executePatient(command, urc);
	}

	@Override
	protected void setModelsToBePersisted(ListStore<Patient> listStore) {
		presenter.setModelsToBePersisted();
	}

	@Override
	protected boolean isUpdatePending() {
		return presenter.isUpdatePending();
	}

	@Override
	protected boolean isSearchPending() {
		return presenter.isSearchPending();
	}
}