package com.rsoft.medicasoft.shared.i18n;

import com.google.gwt.core.client.GWT;
import com.rsoft.medicasoft.server.MessagesUtils;
import com.sencha.gxt.messages.client.XMessages;

/**
 * Default locale-sensitive messages for GXT. This class uses
 * {@link GWT#create(Class)} to create an instance of an automatically generated
 * subclass that implements the {@link XMessages} interface. See the package
 * containing {@link XMessages} for the property files containing the translated
 * messages. See {@link MessagesUtils} for more information.
 */
public class I18NMessages {
	private static II18NMessages instance;
	/**
	 * Returns an instance of an automatically generated subclass that
	 * implements the {@link XMessages} interface containing default
	 * locale-sensitive messages for GXT.
	 * 
	 * @return locale-sensitive messages for GXT
	 */
	public static II18NMessages getMessages() {
		if (instance == null) {
			instance = GWT.create(II18NMessages.class);
		}
		return instance;
	}
}
