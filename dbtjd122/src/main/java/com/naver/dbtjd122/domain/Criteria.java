package com.naver.dbtjd122.domain;



//?��?�� ?��?���? 번호?? ?��?���? ?�� 출력개수�? ???��?��?��?��?�� 
public class Criteria {
	private int page;
	private int perPageNum;
	//?��?��?��?�� ?��?��?��?�� �??��?�� 기본값을 ?��?��?��?���?
	public Criteria() {
		super();
	page= 1;
		
	perPageNum=10;
	}
	//?��?�� ?��?���? 번호?? ?��?���? ?�� ?��?��?�� 개수�? �?�?�? ?��?��?�� ?��?��번호 찾아?��?�� 메서?��
	public int getPageStart(){
		int start =(page -1)* perPageNum +1;
		return start;
		
	}
	//?��근자 메서?�� 
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
}
