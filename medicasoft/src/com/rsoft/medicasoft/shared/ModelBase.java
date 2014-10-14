/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rsoft.medicasoft.shared;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.IgnoreSave;
import com.googlecode.objectify.annotation.Index;

/**
 * 
 * @author Robelkend
 */

public abstract class ModelBase extends AuditModel implements Serializable,
		Cloneable {
	private static final long serialVersionUID = 1L;
	@Index
	@Id
	private Long entityId;
	protected Long rowscn;

	@IgnoreSave
	protected String STATUS;
	@IgnoreSave
	protected String EXTSTATUS;
	@IgnoreSave
	protected boolean updatePending;
	@IgnoreSave
	private SystemMessage errorMessage;
	@IgnoreSave
	private boolean updating;
	@IgnoreSave
	private Integer lineNo;

	protected ModelBase() {
	}

	public GlobalAuditInfo getGlobalAuditInfo() {
		GlobalAuditInfo info = new GlobalAuditInfo();
		info.setAuditModel(this);
		info.setUpdatePending(updatePending);
		info.setRowscn(rowscn);
		info.setSystemMessage(errorMessage);
		return info;
	}

	protected boolean compareFields(Object oldField, Object newField) {
		return ((oldField != null && !oldField.equals(newField)) || (newField != null && !newField
				.equals(oldField)));
	}

	/**
	 * @return the lineNo
	 */
	public Integer getLineNo() {
		return lineNo;
	}

	/**
	 * @param lineNo
	 *            the lineNo to set
	 */
	public void setLineNo(Integer lineNo) {
		this.lineNo = lineNo;
	}

	public void increaseRowscn() {
		// Incremente la valeur du champ nombre d'enregistrement
		// Si c'est une insertion l'initialise a 0 dans le cas contraire
		// incremente le de 1.
		if (this.rowscn == null) {
			rowscn = 0L;
		} else {
			rowscn++;
		}
	}

	/**
	 * Get the value of updating
	 * 
	 * @return the value of updating
	 */
	public boolean isUpdating() {
		return updating;
	}

	/**
	 * Set the value of updating
	 * 
	 * @param updating
	 *            new value of updating
	 */
	public void setUpdating(boolean updating) {
		this.updating = updating;
	}

	/**
	 * Get the value of errorMessage
	 * 
	 * @return the value of errorMessage
	 */
	public SystemMessage getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Set the value of errorMessage
	 * 
	 * @param errorMessage
	 *            new value of errorMessage
	 */
	public void setErrorMessage(SystemMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * Get the value of updatePending
	 * 
	 * @return the value of updatePending
	 */
	public boolean isUpdatePending() {
		return updatePending;
	}

	/**
	 * Set the value of updatePending
	 * 
	 * @param updatePending
	 *            new value of updatePending
	 */
	public void setUpdatePending(boolean updatePending) {
		this.updatePending = updatePending;
	}

	public String getEXTSTATUS() {
		return EXTSTATUS;
	}

	public void setEXTSTATUS(String eXTSTATUS) {
		EXTSTATUS = eXTSTATUS;
		updatePending = true;
	}

	/**
	 * Get the value of STATUS
	 * 
	 * @return the value of STATUS
	 */
	public String getSTATUS() {
		return STATUS;
	}

	/**
	 * Set the value of STATUS
	 * 
	 * @param STATUS
	 *            new value of STATUS
	 */
	public void setSTATUS(String STATUS) {
		this.STATUS = STATUS;
	}

	/**
	 * Get the value of rowscn
	 * 
	 * @return the value of rowscn
	 */
	public Long getRowscn() {
		return rowscn;
	}

	/**
	 * Set the value of rowscn
	 * 
	 * @param rowscn
	 *            new value of rowscn
	 */
	public void setRowscn(Long rowscn) {
		this.rowscn = rowscn;
	}

	/**
	 * Get the value of rowid
	 * 
	 * @return the value of rowid
	 */
	public Long getEntityId() {
		return entityId;
	}

	/**
	 * Set the value of rowid
	 * 
	 * @param rowid
	 *            new value of rowid
	 */
	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	/**
	 * @param updatePending
	 *            the updatePending to set
	 */
	public void fireUpdatePendingChanged(boolean updatePending) {
		if ((updating || entityId != null) && !this.updatePending) {
			this.updatePending = updatePending;
		}
	}

	public String toCSVString() {
		return "";
	}
	
	public abstract String getKey();

	public abstract void validateModel() throws ModelException;

	public abstract void merge(ModelBase model);

	public abstract Object getPrimaryKey();
}