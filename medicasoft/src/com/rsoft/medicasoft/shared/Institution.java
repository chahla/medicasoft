package com.rsoft.medicasoft.shared;

import com.googlecode.objectify.annotation.Entity;

@Entity
public  class Institution extends ModelBaseX {
	private static final long serialVersionUID = 1L;

	private String nomInstitution;
	private String actif;

	public Institution() {
		super();
	}

	public String getNomInstitution() {
		return nomInstitution;
	}

	public void setNomInstitution(String nomInstitution) {
		this.nomInstitution = nomInstitution;
	}

	public String getActif() {
		return actif;
	}

	public void setActif(String actif) {
		this.actif = actif;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateModel() throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void merge(ModelBase model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getPrimaryKey() {
		// TODO Auto-generated method stub
		return null;
	}
}
