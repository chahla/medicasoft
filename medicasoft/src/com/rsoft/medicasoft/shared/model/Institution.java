package com.rsoft.medicasoft.shared.model;

/*
 Templates Generator
 */

/*@Author=Jean Louidort*/
/*@Generation Date=Sat Nov 02 22:13:10 EDT 2013*/
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Institution extends ModelBaseX implements Serializable {
	private static final long serialVersionUID = 1L;

	@Index({ IfNotNull.class })
	@Load
	private Ref<Zone> zoneId;
	@IgnoreSave
	private Zone zone;
	@Index({ IfNotNull.class })
	@Load
	private Ref<Departement> departementId;
	@IgnoreSave
	private Departement departement;
	@Index({ IfNotNull.class })
	@Load
	private Ref<Pays> paysId;
	@IgnoreSave
	private Pays pays;
	@Index({ IfNotNull.class })
	@Load
	private Ref<Commune> communeId;
	@IgnoreSave
	private Commune commune;
	@Index
	private String nomInstitution;

	private String nomCommercialeInstitution;
	@Index
	private Date dateCreation;

	private String adresse;
	@Index
	private String telephone1;
	@Index
	private String telephone2;
	@Index
	private String telephone3;
	@Index
	private String courriel;

	@Load
	private List<TypeInstitution> typeInstitutions = new ArrayList<TypeInstitution>();

	@Index
	private String nomReponsable;
	@Index
	private String prenomResponsable;

	private String extensionResponsable;
	@Index
	private String courrielResponsable;
	@Index
	private String titreResponsable;
	@Index
	private String sexeResponsable;
	@Index
	private String modalitePaiement;
	@Index
	private String typePaiement;

	private Integer balance;
	@Index
	private Date dateBalance;
	@Index
	private String statut;

	private Integer delaiGrace;
	@Index
	private String nomContact;
	@Index
	private String prenomContact;
	@Index
	private String courrielContact;
	@Index
	private String telephoneContact;
	@Index
	private String telephoneResponsable;
	@Index
	private String titreContact;

	public Institution() {
	}

	/**
	 * Get the value of zone
	 * 
	 * @return the value of zone Get the value zone
	 */
	public Ref<Zone> getZoneId() {
		return this.zoneId;
	}

	/**
	 * Get the value of zone
	 * 
	 * @return the value of zone Get the value zone
	 */
	public Zone getZone() {
		return this.zone;
	}

	/**
	 * Get the value of departement
	 * 
	 * @return the value of departement Get the value departement
	 */
	public Ref<Departement> getDepartementId() {
		return this.departementId;
	}

	/**
	 * Get the value of departement
	 * 
	 * @return the value of departement Get the value departement
	 */
	public Departement getDepartement() {
		return this.departement;
	}

	/**
	 * Get the value of pays
	 * 
	 * @return the value of pays Get the value pays
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
	 * Get the value of commune
	 * 
	 * @return the value of commune Get the value commune
	 */
	public Ref<Commune> getCommuneId() {
		return this.communeId;
	}

	/**
	 * Get the value of commune
	 * 
	 * @return the value of commune Get the value commune
	 */
	public Commune getCommune() {
		return this.commune;
	}

	/**
	 * Get the value of nomInstitution
	 * 
	 * @return the value of nomInstitution Get the value nomInstitution
	 */
	public String getNomInstitution() {
		return this.nomInstitution;
	}

	/**
	 * Get the value of nomCommercialeInstitution
	 * 
	 * @return the value of nomCommercialeInstitution Get the value
	 *         nomCommercialeInstitution
	 */
	public String getNomCommercialeInstitution() {
		return this.nomCommercialeInstitution;
	}

	/**
	 * Get the value of dateCreation
	 * 
	 * @return the value of dateCreation Get the value dateCreation
	 */
	public Date getDateCreation() {
		return this.dateCreation;
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
	 * Get the value of telephone3
	 * 
	 * @return the value of telephone3 Get the value telephone3
	 */
	public String getTelephone3() {
		return this.telephone3;
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
	 * Get the value of typeInstitutionId
	 * 
	 * @return the value of typeInstitutionId Get the value typeInstitutionId
	 */
	public List<TypeInstitution> getTypeInstitutions() {
		return this.typeInstitutions;
	}

	/**
	 * Get the value of nomReponsable
	 * 
	 * @return the value of nomReponsable Get the value nomReponsable
	 */
	public String getNomReponsable() {
		return this.nomReponsable;
	}

	/**
	 * Get the value of prenomResponsable
	 * 
	 * @return the value of prenomResponsable Get the value prenomResponsable
	 */
	public String getPrenomResponsable() {
		return this.prenomResponsable;
	}

	/**
	 * Get the value of extensionResponsable
	 * 
	 * @return the value of extensionResponsable Get the value
	 *         extensionResponsable
	 */
	public String getExtensionResponsable() {
		return this.extensionResponsable;
	}

	/**
	 * Get the value of courrielResponsable
	 * 
	 * @return the value of courrielResponsable Get the value
	 *         courrielResponsable
	 */
	public String getCourrielResponsable() {
		return this.courrielResponsable;
	}

	/**
	 * Get the value of titreResponsable
	 * 
	 * @return the value of titreResponsable Get the value titreResponsable
	 */
	public String getTitreResponsable() {
		return this.titreResponsable;
	}

	/**
	 * Get the value of sexeResponsable
	 * 
	 * @return the value of sexeResponsable Get the value sexeResponsable
	 */
	public String getSexeResponsable() {
		return this.sexeResponsable;
	}

	/**
	 * Get the value of modalitePaiement
	 * 
	 * @return the value of modalitePaiement Get the value modalitePaiement
	 */
	public String getModalitePaiement() {
		return this.modalitePaiement;
	}

	/**
	 * Get the value of typePaiement
	 * 
	 * @return the value of typePaiement Get the value typePaiement
	 */
	public String getTypePaiement() {
		return this.typePaiement;
	}

	/**
	 * Get the value of balance
	 * 
	 * @return the value of balance Get the value balance
	 */
	public Integer getBalance() {
		return this.balance;
	}

	/**
	 * Get the value of dateBalance
	 * 
	 * @return the value of dateBalance Get the value dateBalance
	 */
	public Date getDateBalance() {
		return this.dateBalance;
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
	 * Get the value of delaiGrace
	 * 
	 * @return the value of delaiGrace Get the value delaiGrace
	 */
	public Integer getDelaiGrace() {
		return this.delaiGrace;
	}

	/**
	 * Get the value of nomContact
	 * 
	 * @return the value of nomContact Get the value nomContact
	 */
	public String getNomContact() {
		return this.nomContact;
	}

	/**
	 * Get the value of prenomContact
	 * 
	 * @return the value of prenomContact Get the value prenomContact
	 */
	public String getPrenomContact() {
		return this.prenomContact;
	}

	/**
	 * Get the value of courrielContact
	 * 
	 * @return the value of courrielContact Get the value courrielContact
	 */
	public String getCourrielContact() {
		return this.courrielContact;
	}

	/**
	 * Get the value of telephoneContact
	 * 
	 * @return the value of telephoneContact Get the value telephoneContact
	 */
	public String getTelephoneContact() {
		return this.telephoneContact;
	}

	/**
	 * Get the value of telephoneResponsable
	 * 
	 * @return the value of telephoneResponsable Get the value
	 *         telephoneResponsable
	 */
	public String getTelephoneResponsable() {
		return this.telephoneResponsable;
	}

	/**
	 * Get the value of titreContact
	 * 
	 * @return the value of titreContact Get the value titreContact
	 */
	public String getTitreContact() {
		return this.titreContact;
	}

	/**
	 * Set the value of zone
	 * 
	 * @param zone
	 *            * new value of zone
	 */
	public void setZoneId(Ref<Zone> zoneId) {
		this.zoneId = zoneId;
	}

	/**
	 * Set the value of zone
	 * 
	 * @param zone
	 *            * new value of zone
	 */
	public void setZone(Zone zone) {
		this.zone = zone;
	}

	/**
	 * Set the value of departement
	 * 
	 * @param departement
	 *            * new value of departement
	 */
	public void setDepartementId(Ref<Departement> departementId) {
		this.departementId = departementId;
	}

	/**
	 * Set the value of departement
	 * 
	 * @param departement
	 *            * new value of departement
	 */
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	/**
	 * Set the value of pays
	 * 
	 * @param pays
	 *            * new value of pays
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
	 * Set the value of commune
	 * 
	 * @param commune
	 *            * new value of commune
	 */
	public void setCommuneId(Ref<Commune> communeId) {
		this.communeId = communeId;
	}

	/**
	 * Set the value of commune
	 * 
	 * @param commune
	 *            * new value of commune
	 */
	public void setCommune(Commune commune) {
		this.commune = commune;
	}

	/**
	 * Set the value of nomInstitution
	 * 
	 * @param nomInstitution
	 *            * new value of nomInstitution
	 */
	public void setNomInstitution(String nomInstitution) {
		if (this.compareFields(this.nomInstitution, nomInstitution)) {
			this.fireUpdatePendingChanged(true);
		}
		this.nomInstitution = nomInstitution != null ? nomInstitution.trim()
				: null;
	}

	/**
	 * Set the value of nomCommercialeInstitution
	 * 
	 * @param nomCommercialeInstitution
	 *            * new value of nomCommercialeInstitution
	 */
	public void setNomCommercialeInstitution(String nomCommercialeInstitution) {
		if (this.compareFields(this.nomCommercialeInstitution,
				nomCommercialeInstitution)) {
			this.fireUpdatePendingChanged(true);
		}
		this.nomCommercialeInstitution = nomCommercialeInstitution != null ? nomCommercialeInstitution
				.trim() : null;
	}

	/**
	 * Set the value of dateCreation
	 * 
	 * @param dateCreation
	 *            * new value of dateCreation
	 */
	public void setDateCreation(Date dateCreation) {
		if (this.compareFields(this.dateCreation, dateCreation)) {
			this.fireUpdatePendingChanged(true);
		}
		this.dateCreation = dateCreation;
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
	 * Set the value of telephone3
	 * 
	 * @param telephone3
	 *            * new value of telephone3
	 */
	public void setTelephone3(String telephone3) {
		if (this.compareFields(this.telephone3, telephone3)) {
			this.fireUpdatePendingChanged(true);
		}
		this.telephone3 = telephone3 != null ? telephone3.trim() : null;
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
	 * Set the value of typeInstitutionId
	 * 
	 * @param typeInstitutionId
	 *            * new value of typeInstitutionId
	 */
	public void setTypeInstitutions(List<TypeInstitution> typeInstitutions) {
		if (this.compareFields(this.typeInstitutions, typeInstitutions)) {
			this.fireUpdatePendingChanged(true);
		}
		this.typeInstitutions = typeInstitutions;
	}

	public boolean isTypeInstitutionDefined(String typeInstitution) {
		if ("HOPITAL".equalsIgnoreCase(typeInstitution)) {
			return this.typeInstitutions.contains(TypeInstitution.HOPITAL);

		} else if ("CLINIQUE".equalsIgnoreCase(typeInstitution)) {
			return this.typeInstitutions.contains(TypeInstitution.CLINIQUE);

		} else if ("CENTRE_SANTE".equalsIgnoreCase(typeInstitution)) {
			return this.typeInstitutions.contains(TypeInstitution.CENTRE_SANTE);

		} else if ("PHARMACIE".equalsIgnoreCase(typeInstitution)) {
			return this.typeInstitutions.contains(TypeInstitution.PHARMACIE);

		} else if ("LABORATOIRE".equalsIgnoreCase(typeInstitution)) {
			return this.typeInstitutions.contains(TypeInstitution.LABORATOIRE);

		} else if ("EMPLOYEUR".equalsIgnoreCase(typeInstitution)) {
			return this.typeInstitutions.contains(TypeInstitution.EMPLOYEUR);

		} else if ("ASSUREUR".equalsIgnoreCase(typeInstitution)) {
			return this.typeInstitutions.contains(TypeInstitution.ASSUREUR);

		} else if ("ONG".equalsIgnoreCase(typeInstitution)) {
			return this.typeInstitutions.contains(TypeInstitution.ONG);

		} else if ("ETAT_CIVIL".equalsIgnoreCase(typeInstitution)) {
			return this.typeInstitutions.contains(TypeInstitution.ETAT_CIVIL);

		} else if ("ARCHIVES".equalsIgnoreCase(typeInstitution)) {
			return this.typeInstitutions.contains(TypeInstitution.ARCHIVES);

		} else if ("GOUVERNEMENT".equalsIgnoreCase(typeInstitution)) {
			return this.typeInstitutions.contains(TypeInstitution.GOUVERNEMENT);
		}
		return false;
	}
	/**
	 * Set the value of typeInstitutionId
	 * 
	 * @param typeInstitutionId
	 *            * new value of typeInstitutionId
	 */
	public void addTypeInstitution(String typeInstitution, boolean checked) {
		this.fireUpdatePendingChanged(true);
		if (this.typeInstitutions == null) {
			this.typeInstitutions = new ArrayList<TypeInstitution>();
		}
		if (checked) {
			if ("HOPITAL".equalsIgnoreCase(typeInstitution)) {
				this.typeInstitutions.add(TypeInstitution.HOPITAL);

			} else if ("CLINIQUE".equalsIgnoreCase(typeInstitution)) {
				this.typeInstitutions.add(TypeInstitution.CLINIQUE);

			} else if ("CENTRE_SANTE".equalsIgnoreCase(typeInstitution)) {
				this.typeInstitutions.add(TypeInstitution.CENTRE_SANTE);

			} else if ("PHARMACIE".equalsIgnoreCase(typeInstitution)) {
				this.typeInstitutions.add(TypeInstitution.PHARMACIE);

			} else if ("LABORATOIRE".equalsIgnoreCase(typeInstitution)) {
				this.typeInstitutions.add(TypeInstitution.LABORATOIRE);

			} else if ("EMPLOYEUR".equalsIgnoreCase(typeInstitution)) {
				this.typeInstitutions.add(TypeInstitution.EMPLOYEUR);

			} else if ("ASSUREUR".equalsIgnoreCase(typeInstitution)) {
				this.typeInstitutions.add(TypeInstitution.ASSUREUR);

			} else if ("ONG".equalsIgnoreCase(typeInstitution)) {
				this.typeInstitutions.add(TypeInstitution.ONG);

			} else if ("ETAT_CIVIL".equalsIgnoreCase(typeInstitution)) {
				this.typeInstitutions.add(TypeInstitution.ETAT_CIVIL);

			} else if ("ARCHIVES".equalsIgnoreCase(typeInstitution)) {
				this.typeInstitutions.add(TypeInstitution.ARCHIVES);

			} else if ("GOUVERNEMENT".equalsIgnoreCase(typeInstitution)) {
				this.typeInstitutions.add(TypeInstitution.GOUVERNEMENT);
			}
		} else {
			if ("HOPITAL".equalsIgnoreCase(typeInstitution)) {
				this.typeInstitutions.remove(TypeInstitution.HOPITAL);

			} else if ("CLINIQUE".equalsIgnoreCase(typeInstitution)) {
				this.typeInstitutions.remove(TypeInstitution.CLINIQUE);

			} else if ("CENTRE_SANTE".equalsIgnoreCase(typeInstitution)) {
				this.typeInstitutions.remove(TypeInstitution.CENTRE_SANTE);

			} else if ("PHARMACIE".equalsIgnoreCase(typeInstitution)) {
				this.typeInstitutions.remove(TypeInstitution.PHARMACIE);

			} else if ("LABORATOIRE".equalsIgnoreCase(typeInstitution)) {
				this.typeInstitutions.remove(TypeInstitution.LABORATOIRE);

			} else if ("EMPLOYEUR".equalsIgnoreCase(typeInstitution)) {
				this.typeInstitutions.remove(TypeInstitution.EMPLOYEUR);

			} else if ("ASSUREUR".equalsIgnoreCase(typeInstitution)) {
				this.typeInstitutions.remove(TypeInstitution.ASSUREUR);

			} else if ("ONG".equalsIgnoreCase(typeInstitution)) {
				this.typeInstitutions.remove(TypeInstitution.ONG);

			} else if ("ETAT_CIVIL".equalsIgnoreCase(typeInstitution)) {
				this.typeInstitutions.remove(TypeInstitution.ETAT_CIVIL);

			} else if ("ARCHIVES".equalsIgnoreCase(typeInstitution)) {
				this.typeInstitutions.remove(TypeInstitution.ARCHIVES);

			} else if ("GOUVERNEMENT".equalsIgnoreCase(typeInstitution)) {
				this.typeInstitutions.remove(TypeInstitution.GOUVERNEMENT);
			}
		}
	}

	/**
	 * Set the value of typeInstitutionId
	 * 
	 * @param typeInstitutionId
	 *            * new value of typeInstitutionId
	 */
	public void addTypeInstitution(TypeInstitution typeInstitution) {
		this.fireUpdatePendingChanged(true);
		if (this.typeInstitutions == null) {
			this.typeInstitutions = new ArrayList<TypeInstitution>();
		}
		this.typeInstitutions.add(typeInstitution);
	}

	public void removeTypeInstitution(String typeInstitution) {
		this.fireUpdatePendingChanged(true);
		if (this.typeInstitutions != null) {
			this.typeInstitutions.remove(typeInstitution);
		}
	}

	public void clearTypeInstitutions() {
		this.fireUpdatePendingChanged(true);
		if (this.typeInstitutions != null) {
			this.typeInstitutions.clear();
		}
	}

	/**
	 * Set the value of nomReponsable
	 * 
	 * @param nomReponsable
	 *            * new value of nomReponsable
	 */
	public void setNomReponsable(String nomReponsable) {
		if (this.compareFields(this.nomReponsable, nomReponsable)) {
			this.fireUpdatePendingChanged(true);
		}
		this.nomReponsable = nomReponsable != null ? nomReponsable.trim()
				: null;
	}

	/**
	 * Set the value of prenomResponsable
	 * 
	 * @param prenomResponsable
	 *            * new value of prenomResponsable
	 */
	public void setPrenomResponsable(String prenomResponsable) {
		if (this.compareFields(this.prenomResponsable, prenomResponsable)) {
			this.fireUpdatePendingChanged(true);
		}
		this.prenomResponsable = prenomResponsable != null ? prenomResponsable
				.trim() : null;
	}

	/**
	 * Set the value of extensionResponsable
	 * 
	 * @param extensionResponsable
	 *            * new value of extensionResponsable
	 */
	public void setExtensionResponsable(String extensionResponsable) {
		if (this.compareFields(this.extensionResponsable, extensionResponsable)) {
			this.fireUpdatePendingChanged(true);
		}
		this.extensionResponsable = extensionResponsable != null ? extensionResponsable
				.trim() : null;
	}

	/**
	 * Set the value of courrielResponsable
	 * 
	 * @param courrielResponsable
	 *            * new value of courrielResponsable
	 */
	public void setCourrielResponsable(String courrielResponsable) {
		if (this.compareFields(this.courrielResponsable, courrielResponsable)) {
			this.fireUpdatePendingChanged(true);
		}
		this.courrielResponsable = courrielResponsable != null ? courrielResponsable
				.trim() : null;
	}

	/**
	 * Set the value of titreResponsable
	 * 
	 * @param titreResponsable
	 *            * new value of titreResponsable
	 */
	public void setTitreResponsable(String titreResponsable) {
		if (this.compareFields(this.titreResponsable, titreResponsable)) {
			this.fireUpdatePendingChanged(true);
		}
		this.titreResponsable = titreResponsable != null ? titreResponsable
				.trim() : null;
	}

	/**
	 * Set the value of sexeResponsable
	 * 
	 * @param sexeResponsable
	 *            * new value of sexeResponsable
	 */
	public void setSexeResponsable(String sexeResponsable) {
		if (this.compareFields(this.sexeResponsable, sexeResponsable)) {
			this.fireUpdatePendingChanged(true);
		}
		this.sexeResponsable = sexeResponsable != null ? sexeResponsable.trim()
				: null;
	}

	/**
	 * Set the value of modalitePaiement
	 * 
	 * @param modalitePaiement
	 *            * new value of modalitePaiement
	 */
	public void setModalitePaiement(String modalitePaiement) {
		if (this.compareFields(this.modalitePaiement, modalitePaiement)) {
			this.fireUpdatePendingChanged(true);
		}
		this.modalitePaiement = modalitePaiement != null ? modalitePaiement
				.trim() : null;
	}

	/**
	 * Set the value of typePaiement
	 * 
	 * @param typePaiement
	 *            * new value of typePaiement
	 */
	public void setTypePaiement(String typePaiement) {
		if (this.compareFields(this.typePaiement, typePaiement)) {
			this.fireUpdatePendingChanged(true);
		}
		this.typePaiement = typePaiement != null ? typePaiement.trim() : null;
	}

	/**
	 * Set the value of balance
	 * 
	 * @param balance
	 *            * new value of balance
	 */
	public void setBalance(Integer balance) {
		if (this.compareFields(this.balance, balance)) {
			this.fireUpdatePendingChanged(true);
		}
		this.balance = balance;
	}

	/**
	 * Set the value of dateBalance
	 * 
	 * @param dateBalance
	 *            * new value of dateBalance
	 */
	public void setDateBalance(Date dateBalance) {
		if (this.compareFields(this.dateBalance, dateBalance)) {
			this.fireUpdatePendingChanged(true);
		}
		this.dateBalance = dateBalance;
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
	 * Set the value of delaiGrace
	 * 
	 * @param delaiGrace
	 *            * new value of delaiGrace
	 */
	public void setDelaiGrace(Integer delaiGrace) {
		if (this.compareFields(this.delaiGrace, delaiGrace)) {
			this.fireUpdatePendingChanged(true);
		}
		this.delaiGrace = delaiGrace;
	}

	/**
	 * Set the value of nomContact
	 * 
	 * @param nomContact
	 *            * new value of nomContact
	 */
	public void setNomContact(String nomContact) {
		if (this.compareFields(this.nomContact, nomContact)) {
			this.fireUpdatePendingChanged(true);
		}
		this.nomContact = nomContact != null ? nomContact.trim() : null;
	}

	/**
	 * Set the value of prenomContact
	 * 
	 * @param prenomContact
	 *            * new value of prenomContact
	 */
	public void setPrenomContact(String prenomContact) {
		if (this.compareFields(this.prenomContact, prenomContact)) {
			this.fireUpdatePendingChanged(true);
		}
		this.prenomContact = prenomContact != null ? prenomContact.trim()
				: null;
	}

	/**
	 * Set the value of courrielContact
	 * 
	 * @param courrielContact
	 *            * new value of courrielContact
	 */
	public void setCourrielContact(String courrielContact) {
		if (this.compareFields(this.courrielContact, courrielContact)) {
			this.fireUpdatePendingChanged(true);
		}
		this.courrielContact = courrielContact != null ? courrielContact.trim()
				: null;
	}

	/**
	 * Set the value of telephoneContact
	 * 
	 * @param telephoneContact
	 *            * new value of telephoneContact
	 */
	public void setTelephoneContact(String telephoneContact) {
		if (this.compareFields(this.telephoneContact, telephoneContact)) {
			this.fireUpdatePendingChanged(true);
		}
		this.telephoneContact = telephoneContact != null ? telephoneContact
				.trim() : null;
	}

	/**
	 * Set the value of telephoneResponsable
	 * 
	 * @param telephoneResponsable
	 *            * new value of telephoneResponsable
	 */
	public void setTelephoneResponsable(String telephoneResponsable) {
		if (this.compareFields(this.telephoneResponsable, telephoneResponsable)) {
			this.fireUpdatePendingChanged(true);
		}
		this.telephoneResponsable = telephoneResponsable != null ? telephoneResponsable
				.trim() : null;
	}

	/**
	 * Set the value of titreContact
	 * 
	 * @param titreContact
	 *            * new value of titreContact
	 */
	public void setTitreContact(String titreContact) {
		if (this.compareFields(this.titreContact, titreContact)) {
			this.fireUpdatePendingChanged(true);
		}
		this.titreContact = titreContact != null ? titreContact.trim() : null;
	}

	@OnSave
	public void beforeSave() throws PersistenceException {
		if (zoneId == null) {
			zoneId = Ref.create(zone);
		}
		if (departementId == null) {
			departementId = Ref.create(departement);
		}
		if (paysId == null) {
			paysId = Ref.create(pays);
		}
		if (communeId == null) {
			communeId = Ref.create(commune);
		}
	}

	@OnLoad
	public void afterLoad() throws PersistenceException {
		if (zone == null) {
			zone = zoneId.getValue();
		}
		if (departement == null) {
			departement = departementId.getValue();
		}
		if (pays == null) {
			pays = paysId.getValue();
		}
		if (commune == null) {
			commune = communeId.getValue();
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
		Institution other = (Institution) obj;
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
		Institution model = (Institution) modelBase;
		setEntityId(model.getEntityId());
		setErrorMessage(model.getErrorMessage());
		setCreatedBy(model.getCreatedBy());
		setCreatedOn(model.getCreatedOn());
		setUpdatedBy(model.getUpdatedBy());
		setUpdatedOn(model.getUpdatedOn());
		setRowscn(model.getRowscn());
		setRowscn(model.getRowscn());

		nomInstitution = model.getNomInstitution();
		nomCommercialeInstitution = model.getNomCommercialeInstitution();
		dateCreation = model.getDateCreation();
		adresse = model.getAdresse();
		zone = model.getZone();
		commune = model.getCommune();
		departement = model.getDepartement();
		pays = model.getPays();
		telephone1 = model.getTelephone1();
		telephone2 = model.getTelephone2();
		telephone3 = model.getTelephone3();
		courriel = model.getCourriel();
		typeInstitutions = model.getTypeInstitutions();
		nomReponsable = model.getNomReponsable();
		prenomResponsable = model.getPrenomResponsable();
		extensionResponsable = model.getExtensionResponsable();
		courrielResponsable = model.getCourrielResponsable();
		titreResponsable = model.getTitreResponsable();
		sexeResponsable = model.getSexeResponsable();
		modalitePaiement = model.getModalitePaiement();
		typePaiement = model.getTypePaiement();
		balance = model.getBalance();
		dateBalance = model.getDateBalance();
		statut = model.getStatut();
		delaiGrace = model.getDelaiGrace();
		nomContact = model.getNomContact();
		prenomContact = model.getPrenomContact();
		courrielContact = model.getCourrielContact();
		telephoneContact = model.getTelephoneContact();
		telephoneResponsable = model.getTelephoneResponsable();
		titreContact = model.getTitreContact();
	}

	@Override
	public void validateModel() throws ModelException {
		StringBuilder builder = new StringBuilder();
		if (nomInstitution == null || nomInstitution.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("nomInstitution");
			} else {
				builder.append("|nomInstitution");
			}

		}
		if (adresse == null || adresse.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("adresse");
			} else {
				builder.append("|adresse");
			}

		}
		if (zone == null) {
			if (builder.toString().isEmpty()) {
				builder.append("zone");
			} else {
				builder.append("|zone");
			}

		}
		if (commune == null) {
			if (builder.toString().isEmpty()) {
				builder.append("commune");
			} else {
				builder.append("|commune");
			}

		}
		if (departement == null) {
			if (builder.toString().isEmpty()) {
				builder.append("departement");
			} else {
				builder.append("|departement");
			}

		}
		if (pays == null) {
			if (builder.toString().isEmpty()) {
				builder.append("pays");
			} else {
				builder.append("|pays");
			}

		}
		if (telephone1 == null || telephone1.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("telephone1");
			} else {
				builder.append("|telephone1");
			}

		}
		if (!validTypeInstitution(typeInstitutions) || typeInstitutions == null
				|| typeInstitutions.isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("typeInstitutions");
			} else {
				builder.append("|typeInstitutions");
			}

		}
		if (nomReponsable == null || nomReponsable.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("nomReponsable");
			} else {
				builder.append("|nomReponsable");
			}

		}
		if (prenomResponsable == null || prenomResponsable.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("prenomResponsable");
			} else {
				builder.append("|prenomResponsable");
			}

		}
		if (modalitePaiement == null || modalitePaiement.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("modalitePaiement");
			} else {
				builder.append("|modalitePaiement");
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

	private boolean validTypeInstitution(List<TypeInstitution> types) {
		if (types != null
				&& (types.contains(TypeInstitution.ONG)
						|| types.contains(TypeInstitution.ASSUREUR)
						|| types.contains(TypeInstitution.ETAT_CIVIL)
						|| types.contains(TypeInstitution.ETAT_CIVIL) || types
							.contains(TypeInstitution.GOUVERNEMENT))) {
			return types.size() <= 1;
		}
		return true;
	}

	/*-/2 7,Z.*
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

	public static Institution parse(String value) {
		Institution model = new Institution();
		return model;
	}
}