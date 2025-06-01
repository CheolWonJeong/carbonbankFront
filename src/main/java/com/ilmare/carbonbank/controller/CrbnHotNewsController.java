package com.ilmare.carbonbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ilmare.carbonbank.cmn.controller.ConfigConstants;
import com.ilmare.carbonbank.cmn.controller.ConfigConstants.enMenuList;
import com.ilmare.carbonbank.cmn.mgr.SessInfo;
import com.ilmare.carbonbank.cmn.mgr.SessionManager;
import com.ilmare.carbonbank.model.NewsCommonModel;
import com.ilmare.carbonbank.service.CrbnHotNewsService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/main")
public class CrbnHotNewsController {

	@Autowired
	private CrbnHotNewsService hotSvc;

	
	@Autowired
	private SessionManager sessMgr;
	
	@Autowired
	private ConfigConstants conConst;
	
	/*
	 * 핫뉴스 상세
	 */
	@RequestMapping("/cbHotNewsDesc.do")
	public String HotNewsDesc(HttpServletRequest request, final NewsCommonModel paramVo, Model model) throws Exception {
		
		log.info(" ### HotNewsDesc Start ###");
		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			return "redirect:" + conConst.lgnUrl;
			
		}

		SessInfo sess = sessMgr.getSession(request);
		model.addAttribute("sess", sess);

		//읽음 건수 추가
		hotSvc.addReadCnt(paramVo);
		//핫뉴스 한건 조회
		NewsCommonModel rtnModel = hotSvc.selectDesc(paramVo);
		log.info("HotNewsDesc " + rtnModel);
		
		model.addAttribute("pagenm", enMenuList.cbHotNewsDesc.getName());
		
		model.addAttribute("docview", rtnModel);
		log.info("### HotNewsDesc End ###");

		return "/mobile/main/hotnewsdesc";
	}


	/*
	 * 핫뉴스 목록
	 */
	@RequestMapping("/cbHotNewsList.do")
	public String HotNewsList(HttpServletRequest request, final NewsCommonModel paramVo, Model model) throws Exception {
		
		log.info("### HotNewsList Start ###");
		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			return "redirect:" + conConst.lgnUrl;
			
		}

		SessInfo sess = sessMgr.getSession(request);
		model.addAttribute("sess", sess);
		
		//시정뉴스 목록
        int pageSize = conConst.pageSize;    //페이지당 row 건수
        int pageNo   = (paramVo.getPageNo() < 1? 1: paramVo.getPageNo()); //조회할 페이지 번호
        int sRowNum = ((pageNo - 1) * pageSize);    //조회할 row의 시작값
        
		log.info("EnvNewsList {} ~ {}", sRowNum, pageSize);
		paramVo.setPageNo(sRowNum);
		paramVo.setListSize(pageSize);
		paramVo.setPartyCd(sess.getPartyCd());
		
		//핫뉴스 목록
		List<NewsCommonModel> hotNewsList = hotSvc.selectList(paramVo);
		log.info( "HotNewsList hotNewsList.size()=" + hotNewsList.size());
		if ( hotNewsList.size() > 0 )  {
			for (NewsCommonModel tmpdoc : hotNewsList) {
	    		log.info("tmpe()=" + tmpdoc.getShowDtm());
	        }
		}

		model.addAttribute("hotNewsList", hotNewsList);
		//model.addAttribute("menuList", enMenuList);
		log.info("### HotNewsList End ###");

		return "/mobile/main/hotnewslist";

	}

}
