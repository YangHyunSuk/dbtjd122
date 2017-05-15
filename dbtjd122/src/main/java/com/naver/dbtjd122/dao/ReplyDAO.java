package com.naver.dbtjd122.dao;

import java.util.List;

import com.naver.dbtjd122.domain.ReplyVO;

public interface ReplyDAO {
    //�?번호?�� ?��?��?��?�� 모든 ?���? �??��?���?
	public List<ReplyVO>list (int bno);
	//�?번호?�� ?��?��?��?�� ?���??�� 개수 �??��?���?
	public int count(int bno);
	//?���??�� ?��?��
	public void insert(ReplyVO vo);
	
	//?���??�� ?��?��
	public void update(ReplyVO vo);
	
	//?���??�� ?��?��
	public void delete(int rno);
}
