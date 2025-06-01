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
import com.ilmare.carbonbank.service.CrbnMunNewsService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/main")
public class MunicipalNewsController {

	@Autowired
	private CrbnMunNewsService mnSvc;

	
	@Autowired
	private SessionManager sessMgr;
	
	@Autowired
	private ConfigConstants conConst;
	
	/*
	 * 시정뉴스 상세
	 */
	@RequestMapping("/cbMunicipalNewsDesc.do")
	public String MunicipalNewsDesc(HttpServletRequest request, final NewsCommonModel paramVo, Model model) throws Exception {
		
		log.info("MunicipalNewsDesc Start");
		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			return "redirect:" + conConst.lgnUrl;
			
		}

		SessInfo sess = sessMgr.getSession(request);
		model.addAttribute("sess", sess);

		//읽음 건수 추가
		mnSvc.addReadCnt(paramVo);
		//공지사항 한건 조회
		NewsCommonModel rtnModel = mnSvc.selectDesc(paramVo);
		log.info("MunicipalNewsDesc " + rtnModel);
		
		// Head pagenm
		model.addAttribute("pagenm", enMenuList.cbMunicipalNewsDesc.getName());

		model.addAttribute("docview", rtnModel);
		log.info("MunicipalNewsDesc End");

		return "/mobile/main/municipalnewsdesc";
	}


	/*
	 * 시정뉴스 목록
	 */
	@RequestMapping("/cbMunicipalNewsList.do")
	public String MunicipalNewsList(HttpServletRequest request, final NewsCommonModel paramVo, Model model) throws Exception {
		
		log.info("MunicipalNewsList Start");
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
		
		List<NewsCommonModel> municipalNewsList = mnSvc.selectList(paramVo);

		// Head pagenm
		model.addAttribute("pagenm", enMenuList.cbMunicipalNewsList.getName());

		model.addAttribute("municipalNewsList", municipalNewsList);
		//model.addAttribute("menuList", enMenuList);
		log.info("MunicipalNewsList End");

		return "/mobile/main/municipalnewslist";
	}

}
