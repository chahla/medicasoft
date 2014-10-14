package com.rsoft.medicasoft.shared.model;
/*
	Templates Generator
*/

/*@Author=Jean Louidort*/
/*@Generation Date=Mon Nov 18 01:14:07 EST 2013*/
/*@Version=1.0*/

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface AssurancePropertiesAccess extends PropertyAccess<Assurance>  {
	@Path("entityId")
	ModelKeyProvider<Assurance> entityId();
	@Path("key")
	ModelKeyProvider<Assurance> key();
	
    ValueProvider<Assurance, String> typeAssurance();
    ValueProvider<Assurance, String> description();
    ValueProvider<Assurance, Date> dateEffective();
    ValueProvider<Assurance, String> statut();
    ValueProvider<Assurance, Date> dateStatut();
    ValueProvider<Assurance, Double> montant();
    ValueProvider<Assurance, Integer> patientId();
    ValueProvider<Assurance, Institution> assureur();
    ValueProvider<Assurance, Emploi> emploi();
    ValueProvider<Assurance, String> createdBy();
    ValueProvider<Assurance, Date> createdOn();
    ValueProvider<Assurance, String> updatedBy();
    ValueProvider<Assurance, Date> updatedOn();
}