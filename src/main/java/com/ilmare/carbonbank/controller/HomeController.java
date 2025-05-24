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
import org.springframework.web.servlet.ModelAndView;

import com.ilmare.carbonbank.cmn.mgr.SessInfo;
import com.ilmare.carbonbank.cmn.mgr.SessionManager;
import com.ilmare.carbonbank.cmn.util.AES256Util;
import com.ilmare.carbonbank.cmn.util.DateUtil;
import com.ilmare.carbonbank.cmn.util.QRCodeCreate;
import com.ilmare.carbonbank.cmn.vo.CommonVo;
import com.ilmare.carbonbank.model.CrbnMbrInfoModel;
import com.ilmare.carbonbank.service.CrbnMbrInfoService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	CrbnMbrInfoService svc;

	@Autowired
	private SessionManager sessMgr;
	
	@RequestMapping(value = "/cbPwdInit.do", method=RequestMethod.GET)
	@ResponseBody
	public String goHome(HttpServletRequest request,  Model model) {

/*
		log.info("main 로그인 상태");
		SessInfo sessInfo = sessMgr.getSessInfo();
		log.info("main sessInfo=" + sessInfo.toString());
		model.addAttribute("sessInfo", sessInfo);
*/
		return "/mobile/home/passwdinit";
	}

	@RequestMapping(value = "/cbRegMmeber.do", method=RequestMethod.GET)
	public String htmlHome(HttpServletRequest request,  Model model) {
		return "/mobile/home/memberreg";
	}

	@RequestMapping("/cbRegMmeberProc")
	public @ResponseBody  HashMap cbRegMmeberProc(HttpServletRequest request, final CrbnMbrInfoModel paramVo, Model model) throws Exception {
		
		HashMap result = new HashMap();
		log.info("cbRegMmeberProc Start");

		//멤버 아이디 생성
		String memId= "M" + svc.memberNextVal();
		paramVo.setMbrId(memId);

		//QR 코드 생성
		//QR테이블에서 MAX 값을 읽어 +1 한다.
		String dgnQrCd = "DGT" + svc.dgtQrCdNextVal();
		paramVo.setDgtQrCd(dgnQrCd);
		
		// 패승뤄드 암호화
		String encParamPasswd = AES256Util.encrypt(paramVo.getMbrPwd());
		log.info("loginProc pwd  {} ", encParamPasswd);
		paramVo.setMbrPwd(encParamPasswd);
		
		//QR 코드 이미지 생성
		byte[] qrImgBytes = QRCodeCreate.generateQRCodeImage(dgnQrCd, null);
		//멤버 저장
		svc.insertMbr(paramVo);
		result.put("procInd", "S");  // 정상
		log.info("NoticeMainList End");

		return result;
	}

	

	@RequestMapping(value = "/cbRegStore.do", method=RequestMethod.GET)
	public ModelAndView Viewhome(HttpServletRequest request ) {
		ModelAndView mav = new ModelAndView();
		List<String> resultList = new ArrayList<String>();
		
		resultList.add("AAA");
		resultList.add("BBB");
		resultList.add("CCC");
		resultList.add("DDD");
		resultList.add("EEE");
		resultList.add("FFF");
		
		mav.addObject("resultList",resultList);
		mav.setViewName("/mobile/home/storereg");
		
		return mav;
	}

	
}
