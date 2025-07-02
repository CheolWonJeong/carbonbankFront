package com.ilmare.carbonbank.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ilmare.carbonbank.cmn.controller.ConfigConstants;
import com.ilmare.carbonbank.cmn.controller.ConfigConstants.imgFilePath;
import com.ilmare.carbonbank.cmn.mgr.SessInfo;
import com.ilmare.carbonbank.cmn.mgr.SessionManager;
import com.ilmare.carbonbank.cmn.util.AES256Util;
import com.ilmare.carbonbank.cmn.util.CommUtil;
import com.ilmare.carbonbank.cmn.util.DateUtil;
import com.ilmare.carbonbank.cmn.util.FileUtil;
import com.ilmare.carbonbank.cmn.util.KakoMapUtil;
import com.ilmare.carbonbank.cmn.util.QRCodeCreate;
import com.ilmare.carbonbank.cmn.vo.CommonVo;
import com.ilmare.carbonbank.model.CrbnMbrInfoModel;
import com.ilmare.carbonbank.model.CrbnStoreInfoModel;
import com.ilmare.carbonbank.service.CrbnMbrInfoService;
import com.ilmare.carbonbank.service.CrbnStoreInfoService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	CrbnMbrInfoService svc;

	@Autowired
	CrbnStoreInfoService storeSvc;

	@Autowired
	private SessionManager sessMgr;

	@Autowired
	private FileUtil fileUtil;

	@Autowired
	private KakoMapUtil kakaoUtil;

	@RequestMapping(value = "/cbPwdInit.do", method = RequestMethod.GET)
	public String cbPwdInit(HttpServletRequest request, Model model) {

		return "/mobile/home/passwdinit_" + CommUtil.getPartyCd(request);
	}

	@RequestMapping("/cbPwdInitProc")
	public @ResponseBody HashMap cbPwdInitProc(HttpServletRequest request, final CrbnMbrInfoModel paramVo, Model model)
			throws Exception {

		HashMap result = new HashMap();
		log.info("cbPwdInitProc Start");

		// 기 가입여부 확인
		paramVo.setPartyCd(CommUtil.getPartyCd(request));
		CrbnMbrInfoModel rtnVo = svc.chechJoin(paramVo);
		if (rtnVo == null || rtnVo.getMbrId().isEmpty() && rtnVo.getStoreId().isEmpty()) {
			result.put("procInd", "D"); // 미존재
			return result;
		}

		// 패승뤄드 암호화
		String encParamPasswd = AES256Util.encrypt(paramVo.getMbrPwd());
		log.info("cbPwdInitProc pwd  {} ", encParamPasswd);
		rtnVo.setMbrPwd(encParamPasswd);
		rtnVo.setPartyCd(paramVo.getPartyCd());

		// 비번 update
		svc.updatePasswd(rtnVo);
		result.put("procInd", "S"); // 정상
		log.info("cbPwdInitProc End");

		return result;
	}

	@RequestMapping(value = "/cbRegMmeber.do", method = RequestMethod.GET)
	public String htmlHome(HttpServletRequest request, Model model) {
		return "/mobile/home/memberreg_" + CommUtil.getPartyCd(request);
	}

	@RequestMapping("/cbRegMmeberProc")
	public @ResponseBody HashMap cbRegMmeberProc(HttpServletRequest request, final CrbnMbrInfoModel paramVo,
			Model model) throws Exception {

		HashMap result = new HashMap();
		log.info("cbRegMmeberProc Start");

		// 기 가입여부 확인
		paramVo.setPartyCd(CommUtil.getPartyCd(request));
		int iTmp = svc.selectCount(paramVo);
		if (iTmp > 0) {
			// 이미 가입된 휴대폰 번호
			result.put("procInd", "D"); // 정상
			return result;
		}
		// 멤버 아이디 생성
		String memId = "M" + svc.memberNextVal();
		paramVo.setMbrId(memId);

		// QR 코드 생성
		// QR테이블에서 MAX 값을 읽어 +1 한다.
		String dgnQrCd = "DGT" + svc.dgtQrCdNextVal();
		paramVo.setDgtQrCd(dgnQrCd);

		// 패승뤄드 암호화
		String encParamPasswd = AES256Util.encrypt(paramVo.getMbrPwd());
		log.info("cbRegMmeberProc pwd  {}  | {} ", paramVo.getMbrPwd(), encParamPasswd);
		paramVo.setMbrPwd(encParamPasswd);
		paramVo.setPartyCd(CommUtil.getPartyCd(request));

		// 멤버 저장
		svc.insertMbr(paramVo);
		result.put("procInd", "S"); // 정상
		log.info("cbRegMmeberProc End");

		return result;
	}

	@RequestMapping(value = "/cbRegStore.do", method = RequestMethod.GET)
	public ModelAndView Viewhome(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		List<String> resultList = new ArrayList<String>();

		resultList.add("AAA");
		resultList.add("BBB");
		resultList.add("CCC");
		resultList.add("DDD");
		resultList.add("EEE");
		resultList.add("FFF");

		mav.addObject("resultList", resultList);
		mav.setViewName("/mobile/home/storereg_" + CommUtil.getPartyCd(request));

		return mav;
	}

	@RequestMapping("/cbRegStoreProc")
	public @ResponseBody HashMap cbRegStoreProc(HttpServletRequest request,
			@RequestPart(value = "storeNoImgFile", required = false) MultipartFile storeNoImgFile,
			@RequestPart(value = "storeImgFile", required = false) MultipartFile storeImgFile,
			final CrbnStoreInfoModel paramVo, Model model) throws Exception {

		HashMap result = new HashMap();
		log.info("cbRegStoreProc Start");

		paramVo.setPartyCd(CommUtil.getPartyCd(request));
		// 휴대폰 & 사업자번호
		if (storeSvc.countCellNum(paramVo) > 0) {
			result.put("procInd", "E");
			result.put("errorId", "dupCellNum");
			result.put("errorMsg", "휴대폰 번호가 중복되었습니다.");
			return result;
		}
		if (storeSvc.countBisNum(paramVo) > 0) {
			result.put("procInd", "E");
			result.put("errorId", "dupBisNum");
			result.put("errorMsg", "사업자 번호가 중복되었습니다.");
			return result;
		}

		// 패승뤄드 암호화
		String encParamPasswd = AES256Util.encrypt(paramVo.getStorePwd());
		log.info("cbRegStoreProc pwd  {} ", encParamPasswd);
		paramVo.setStorePwd(encParamPasswd);

		// 사업자등록증 이미지파일 관련
		if (storeNoImgFile != null && !storeNoImgFile.isEmpty()) {
			String fileSavePath = fileUtil.getFileSavePath(imgFilePath.StoreBis.getFilePath(), DateUtil.getCurrDate());

			log.info("파일 이름: " + storeNoImgFile.getOriginalFilename());
			log.info("fileSavePath: {} ", fileSavePath);

			String fileExt = storeNoImgFile.getOriginalFilename()
					.substring(storeNoImgFile.getOriginalFilename().lastIndexOf("."));
			String originalFilename = storeNoImgFile.getOriginalFilename();
			String tmpFileNm = fileSavePath + File.separator + originalFilename;

			log.info("파라미터: {}| {} | {}   ", originalFilename, fileExt, tmpFileNm);

			FileUtil.createDirectory(fileSavePath);
			File savedFile = new File(tmpFileNm);
			storeNoImgFile.transferTo(savedFile); // 업로드된 파일 저장

			paramVo.setStoreNoImg(tmpFileNm);

		} else {
			log.info("storeNoImgFile is null ");
			paramVo.setStoreNoImg("");
		}

		// 파일 관련
		if (storeImgFile != null && !storeImgFile.isEmpty()) {
			String fileSavePath = fileUtil.getFileSavePath(imgFilePath.Store.getFilePath(), DateUtil.getCurrDate());
			String fileUriPath = fileUtil.getFileLinkUrl(imgFilePath.Store.getFilePath(), DateUtil.getCurrDate());

			log.info("파일 이름: " + storeImgFile.getOriginalFilename());
			log.info("fileSavePath: {} ", fileSavePath);

			String fileExt = storeImgFile.getOriginalFilename()
					.substring(storeImgFile.getOriginalFilename().lastIndexOf("."));
			String originalFilename = storeImgFile.getOriginalFilename();
			String imgNailNm = fileSavePath + File.separator + "640" + DateUtil.getCurrDateTime() + fileExt; // 중간에 점 제거
			String urlNailNm = fileUriPath + File.separator + "640" + DateUtil.getCurrDateTime() + fileExt;
			String tmpFileNm = fileSavePath + File.separator + originalFilename;

			log.info("파라미터: {}| {} | {} |  {}  ", originalFilename, fileExt, imgNailNm, tmpFileNm);

			FileUtil.createDirectory(fileSavePath);
			File savedFile = new File(tmpFileNm);
			storeImgFile.transferTo(savedFile); // 업로드된 파일 저장

			// 썸네일 생성
			File thumbnailFile = new File(imgNailNm);
			Thumbnails.of(savedFile).size(700, 400).toFile(thumbnailFile);

			paramVo.setStoreImg(urlNailNm);

		} else {
			log.info("storeImgFile is null ");
			paramVo.setStoreImg("");
		}
		
		//주소에 따른 위경도 조회
		HashMap<String, String> tmpMap = kakaoUtil.getCoordinatesFromAddress(paramVo.getStoreAddr());
		if ( tmpMap != null && tmpMap.size() > 0) {
			paramVo.setStoreLatitude(tmpMap.get("lat"));
			paramVo.setStoreLongitude(tmpMap.get("lng"));
		}
		// 저장
		storeSvc.createStore(paramVo);

		result.put("procInd", "S"); // 정상
		log.info("cbRegStoreProc End");

		return result;
	}

	@RequestMapping("/cbSetPushKey")
	public @ResponseBody HashMap cbSetPushKey(HttpServletRequest request, final CrbnMbrInfoModel paramVo, Model model)
			throws Exception {

		HashMap result = new HashMap();
		log.info("cbSetPushKey Start");

		// 1.세션검사
		if (!sessMgr.isSession(request)) {
			result.put("procInd", "N"); // 정상
			return result;

		}

		SessInfo sess = sessMgr.getSession(request);
		paramVo.setMbrId(sess.getMbrId());
		paramVo.setStoreId(sess.getStoreId());

		log.info("cbSetPushKey PushToken  {} ", paramVo.getPushToken());

		log.info("cbSetPushKey End");

		return result;
	}

}
