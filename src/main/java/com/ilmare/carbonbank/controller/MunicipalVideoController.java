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
import com.ilmare.carbonbank.service.CrbnMunVideoService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/main")
public class MunicipalVideoController {

	@Autowired
	private CrbnMunVideoService mvSvc;

	
	@Autowired
	private SessionManager sessMgr;
	
	@Autowired
	private ConfigConstants conConst;
	
	/*
	 * 시정활동 영상 상세
	 */
	@RequestMapping("/cbMunicipalVideoDesc.do")
	public String MunicipalVideoDesc(HttpServletRequest request, final NewsCommonModel paramVo, Model model) throws Exception {
		
		log.info("MunicipalVideoDesc Start");
		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			return "redirect:" + conConst.lgnUrl;
			
		}

		SessInfo sess = sessMgr.getSession(request);
		model.addAttribute("sess", sess);

		//읽음 건수 추가
		mvSvc.addReadCnt(paramVo);
		//시정활동 영상 한건
		NewsCommonModel rtnModel = mvSvc.selectDesc(paramVo);
		log.info("MunicipalVideoDesc " + rtnModel);
		
		// Head pagenm
		model.addAttribute("pagenm", enMenuList.cbMunicipalVideoDesc.getName());
		
		model.addAttribute("docview", rtnModel);

		log.info("MunicipalVideoDesc " + model);
		log.info("MunicipalVideoDesc End");

		return "/mobile/main/municipalvideodesc";
	}


	/*
	 * 시정활동 영상 목록
	 */
	@RequestMapping("/cbMunicipalVideoList.do")
	public String MunicipalVideoList(HttpServletRequest request, final NewsCommonModel paramVo, Model model) throws Exception {
		
		log.info("MunicipalVideoList Start");
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
        
		log.info("MunicipalVideoList {} ~ {}", sRowNum, pageSize);
		paramVo.setPageNo(sRowNum);
		paramVo.setListSize(pageSize);
		paramVo.setPartyCd(sess.getPartyCd());
		
		//시정활동 영상 목록
		List<NewsCommonModel> municipalVideoList = mvSvc.selectList(paramVo);
		
		// Head pagenm
		model.addAttribute("pagenm", enMenuList.cbMunicipalVideoList.getName());

		model.addAttribute("municipalVideoList", municipalVideoList);

		log.info("municipalVideoList " + municipalVideoList.size());
		log.info("municipalVideoList End");

		return "/mobile/main/municipalvideolist";

	}

}
