package com.ilmare.carbonbank.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ilmare.carbonbank.cmn.controller.ConfigConstants;
import com.ilmare.carbonbank.cmn.controller.ConfigConstants.enMenuList;
import com.ilmare.carbonbank.cmn.controller.ConfigConstants.mainUrl;
import com.ilmare.carbonbank.cmn.mgr.SessInfo;
import com.ilmare.carbonbank.cmn.mgr.SessionManager;
import com.ilmare.carbonbank.cmn.util.DateUtil;
import com.ilmare.carbonbank.cmn.util.FileUtil;
import com.ilmare.carbonbank.cmn.util.QRCodeCreate;
import com.ilmare.carbonbank.cmn.vo.CommonVo;
import com.ilmare.carbonbank.model.CarbonInfoModel;
import com.ilmare.carbonbank.model.CrbnMbrInfoModel;
import com.ilmare.carbonbank.model.NewsCommonModel;
import com.ilmare.carbonbank.service.CarbonInfoService;
import com.ilmare.carbonbank.service.CrbnMbrInfoService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/carbon")
public class CarbonController {

	@Autowired
	private CarbonInfoService svc;

	@Autowired
	private CrbnMbrInfoService memSvc;

	@Autowired
	private SessionManager sessMgr;
	
	@Autowired
	private ConfigConstants conConst;
	
	
	@RequestMapping(value = "/cbCarbonMain.do", method=RequestMethod.GET)
	public String cbCarbonMain(HttpServletRequest request, @RequestParam(required=false) String viewInd,  Model model) {
		
		log.info("cbCarbonMain START ");
		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			return "redirect:" + conConst.lgnUrl;
			
		}
		
		SessInfo sess = sessMgr.getSession(request);
		model.addAttribute("sess", sess);
		
		//회원, 가맹점에 따른 view 결정
		String loginInd = sess.getLoginInd();
		String viewNm = null;
		
		log.info("QR IMG loginInd={} ", loginInd);
		
		if ( !"S".equals(loginInd) && viewInd == null) {
			//회원
			
            //디지털 QR 이미지 생성
			String fileUrl = sessMgr.getDgtQrUri(request);
			
			String filePath = FileUtil.dgtQrPath + sess.getCreDtm();
			String fileSavePath = FileUtil.imgServerPath + filePath;

			String fileName = File.separator + sess.getDgtQrCd() + ".png";
			String fileFullName = fileSavePath +fileName;
	        fileUrl = (fileUrl == null || fileUrl.isEmpty()? FileUtil.imgUri + filePath + fileName : fileUrl);
			log.info("QR IMG {} |{}", fileFullName, fileUrl);
            //서버에 파일 이미지 존재 검사
            File file = new File(fileFullName);
            
            if (!file.exists()) {
				//디지털 QR 이미지 생성
            	try {
            		
	        		byte[] qrImgBytes = QRCodeCreate.generateQRCodeImage(sess.getDgtQrCd(), null);
					
					//이미지 임시 경로에 파일 저장
					try (FileOutputStream fos = new FileOutputStream(fileFullName)) {
			            fos.write(qrImgBytes);
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
		        } catch (Exception e) {
		            e.printStackTrace();
            	}
            }	            
			
			sessMgr.setDgtQrUri(request, fileUrl);
			model.addAttribute("dgtQrImgUrl", fileUrl);

            
			//나의실천점수
			int useCnt = svc.getMbrUseTotal(sess.getMbrId());
			String myLank = svc.getMbrRanking(sess.getMbrId());
			model.addAttribute("useCnt", useCnt);
			model.addAttribute("myLank", myLank);

			viewNm = mainUrl.member.getLinkUrl();
			
		} else  {
			//가맹점
			//등록 회원수
			int storeMemTotalCnt = svc.getStoreMemberCount(sess.getStoreId());
			model.addAttribute("storeMemTotalCnt", storeMemTotalCnt);

			viewNm = mainUrl.store.getLinkUrl();
		}
		log.info("viewNm = {} | {}", sess.getPartyCd(), viewNm);
		
		
		
		return viewNm;
	}

	@RequestMapping(value = "/cbQRView.do", method=RequestMethod.GET)
	public String cbQRView(HttpServletRequest request,  Model model) {
		
		log.info("cbQRView START ");
		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			return "redirect:" + conConst.lgnUrl;
			
		}
		
		SessInfo sess = sessMgr.getSession(request);
		model.addAttribute("sess", sess);
		model.addAttribute("pagenm", enMenuList.cbQRView.getName());
		
        //디지털 QR 이미지 생성
		String fileUrl = sessMgr.getDgtQrUri(request);
		
		String filePath = FileUtil.dgtQrPath + sess.getCreDtm();
		String fileSavePath = FileUtil.imgServerPath + filePath;

		String fileName = File.separator + sess.getDgtQrCd() + ".png";
		String fileFullName = fileSavePath +fileName;
        fileUrl = (fileUrl == null || fileUrl.isEmpty()? FileUtil.imgUri + filePath + fileName : fileUrl);
		log.info("QR IMG {} |{}", fileFullName, fileUrl);
        //서버에 파일 이미지 존재 검사
        File file = new File(fileFullName);
        
        if (!file.exists()) {
			//디지털 QR 이미지 생성
        	try {
        		
        		byte[] qrImgBytes = QRCodeCreate.generateQRCodeImage(sess.getDgtQrCd(), null);
				
				//이미지 임시 경로에 파일 저장
				try (FileOutputStream fos = new FileOutputStream(fileFullName)) {
		            fos.write(qrImgBytes);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
	        } catch (Exception e) {
	            e.printStackTrace();
        	}
        }	            
		
		sessMgr.setDgtQrUri(request, fileUrl);
		model.addAttribute("dgtQrImgUrl", fileUrl);

		return "/mobile/carbon/qrview";
	}

	@RequestMapping(value = "/cbPerformanceQuery.do", method=RequestMethod.GET)
	public String cbPerformanceQuery(HttpServletRequest request,  CarbonInfoModel cbnInfo, Model model) {
		
		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			return "redirect:" + conConst.lgnUrl;
			
		}
		
		SessInfo sess = sessMgr.getSession(request);
		model.addAttribute("sess", sess);
		model.addAttribute("pagenm", enMenuList.cbPerformanceQuery.getName());
		
		String currDate = DateUtil.getCurrDate();
		log.info("currDate {} {}  {}", currDate, currDate.substring(0, 4), currDate.substring(4, 6));
		model.addAttribute("curyy", currDate.substring(0, 4));
		model.addAttribute("curmm", currDate.substring(4, 6));
		
		return "/mobile/carbon/performancequery";
	}

	
	
	@RequestMapping(value = "/cbPerformanceQueryProc", method=RequestMethod.POST)
	public @ResponseBody HashMap cbPerformanceQueryProc(HttpServletRequest request,  CarbonInfoModel cbnInfo, Model model) {
		HashMap rtnmap = new HashMap();
		
		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			rtnmap.put("listSize" , "0");
			return rtnmap;
			
		}
		
		SessInfo sess = sessMgr.getSession(request);
		cbnInfo.setMbrId(sess.getMbrId());
		List<CarbonInfoModel>  infoList = null;
		
		log.info("Viewhome 세션 없음 상태");
		//조회구분(D:일별, M:월별)
		if ("D".equals(cbnInfo.getQryInd())) {
			infoList = svc.memberUseCntStatsYmd(cbnInfo);
		} else if ("M".equals(cbnInfo.getQryInd())) {
			infoList = svc.memberUseCntStatsYm(cbnInfo);
		}

		//전체 합계 구함
		int listSum = 0;
		if ( infoList != null && infoList.size() > 0 ) {
			for (int i=0; i < infoList.size(); i++) {
				CarbonInfoModel info = infoList.get(i);
				listSum  += info.getQrUseCnt();
			}
			
		}
		rtnmap.put("infoList", infoList);
		rtnmap.put("listSum", listSum+"");
		return rtnmap;
	}


	@RequestMapping(value = "/cbQrList.do", method=RequestMethod.GET)
	public String cbQrList(HttpServletRequest request,  CarbonInfoModel cbnInfo, Model model) {
		
		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			return "redirect:" + conConst.lgnUrl;
			
		}
		
		SessInfo sess = sessMgr.getSession(request);
		model.addAttribute("sess", sess);
		model.addAttribute("pagenm", enMenuList.cbQrList.getName());
		
		
		String currDate = DateUtil.getCurrDate();
		log.info("currDate {} {}  {}", currDate, currDate.substring(0, 4), currDate.substring(4, 6));
		model.addAttribute("curyy", currDate.substring(0, 4));
		model.addAttribute("curmm", currDate.substring(4, 6));
		
		return "/mobile/carbon/storeqrlist";
	}

	
	
	@RequestMapping(value = "/cbQrListProc", method=RequestMethod.POST)
	public @ResponseBody HashMap cbQrListProc(HttpServletRequest request,  CarbonInfoModel cbnInfo, Model model) {
		HashMap rtnmap = new HashMap();
		
		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			rtnmap.put("listSize" , "0");
			return rtnmap;
			
		}
		
		SessInfo sess = sessMgr.getSession(request);
		cbnInfo.setMbrId(sess.getMbrId());
		List<CarbonInfoModel>  infoList = null;
		
		log.info("Viewhome 세션 없음 상태");
		//조회구분(D:일별, M:월별)
		if ("D".equals(cbnInfo.getQryInd())) {
			infoList = svc.storeSaleCntStatsYmd(cbnInfo);
		} else if ("M".equals(cbnInfo.getQryInd())) {
			infoList = svc.storeSaleCntStatsYm(cbnInfo);
		}

		//전체 합계 구함
		int listSum = 0;
		if ( infoList != null && infoList.size() > 0 ) {
			for (int i=0; i < infoList.size(); i++) {
				CarbonInfoModel info = infoList.get(i);
				listSum  += info.getQrUseCnt();
			}
			
		}
		rtnmap.put("infoList", infoList);
		rtnmap.put("listSum", listSum+"");
		return rtnmap;
	}

	@RequestMapping(value = "/cbSaleList.do", method=RequestMethod.GET)
	public String cbSaleList(HttpServletRequest request,  CarbonInfoModel cbnInfo, Model model) {
		
		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			return "redirect:" + conConst.lgnUrl;
			
		}
		
		SessInfo sess = sessMgr.getSession(request);
		model.addAttribute("sess", sess);
		model.addAttribute("pagenm", enMenuList.cbSaleList.getName());
		
		
		String currDate = DateUtil.getCurrDate();
		log.info("currDate {} {}  {}", currDate, currDate.substring(0, 4), currDate.substring(4, 6));
		model.addAttribute("curyy", currDate.substring(0, 4));
		model.addAttribute("curmm", currDate.substring(4, 6));
		
		return "/mobile/carbon/storeqrsalelist";
	}

	
	
	@RequestMapping(value = "/cbSaleListProc", method=RequestMethod.POST)
	public @ResponseBody HashMap cbSaleListProc(HttpServletRequest request,  CarbonInfoModel cbnInfo, Model model) {
		HashMap rtnmap = new HashMap();
		
		//1.세션검사
		if ( !sessMgr.isSession(request) ) {
			log.info("Viewhome 세션 없음 상태");
			rtnmap.put("listSize" , "0");
			return rtnmap;
			
		}
		
		SessInfo sess = sessMgr.getSession(request);
		cbnInfo.setMbrId(sess.getMbrId());
		List<CarbonInfoModel>  infoList = null;
		
		log.info("Viewhome 세션 없음 상태");
		//조회구분(D:일별, M:월별)
		if ("D".equals(cbnInfo.getQryInd())) {
			infoList = svc.storeUseCntStatsYmd(cbnInfo);
		} else if ("M".equals(cbnInfo.getQryInd())) {
			infoList = svc.storeUseCntStatsYm(cbnInfo);
		}

		//전체 합계 구함
		int listSum = 0;
		if ( infoList != null && infoList.size() > 0 ) {
			for (int i=0; i < infoList.size(); i++) {
				CarbonInfoModel info = infoList.get(i);
				listSum  += info.getQrUseCnt();
			}
			
		}
		rtnmap.put("infoList", infoList);
		rtnmap.put("listSum", listSum+"");
		return rtnmap;
	}

	
}
