package com.naver.dbtjd122;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naver.dbtjd122.domain.LoginDTO;
import com.naver.dbtjd122.domain.UserDTO;
import com.naver.dbtjd122.domain.UserVO;
import com.naver.dbtjd122.service.UserService;

@Controller
//모든?���?처리메서?��?�� 주소?��?�� url ?��?�� /user 붙임 
@RequestMapping("/user")
public class UserController {
@Autowired
private UserService userService;


//user /login ?���??�� ?��?��?�� 처리user.jsp�? ?��?�� 
//?���?�? ?��?��?�� ?��름의 jsp ?��?���? 갈떄?�� 리턴?�� ?��?��?���??��?��

@RequestMapping("/login")
public void  login(){}

@RequestMapping("loginpost")
//?��?��?��?��를사?��?��?���??��?�� 로그?�� 처리
public void login(
		LoginDTO loginDTO,
		HttpSession session,
		Model model){
		UserVO vo =userService.login(loginDTO);
		if(loginDTO.isUseCookie()){
		String id=loginDTO.getId();
		String sessionKey=session.getId();
//?��주일?��
	Date sessionLimit=new Date(System.currentTimeMillis()+1000+60*60*24*30);
	userService.updateSession(id, sessionKey, sessionLimit);
	
System.out.println(loginDTO.isUseCookie());

//로그?�� ?���?�? vo?�� ???��
model.addAttribute("userVO", vo);
}

}

@RequestMapping("/logout")
public String logout(HttpSession session){
	session.removeAttribute("LOGIN");
	return "redirect:/";
	
}

@RequestMapping(value="/register", method=RequestMethod.GET)
public String register(){
		
		return "/user/register";
	  }
@RequestMapping(value="/register",method=RequestMethod.POST)
public String register(UserDTO dto,HttpServletRequest request,RedirectAttributes attr){
	userService.register(dto, request);
	attr.addFlashAttribute("msg","?��?���??��?���?");
	return "redirect:/";
}

}
