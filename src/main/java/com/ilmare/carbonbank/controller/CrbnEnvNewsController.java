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
import com.ilmare.carbonbank.mapper.CrbnEnvNewsMapper;
import com.ilmare.carbonbank.model.NewsCommonModel;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/main")
public class CrbnEnvNewsController {

	@Autowired
	private CrbnEnvNewsMapper envSvc;

	@Autowired
	private SessionManager sessMgr;

	@Autowired
	private ConfigConstants conConst;
	
	
	/*
	 * 환경뉴스 상세
	 */
	@RequestMapping("/cbEnvNewsDesc.do")
	public String EnvNewsDesc(HttpServletRequest request, final NewsCommonModel paramVo, Model model) throws Exception {
		
		log.info("EnvNewsDesc Start");
		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			return "redirect:" + conConst.lgnUrl;
			
		}

		SessInfo sess = sessMgr.getSession(request);
		model.addAttribute("sess", sess);


		//읽음 건수 추가
		envSvc.addReadCnt(paramVo);
		NewsCommonModel rtnModel = envSvc.selectDesc(paramVo);
		log.info("EnvNewsDesc " + rtnModel);
		
		model.addAttribute("pagenm", enMenuList.cbEnvNewsDesc.getName());
		
		model.addAttribute("docview", rtnModel);
		//model.addAttribute("menuList", enMenuList);
		log.info("EnvNewsDesc End");

		return "/mobile/main/envnewsdesc";
	}


	/*
	 * 환경뉴스 목록
	 */
	@RequestMapping("/cbEnvNewsList.do")
	public String EnvNewsList(HttpServletRequest request, final NewsCommonModel paramVo, Model model) throws Exception {
		
		log.info("EnvNewsList Start");
		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			return "redirect:" + conConst.lgnUrl;
			
		}

		SessInfo sess = sessMgr.getSession(request);
		model.addAttribute("sess", sess);

        int pageSize = conConst.pageSize;    //페이지당 row 건수
        int pageNo   = (paramVo.getPageNo() < 1? 1: paramVo.getPageNo()); //조회할 페이지 번호
        int sRowNum = ((pageNo - 1) * pageSize);    //조회할 row의 시작값
        
		log.info("EnvNewsList {} ~ {}", sRowNum, pageSize);
		paramVo.setPageNo(sRowNum);
		paramVo.setListSize(pageSize);
		paramVo.setPartyCd(sess.getPartyCd());
		
		//환경뉴스 목록
		List<NewsCommonModel> cbEnvNewsList = envSvc.selectList(paramVo);

		model.addAttribute("envNewsList", cbEnvNewsList);
		log.info("EnvNewsList End");

		return "/mobile/main/envnewslist";
	}

}
