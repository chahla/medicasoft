/*
 * XMLRootMenu.java
 * Created on February 8, 2008, 2:02 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.rsoft.medicasoft.shared.xml;

/**
 *
 * @author Robelkend
 */
import org.w3c.dom.*;
//
import java.util.HashMap;

//
public class XMLRootMenu extends XMLMenu {
	private String description = null;
	private String type = null;
	private String position;
	private boolean forReport;
	private String module;

	/**
	 * Method XMLRootMenu
	 * 
	 * 
	 */
	public XMLRootMenu(String id) {
		super(id, null, null);
	}

	/**
	 * Method XMLRootMenu
	 * 
	 * 
	 */
	public XMLRootMenu(String id, String description, String type, String module)
			throws IllegalArgumentException {
		super(id, description, type);
		this.module = module;
	}

	/**
	 * Get the value of forReport
	 * 
	 * @return the value of forReport
	 */
	public boolean isForReport() {
		return forReport;
	}

	/**
	 * Set the value of forReport
	 * 
	 * @param forReport
	 *            new value of forReport
	 */
	public void setForReport(boolean forReport) {
		this.forReport = forReport;
	}

	/**
	 * Get the value of position
	 * 
	 * @return the value of position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * Set the value of position
	 * 
	 * @param position
	 *            new value of position
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * Method XMLRootMenu
	 * 
	 * 
	 */
	public String getDescrition() {
		return this.description;
	}

	/**
	 * Method getType
	 * 
	 * 
	 * @return
	 * 
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Method getDataFormat
	 * 
	 * 
	 * @return
	 * 
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Method setDescrition
	 * 
	 * 
	 * @param value
	 * 
	 */
	public static XMLRootMenu parse(HashMap<String, XMLMenu> XMLMenu, Element e)
			throws IllegalArgumentException {
		if (e != null) {
			if (e.getTagName().equals("root-menu")) {
				String position = e.getAttribute("location") != null
						&& e.getAttribute("location").isEmpty() ? e
						.getAttribute("location") : "NORTH";
				boolean forReport = !"no".equalsIgnoreCase(e
						.getAttribute("for-report"));
				XMLRootMenu s = new XMLRootMenu(e.getAttribute("id"),
						e.getAttribute("description"), e.getAttribute("type"), e.getAttribute("module"));
				s.setForReport(forReport);
				s.setPosition(position);
				// parse child elements
				NodeList l = e.getChildNodes();
				for (int i = 0; i < l.getLength(); i++) {
					if (l.item(i).getNodeType() == Node.ELEMENT_NODE) {
						Element ei = (Element) l.item(i);
						if (ei.getTagName().equals("field")) {
							if (ei.getAttribute("type").equalsIgnoreCase(
									"menu-item")
									|| ei.getAttribute("type")
											.equalsIgnoreCase("separator")) {
								MenuField f = MenuField.parse(ei);
								s.addField(f);
							}
							if (ei.getAttribute("type").equalsIgnoreCase(
									"menu-parent")) {
								XMLRootMenu rootItem = new XMLRootMenu(
										ei.getAttribute("id"),
										ei.getAttribute("description"),
										ei.getAttribute("type"), e.getAttribute("module"));
								NodeList item = ei.getChildNodes();
								MenuField field = MenuField.parse(ei);
								for (int j = 0; j < item.getLength(); j++) {
									if (item.item(j).getNodeType() == Node.ELEMENT_NODE) {
										Element eItem = (Element) item.item(j);
										if (eItem.getTagName().equals("field")) {
											MenuField fItem = MenuField
													.parse(eItem);
											rootItem.addField(fItem);
										}
									}
								}
								field.setFieldMenu(rootItem);
								field.setFieldType("menu-item");
								s.addField(field);
							} else {
								// throw new IllegalArgumentException
								// ("Invalid menu field type.");
							}
						}
					}
				} // add root menu to all menu
				return s;
			} else {
				throw new IllegalArgumentException("unexpected tag."
						+ e.getTagName());
			}
		} else {
			throw new IllegalArgumentException("null element.");
		}
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

}
