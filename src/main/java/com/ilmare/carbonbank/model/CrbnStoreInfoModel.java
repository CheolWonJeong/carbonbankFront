package com.ilmare.carbonbank.model;



/*
 * 가맹점정보
 */
public class CrbnStoreInfoModel {

	private String storeId;        //가맹점아이디
	private String partyCd;        //소속코드
	private String bisNum;        //사업자 등록번호
	private String storePwd;        //비밀번호
	private String storeCellNum;        //휴대폰번호
	private String storeCeoNm;        //대표자 성명
	private String storeNm;        //상호
	private String storeNoImg;        //사업자등록증 이미지 파일 경로
	private String storeImg;        //사업장 사진 이미지 파일 경로
	private String storeEmail;        //메일주소
	private String storeAddr;        //사업장 주소
	private String storeEvent;        //할인행사
	private String storeCacaoId;        //카카오 등록 아이디
	private String storeLatitude;        //위도
	private String storeLongitude;        //경도
	private String chgPwdDtm;      //패스워드 변경일
	private String lstLgnDtm;      //마지막 로그인 일시
	private String creDtm;         //생성일시
	private String chgDtm;         //변경일시

	private String storeUuid;		// 가맹점 UUID(kakaoMap 제공)
	private String storeX;			// 가맹점 x 좌표 
	private String storeY;			// 가맹점 y 좌표 	
	
	public CrbnStoreInfoModel(String storeUuid, String storeNm, String storeX, String storeY) {
		this.storeUuid = storeUuid;
		this.storeNm = storeNm;
		this.storeX = storeX;
		this.storeY = storeY;
	}	
    public void setStoreUuid(String storeUuid) {
        this.storeUuid = storeUuid;
    }

    public String getStoreUuid() {
        return storeUuid;
    }

    public void setStoreX(String storeX) {
        this.storeY = storeX;
    }

    public String getStoreX() {
        return storeX;
    }

    public void setStoreY(String storeY) {
        this.storeY = storeY;
    }

    public String getStoreY() {
        return storeY;
    }

    public void setStoreCacaoId(String storeCacaoId) {
        this.storeCacaoId = storeCacaoId;
    }

    public String getStoreCacaoId() {
        return storeCacaoId;
    }

    public void setStoreLatitude(String storeLatitude) {
        this.storeLatitude = storeLatitude;
    }

    public String getStoreLatitude() {
        return storeLatitude;
    }

    public void setStoreLongitude(String storeLongitude) {
        this.storeLongitude = storeLongitude;
    }

    public String getStoreLongitude() {
        return storeLongitude;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setBisNum(String bisNum) {
        this.bisNum = bisNum;
    }

    public String getBisNum() {
        return bisNum;
    }

    public void setStorePwd(String storePwd) {
        this.storePwd = storePwd;
    }

    public String getStorePwd() {
        return storePwd;
    }

    public void setStoreCellNum(String storeCellNum) {
        this.storeCellNum = storeCellNum;
    }

    public String getStoreCellNum() {
        return storeCellNum;
    }

    public void setStoreCeoNm(String storeCeoNm) {
        this.storeCeoNm = storeCeoNm;
    }

    public String getStoreCeoNm() {
        return storeCeoNm;
    }

    public void setStoreNm(String storeNm) {
        this.storeNm = storeNm;
    }

    public String getStoreNm() {
        return storeNm;
    }

    public void setStoreNoImg(String storeNoImg) {
        this.storeNoImg = storeNoImg;
    }

    public String getStoreNoImg() {
        return storeNoImg;
    }

    public void setStoreImg(String storeImg) {
        this.storeImg = storeImg;
    }

    public String getStoreImg() {
        return storeImg;
    }

    public void setStoreEmail(String storeEmail) {
        this.storeEmail = storeEmail;
    }

    public String getStoreEmail() {
        return storeEmail;
    }

    public void setStoreAddr(String storeAddr) {
        this.storeAddr = storeAddr;
    }

    public String getStoreAddr() {
        return storeAddr;
    }

    public void setStoreEvent(String storeEvent) {
        this.storeEvent = storeEvent;
    }

    public String getStoreEvent() {
        return storeEvent;
    }

    public void setPartyCd(String partyCd) {
        this.partyCd = partyCd;
    }

    public String getPartyCd() {
        return partyCd;
    }

    public void setLChgPwdDtm(String chgPwdDtm) {
        this.chgPwdDtm = chgPwdDtm;
    }

    public String getChgPwdDtm() {
        return chgPwdDtm;
    }

    public void setLLstLgnDtm(String lstLgnDtm) {
        this.lstLgnDtm = lstLgnDtm;
    }

    public String getLstLgnDtm() {
        return lstLgnDtm;
    }

    public void setLCreDtm(String creDtm) {
        this.creDtm = creDtm;
    }

    public String getCreDtm() {
        return creDtm;
    }

    public void setLChgDtm(String chgDtm) {
        this.chgDtm = chgDtm;
    }

    public String getChgDtm() {
        return chgDtm;
    }

}
