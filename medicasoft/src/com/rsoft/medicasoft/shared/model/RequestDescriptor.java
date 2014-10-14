package com.rsoft.medicasoft.shared.model;

import java.io.Serializable;

public class RequestDescriptor implements Serializable {
	private static final long serialVersionUID = 1L;
	private String sessionId;
	private String entityName;
	private Long headerEntityId;
	private String persistenceManagerPrefix;

	public RequestDescriptor() {
	}

	public RequestDescriptor(String sessionId, String entityName) {
		super();
		this.sessionId = sessionId;
		this.entityName = entityName;
	}

	public RequestDescriptor(String entityName) {
		super();
		this.entityName = entityName;
	}

	public String getPersistenceManagerPrefix() {
		return persistenceManagerPrefix;
	}

	public void setPersistenceManagerPrefix(String persistenceManagerPrefix) {
		this.persistenceManagerPrefix = persistenceManagerPrefix;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Long getHeaderEntityId() {
		return headerEntityId;
	}

	public void setHeaderEntityId(Long headerEntityId) {
		this.headerEntityId = headerEntityId;
	}
}
