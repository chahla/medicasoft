package com.rsoft.medicasoft.server.dao;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.rsoft.medicasoft.shared.MessageCodes;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.PersistenceException;

public class DaoFactory {
	private static final String _DAO = "Default";
	private static final String _DAO_DESCRIPTOR = "Dao";
	private static final String _DEFAULT_DAO_PACKAGE = "com.rsoft.medicasoft.server.dao.";
	private static final String _DEFAULT_MODEL_PACKAGE = "com.rsoft.medicasoft.shared.model.";

	public static <T extends ModelBase> DaoContext<T> createDao(
			String daoPrefix, String suffixe, List<Object> daoParameters)
			throws PersistenceException {
		return createDao(daoPrefix, _DEFAULT_DAO_PACKAGE, suffixe,
				daoParameters);
	}

	@SuppressWarnings("unchecked")
	public static <T extends ModelBase> DaoContext<T> createDao(
			String daoPrefix, String prefixe, String suffixe,
			List<Object> daoParameters) throws PersistenceException {
		// PersistenceException
		// Les informations relatives a l'utilisateurs seront prises au niveau
		// de la session
		String category = "";
		try {
			if (prefixe == null) {
				prefixe = "";
			}
			String daoClass = prefixe
					+ category
					+ (daoPrefix != null && !daoPrefix.trim().isEmpty() ? daoPrefix
							: _DAO) + _DAO_DESCRIPTOR;
			Class<?> _c = null;
			_c = Class.forName(daoClass);
			Constructor<?> constructor = (Constructor<?>) _c.getConstructor();
			IDao<T> dao = ((IDao<T>) constructor.newInstance());
			dao.setDaoParameters(daoParameters);
			dao.setModelClass((Class<T>) Class.forName(_DEFAULT_MODEL_PACKAGE
					+ suffixe));
			return new DaoContext<T>(dao);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace(System.out);
			throw new PersistenceException(
					MessageCodes.FUNCTION_NOT_YET_IMPLEMENTED.name(), true,
					"This option is not yet developped");
		} catch (IllegalArgumentException e) {
			e.printStackTrace(System.out);
			throw new PersistenceException(MessageCodes.INTERNAL_ERROR.name(),
					true,
					"Internal error. Invalid persistence class name no empty constructor found");
		} catch (InstantiationException e) {
			e.printStackTrace(System.out);
			throw new PersistenceException(MessageCodes.INTERNAL_ERROR.name(),
					true, "Internal error. Unable to instanciate class ("
							+ prefixe + suffixe + ")");
		} catch (IllegalAccessException e) {
			e.printStackTrace(System.out);
			throw new PersistenceException(MessageCodes.INTERNAL_ERROR.name(),
					true, "Internal error. Unable to instanciate class ("
							+ prefixe + suffixe + ")");
		} catch (InvocationTargetException e) {
			e.printStackTrace(System.out);
			throw new PersistenceException(MessageCodes.INTERNAL_ERROR.name(),
					true, "Internal error. Cause: " + e.getMessage());
		} catch (SecurityException e) {
			e.printStackTrace(System.out);
			throw new PersistenceException(MessageCodes.INTERNAL_ERROR.name(),
					true, "Internal error. Cause: " + e.getMessage());
		} catch (NoSuchMethodException e) {
			e.printStackTrace(System.out);
			throw new PersistenceException(MessageCodes.INTERNAL_ERROR.name(),
					true,
					"Internal error. Invalid persistence class name no empty constructor found");
		}
	}
}
