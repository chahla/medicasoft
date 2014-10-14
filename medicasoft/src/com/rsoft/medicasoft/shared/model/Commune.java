package com.rsoft.medicasoft.shared.model;
/*
	Templates Generator
*/

/*@Author=Jean Louidort*/
/*@Generation Date=Mon Oct 14 22:30:48 EDT 2013*/
/*@Version=1.0*/
import java.io.Serializable;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.IgnoreSave;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.googlecode.objectify.annotation.OnLoad;
import com.googlecode.objectify.annotation.OnSave;
import com.googlecode.objectify.condition.IfNotNull;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.ModelBaseX;
import com.rsoft.medicasoft.shared.ModelException;
import com.rsoft.medicasoft.shared.PersistenceException;


@Entity
public class Commune extends ModelBaseX implements Serializable{
	private static final long serialVersionUID = 1L;
	
   
@Index({ IfNotNull.class })
@Load
private Ref<Departement> departementId;
@IgnoreSave private Departement departement;
  private  String  nom;





	
  public Commune() {
  }
	

  /**
    * Get the value of departementId
    * @return the value of departementId
    * Get the value departementId
  */
  public  Ref<Departement>   getDepartementId() {
    return this.departementId;
  }

  /**
    * Get the value of departement
    * @return the value of departement
    * Get the value departement
  */
  public  Departement  getDepartement() {
    return this.departement;
  }
  /**
    * Get the value of nom
    * @return the value of nom
    * Get the value nom
  */
  public  String  getNom() {
    return this.nom;
  }

	

  /**
    * Set the value of departementId
    * @param departementId* new value of departementId
  */
  public  void  setDepartementId(Ref<Departement>   departementId) {
    this.departementId  =  departementId;
  }

  /**
    * Set the value of departement
    * @param departement* new value of departement
  */
  public  void  setDepartement(Departement  departement) {
    this.departement  =  departement;
  }
  /**
    * Set the value of nom
    * @param nom* new value of nom
  */
  public  void  setNom(String  nom) {
    if(this.compareFields(this.nom, nom)){
    this.fireUpdatePendingChanged(true);
    }
    this.nom  =  nom != null ? nom.trim() : null;
  }

 
	
	@OnSave
	public void beforeSave() throws PersistenceException {
		 if (departement != null) {
departementId = Ref.create(departement);
}
	}

	@OnLoad
	public void afterLoad() throws PersistenceException {
		if (departementId != null) {
departement = departementId.getValue();
}

	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result;
		if (this.getEntityId() != null) {
			result += ((this.getEntityId() == null) ? 0 : this.getEntityId().hashCode());
		}
		if (this.getLineNo() != null) {
			result += ((this.getLineNo() == null) ? 0 : this.getLineNo().hashCode());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commune other = (Commune) obj;
		// Je fais de comparaison par id dans le cas de mise a jour ou de
		// suppression
		// dans le cas d'insertion je le fais par lineNo
		if (this.getEntityId() != null && other.getEntityId() != null
				&& !this.getEntityId().equals(other.getEntityId())) {
			return false;
		}
		if (this.getLineNo() == null) {
			if (other.getLineNo() != null)
				return false;
		} else if (other.getLineNo() == null) {
			if (this.getLineNo() != null)
				return false;
		} else if (this.getLineNo() != other.getLineNo()) {
			return false;
		}
		return true;
	}


	// Recuperer la signature de l'entite
	public String getKey() {
		StringBuilder builder = new StringBuilder();
		if (getLineNo() != null) {
			builder.append(getLineNo());
		} else if (this.getEntityId() != null) {
			builder.append(getEntityId());
		}
		return builder.toString();
	}

	public void merge(ModelBase modelBase) {
		Commune model = (Commune)modelBase;
		setEntityId(model.getEntityId());
		setErrorMessage(model.getErrorMessage());
		setCreatedBy(model.getCreatedBy());
		setCreatedOn(model.getCreatedOn());
		setUpdatedBy(model.getUpdatedBy());
		setUpdatedOn(model.getUpdatedOn());
		setRowscn(model.getRowscn());
		setRowscn(model.getRowscn());
		
        nom = model.getNom();
        departementId = model.getDepartementId();
	}

	@Override
	public void validateModel() throws ModelException {
		StringBuilder builder = new StringBuilder();
		
        if (nom == null || nom.trim().isEmpty()) {
          if(builder.toString().isEmpty()) {
            builder.append("nom");} else {
            builder.append("|nom");}
          
          }
		if (!builder.toString().trim().isEmpty()) {
			throw new ModelException("MISSING_FIELDS",
					builder.toString(),
					"Fill all required fields before continue");
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if(this.getEntityId() != null) {
			return Long.toString(this.getEntityId());
		}
		if(this.getLineNo() != null) {
			return Long.toString(this.getLineNo());
		}
		return super.toString();
	}

	@Override
	public Object getPrimaryKey() {
		return this.getEntityId();
	}
	
	public static Commune parse(String value) {
		Commune model = new Commune();
		return model;
	}
}