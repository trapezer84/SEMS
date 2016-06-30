package com.ktds.sems.common;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SemsContext {

	public static ServletContext getCtx(HttpServletRequest request) {
		return getCtx(request.getSession());
		
	}
	
	public static ServletContext getCtx(HttpSession session) {
		ServletContext context = session.getServletContext();
		return context;
	}
	
	public static ServletContext getCtx(HttpSessionEvent sessionEvent) {
		HttpSession session = sessionEvent.getSession();
		return getCtx(session);
	}
	
	public static <T> T getBean(HttpSession session, String beanName) {
		WebApplicationContext context = getApplicationContext(getCtx(session));
		return (T) context.getBean(beanName);
	}
	
	private static WebApplicationContext getApplicationContext(ServletContext context) { 
		return WebApplicationContextUtils.getWebApplicationContext(context);
		
	}
	
}
