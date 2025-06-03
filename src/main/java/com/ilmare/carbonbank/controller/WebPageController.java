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
import com.ilmare.carbonbank.model.CrbnNoticeModel;
import com.ilmare.carbonbank.model.NewsCommonModel;
import com.ilmare.carbonbank.model.WebMainModel;
import com.ilmare.carbonbank.service.CarbonInfoService;
import com.ilmare.carbonbank.service.CrbnMbrInfoService;
import com.ilmare.carbonbank.service.CrbnNoticeService;
import com.ilmare.carbonbank.service.WebMainService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/web")
public class WebPageController {

	@Autowired
	private WebMainService svc;
	
	@Autowired
	private CrbnNoticeService noticeSvc;

	@Autowired
	private ConfigConstants conConst;
	
	private static final String PARTY_CD = "CARBONBANK";
	

	@RequestMapping(value = "/webMain.do", method=RequestMethod.GET)
	public String cbCarbonMain(HttpServletRequest request, Model model) {
		
		WebMainModel  info = svc.getCarbonData(PARTY_CD);
		
		//탄소 저감량 계산
		int carbonReduction = info.getUseCnt() * 30; 
		info.setCarbonReduction(carbonReduction);
		model.addAttribute("info", info);
		
		return "/web/main";
	}

	@RequestMapping(value = "/webNoticeList.do", method=RequestMethod.GET)
	public String webNoticeList(HttpServletRequest request, final CrbnNoticeModel paramVo,  Model model) {
		
		
		log.info("### webNoticeList Start");

		//공지사항 목록 조회
        int pageSize = conConst.pageSize;    //페이지당 row 건수
        int pageNo   = (paramVo.getPageNo() < 1? 1: paramVo.getPageNo()); //조회할 페이지 번호
        int sRowNum = ((pageNo - 1) * pageSize);    //조회할 row의 시작값
        
		log.info("webNoticeList {} ~ {}", sRowNum, pageSize);
		paramVo.setPageNo(sRowNum);
		paramVo.setListSize(pageSize);
		paramVo.setPartyCd(PARTY_CD);
		
		List<CrbnNoticeModel> noticeList = noticeSvc.selectList(paramVo);
		model.addAttribute("noticeList", noticeList);
		log.info("### webNoticeList End");

		return "/web/noticelist";
	}

	@RequestMapping(value = "/webNoticeDesc.do", method=RequestMethod.GET)
	public String webNoticeDesc(HttpServletRequest request, final CrbnNoticeModel paramVo,   Model model) {
		
		log.info("### webNoticeDesc Start");
		log.info("### webNoticeDesc IN " + paramVo.getDocSeq()); // docSeq / pageNo / listSize 

		//공지사항 읽음 건수 추가
		noticeSvc.addReadCnt(paramVo);

		//공지사항 한건 조회
		CrbnNoticeModel rtnModel = noticeSvc.selectDesc(paramVo);
		model.addAttribute("docview", rtnModel);
		log.info("### webNoticeDesc End");

		return "/web/noticedesc";
	}

	
}
