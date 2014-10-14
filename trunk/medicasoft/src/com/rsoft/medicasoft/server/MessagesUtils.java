package com.rsoft.medicasoft.server;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import com.rsoft.medicasoft.shared.model.UserProfile;

public class MessagesUtils {

	public static String getMessage(UserProfile userProfile, String cle) {
		return getMessage(userProfile, cle, null);
	}

	public static String getMessage(UserProfile userProfile, String cle,
			String altLabel) {
		return getMessage(userProfile, cle, altLabel, null);
	}

	public static String getMessage(UserProfile userProfile, String cle,
			String altLabel, Object[] parameters) {
		ResourceBundle bundle = null;
		Locale locale = Locale.getDefault();;
		if (userProfile != null) {
			locale = new Locale(userProfile.getLanguage());
		}
		bundle = ResourceBundle.getBundle(
				"com.rsoft.medicasoft.shared.i18n.II18NMessages",
				locale);
		MessageLogger.dbLog(MessageLogger.ERROR_LEVEL, userProfile, altLabel);
		if (parameters == null) {
			if (bundle.containsKey(cle != null ? cle.trim() : cle)) {
				return bundle.getString(cle != null ? cle.trim() : cle);
			} else {
				if (altLabel != null) {
					return altLabel;
				}
				return cle;
			}
		} else {
			try {
				if (bundle.containsKey(cle != null ? cle.trim() : cle)) {
					return MessageFormat.format(
							bundle.getString(cle != null ? cle.trim() : cle),
							parameters);
				} else {
					return cle;
				}
			} catch (Exception ex) {
				return ex.toString();
			}
		}
	}
}
