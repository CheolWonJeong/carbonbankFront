package com.ilmare.carbonbank.model;



/*
 * 공지사항
 */
public class CrbnNoticeModel {

	private long docSeq;		//문서순번	
	private String partyCd;		//소속코드	
	private String docTitle;		//제목	
	private String docInfo;		//문서내용
	private int docRead;		//읽은건수
	private String docStat;		//문서상태(N:등록, V:게시, C:게시취소)	
	private String auditId;		//등록자ID
	private String creDtm;		//생성일시	
	private String showId;		//게시 아이디
	private String showDtm;		//게시일시
	private String cancelId;		//게시취소 아이디
	private String cancelDtm;		//게시취소일시
	private String delId;		//삭제 아이디
	private String delDtm;		//삭제일시

	private String prodDtm;		//처리일시
	private String docStatNm;	//문서상태(N:등록, V:게시, C:게시취소)	

	//조회
	private int pageNo  = 1;		//조회시작 위치 
	private int listSize  ;		//조회 건수  
	
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
    public String getPartyCd() {
        return partyCd;
    }
    public void setPartyCd(String partyCd) {
        this.partyCd = partyCd;
    }
    public String getDocTitle() {
        return docTitle;
    }
    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }
    public String getDocStat() {
        return docStat;
    }
    public void setDocStat(String docStat) {
        this.docStat = docStat;
    }
	
    public String getDocStatNm() {
        return docStatNm;
    }
    public void setDocStatNm(String docStatNm) {
        this.docStatNm = docStatNm;
    }
	
    public String getImgSrcNm() {
        return creDtm;
    }
    public void setㅊreDtm(String creDtm) {
        this.creDtm = creDtm;
    }
    public String getㅁuditId() {
        return auditId;
    }
    public void setㅁuditId(String auditId) {
        this.auditId = auditId;
    }

    public String getDocInfo() {
        return docInfo;
    }
    public void setDocInfo(String docInfo) {
        this.docInfo = docInfo;
    }
    public int getDocRead() {
        return docRead;
    }
    public void setDocRead(int docRead) {
        this.docRead = docRead;
    }
    public String getShowId() {
        return showId;
    }
    public void setShowId(String showId) {
        this.showId = showId;
    }
    public String getShowDtm() {
        return showDtm;
    }
    public void setShowDtm(String showDtm) {
        this.showDtm = showDtm;
    }
    public String getCancelId() {
        return cancelId;
    }
    public void setCancelId(String cancelId) {
        this.cancelId = cancelId;
    }
    public String getCancelDtm() {
        return cancelDtm;
    }
    public void setCancelDtm(String cancelDtm) {
        this.cancelDtm = cancelDtm;
    }
    public String getDelId() {
        return delId;
    }
    public void setDelId(String delId) {
        this.delId = delId;
    }
 
    public String getDelDtm() {
        return delDtm;
    }
    public void setDelDtm(String delDtm) {
        this.delDtm = delDtm;
    }
    
    public String getProdDtm() {
        return prodDtm;
    }
    public void setProdDtm(String prodDtm) {
        this.prodDtm = prodDtm;
    }
}
