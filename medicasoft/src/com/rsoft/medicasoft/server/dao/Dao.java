package com.rsoft.medicasoft.server.dao;

import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.model.Assurance;
import com.rsoft.medicasoft.shared.model.Commune;
import com.rsoft.medicasoft.shared.model.Departement;
import com.rsoft.medicasoft.shared.model.Emploi;
import com.rsoft.medicasoft.shared.model.EntityKeyDescriptor;
import com.rsoft.medicasoft.shared.model.Institution;
import com.rsoft.medicasoft.shared.model.ParamInout;
import com.rsoft.medicasoft.shared.model.Patient;
import com.rsoft.medicasoft.shared.model.Pays;
import com.rsoft.medicasoft.shared.model.Profession;
import com.rsoft.medicasoft.shared.model.Religion;
import com.rsoft.medicasoft.shared.model.UserGroup;
import com.rsoft.medicasoft.shared.model.UserProfile;
import com.rsoft.medicasoft.shared.model.Zone;

//<!-Automatically added importation do not remove or modify this line->
public abstract class Dao<T extends ModelBase> implements IDao<T> {
	static {
		try {
			// <!-Marked line, do not remove or modify this line->
			ObjectifyService.register(Profession.class);
			ObjectifyService.register(Religion.class);
			ObjectifyService.register(Emploi.class);
			ObjectifyService.register(Assurance.class);
			
			ObjectifyService.register(ParamInout.class);
			ObjectifyService.register(Pays.class);
			ObjectifyService.register(Departement.class);
			ObjectifyService.register(Commune.class);
			ObjectifyService.register(Zone.class);

			ObjectifyService.register(UserGroup.class);
			ObjectifyService.register(UserProfile.class);
			ObjectifyService.register(Patient.class);
			ObjectifyService.register(Institution.class);
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		}
	}
	protected EntityKeyDescriptor entityKeyDescriptor;

	protected List<Object> daoParameters;

	public void setEntityKeyDescriptor(EntityKeyDescriptor entityKeyDescriptor) {
		this.entityKeyDescriptor = entityKeyDescriptor;
	}

	public List<Object> getDaoParameters() {
		return daoParameters;
	}

	public void setDaoParameters(List<Object> daoParameters) {
		this.daoParameters = daoParameters;
	}
}
