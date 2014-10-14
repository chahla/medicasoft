package com.rsoft.medicasoft.shared.model;

/*
 Templates Generator
 */

/*@Author=Jean Louidort*/
/*@Generation Date=Mon Oct 14 22:30:48 EDT 2013*/
/*@Version=1.0*/
import java.io.Serializable;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.IgnoreSave;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.googlecode.objectify.annotation.OnLoad;
import com.googlecode.objectify.annotation.OnSave;
import com.googlecode.objectify.condition.IfNotNull;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.ModelBaseX;
import com.rsoft.medicasoft.shared.ModelException;
import com.rsoft.medicasoft.shared.PersistenceException;

@Entity
public class Departement extends ModelBaseX implements Serializable {
	private static final long serialVersionUID = 1L;

	@Index({ IfNotNull.class })
	@Load
	private Ref<Pays> paysId;
	@IgnoreSave
	private Pays pays;
	private String nom;
	@IgnoreSave
	private Long _paysId;

	public Departement() {
	}

	public void setPaysId(Long _paysId) {
		this._paysId = _paysId;
	}

	/**
	 * Get the value of paysId
	 * 
	 * @return the value of paysId Get the value paysId
	 */
	public Ref<Pays> getPaysId() {
		return this.paysId;
	}

	/**
	 * Get the value of pays
	 * 
	 * @return the value of pays Get the value pays
	 */
	public Pays getPays() {
		return this.pays;
	}

	/**
	 * Get the value of nom
	 * 
	 * @return the value of nom Get the value nom
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Set the value of paysId
	 * 
	 * @param paysId
	 *            * new value of paysId
	 */
	public void setPaysId(Ref<Pays> paysId) {
		this.paysId = paysId;
	}

	/**
	 * Set the value of pays
	 * 
	 * @param pays
	 *            * new value of pays
	 */
	public void setPays(Pays pays) {
		this.pays = pays;
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
		if (pays != null) {
			paysId = Ref.create(pays);
		} else if (_paysId != null) {
			pays = new Pays();
			pays.setEntityId(_paysId);
			paysId = Ref.create(pays);
		}
	}

	@OnLoad
	public void afterLoad() throws PersistenceException {
		if (paysId != null) {
			pays = paysId.getValue();
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
		Departement other = (Departement) obj;
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
		Departement model = (Departement) modelBase;
		setEntityId(model.getEntityId());
		setErrorMessage(model.getErrorMessage());
		setCreatedBy(model.getCreatedBy());
		setCreatedOn(model.getCreatedOn());
		setUpdatedBy(model.getUpdatedBy());
		setUpdatedOn(model.getUpdatedOn());
		setRowscn(model.getRowscn());
		setRowscn(model.getRowscn());

		nom = model.getNom();
		paysId = model.getPaysId();
	}

	@Override
	public void validateModel() throws ModelException {
		StringBuilder builder = new StringBuilder();

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

	public static Departement parse(String value) {
		Departement model = new Departement();
		return model;
	}
}