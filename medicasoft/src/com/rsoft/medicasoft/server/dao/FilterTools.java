package com.rsoft.medicasoft.server.dao;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
import com.rsoft.medicasoft.shared.FilterWrapper;
import com.rsoft.medicasoft.shared.XFilter;

public class FilterTools {

	public static <T> Query<T> addCriterias(Query<T> query,
			FilterWrapper filters)  {
		if (query != null && filters != null && filters.getFilters() != null) {
			// Construire les criteres de recherche de l'utilisateur
			// Les chaines doivent etre non null et non vide pour etre
			// prises en compte
			// Les autres type d'object doivent etre non null
			if (filters != null && filters.getFilters() != null
					&& !filters.getFilters().isEmpty()) {
				if (filters != null && !filters.getFilters().isEmpty()) {
					for (XFilter fi : filters.getFilters()) {
						if (fi.getValue() != null
								&& !fi.getValue().trim().isEmpty()) {
							if ("lt".equalsIgnoreCase(fi.getComparison())
									|| "before".equalsIgnoreCase(fi
											.getComparison())) {
								query = query.filter(fi.getField() + " <",
										fi.getValueInType());
							} else if ("gt"
									.equalsIgnoreCase(fi.getComparison())
									|| "after".equalsIgnoreCase(fi
											.getComparison())) {
								query = query.filter(fi.getField() + " >",
										fi.getValueInType());
							} else {
								query = query.filter(fi.getField() + " =",
										fi.getValueInType());
							}
						} else if (fi.getField() != null && fi.getKey() != null) {
							Key<?> headerkey = Key.create(fi.getKey().getClass(), fi
									.getKey().getEntityId());
							query = query.filter(fi.getField(), headerkey);
						}
					}
				}
			}
		}
		return query;
	}
}