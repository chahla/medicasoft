package com.rsoft.medicasoft.shared.model;

/*
 Templates Generator
 */

/*@Author=Jean Louidort*/
/*@Generation Date=Sat Oct 26 19:38:26 EDT 2013*/
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
public class ParamInout extends ModelBaseX implements Serializable {
	private static final long serialVersionUID = 1L;

	@Index
	private String description;

	private String contenu;

	public ParamInout() {
	}

	/**
	 * Get the value of contenu
	 * 
	 * @return the value of contenu Get the value contenu
	 */
	public String getContenu() {
		return this.contenu;
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
	 * Set the value of contenu
	 * 
	 * @param contenu
	 *            * new value of contenu
	 */
	public void setContenu(String contenu) {
		if (this.compareFields(this.contenu, contenu)) {
			this.fireUpdatePendingChanged(true);
		}
		this.contenu = contenu != null ? contenu.trim() : null;
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
		ParamInout other = (ParamInout) obj;
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
		ParamInout model = (ParamInout) modelBase;
		setEntityId(model.getEntityId());
		setErrorMessage(model.getErrorMessage());
		setCreatedBy(model.getCreatedBy());
		setCreatedOn(model.getCreatedOn());
		setUpdatedBy(model.getUpdatedBy());
		setUpdatedOn(model.getUpdatedOn());
		setRowscn(model.getRowscn());
		setRowscn(model.getRowscn());

		contenu = model.getContenu();
		description = model.getDescription();
	}

	@Override
	public void validateModel() throws ModelException {
		StringBuilder builder = new StringBuilder();

		if (contenu == null || contenu.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("contenu");
			} else {
				builder.append("|contenu");
			}

		}
		if (description == null || description.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("description");
			} else {
				builder.append("|description");
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

	public static ParamInout parse(String value) {
		ParamInout model = new ParamInout();
		return model;
	}
}