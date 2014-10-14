package com.rsoft.medicasoft.shared.model;

/*
 Templates Generator
 */

/*@Author=Jean Louidort*/
/*@Generation Date=Mon Nov 18 01:14:07 EST 2013*/
/*@Version=1.0*/
import com.googlecode.objectify.annotation.IgnoreSave;
import com.googlecode.objectify.annotation.Load;
import com.googlecode.objectify.annotation.OnLoad;
import com.googlecode.objectify.annotation.OnSave;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.condition.IfNotNull;

import com.googlecode.objectify.annotation.Entity;
import com.rsoft.medicasoft.shared.ModelException;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.ModelBaseX;
import com.rsoft.medicasoft.shared.PersistenceException;
import java.io.Serializable;
import com.googlecode.objectify.Ref;
import java.util.Date;

@Entity
public class Assurance extends ModelBaseX implements Serializable {
	private static final long serialVersionUID = 1L;

	@Index({ IfNotNull.class })
	@Load
	private Ref<Emploi> emploiId;
	@IgnoreSave
	private Emploi emploi;
	@Index({ IfNotNull.class })
	@Load
	private Ref<Institution> assureurId;
	@IgnoreSave
	private Institution assureur;

	private String typeAssurance;

	private String description;

	private Date dateEffective;

	private String statut;

	private Date dateStatut;

	private Double montant;

	private Integer patientId;

	public Assurance() {
	}

	/**
	 * Get the value of emploiId
	 * 
	 * @return the value of emploiId Get the value emploiId
	 */
	public Ref<Emploi> getEmploiId() {
		return this.emploiId;
	}

	/**
	 * Get the value of emploi
	 * 
	 * @return the value of emploi Get the value emploi
	 */
	public Emploi getEmploi() {
		return this.emploi;
	}

	/**
	 * Get the value of assureurId
	 * 
	 * @return the value of assureurId Get the value assureurId
	 */
	public Ref<Institution> getAssureurId() {
		return this.assureurId;
	}

	/**
	 * Get the value of institution
	 * 
	 * @return the value of institution Get the value institution
	 */
	public Institution getAssureur() {
		return this.assureur;
	}

	/**
	 * Get the value of typeAssurance
	 * 
	 * @return the value of typeAssurance Get the value typeAssurance
	 */
	public String getTypeAssurance() {
		return this.typeAssurance;
	}

	/**
	 * Get the value of description
	 * 
	 * @return the value of description Get the value description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Get the value of dateEffective
	 * 
	 * @return the value of dateEffective Get the value dateEffective
	 */
	public Date getDateEffective() {
		return this.dateEffective;
	}

	/**
	 * Get the value of statut
	 * 
	 * @return the value of statut Get the value statut
	 */
	public String getStatut() {
		return this.statut;
	}

	/**
	 * Get the value of dateStatut
	 * 
	 * @return the value of dateStatut Get the value dateStatut
	 */
	public Date getDateStatut() {
		return this.dateStatut;
	}

	/**
	 * Get the value of montant
	 * 
	 * @return the value of montant Get the value montant
	 */
	public Double getMontant() {
		return this.montant;
	}

	/**
	 * Get the value of patientId
	 * 
	 * @return the value of patientId Get the value patientId
	 */
	public Integer getPatientId() {
		return this.patientId;
	}

	/**
	 * Set the value of emploiId
	 * 
	 * @param emploiId
	 *            * new value of emploiId
	 */
	public void setEmploiId(Ref<Emploi> emploiId) {
		this.emploiId = emploiId;
	}

	/**
	 * Set the value of emploi
	 * 
	 * @param emploi
	 *            * new value of emploi
	 */
	public void setEmploi(Emploi emploi) {
		this.emploi = emploi;
	}

	/**
	 * Set the value of assureurId
	 * 
	 * @param assureurId
	 *            * new value of assureurId
	 */
	public void setAssureurId(Ref<Institution> assureurId) {
		this.assureurId = assureurId;
	}

	/**
	 * Set the value of institution
	 * 
	 * @param institution
	 *            * new value of institution
	 */
	public void setAssureur(Institution assureur) {
		this.assureur = assureur;
	}

	/**
	 * Set the value of typeAssurance
	 * 
	 * @param typeAssurance
	 *            * new value of typeAssurance
	 */
	public void setTypeAssurance(String typeAssurance) {
		if (this.compareFields(this.typeAssurance, typeAssurance)) {
			this.fireUpdatePendingChanged(true);
		}
		this.typeAssurance = typeAssurance != null ? typeAssurance.trim()
				: null;
	}

	/**
	 * Set the value of description
	 * 
	 * @param description
	 *            * new value of description
	 */
	public void setDescription(String description) {
		if (this.compareFields(this.description, description)) {
			this.fireUpdatePendingChanged(true);
		}
		this.description = description != null ? description.trim() : null;
	}

	/**
	 * Set the value of dateEffective
	 * 
	 * @param dateEffective
	 *            * new value of dateEffective
	 */
	public void setDateEffective(Date dateEffective) {
		if (this.compareFields(this.dateEffective, dateEffective)) {
			this.fireUpdatePendingChanged(true);
		}
		this.dateEffective = dateEffective;
	}

	/**
	 * Set the value of statut
	 * 
	 * @param statut
	 *            * new value of statut
	 */
	public void setStatut(String statut) {
		if (this.compareFields(this.statut, statut)) {
			this.fireUpdatePendingChanged(true);
		}
		this.statut = statut != null ? statut.trim() : null;
	}

	/**
	 * Set the value of dateStatut
	 * 
	 * @param dateStatut
	 *            * new value of dateStatut
	 */
	public void setDateStatut(Date dateStatut) {
		if (this.compareFields(this.dateStatut, dateStatut)) {
			this.fireUpdatePendingChanged(true);
		}
		this.dateStatut = dateStatut;
	}

	/**
	 * Set the value of montant
	 * 
	 * @param montant
	 *            * new value of montant
	 */
	public void setMontant(Double montant) {
		if (this.compareFields(this.montant, montant)) {
			this.fireUpdatePendingChanged(true);
		}
		this.montant = montant;
	}

	/**
	 * Set the value of patientId
	 * 
	 * @param patientId
	 *            * new value of patientId
	 */
	public void setPatientId(Integer patientId) {
		if (this.compareFields(this.patientId, patientId)) {
			this.fireUpdatePendingChanged(true);
		}
		this.patientId = patientId;
	}

	@OnSave
	public void beforeSave() throws PersistenceException {
		if (emploi != null) {
			emploiId = Ref.create(emploi);
		}
		if (assureur != null) {
			assureurId = Ref.create(assureur);
		}
	}

	@OnLoad
	public void afterLoad() throws PersistenceException {
		if (emploiId != null) {
			emploi = emploiId.getValue();
		}
		if (assureurId != null) {
			assureur = assureurId.getValue();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result;
		if (this.getEntityId() != null) {
			result += ((this.getEntityId() == null) ? 0 : this.getEntityId()
					.hashCode());
		}
		if (this.getLineNo() != null) {
			result += ((this.getLineNo() == null) ? 0 : this.getLineNo()
					.hashCode());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assurance other = (Assurance) obj;
		// Je fais de comparaison par id dans le cas de mise a jour ou de
		// suppression
		// dans le cas d'insertion je le fais par lineNo
		if (this.getEntityId() != null && other.getEntityId() != null
				&& !this.getEntityId().equals(other.getEntityId())) {
			return false;
		}
		if (this.getLineNo() == null) {
			if (other.getLineNo() != null)
				return false;
		} else if (other.getLineNo() == null) {
			if (this.getLineNo() != null)
				return false;
		} else if (this.getLineNo() != other.getLineNo()) {
			return false;
		}
		return true;
	}

	// Recuperer la signature de l'entite
	public String getKey() {
		StringBuilder builder = new StringBuilder();
		if (getLineNo() != null) {
			builder.append(getLineNo());
		} else if (this.getEntityId() != null) {
			builder.append(getEntityId());
		}
		return builder.toString();
	}

	public void merge(ModelBase modelBase) {
		Assurance model = (Assurance) modelBase;
		setEntityId(model.getEntityId());
		setErrorMessage(model.getErrorMessage());
		setCreatedBy(model.getCreatedBy());
		setCreatedOn(model.getCreatedOn());
		setUpdatedBy(model.getUpdatedBy());
		setUpdatedOn(model.getUpdatedOn());
		setRowscn(model.getRowscn());
		setRowscn(model.getRowscn());

		typeAssurance = model.getTypeAssurance();
		description = model.getDescription();
		dateEffective = model.getDateEffective();
		statut = model.getStatut();
		dateStatut = model.getDateStatut();
		montant = model.getMontant();
		patientId = model.getPatientId();
		assureurId = model.getAssureurId();
		emploiId = model.getEmploiId();
	}

	@Override
	public void validateModel() throws ModelException {
		StringBuilder builder = new StringBuilder();
		if (typeAssurance == null || typeAssurance.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("typeAssurance");
			} else {
				builder.append("|typeAssurance");
			}

		}
		if (description == null || description.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("description");
			} else {
				builder.append("|description");
			}

		}
		if (dateEffective == null) {
			builder.append("dateEffective");
		}
		if (statut == null || statut.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("statut");
			} else {
				builder.append("|statut");
			}

		}
		if (dateStatut == null) {
			builder.append("dateStatut");
		}
		if (montant == null) {
			builder.append("montant");
		}
		if (assureurId == null) {
			if (builder.toString().isEmpty()) {
				builder.append("assureurId");
			} else {
				builder.append("|assureurId");
			}

		}
		if (!builder.toString().trim().isEmpty()) {
			throw new ModelException("MISSING_FIELDS", builder.toString(),
					"Fill all required fields before continue");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (this.getEntityId() != null) {
			return Long.toString(this.getEntityId());
		}
		if (this.getLineNo() != null) {
			return Long.toString(this.getLineNo());
		}
		return super.toString();
	}

	@Override
	public Object getPrimaryKey() {
		return this.getEntityId();
	}

	public static Assurance parse(String value) {
		Assurance model = new Assurance();
		return model;
	}
}