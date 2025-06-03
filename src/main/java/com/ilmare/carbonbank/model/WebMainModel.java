package com.ilmare.carbonbank.model;



/*
 * 뉴스 공통
 */
public class WebMainModel {
	private String partyCd;		//소속코드	
	private int memberCnt;		//탄소중립 회원
	private int storeCnt  ;		//탄소중립 가맹점
	private int useCnt   ;		//절감된 일회용 컵  
	private int carbonReduction   ;		//탄소저감량  

    public String getPartyCd() {
        return partyCd;
    }
    public void setPartyCd(String partyCd) {
        this.partyCd = partyCd;
    }
    public int getMemberCnt() {
        return memberCnt;
    }
    public void setMemberCnt(int memberCnt) {
        this.memberCnt = memberCnt;
    }
	
    public int getStoreCnt() {
        return storeCnt;
    }
    public void setStoreCnt(int storeCnt) {
        this.storeCnt = storeCnt;
    }
	
    public int getUseCnt() {
        return useCnt;
    }
    public void setUseCnt(int useCnt) {
        this.useCnt = useCnt;
    }

    public int getCarbonReduction() {
        return carbonReduction;
    }
    public void setCarbonReduction(int carbonReduction) {
        this.carbonReduction = carbonReduction;
    }
	
    
}
