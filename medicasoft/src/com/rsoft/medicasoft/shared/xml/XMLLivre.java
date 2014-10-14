/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rsoft.medicasoft.shared.xml;
import org.w3c.dom.*;
import java.util.ArrayList;

/**
 *
 * @author Robelkend
 */
public class XMLLivre {
    private String iconName;
    private ArrayList<XMLChapitre> chapitres;
    
    public XMLLivre(String iconName) {
        this.iconName = iconName;
    }
    @Override
    public String toString() {
       return "Contenu";
    }
    /**
     * Get the value of chapitres
     *
     * @return the value of chapitres
     */
    public ArrayList<XMLChapitre> getChapitres() {
        return chapitres;
    }

    /**
     * Set the value of chapitres
     *
     * @param chapitres new value of chapitres
     */
    public void setChapitres(ArrayList<XMLChapitre> chapitres) {
        this.chapitres = chapitres;
    }

    /**
     * add the value of chapitres
     *
     * @param chapitre new value of XMLChapitres
     */
    public void addChapitre(XMLChapitre chapitre) {
        if(this.chapitres == null) {
            this.chapitres = new ArrayList<XMLChapitre>();
        }
        this.chapitres.add(chapitre);
    }

    /**
     * Get the value of iconName
     *
     * @return the value of iconName
     */
    public String getIconName() {
        return iconName;
    }
    /**
     * Set the value of iconName
     *
     * @param iconName new value of iconName
     */
    public void setIconName(String iconName) {
        this.iconName = iconName;
    }
    
    public static XMLLivre parse (Element e) throws IllegalArgumentException {
        XMLLivre livre = null;
        if (e != null) {
            if (e.getTagName().equals("livre")) {
                livre = new XMLLivre(e.getAttribute("icon-name"));
                NodeList l = e.getChildNodes();
                for (int i = 0; i < l.getLength(); i++) {
                    if (l.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        Element ei = (Element)l.item(i);
                        if (ei.getTagName().equals("chapitre")) {
                            XMLChapitre chapitre = XMLChapitre.parse(ei);
                            livre.addChapitre(chapitre);
                        }
                    }
                }
            }
        }
        return livre;
    }
}
