package com.ilmare.carbonbank.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ilmare.carbonbank.cmn.controller.ConfigConstants;
import com.ilmare.carbonbank.cmn.controller.ConfigConstants.enMenuList;
import com.ilmare.carbonbank.cmn.controller.ConfigConstants.mainUrl;
import com.ilmare.carbonbank.cmn.mgr.SessInfo;
import com.ilmare.carbonbank.cmn.mgr.SessionManager;
import com.ilmare.carbonbank.cmn.util.AES256Util;
import com.ilmare.carbonbank.model.CrbnNoticeModel;
import com.ilmare.carbonbank.model.CrbnStoreInfoModel;
import com.ilmare.carbonbank.model.NewsCommonModel;
import com.ilmare.carbonbank.service.CarbonInfoService;
import com.ilmare.carbonbank.service.CrbnEnvNewsService;
import com.ilmare.carbonbank.service.CrbnHotNewsService;
import com.ilmare.carbonbank.service.CrbnMunNewsService;
import com.ilmare.carbonbank.service.CrbnMunVideoService;
import com.ilmare.carbonbank.service.CrbnNoticeService;
import com.ilmare.carbonbank.service.CrbnStoreInfoService;

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
	private CrbnEnvNewsService envSvc;
	
	@Autowired
	private CrbnHotNewsService hotSvc;

	@Autowired
	private CrbnMunNewsService munNewsSvc;

	@Autowired
	private CrbnMunVideoService munVideoSvc;

	@Autowired
	private CrbnStoreInfoService storeSvc;

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

		
		//나의실천점수
		int useCnt = carbonSvc.getMbrUseTotal(sess.getMbrId());
		String myLank = carbonSvc.getMbrRanking(sess.getMbrId());
		model.addAttribute("useCnt", useCnt);
		model.addAttribute("myLank", myLank);
		
		//공지사항
		List<CrbnNoticeModel> noticeList = noticeSvc.selectLatestList(sess.getPartyCd());
		log.info( "noticeList.size()=" + noticeList.size());
		if ( noticeList.size() > 0 )  {
			for (CrbnNoticeModel tmpdoc : noticeList) {
	    		log.info("tmpe()=" + tmpdoc.getShowDtm());
	        }
		}

		model.addAttribute("noticeList", noticeList);

		//환경뉴스
		List<NewsCommonModel> envNewsList = envSvc.selectLatestList(sess.getPartyCd());
		model.addAttribute("envNewsList", envNewsList);
		
		//가맹점
		List<CrbnStoreInfoModel>  storeList = storeSvc.selectLatestList(sess.getPartyCd());
		model.addAttribute("storeList", storeList);


		//소속에 따른 view 결정
		String partyCd = sess.getPartyCd();
		String viewNm = null;
		
		if ( partyCd.equals(mainUrl.CARBONBANK.getCode())) {
			viewNm = mainUrl.CARBONBANK.getLinkUrl();
			//핫뉴스
			List<NewsCommonModel> hotNewsList = hotSvc.selectLatestList(sess.getPartyCd());
			model.addAttribute("hotNewsList", hotNewsList);
			
		} else if ( partyCd.equals(mainUrl.INCHEON.getCode())) {
			viewNm = mainUrl.INCHEON.getLinkUrl();
			
			//시정뉴스
			List<NewsCommonModel> munNewsList = munNewsSvc.selectLatestList(sess.getPartyCd());
			model.addAttribute("munNewsList", munNewsList);
			
			//시정활동 영상
			List<NewsCommonModel> munVideoList = munVideoSvc.selectLatestList(sess.getPartyCd());
			model.addAttribute("munVideoList", munVideoList);
			
		} else {
			viewNm = mainUrl.CARBONBANK.getLinkUrl();
			//핫뉴스
			List<NewsCommonModel> hotNewsList = null;
			model.addAttribute("hotNewsList", hotNewsList);
			
		}
		log.info("viewNm = {} | {}", sess.getPartyCd(), viewNm);
		
		return viewNm;
	}

	@RequestMapping(value = "/cbMyPage.do", method=RequestMethod.GET)
	public String cbMyPage(HttpServletRequest request,  Model model) {
		
		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			return "redirect:" + conConst.lgnUrl;
			
		}

		SessInfo sess = sessMgr.getSession(request);
		model.addAttribute("sess", sess);
		model.addAttribute("pagenm", enMenuList.cbMyPage.getName());
		
		return "/mobile/main/main_mypage";
	}

	@RequestMapping("/AddDocRcmnd")
	public @ResponseBody  HashMap AddDocRcmnd(HttpServletRequest request, final NewsCommonModel paramVo, Model model) throws Exception {
		
		HashMap result = new HashMap();
		log.info("AddDocRcmnd Start");

		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			result.put("procInd", "N");  // 
			return result;
			
		}
		
		switch (paramVo.getDocStat()) {
			case "1":
				// 1 환경뉴스
				envSvc.addRcmndCnt(paramVo);
				break;
			case "2":
				// 2	기관핫뉴스
				hotSvc.addRcmndCnt(paramVo);
				break;
			case "3":
				// 3	시정뉴스
				munNewsSvc.addRcmndCnt(paramVo);
				break;
			case "4":
				// 3	시정뉴스
				munVideoSvc.addRcmndCnt(paramVo);
				break;
			default:
				// 모두 해당되지 않을 때 실행
		}
		result.put("procInd", "S");  // 
		return result;
	}


	@RequestMapping("/AddDocLike")
	public @ResponseBody  HashMap AddDocLike(HttpServletRequest request, final NewsCommonModel paramVo, Model model) throws Exception {
		
		HashMap result = new HashMap();
		log.info("AddDocRcmnd Start");

		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			result.put("procInd", "N");  // 
			return result;
			
		}
		
		switch (paramVo.getDocStat()) {
			case "1":
				// 1 환경뉴스
				envSvc.addLikeCnt(paramVo);
				break;
			case "2":
				// 2	기관핫뉴스
				hotSvc.addLikeCnt(paramVo);
				break;
			case "3":
				// 3	시정뉴스
				munNewsSvc.addLikeCnt(paramVo);
				break;
			case "4":
				// 3	시정뉴스
				munVideoSvc.addLikeCnt(paramVo);
				break;
			default:
				// 모두 해당되지 않을 때 실행
		}
		result.put("procInd", "S");  // 
		return result;
	}

	@RequestMapping("/AddDocSad")
	public @ResponseBody  HashMap AddDocSad(HttpServletRequest request, final NewsCommonModel paramVo, Model model) throws Exception {
		
		HashMap result = new HashMap();
		log.info("AddDocRcmnd Start");

		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			result.put("procInd", "N");  // 
			return result;
			
		}
		
		switch (paramVo.getDocStat()) {
			case "1":
				// 1 환경뉴스
				envSvc.addSadCnt(paramVo);
				break;
			case "2":
				// 2	기관핫뉴스
				hotSvc.addSadCnt(paramVo);
				break;
			case "3":
				// 3	시정뉴스
				munNewsSvc.addSadCnt(paramVo);
				break;
			case "4":
				// 3	시정뉴스
				munVideoSvc.addSadCnt(paramVo);
				break;
			default:
				// 모두 해당되지 않을 때 실행
		}
		result.put("procInd", "S");  // 
		return result;
	}

	@RequestMapping("/AddDocAngry")
	public @ResponseBody  HashMap AddDocAngry(HttpServletRequest request, final NewsCommonModel paramVo, Model model) throws Exception {
		
		HashMap result = new HashMap();
		log.info("AddDocRcmnd Start");

		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			result.put("procInd", "N");  // 
			return result;
			
		}
		
		switch (paramVo.getDocStat()) {
			case "1":
				// 1 환경뉴스
				envSvc.addAngryCnt(paramVo);
				break;
			case "2":
				// 2	기관핫뉴스
				hotSvc.addAngryCnt(paramVo);
				break;
			case "3":
				// 3	시정뉴스
				munNewsSvc.addAngryCnt(paramVo);
				break;
			case "4":
				// 3	시정뉴스
				munVideoSvc.addAngryCnt(paramVo);
				break;
			default:
				// 모두 해당되지 않을 때 실행
		}
		result.put("procInd", "S");  // 
		return result;
	}

	
}
