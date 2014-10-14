package com.rsoft.medicasoft.client.view;

import com.google.gwt.user.client.ui.HTML;
import com.rsoft.medicasoft.shared.AuditModel;
import com.rsoft.medicasoft.shared.GlobalAuditInfo;
import com.rsoft.medicasoft.shared.SystemMessage;

public class ViewUtils {
	public static void notify(HTML messageArea, SystemMessage message) {
		messageArea.setHTML(message.getMessage());
	}

	public static void unNotify(HTML messageArea) {
		messageArea.setHTML("");
	}

	public static void showAuditInfos(HTML messageArea,
			GlobalAuditInfo auditInfo) {
		if (auditInfo != null) {
			if (auditInfo.getSystemMessage() == null
					|| auditInfo.getSystemMessage().getMessage() == null
					|| auditInfo.getSystemMessage().getMessage().trim()
							.isEmpty()) {
				StringBuilder builder = new StringBuilder();
				AuditModel auditModel = auditInfo.getAuditModel();
				if (auditModel != null && auditModel.getCreatedBy() != null) {
//					builder.append(I18NMessages.getMessages().createdBy());
					builder.append(": ");
					builder.append("<font color='blue'><b>");
					builder.append(auditModel.getCreatedBy());
					builder.append("</font></b>");
					builder.append(" ");
//					builder.append(I18NMessages.getMessages().on());
					builder.append(": ");
					builder.append("<font color='blue'><b>");
					builder.append(auditModel.getCreatedOn());
					builder.append("</font></b>");
				}
				if (auditModel != null && auditModel.getUpdatedBy() != null) {
					builder.append(", ");
//					builder.append(I18NMessages.getMessages().modifiedBy());
					builder.append(": ");
					builder.append("<font color='blue'><b>");
					builder.append(auditModel.getUpdatedBy());
					builder.append("</font></b>");
					builder.append(" ");
//					builder.append(I18NMessages.getMessages().on());
					builder.append(": ");
					builder.append("<font color='blue'><b>");
					builder.append(auditModel.getUpdatedOn());
					builder.append("</font></b>");
				}
				if (auditInfo.getValidatedBy() != null) {
					builder.append(", ");
//					builder.append(I18NMessages.getMessages().validatedBy());
					builder.append(": ");
					builder.append("<font color='blue'><b>");
					builder.append(auditInfo.getValidatedBy());
					builder.append("</font></b>");
				}
				if (auditInfo.getValidatedOn() != null) {
					if (auditInfo.getValidatedBy() == null) {
						builder.append(", ");
					}
					builder.append(" ");
//					builder.append(I18NMessages.getMessages().on());
					builder.append(": ");
					builder.append("<font color='blue'><b>");
					builder.append(auditInfo.getValidatedOn());
					builder.append("</font></b>");
				}
				if (auditInfo.getPostedBy() != null) {
					builder.append(", ");
//					builder.append(I18NMessages.getMessages().postedBy());
					builder.append(": ");
					builder.append("<font color='blue'><b>");
					builder.append(auditInfo.getPostedBy());
					builder.append("</font></b>");
				}
				if (auditInfo.getPostedOn() != null) {
					if (auditInfo.getPostedBy() == null) {
						builder.append(", ");
					}
					builder.append(" ");
//					builder.append(I18NMessages.getMessages().on());
					builder.append(": ");
					builder.append("<font color='blue'><b>");
					builder.append(auditInfo.getPostedOn());
					builder.append("</font></b>");
				}
				if (auditInfo.getPrintedBy() != null) {
					builder.append(", ");
//					builder.append(I18NMessages.getMessages().printedBy());
					builder.append(": ");
					builder.append("<font color='blue'><b>");
					builder.append(auditInfo.getPrintedBy());
					builder.append("</font></b>");
				}
				if (auditInfo.getPrintedOn() != null) {
					if (auditInfo.getPrintedBy() == null) {
						builder.append(", ");
					}
					builder.append(" ");
//					builder.append(I18NMessages.getMessages().on());
					builder.append(": ");
					builder.append("<font color='blue'><b>");
					builder.append(auditInfo.getPrintedOn());
					builder.append("</font></b>");
				}
				if (auditInfo.getRowscn() != null) {
					builder.append(", ");
//					builder.append(I18NMessages.getMessages()
//							.modificationCount());
					builder.append(": ");
					builder.append("<font color='blue'><b>");
					builder.append(auditInfo.getRowscn());
					builder.append("</font></b>");
				}
				if (auditInfo.isUpdatePending()) {
					builder.append(", ");
//					builder.append(I18NMessages.getMessages()
//							.persistencePending());
				}
				if (builder.toString().trim().isEmpty()) {
					unNotify(messageArea);
				} else {
					builder.insert(0, "<font color='red' size=-3> <i> ");
					builder.append(".</font> </i> ");
					messageArea.setHTML(builder.toString());
				}
			} else {
				messageArea.setHTML("<font color='red' size=-2> <i> "
						+ auditInfo.getSystemMessage().getMessage()
						+ ".</font> </i> ");
			}
		}
	}
}
