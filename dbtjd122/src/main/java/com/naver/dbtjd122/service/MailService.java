package com.naver.dbtjd122.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MailService {
	//메일 보내기위?�� 리�?�스?�� �? 리스?��?�� 
public void sendMail(HttpServletRequest request,HttpServletResponse response);
//?��록시
public void download(HttpServletRequest request, HttpServletResponse response);
//?��2 ?��?��메세�? 보내�?
public void home2mail(HttpServletRequest request, HttpServletResponse response);
}
