package com.ktds.sems.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ktds.sems.common.Session;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
		boolean wasLogin = request.getSession().getAttribute(Session.MEMBER) != null;
		
		if ( wasLogin ) {
			// 로그인 체크시 강사, 관리자, 회원 접근 가능 
			String authority = (String) request.getSession().getAttribute(Session.MEMBER_TYPE);
			
			if (authority.equals("MBR") || authority.equals("TR") || authority.equals("ADM")) {
				response.sendRedirect("/main");
				return;
			} else {
				request.getSession().invalidate();
				return;
			}
		}
	}
}
