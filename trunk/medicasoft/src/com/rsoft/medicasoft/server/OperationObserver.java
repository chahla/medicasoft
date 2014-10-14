package com.rsoft.medicasoft.server;

import com.rsoft.medicasoft.shared.PersistenceException;


public interface OperationObserver {
	public void beforeOperation(UserOperation operation) throws PersistenceException;
	public void afterOperation(UserOperation operation) throws PersistenceException;
}
