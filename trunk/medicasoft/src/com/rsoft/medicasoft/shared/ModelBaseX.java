package com.rsoft.medicasoft.shared;

import java.io.Serializable;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Index;

public abstract class ModelBaseX extends ModelBase implements Serializable{
	private static final long serialVersionUID = 1L;
	
    @Index
	private Key<Institution> institution;

	public Key<Institution> getInstitution() {
		return institution;
	}

	public void setInstitution(Key<Institution> institution) {
		this.institution = institution;
	}
}

