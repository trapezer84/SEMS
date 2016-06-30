package com.ktds.sems.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class LoginStore {

	private static volatile LoginStore loginStore;
	private Map<String, HttpSession> loginSessions;
	
	private LoginStore() {
		loginSessions = new HashMap<String, HttpSession>();
	}

	public static synchronized LoginStore getInstance() {

		if (loginStore == null) {
			loginStore = new LoginStore();
		}

		return loginStore;
	}

	public void add(String memberId, HttpSession session) {
		loginSessions.put(memberId, session);
	}

	public HttpSession get(String memberId) {
		return loginSessions.get(memberId);
	}
	
	public String getMemberId(String sessionID) {
		
		Iterator<String> memberIdList = loginSessions.keySet().iterator();
		String memberId = null;
		
		HttpSession session = null;
		while ( memberIdList.hasNext() ) {
			memberId = memberIdList.next();
			
			session = loginSessions.get(memberId);
			if ( session.getId().equals(sessionID) ) {
				return memberId;
			}
		}
		
		return memberId;
	}
	
	public void logout(String memberId) {
		if (loginSessions.containsKey(memberId)) {
			try {
				loginSessions.get(memberId).invalidate();
			} catch (RuntimeException re) {
			}
			loginSessions.remove(memberId);
		}
	}

}
