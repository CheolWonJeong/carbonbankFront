package com.ilmare.carbonbank.model;



/*
 * QNA
 */
public class CrbnQnaModel {

	private long docSeq;		//문서순번	
	private String partyCd;		//소속코드	
	private String mbrId;          //회원아이디
	private String qnaPwd;	      //
	private String qnaSort;	      //
	private String qnaTitle;	      //
	private String qnaContent;	      //
	private String qnaInd;	      //
	private int docRead;		//읽은건수
	private String creDtm;		//생성일시	
	private String chgDtm;	      //
	private String auditId;		//등록자ID
	private String ansContent;	      //
	private String ansDtm;	      //
	
    public String getQnaPwd() {
        return qnaPwd;
    }
    public void setQnaPwd(String qnaPwd) {
        this.qnaPwd = qnaPwd;
    }

    public String getQnaSort() {
        return qnaSort;
    }
    public void setQnaSort(String qnaSort) {
        this.qnaSort = qnaSort;
    }

    public String getQnaTitle() {
        return qnaTitle;
    }
    public void setQnaTitle(String qnaTitle) {
        this.qnaTitle = qnaTitle;
    }

    public String getQnaContent() {
        return qnaContent;
    }
    public void setQnaContent(String qnaContent) {
        this.qnaContent = qnaContent;
    }

    public String getQnaInd() {
        return qnaInd;
    }
    public void setQnaInd(String qnaInd) {
        this.qnaInd = qnaInd;
    }

    public String getCreDtm() {
        return creDtm;
    }
    public void setCreDtm(String creDtm) {
        this.creDtm = creDtm;
    }

    public String getChgDtm() {
        return chgDtm;
    }
    public void setChgDtm(String chgDtm) {
        this.chgDtm = chgDtm;
    }

    public String getAuditId() {
        return auditId;
    }
    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }

    public String getAnsContent() {
        return ansContent;
    }
    public void setAnsContent(String ansContent) {
        this.ansContent = ansContent;
    }

    public String getAnsDtm() {
        return ansDtm;
    }
    public void setAnsDtm(String ansDtm) {
        this.ansDtm = ansDtm;
    }

    public long getDocSeq() {
        return docSeq;
    }
    public void setDocSeq(long docSeq) {
        this.docSeq = docSeq;
    }
    public String getPartyCd() {
        return partyCd;
    }
    public void setPartyCd(String partyCd) {
        this.partyCd = partyCd;
    }

    public int getDocRead() {
        return docRead;
    }
    public void setDocRead(int docRead) {
        this.docRead = docRead;
    }

    public void setMbrId(String mbrId) {
        this.mbrId = mbrId;
    }

    public String getMbrId() {
        return mbrId;
    }

    
}
