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
public class XMLTheme {
    private String ID;
    private String name;
    private String description;
    private String iconName;
    private String mnemonic;
    private String type;
    private String className;
    private String protocole;
    private String host;
    private String fileName;
    private String contentType;
    private ArrayList<XMLTheme> themes = null;

    public XMLTheme(String ID, String name, String description, String iconName, String mnemonic, String type, String className) {
        setID(ID);
        setName(name);
        setDescription(description);
        setIconName(iconName);
        setMnemonic(mnemonic);
        setType(type);
        setClassName(className);
    }

    public XMLTheme(String ID, String name, String description, String iconName, String mnemonic, String type, String className, String protocole, String host, String fileName) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.iconName = iconName;
        this.mnemonic = mnemonic;
        this.type = type;
        this.className = className;
        this.protocole = protocole;
        this.host = host;
        this.fileName = fileName;
    }

    public XMLTheme(String ID, String name, String description, String iconName, String mnemonic, String type, String className, String protocole, String host, String fileName, String contentType) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.iconName = iconName;
        this.mnemonic = mnemonic;
        this.type = type;
        this.className = className;
        this.protocole = protocole;
        this.host = host;
        this.fileName = fileName;
        this.contentType = contentType;
    }

    public String toString() {        
        return description;
    }

    /**
     * Get the value of contentType
     *
     * @return the value of contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Set the value of contentType
     *
     * @param contentType new value of contentType
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * Get the value of fileName
     *
     * @return the value of fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Set the value of fileName
     *
     * @param fileName new value of fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Get the value of host
     *
     * @return the value of host
     */
    public String getHost() {
        return host;
    }

    /**
     * Set the value of host
     *
     * @param host new value of host
     */
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * Get the value of protocole
     *
     * @return the value of protocole
     */
    public String getProtocole() {
        return protocole;
    }

    /**
     * Set the value of protocole
     *
     * @param protocole new value of protocole
     */
    public void setProtocole(String protocole) {
        this.protocole = protocole;
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
     * Get the value of className
     *
     * @return the value of className
     */
    public String getClassName() {
        return className;
    }

    /**
     * Set the value of className
     *
     * @param className new value of className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the value of type
     *
     * @param type new value of type
     */
    public void setType(String type) {
        if(type == null || type.length() == 0) {
            throw new IllegalArgumentException("La valeur du champ type n'est pas specifiee dans le fichier xmm pour la balise Theme");
        } else {
            this.type = type;
        }
    }

    /**
     * Get the value of mnemonic
     *
     * @return the value of mnemonic
     */
    public String getMnemonic() {
        return mnemonic;
    }

    /**
     * Set the value of mnemonic
     *
     * @param mnemonic new value of mnemonic
     */
    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
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
            throw new IllegalArgumentException("La valeur du champ description n'est pas specifiee dans le fichier xmm pour la balise Theme");
        } else {
            this.description = description;
        }
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
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
            throw new IllegalArgumentException("La valeur du champ ID n'est pas specifiee dans le fichier xmm pour la balise Theme");
        } else {
            this.ID = ID;
        }
    }

    public static XMLTheme parse (Element e) throws IllegalArgumentException {
        XMLTheme theme = null;
        if (e != null) {
            if (e.getTagName().equals("theme")) {
                theme = new XMLTheme(e.getAttribute("id"), e.getAttribute("name"), e.getAttribute("description"), e.getAttribute("icon-name"), e.getAttribute("mnemonic"), e.getAttribute("type"), e.getAttribute("class-name"), e.getAttribute("protocole"), e.getAttribute("host-name"), e.getAttribute("file-name"), e.getAttribute("content-type"));
                NodeList l = e.getChildNodes();
                for (int i = 0; i < l.getLength(); i++) {
                    if (l.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        Element ei = (Element)l.item(i);
                        if (ei.getTagName().equals("theme")) {
                            XMLTheme _theme = XMLTheme.parse(ei);
                            theme.addTheme(_theme);
                        }
                    }
                }
            }
        }
        return theme;
    }
}