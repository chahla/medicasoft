package com.rsoft.medicasoft.server;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.sencha.gxt.data.shared.loader.PagingLoadConfig;

public class ObjectSorter<T> {

	Object obj1 = null;
	Object obj2 = null;
	Field[] fields = null;
	Field[] superFields = null;

	public ObjectSorter() {
	}

	public void manageSortInfo(PagingLoadConfig config, ArrayList<T> itemlist) {

		if (itemlist != null && itemlist.size() > 0) {
			fields = itemlist.get(0).getClass().getDeclaredFields();
			superFields = itemlist.get(0).getClass().getSuperclass()
					.getDeclaredFields();

			if (config.getSortInfo() != null && config.getSortInfo().size() > 0) {
				for (int i = 0; i < config.getSortInfo().size(); i++) {
					if (config.getSortInfo().get(i).getSortField() != null) {
						final String sortField = config.getSortInfo().get(i)
								.getSortField();
						if (sortField != null) {
							Collections.sort(itemlist,
									config.getSortInfo().get(i).getSortDir()
											.comparator(new Comparator<T>() {
												public int compare(T c1, T c2) {
													try {
														for (int j = 0; j < fields.length; j++) {
															if (sortField
																	.equals(fields[j]
																			.getName())) {
																obj1 = c1
																		.getClass()
																		.getMethod(
																				"get"
																						+ fields[j]
																								.getName()
																								.substring(
																										0,
																										1)
																								.toUpperCase()
																						+ fields[j]
																								.getName()
																								.substring(
																										1))
																		.invoke(c1);
																obj2 = c2
																		.getClass()
																		.getMethod(
																				"get"
																						+ fields[j]
																								.getName()
																								.substring(
																										0,
																										1)
																								.toUpperCase()
																						+ fields[j]
																								.getName()
																								.substring(
																										1))
																		.invoke(c2);
																if (obj1 != null
																		&& obj2 != null)
																	return obj1
																			.toString()
																			.compareTo(
																					obj2.toString());
															}
														}
														for (int j = 0; j < superFields.length; j++) {
															if (sortField
																	.equals(superFields[j]
																			.getName())) {
																obj1 = c1
																		.getClass()
																		.getMethod(
																				"get"
																						+ superFields[j]
																								.getName()
																								.substring(
																										0,
																										1)
																								.toUpperCase()
																						+ superFields[j]
																								.getName()
																								.substring(
																										1))
																		.invoke(c1);
																obj2 = c2
																		.getClass()
																		.getMethod(
																				"get"
																						+ superFields[j]
																								.getName()
																								.substring(
																										0,
																										1)
																								.toUpperCase()
																						+ superFields[j]
																								.getName()
																								.substring(
																										1))
																		.invoke(c2);
																if (obj1 != null
																		&& obj2 != null)
																	return obj1
																			.toString()
																			.compareTo(
																					obj2.toString());
															}
														}
													} catch (IllegalArgumentException e) {
														e.printStackTrace();
													} catch (IllegalAccessException e) {
														e.printStackTrace();
													} catch (SecurityException e) {
														e.printStackTrace();
													} catch (NoSuchMethodException e) {
														e.printStackTrace();
													} catch (InvocationTargetException e) {
														e.printStackTrace();
													}
													return 0;
												}
											}));
						}
					}
				}
			}

		}
	}

}
