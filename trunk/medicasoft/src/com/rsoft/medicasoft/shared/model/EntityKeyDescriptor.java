package com.rsoft.medicasoft.shared.model;

import java.io.Serializable;

public class EntityKeyDescriptor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String headerClassName;
	private String className;
	private String keySetter;
	private Long headerId;
	private boolean ref;

	public EntityKeyDescriptor() {
		super();
	}

	public EntityKeyDescriptor(String headerClassName, String className,
			String keySetter, Long headerId) {
		super();
		this.headerClassName = headerClassName;
		this.className = className;
		this.keySetter = keySetter;
		this.headerId = headerId;
	}

	public boolean isRef() {
		return ref;
	}

	public void setRef(boolean ref) {
		this.ref = ref;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getHeaderClassName() {
		return headerClassName;
	}

	public void setHeaderClassName(String headerClassName) {
		this.headerClassName = headerClassName;
	}

	public String getKeySetter() {
		return keySetter;
	}

	public void setKeySetter(String keySetter) {
		this.keySetter = keySetter;
	}

	public Long getHeaderId() {
		return headerId;
	}

	public void setHeaderId(Long headerId) {
		this.headerId = headerId;
	}
}
