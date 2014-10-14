package com.rsoft.medicasoft.shared;

import java.math.BigDecimal;
import java.math.BigInteger;
//import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
	// public static CurrentProfile userProfile;
	public static String DATE_FORMAT = "dd/MM/yyyy";
	public static String DATE_TIME_FORMAT = "dd/MM/yyyy k:mm";

	public static Date toDate(String value) {
		if (value != null) {
			try {
		//		return new SimpleDateFormat(DATE_FORMAT).parse(value);
			} catch (Exception ex) {
			}
		}
		return null;
	}

	public static Date toDateTime(String value) {
		if (value != null) {
			try {
	//			return new SimpleDateFormat(DATE_TIME_FORMAT).parse(value);
			} catch (Exception ex) {
			}
		}
		return null;
	}

	public static Integer toInteger(String value) {
		if (value != null) {
			try {
				return Integer.parseInt(value.trim());
			} catch (Exception ex) {
			}
		}
		return null;
	}

	public static Long toLong(String value) {
		if (value != null) {
			try {
				return Long.parseLong(value.trim());
			} catch (Exception ex) {
			}
		}
		return null;
	}

	public static BigInteger toBigInteger(String value) {
		if (value != null) {
			try {
				return new BigInteger(value.trim());
			} catch (Exception ex) {
			}
		}
		return null;
	}

	public static Double toDouble(String value) {
		if (value != null) {
			try {
				return Double.parseDouble(value.trim());
			} catch (Exception ex) {
			}
		}
		return null;
	}

	public static BigDecimal toBigDecimal(String value) {
		if (value != null) {
			try {
				return new BigDecimal(value.trim());
			} catch (Exception ex) {
			}
		}
		return null;
	}

	public static void maFonctionJavaAide(int id) {
	}

	public static native void exportationMaFonctionAide() /*-{
    // $wnd.maFonctionJavaAide = $entry(@com.inuqua.gestionAcademique.client.Utilities::maFonctionJavaAide(I));
     }-*/;
}
