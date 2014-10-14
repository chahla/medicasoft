package com.rsoft.medicasoft.server;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

import com.rsoft.files_processor.Processor;
import com.rsoft.medicasoft.server.dao.ConnectionManager;
import com.rsoft.medicasoft.server.dao.DaoContext;
import com.rsoft.medicasoft.server.dao.DaoFactory;
import com.rsoft.medicasoft.server.dao.DaoParameterManager;
import com.rsoft.medicasoft.shared.FilterWrapper;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.UserInfo;
import com.rsoft.medicasoft.shared.XFilter;
import com.rsoft.medicasoft.shared.model.ParamInout;

import gwtupload.server.exceptions.UploadActionException;
import gwtupload.server.gae.AppEngineUploadAction;

public class EasyUploadServlet extends AppEngineUploadAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String executeAction(HttpServletRequest request,
			List<FileItem> sessionFiles) throws UploadActionException {
		String response = "";
		String loadingParam = "";
		String entityName = request.getParameter("entityName") != null ? request
				.getParameter("entityName") : "";
		UserInfo userInfo = null;
		try {
			DaoContext<ModelBase> daoParam = DaoFactory.createDao(null,
				 	"ParamInout", DaoParameterManager.getParameters(
							getThreadLocalRequest().getSession(), null));
			userInfo = ConnectionManager.getUserInfo(getThreadLocalRequest());
			daoParam.setUserProfile(userInfo.getUserProfile());
			FilterWrapper filters = new FilterWrapper();
			loadingParam = entityName.toUpperCase() + "_LOADING";
			filters.addFilter(new XFilter("eq", "description", "string",
					loadingParam));
			daoParam.setFilters(filters);
			ArrayList<ModelBase> results = daoParam.search();
			if (results == null || results.isEmpty()) {
				return MessagesUtils.getMessage(userInfo.getUserProfile(),
						"undefined_parameter", "Undefined parameter.",
						new String[] { loadingParam });
			}
			String paramXML = null;
			ParamInout paramInout = (ParamInout) results.get(0);
			paramXML = paramInout.getContenu();
			String errorMessage = null;
			long dataSize = 0;
			if (paramXML != null && !paramXML.trim().isEmpty()) {
				DaoContext<ModelBase> daoLoading = DaoFactory.createDao(null,
						entityName, DaoParameterManager.getParameters(
								getThreadLocalRequest().getSession(), null));
				daoLoading.setUserProfile(userInfo.getUserProfile());
				for (FileItem item : sessionFiles) {					
					if (false == item.isFormField() && item.getSize() > 0) {
						dataSize = item.getSize();
						com.rsoft.files_processor.Utilities.DEFAULT_PKGE_NAME = "com.rsoft.medicasoft";
						Processor.process(daoLoading, "DEFAULT"/*
																 * userInfo.
																 * getUserProfile
																 * ()
																 * .getInstitution
																 * ().
																 * getNomInstitution
																 * ()
																 */,
								loadingParam, paramXML,
								new ByteArrayInputStream(item.get()));
					}
					errorMessage = "";
				}
				if (errorMessage == null || errorMessage.isEmpty()) {
					response = MessagesUtils.getMessage(
							userInfo.getUserProfile(),
							"file_loaded_successfully", dataSize
									+ " bytes of data file(s) loaded successfully",
							new String[] { Long.toString(dataSize) });
				} else {
					response = errorMessage;
				}
			} else {
				return MessagesUtils.getMessage(userInfo.getUserProfile(),
						"undefined_parameter", "Undefined parameter.");
			}
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			return MessagesUtils.getMessage(
					userInfo != null ? userInfo.getUserProfile() : null,
					"unexpectedError_insertRecord",
					"Unexpected error inserting record.");

		}
		return response;
	}
}