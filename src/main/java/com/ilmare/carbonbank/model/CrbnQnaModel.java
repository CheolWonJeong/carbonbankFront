package com.ilmare.carbonbank.model;



/*
 * QNA
 */
public class CrbnQnaModel {

	private long docSeq;		//문서순번	
	private String partyCd;		//소속코드	
	private String mbrId;          //회원아이디
	private String qnaSort;	      //
	private String docTitle;	      //
	private String docInfo;	      //
	private String docInd;	      //
	private String docStat;	      //
	private int docRead;		//읽은건수
	private String creDtm;		//생성일시	
	private String auditId;		//등록자ID
	private String ansContent;	      //
	private String ansDtm;	      //
	
	private String searchType;	      //
	private String searchValue;	      //
	private int pageNo;		//조회시작 위치 
	private int listSize  ;		//조회 건수  
	
    public String getSearchType() {
        return searchType;
    }
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchValue() {
        return searchValue;
    }
    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public int getPageNo() {
        return pageNo;
    }
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
    public int getListSize() {
        return listSize;
    }
    public void setListSize(int listSize) {
        this.listSize = listSize;
    }

    public long getDocSeq() {
        return docSeq;
    }
    public void setDocSeq(long docSeq) {
        this.docSeq = docSeq;
    }

    public String getDocStat() {
        return docStat;
    }
    public void setDocStat(String docStat) {
        this.docStat = docStat;
    }

    public String getQnaSort() {
        return qnaSort;
    }
    public void setQnaSort(String qnaSort) {
        this.qnaSort = qnaSort;
    }

    public String getDocTitle() {
        return docTitle;
    }
    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public String getDocInfo() {
        return docInfo;
    }
    public void setDocInfo(String docInfo) {
        this.docInfo = docInfo;
    }

    public String getDocInd() {
        return docInd;
    }
    public void setDocInd(String docInd) {
        this.docInd = docInd;
    }

    public String getCreDtm() {
        return creDtm;
    }
    public void setCreDtm(String creDtm) {
        this.creDtm = creDtm;
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
