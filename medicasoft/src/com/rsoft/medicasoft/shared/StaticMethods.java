package com.rsoft.medicasoft.shared;

import java.util.Date;

public class StaticMethods {
	public static String formatDate(Date date) {
		if (date != null) {
			 @SuppressWarnings("deprecation")
			String jour=String.valueOf(date.getDate());
			 jour=jour.length()<2?"0"+jour:jour;
			  StringBuilder stringBuilder = new StringBuilder();
			 stringBuilder.append(jour);
			 @SuppressWarnings("deprecation")
			String mois=String.valueOf(date.getMonth()+1);
			 mois=mois.length()<2?"0"+mois:mois;
	    	 @SuppressWarnings("deprecation")
			String annee=String.valueOf((date.getYear()+1900));
		    return jour + "-" + mois+ "-" +annee;
			// return new
			// SimpleDateFormat(StaticField.DATE_FORMAT).format(date);
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public static String formatDateTime(Date date) {
		if (date != null) {
			return date.getDay() + "/" + date.getMonth() + "/" + date.getYear()
					+ " " + date.getHours() + ":" + date.getMinutes();
			// return new SimpleDateFormat(StaticField.DATE_TIME_FORMAT)
			// .format(date);
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public static String formatTime(Date time) {
		if (time != null) {
			return time.getHours() + ":" + time.getMinutes();
			// return new SimpleDateFormat(StaticField.DATE_TIME_FORMAT)
			// .format(date);
		}
		return null;
	}
}
