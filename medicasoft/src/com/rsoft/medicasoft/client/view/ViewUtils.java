package com.rsoft.medicasoft.client.view;

import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HTML;
import com.rsoft.medicasoft.shared.AuditModel;
import com.rsoft.medicasoft.shared.GlobalAuditInfo;
import com.rsoft.medicasoft.shared.SystemMessage;
import com.rsoft.medicasoft.shared.i18n.I18NMessages;

public class ViewUtils {
	public static void notify(HTML messageArea, SystemMessage message) {
		setMessageAreaStyle(messageArea, "#419641"); // Success message
		messageArea.setHTML(message.getMessage());
	}

	public static void warning(HTML messageArea, SystemMessage message) {
		setMessageAreaStyle(messageArea, "#ffffff"); // warning message
		messageArea.setHTML(message.getMessage());
	}

	public static void success(HTML messageArea, SystemMessage message) {
		setMessageAreaStyle(messageArea, "#419641"); // Success message
		messageArea.setHTML(message.getMessage());
	}

	private static void setMessageAreaStyle(HTML messageArea, String backColor) {
		messageArea.setSize("100%", "43%");
		messageArea.getElement().getStyle().setBackgroundColor(backColor);
		messageArea.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
		messageArea.getElement().getStyle().setBorderColor("#2106C2");
		messageArea.getElement().getStyle().setBorderWidth(1, Unit.PX);
		messageArea.getElement().getStyle().setColor("#2106C2");
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
				setMessageAreaStyle(messageArea, "#2aabd2");
				StringBuilder builder = new StringBuilder();
				AuditModel auditModel = auditInfo.getAuditModel();
				if (auditModel != null && auditModel.getCreatedBy() != null) {
					builder.append(I18NMessages.getMessages().createdBy());
					builder.append(": ");
					builder.append("<font color='#ffffff'><b>");
					builder.append(auditModel.getCreatedBy());
					builder.append("</font></b>");
					builder.append(" ");
					builder.append(I18NMessages.getMessages().on());
					builder.append(": ");
					builder.append("<font color='#ffffff'><b>");
					builder.append(auditModel.getCreatedOn());
					builder.append("</font></b>");
				}
				if (auditModel != null && auditModel.getUpdatedBy() != null) {
					builder.append(", ");
					builder.append(I18NMessages.getMessages().updateBy());
					builder.append(": ");
					builder.append("<font color='#ffffff'><b>");
					builder.append(auditModel.getUpdatedBy());
					builder.append("</font></b>");
					builder.append(" ");
					builder.append(I18NMessages.getMessages().on());
					builder.append(": ");
					builder.append("<font color='#ffffff'><b>");
					builder.append(auditModel.getUpdatedOn());
					builder.append("</font></b>");
				}
				if (auditInfo.getValidatedBy() != null) {
					builder.append(", ");
					builder.append(I18NMessages.getMessages().validatedBy());
					builder.append(": ");
					builder.append("<font color='#ffffff'><b>");
					builder.append(auditInfo.getValidatedBy());
					builder.append("</font></b>");
				}
				if (auditInfo.getValidatedOn() != null) {
					if (auditInfo.getValidatedBy() == null) {
						builder.append(", ");
					}
					builder.append(" ");
					builder.append(I18NMessages.getMessages().on());
					builder.append(": ");
					builder.append("<font color='#ffffff'><b>");
					builder.append(auditInfo.getValidatedOn());
					builder.append("</font></b>");
				}
				if (auditInfo.getPostedBy() != null) {
					builder.append(", ");
					builder.append(I18NMessages.getMessages().postedBy());
					builder.append(": ");
					builder.append("<font color='#ffffff'><b>");
					builder.append(auditInfo.getPostedBy());
					builder.append("</font></b>");
				}
				if (auditInfo.getPostedOn() != null) {
					if (auditInfo.getPostedBy() == null) {
						builder.append(", ");
					}
					builder.append(" ");
					builder.append(I18NMessages.getMessages().on());
					builder.append(": ");
					builder.append("<font color='#ffffff'><b>");
					builder.append(auditInfo.getPostedOn());
					builder.append("</font></b>");
				}
				if (auditInfo.getPrintedBy() != null) {
					builder.append(", ");
					builder.append(I18NMessages.getMessages().printedBy());
					builder.append(": ");
					builder.append("<font color='#ffffff'><b>");
					builder.append(auditInfo.getPrintedBy());
					builder.append("</font></b>");
				}
				if (auditInfo.getPrintedOn() != null) {
					if (auditInfo.getPrintedBy() == null) {
						builder.append(", ");
					}
					builder.append(" ");
					builder.append(I18NMessages.getMessages().on());
					builder.append(": ");
					builder.append("<font color='#ffffff'><b>");
					builder.append(auditInfo.getPrintedOn());
					builder.append("</font></b>");
				}
				if (auditInfo.getRowscn() != null) {
					builder.append(", ");
					builder.append(I18NMessages.getMessages()
							.modificationCount());
					builder.append(": ");
					builder.append("<font color='#ffffff'><b>");
					builder.append(auditInfo.getRowscn());
					builder.append("</font></b>");
				}
				if (auditInfo.isUpdatePending()) {
					builder.append(", ");
					builder.append(I18NMessages.getMessages()
							.persistencePending());
				}
				if (builder.toString().trim().isEmpty()) {
					unNotify(messageArea);
				} else {
					builder.insert(0, "<font color='red' size=-3> <i> ");
					builder.append(".</font> </i> ");
					messageArea.setHTML(builder.toString());
				}
			} else {
				setMessageAreaStyle(messageArea, "#c12e2a"); // Error message
				messageArea.setHTML("<font color='#fff' size=-1> <i> "
						+ auditInfo.getSystemMessage().getMessage()
						+ ".</font> </i> ");
			}
		}
	}
}
