package com.ilmare.carbonbank.model;



/*
 * 회원정보
 */
public class CrbnMbrInfoModel {
	private String mbrId;          //회원아이디
	private String mbrPwd;         //비밀번호
	private String mbrCellNum;     //휴대폰번호
	private String mbrNm;          //이름
	private String partyCd;        //소속코드
	private String qrStat;         //QR 상태: N 생성, P:출력, S:판매, U:사용등록(매핑)
	private String dgtQrCd;        //디지털 QR코드
	private String pprQrCd;        //종이 QR코드
	private String storeId;        //사용등록 등록 가맹점
	private String chgPwdDtm;      //패스워드 변경일
	private String lstLgnDtm;      //마지막 로그인 일시
	private String creDtm;         //생성일시
	private String chgDtm;         //변경일시

	private String lgnIp;         //로그인 IP
	private String lgnOs;         //로그인 OS
	private String lgnBrowser;    //로그인 브라우저

	public void setLgnIp(String lgnIp) {
        this.lgnIp = lgnIp;
    }

    public String getLgnIp() {
        return lgnIp;
    }

	public void setLgnOs(String lgnOs) {
        this.lgnOs = lgnOs;
    }

    public String getLgnOs() {
        return lgnOs;
    }

	public void setLgnBrowser(String lgnBrowser) {
        this.lgnBrowser = lgnBrowser;
    }

    public String getLgnBrowser() {
        return lgnBrowser;
    }

	public void setMbrId(String mbrId) {
        this.mbrId = mbrId;
    }

    public String getMbrId() {
        return mbrId;
    }

    public void setMbrPwd(String mbrPwd) {
        this.mbrPwd = mbrPwd;
    }

    public String getMbrPwd() {
        return mbrPwd;
    }

    public void setMbrCellNum(String mbrCellNum) {
        this.mbrCellNum = mbrCellNum;
    }

    public String getMbrCellNum() {
        return mbrCellNum;
    }

    public void setMbrNm(String mbrNm) {
        this.mbrNm = mbrNm;
    }

    public String getMbrNm() {
        return mbrNm;
    }

    public void setPartyCd(String partyCd) {
        this.partyCd = partyCd;
    }

    public String getPartyCd() {
        return partyCd;
    }

    public void setQrStat(String qrStat) {
        this.qrStat = qrStat;
    }

    public String getQrStat() {
        return qrStat;
    }

    public void setDgtQrCd(String dgtQrCd) {
        this.dgtQrCd = dgtQrCd;
    }

    public String getDgtQrCd() {
        return dgtQrCd;
    }

    public void setPprQrCd(String pprQrCd) {
        this.pprQrCd = pprQrCd;
    }

    public String getPprQrCd() {
        return pprQrCd;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreId() {
        return storeId;
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
