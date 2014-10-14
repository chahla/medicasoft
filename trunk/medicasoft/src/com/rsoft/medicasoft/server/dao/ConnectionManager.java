package com.rsoft.medicasoft.server.dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.rsoft.medicasoft.shared.ConnectionException;
import com.rsoft.medicasoft.shared.MessageCodes;
import com.rsoft.medicasoft.shared.UserInfo;
import com.rsoft.medicasoft.shared.model.UserProfile;

public class ConnectionManager {
	private ConnectionManager() {
	}

	@SuppressWarnings("unused")
	public static UserInfo getUserInfo(HttpServletRequest request)
			throws ConnectionException {
		UserInfo userInfo = new UserInfo();
		UserProfile profile = new UserProfile();
		profile.setUserId("DEFAULT");
		profile.setFirstName("Jean Robelkend");
		profile.setLastName("Charles");
		profile.setLanguage("en");
		profile.setTitle("Mr");
		userInfo.setUserProfile(profile);
		if(true) {
			return userInfo;
		}
		if (request == null) {
			throw new ConnectionException(
					
					MessageCodes.SESSION_INVALIDATED.name(), false,
					"sessionInvalidate null session ");
		}
		HttpSession session = request.getSession(false);
		if (session == null) {
			throw new ConnectionException(
					MessageCodes.SESSION_INVALIDATED.name(), false,
					"sessionInvalidate null session ");
		}
		// Les cles de sessions sont differentes,
		// l'utilisateur est un intrui, ou il est entrain d'essayer de se
		// connecter
		// a partir d'un autre terminal
		// qu'il se reconnecte en vue d'avoir un numero de session valide
		String sessionId = request.getHeader(MessageCodes.CLIENT_SESSION_ID
				.name());
		if (sessionId == null || sessionId.equals(session.getId())) {
			throw new ConnectionException(
					MessageCodes.SESSION_INVALIDATED.name(), false,
					"sessionInvalidate null session ");
		}
		Object object = session.getAttribute("userProfile");
		if (object == null) {
			userInfo = new UserInfo();
			String utilisateur = (String) session.getAttribute("pin");
			if (utilisateur == null) {
				session.invalidate();
				throw new ConnectionException(
						MessageCodes.SESSION_INVALIDATED.name(), false,
						"sessionInvalidate");
			} else {
				/*
				 * try { } catch (PersistenceException e) {
				 * e.printStackTrace(System.out); session.invalidate(); throw
				 * new ConnectionException(
				 * MessageCodes.SESSION_INVALIDATED.name(), false,
				 * "sessionInvalidate"); }
				 */}
		} else {
			// La session cote serveur n'est pas mise à jour lors des evenements
			// clients
			// if (new Boolean(
			// (System.currentTimeMillis() - session.getLastAccessedTime()) <
			// profile
			// .getDelaiInactivite())) {
			// throw new ConnectionException("sessionInvalidate null session ");
			// }
		}
		return userInfo;
	}
}
