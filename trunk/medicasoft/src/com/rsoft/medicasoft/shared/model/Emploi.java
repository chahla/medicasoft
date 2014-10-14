package com.rsoft.medicasoft.shared.model;

/*
 Templates Generator
 */

/*@Author=Jean Louidort*/
/*@Generation Date=Mon Nov 18 01:14:08 EST 2013*/
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
public class Emploi extends ModelBaseX implements Serializable {
	private static final long serialVersionUID = 1L;

	@Index({ IfNotNull.class })
	@Load
	private Ref<Institution> entrepriseId;
	@IgnoreSave
	private Institution employeur;

	private String nomPoste;

	private Date dateEmbauche;

	private Date finEmbauche;

	private String monnaie;

	private Double salaire;

	private String statut;

	public Emploi() {
	}

	/**
	 * Get the value of entrepriseId
	 * 
	 * @return the value of entrepriseId Get the value entrepriseId
	 */
	public Ref<Institution> getEntrepriseId() {
		return this.entrepriseId;
	}

	/**
	 * Get the value of institution
	 * 
	 * @return the value of institution Get the value institution
	 */
	public Institution getEmployeur() {
		return this.employeur;
	}


	/**
	 * Get the value of nomPoste
	 * 
	 * @return the value of nomPoste Get the value nomPoste
	 */
	public String getNomPoste() {
		return this.nomPoste;
	}

	/**
	 * Get the value of dateEmbauche
	 * 
	 * @return the value of dateEmbauche Get the value dateEmbauche
	 */
	public Date getDateEmbauche() {
		return this.dateEmbauche;
	}

	/**
	 * Get the value of finEmbauche
	 * 
	 * @return the value of finEmbauche Get the value finEmbauche
	 */
	public Date getFinEmbauche() {
		return this.finEmbauche;
	}

	/**
	 * Get the value of monnaie
	 * 
	 * @return the value of monnaie Get the value monnaie
	 */
	public String getMonnaie() {
		return this.monnaie;
	}

	/**
	 * Get the value of salaire
	 * 
	 * @return the value of salaire Get the value salaire
	 */
	public Double getSalaire() {
		return this.salaire;
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
	 * Set the value of entrepriseId
	 * 
	 * @param entrepriseId
	 *            * new value of entrepriseId
	 */
	public void setEntrepriseId(Ref<Institution> entrepriseId) {
		this.entrepriseId = entrepriseId;
	}

	/**
	 * Set the value of institution
	 * 
	 * @param institution
	 *            * new value of institution
	 */
	public void setEmployeur(Institution employeur) {
		this.employeur = employeur;
	}


	/**
	 * Set the value of nomPoste
	 * 
	 * @param nomPoste
	 *            * new value of nomPoste
	 */
	public void setNomPoste(String nomPoste) {
		if (this.compareFields(this.nomPoste, nomPoste)) {
			this.fireUpdatePendingChanged(true);
		}
		this.nomPoste = nomPoste != null ? nomPoste.trim() : null;
	}

	/**
	 * Set the value of dateEmbauche
	 * 
	 * @param dateEmbauche
	 *            * new value of dateEmbauche
	 */
	public void setDateEmbauche(Date dateEmbauche) {
		if (this.compareFields(this.dateEmbauche, dateEmbauche)) {
			this.fireUpdatePendingChanged(true);
		}
		this.dateEmbauche = dateEmbauche;
	}

	/**
	 * Set the value of finEmbauche
	 * 
	 * @param finEmbauche
	 *            * new value of finEmbauche
	 */
	public void setFinEmbauche(Date finEmbauche) {
		if (this.compareFields(this.finEmbauche, finEmbauche)) {
			this.fireUpdatePendingChanged(true);
		}
		this.finEmbauche = finEmbauche;
	}

	/**
	 * Set the value of monnaie
	 * 
	 * @param monnaie
	 *            * new value of monnaie
	 */
	public void setMonnaie(String monnaie) {
		if (this.compareFields(this.monnaie, monnaie)) {
			this.fireUpdatePendingChanged(true);
		}
		this.monnaie = monnaie != null ? monnaie.trim() : null;
	}

	/**
	 * Set the value of salaire
	 * 
	 * @param salaire
	 *            * new value of salaire
	 */
	public void setSalaire(Double salaire) {
		if (this.compareFields(this.salaire, salaire)) {
			this.fireUpdatePendingChanged(true);
		}
		this.salaire = salaire;
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

	@OnSave
	public void beforeSave() throws PersistenceException {
		if (employeur != null) {
			entrepriseId = Ref.create(employeur);
		}
	}

	@OnLoad
	public void afterLoad() throws PersistenceException {
		if (entrepriseId != null) {
			employeur = entrepriseId.getValue();
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
		Emploi other = (Emploi) obj;
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
		Emploi model = (Emploi) modelBase;
		setEntityId(model.getEntityId());
		setErrorMessage(model.getErrorMessage());
		setCreatedBy(model.getCreatedBy());
		setCreatedOn(model.getCreatedOn());
		setUpdatedBy(model.getUpdatedBy());
		setUpdatedOn(model.getUpdatedOn());
		setRowscn(model.getRowscn());
		setRowscn(model.getRowscn());

		nomPoste = model.getNomPoste();
		dateEmbauche = model.getDateEmbauche();
		finEmbauche = model.getFinEmbauche();
		monnaie = model.getMonnaie();
		salaire = model.getSalaire();
		entrepriseId = model.getEntrepriseId();
		statut = model.getStatut();
	}

	@Override
	public void validateModel() throws ModelException {
		StringBuilder builder = new StringBuilder();
		if (nomPoste == null || nomPoste.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("nomPoste");
			} else {
				builder.append("|nomPoste");
			}

		}
		if (monnaie == null || monnaie.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("monnaie");
			} else {
				builder.append("|monnaie");
			}

		}
		if (salaire == null) {
			builder.append("salaire");
		}
		if (entrepriseId == null) {
			if (builder.toString().isEmpty()) {
				builder.append("entrepriseId");
			} else {
				builder.append("|entrepriseId");
			}

		}
		if (statut == null || statut.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("statut");
			} else {
				builder.append("|statut");
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

	public static Emploi parse(String value) {
		Emploi model = new Emploi();
		return model;
	}
}