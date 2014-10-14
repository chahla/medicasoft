package com.rsoft.medicasoft.shared;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.sencha.gxt.widget.core.client.form.DateTimePropertyEditor;

public class StaticField {
	public static final String DATE_FORMAT ="dd/MM/yyyy";
	public static final String DATE_TIME_FORMAT ="dd/MM/yyyy k:mm";
	public static final String TIME_FORMAT ="k:mm";
	public static final String DECIMAL_FORMAT ="###,###,###,###.00";
	public static final String INTEGER_FORMAT ="0";
	public static final int COMBO_PAGE_SIZE = 5;
	public static final int COMBO_MIN_WIDTH = 350;
	public static final int COMBO_DEFAULT_MIN_CHARS = 0;
	public static final DateTimePropertyEditor DATE_FORMAT_PROPERTYEDITOR = new DateTimePropertyEditor(DateTimeFormat.getFormat(StaticField.DATE_FORMAT));
	public static final DateTimePropertyEditor DATETIME_FORMAT_PROPERTYEDITOR = new DateTimePropertyEditor(DateTimeFormat.getFormat(StaticField.DATE_TIME_FORMAT));
	public static final DateTimePropertyEditor TIME_FORMAT_PROPERTYEDITOR = new DateTimePropertyEditor(DateTimeFormat.getFormat(StaticField.TIME_FORMAT));
}
