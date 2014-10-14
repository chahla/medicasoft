package com.rsoft.medicasoft.server.dao;

import java.lang.reflect.Method;
import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.rsoft.medicasoft.shared.Institution;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.ModelBaseX;
import com.rsoft.medicasoft.shared.model.EntityKeyDescriptor;
import com.rsoft.medicasoft.shared.model.UserProfile;

public class DaoTools {
	public static String SET_SUCCURSALE_ID_METHOD_NAME = "setSuccursaleId";
	public static String SET_DEPARTEMENT_ID_METHOD_NAME = "setDepartementId";
	public static String SET_USER_ID_METHOD_NAME = "setUserId";
	private static String DEFAULT_MODEL_PACKAGE = "com.rsoft.medicasoft.shared.model.";

	public static void finishObject(Object obj, UserProfile profile,
			EntityKeyDescriptor entityKeyDescriptor, String action)
			throws Exception {
		/*
		 * Ajout automatiquement des informations d'audit et de gestion d'access
		 * concurrent
		 */
		if (obj instanceof ModelBase) {
			ModelBase model = (ModelBase) obj;
			if ("PERSIST".equalsIgnoreCase(action)) {
				/*
				 * Drapeau permettant de savoir si l'enregistrement est entrain
				 * d'etre modifié par quelqu'un d'autre ou pas
				 */
				model.setRowscn(0L);
				model.setCreatedBy(profile.getUserId());
				model.setCreatedOn(new Date());
			} else if ("MERGE".equalsIgnoreCase(action)) {
				model.setUpdatedBy(profile.getUserId());
				model.setUpdatedOn(new Date());
				/*
				 * Drapeau permettant de savoir si l'enregistrement est entrain
				 * d'etre modifié par quelqu'un d'autre ou pas
				 */
				model.setRowscn(model.getRowscn() != null ? model.getRowscn() + 1
						: 1);
			} else if ("SEARCH".equalsIgnoreCase(action)) {
			}
		}
		/*
		 * Ajout automatiquement si c'est necessaire, l'instutiton de la
		 * personne connectée avant l'insertion
		 */
		if (obj instanceof ModelBaseX && profile.getEntityId() != null) {
			ModelBaseX model = (ModelBaseX) obj;
			if ("PERSIST".equalsIgnoreCase(action)) {
				//
				Key<Institution> key = Key.create(Institution.class,
						profile.getEntityId());
				/*
				 * Les entites appartiennent a une institution, finir l'objet
				 * avant son insertion en y ajoutant l'institution de la
				 * personne connectee
				 */
				model.setInstitution(key);
			}
		}
		/*
		 * Une entite peut avoir une cle etrangere, ajoute automatiquement la
		 * cle etrangere qui va etre passee lors de la requete, automatiquement
		 * dans un objet de type EntityKeyDescriptor. On ne saura pas avec quel
		 * objet on a affaire, donc, on doit tout fait de facon dynamique Si on
		 * appelle le setter de facon dynamique.
		 * 
		 * On doit s'assurer que la methode existe, je le fais express de ne pas
		 * capturer d'exception
		 */
		if (entityKeyDescriptor != null
				&& entityKeyDescriptor.getHeaderClassName() != null
				&& !entityKeyDescriptor.getHeaderClassName().trim().isEmpty()
				&& entityKeyDescriptor.getClassName() != null
				&& !entityKeyDescriptor.getClassName().trim().isEmpty()
				&& entityKeyDescriptor.getKeySetter() != null
				&& !entityKeyDescriptor.getKeySetter().trim().isEmpty()) {
			String headerEntityName = entityKeyDescriptor.getHeaderClassName();
			// Si on ne met pas de package, on met le package par defaut
			// Si votre modele se trouve dans un autre package, il faut le
			// preciser
			// de facon explicite..
			if (!headerEntityName.contains("\\.")) {
				headerEntityName = DEFAULT_MODEL_PACKAGE + headerEntityName;
			}
			if (entityKeyDescriptor.isRef()) {
				Method _method = obj.getClass().getMethod(
						entityKeyDescriptor.getKeySetter(), Ref.class);
				ModelBase objx = (ModelBase)Class.forName(headerEntityName).getConstructor().newInstance();
				objx.setEntityId(entityKeyDescriptor.getHeaderId());
				Ref<?> headerRef = Ref.create(objx);
				_method.invoke(obj, headerRef);
			} else {
				Method _method = obj.getClass().getMethod(
						entityKeyDescriptor.getKeySetter(), Key.class);
				Key<?> headerkey = Key.create(Class.forName(headerEntityName),
						entityKeyDescriptor.getHeaderId());
				_method.invoke(obj, headerkey);
			}
		}
	}
}