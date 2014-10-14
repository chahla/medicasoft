package com.rsoft.medicasoft.server.dao;

import java.util.ArrayList;
import java.util.List;

import com.rsoft.medicasoft.shared.FilterWrapper;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.PersistenceException;
import com.rsoft.medicasoft.shared.model.EntityKeyDescriptor;
import com.rsoft.medicasoft.shared.model.UserProfile;

public interface IDao<T extends ModelBase> {
	
	public void insert() throws PersistenceException;

	public void merge() throws PersistenceException;

	public void remove() throws PersistenceException;

	public ArrayList<T> search() throws PersistenceException;

	public void setFilters(FilterWrapper filters);

	public void setModel(T model) throws PersistenceException;

	public T getModel();

	public void setUserProfile(UserProfile userProfile);

	public void setModelClass(Class<T> modelClass);

	public String getModelClass();

	public void setEntityKeyDescriptor(EntityKeyDescriptor entityKeyDescriptor);

	public void setDaoParameters(List<Object> daoParameters);
}
