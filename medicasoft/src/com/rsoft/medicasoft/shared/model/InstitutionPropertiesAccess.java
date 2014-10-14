package com.rsoft.medicasoft.shared.model;
/*
	Templates Generator
*/

/*@Author=Jean Louidort*/
/*@Generation Date=Sat Nov 02 22:13:10 EDT 2013*/
/*@Version=1.0*/

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface InstitutionPropertiesAccess extends PropertyAccess<Institution>  {
	@Path("entityId")
	ModelKeyProvider<Institution> entityId();
	@Path("key")
	ModelKeyProvider<Institution> key();
	
    ValueProvider<Institution, Integer> institutionId();
    ValueProvider<Institution, String> nomInstitution();
    ValueProvider<Institution, String> nomCommercialeInstitution();
    ValueProvider<Institution, Date> dateCreation();
    ValueProvider<Institution, String> adresse();
    ValueProvider<Institution, Zone> zone();
    ValueProvider<Institution, Commune> commune();
    ValueProvider<Institution, Departement> departement();
    ValueProvider<Institution, Pays> pays();
    ValueProvider<Institution, String> telephone1();
    ValueProvider<Institution, String> telephone2();
    ValueProvider<Institution, String> telephone3();
    ValueProvider<Institution, String> courriel();
    ValueProvider<Institution, String> typeInstitutionId();
    ValueProvider<Institution, String> nomReponsable();
    ValueProvider<Institution, String> prenomResponsable();
    ValueProvider<Institution, String> extensionResponsable();
    ValueProvider<Institution, String> courrielResponsable();
    ValueProvider<Institution, String> titreResponsable();
    ValueProvider<Institution, String> sexeResponsable();
    ValueProvider<Institution, String> modalitePaiement();
    ValueProvider<Institution, String> typePaiement();
    ValueProvider<Institution, Integer> balance();
    ValueProvider<Institution, Date> dateBalance();
    ValueProvider<Institution, String> statut();
    ValueProvider<Institution, Integer> delaiGrace();
    ValueProvider<Institution, String> nomContact();
    ValueProvider<Institution, String> prenomContact();
    ValueProvider<Institution, String> courrielContact();
    ValueProvider<Institution, String> telephoneContact();
    ValueProvider<Institution, String> telephoneResponsable();
    ValueProvider<Institution, String> titreContact();
    ValueProvider<Institution, String> createdBy();
    ValueProvider<Institution, Date> updatedOn();
    ValueProvider<Institution, Date> createdOn();
    ValueProvider<Institution, String> updatedBy();
}