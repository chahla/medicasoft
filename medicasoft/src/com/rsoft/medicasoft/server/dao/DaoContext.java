package com.rsoft.medicasoft.server.dao;

import java.util.ArrayList;

import com.rsoft.medicasoft.server.OperationObserver;
import com.rsoft.medicasoft.server.UserOperation;
import com.rsoft.medicasoft.shared.FilterWrapper;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.PersistenceException;
import com.rsoft.medicasoft.shared.model.EntityKeyDescriptor;
import com.rsoft.medicasoft.shared.model.UserProfile;

public class DaoContext<T extends ModelBase> implements OperationObserver {
	private IDao<T> dao;

	public DaoContext(IDao<T> dao) throws PersistenceException {
		if (dao == null) {
			throw new PersistenceException("Unexpected error null dao found");
		}
		this.dao = dao;
	}

	public void setEntityKeyDescriptor(EntityKeyDescriptor entityKeyDescriptor) {
		this.dao.setEntityKeyDescriptor(entityKeyDescriptor);
	}

	public void insert() throws PersistenceException {
		beforeOperation(UserOperation.insert);
		this.dao.insert();
		afterOperation(UserOperation.insert);
	}

	public void merge() throws PersistenceException {
		beforeOperation(UserOperation.merge);
		this.dao.merge();
		afterOperation(UserOperation.merge);
	}

	public void remove() throws PersistenceException {
		beforeOperation(UserOperation.remove);
		this.dao.remove();
		afterOperation(UserOperation.remove);
	}

	public ArrayList<T> search() throws PersistenceException {
		beforeOperation(UserOperation.search);
		ArrayList<T> results = this.dao.search();
		afterOperation(UserOperation.remove);
		return results;
	}

	public void setFilters(FilterWrapper filters) {
		this.dao.setFilters(filters);
	}

	public void setModel(T model) throws PersistenceException {
		dao.setModel(model);
	}

	public T getModel() {
		return this.dao.getModel();
	}

	public void setUserProfile(UserProfile userProfile) {
		this.dao.setUserProfile(userProfile);
	}

	// Cette fonction permet de verifie le respect des contrainte etablie pour
	// une operation quelconque
	// Elle permet par exemple de determiner qu'un utilsateur a le droit de
	// faire telle ou telle operation
	// Si l'utilisateur a le droit, il donne la main a la methode appellant dans
	// le cas contraire une
	// exception est levee
	// Les contrainte seront donnees par table
	@Override
	public void beforeOperation(UserOperation operation)
			throws PersistenceException {
		// String modelName = dao.getModelName();
		// TODO Auto-generated method stub
	}

	@Override
	public void afterOperation(UserOperation operation)
			throws PersistenceException {
		// String modelName = dao.getModelName();
		// TODO Auto-generated method stub
	}
}
