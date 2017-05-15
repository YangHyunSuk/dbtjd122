
package com.naver.dbtjd122.service;

import java.io.File;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.dbtjd122.dao.UserDAO;
import com.naver.dbtjd122.domain.LoginDTO;
import com.naver.dbtjd122.domain.UserDTO;
import com.naver.dbtjd122.domain.UserVO;
@Service
public class UserServiceImpl implements UserService {
@Autowired
private  UserDAO userDAO; 



	
	@Override
	public UserVO login(LoginDTO loginDTO) {
		
		return userDAO.login(loginDTO);
	}



	@Override
	public void updateSession(String id, String sessionKey, Date sessionLimit) {
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("id", id);
		map.put("sessionkey", sessionKey);
		map.put("sessionLimit", sessionLimit);
		
		userDAO.updateSession(map);
	}



	@Override
	public UserVO userCheck(String sessionKey) {
		return userDAO.checkUser(sessionKey);
	}



	@Override
	public Map<String, Object> idCheck(String id) {
		UserVO vo =userDAO.idcheck(id);
		Map<String,Object>map=new HashMap<String,Object>();
		//?��?��?���? 존재?��?��경우?�� result?�� false �? 존재?���? ?��?��경우?��?�� true�? ???�� 
		if(vo==null){
			map.put("result", "true");
		}else{			
			map.put("result", "false");
		}
		return map;
		
		}



	@Override
	public void register(UserDTO dto, HttpServletRequest request) {
		//DAO?�� 매개�??��
		UserVO vo =new UserVO();
		//?��?��?�� ?��로드 ?�� 경로 ?��?��
		String uploadPath =request.getServletContext().getRealPath("/userimage");
		//?��?��무일?�� 문자?��?��?��
		UUID uid=UUID.randomUUID();
		//?��로드?��?��?�� ?��?�� ?��름과 ?��?��무이?�� 문자?��?�� ?��쳐서 ?��?��?���? 만들�?
		String filename =uid +"_"+dto.getImage().getOriginalFilename();
		//?��?��?�� ?��로드?�� 경로만들�?
		String filepath=uploadPath+"\\"+filename;
		
	
	try{
		File f=new File(filepath);
			dto.getImage().transferTo(f);
			vo.setId(dto.getId());
			vo.setPw(dto.getPw());
			vo.setName(dto.getName());
			vo.setImage(filename);
				
			userDAO.insert(vo);
			
	}catch(Exception e){
		System.out.println(e.getMessage());
	}
	}
}
