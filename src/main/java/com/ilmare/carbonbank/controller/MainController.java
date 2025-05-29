package com.ilmare.carbonbank.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ilmare.carbonbank.cmn.controller.ConfigConstants;
import com.ilmare.carbonbank.cmn.controller.ConfigConstants.enMenuList;
import com.ilmare.carbonbank.cmn.controller.ConfigConstants.mainUrl;
import com.ilmare.carbonbank.cmn.mgr.SessInfo;
import com.ilmare.carbonbank.cmn.mgr.SessionManager;
import com.ilmare.carbonbank.cmn.vo.CommonVo;
import com.ilmare.carbonbank.mapper.CrbnEnvNewsMapper;
import com.ilmare.carbonbank.model.CrbnNoticeModel;
import com.ilmare.carbonbank.model.CrbnStoreInfoModel;
import com.ilmare.carbonbank.model.NewsCommonModel;
import com.ilmare.carbonbank.service.CarbonInfoService;
import com.ilmare.carbonbank.service.CrbnNoticeService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/main")
public class MainController {

	@Autowired
	private CrbnNoticeService noticeSvc;

	@Autowired
	private CarbonInfoService carbonSvc;

	@Autowired
	private CrbnEnvNewsMapper envSvc;

	@Autowired
	private SessionManager sessMgr;
	
	@Autowired
	private ConfigConstants conConst;
	
	
	@RequestMapping(value = "/cbMain.do", method=RequestMethod.GET)
	public String Viewhome(HttpServletRequest request,  Model model) {
		
		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			return "redirect:" + conConst.lgnUrl;
			
		}

		SessInfo sess = sessMgr.getSession(request);
		model.addAttribute("sess", sess);
		//소속에 따른 view 결정
		String partyCd = sess.getPartyCd();
		String viewNm = null;
		
		if ( partyCd.equals(mainUrl.CARBONBANK.getCode())) {
			viewNm = mainUrl.CARBONBANK.getLinkUrl();
		} else if ( partyCd.equals(mainUrl.INCHEON.getCode())) {
			viewNm = mainUrl.INCHEON.getLinkUrl();
			
		} else {
			viewNm = mainUrl.CARBONBANK.getLinkUrl();
		}
		log.info("viewNm = {} | {}", sess.getPartyCd(), viewNm);
		
		//2 자료 조회
		//파람 set
		
		//나의실천점수
		int useCnt = carbonSvc.getMbrUseTotal(sess.getMbrId());
		String myLank = carbonSvc.getMbrRanking(sess.getMbrId());
		model.addAttribute("useCnt", useCnt);
		model.addAttribute("myLank", myLank);
		
		//공지사항
		List<CrbnNoticeModel> noticeList = noticeSvc.selectLatestList();
		log.info( "noticeList.size()=" + noticeList.size());
		if ( noticeList.size() > 0 )  {
			for (CrbnNoticeModel tmpdoc : noticeList) {
	    		log.info("tmpe()=" + tmpdoc.getShowDtm());
	        }
		}

		model.addAttribute("noticeList", noticeList);

		//환경뉴스
		List<NewsCommonModel> envNewsList = envSvc.selectLatestList();
/*		
		NewsCommonModel tmpm = new NewsCommonModel();
		tmpm.setDocTitle("인천 서구, 텀블러로 환경 지키고 탄소중립포인트 혜택 탄소 중립포인트");
		tmpm.setImgNailNm("/resources/images/thumb_01.png");
		tmpm.setDocFrom("YTN");
		envNewsList.add(tmpm);
*/
		model.addAttribute("envNewsList", envNewsList);
		
		//가맹점
		List<CrbnStoreInfoModel>  storeList = null;
		NewsCommonModel tmpS = new NewsCommonModel();
		tmpS.setDocTitle("인천 서구, 텀블러로 환경 지키고 탄소중립포인트 혜택 탄소 중립포인트");
		tmpS.setImgNailNm("/resources/images/thumb_01.png");
		tmpS.setDocFrom("YTN");
		envNewsList.add(tmpS);

		model.addAttribute("storeList", storeList);

		//핫뉴스
		List<NewsCommonModel> hotNewsList = null;
		model.addAttribute("hotNewsList", hotNewsList);
		
		return viewNm;
	}

	/*
	 * 공지사항 상세 조회
	 */
	@RequestMapping("/cbNoticeDesc.do")
	public String NoticeDesc(HttpServletRequest request, final CrbnNoticeModel paramVo, Model model) throws Exception {
		
		log.info("NoticeDesc Start");
/*
		sessMgr.createSession(request, false);
		if ( !sessMgr.isSession() ) {
			log.info("AdmNoticeView 세션 없음 상태");
			return "redirect:" + conConst.lgnUrl;
		}
		
		log.info("NoticeDesc 로그인 상태");
		SessInfo sessInfo = sessMgr.getSessInfo();
		log.info("NoticeDesc sessInfo=" + sessInfo.toString());

		//권한 검사
		log.info("NoticeDesc PartyGrp=" + sessInfo.getPartyGrp());
		if ( !commSvc.checkContentUse(sessInfo.getPartyGrp()) ) {
			log.info("NoticeDesc 권한 없음 상태");
			return "redirect:" + conConst.lgnUrl;
		}
*/		
		//공지사항 한건 조회
		CrbnNoticeModel rtnModel = noticeSvc.selectDesc(paramVo);
		log.info("NoticeDesc " + rtnModel);

		model.addAttribute("title", enMenuList.cbEnvNewsDesc.getName());
		model.addAttribute("docview", rtnModel);
		//model.addAttribute("menuList", menuList);
		log.info("NoticeDesc End");

		return "/mobile/main/noticedesc";
	}


}
