package com.naver.dbtjd122.service;

import java.sql.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.naver.dbtjd122.domain.LoginDTO;
import com.naver.dbtjd122.domain.UserDTO;
import com.naver.dbtjd122.domain.UserVO;

public interface UserService {
//로그?��?�� 처리?��주는메서?��
public UserVO login(LoginDTO loginDTO);

//로그?��?��?�� ?��?��로그?�� 체크?�� 경우?�� ?��?��?�� ?��?? ?��?��?��간을 ?��?��?��?�� ?��주는 메서?�� 
public void updateSession(String id,String sessionKey,Date sessionLimit);

//?��?��로그?��?�� 체크?��?��?��?��?�� 쿠키?�� ?��?��?�� ?��값을 ???��?��?��?��
//로그?��?�� ?��?��?���? ?��?�� 로그?��?��?��?��?���? ?��?��?��?? 비�?번호�? 찾아?��?�� 메서?�� 
public UserVO userCheck(String sessionKey);

//id 중복체크?��주는 메서?���? ?��?��?��?��?��
//결과?�� 맵으�? 리턴
public Map<String,Object>idCheck(String id);

//?��?���??��처리
//User?�� ?��?��?���? ?��?��?�� ?��?��?��,?�� 처리 HttpServletRequest?�� ?��?��?��로드처리
public void register(UserDTO dto,HttpServletRequest request);



}
