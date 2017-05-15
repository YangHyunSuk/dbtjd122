package com.naver.dbtjd122.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {
	// 컨트롤러?�� ?���??�� 처리?���? ?��?��?�� ?��출되?��메서?��
	// ?��?���? ?��?�� ?��?�� 무조�??��?��?��
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	// Controller?�� ?���??�� 처리?��고난 ?�� ?��출되?�� 메서?��
	// ?��?���? 발생?���? ?��?? 경우?��만호�?
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// 로그?�� ?��?��?�� 리턴?��?�� ?��?��?���? �??��?��것이?�� .
		ModelMap modelMap = arg3.getModelMap();
		Object userVO = modelMap.get("userVO");
		HttpSession session = arg0.getSession();
		if (userVO == null) {
			// ?��?��?��
			session.setAttribute("fail", "?��?��?���? ?��거나 비�?번호 ???��?��");
			arg1.sendRedirect("/user/login");
		} else {
			session.removeAttribute("fail");
			session.setAttribute("LOGIN", userVO);
			System.out.println(session);

			// ?��?�� 로그?�� 체크?���?�? ?��?��?��?�� 쿠키�? 브라?��???�� ???��
			if (arg0.getParameter("useCookie") != null) {
				// 쿠키?��?��?��?��?��?��?��?��?��?��

				Cookie loginCookie = new Cookie("loginCookie", session.getId());
				loginCookie.setPath("/");

				// postHandle ?��?��?�� ?��?��?���?
				// ?���? 쿠키?�� ?��?��?���?
				loginCookie.setMaxAge(60 * 60 * 24 * 7);
				arg1.addCookie(loginCookie);
			} else {
				Cookie loginCookie = new Cookie("loginCookie", session.getId());
				// 쿠키?��?��?��?��?��?��?��?��?��?��
				loginCookie.setPath("/");
				// 쿠키?�� ?��?��?��간�? ?��?�� =쿠키?��?��
				loginCookie.setMaxAge(0);
				// 쿠키 ???��
				arg1.addCookie(loginCookie);
			}
			// 로그?��?�� ?��?��?�� ?���??�� ?��?��?�� ?��건�? ?��?�� ?��기위?�� session?�� ???��?�� dest 값을�??��?���?
			Object dest = arg0.getSession().getAttribute("dest");
			if (dest == null)
				// ?��?��?��?���?로�??��처리
				arg1.sendRedirect("/");

			else
				arg1.sendRedirect((String) dest);

		}
	}
	// Controller ?�� ?���??�� 처리?���? ?��?�� ?��출되?��메서?��

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// ?��?��?�� 로그?��?��보�? ?��?��?���? ?��?��
		HttpSession session = arg0.getSession();
		if (session.getAttribute("LOGIN") != null) {
			session.removeAttribute("LOGIN");
		}
		// true �? 리턴?���? Controller 로처리하?�� ?��?��?���?
		// false�? 리턴?���? Controller�? �?�??��?��?��
		return true;
	}

}
