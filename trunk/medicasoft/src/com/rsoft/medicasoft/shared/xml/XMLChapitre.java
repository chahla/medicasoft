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
public class XMLChapitre {
    private String ID;
    private String description;
    private String iconName;
    private ArrayList<XMLTheme> themes = null;

    public XMLChapitre(String ID, String description, String iconName) {
        setID(ID);
        setDescription(description);
        setIconName(iconName);
    }
    //
    public String toString() {
        return description;
    }
    /**
     * Get the value of themes
     *
     * @return the value of themes
     */
    public ArrayList<XMLTheme> getThemes() {
        return themes;
    }

    /**
     * Set the value of themes
     *
     * @param themes new value of themes
     */
    public void setThemes(ArrayList<XMLTheme> themes) {
        this.themes = themes;
    }


    /**
     * add the value of themes
     *
     * @param theme new value of XMLTheme
     */
    public void addTheme(XMLTheme theme) {
        if(this.themes == null) {
            themes = new ArrayList<XMLTheme>();
        }
        this.themes.add(theme);
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

    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value of description
     *
     * @param description new value of description
     */
    public void setDescription(String description) {
        if(description == null || description.length() == 0) {
            throw new IllegalArgumentException("La valeur du champ description n'est pas specifiee dans le fichier xmm pour la balise chapitre");
        } else {
            this.description = description;
        }
    }

    /**
     * Get the value of ID
     *
     * @return the value of ID
     */
    public String getID() {
        return ID;
    }

    /**
     * Set the value of ID
     *
     * @param ID new value of ID
     */
    public void setID(String ID) {
        if(ID == null || ID.length() == 0) {
            throw new IllegalArgumentException("La valeur du champ ID n'est pas specifiee dans le fichier xmm pour la balise chapitre");
        } else {
            this.ID = ID;
        }
    }
    public static XMLChapitre parse (Element e) throws IllegalArgumentException {
        XMLChapitre chapitre = null;
        if (e != null) {
            if (e.getTagName().equals("chapitre")) {
                chapitre = new XMLChapitre(e.getAttribute("id"), e.getAttribute("description"), e.getAttribute("icon-name"));
                NodeList l = e.getChildNodes();
                for (int i = 0; i < l.getLength(); i++) {
                    if (l.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        Element ei = (Element)l.item(i);
                        if (ei.getTagName().equals("theme")) {
                            XMLTheme theme = XMLTheme.parse(ei);
                            chapitre.addTheme(theme);
                        }
                    }
                }
            }
        }
        return chapitre;
    }
}