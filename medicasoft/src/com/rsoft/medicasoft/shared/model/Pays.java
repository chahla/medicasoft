package com.rsoft.medicasoft.shared.model;

/*
 Templates Generator
 */

/*@Author=Jean Louidort*/
/*@Generation Date=Mon Oct 14 22:30:48 EDT 2013*/
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
public class Pays extends ModelBaseX implements Serializable {
	private static final long serialVersionUID = 1L;
	@Index
	private String code;
	private String nom;

	public Pays() {
	}

	/**
	 * Get the value of nom
	 * 
	 * @return the value of nom Get the value nom
	 */
	public String getNom() {
		return this.nom;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if (this.compareFields(this.code, code)) {
			this.fireUpdatePendingChanged(true);
		}
		this.code = code != null ? code.trim() : null;
	}

	/**
	 * Set the value of nom
	 * 
	 * @param nom
	 *            * new value of nom
	 */
	public void setNom(String nom) {
		if (this.compareFields(this.nom, nom)) {
			this.fireUpdatePendingChanged(true);
		}
		this.nom = nom != null ? nom.trim() : null;
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
		Pays other = (Pays) obj;
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
		Pays model = (Pays) modelBase;
		setEntityId(model.getEntityId());
		setErrorMessage(model.getErrorMessage());
		setCreatedBy(model.getCreatedBy());
		setCreatedOn(model.getCreatedOn());
		setUpdatedBy(model.getUpdatedBy());
		setUpdatedOn(model.getUpdatedOn());
		setRowscn(model.getRowscn());

		code = model.getCode();
		nom = model.getNom();
	}

	@Override
	public void validateModel() throws ModelException {
		StringBuilder builder = new StringBuilder();

		if (code == null || code.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("code");
			} else {
				builder.append("|code");
			}

		}
		if (nom == null || nom.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("nom");
			} else {
				builder.append("|nom");
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

	public static Pays parse(String value) {
		Pays model = new Pays();
		return model;
	}
}