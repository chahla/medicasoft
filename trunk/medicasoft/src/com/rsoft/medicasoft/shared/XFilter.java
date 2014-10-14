package com.rsoft.medicasoft.shared;

import java.io.Serializable;
import java.util.List;

public class XFilter implements Serializable {

	private static final long serialVersionUID = 1L;
	private String comparison;
	private String field;
	private String type;
	private String value;
	private boolean embeddedField;
	private List<String> embeddedObjectNames;
	private ModelBase key;

	public XFilter() {
	}

	public XFilter(String field, ModelBase key) {
		super();
		this.field = field;
		this.key = key;
	}

	public XFilter(String comparison, String field, String type, String value) {
		super();
		this.comparison = comparison;
		this.field = field;
		this.type = type;
		this.value = value;
	}

	public XFilter(String comparison, String field, String type, String value,
			List<String> embeddedObjectNames) {
		super();
		this.comparison = comparison;
		this.field = field;
		this.type = type;
		this.value = value;
		this.embeddedObjectNames = embeddedObjectNames;
		this.embeddedField = embeddedObjectNames != null
				&& !embeddedObjectNames.isEmpty();
	}

	public ModelBase getKey() {
		return key;
	}

	public void setKey(ModelBase key) {
		this.key = key;
	}

	/**
	 * @return the embeddedField
	 */
	public boolean isEmbeddedField() {
		return embeddedField;
	}

	/**
	 * @param embeddedField
	 *            the embeddedField to set
	 */
	public void setEmbeddedField(boolean embeddedField) {
		this.embeddedField = embeddedField;
	}

	/**
	 * @return the embeddedObjectNames
	 */
	public List<String> getEmbeddedObjectNames() {
		return embeddedObjectNames;
	}

	/**
	 * @param embeddedObjectNames
	 *            the embeddedObjectNames to set
	 */
	public void setEmbeddedObjectNames(List<String> embeddedObjectNames) {
		this.embeddedObjectNames = embeddedObjectNames;
	}

	/**
	 * @return the comparison
	 */
	public String getComparison() {
		return comparison;
	}

	/**
	 * @param comparison
	 *            the comparison to set
	 */
	public void setComparison(String comparison) {
		this.comparison = comparison;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field
	 *            the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @return the type
	 */
	public Object getValueInType() {
		if ("int".equalsIgnoreCase(getType())
				|| "integer".equalsIgnoreCase(getType())) {
			return Utilities.toInteger((String) value);
		} else if ("biginteger".equalsIgnoreCase(getType())) {
			return Utilities.toInteger((String) value);
		} else if ("date".equalsIgnoreCase(getType())) {
			return Utilities.toDate((String) value);
		} else if ("datetime".equalsIgnoreCase(getType())) {
			return Utilities.toDateTime((String) value);
		} else if ("long".equalsIgnoreCase(getType())) {
			return Utilities.toLong((String) value);
		} else if ("double".equalsIgnoreCase(getType())) {
			return Utilities.toDouble((String) value);
		} else if ("BigDecimal".equalsIgnoreCase(getType())) {
			return Utilities.toBigDecimal((String) value);
		}
		return value;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return (String) value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
