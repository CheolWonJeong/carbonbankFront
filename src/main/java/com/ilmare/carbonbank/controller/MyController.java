package com.ilmare.carbonbank.controller;

import java.util.ArrayList;
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
import com.ilmare.carbonbank.cmn.vo.CommonVo;
import com.ilmare.carbonbank.mapper.CrbnEnvNewsMapper;
import com.ilmare.carbonbank.model.CrbnMbrInfoModel;
import com.ilmare.carbonbank.model.CrbnNoticeModel;
import com.ilmare.carbonbank.model.CrbnStoreInfoModel;
import com.ilmare.carbonbank.model.NewsCommonModel;
import com.ilmare.carbonbank.service.CarbonInfoService;
import com.ilmare.carbonbank.service.CrbnMbrInfoService;
import com.ilmare.carbonbank.service.CrbnNoticeService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/my")
public class MyController {

	@Autowired
	CrbnMbrInfoService mbrSvc;

	@Autowired
	private SessionManager sessMgr;
	
	@Autowired
	private ConfigConstants conConst;
	
	
	@RequestMapping(value = "/cbPersonal.do", method=RequestMethod.GET)
	public String cbPersonal(HttpServletRequest request,  Model model) {
		
		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			return "redirect:" + conConst.lgnUrl;
			
		}

		SessInfo sess = sessMgr.getSession(request);
		model.addAttribute("sess", sess);
		model.addAttribute("pagenm", enMenuList.cbPersonal.getName());
		
		return "/mobile/my/personal";
	}

	@RequestMapping(value = "/cbLogOutProc", method=RequestMethod.POST)
	public @ResponseBody HashMap cbLogOutProc(HttpServletRequest request,  Model model) {
		HashMap rtnmap = new HashMap();
		log.info("cbLogOutProc start");
		
		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("cbLogOutProc Not session");
			rtnmap.put("procind", "S");
			return rtnmap;
		}
		sessMgr.deleteSession(request);
		rtnmap.put("procind", "S");
		log.info("cbLogOutProc session");
		return rtnmap;
	}

	@RequestMapping(value = "/cbMemberExitProc", method=RequestMethod.POST)
	public @ResponseBody HashMap cbMemberExitProc(HttpServletRequest request,  Model model) {
		HashMap rtnmap = new HashMap();
		
		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			rtnmap.put("procind", "N");
		} else {
			
			sessMgr.deleteSession(request);
			
			//탈퇴 처리
			rtnmap.put("procind", "Y");
			
		}
		
		return rtnmap;
	}


	@RequestMapping(value = "/cbPwdChange.do", method=RequestMethod.GET)
	public String cbPwdChange(HttpServletRequest request,  Model model) {
		
		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			return "redirect:" + conConst.lgnUrl;
			
		}

		SessInfo sess = sessMgr.getSession(request);
		model.addAttribute("sess", sess);
		model.addAttribute("pagenm", enMenuList.cbPwdChange.getName());
		
		return "/mobile/my/passwdupdate";
	}

	@RequestMapping("/cbPwdChangeProc")
	public @ResponseBody  HashMap cbPwdChangeProc(HttpServletRequest request, final CrbnMbrInfoModel paramVo, Model model) throws Exception {
		
		HashMap result = new HashMap();
		log.info("cbPwdChangeProc Start");

		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			result.put("procInd", "E");  // 비밀번호 처리중 오류
			return result;
			
		}

		SessInfo sess = sessMgr.getSession(request);
		CrbnMbrInfoModel  pwdInfo = new CrbnMbrInfoModel();
		
		if (sess != null && sess.getMbrId() != null) {
			pwdInfo.setMbrId(sess.getMbrId());
		}

		if (sess != null && sess.getStoreId() != null) {
			pwdInfo.setStoreId(sess.getStoreId());
		}

		//현재 패스워드 비교
		// 패스워드 암호화
		String encCurrPasswd = AES256Util.encrypt(paramVo.getCurPwd());
		
		pwdInfo.setMbrPwd(encCurrPasswd);

		log.info("cbPwdChangeProc compPasswd {} | {} | {}|{}", pwdInfo.getMbrId(), pwdInfo.getStoreId(), pwdInfo.getCurPwd(), pwdInfo.getMbrPwd());
		//멤버 조회
		int cnt = mbrSvc.compPasswd(pwdInfo);
		
		log.info("cbPwdChangeProc compPasswd {} ", cnt);
		if ( cnt < 1)	{
			//현재 비밀번호 틀림
			result.put("procInd", "X");  // 비밀번호틀림
			return result;
		}

		//현재 패스워드 비교
		// 패스워드 암호화
		String encNewPasswd = AES256Util.encrypt(paramVo.getMbrPwd());
		pwdInfo.setMbrPwd(encNewPasswd);

		log.info("cbPwdChangeProc updatePasswd {} | {} | {}|{}", pwdInfo.getMbrId(), pwdInfo.getStoreId(), pwdInfo.getMbrPwd(), pwdInfo.getMbrPwd());
		//멤버 조회
		int rtn = mbrSvc.updatePasswd(pwdInfo);
		
		if ( rtn < 1)	{
			//현재 비밀번호 틀림
			result.put("procInd", "E");  // 비밀번호 처리중 오류
			return result;
		}

		//세션 삭제
		sessMgr.deleteSession(request);
		result.put("procInd", "S");  // 비밀번호틀림
		return result;
	}

	
	@RequestMapping(value = "/cbCustSvc.do", method=RequestMethod.GET)
	public String cbCustSvc(HttpServletRequest request,  Model model) {
		
		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			return "redirect:" + conConst.lgnUrl;
			
		}

		SessInfo sess = sessMgr.getSession(request);
		model.addAttribute("sess", sess);
		model.addAttribute("pagenm", enMenuList.cbCustSvc.getName());
		
		return "/mobile/my/custsvc";
	}


}
