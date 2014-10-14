/*
 * LireXMLChapitre.java
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
 * @(#)LireXMLChapitre.java
 *
 *
 * @author Charles Jean Robelkend
 * @version 1.00 2008/3/7
 */
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

//
public class LireXMLChapitre {
	private DocumentBuilderFactory factory = null;
	private DocumentBuilder builder = null;
	private Document chapitreItemDoc = null;
	private String xmlFileName = null;

	public LireXMLChapitre(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public XMLLivre getXMLChapitres() throws Exception {
		XMLLivre livre = null;
		try {
			factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringComments(true);
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			throw new Exception(
					"Failed creating Document builder Factory. Please contact your administrator.");
		}
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException ex) {
			builder.reset();
			builder = null;
			ex.printStackTrace(System.out);
			throw new Exception(
					"Parser configuration exception. Please contact your administrator. [LoaderServlet.init]. "
							+ ex);
		}
		try {
			// obtain document object from XML document
			chapitreItemDoc = builder.parse(getClass().getResourceAsStream(xmlFileName));
		} catch (Exception ex) {
			builder.reset();
			builder = null;
			ex.printStackTrace(System.out);
			throw new Exception(
					"Invalid configuration file format. Please contact your administrator."
							+ ex);
		}
		Element root = null;
		try {
			root = chapitreItemDoc.getDocumentElement();
			if (root.getTagName().equals("livre")) {
				livre = XMLLivre.parse(root);
			}
		} catch (Exception ex) {
			builder.reset();
			builder = null;
			ex.printStackTrace(System.err);
			throw new Exception(
					"Failed loading file  configuration. Please contact your administrator."
							+ ex);
		} finally {
			try {
				builder.reset();
			} catch (Exception ex) {
			}
			builder = null;
		}
		return livre;
	}

	public static void main(String[] args) {
		LireXMLChapitre lireLivre = new LireXMLChapitre("config/chapitre.xml");
		try {
			XMLLivre livre = lireLivre.getXMLChapitres();
			System.out.println(livre);
		} catch (Exception ex) {
		}
	}
}
