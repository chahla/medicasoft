package com.rsoft.medicasoft.shared.model;
/*
	Templates Generator
*/

/*@Author=Jean Louidort*/
/*@Generation Date=Mon Nov 18 01:14:06 EST 2013*/
/*@Version=1.0*/

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface SportPratiquePropertiesAccess extends PropertyAccess<SportPratique>  {
	@Path("entityId")
	ModelKeyProvider<SportPratique> entityId();
	@Path("key")
	ModelKeyProvider<SportPratique> key();
	
    ValueProvider<SportPratique, Integer> sportFrequenteId();
    ValueProvider<SportPratique, String> nomSport();
    ValueProvider<SportPratique, Integer> frequence();
    ValueProvider<SportPratique, String> unite();
    ValueProvider<SportPratique, Integer> patientId();
    ValueProvider<SportPratique, Date> dateDebut();
    ValueProvider<SportPratique, Date> dateFin();
    ValueProvider<SportPratique, String> createdBy();
    ValueProvider<SportPratique, Date> createdOn();
    ValueProvider<SportPratique, String> updatedBy();
    ValueProvider<SportPratique, Date> updatedOn();
}