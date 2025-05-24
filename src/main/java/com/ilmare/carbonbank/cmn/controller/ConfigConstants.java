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
		cbMunicipalVideoList("시정활동영상");
		private String name;

		enMenuList(final String _name) {
			this.name = _name;
		}

		public String getName() {
			return name;
		}


	}
}
