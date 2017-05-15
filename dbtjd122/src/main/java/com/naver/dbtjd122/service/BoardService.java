package com.naver.dbtjd122.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.naver.dbtjd122.domain.BoardVO;
import com.naver.dbtjd122.domain.Criteria;
import com.naver.dbtjd122.domain.SearchCriteria;

public interface BoardService {
	
public void insert(HttpServletRequest request);
/*
 ?��체목록보기�?? ?��?��메서?��
public List<BoardVO>getAll();
*/
//?��?���? 처리�? ?��?��메서?��
//public int totalCount();
//public List<BoardVO>getList(Criteria criteria);

//�??�� 처리�? ?��?�� 메서?��
public int totalCount(
		SearchCriteria criteria);
public List<BoardVO>getList(
		SearchCriteria criteria);

//?��?��보기
public BoardVO getDetail(int bno);
//�??��?��
public void delete(int bno);
//ip?��문에 request �? 받아?��?��?�� 매개 �??���? 
public void update(HttpServletRequest request);
}
