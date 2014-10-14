/*
 * XMLMenu.java
 *
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
import java.util.ArrayList;

//
public class XMLMenu {
	private ArrayList<XMLMenu> children = null;
	private String description = null;
	private String type = null;
	private String menuId = null;
	private XMLMenu menu;
	private XMLMenu parentMenu;
	private ArrayList<MenuField> fields = null;
	private int depth = 0;

	/**
	 * Method LoaderDBRootStatement
	 * 
	 * 
	 */

	private XMLMenu(String id) throws IllegalArgumentException {
		super();
		if (id == null) {
			throw new IllegalArgumentException("statement id may not be null.");
		} else if (id.trim().length() == 0) {
			throw new IllegalArgumentException("statement id may not be empty.");
		}
		this.menuId = id;
		children = new ArrayList<XMLMenu>(5);
		fields = new ArrayList<MenuField>();
	}

	public XMLMenu(String id, XMLMenu parent) throws IllegalArgumentException {
		this(id);
		if (parent == null) {
			menu = this;
			parentMenu = this;
			this.depth = 0;
		} else {
			this.parentMenu = parent;
			this.parentMenu.addChild(this);
			this.menu = parent.getXMLMenu();
			this.depth = parent.getDepth() + 1;
		}
	}

	public XMLMenu(String id, String description, String type) throws IllegalArgumentException {
		this(id);
		this.description = description;
		this.type = type;
	}

	public boolean isRoot() {
		return (parentMenu == null || parentMenu == this);
	}

	private void incSubtreeDepth() {
		if (!isRoot()) {
			parentMenu.incSubtreeDepth();
		}
	}

	public String getId() {
		return menuId;
	}

	/**
	 * Method getDepth
	 * 
	 * 
	 * @return
	 * 
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * Method getXMLMenu
	 * 
	 * 
	 * @return
	 * 
	 */
	public XMLMenu getXMLMenu() {
		return this.menu;
	}

	public boolean hasChild() {
		return children != null && children.size() > 0;
	}

	/**
	 * Method getChildren
	 * 
	 * 
	 * @return
	 * 
	 */
	public ArrayList<XMLMenu> getChildren() {
		return this.children;
	}

	/*
	 * Method addChild
	 * 
	 * 
	 * @param child
	 */
	public void addChild(XMLMenu child) {
		if (!hasChild()) {
			incSubtreeDepth();
		}
		children.add(child);
	}

	public XMLMenu(String description, String type) {
		this.description = description;
		this.type = type;
		this.depth = 0;
	}

	/**
	 * Method getDescription
	 * 
	 * 
	 * @return
	 * 
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Method getDataDestination
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

	public ArrayList<MenuField> getFields() {
		return fields;
	}

	/**
	 * Method addField
	 * 
	 */
	public void addField(MenuField aField) {
		if (aField == null) {
			throw new IllegalArgumentException(
					"Statement field may not be null.");
		}
		if(fields==null) {
			fields = new ArrayList<MenuField>();
		}
		fields.add(aField);
	}

	public static XMLMenu parse(Element e) throws IllegalArgumentException {
		if (e != null) {
			if (e.getTagName().equals("field")) {
				String x1 = null, x2 = null, x3 = null;
				x1 = e.getAttribute("description");
				x2 = e.getAttribute("type");
				return new XMLMenu(x1, x2);
			} else {
				throw new IllegalArgumentException("unexpected tag."
						+ e.getTagName());
			}
		} else {
			throw new IllegalArgumentException("null element.");
		}
	}
}
