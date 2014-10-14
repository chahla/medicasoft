/*
 * LireXML.java
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
/**
 * @(#)LireXML.java
 *
 *
 * @author Charles Jean Robelkend
 * @version 1.00 2008/3/7
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LireXML {
    private DocumentBuilderFactory factory = null;
    private DocumentBuilder builder = null;
    private Document menuItemDoc = null;
    private HashMap<String, XMLMenu> rootMenu = null;
    private ArrayList<XMLRootMenu> menu = null;
    private String xmlFileName = null;
    public LireXML(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }
    public ArrayList<XMLRootMenu> getXMLMenu() throws Exception {
        menu = new ArrayList<XMLRootMenu>();
        try {
            factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw new Exception("Failed creating Document builder Factory. Please contact your administrator.");
        }
        try {
            if (rootMenu == null) {
                rootMenu = new LinkedHashMap<String, XMLMenu>(20);
            } else {
                rootMenu.clear();
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw new Exception("Failed creating statements. Please contact your administrator. " + ex);
        }
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            builder.reset();
            builder = null;
            ex.printStackTrace(System.out);
            throw new Exception("Parser configuration exception. Please contact your administrator. [LoaderServlet.init]. " + ex);
        }
        try {
        	menuItemDoc = builder.parse(getClass().getResourceAsStream(xmlFileName));
        } catch (Exception ex) {
            try {
                builder.reset();
            }catch(Exception e) {
            }
            builder = null;
            ex.printStackTrace(System.out);
            throw new Exception("Invalid configuration file format. Please contact your administrator." + ex);
        }
        Element root = null;
        NodeList l = null;
        XMLRootMenu Root;
        try {
            root = menuItemDoc.getDocumentElement();
            l = ((Node) root).getChildNodes();
            for (int i = 0; i < l.getLength(); i++) {
                if (l.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element ei = (Element) l.item(i);
                    if (ei.getTagName().equals("root-menu")) {
                        Root = XMLRootMenu.parse(rootMenu, ei);
                        menu.add(Root);
                    }
                }
            } // end for
        } catch (Exception ex) {
            builder.reset();
            builder = null;
            ex.printStackTrace(System.err);
            throw new Exception("Failed loading file  configuration. Please contact your administrator." + ex);
        } finally {
            try {
                builder.reset();
            }catch(Exception e) {}
            builder = null;
        }
        return menu;
    }
}
