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
import com.ilmare.carbonbank.cmn.util.StringUtil;
import com.ilmare.carbonbank.model.CrbnNoticeModel;
import com.ilmare.carbonbank.service.CrbnNoticeService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/main")
public class CrbnNoticeController {

	@Autowired
	private CrbnNoticeService noticeSvc;

	@Autowired
	private SessionManager sessMgr;

	@Autowired
	private ConfigConstants conConst;
	
	/*
	 * 공지사항 상세 조회
	 */
	@RequestMapping("/cbNoticeDesc.do")
	public String NoticeDesc(HttpServletRequest request, final CrbnNoticeModel paramVo, Model model) throws Exception {
		
		log.info("### NoticeDesc Start");
		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			return "redirect:" + conConst.lgnUrl;
			
		}

		SessInfo sess = sessMgr.getSession(request);
		model.addAttribute("sess", sess);

		log.info("### NoticeDesc IN " + paramVo.getDocSeq()); // docSeq / pageNo / listSize 

		//공지사항 읽음 건수 추가
		noticeSvc.addReadCnt(paramVo);

		//공지사항 한건 조회
		CrbnNoticeModel rtnModel = noticeSvc.selectDesc(paramVo);

		// Head Title
		model.addAttribute("pagenm", enMenuList.cbNoticeDesc.getName());
		
		model.addAttribute("docview", rtnModel);
		log.info("### NoticeDesc End");

		return "/mobile/main/noticedesc";
	}


	/*
	 * 공지사항 목록 조회
	 */
	@RequestMapping("/cbNoticeList.do")
	public String NoticeList(HttpServletRequest request, final CrbnNoticeModel paramVo, Model model) throws Exception {
		
		log.info("### NoticeList Start");
		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			return "redirect:" + conConst.lgnUrl;
			
		}

		SessInfo sess = sessMgr.getSession(request);
		model.addAttribute("sess", sess);

		//공지사항 목록 조회
        int pageSize = conConst.pageSize;    //페이지당 row 건수
        int pageNo   = (paramVo.getPageNo() < 1? 1: paramVo.getPageNo()); //조회할 페이지 번호
        int sRowNum = ((pageNo - 1) * pageSize);    //조회할 row의 시작값
        
		log.info("NoticeList {} ~ {}", sRowNum, pageSize);
		paramVo.setPageNo(sRowNum);
		paramVo.setListSize(pageSize);
		paramVo.setPartyCd(sess.getPartyCd());
		
		List<CrbnNoticeModel> noticeList = noticeSvc.selectList(paramVo);

		// Head Title
		model.addAttribute("pagenm", enMenuList.cbNoticeList.getName());

		model.addAttribute("noticeList", noticeList);
		log.info("### NoticeList End");

		return "/mobile/main/noticelist";
	}

}
