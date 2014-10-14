package com.rsoft.medicasoft.server;

import com.rsoft.medicasoft.shared.model.UserProfile;


public class MessageLogger {
	public static final String ERROR_LEVEL = "ERROR";
	public static final String WARNING_LEVEL = "WARNING";
	public static final String DEBUG_LEVEL = "DEBUG";
	public static final String INFO_LEVEL = "INFO";

	public static void dbLog(String type, UserProfile profile, String message) {
		if (profile != null) {
			//ILogMessagesDao dao;
			try {
/*
				dao = new LogMessagesDao();
				LogMessages logMessage = new LogMessages();
				logMessage.setDateMessage(new Timestamp(new Date().getTime()));
				logMessage.setDescription(message);
				logMessage.setIdUtilisateur(profile.getUserId());
				if(logMessage.getTypeMessage() == null) {
					logMessage.setTypeMessage(ERROR_LEVEL);
				}
				if (logMessage.getColonne() == null) {
					logMessage.setColonne("0");
				}
				if (logMessage.getLigne() == null) {
					logMessage.setLigne(0);
				}
				dao.setModel(logMessage);
				dao.setUserProfile(profile);
				dao.log();
*/			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
