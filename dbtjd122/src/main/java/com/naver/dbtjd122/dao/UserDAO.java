package com.naver.dbtjd122.dao;

import java.util.Map;

import com.naver.dbtjd122.domain.LoginDTO;
import com.naver.dbtjd122.domain.UserVO;

public interface UserDAO {
//로그?�� 처리?��?�� 메서?��
public UserVO login(LoginDTO loginDTO);
//쿠키?�� ?��?��?��간을 ?��?��?��?��?��?�� 메서?�� 
//매개�??��?�� ?��???�� ?��?��?��, ?��?�� �? ?��?��?���? 3�?�??�� 
public void updateSession(Map<String,Object>map);
//쿠키?�� 값을 �?�?�? id ?? pw 찾아?��?��메서?�� 
public UserVO checkUser(String sessionKey);
	
//?��?�� �??�� 처리메서?��
public void insert(UserVO vo);

//?��?��?�� 중복체크?�� 메서?��
public UserVO idcheck(String id);


}
