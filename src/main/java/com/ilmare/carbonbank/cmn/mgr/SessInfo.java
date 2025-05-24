package com.ilmare.carbonbank.cmn.mgr;

import lombok.Data;
import lombok.NoArgsConstructor;

public class SessInfo 
{
	private String mbrId;          //회원아이디
	private String mbrPwd;         //비밀번호
	private String mbrCellNum;     //휴대폰번호
	private String mbrNm;          //이름
	private String partyCd;        //소속코드
	private String dgtQrCd;        //디지털 QR코드
	private String pprQrCd;        //종이 QR코드
	private String lstLgnDtm;      //마지막 로그인 일시

	private String storeId;          //가맹점아이디

	public void seStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreId() {
        return storeId;
    }

	public void setLstLgnDtm(String lstLgnDtm) {
        this.lstLgnDtm = lstLgnDtm;
    }

    public String getLstLgnDtm() {
        return lstLgnDtm;
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

	
}
