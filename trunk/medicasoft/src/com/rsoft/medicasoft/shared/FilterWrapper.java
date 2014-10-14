package com.rsoft.medicasoft.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FilterWrapper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<XFilter> filters;
	private Long id;
	private String communicationEntente;
	private String utilisateur;
	
	public FilterWrapper() {
		super();
	}

	public FilterWrapper(List<XFilter> filters) {
		this();
		this.filters = filters;
	}

	public String getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(String utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getCommunicationEntente() {
		return communicationEntente;
	}

	public void setCommunicationEntente(String communicationEntente) {
		this.communicationEntente = communicationEntente;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the filter
	 */
	public List<XFilter> getFilters() {
		return filters;
	}

	/**
	 * @param filter
	 *            the filter to set
	 */
	public void setFilters(List<XFilter> filter) {
		this.filters = filter;
	}

	/**
	 * @param filter
	 *            the filter to set
	 */
	public void addFilter(XFilter filter) {
		if (filters == null) {
			this.filters = new ArrayList<XFilter>();
		}
		this.filters.add(filter);
	}
}