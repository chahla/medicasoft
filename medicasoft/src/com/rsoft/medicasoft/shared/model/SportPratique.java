package com.rsoft.medicasoft.shared.model;

/*
 Templates Generator
 */

/*@Author=Jean Louidort*/
/*@Generation Date=Mon Nov 18 01:14:06 EST 2013*/
/*@Version=1.0*/
import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.OnLoad;
import com.googlecode.objectify.annotation.OnSave;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.ModelBaseX;
import com.rsoft.medicasoft.shared.ModelException;
import com.rsoft.medicasoft.shared.PersistenceException;

@Entity
public class SportPratique extends ModelBaseX implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer sportFrequenteId;

	private String nomSport;

	private Integer frequence;

	private String unite;

	private Integer patientId;

	private Date dateDebut;

	private Date dateFin;

	public SportPratique() {
	}

	/**
	 * Get the value of sportFrequenteId
	 * 
	 * @return the value of sportFrequenteId Get the value sportFrequenteId
	 */
	public Integer getSportFrequenteId() {
		return this.sportFrequenteId;
	}

	/**
	 * Get the value of nomSport
	 * 
	 * @return the value of nomSport Get the value nomSport
	 */
	public String getNomSport() {
		return this.nomSport;
	}

	/**
	 * Get the value of frequence
	 * 
	 * @return the value of frequence Get the value frequence
	 */
	public Integer getFrequence() {
		return this.frequence;
	}

	/**
	 * Get the value of unite
	 * 
	 * @return the value of unite Get the value unite
	 */
	public String getUnite() {
		return this.unite;
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
	 * Get the value of dateDebut
	 * 
	 * @return the value of dateDebut Get the value dateDebut
	 */
	public Date getDateDebut() {
		return this.dateDebut;
	}

	/**
	 * Get the value of dateFin
	 * 
	 * @return the value of dateFin Get the value dateFin
	 */
	public Date getDateFin() {
		return this.dateFin;
	}

	/**
	 * Set the value of sportFrequenteId
	 * 
	 * @param sportFrequenteId
	 *            * new value of sportFrequenteId
	 */
	public void setSportFrequenteId(Integer sportFrequenteId) {
		if (this.compareFields(this.sportFrequenteId, sportFrequenteId)) {
			this.fireUpdatePendingChanged(true);
		}
		this.sportFrequenteId = sportFrequenteId;
	}

	/**
	 * Set the value of nomSport
	 * 
	 * @param nomSport
	 *            * new value of nomSport
	 */
	public void setNomSport(String nomSport) {
		if (this.compareFields(this.nomSport, nomSport)) {
			this.fireUpdatePendingChanged(true);
		}
		this.nomSport = nomSport != null ? nomSport.trim() : null;
	}

	/**
	 * Set the value of frequence
	 * 
	 * @param frequence
	 *            * new value of frequence
	 */
	public void setFrequence(Integer frequence) {
		if (this.compareFields(this.frequence, frequence)) {
			this.fireUpdatePendingChanged(true);
		}
		this.frequence = frequence;
	}

	/**
	 * Set the value of unite
	 * 
	 * @param unite
	 *            * new value of unite
	 */
	public void setUnite(String unite) {
		if (this.compareFields(this.unite, unite)) {
			this.fireUpdatePendingChanged(true);
		}
		this.unite = unite != null ? unite.trim() : null;
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

	/**
	 * Set the value of dateDebut
	 * 
	 * @param dateDebut
	 *            * new value of dateDebut
	 */
	public void setDateDebut(Date dateDebut) {
		if (this.compareFields(this.dateDebut, dateDebut)) {
			this.fireUpdatePendingChanged(true);
		}
		this.dateDebut = dateDebut;
	}

	/**
	 * Set the value of dateFin
	 * 
	 * @param dateFin
	 *            * new value of dateFin
	 */
	public void setDateFin(Date dateFin) {
		if (this.compareFields(this.dateFin, dateFin)) {
			this.fireUpdatePendingChanged(true);
		}
		this.dateFin = dateFin;
	}

	@OnSave
	public void beforeSave() throws PersistenceException {

	}

	@OnLoad
	public void afterLoad() throws PersistenceException {

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
		SportPratique other = (SportPratique) obj;
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
		SportPratique model = (SportPratique) modelBase;
		setEntityId(model.getEntityId());
		setErrorMessage(model.getErrorMessage());
		setCreatedBy(model.getCreatedBy());
		setCreatedOn(model.getCreatedOn());
		setUpdatedBy(model.getUpdatedBy());
		setUpdatedOn(model.getUpdatedOn());
		setRowscn(model.getRowscn());
		setRowscn(model.getRowscn());

		sportFrequenteId = model.getSportFrequenteId();
		nomSport = model.getNomSport();
		frequence = model.getFrequence();
		unite = model.getUnite();
		patientId = model.getPatientId();
		dateDebut = model.getDateDebut();
		dateFin = model.getDateFin();
	}

	@Override
	public void validateModel() throws ModelException {
		StringBuilder builder = new StringBuilder();

		if (sportFrequenteId == null) {
			builder.append("sportFrequenteId");
		}
		if (nomSport == null || nomSport.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("nomSport");
			} else {
				builder.append("|nomSport");
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

	public static SportPratique parse(String value) {
		SportPratique model = new SportPratique();
		return model;
	}
}