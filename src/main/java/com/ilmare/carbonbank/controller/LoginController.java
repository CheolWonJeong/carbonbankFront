package com.ilmare.carbonbank.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ilmare.carbonbank.cmn.mgr.SessInfo;
import com.ilmare.carbonbank.cmn.mgr.SessionManager;
import com.ilmare.carbonbank.cmn.util.AES256Util;
import com.ilmare.carbonbank.cmn.util.CommUtil;
import com.ilmare.carbonbank.cmn.util.DateUtil;
import com.ilmare.carbonbank.cmn.util.QRCodeCreate;
import com.ilmare.carbonbank.model.CrbnMbrInfoModel;
import com.ilmare.carbonbank.service.CrbnMbrInfoService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/home")
public class LoginController {

	@Autowired
	CrbnMbrInfoService svc;

	@Autowired
	private SessionManager sessMgr;
	
	@Value("${comm.imgServerPath}")
    private String imgServerPath;	

	@RequestMapping(value = "/cbLogin.do", method=RequestMethod.GET)
	public String cbLogin(HttpServletRequest request,  Model model) {

/*
		log.info("main 로그인 상태");
		SessInfo sessInfo = sessMgr.getSessInfo();
		log.info("main sessInfo=" + sessInfo.toString());
		model.addAttribute("sessInfo", sessInfo);
*/
		return "/mobile/home/login";
	}

	@RequestMapping("/cbLgnProc")
	public @ResponseBody  HashMap cbLgnProc(HttpServletRequest request, final CrbnMbrInfoModel paramVo, Model model) throws Exception {
		
		HashMap result = new HashMap();
		String procInd = "M";	//M:회원이면서 성공, S:가맹점 성공,이외 오류
		log.info("cbLgnProc Start");

		// 패스워드 암호화
		String encParamPasswd = AES256Util.encrypt(paramVo.getMbrPwd());
		paramVo.setMbrPwd(encParamPasswd);

		//멤버 조회
		CrbnMbrInfoModel info = svc.selectLoginData(paramVo);

		if ( info == null ) {
			result.put("procInd", "N");  // 정상
			return result;
		}
		String loginInd = null;		//로그인 구분:M:회원, S:가맹점, A:회원, 가맹점 둘다 가입
		if (info.getMbrId() != null && info.getStoreId() != null) {
			loginInd = "A";
			procInd = "M";	//M:회원이면서 성공, S:가맹점 성공,이외 오류
		} else if (info.getMbrId() != null ) {
			loginInd = "M";
			procInd = "M";	//M:회원이면서 성공, S:가맹점 성공,이외 오류
		} else if (info.getStoreId() != null) {
			loginInd = "S";
			procInd = "S";	//M:회원이면서 성공, S:가맹점 성공,이외 오류
		}
		
		log.info("cbLgnProc {} | {} | {} | {}", procInd,loginInd, info.getMbrId(), info.getStoreId() );
		//로그인 성공
		info.setLgnIp(CommUtil.getIp(request));
		info.setLgnBrowser(CommUtil.getBrowser(request));
		info.setLgnOs(CommUtil.getOs(request));
		//로그인 이력 처리
		svc.loginProc(info);
		//세션 생성
		SessInfo sess= new SessInfo();
		sess.setMbrId(info.getMbrId());
		sess.setMbrNm(info.getMbrNm());
		sess.setMbrPwd(encParamPasswd);
		sess.setMbrCellNum(info.getMbrCellNum());
		sess.setPartyCd(info.getPartyCd());
		sess.setDgtQrCd(info.getDgtQrCd());
		sess.setPprQrCd(info.getPprQrCd());
		sess.setLstLgnDtm(info.getLstLgnDtm());
		sess.setStoreId(info.getStoreId());
		sess.setLoginInd(loginInd);
		sess.setCreDtm(info.getCreDtm());
			
		sessMgr.createSession( request, sess );
		
		result.put("sess", sess);  // 정상
		result.put("procInd", procInd);  // 정상
		log.info("NoticeMainList End procInd={}", procInd);
			
		return result;
	}

	@RequestMapping("/cbAutoLgnProc")
	public @ResponseBody  HashMap cbAutoLgnProc(HttpServletRequest request, final CrbnMbrInfoModel paramVo, Model model) throws Exception {
		
		HashMap result = new HashMap();
		String procInd = "M";	//M:회원이면서 성공, S:가맹점 성공,이외 오류
		String loginInd = null;		//로그인 구분:M:회원, S:가맹점, A:회원, 가맹점 둘다 가입
		log.info("cbAutoLgnProc Start");

		//세션이 존재하고,  파라머터와 id, pwd가 같으면 로그인 성공으로 리턴
		//1.세션검사
		if ( sessMgr.isSession(request) ) {
			log.info("cbAutoLgnProc 세션 존재");
			SessInfo sess = sessMgr.getSession(request);
			if (sess.getMbrId() != null && sess.getStoreId() != null) {
				loginInd = "A";
				procInd = "M";	//M:회원이면서 성공, S:가맹점 성공,이외 오류
			} else if (sess.getMbrId() != null ) {
				loginInd = "M";
				procInd = "M";	//M:회원이면서 성공, S:가맹점 성공,이외 오류
			} else if (sess.getStoreId() != null) {
				loginInd = "S";
				procInd = "S";	//M:회원이면서 성공, S:가맹점 성공,이외 오류
			}
			
			result.put("sess", sess);  // 정상
			result.put("procInd", procInd);  // 정상
			log.info("cbAutoLgnProc 11 End procInd={}", procInd);
			return result;
			
		}

		
		log.info("cbAutoLgnProc 세션 업슴");
		// 패스워드가 암호화되어 있어 암호화 필요 없음
		//멤버 조회
		CrbnMbrInfoModel info = svc.selectLoginData(paramVo);

		if ( info == null ) {
			result.put("procInd", "N");  // 정상
			return result;
		}
		if (info.getMbrId() != null && info.getStoreId() != null) {
			loginInd = "A";
			procInd = "M";	//M:회원이면서 성공, S:가맹점 성공,이외 오류
		} else if (info.getMbrId() != null ) {
			loginInd = "M";
			procInd = "M";	//M:회원이면서 성공, S:가맹점 성공,이외 오류
		} else if (info.getStoreId() != null) {
			loginInd = "S";
			procInd = "S";	//M:회원이면서 성공, S:가맹점 성공,이외 오류
		}
		
		log.info("cbAutoLgnProc {} | {} | {} | {}", procInd,loginInd, info.getMbrId(), info.getStoreId() );
		//로그인 성공
		info.setLgnIp(CommUtil.getIp(request));
		info.setLgnBrowser(CommUtil.getBrowser(request));
		info.setLgnOs(CommUtil.getOs(request));
		//로그인 이력 처리
		svc.loginProc(info);
		//세션 생성
		SessInfo sess= new SessInfo();
		sess.setMbrId(info.getMbrId());
		sess.setMbrNm(info.getMbrNm());
		sess.setMbrCellNum(info.getMbrCellNum());
		sess.setPartyCd(info.getPartyCd());
		sess.setDgtQrCd(info.getDgtQrCd());
		sess.setPprQrCd(info.getPprQrCd());
		sess.setLstLgnDtm(info.getLstLgnDtm());
		sess.setStoreId(info.getStoreId());
		sess.setLoginInd(loginInd);
		sess.setCreDtm(info.getCreDtm());
			
		sessMgr.createSession( request, sess );
		
		result.put("sess", sess);  // 정상
		result.put("procInd", procInd);  // 정상
		log.info("cbAutoLgnProc End procInd={}", procInd);
			
		return result;
	}

	@RequestMapping("/viewSession")
	public @ResponseBody  HashMap viewSession(HttpServletRequest request, final CrbnMbrInfoModel paramVo, Model model) throws Exception {
		
		HashMap result = new HashMap();
		result.put("imgServerPath",  imgServerPath);

		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			result.put("msg" , "session is null");
			return result;
		}
		SessInfo sess = sessMgr.getSession(request);

		result.put("mbrId", sess.getMbrId());
		result.put("mbrNm", sess.getMbrNm());
		result.put("mbrCellNum", sess.getMbrCellNum());
		result.put("partyCd", sess.getPartyCd());
		result.put("dgtQrCd", sess.getDgtQrCd());
		result.put("pprQrCd", sess.getPprQrCd());
		result.put("lstLgnDtm", sess.getLstLgnDtm());
		result.put("creDtm", sess.getCreDtm());
		result.put("loginInd", sess.getLoginInd());
		result.put("storeId",  sess.getStoreId());
		
		return result;
	}

	
}
