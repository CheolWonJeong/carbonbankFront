package com.ilmare.carbonbank.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ilmare.carbonbank.cmn.controller.ConfigConstants;
import com.ilmare.carbonbank.cmn.controller.ConfigConstants.enMenuList;
import com.ilmare.carbonbank.cmn.mgr.SessInfo;
import com.ilmare.carbonbank.cmn.mgr.SessionManager;
import com.ilmare.carbonbank.model.CrbnQnaModel;
import com.ilmare.carbonbank.service.CrbnQnaService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/my")
public class CrbnQnaController {

	@Autowired
	private CrbnQnaService qnaSvc;

	@Autowired
	private SessionManager sessMgr;

	@Autowired
	private ConfigConstants conConst;
	
	/*
	 * 공지사항 목록 조회
	 */
	@RequestMapping("/cbQnaList.do")
	public String cbQnaList(HttpServletRequest request, final CrbnQnaModel paramVo, Model model) throws Exception {
		
		log.info("### cbQnaList Start");
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
        
		log.info("cbQnaList {} ~ {}", sRowNum, pageSize);
		paramVo.setPageNo(sRowNum);
		paramVo.setListSize(pageSize);
		paramVo.setPartyCd(sess.getPartyCd());
		
		List<CrbnQnaModel> qnaList = qnaSvc.selectList(paramVo);

		// Head Title
		model.addAttribute("pagenm", enMenuList.cbQna.getName());

		if ( qnaList == null || qnaList.isEmpty()) {
			//등록 화면으로 링크 
			log.info("자료가 업습니다.");
			return "redirect:/my/cbQnaIns.do";
		}
		model.addAttribute("qnaList", qnaList);
		log.info("### cbQnaList End");

		return "/mobile/my/qnalist";
	}

	/*
	 * 공지사항 상세 조회
	 */
	@RequestMapping("/cbQnaIns.do")
	public String cbQnaIns(HttpServletRequest request, final CrbnQnaModel paramVo, Model model) throws Exception {
		
		log.info("### cbQnaIns Start");
		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			return "redirect:" + conConst.lgnUrl;
			
		}

		SessInfo sess = sessMgr.getSession(request);
		model.addAttribute("sess", sess);

		// Head Title
		model.addAttribute("pagenm", enMenuList.cbQna.getName());
		log.info("### NoticeDesc End");

		return "/mobile/my/qnains";
	}

	
	@RequestMapping("/cbQnaInsProc")
	public @ResponseBody  HashMap cbQnaInsProc(HttpServletRequest request, final CrbnQnaModel paramVo, Model model) throws Exception {
		
		HashMap result = new HashMap();
		log.info("cbQnaInsProc Start");

		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			result.put("procInd", "N");  // 
			return result;
			
		}
		
		SessInfo sess = sessMgr.getSession(request);
		paramVo.setPartyCd(sess.getPartyCd());
		paramVo.setMbrId(sess.getMbrId());
		//데이터 검사
		
		//데이터 저장처리
		int insRtn = qnaSvc.insert(paramVo);
		if ( insRtn < 1) {
			//저장 초류
			result.put("procInd", "E");  // 
		} else {
			//저장 성공
			result.put("procInd", "S");  // 
		}
		log.info("cbQnaInsProc End");
		return result;
	}

	
	/*
	 * 공지사항 상세 조회
	 */
	@RequestMapping("/cbQnaUpdate.do")
	public String cbQnaUpdate(HttpServletRequest request, final CrbnQnaModel paramVo, Model model) throws Exception {
		
		log.info("### NoticeDesc Start");
		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			return "redirect:" + conConst.lgnUrl;
			
		}

		SessInfo sess = sessMgr.getSession(request);
		model.addAttribute("sess", sess);

		log.info("### cbQnaUpdate IN " + paramVo.getDocSeq()); // docSeq / pageNo / listSize 

		//공지사항 읽음 건수 추가
		qnaSvc.addReadCnt(paramVo);

		//공지사항 한건 조회
		CrbnQnaModel rtnModel = qnaSvc.selectDesc(paramVo);
		log.info("### cbQnaUpdate " + rtnModel);

		// Head Title
		model.addAttribute("pagenm", enMenuList.cbQna.getName());
		
		model.addAttribute("docview", rtnModel);
		log.info("### cbQnaUpdate End");

		return "/mobile/my/qnaupdate";
	}


	@RequestMapping("/cbQnaUpdateProc")
	public @ResponseBody  HashMap cbQnaUpdateProc(HttpServletRequest request, final CrbnQnaModel paramVo, Model model) throws Exception {
		
		HashMap result = new HashMap();
		log.info("cbQnaUpdateProc Start");

		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			result.put("procInd", "N");  // 
			return result;
			
		}
		
		SessInfo sess = sessMgr.getSession(request);
		paramVo.setPartyCd(sess.getPartyCd());
		paramVo.setMbrId(sess.getMbrId());
		//데이터 검사
		
		//데이터 저장처리
		int insRtn = qnaSvc.update(paramVo);
		if ( insRtn < 1) {
			//저장 초류
			result.put("procInd", "E");  // 
		} else {
			//저장 성공
			result.put("procInd", "S");  // 
		}
		log.info("cbQnaUpdateProc End");
		return result;
	}

	
	
}
