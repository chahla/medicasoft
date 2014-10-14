package com.rsoft.medicasoft.server.dao;

import java.util.ArrayList;
import java.util.List;

import com.rsoft.medicasoft.shared.PersistenceException;
import com.rsoft.medicasoft.shared.model.UserGroup;
import com.rsoft.medicasoft.shared.model.UserGroupDetail;

public class UserGroupDao extends DefaultDao<UserGroup> {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void insert() throws PersistenceException {
		if (this.getModel() != null && this.getDaoParameters() != null
				&& !this.getDaoParameters().isEmpty()
				&& !"admin".equalsIgnoreCase(model.getType())) {
			List<String> detailsNames = (List<String>) this.getDaoParameters()
					.get(0);
			List<UserGroupDetail> details = new ArrayList<UserGroupDetail>();
			if (detailsNames != null) {
				long entityId = 1;
				for (String detailName : detailsNames) {
					UserGroupDetail detail = new UserGroupDetail();
					detail.initAttributes(true);
					detail.setEntityId(entityId++);
					detail.setFormId(detailName);
					details.add(detail);
				}
			}
			this.getModel().setUserGroupDetails(details);
		}
		super.insert();
	}

	@Override
	public void merge() throws PersistenceException {
		if (this.getModel() != null && this.getDaoParameters() != null
				&& !this.getDaoParameters().isEmpty()
				&& "admin".equalsIgnoreCase(model.getType())) {
			model.setUserGroupDetails(new ArrayList<UserGroupDetail>());
		}
		super.merge();
	}
}
