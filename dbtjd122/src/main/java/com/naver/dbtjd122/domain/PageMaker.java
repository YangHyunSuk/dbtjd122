package com.naver.dbtjd122.domain;


//?��?���? 처리�? ?��?�� ?��?��?�� ?��?��?���? ???��?��?��?��?��?�� 
public class PageMaker {
//?��?��?��?���?번호?? ?��?���??�� ?��?��?�� 출력개수
private Criteria criteria;
//?��?���??�� ?��?���?번호출력개수
private int displayPageNum = 10;
//?��?��?��?��체개?��
private int totalCount;
//?��?��?��?�� ?��?���? 번호?? ?��?��?�� 번호?��?���?

private int startPage, endPage;
//?��?���? ?��?��링크출력?���?
private boolean prev,next;



public Criteria getCriteria() {
	return criteria;
}
public void setCriteria(Criteria criteria) {
	this.criteria = criteria;
}
public int getDisplayPageNum() {
	return displayPageNum;
}
public void setDisplayPageNum(int displayPageNum) {
	this.displayPageNum = displayPageNum;
}
public int getTotalCount() {
	return totalCount;
}
public void setTotalCount(int totalCount) {
	this.totalCount = totalCount;
//totalcount ?�� 값이 ?��?��?���?
	//?��머�? ?��?��?���? 계산?��?�� 메서?���? ?���? 
calcData();
}
public void calcData(){
	//?��?��?��?���? 번호 			criteria.page
	//?��?���??�� ?��?��?�� 출력개수criteria.perPageNum
	//?��체데?��?�� 개수 		totalCount
	//?��?���? 번호출력개수 displayPagenNum

	int imsi =criteria.getPage()/displayPageNum;
	if(criteria.getPage()%displayPageNum==0){
		imsi=imsi-1;
	}
startPage=imsi*displayPageNum+1;
endPage=startPage+displayPageNum-1;
//?���? ?��?���? 개수 보다 endpage�? ?��?��면안?��?��
//?���? ?��?���? 개수구하기ㅏ
int pageCount =totalCount/criteria.getPerPageNum();
if(totalCount%criteria.getPerPageNum()!=0){
	pageCount=pageCount+1;
}
if(endPage>pageCount){
	endPage=pageCount;
}
//startPage�? 1?���? false 그렇�? ?��?���? true
prev=startPage==1?false:true;
//endPage�? ?���? ?��?���? 개수?? 같으�? false 그렇�? ?��?���? true
next=endPage==pageCount?false:true;

}

public int getStartPage() {
	return startPage;
}
public void setStartPage(int startPage) {
	this.startPage = startPage;
}
public int getEndPage() {
	return endPage;
}
public void setEndPage(int endPage) {
	this.endPage = endPage;
}
public boolean isPrev() {
	return prev;
}
public void setPrev(boolean prev) {
	this.prev = prev;
}
public boolean isNext() {
	return next;
}
public void setNext(boolean next) {
	this.next = next;
}
@Override
public String toString() {
	return "PageMaker [criteria=" + criteria + ", displayPageNum=" + displayPageNum + ", totalCount=" + totalCount
			+ ", startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next + "]";
}



}
