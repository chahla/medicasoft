package com.rsoft.medicasoft.server.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rsoft.medicasoft.client.service.EasyGwtRpcService;
import com.rsoft.medicasoft.server.MessagesUtils;
import com.rsoft.medicasoft.server.PagingLoadTools;
import com.rsoft.medicasoft.server.ServerUtilities;
import com.rsoft.medicasoft.server.dao.ConnectionManager;
import com.rsoft.medicasoft.server.dao.DaoContext;
import com.rsoft.medicasoft.server.dao.DaoFactory;
import com.rsoft.medicasoft.server.dao.DaoParameterManager;
import com.rsoft.medicasoft.shared.ConnectionException;
import com.rsoft.medicasoft.shared.DataException;
import com.rsoft.medicasoft.shared.FilterWrapper;
import com.rsoft.medicasoft.shared.MessageType;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.PersistenceException;
import com.rsoft.medicasoft.shared.SystemMessage;
import com.rsoft.medicasoft.shared.UserInfo;
import com.rsoft.medicasoft.shared.menu.Menu;
import com.rsoft.medicasoft.shared.model.EntityKeyDescriptor;
import com.rsoft.medicasoft.shared.model.RequestDescriptor;
import com.rsoft.medicasoft.shared.xml.LireXML;
import com.rsoft.medicasoft.shared.xml.XMLRootMenu;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

public class EasyGwtRpcServiceImpl extends RemoteServiceServlet implements
		EasyGwtRpcService {
	private static final long serialVersionUID = 1L;

	@Override
	public <T extends ModelBase> ArrayList<T> persist(
			RequestDescriptor descpritor, EntityKeyDescriptor keyDescriptor,
			ArrayList<T> values) throws PersistenceException,
			ConnectionException {
		UserInfo userInfo = ConnectionManager.getUserInfo(this
				.getThreadLocalRequest());
		if (userInfo == null) {
			throw new PersistenceException(
					"Internal error. User Information is null.");
		}
		if (values == null) {
			throw new PersistenceException(MessagesUtils.getMessage(
					userInfo.getUserProfile(), "unexpectedError_nullModel",
					"Internal error. The model is null."));
		}
		DaoContext<ModelBase> dao = DaoFactory
				.createDao(descpritor.getPersistenceManagerPrefix(), descpritor
						.getEntityName(), DaoParameterManager.getParameters(
						this.getThreadLocalRequest().getSession(),
						descpritor.getPersistenceManagerPrefix()));
		dao.setUserProfile(userInfo.getUserProfile());
		dao.setEntityKeyDescriptor(keyDescriptor);
		for (ModelBase value : values) {
			try {
				dao.setModel((ModelBase) value);
				if ("INSERT".equalsIgnoreCase(value.getSTATUS())) {
					dao.insert();
				} else if ("MERGE".equalsIgnoreCase(value.getSTATUS())) {
					dao.merge();
				} else if ("REMOVE".equalsIgnoreCase(value.getSTATUS())) {
					dao.remove();
				} else {
					throw new PersistenceException(
							MessagesUtils.getMessage(
									userInfo.getUserProfile(),
									"unexpectedError_invalidAction",
									"Internal error. Unexpected action. Valid actions(INSERT, MERGE, REMOVE). Current Action: "
											+ value.getSTATUS()));
				}
				value.setErrorMessage(null);
			} catch (PersistenceException ex) {
				value.setErrorMessage(new SystemMessage(MessageType.error, ex
						.getMessage() != null ? ex.getMessage() : ex.toString()));
			}
		}
		return values;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends ModelBase> T persist(RequestDescriptor descpritor,
			EntityKeyDescriptor keyDescriptor, T value)
			throws PersistenceException, ConnectionException {
		UserInfo userInfo = ConnectionManager.getUserInfo(this
				.getThreadLocalRequest());
		if (userInfo == null) {
			throw new PersistenceException(
					"Internal error. User Information is null.");
		}
		if (value == null) {
			throw new PersistenceException(MessagesUtils.getMessage(
					userInfo.getUserProfile(), "unexpectedError_nullModel",
					"Internal error. The model is null."));
		}
		DaoContext<ModelBase> dao = DaoFactory
				.createDao(descpritor.getPersistenceManagerPrefix(), descpritor
						.getEntityName(), DaoParameterManager.getParameters(
						this.getThreadLocalRequest().getSession(),
						descpritor.getPersistenceManagerPrefix()));
		dao.setModel((ModelBase) value);
		dao.setUserProfile(userInfo.getUserProfile());
		dao.setEntityKeyDescriptor(keyDescriptor);
		if ("INSERT".equalsIgnoreCase(value.getSTATUS())) {
			dao.insert();
			return (T) dao.getModel();
		} else if ("MERGE".equalsIgnoreCase(value.getSTATUS())
				|| "ACTIVATE".equalsIgnoreCase(value.getSTATUS())
				|| "DEACTIVATE".equalsIgnoreCase(value.getSTATUS())
				|| "VALIDATE".equalsIgnoreCase(value.getSTATUS())
				|| "DEVALIDATE".equalsIgnoreCase(value.getSTATUS())
				|| "POST".equalsIgnoreCase(value.getSTATUS())
				|| "UNPOST".equalsIgnoreCase(value.getSTATUS())
				|| "CANCEL".equalsIgnoreCase(value.getSTATUS())
				|| "UNCANCEL".equalsIgnoreCase(value.getSTATUS())
				|| "PAY".equalsIgnoreCase(value.getSTATUS())
				|| "CLOSE".equalsIgnoreCase(value.getSTATUS())
				|| "UNCLOSE".equalsIgnoreCase(value.getSTATUS())
				|| "FINALIZE".equalsIgnoreCase(value.getSTATUS())) {
			dao.merge();
			return (T) dao.getModel();
		} else if ("REMOVE".equalsIgnoreCase(value.getSTATUS())) {
			dao.remove();
			return (T) dao.getModel();
		} else {
			throw new PersistenceException(
					MessagesUtils.getMessage(
							userInfo.getUserProfile(),
							"unexpectedError_invalidAction",
							"Internal error. Unexpected action. Valid actions(INSERT, MERGE, REMOVE). Current Action: "
									+ value.getSTATUS()));
		}
	}

	@Override
	public <T extends ModelBase> ArrayList<T> persist(
			RequestDescriptor descpritor, ArrayList<T> values)
			throws PersistenceException, ConnectionException {
		return persist(descpritor, null, values);
	}

	public <T extends ModelBase> T persist(RequestDescriptor descpritor, T value)
			throws PersistenceException, ConnectionException {
		return persist(descpritor, null, value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends ModelBase> ArrayList<T> search(
			RequestDescriptor descpritor, FilterWrapper filters)
			throws PersistenceException, ConnectionException {
		DaoContext<ModelBase> dao = DaoFactory
				.createDao(descpritor.getPersistenceManagerPrefix(), descpritor
						.getEntityName(), DaoParameterManager.getParameters(
						this.getThreadLocalRequest().getSession(),
						descpritor.getPersistenceManagerPrefix()));
		UserInfo userInfo = ConnectionManager.getUserInfo(this
				.getThreadLocalRequest());
		dao.setUserProfile(userInfo.getUserProfile());
		dao.setFilters(filters);
		ArrayList<T> x = (ArrayList<T>) dao.search();
		return x;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends ModelBase> PagingLoadResult<T> search(
			RequestDescriptor descpritor, PagingLoadConfig loadConfig,
			FilterWrapper filters) throws PersistenceException,
			ConnectionException {
		DaoContext<ModelBase> dao = DaoFactory
				.createDao(descpritor.getPersistenceManagerPrefix(), descpritor
						.getEntityName(), DaoParameterManager.getParameters(
						this.getThreadLocalRequest().getSession(),
						descpritor.getPersistenceManagerPrefix()));
		UserInfo userInfo = ConnectionManager.getUserInfo(this
				.getThreadLocalRequest());
		if (userInfo == null) {
			throw new PersistenceException(
					"Internal error. User Information is null.");
		}
		dao.setFilters(filters);
		dao.setUserProfile(userInfo.getUserProfile());
		ArrayList<ModelBase> datas = new ArrayList<ModelBase>(dao.search());
		return (PagingLoadResult<T>) new PagingLoadTools<ModelBase>(loadConfig,
				datas).getNextPage();
	}

	@Override
	public <T extends ModelBase> String extract(RequestDescriptor descpritor,
			T value) throws PersistenceException, ConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends ModelBase> T validate(RequestDescriptor descpritor,
			T value) throws PersistenceException, ConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends ModelBase> T unvalidate(RequestDescriptor descpritor,
			T value) throws PersistenceException, ConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends ModelBase> T post(RequestDescriptor descpritor, T value)
			throws PersistenceException, ConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends ModelBase> T activate(RequestDescriptor descpritor,
			T value) throws PersistenceException, ConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends ModelBase> T deactivate(RequestDescriptor descpritor,
			T value) throws PersistenceException, ConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

	// Autres methodes, fera en sorte que le seul le menu de l'utilisateur soit
	// fourni
	// en fonction des ses droits et de sont type d'entreprise
	@Override
	public UserInfo loadUserInfos() throws DataException {
		LireXML lecteurMenus = new LireXML(
				"/com/rsoft/medicasoft/shared/config/menu.xml");
		UserInfo userInfo = new UserInfo();
		try {
			List<String> menuNames = new ArrayList<String>();
			ArrayList<Menu> menus = new ArrayList<Menu>();
			ArrayList<XMLRootMenu> xmlMenus = lecteurMenus.getXMLMenu();
			for (XMLRootMenu xmlMenu : xmlMenus) {
				menus.add(ServerUtilities.buildMenu(xmlMenu, menuNames));
			}
			userInfo = ConnectionManager.getUserInfo(this
					.getThreadLocalRequest());
			userInfo.setMenus(menus);
			getThreadLocalRequest().getSession().setAttribute(
					ServerUtilities.MENU_NAMES_ATTRIBUTE_NAME, menuNames);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw new DataException(MessagesUtils.getMessage(
					userInfo != null ? userInfo.getUserProfile() : null,
					"unexpectedError_loadingMenu",
					"Internal error. Unexpected error loading menu " + ""));
		}
		return userInfo;
	}
}