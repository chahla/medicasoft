package com.rsoft.medicasoft.shared.model;

/*
 Templates Generator
 */

/*@Author=Jean Louidort*/
/*@Generation Date=Mon Nov 18 01:14:08 EST 2013*/
/*@Version=1.0*/

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface EmploiPropertiesAccess extends PropertyAccess<Emploi> {
	@Path("entityId")
	ModelKeyProvider<Emploi> entityId();

	@Path("key")
	ModelKeyProvider<Emploi> key();

	ValueProvider<Emploi, String> nomPoste();

	ValueProvider<Emploi, Date> dateEmbauche();

	ValueProvider<Emploi, Date> finEmbauche();

	ValueProvider<Emploi, String> monnaie();

	ValueProvider<Emploi, Double> salaire();

	ValueProvider<Emploi, Institution> employeur();

	ValueProvider<Emploi, String> statut();

	ValueProvider<Emploi, Date> createdOn();

	ValueProvider<Emploi, String> updatedBy();

	ValueProvider<Emploi, Date> updatedOn();

	ValueProvider<Emploi, String> createdBy();
}