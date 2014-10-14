package com.rsoft.medicasoft.shared.model;

/*
 Templates Generator
 */

/*@Author=Jean Louidort*/
/*@Generation Date=Thu Sep 19 22:50:28 EDT 2013*/
/*@Version=1.0*/
import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.IgnoreSave;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.OnLoad;
import com.googlecode.objectify.annotation.OnSave;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.ModelBaseX;
import com.rsoft.medicasoft.shared.ModelException;
import com.rsoft.medicasoft.shared.PersistenceException;

@Entity
public class Patient extends ModelBaseX implements Serializable {
	private static final long serialVersionUID = 1L;
	@Index
	private String statut;
	@Index
	private String nom;
	@Index
	private String prenom;
	@Index
	private String religion;
	@Index
	private String dateNaissance;
	@Index
	private String lieuNaissance;

	private String heureNaissance;
	@Index
	private Date dateDeces;
	@Index
	private String lieuDeces;
	private String heureDeces;
	@Index
	private String causeDeces;
	@Index
	private String telephone1;
	@Index
	private String telephone2;
	@Index
	private String nomResponsable1;
	@Index
	private String prenomResponsable1;
	@Index
	private String courriel;

	private String courrielResponsable1;

	private String telephone1Responsable1;

	private String telephone2Responsable1;

	private String nomPere;

	private String nomMere;
	@Index
	private String etatCivil;

	private String nomEpoux;

	private String prenomEpoux;

	private String telephoneEpoux;

	private String courrielEpoux;

	private String prenomPere;

	private String prenomMere;

	private Integer pereId;

	private Integer mereId;

	private Integer epouxId;
	@Index
	private String nationalite;
	@Index
	private String paysResidence;

	private String adresse;
	@Index
	private String sexe = "MASCULIN";
	@Index
	private String typeIdentication;
	@Index
	private String noIdentification;
	@Index
	private String paysIdentifcation;

	private Integer nomEnfants;

	private String lienParenteResponsable1;

	private String telephonePere;

	private String telephoneMere;

	private String nomResponsable2;

	private String prenomResponsable2;

	private String courrielResponsable2;

	private String telephone1Responsable2;

	private String telephone2Responsable2;

	private String adresseResponsable1;

	private String adresseResponsable2;

	private String taille;

	private String poids;
	@Index
	private String zone;
	@Index
	private String commune;
	@Index
	private String departement;

	@IgnoreSave
	private Item religionItem;
	@IgnoreSave
	private Item sexeItem;

	public Patient() {
	}

	/**
	 * Get the value of nom
	 * 
	 * @return the value of nom Get the value nom
	 */
	public String getNom() {
		return this.nom;
	}

	public String getReligion() {
		return religion;
	}

	/**
	 * Get the value of prenom
	 * 
	 * @return the value of prenom Get the value prenom
	 */
	public String getPrenom() {
		return this.prenom;
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
	 * Get the value of dateNaissance
	 * 
	 * @return the value of dateNaissance Get the value dateNaissance
	 */
	public String getDateNaissance() {
		return this.dateNaissance;
	}

	/**
	 * Get the value of lieuNaissance
	 * 
	 * @return the value of lieuNaissance Get the value lieuNaissance
	 */
	public String getLieuNaissance() {
		return this.lieuNaissance;
	}

	/**
	 * Get the value of dateDeces
	 * 
	 * @return the value of dateDeces Get the value dateDeces
	 */
	public Date getDateDeces() {
		return this.dateDeces;
	}

	/**
	 * Get the value of lieuDeces
	 * 
	 * @return the value of lieuDeces Get the value lieuDeces
	 */
	public String getLieuDeces() {
		return this.lieuDeces;
	}

	/**
	 * Get the value of heureNaissance
	 * 
	 * @return the value of heureNaissance Get the value heureNaissance
	 */
	public String getHeureNaissance() {
		return this.heureNaissance;
	}

	/**
	 * Get the value of heureDeces
	 * 
	 * @return the value of heureDeces Get the value heureDeces
	 */
	public String getHeureDeces() {
		return this.heureDeces;
	}

	/**
	 * Get the value of causeDeces
	 * 
	 * @return the value of causeDeces Get the value causeDeces
	 */
	public String getCauseDeces() {
		return this.causeDeces;
	}

	/**
	 * Get the value of telephone1
	 * 
	 * @return the value of telephone1 Get the value telephone1
	 */
	public String getTelephone1() {
		return this.telephone1;
	}

	/**
	 * Get the value of telephone2
	 * 
	 * @return the value of telephone2 Get the value telephone2
	 */
	public String getTelephone2() {
		return this.telephone2;
	}

	/**
	 * Get the value of nomResponsable1
	 * 
	 * @return the value of nomResponsable1 Get the value nomResponsable1
	 */
	public String getNomResponsable1() {
		return this.nomResponsable1;
	}

	/**
	 * Get the value of prenomResponsable1
	 * 
	 * @return the value of prenomResponsable1 Get the value prenomResponsable1
	 */
	public String getPrenomResponsable1() {
		return this.prenomResponsable1;
	}

	/**
	 * Get the value of courriel
	 * 
	 * @return the value of courriel Get the value courriel
	 */
	public String getCourriel() {
		return this.courriel;
	}

	/**
	 * Get the value of courrielResponsable1
	 * 
	 * @return the value of courrielResponsable1 Get the value
	 *         courrielResponsable1
	 */
	public String getCourrielResponsable1() {
		return this.courrielResponsable1;
	}

	/**
	 * Get the value of telephone1Responsable1
	 * 
	 * @return the value of telephone1Responsable1 Get the value
	 *         telephone1Responsable1
	 */
	public String getTelephone1Responsable1() {
		return this.telephone1Responsable1;
	}

	/**
	 * Get the value of telephone2Responsable1
	 * 
	 * @return the value of telephone2Responsable1 Get the value
	 *         telephone2Responsable1
	 */
	public String getTelephone2Responsable1() {
		return this.telephone2Responsable1;
	}

	/**
	 * Get the value of nomPere
	 * 
	 * @return the value of nomPere Get the value nomPere
	 */
	public String getNomPere() {
		return this.nomPere;
	}

	/**
	 * Get the value of nomMere
	 * 
	 * @return the value of nomMere Get the value nomMere
	 */
	public String getNomMere() {
		return this.nomMere;
	}

	/**
	 * Get the value of etatCivil
	 * 
	 * @return the value of etatCivil Get the value etatCivil
	 */
	public String getEtatCivil() {
		return this.etatCivil;
	}

	/**
	 * Get the value of nomEpoux
	 * 
	 * @return the value of nomEpoux Get the value nomEpoux
	 */
	public String getNomEpoux() {
		return this.nomEpoux;
	}

	/**
	 * Get the value of prenomEpoux
	 * 
	 * @return the value of prenomEpoux Get the value prenomEpoux
	 */
	public String getPrenomEpoux() {
		return this.prenomEpoux;
	}

	/**
	 * Get the value of telephoneEpoux
	 * 
	 * @return the value of telephoneEpoux Get the value telephoneEpoux
	 */
	public String getTelephoneEpoux() {
		return this.telephoneEpoux;
	}

	/**
	 * Get the value of courrielEpoux
	 * 
	 * @return the value of courrielEpoux Get the value courrielEpoux
	 */
	public String getCourrielEpoux() {
		return this.courrielEpoux;
	}

	/**
	 * Get the value of prenomPere
	 * 
	 * @return the value of prenomPere Get the value prenomPere
	 */
	public String getPrenomPere() {
		return this.prenomPere;
	}

	/**
	 * Get the value of prenomMere
	 * 
	 * @return the value of prenomMere Get the value prenomMere
	 */
	public String getPrenomMere() {
		return this.prenomMere;
	}

	/**
	 * Get the value of pereId
	 * 
	 * @return the value of pereId Get the value pereId
	 */
	public Integer getPereId() {
		return this.pereId;
	}

	/**
	 * Get the value of mereId
	 * 
	 * @return the value of mereId Get the value mereId
	 */
	public Integer getMereId() {
		return this.mereId;
	}

	/**
	 * Get the value of epouxId
	 * 
	 * @return the value of epouxId Get the value epouxId
	 */
	public Integer getEpouxId() {
		return this.epouxId;
	}

	/**
	 * Get the value of nationalite
	 * 
	 * @return the value of nationalite Get the value nationalite
	 */
	public String getNationalite() {
		return this.nationalite;
	}

	/**
	 * Get the value of paysResidence
	 * 
	 * @return the value of paysResidence Get the value paysResidence
	 */
	public String getPaysResidence() {
		return this.paysResidence;
	}

	/**
	 * Get the value of adresse
	 * 
	 * @return the value of adresse Get the value adresse
	 */
	public String getAdresse() {
		return this.adresse;
	}

	/**
	 * Get the value of sexe
	 * 
	 * @return the value of sexe Get the value sexe
	 */
	public String getSexe() {
		return this.sexe;
	}

	/**
	 * Get the value of typeIdentication
	 * 
	 * @return the value of typeIdentication Get the value typeIdentication
	 */
	public String getTypeIdentication() {
		return this.typeIdentication;
	}

	/**
	 * Get the value of noIdentification
	 * 
	 * @return the value of noIdentification Get the value noIdentification
	 */
	public String getNoIdentification() {
		return this.noIdentification;
	}

	/**
	 * Get the value of paysIdentifcation
	 * 
	 * @return the value of paysIdentifcation Get the value paysIdentifcation
	 */
	public String getPaysIdentifcation() {
		return this.paysIdentifcation;
	}

	/**
	 * Get the value of nomEnfants
	 * 
	 * @return the value of nomEnfants Get the value nomEnfants
	 */
	public Integer getNomEnfants() {
		return this.nomEnfants;
	}

	/**
	 * Get the value of lienParenteResponsable1
	 * 
	 * @return the value of lienParenteResponsable1 Get the value
	 *         lienParenteResponsable1
	 */
	public String getLienParenteResponsable1() {
		return this.lienParenteResponsable1;
	}

	/**
	 * Get the value of telephonePere
	 * 
	 * @return the value of telephonePere Get the value telephonePere
	 */
	public String getTelephonePere() {
		return this.telephonePere;
	}

	/**
	 * Get the value of telephoneMere
	 * 
	 * @return the value of telephoneMere Get the value telephoneMere
	 */
	public String getTelephoneMere() {
		return this.telephoneMere;
	}

	/**
	 * Get the value of nomResponsable2
	 * 
	 * @return the value of nomResponsable2 Get the value nomResponsable2
	 */
	public String getNomResponsable2() {
		return this.nomResponsable2;
	}

	/**
	 * Get the value of prenomResponsable2
	 * 
	 * @return the value of prenomResponsable2 Get the value prenomResponsable2
	 */
	public String getPrenomResponsable2() {
		return this.prenomResponsable2;
	}

	/**
	 * Get the value of courrielResponsable2
	 * 
	 * @return the value of courrielResponsable2 Get the value
	 *         courrielResponsable2
	 */
	public String getCourrielResponsable2() {
		return this.courrielResponsable2;
	}

	/**
	 * Get the value of telephone1Responsable2
	 * 
	 * @return the value of telephone1Responsable2 Get the value
	 *         telephone1Responsable2
	 */
	public String getTelephone1Responsable2() {
		return this.telephone1Responsable2;
	}

	/**
	 * Get the value of telephone2Responsable2
	 * 
	 * @return the value of telephone2Responsable2 Get the value
	 *         telephone2Responsable2
	 */
	public String getTelephone2Responsable2() {
		return this.telephone2Responsable2;
	}

	/**
	 * Get the value of adresseResponsable1
	 * 
	 * @return the value of adresseResponsable1 Get the value
	 *         adresseResponsable1
	 */
	public String getAdresseResponsable1() {
		return this.adresseResponsable1;
	}

	/**
	 * Get the value of adresseResponsable2
	 * 
	 * @return the value of adresseResponsable2 Get the value
	 *         adresseResponsable2
	 */
	public String getAdresseResponsable2() {
		return this.adresseResponsable2;
	}

	/**
	 * Get the value of taille
	 * 
	 * @return the value of taille Get the value taille
	 */
	public String getTaille() {
		return this.taille;
	}

	/**
	 * Get the value of poids
	 * 
	 * @return the value of poids Get the value poids
	 */
	public String getPoids() {
		return this.poids;
	}

	/**
	 * Get the value of zone
	 * 
	 * @return the value of zone Get the value zone
	 */
	public String getZone() {
		return this.zone;
	}

	/**
	 * Get the value of commune
	 * 
	 * @return the value of commune Get the value commune
	 */
	public String getCommune() {
		return this.commune;
	}

	/**
	 * Get the value of departement
	 * 
	 * @return the value of departement Get the value departement
	 */
	public String getDepartement() {
		return this.departement;
	}

	public Item getReligionItem() {
		return religionItem != null ? religionItem
				: religion != null ? new Item(religion) : null;
	}

	public Item getSexeItem() {
		return sexeItem != null ? sexeItem : sexe != null ? new Item(sexe)
				: null;
	}

	public void setSexeItem(Item sexeItem) {
		this.sexeItem = sexeItem;
		if (sexeItem != null) {
			this.setSexe(sexeItem.getID());
		} else {
			this.setSexe(null);

		}
	}

	public void setReligionItem(Item religionItem) {
		this.religionItem = religionItem;
		if (religionItem != null) {
			this.setReligion(religionItem.getID());
		} else {
			this.setReligion(null);
		}
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

	public void setReligion(String religion) {
		if (this.compareFields(this.religion, religion)) {
			this.fireUpdatePendingChanged(true);
		}
		this.religion = religion;
	}

	/**
	 * Set the value of prenom
	 * 
	 * @param prenom
	 *            * new value of prenom
	 */
	public void setPrenom(String prenom) {
		if (this.compareFields(this.prenom, prenom)) {
			this.fireUpdatePendingChanged(true);
		}
		this.prenom = prenom != null ? prenom.trim() : null;
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
	 * Set the value of dateNaissance
	 * 
	 * @param dateNaissance
	 *            * new value of dateNaissance
	 */
	public void setDateNaissance(String dateNaissance) {
		if (this.compareFields(this.dateNaissance, dateNaissance)) {
			this.fireUpdatePendingChanged(true);
		}
		this.dateNaissance = dateNaissance != null ? dateNaissance.trim()
				: null;
	}

	/**
	 * Set the value of lieuNaissance
	 * 
	 * @param lieuNaissance
	 *            * new value of lieuNaissance
	 */
	public void setLieuNaissance(String lieuNaissance) {
		if (this.compareFields(this.lieuNaissance, lieuNaissance)) {
			this.fireUpdatePendingChanged(true);
		}
		this.lieuNaissance = lieuNaissance != null ? lieuNaissance.trim()
				: null;
	}

	/**
	 * Set the value of dateDeces
	 * 
	 * @param dateDeces
	 *            * new value of dateDeces
	 */
	public void setDateDeces(Date dateDeces) {
		if (this.compareFields(this.dateDeces, dateDeces)) {
			this.fireUpdatePendingChanged(true);
		}
		this.dateDeces = dateDeces;
	}

	/**
	 * Set the value of lieuDeces
	 * 
	 * @param lieuDeces
	 *            * new value of lieuDeces
	 */
	public void setLieuDeces(String lieuDeces) {
		if (this.compareFields(this.lieuDeces, lieuDeces)) {
			this.fireUpdatePendingChanged(true);
		}
		this.lieuDeces = lieuDeces != null ? lieuDeces.trim() : null;
	}

	/**
	 * Set the value of heureNaissance
	 * 
	 * @param heureNaissance
	 *            * new value of heureNaissance
	 */
	public void setHeureNaissance(String heureNaissance) {
		if (this.compareFields(this.heureNaissance, heureNaissance)) {
			this.fireUpdatePendingChanged(true);
		}
		this.heureNaissance = heureNaissance != null ? heureNaissance.trim()
				: null;
	}

	/**
	 * Set the value of heureDeces
	 * 
	 * @param heureDeces
	 *            * new value of heureDeces
	 */
	public void setHeureDeces(String heureDeces) {
		if (this.compareFields(this.heureDeces, heureDeces)) {
			this.fireUpdatePendingChanged(true);
		}
		this.heureDeces = heureDeces != null ? heureDeces.trim() : null;
	}

	/**
	 * Set the value of causeDeces
	 * 
	 * @param causeDeces
	 *            * new value of causeDeces
	 */
	public void setCauseDeces(String causeDeces) {
		if (this.compareFields(this.causeDeces, causeDeces)) {
			this.fireUpdatePendingChanged(true);
		}
		this.causeDeces = causeDeces != null ? causeDeces.trim() : null;
	}

	/**
	 * Set the value of telephone1
	 * 
	 * @param telephone1
	 *            * new value of telephone1
	 */
	public void setTelephone1(String telephone1) {
		if (this.compareFields(this.telephone1, telephone1)) {
			this.fireUpdatePendingChanged(true);
		}
		this.telephone1 = telephone1 != null ? telephone1.trim() : null;
	}

	/**
	 * Set the value of telephone2
	 * 
	 * @param telephone2
	 *            * new value of telephone2
	 */
	public void setTelephone2(String telephone2) {
		if (this.compareFields(this.telephone2, telephone2)) {
			this.fireUpdatePendingChanged(true);
		}
		this.telephone2 = telephone2 != null ? telephone2.trim() : null;
	}

	/**
	 * Set the value of nomResponsable1
	 * 
	 * @param nomResponsable1
	 *            * new value of nomResponsable1
	 */
	public void setNomResponsable1(String nomResponsable1) {
		if (this.compareFields(this.nomResponsable1, nomResponsable1)) {
			this.fireUpdatePendingChanged(true);
		}
		this.nomResponsable1 = nomResponsable1 != null ? nomResponsable1.trim()
				: null;
	}

	/**
	 * Set the value of prenomResponsable1
	 * 
	 * @param prenomResponsable1
	 *            * new value of prenomResponsable1
	 */
	public void setPrenomResponsable1(String prenomResponsable1) {
		if (this.compareFields(this.prenomResponsable1, prenomResponsable1)) {
			this.fireUpdatePendingChanged(true);
		}
		this.prenomResponsable1 = prenomResponsable1 != null ? prenomResponsable1
				.trim() : null;
	}

	/**
	 * Set the value of courriel
	 * 
	 * @param courriel
	 *            * new value of courriel
	 */
	public void setCourriel(String courriel) {
		if (this.compareFields(this.courriel, courriel)) {
			this.fireUpdatePendingChanged(true);
		}
		this.courriel = courriel != null ? courriel.trim() : null;
	}

	/**
	 * Set the value of courrielResponsable1
	 * 
	 * @param courrielResponsable1
	 *            * new value of courrielResponsable1
	 */
	public void setCourrielResponsable1(String courrielResponsable1) {
		if (this.compareFields(this.courrielResponsable1, courrielResponsable1)) {
			this.fireUpdatePendingChanged(true);
		}
		this.courrielResponsable1 = courrielResponsable1 != null ? courrielResponsable1
				.trim() : null;
	}

	/**
	 * Set the value of telephone1Responsable1
	 * 
	 * @param telephone1Responsable1
	 *            * new value of telephone1Responsable1
	 */
	public void setTelephone1Responsable1(String telephone1Responsable1) {
		if (this.compareFields(this.telephone1Responsable1,
				telephone1Responsable1)) {
			this.fireUpdatePendingChanged(true);
		}
		this.telephone1Responsable1 = telephone1Responsable1 != null ? telephone1Responsable1
				.trim() : null;
	}

	/**
	 * Set the value of telephone2Responsable1
	 * 
	 * @param telephone2Responsable1
	 *            * new value of telephone2Responsable1
	 */
	public void setTelephone2Responsable1(String telephone2Responsable1) {
		if (this.compareFields(this.telephone2Responsable1,
				telephone2Responsable1)) {
			this.fireUpdatePendingChanged(true);
		}
		this.telephone2Responsable1 = telephone2Responsable1 != null ? telephone2Responsable1
				.trim() : null;
	}

	/**
	 * Set the value of nomPere
	 * 
	 * @param nomPere
	 *            * new value of nomPere
	 */
	public void setNomPere(String nomPere) {
		if (this.compareFields(this.nomPere, nomPere)) {
			this.fireUpdatePendingChanged(true);
		}
		this.nomPere = nomPere != null ? nomPere.trim() : null;
	}

	/**
	 * Set the value of nomMere
	 * 
	 * @param nomMere
	 *            * new value of nomMere
	 */
	public void setNomMere(String nomMere) {
		if (this.compareFields(this.nomMere, nomMere)) {
			this.fireUpdatePendingChanged(true);
		}
		this.nomMere = nomMere != null ? nomMere.trim() : null;
	}

	/**
	 * Set the value of etatCivil
	 * 
	 * @param etatCivil
	 *            * new value of etatCivil
	 */
	public void setEtatCivil(String etatCivil) {
		if (this.compareFields(this.etatCivil, etatCivil)) {
			this.fireUpdatePendingChanged(true);
		}
		this.etatCivil = etatCivil != null ? etatCivil.trim() : null;
	}

	/**
	 * Set the value of nomEpoux
	 * 
	 * @param nomEpoux
	 *            * new value of nomEpoux
	 */
	public void setNomEpoux(String nomEpoux) {
		if (this.compareFields(this.nomEpoux, nomEpoux)) {
			this.fireUpdatePendingChanged(true);
		}
		this.nomEpoux = nomEpoux != null ? nomEpoux.trim() : null;
	}

	/**
	 * Set the value of prenomEpoux
	 * 
	 * @param prenomEpoux
	 *            * new value of prenomEpoux
	 */
	public void setPrenomEpoux(String prenomEpoux) {
		if (this.compareFields(this.prenomEpoux, prenomEpoux)) {
			this.fireUpdatePendingChanged(true);
		}
		this.prenomEpoux = prenomEpoux != null ? prenomEpoux.trim() : null;
	}

	/**
	 * Set the value of telephoneEpoux
	 * 
	 * @param telephoneEpoux
	 *            * new value of telephoneEpoux
	 */
	public void setTelephoneEpoux(String telephoneEpoux) {
		if (this.compareFields(this.telephoneEpoux, telephoneEpoux)) {
			this.fireUpdatePendingChanged(true);
		}
		this.telephoneEpoux = telephoneEpoux != null ? telephoneEpoux.trim()
				: null;
	}

	/**
	 * Set the value of courrielEpoux
	 * 
	 * @param courrielEpoux
	 *            * new value of courrielEpoux
	 */
	public void setCourrielEpoux(String courrielEpoux) {
		if (this.compareFields(this.courrielEpoux, courrielEpoux)) {
			this.fireUpdatePendingChanged(true);
		}
		this.courrielEpoux = courrielEpoux != null ? courrielEpoux.trim()
				: null;
	}

	/**
	 * Set the value of prenomPere
	 * 
	 * @param prenomPere
	 *            * new value of prenomPere
	 */
	public void setPrenomPere(String prenomPere) {
		if (this.compareFields(this.prenomPere, prenomPere)) {
			this.fireUpdatePendingChanged(true);
		}
		this.prenomPere = prenomPere != null ? prenomPere.trim() : null;
	}

	/**
	 * Set the value of prenomMere
	 * 
	 * @param prenomMere
	 *            * new value of prenomMere
	 */
	public void setPrenomMere(String prenomMere) {
		if (this.compareFields(this.prenomMere, prenomMere)) {
			this.fireUpdatePendingChanged(true);
		}
		this.prenomMere = prenomMere != null ? prenomMere.trim() : null;
	}

	/**
	 * Set the value of pereId
	 * 
	 * @param pereId
	 *            * new value of pereId
	 */
	public void setPereId(Integer pereId) {
		if (this.compareFields(this.pereId, pereId)) {
			this.fireUpdatePendingChanged(true);
		}
		this.pereId = pereId;
	}

	/**
	 * Set the value of mereId
	 * 
	 * @param mereId
	 *            * new value of mereId
	 */
	public void setMereId(Integer mereId) {
		if (this.compareFields(this.mereId, mereId)) {
			this.fireUpdatePendingChanged(true);
		}
		this.mereId = mereId;
	}

	/**
	 * Set the value of epouxId
	 * 
	 * @param epouxId
	 *            * new value of epouxId
	 */
	public void setEpouxId(Integer epouxId) {
		if (this.compareFields(this.epouxId, epouxId)) {
			this.fireUpdatePendingChanged(true);
		}
		this.epouxId = epouxId;
	}

	/**
	 * Set the value of nationalite
	 * 
	 * @param nationalite
	 *            * new value of nationalite
	 */
	public void setNationalite(String nationalite) {
		if (this.compareFields(this.nationalite, nationalite)) {
			this.fireUpdatePendingChanged(true);
		}
		this.nationalite = nationalite != null ? nationalite.trim() : null;
	}

	/**
	 * Set the value of paysResidence
	 * 
	 * @param paysResidence
	 *            * new value of paysResidence
	 */
	public void setPaysResidence(String paysResidence) {
		if (this.compareFields(this.paysResidence, paysResidence)) {
			this.fireUpdatePendingChanged(true);
		}
		this.paysResidence = paysResidence != null ? paysResidence.trim()
				: null;
	}

	/**
	 * Set the value of adresse
	 * 
	 * @param adresse
	 *            * new value of adresse
	 */
	public void setAdresse(String adresse) {
		if (this.compareFields(this.adresse, adresse)) {
			this.fireUpdatePendingChanged(true);
		}
		this.adresse = adresse != null ? adresse.trim() : null;
	}

	/**
	 * Set the value of sexe
	 * 
	 * @param sexe
	 *            * new value of sexe
	 */
	public void setSexe(String sexe) {
		if (this.compareFields(this.sexe, sexe)) {
			this.fireUpdatePendingChanged(true);
		}
		this.sexe = sexe != null ? sexe.trim() : null;
	}

	/**
	 * Set the value of typeIdentication
	 * 
	 * @param typeIdentication
	 *            * new value of typeIdentication
	 */
	public void setTypeIdentication(String typeIdentication) {
		if (this.compareFields(this.typeIdentication, typeIdentication)) {
			this.fireUpdatePendingChanged(true);
		}
		this.typeIdentication = typeIdentication != null ? typeIdentication
				.trim() : null;
	}

	/**
	 * Set the value of noIdentification
	 * 
	 * @param noIdentification
	 *            * new value of noIdentification
	 */
	public void setNoIdentification(String noIdentification) {
		if (this.compareFields(this.noIdentification, noIdentification)) {
			this.fireUpdatePendingChanged(true);
		}
		this.noIdentification = noIdentification != null ? noIdentification
				.trim() : null;
	}

	/**
	 * Set the value of paysIdentifcation
	 * 
	 * @param paysIdentifcation
	 *            * new value of paysIdentifcation
	 */
	public void setPaysIdentifcation(String paysIdentifcation) {
		if (this.compareFields(this.paysIdentifcation, paysIdentifcation)) {
			this.fireUpdatePendingChanged(true);
		}
		this.paysIdentifcation = paysIdentifcation != null ? paysIdentifcation
				.trim() : null;
	}

	/**
	 * Set the value of nomEnfants
	 * 
	 * @param nomEnfants
	 *            * new value of nomEnfants
	 */
	public void setNomEnfants(Integer nomEnfants) {
		if (this.compareFields(this.nomEnfants, nomEnfants)) {
			this.fireUpdatePendingChanged(true);
		}
		this.nomEnfants = nomEnfants;
	}

	/**
	 * Set the value of lienParenteResponsable1
	 * 
	 * @param lienParenteResponsable1
	 *            * new value of lienParenteResponsable1
	 */
	public void setLienParenteResponsable1(String lienParenteResponsable1) {
		if (this.compareFields(this.lienParenteResponsable1,
				lienParenteResponsable1)) {
			this.fireUpdatePendingChanged(true);
		}
		this.lienParenteResponsable1 = lienParenteResponsable1 != null ? lienParenteResponsable1
				.trim() : null;
	}

	/**
	 * Set the value of telephonePere
	 * 
	 * @param telephonePere
	 *            * new value of telephonePere
	 */
	public void setTelephonePere(String telephonePere) {
		if (this.compareFields(this.telephonePere, telephonePere)) {
			this.fireUpdatePendingChanged(true);
		}
		this.telephonePere = telephonePere != null ? telephonePere.trim()
				: null;
	}

	/**
	 * Set the value of telephoneMere
	 * 
	 * @param telephoneMere
	 *            * new value of telephoneMere
	 */
	public void setTelephoneMere(String telephoneMere) {
		if (this.compareFields(this.telephoneMere, telephoneMere)) {
			this.fireUpdatePendingChanged(true);
		}
		this.telephoneMere = telephoneMere != null ? telephoneMere.trim()
				: null;
	}

	/**
	 * Set the value of nomResponsable2
	 * 
	 * @param nomResponsable2
	 *            * new value of nomResponsable2
	 */
	public void setNomResponsable2(String nomResponsable2) {
		if (this.compareFields(this.nomResponsable2, nomResponsable2)) {
			this.fireUpdatePendingChanged(true);
		}
		this.nomResponsable2 = nomResponsable2 != null ? nomResponsable2.trim()
				: null;
	}

	/**
	 * Set the value of prenomResponsable2
	 * 
	 * @param prenomResponsable2
	 *            * new value of prenomResponsable2
	 */
	public void setPrenomResponsable2(String prenomResponsable2) {
		if (this.compareFields(this.prenomResponsable2, prenomResponsable2)) {
			this.fireUpdatePendingChanged(true);
		}
		this.prenomResponsable2 = prenomResponsable2 != null ? prenomResponsable2
				.trim() : null;
	}

	/**
	 * Set the value of courrielResponsable2
	 * 
	 * @param courrielResponsable2
	 *            * new value of courrielResponsable2
	 */
	public void setCourrielResponsable2(String courrielResponsable2) {
		if (this.compareFields(this.courrielResponsable2, courrielResponsable2)) {
			this.fireUpdatePendingChanged(true);
		}
		this.courrielResponsable2 = courrielResponsable2 != null ? courrielResponsable2
				.trim() : null;
	}

	/**
	 * Set the value of telephone1Responsable2
	 * 
	 * @param telephone1Responsable2
	 *            * new value of telephone1Responsable2
	 */
	public void setTelephone1Responsable2(String telephone1Responsable2) {
		if (this.compareFields(this.telephone1Responsable2,
				telephone1Responsable2)) {
			this.fireUpdatePendingChanged(true);
		}
		this.telephone1Responsable2 = telephone1Responsable2 != null ? telephone1Responsable2
				.trim() : null;
	}

	/**
	 * Set the value of telephone2Responsable2
	 * 
	 * @param telephone2Responsable2
	 *            * new value of telephone2Responsable2
	 */
	public void setTelephone2Responsable2(String telephone2Responsable2) {
		if (this.compareFields(this.telephone2Responsable2,
				telephone2Responsable2)) {
			this.fireUpdatePendingChanged(true);
		}
		this.telephone2Responsable2 = telephone2Responsable2 != null ? telephone2Responsable2
				.trim() : null;
	}

	/**
	 * Set the value of adresseResponsable1
	 * 
	 * @param adresseResponsable1
	 *            * new value of adresseResponsable1
	 */
	public void setAdresseResponsable1(String adresseResponsable1) {
		if (this.compareFields(this.adresseResponsable1, adresseResponsable1)) {
			this.fireUpdatePendingChanged(true);
		}
		this.adresseResponsable1 = adresseResponsable1 != null ? adresseResponsable1
				.trim() : null;
	}

	/**
	 * Set the value of adresseResponsable2
	 * 
	 * @param adresseResponsable2
	 *            * new value of adresseResponsable2
	 */
	public void setAdresseResponsable2(String adresseResponsable2) {
		if (this.compareFields(this.adresseResponsable2, adresseResponsable2)) {
			this.fireUpdatePendingChanged(true);
		}
		this.adresseResponsable2 = adresseResponsable2 != null ? adresseResponsable2
				.trim() : null;
	}

	/**
	 * Set the value of taille
	 * 
	 * @param taille
	 *            * new value of taille
	 */
	public void setTaille(String taille) {
		if (this.compareFields(this.taille, taille)) {
			this.fireUpdatePendingChanged(true);
		}
		this.taille = taille != null ? taille.trim() : null;
	}

	/**
	 * Set the value of poids
	 * 
	 * @param poids
	 *            * new value of poids
	 */
	public void setPoids(String poids) {
		if (this.compareFields(this.poids, poids)) {
			this.fireUpdatePendingChanged(true);
		}
		this.poids = poids != null ? poids.trim() : null;
	}

	/**
	 * Set the value of zone
	 * 
	 * @param zone
	 *            * new value of zone
	 */
	public void setZone(String zone) {
		if (this.compareFields(this.zone, zone)) {
			this.fireUpdatePendingChanged(true);
		}
		this.zone = zone != null ? zone.trim() : null;
	}

	/**
	 * Set the value of commune
	 * 
	 * @param commune
	 *            * new value of commune
	 */
	public void setCommune(String commune) {
		if (this.compareFields(this.commune, commune)) {
			this.fireUpdatePendingChanged(true);
		}
		this.commune = commune != null ? commune.trim() : null;
	}

	/**
	 * Set the value of departement
	 * 
	 * @param departement
	 *            * new value of departement
	 */
	public void setDepartement(String departement) {
		if (this.compareFields(this.departement, departement)) {
			this.fireUpdatePendingChanged(true);
		}
		this.departement = departement != null ? departement.trim() : null;
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
		Patient other = (Patient) obj;
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
		Patient model = (Patient) modelBase;
		setEntityId(model.getEntityId());
		setErrorMessage(model.getErrorMessage());
		setCreatedBy(model.getCreatedBy());
		setCreatedOn(model.getCreatedOn());
		setUpdatedBy(model.getUpdatedBy());
		setUpdatedOn(model.getUpdatedOn());
		setRowscn(model.getRowscn());
		setRowscn(model.getRowscn());

		nom = model.getNom();
		prenom = model.getPrenom();
		statut = model.getStatut();
		dateNaissance = model.getDateNaissance();
		lieuNaissance = model.getLieuNaissance();
		dateDeces = model.getDateDeces();
		lieuDeces = model.getLieuDeces();
		heureNaissance = model.getHeureNaissance();
		heureDeces = model.getHeureDeces();
		causeDeces = model.getCauseDeces();
		telephone1 = model.getTelephone1();
		telephone2 = model.getTelephone2();
		nomResponsable1 = model.getNomResponsable1();
		prenomResponsable1 = model.getPrenomResponsable1();
		courriel = model.getCourriel();
		courrielResponsable1 = model.getCourrielResponsable1();
		telephone1Responsable1 = model.getTelephone1Responsable1();
		telephone2Responsable1 = model.getTelephone2Responsable1();
		religion = model.getReligion();
		nomPere = model.getNomPere();
		nomMere = model.getNomMere();
		etatCivil = model.getEtatCivil();
		nomEpoux = model.getNomEpoux();
		prenomEpoux = model.getPrenomEpoux();
		telephoneEpoux = model.getTelephoneEpoux();
		courrielEpoux = model.getCourrielEpoux();
		prenomPere = model.getPrenomPere();
		prenomMere = model.getPrenomMere();
		pereId = model.getPereId();
		mereId = model.getMereId();
		epouxId = model.getEpouxId();
		nationalite = model.getNationalite();
		paysResidence = model.getPaysResidence();
		adresse = model.getAdresse();
		sexe = model.getSexe();
		typeIdentication = model.getTypeIdentication();
		noIdentification = model.getNoIdentification();
		paysIdentifcation = model.getPaysIdentifcation();
		nomEnfants = model.getNomEnfants();
		lienParenteResponsable1 = model.getLienParenteResponsable1();
		telephonePere = model.getTelephonePere();
		telephoneMere = model.getTelephoneMere();
		nomResponsable2 = model.getNomResponsable2();
		prenomResponsable2 = model.getPrenomResponsable2();
		courrielResponsable2 = model.getCourrielResponsable2();
		telephone1Responsable2 = model.getTelephone1Responsable2();
		telephone2Responsable2 = model.getTelephone2Responsable2();
		adresseResponsable1 = model.getAdresseResponsable1();
		adresseResponsable2 = model.getAdresseResponsable2();
		taille = model.getTaille();
		poids = model.getPoids();
		zone = model.getZone();
		commune = model.getCommune();
		departement = model.getDepartement();
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
		if (prenom == null || prenom.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("prenom");
			} else {
				builder.append("|prenom");
			}

		}
		if (statut == null || statut.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("statut");
			} else {
				builder.append("|statut");
			}

		}
		if (sexe == null || sexe.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("sexe");
			} else {
				builder.append("|sexe");
			}

		}

		if (religion == null || religion.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("religion");
			} else {
				builder.append("|religion");
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

	public static Patient parse(String value) {
		Patient model = new Patient();
		return model;
	}
}