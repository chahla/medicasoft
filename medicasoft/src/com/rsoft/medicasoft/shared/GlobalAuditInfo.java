package com.rsoft.medicasoft.shared;

import java.io.Serializable;
import java.util.Date;

public class GlobalAuditInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AuditModel auditModel;
	private String postedBy;
	private Date postedOn;
	private String validatedBy;
	private Date validatedOn;
	private String printedBy;
	private Date printedOn;
	private Long rowscn;
	private boolean updatePending;
	private SystemMessage systemMessage;

	public GlobalAuditInfo() {

	}

	public GlobalAuditInfo(AuditModel auditModel, String postedBy,
			Date postedOn, String validatedBy, Date validatedOn,
			String printedBy, Date printedOn, Long rowscn, boolean updatePending) {
		super();
		this.auditModel = auditModel;
		this.postedBy = postedBy;
		this.postedOn = postedOn;
		this.validatedBy = validatedBy;
		this.validatedOn = validatedOn;
		this.printedBy = printedBy;
		this.printedOn = printedOn;
		this.rowscn = rowscn;
		this.updatePending = updatePending;
	}

	public GlobalAuditInfo(AuditModel auditModel) {
		super();
		this.auditModel = auditModel;
	}

	public SystemMessage getSystemMessage() {
		return systemMessage;
	}

	public void setSystemMessage(SystemMessage systemMessage) {
		this.systemMessage = systemMessage;
	}

	public AuditModel getAuditModel() {
		return auditModel;
	}

	public void setAuditModel(AuditModel auditModel) {
		this.auditModel = auditModel;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	public Date getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}

	public String getValidatedBy() {
		return validatedBy;
	}

	public void setValidatedBy(String validatedBy) {
		this.validatedBy = validatedBy;
	}

	public Date getValidatedOn() {
		return validatedOn;
	}

	public void setValidatedOn(Date validatedOn) {
		this.validatedOn = validatedOn;
	}

	public String getPrintedBy() {
		return printedBy;
	}

	public void setPrintedBy(String printedBy) {
		this.printedBy = printedBy;
	}

	public Date getPrintedOn() {
		return printedOn;
	}

	public void setPrintedOn(Date printedOn) {
		this.printedOn = printedOn;
	}

	public Long getRowscn() {
		return rowscn;
	}

	public void setRowscn(Long rowscn) {
		this.rowscn = rowscn;
	}

	public boolean isUpdatePending() {
		return updatePending;
	}

	public void setUpdatePending(boolean updatePending) {
		this.updatePending = updatePending;
	}
}
