package com.naver.dbtjd122.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, 
			HttpServletResponse arg1,
			Object arg2, 
			Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0,
			HttpServletResponse arg1, 
			Object arg2, 
			ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}
	
	//?���??�� 처리?���? ?��?�� ?��출되?�� 메서?��
	//메서?��?��?�� true�? 리턴?���? Controller?�� ?���??�� 처리?��?�� 메서?���? ?��?��
	//메서?��?��?�� false�? 리턴?���? Controller?�� ?���??�� 처리?��?�� 메서?���? ?��?��?���?
	//?��?��?��?��.
	@Override
	public boolean preHandle(HttpServletRequest arg0, 
			HttpServletResponse arg1, 
			Object arg2) throws Exception {
		//로그?�� ?���?�? ?��?��?���? ?��?��?�� ?��?��?�� �??��?���?
		HttpSession session = arg0.getSession();
		//로그?��?�� ?��?�� 경우
		if(session.getAttribute("LOGIN") == null){
			//?���??��?���??�� 주소�? session?�� ???��
			//?���? 주소�? �??��?���? 
			String uri =arg0.getRequestURI();
			//?��?��미터 �??��?���? 
			String query=arg0.getQueryString();
			//?��?��미터�??��?��?��
			if(query != null&& !query.equals("null")){
				uri=uri+"?"+query;
				
		}
			//?��?��?��???��
			arg0.getSession().setAttribute("dest", uri);
			
			//로그?�� ?��?���?�? 리이?��?��
			arg1.sendRedirect("/user/login");
			return false;
		}
		//로그?��?�� ?�� 경우
		return true;
		
	}

}






