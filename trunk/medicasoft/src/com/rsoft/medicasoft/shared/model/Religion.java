package com.rsoft.medicasoft.shared.model;

/*
 Templates Generator
 */

/*@Author=Jean Louidort*/
/*@Generation Date=Mon Nov 18 01:14:09 EST 2013*/
/*@Version=1.0*/
import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.OnLoad;
import com.googlecode.objectify.annotation.OnSave;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.ModelBaseX;
import com.rsoft.medicasoft.shared.ModelException;
import com.rsoft.medicasoft.shared.PersistenceException;

@Entity
public class Religion extends ModelBaseX implements Serializable {
	private static final long serialVersionUID = 1L;
	@Index
	private String langue;

	private String description;
	@Index
	private String classification;

	public Religion() {
	}

	public String getLangue() {
		return langue;
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
	 * Get the value of classification
	 * 
	 * @return the value of classification Get the value classification
	 */
	public String getClassification() {
		return this.classification;
	}

	public void setLangue(String langue) {
		if (this.compareFields(this.langue, langue)) {
			this.fireUpdatePendingChanged(true);
		}
		this.langue = langue;
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
	 * Set the value of classification
	 * 
	 * @param classification
	 *            * new value of classification
	 */
	public void setClassification(String classification) {
		if (this.compareFields(this.classification, classification)) {
			this.fireUpdatePendingChanged(true);
		}
		this.classification = classification != null ? classification.trim()
				: null;
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
		Religion other = (Religion) obj;
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
		Religion model = (Religion) modelBase;
		setEntityId(model.getEntityId());
		setErrorMessage(model.getErrorMessage());
		setCreatedBy(model.getCreatedBy());
		setCreatedOn(model.getCreatedOn());
		setUpdatedBy(model.getUpdatedBy());
		setUpdatedOn(model.getUpdatedOn());
		setRowscn(model.getRowscn());
		setRowscn(model.getRowscn());
		description = model.getDescription();
		classification = model.getClassification();
	}

	@Override
	public void validateModel() throws ModelException {
		StringBuilder builder = new StringBuilder();

		if (description == null || description.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("description");
			} else {
				builder.append("|description");
			}
		}
		if (classification == null || classification.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("classification");
			} else {
				builder.append("|classification");
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

	public static Religion parse(String value) {
		Religion model = new Religion();
		return model;
	}
}