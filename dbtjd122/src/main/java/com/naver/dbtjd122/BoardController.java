package com.naver.dbtjd122;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naver.dbtjd122.dao.UserDAO;
import com.naver.dbtjd122.domain.BoardVO;

import com.naver.dbtjd122.domain.PageMaker;

import com.naver.dbtjd122.domain.SearchCriteria;
import com.naver.dbtjd122.domain.UserVO;
import com.naver.dbtjd122.service.BoardService;
import com.naver.dbtjd122.service.MailService;
import com.naver.dbtjd122.service.MailServiceImpl;


@Controller
public class BoardController {
@Autowired
private BoardService boardService;
//@Autowired
//private UserDAO userDAO;
	@RequestMapping("/")
public String home(){

		return "home";

}
@RequestMapping(value="/board/register", method=RequestMethod.GET)
public String register(){
		
		return "/board/register";
	  }

@RequestMapping(value="/board/register", method=RequestMethod.POST)
public String register(HttpServletRequest request,RedirectAttributes attr){
boardService.insert(request);
//�??���? ?��?��?��?��?�� 목록보기�? ?��?�� ?��?��?�� 베이?��?�� �??���? �??��?���?�?  redirect
attr.addFlashAttribute("msg", "�??��기성�?");		
return "redirect:list";
	  }

/*?��체목록보기일?��?��?���?
@RequestMapping("/board/list")
public String getAll(Model model){
//List<BoardVO>list =boardService.getAll();
//model.addAttribute("list",list);
return "/board/list";

}*/

@RequestMapping("/board/list")
public String getList(@ModelAttribute("criteria") SearchCriteria criteria, Model model){
List<BoardVO>list =boardService.getList(criteria);
model.addAttribute("list",list);
//?��?���? 처리�? ?��?�� ?��?��?�� �??��?���?
PageMaker pageMaker =new PageMaker();
//?��?�� ?��?���? 번호?? 출력?�� ?��?��?�� 개수�? pageMaker?�� ???�� 
pageMaker.setCriteria(criteria);
//pageMaker?�� totalCount?�� ?��?��?�� ?���? 개수�? ?��?��?��?��?�� 
//?��머�? 구성?��?��?�� ?��?��?���? 계산?��?�� 
pageMaker.setTotalCount(boardService.totalCount(criteria));
model.addAttribute("pageMaker", pageMaker);

return "/board/list";

}


//1개의 ?��?��미터�? URL?�� 붙여?��?��?��
//블로그처?�� 기사 ?��?��?�� 보여주는�? 목적?�� ?�� ?��?��리�??��?��?��?�� 많이 ?��?��?��?�� 주소?��?��
@RequestMapping("/board/detail")
public String getDetail(@RequestParam("bno")  int bno,
@ModelAttribute("criteria") SearchCriteria criteria, Model model){
BoardVO vo =boardService.getDetail(bno);
model.addAttribute("vo",vo);
return"/board/detail";

}
@RequestMapping("/board/delete/{bno}")
public String delete(@PathVariable int bno,RedirectAttributes attr){
	boardService.delete(bno);
	attr.addFlashAttribute("msg", "?��?��?�� ?��공했?��?��?��");
//목록보기�? redirect
	return "redirect:/board/list";
}
@RequestMapping("/board/update/{bno}")
public String update(@PathVariable int bno,Model model){
	BoardVO vo= boardService.getDetail(bno);
model.addAttribute("vo",vo);
	//목록보기�? redirect
	return "/board/update";
}

@RequestMapping("/board/updateboard")
public String updateBoard(HttpServletRequest request,RedirectAttributes attr){
	boardService.update(request);
	attr.addFlashAttribute("msg","?��?��?���?");
	return "redirect:/board/list";
}
@RequestMapping("/user/naverlogin")
public String test(HttpServletRequest request){
	return "/user/naverlogin";
}
@RequestMapping("/user/callback")
public String callback(HttpServletRequest request){
	return "/user/callback";
}
@RequestMapping("/user/jido")
public String jido(HttpServletRequest request){
	return "/user/jido";
}
@RequestMapping("/board/bookmark")
public String bookmark(){
	return"/board/bookmark";
}
@RequestMapping("/pasing/message")
public String doGet(HttpServletRequest request, HttpServletResponse response,RedirectAttributes attr)
throws ServletException,IOException{
MailService action = new MailServiceImpl();
action.sendMail(request, response);
attr.addFlashAttribute("msg","�?리자 ?��출완�?");

return "redirect:/";
	
}
@RequestMapping("/pasing/download")
public String download(HttpServletRequest request, HttpServletResponse response)
throws ServletException,IOException{
MailService pasingproxy = new MailServiceImpl();
pasingproxy.download(request, response);
	
return "/pasing/proxyse";

}
@RequestMapping("/pasing/proxy")
public String proxy(){
return "/pasing/proxy";
}

@RequestMapping("home2")
public String home2(){
return "home2";
}

@RequestMapping("/home2/mail")
public String home2mail(HttpServletRequest request, HttpServletResponse response,RedirectAttributes attr)
		throws ServletException,IOException{
	MailService action = new MailServiceImpl();
	action.home2mail(request, response);
	return "home2";
}

}