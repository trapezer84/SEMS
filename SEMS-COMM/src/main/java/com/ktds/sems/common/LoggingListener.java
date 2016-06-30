package com.ktds.sems.common;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.ktds.sems.member.dao.MemberDAO;

public class LoggingListener implements HttpSessionListener {

	private MemberDAO memberDAO;
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent hse) {}

	@Override
	public void sessionDestroyed(HttpSessionEvent hse) {
		HttpSession session = hse.getSession();
		memberDAO = SemsContext.getBean(session, "memberDAO");
		
		LoginStore store = LoginStore.getInstance();
		String memberId = store.getMemberId(session.getId());
		
		if ( StringUtils.isNotEmpty(memberId) ) {
			memberDAO.stampLogoutTimeByMemberId(memberId);
			store.logout(memberId);
		} 
	}

}
