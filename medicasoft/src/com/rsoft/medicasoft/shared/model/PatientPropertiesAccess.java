package com.rsoft.medicasoft.shared.model;
/*
	Templates Generator
*/

/*@Author=Jean Louidort*/
/*@Generation Date=Thu Sep 19 22:50:29 EDT 2013*/
/*@Version=1.0*/

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface PatientPropertiesAccess extends PropertyAccess<Patient>  {
	@Path("entityId")
	ModelKeyProvider<Patient> entityId();
	@Path("key")
	ModelKeyProvider<Patient> key();
	
    ValueProvider<Patient, String> nom();
    ValueProvider<Patient, String> prenom();
    ValueProvider<Patient, String> statut();
    ValueProvider<Patient, String> dateNaissance();
    ValueProvider<Patient, String> lieuNaissance();
    ValueProvider<Patient, Date> dateDeces();
    ValueProvider<Patient, String> lieuDeces();
    ValueProvider<Patient, String> heureNaissance();
    ValueProvider<Patient, String> heureDeces();
    ValueProvider<Patient, String> causeDeces();
    ValueProvider<Patient, String> telephone1();
    ValueProvider<Patient, String> telephone2();
    ValueProvider<Patient, String> nomResponsable1();
    ValueProvider<Patient, String> prenomResponsable1();
    ValueProvider<Patient, String> courriel();
    ValueProvider<Patient, String> courrielResponsable1();
    ValueProvider<Patient, String> telephone1Responsable1();
    ValueProvider<Patient, String> telephone2Responsable1();
    ValueProvider<Patient, String> nomPere();
    ValueProvider<Patient, String> nomMere();
    ValueProvider<Patient, String> etatCivil();
    ValueProvider<Patient, String> nomEpoux();
    ValueProvider<Patient, String> prenomEpoux();
    ValueProvider<Patient, String> telephoneEpoux();
    ValueProvider<Patient, String> courrielEpoux();
    ValueProvider<Patient, String> prenomPere();
    ValueProvider<Patient, String> prenomMere();
    ValueProvider<Patient, Integer> pereId();
    ValueProvider<Patient, Integer> mereId();
    ValueProvider<Patient, Integer> epouxId();
    ValueProvider<Patient, String> nationalite();
    ValueProvider<Patient, String> paysResidence();
    ValueProvider<Patient, String> adresse();
    ValueProvider<Patient, String> sexe();
    ValueProvider<Patient, String> typeIdentication();
    ValueProvider<Patient, String> noIdentification();
    ValueProvider<Patient, String> paysIdentifcation();
    ValueProvider<Patient, Integer> nomEnfants();
    ValueProvider<Patient, String> lienParenteResponsable1();
    ValueProvider<Patient, String> telephonePere();
    ValueProvider<Patient, String> telephoneMere();
    ValueProvider<Patient, String> nomResponsable2();
    ValueProvider<Patient, String> prenomResponsable2();
    ValueProvider<Patient, String> courrielResponsable2();
    ValueProvider<Patient, String> telephone1Responsable2();
    ValueProvider<Patient, String> telephone2Responsable2();
    ValueProvider<Patient, String> adresseResponsable1();
    ValueProvider<Patient, String> adresseResponsable2();
    ValueProvider<Patient, String> taille();
    ValueProvider<Patient, String> poids();
    ValueProvider<Patient, String> zone();
    ValueProvider<Patient, String> commune();
    ValueProvider<Patient, String> departement();
    ValueProvider<Patient, String> createdBy();
    ValueProvider<Patient, Date> createdOn();
    ValueProvider<Patient, String> updatedBy();
    ValueProvider<Patient, Date> updatedOn();
}