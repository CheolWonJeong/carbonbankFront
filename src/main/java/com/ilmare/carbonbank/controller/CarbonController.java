package com.ilmare.carbonbank.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ilmare.carbonbank.cmn.controller.ConfigConstants;
import com.ilmare.carbonbank.cmn.controller.ConfigConstants.enMenuList;
import com.ilmare.carbonbank.cmn.controller.ConfigConstants.mainUrl;
import com.ilmare.carbonbank.cmn.mgr.SessInfo;
import com.ilmare.carbonbank.cmn.mgr.SessionManager;
import com.ilmare.carbonbank.cmn.util.DateUtil;
import com.ilmare.carbonbank.cmn.util.FileUtil;
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
	public String cbCarbonMain(HttpServletRequest request,  Model model) {
		
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
		
		if ( !"S".equals(loginInd) ) {
			//회원
			
			//디지털 QR이미지 세션에 있는지 확인
			String fileUrl = sessMgr.getDgtQrUri(request);
			log.info("QR IMG fileUrl={} ", fileUrl);
			if (fileUrl == null || fileUrl.isEmpty()) {
				
	            String fileName = FileUtil.imgTempServerPath +File.separator + sess.getDgtQrCd() + ".png";
	            fileUrl = FileUtil.imgUri   +File.separator + sess.getDgtQrCd() + ".png";
				log.info("QR IMG {} |{}", fileName, fileUrl);
	            //서버에 파일 이미지 존재 검사
	            File file = new File(fileName);
	            
	            if (!file.exists()) {
					//QR 이미지 조회
					CrbnMbrInfoModel memInfo = svc.getDgtQrImg(sess.getDgtQrCd());       //디지털 QR 이미지
					
					//이미지 임시 경로에 파일 저장
					try (FileOutputStream fos = new FileOutputStream(fileName)) {
			            fos.write(memInfo.getDgtQrImg());
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
	            }	            
				
				sessMgr.setDgtQrUri(request, fileUrl);
				
			}
			model.addAttribute("dgtQrImgUrl", fileUrl);

            
			//나의실천점수
			int useCnt = svc.getMbrUseTotal(sess.getMbrId());
			String myLank = svc.getMbrRanking(sess.getMbrId());
			
			//추후 꼭 삭제
			useCnt = (useCnt < 1? 132: useCnt);
			myLank = (myLank == null? "2": myLank);

			model.addAttribute("useCnt", useCnt);
			model.addAttribute("myLank", myLank);

			viewNm = mainUrl.member.getLinkUrl();
			
		} else if ( "S".equals(loginInd) ) {
			//가맹점
			//등록 회원수
			int storeMemTotalCnt = svc.getStoreMemberCount(sess.getStoreId());
			model.addAttribute("storeMemTotalCnt", storeMemTotalCnt);

			viewNm = mainUrl.store.getLinkUrl();
		}
		log.info("viewNm = {} | {}", sess.getPartyCd(), viewNm);
		
		
		
		return viewNm;
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
		
		//회원, 가맹점에 따른 view 결정
		String loginInd = sess.getLoginInd();
		String viewNm = null;
		
		List<String> months = new ArrayList<>();
		for (int i = 1; i <= 12; i++) {
		    months.add(String.format("%02d", i)); // "01", "02", ..., "12"
		}
		model.addAttribute("months", months);		
		
		return "/mobile/carbon/performancequery";
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
		
		//디지털 QR이미지 세션에 있는지 확인
		String fileUrl = sessMgr.getDgtQrUri(request);
		log.info("QR IMG fileUrl={} ", fileUrl);
		if (fileUrl == null || fileUrl.isEmpty()) {
			
            String fileName = FileUtil.imgTempServerPath +File.separator + sess.getDgtQrCd() + ".png";
            fileUrl = FileUtil.imgUri   +File.separator + sess.getDgtQrCd() + ".png";
			log.info("QR IMG {} |{}", fileName, fileUrl);
            //서버에 파일 이미지 존재 검사
            File file = new File(fileName);
            
            if (!file.exists()) {
				//QR 이미지 조회
				CrbnMbrInfoModel memInfo = svc.getDgtQrImg(sess.getDgtQrCd());       //디지털 QR 이미지
				
				//이미지 임시 경로에 파일 저장
				try (FileOutputStream fos = new FileOutputStream(fileName)) {
		            fos.write(memInfo.getDgtQrImg());
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
            }	            
			
			sessMgr.setDgtQrUri(request, fileUrl);
			
		}
		model.addAttribute("dgtQrImgUrl", fileUrl);

		return "/mobile/carbon/qrview";
	}
	
}
