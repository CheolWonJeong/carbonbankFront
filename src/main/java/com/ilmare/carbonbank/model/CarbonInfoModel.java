package com.ilmare.carbonbank.model;



/*
 * 회원정보
 */
public class CarbonInfoModel {
	private String mbrId;          //회원아이디
	private String storeId;        //가맹점

	private int mbrUseTotal;    //회원 실천 전체점수
	private int mbrRanking;     //회원 랭킹 순위
	
	
	private int storeMemTotal;        //가맹점 회원수
	
	//조회조건
	private String qryYmd;          //연월
	private String qryInd;        //조회구분(D:일별, M:월별)
	
	//조회 리스트
	private String ymd;         //일자
	private int qrSaleCnt;        //매출건수:가맹점에서 등록된 건수 
	private int qrUseCnt;        //판매건수:가맹점에서 사용한 건수
	
    public void setMbrUseTotal(int mbrUseTotal) {
        this.mbrUseTotal = mbrUseTotal;
    }

    public int getMbrUseTotal() {
        return mbrUseTotal;
    }

    public void setMbrRanking(int mbrRanking) {
        this.mbrRanking = mbrRanking;
    }

    public int getMbrRanking() {
        return mbrRanking;
    }

    public void setStoreMemTotal(int storeMemTotal) {
        this.storeMemTotal = storeMemTotal;
    }

    public int getStoreMemTotal() {
        return storeMemTotal;
    }

	public void setQryYmd(String qryYmd) {
        this.qryYmd = qryYmd;
    }

    public String getQryYmd() {
        return qryYmd;
    }

	public void setQryInd(String qryInd) {
        this.qryInd = qryInd;
    }

    public String getQryInd() {
        return qryInd;
    }

	public void setYmd(String ymd) {
        this.ymd = ymd;
    }

    public String getYmd() {
        return ymd;
    }

    public void setQrSaleCnt(int qrSaleCnt) {
        this.qrSaleCnt = qrSaleCnt;
    }

    public int getQrSaleCnt() {
        return qrSaleCnt;
    }

    public void setQrUseCnt(int qrUseCnt) {
        this.qrUseCnt = qrUseCnt;
    }

    public int getQrUseCnt() {
        return qrUseCnt;
    }

	public void setMbrId(String mbrId) {
        this.mbrId = mbrId;
    }

    public String getMbrId() {
        return mbrId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreId() {
        return storeId;
    }

}
