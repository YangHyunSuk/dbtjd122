package com.naver.dbtjd122.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.naver.dbtjd122.dao.UserDAO;
import com.naver.dbtjd122.domain.UserVO;

public class AutoLoginterceptor implements HandlerInterceptor {
	@Autowired
	private UserDAO userDAO;

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		/*
		 * java web?��?�� 쿠키찾기 Cookie[] cookie =arg0.getCookies(); for(Cookie
		 * cookie:cookies){ if(cookie.getName().equals("loginCookie")){
		 * 
		 * } }
		 */
		// ?��?��링에?�� ?��?��추키 찾기
		Cookie cookie = WebUtils.getCookie(arg0, "loginCookie");
		HttpSession session =arg0.getSession();			
	
			//로그?�� 값에 session autologin 값이 1?���? 쿠키값이 ?��?�� ?��?��?��    로그?��?��?��만드?���?
		if (session.getAttribute("autologin") == null&&cookie != null) {
			UserVO vo = userDAO.checkUser(cookie.getValue());	
			// 로그?�� ?��켜줬?���?
			session.setAttribute("LOGIN", vo);
			///?��?��로그?��?�� ?��?��?��?���? autologin?�� 값을???��
			session.setAttribute("autologin", 1);
		}
		return true;
	}
}
