package com.ktds.sems.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.ktds.sems.member.vo.LoginHistorySearchVO;

public class HistorySearchStore {
	private static volatile HistorySearchStore historySearchStore;
	private Map<LoginHistorySearchVO, HttpSession> historySessions;

	private HistorySearchStore() {
		historySessions = new HashMap<LoginHistorySearchVO, HttpSession>();
	}

	public static synchronized HistorySearchStore getInstance() {
		if (historySearchStore == null) {
			historySearchStore = new HistorySearchStore();
		}
		return historySearchStore;
	}

	public void add(LoginHistorySearchVO historySearchVO, HttpSession session) {
		historySessions.put(historySearchVO, session);
	}

	public HttpSession get(LoginHistorySearchVO historySearchVO) {
		return historySessions.get(historySearchVO);
	}

	public void init(LoginHistorySearchVO historySearchVO) {
		if (historySessions.containsKey(historySearchVO)) {
			try {
				historySessions.get(historySearchVO).invalidate();
			} catch (RuntimeException re) {
			}
			historySessions.remove(historySearchVO);
		}
	}
}
