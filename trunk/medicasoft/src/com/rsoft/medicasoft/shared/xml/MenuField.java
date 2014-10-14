/*
 * MenuField.java
 *
 * Created on March 8, 2008, 1:57 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.rsoft.medicasoft.shared.xml;

/**
 *
 * @author Robelkend
 */
import java.io.Serializable;

import org.w3c.dom.*;

//
public final class MenuField implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fieldName = null;
	private String fieldType = null;
	private String fieldValue = null;
	private String fieldClassName = null;
	private String description = null;
	private String icon = null;
	private String hasIcon = null;
	private String mnemonic = null;
	private int acceleratorMask = 2;// CTRL 2: SHIFT 1: ALT 8
	private String componentType;
	private String availableActions;

	public String getAvailableActions() {
		return availableActions;
	}

	public void setAvailableActions(String availableActions) {
		this.availableActions = availableActions;
	}

	XMLRootMenu rootMenu = null;

	/*
	 * default Constructor
	 */

	public MenuField(String fieldName) {
		super();
		setFieldName(fieldName);
	}

	/**
	 * 
	 * Constructor
	 * 
	 * @param fieldName
	 * @param dataType
	 * 
	 */
	public MenuField(String name, String type, String value, String classFile,
			String description, String icon, String showIcon, String mnemonic,
			int acceleratorMask, String componentType, String availableActions,
			XMLRootMenu rootMenu) {
		this(name);
		setFieldType(type);
		setFieldValue(value);
		setFieldClassName(classFile);
		setFieldDescription(description);
		setFieldIcon(icon);
		showIcon(showIcon);
		setFieldMnemonic(mnemonic);
		setAcceleratorMask(acceleratorMask);
		setAvailableActions(availableActions);
		setFieldMenu(rootMenu);
		setComponentType(componentType);
	}

	/**
	 * 
	 * Constructor
	 * 
	 * @param fieldName
	 * @param dataType
	 * 
	 */
	public MenuField(String name, String type, String value, String classFile,
			String description, String icon, String showIcon, String mnemonic,
			XMLRootMenu rootMenu) {
		this(name);
		setFieldType(type);
		setFieldValue(value);
		setFieldClassName(classFile);
		setFieldDescription(description);
		setFieldIcon(icon);
		showIcon(showIcon);
		setFieldMnemonic(mnemonic);
		setFieldMenu(rootMenu);
	}

	/**
	 * Get the value of componentType
	 * 
	 * @return the value of componentType
	 */
	public String getComponentType() {
		return componentType;
	}

	/**
	 * Set the value of componentType
	 * 
	 * @param componentType
	 *            new value of componentType
	 */
	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}

	/**
	 * Get the value of acceleratorMask
	 * 
	 * @return the value of acceleratorMask
	 */
	public int getAcceleratorMask() {
		return acceleratorMask;
	}

	/**
	 * Set the value of acceleratorMask
	 * 
	 * @param acceleratorMask
	 *            new value of acceleratorMask
	 */
	public void setAcceleratorMask(int acceleratorMask) {
		this.acceleratorMask = acceleratorMask;
	}

	/**
	 * Method getField
	 * 
	 * @return
	 */
	public String getFieldName() {
		return this.fieldName;
	}

	public String getFieldDescription() {
		return this.description;
	}

	public int getFieldMnemonic() {
		try {
			return Integer.parseInt(mnemonic);
		} catch (Exception ex) {
			return -1;
		}
	}

	public void setFieldMnemonic(String mnemonic) {
		this.mnemonic = mnemonic;
	}

	public XMLRootMenu getFieldMenu() {
		return this.rootMenu;
	}

	public void setFieldMenu(XMLRootMenu rootMenu) {
		this.rootMenu = rootMenu;
	}

	public String getFieldIcon() {
		return this.icon;
	}

	public void setFieldDescription(String description) {
		this.description = description;
	}

	public boolean hasIcon() {
		return this.hasIcon.equalsIgnoreCase("yes");
	}

	public void showIcon(String hasIcon) {
		this.hasIcon = hasIcon;
	}

	public void setFieldIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * Method setField
	 * 
	 * @param value
	 * @return
	 * 
	 */
	public void setFieldName(String value) throws IllegalArgumentException {
		if (value == null) {
			throw new IllegalArgumentException("field name may not be null.");
		} else if (value.trim().length() < 1) {
			throw new IllegalArgumentException("field name may not be empty.");
		}
		this.fieldName = value.trim();
	}

	/**
	 * Method getField
	 * 
	 * @return
	 */
	public String getFieldType() {
		return this.fieldType;
	}

	/**
	 * Method setField
	 * 
	 * @param value
	 * @return
	 * 
	 */
	public void setFieldType(String value) throws IllegalArgumentException {
		if (value == null) {
			throw new IllegalArgumentException("field name may not be null.");
		} else if (value.trim().length() < 1) {
			throw new IllegalArgumentException("field name may not be empty.");
		}
		this.fieldType = value.trim();
	}

	/**
	 * Method getField
	 * 
	 * @return
	 */
	public String getFieldClassName() {
		return this.fieldClassName;
	}

	/**
	 * Method setField
	 * 
	 * @param value
	 * @return
	 * 
	 */
	public void setFieldClassName(String value) throws IllegalArgumentException {
		if (value == null) {
			throw new IllegalArgumentException("field name may not be null.");
		} else if (value.trim().length() < 1) {
			throw new IllegalArgumentException("field name may not be empty.");
		}
		this.fieldClassName = value.trim();
	}

	/**
	 * Method getFieldType
	 * 
	 * 
	 * @return
	 * 
	 */
	public String getFieldValue() {
		return this.fieldValue;
	}

	/**
	 * Method setFieldValue
	 * 
	 * 
	 * @param value
	 * 
	 * @return
	 * 
	 */
	public void setFieldValue(String value) throws IllegalArgumentException {
		if (value == null) {
			throw new IllegalArgumentException(
					"field field value may not be null.");
		} else if (value.trim().length() < 1) {
			throw new IllegalArgumentException(
					"field field value may not be empty.");
		}
		this.fieldValue = value;
	}

	/**
	 * Method parse
	 * 
	 * @param Element
	 *            e
	 * @return DataFormat
	 * 
	 */
	public static MenuField parse(Element e) {
		String x1 = null, x2 = null, x3 = null, x4 = null, x5 = null, x6 = null, x7 = null, x8 = null, x10 = null, x11 = null;
		int x9 = 2;
		XMLRootMenu root = null;

		if (e != null) {
			if (e.getTagName().equals("root-menu-item")) {
				root = new XMLRootMenu(e.getAttribute("id"),
						e.getAttribute("description"), e.getAttribute("type"),
						e.getAttribute("module"));
				// parse child elements
				x1 = "";
				x2 = "";
				x3 = "";
				x4 = "";
				x5 = "";
				x6 = "";
				x7 = "";
				x8 = "";
				x10 = "";
				x11 = "";
				NodeList l1 = e.getChildNodes();
				for (int j = 0; j < l1.getLength(); j++) {
					if (l1.item(j).getNodeType() == Node.ELEMENT_NODE) {
						Element ei1 = (Element) l1.item(j);
						if (ei1.getTagName().equals("field")) {
							MenuField f1 = MenuField.parse(ei1);
							root.addField(f1);
						}
					}
				}
			} else if (e.getTagName().equals("field")) {
				String smask = e.getAttribute("accelerator-mask");
				if (smask != null && smask.trim().length() > 0) {
					x9 = Integer.parseInt(smask);
				}
				x1 = e.getAttribute("name");
				x2 = e.getAttribute("type");
				x3 = e.getAttribute("value");
				x4 = e.getAttribute("class-name");
				x5 = e.getAttribute("description");
				x6 = e.getAttribute("icon-name");
				x7 = e.getAttribute("has-icon");
				x8 = e.getAttribute("mnemonic");
				x10 = e.getAttribute("component-type");
				x11 = e.getAttribute("available-actions");
			} else {
				throw new IllegalArgumentException("unexpected tag."
						+ e.getTagName());
			}
		} else {
			throw new IllegalArgumentException("null element.");
		}
		return new MenuField(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, root);
	}

	@Override
	public String toString() {
		return this.getFieldClassName();
	}
}