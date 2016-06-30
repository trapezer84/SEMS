package com.ktds.sems.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

// 아래 패키지아래 발생된 예외들을 모두 이컨트롤러에서 확인하겠다.
@ControllerAdvice("com.ktds.sems")
public class CustomExceptionHandler {
	
	//{}은 배열이라는 뜻 여러개 적을려면 {, }일케함 됌
	@ExceptionHandler({RuntimeException.class})
	public ModelAndView runtimeExceptionHandler(RuntimeException re, HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.setViewName("error/500");
		view.addObject("message",re.getMessage());	
		
		// Referer은 이 페이지를 호출하기 직전의 페이지를 의미한다.
		String referer =  request.getHeader("Referer");
		view.addObject("from", referer);
		
		view.addObject("content","내가 보냄...");
		return view;
	}
	
}
