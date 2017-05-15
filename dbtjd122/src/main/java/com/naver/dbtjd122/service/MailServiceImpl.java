package com.naver.dbtjd122.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.dbtjd122.dao.MailDAO;
import com.naver.dbtjd122.domain.BoardVO;
import com.naver.dbtjd122.domain.MailVO;
@Service
public class MailServiceImpl implements MailService {
@Autowired
public MailDAO maiDAO;
	@Override
	public void sendMail(HttpServletRequest request, HttpServletResponse response) {

		SimpleEmail simpleEmail=new SimpleEmail();
		//Smtp ?��버연�?
		simpleEmail.setHostName("smtp.naver.com");
		simpleEmail.setSmtpPort(465);
		simpleEmail.setAuthentication("dbtjd122","$Youkin122");
		try {
			simpleEmail.setSSLOnConnect(true);
			simpleEmail.setStartTLSEnabled(true);
			simpleEmail.setCharset("utf-8");
		//받는?��?��?��?��		
		simpleEmail.addTo("dbtjd122@naver.com","?��?��?��","utf-8");
		//보내?��?��?��?��?��
		simpleEmail.setFrom("dbtjd122@naver.com","보낸?��?��","utf-8");
		//?��목설?��
		simpleEmail.setSubject("메일보내�?");
		//?��?��
		simpleEmail.setMsg("?���?");
		//보내�?
		simpleEmail.send();
		request.getSession().setAttribute("message","보내기성�?");
		}
		catch (Exception e) {
			request.setAttribute("message", "메일 보내기에 ?��?��?��?��?��?��?��.");
			e.printStackTrace();
		
		}
	}
@Override
public void download(HttpServletRequest request, HttpServletResponse response) {
		StringBuilder sb = new StringBuilder();
		try {
		URL url = new URL(request.getParameter("addr"));
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				if (conn != null) {
				//?��결시간제?�� 
					conn.setConnectTimeout(50);
					conn.setUseCaches(false);
					if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
						InputStreamReader isr = new InputStreamReader(conn.getInputStream());
						BufferedReader br = new BufferedReader(isr);
						while (true) {
							String line = br.readLine();
							//System.out.println(line);
						//line ?��  addr �? ?��?��주고  값이 ?��?��?�� break 	
							if (line == null) {
								break;
							}
							sb.append(line + "\n");
							System.out.println(line);
						}
						br.close();
						conn.disconnect();
					}
				}
			} catch (Exception e) {
				System.out.println("�??��?���? ?��?��:" + e.getMessage());
			}
			request.getSession().setAttribute("pasing", sb.toString());			
	 }
@Override
public void home2mail(HttpServletRequest request, HttpServletResponse response) {
	
	String from=request.getParameter("from");
	String tubject =request.getParameter("subject");
	String gmsg= request.getParameter("msg");
	
	
	
	SimpleEmail simpleEmail=new SimpleEmail();
	//Smtp ?��버연�?
	simpleEmail.setHostName("smtp.naver.com");
	simpleEmail.setSmtpPort(465);
	simpleEmail.setAuthentication("dbtjd122","$Youkin122");
	
	try {
		simpleEmail.setSSLOnConnect(true);
		simpleEmail.setStartTLSEnabled(true);
		simpleEmail.setCharset("utf-8");
	//받는?��?��?��?��		
	simpleEmail.addTo("dbtjd122@naver.com","?��?��?��","utf-8");
	//보내?��?��?��?��?��
	simpleEmail.setFrom(from);
	//?��목설?��
	simpleEmail.setSubject(tubject);
	//?��?��
	simpleEmail.setMsg(gmsg);
	//보내�?
	simpleEmail.send();
	request.getSession().setAttribute("message","보내기성�?");
	}
	catch (Exception e) {
		request.setAttribute("message", "메일 보내기에 ?��?��?��?��?��?��?��.");
		e.printStackTrace();
	
	}
}
	
}	
	





