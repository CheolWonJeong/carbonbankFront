package com.ilmare.carbonbank.model;



/*
 * 회원정보
 */
public class CrbnQrCommonModel {
	private String dgtQrCd;        //디지털 QR코드
	private byte[] dgtQrImg;        //디지털 QR이지
	private String qrStat;         //QR 상태: N 생성, P:출력, S:판매, U:사용등록(매핑)
	private String crbnAdmId;	//사용등록 담당자
	private String partyCd;        //소속코드
	private String qrPrintYn;		//종이 QR 상태
	private String qrPrintDtm;	//출력일시
	private String qrPrintId;		//출력처리 담당자
	private String qrSaleDtm;		//판매일시
	private String qrSaleId;			//판매 담당자
	private String qrUseAvailDtm;	//사용등록 일시
	private String creDtm;         //생성일시
	private String chgDtm;         //변경일시
	
    public void setDgtQrImg(byte[] dgtQrImg) {
        this.dgtQrImg = dgtQrImg;
    }

    public byte[] getDgtQrImg() {
        return dgtQrImg;
    }

    public void setCrbnAdmId(String crbnAdmId) {
        this.crbnAdmId = crbnAdmId;
    }

    public String getCrbnAdmId() {
        return crbnAdmId;
    }

    public void setQrPrintYn(String qrPrintYn) {
        this.qrPrintYn = qrPrintYn;
    }

    public String getQrPrintYn() {
        return qrPrintYn;
    }

    public void setQrPrintDtm(String qrPrintDtm) {
        this.qrPrintDtm = qrPrintDtm;
    }

    public String getQrPrintDtm() {
        return qrPrintDtm;
    }

    public void setQrPrintId(String qrPrintId) {
        this.qrPrintId = qrPrintId;
    }

    public String getQrPrintId() {
        return qrPrintId;
    }

    public void setQrSaleDtm(String qrSaleDtm) {
        this.qrSaleDtm = qrSaleDtm;
    }

    public String getQrSaleDtm() {
        return qrSaleDtm;
    }

    public void setQrSaleId(String qrSaleId) {
        this.qrSaleId = qrSaleId;
    }

    public String getQrSaleId() {
        return qrSaleId;
    }

    public void setQrUseAvailDtm(String qrUseAvailDtm) {
        this.qrUseAvailDtm = qrUseAvailDtm;
    }

    public String getQrUseAvailDtm() {
        return qrUseAvailDtm;
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
