package com.ilmare.carbonbank.cmn.controller;

import org.springframework.stereotype.Component;


@Component
public class ConfigConstants {

	public static final String  lgnUrl = "/home/cbLogin.do";

	//페이지간련
	public static final int pageSize = 20;    //페이지당 row 건수
	
	public static enum enDocStat{
		INS("N", "등록상태"),
		VIEW("Y", "게시상태"),
		CANCEL("C", "게시취소"),
		DEL("D", "삭제");

		private String code;
		private String name;

		enDocStat(final String _code, final String _name) {
			this.code = _code;
			this.name = _name;
		}

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}


	}

	public static enum partyCdUrl{
		carbonbank("carbonbank", "www.carbonbank.re.kr"),
		incheon("incheon", "incheon.carbonbank.re.kr"),
		local("incheon", "localhost");

		private String code;
		private String linkUrl;

		partyCdUrl(final String _code, final String linkUrl) {
			this.code = _code;
			this.linkUrl = linkUrl;
		}

		public String getCode() {
			return code;
		}

		public String getLinkUrl() {
			return linkUrl;
		}


	}

	public static enum mainUrl{
		carbonbank("carbonbank", "/mobile/main/main_carbon"),
		incheon("incheon", "/mobile/main/main_incheon"),
		member("member", "/mobile/main/main_member"),
		store("store", "/mobile/main/main_store");

		private String code;
		private String linkUrl;

		mainUrl(final String _code, final String linkUrl) {
			this.code = _code;
			this.linkUrl = linkUrl;
		}

		public String getCode() {
			return code;
		}

		public String getLinkUrl() {
			return linkUrl;
		}


	}
	public static enum enMenuList{
		cbNoticeDesc("공지사항"),
		cbNoticeList("공지사항"),
		cbEnvNewsDesc("환경뉴스"),
		cbEnvNewsList("환경뉴스"),
		cbStoreDesc("가맹점"),
		cbStoreList("가맹점조회"),
		cbHotNewsDesc("기관핫뉴스"),
		cbHotNewsList("기관핫뉴스"),
		cbMunicipalNewsDesc("시정뉴스"),
		cbMunicipalNewsList("시정뉴스"),
		cbMunicipalVideoDesc("시정활동영상"),
		cbMunicipalVideoList("시정활동영상"),
		cbQna("문의 및 제안"),
		cbFaq("자주하는 질문"),
		cbQRView("QR보기"),
		cbStoreSearch("매장착기"),
		cbPerformanceQuery("실적조회"),
		cbSaleList("매출관리"),
		cbQrScan("QR 스캔"),
		cbQrList("QR관리"),
		cbMyPage("마이페이지"),
		cbPersonal("개인/보안"),
		cbCustSvc("고객센타"),
		cbSetting("설정"),
		cbPwdChange("비밀번호 변경");
		private String name;
		
		enMenuList(final String _name) {
			this.name = _name;
		}

		public String getName() {
			return name;
		}


	}
}
