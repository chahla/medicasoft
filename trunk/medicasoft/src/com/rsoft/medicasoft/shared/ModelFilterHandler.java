package com.rsoft.medicasoft.shared;

import com.sencha.gxt.data.shared.loader.FilterHandler;
public class ModelFilterHandler extends FilterHandler<ModelBase> {

	@Override
	public ModelBase convertToObject(String value) {
		if (value != null) {
			try {
				ModelBase dummy = new ModelBase() {
					private static final long serialVersionUID = 1L;

					@Override
					public String getKey() {
						return null;
					}

					@Override
					public void validateModel() throws ModelException {
					}

					@Override
					public void merge(ModelBase model) {
					}

					@Override
					public Object getPrimaryKey() {
						return null;
					}
				};
				dummy.setEntityId(Long.parseLong(value));
				return dummy;
			} catch (Exception ex) {
			}
		}
		return null;
	}

	@Override
	public String convertToString(ModelBase object) {
		if (object != null && object.getEntityId() != null) {
			Long.toString(object.getEntityId());
		}
		return null;
	}
}
