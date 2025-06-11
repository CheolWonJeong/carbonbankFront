package com.ilmare.carbonbank.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.ilmare.carbonbank.cmn.controller.ConfigConstants;
import com.ilmare.carbonbank.cmn.controller.ConfigConstants.enMenuList;
import com.ilmare.carbonbank.cmn.mgr.SessInfo;
import com.ilmare.carbonbank.cmn.mgr.SessionManager;
import com.ilmare.carbonbank.cmn.util.KakoMapUtil;
import com.ilmare.carbonbank.model.CrbnStoreInfoModel;
import com.ilmare.carbonbank.service.CrbnStoreInfoService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/store")
public class StoreController {

	@Autowired
	KakoMapUtil kakoMapUtil;

	@Autowired
	private CrbnStoreInfoService storeSvc;

	@Autowired
	private SessionManager sessMgr;

	@Autowired
	private ConfigConstants conConst;

	@RequestMapping(value = "/cbStoreSearch.do")
	public String cbStoreSearch(HttpServletRequest request, Model model) {

		// 1.세션검사
		if (!sessMgr.isSession(request)) {
			log.info("Viewhome 세션 없음 상태");
			return "redirect:" + conConst.lgnUrl;

		}

		SessInfo sess = sessMgr.getSession(request);
		model.addAttribute("sess", sess);

		return "/mobile/store/storesearch";
	}

	@RequestMapping(value = "/cbStoreSearchProc")
	@ResponseBody
	public Map<String, Object> cbStoreSearchProc(@RequestParam String keyword) {

		HashMap<String, Object> hashMap = new HashMap<>();

		log.info("컨트롤러 진입");

		// db에서 가맹점 찾아오기 (현재 스타벅스 연신내 지점 3개 기본세팅)
		ArrayList<CrbnStoreInfoModel> dbStroeList = new ArrayList<>();
		dbStroeList.add(new CrbnStoreInfoModel("840743372", "스타벅스 연신내점", "126.91995670313499", "37.62023680993725"));
		dbStroeList.add(new CrbnStoreInfoModel("2045961313", "스타벅스 연신내역사거리점", "126.9217762570655", "37.6187604104358"));
		dbStroeList
				.add(new CrbnStoreInfoModel("268869607", "스타벅스 연신내역5번출구점", "126.92025914328745", "37.618110683796964"));

		JsonNode kakaoloc = kakoMapUtil.getStoreInfo(keyword);
		JsonNode kakaoStoreList = kakaoloc.get("documents");

		ArrayList<CrbnStoreInfoModel> selectStroeList = new ArrayList<>();

		if (kakaoStoreList != null && kakaoStoreList.isArray()) {
			log.info("저장 시작");
			kakaoStoreList.forEach(JsonNode -> {
				// 검색해온 각 장소의 id 값이 db에 저장되어 있는지 확인 후 리스트 저장
				for (CrbnStoreInfoModel model : dbStroeList) {
					log.info("db 확인 : " + model.getStoreUuid());
					log.info("json 확인 : " + JsonNode.get("id").toString());
					if (model.getStoreUuid().equals(JsonNode.get("id").asText())) {
						log.info("저장 " + model.getStoreUuid());
						selectStroeList.add(model);
					}
				}
			});
			hashMap.put("storeList", selectStroeList);
		}

		return hashMap;

	}

	/*
	 * 환경뉴스 목록
	 */
	@RequestMapping("/cbStoreList.do")
	public String cbStoreList(HttpServletRequest request, final CrbnStoreInfoModel paramVo, Model model)
			throws Exception {

		log.info("cbStoreList Start");
		// 1.세션검사
		if (!sessMgr.isSession(request)) {
			log.info("Viewhome 세션 없음 상태");
			return "redirect:" + conConst.lgnUrl;

		}

		SessInfo sess = sessMgr.getSession(request);
		model.addAttribute("sess", sess);

		int pageSize = conConst.pageSize; // 페이지당 row 건수
		int pageNo = (paramVo.getPageNo() < 1 ? 1 : paramVo.getPageNo()); // 조회할 페이지 번호
		int sRowNum = ((pageNo - 1) * pageSize); // 조회할 row의 시작값

		log.info("cbStoreList {} ~ {}", sRowNum, pageSize);
		paramVo.setPageNo(sRowNum);
		paramVo.setListSize(pageSize);
		paramVo.setPartyCd(sess.getPartyCd());

		// 환경뉴스 목록
		List<CrbnStoreInfoModel> storeList = storeSvc.selectList(paramVo);

		model.addAttribute("pagenm", enMenuList.cbStoreDesc.getName());
		model.addAttribute("storeList", storeList);
		log.info("cbStoreList End");

		return "/mobile/store/storelist";
	}

	/*
	 * 초기조회 밒 버튼 클릭
	 */
	@RequestMapping("/cbStoreListProc")
	public @ResponseBody HashMap cbStoreListProc(HttpServletRequest request, final CrbnStoreInfoModel paramVo,
			Model model) throws Exception {

		log.info("cbStoreListProd Start");
		HashMap result = new HashMap();

		// 1.세션검사
		if (!sessMgr.isSession(request)) {
			log.info("Viewhome 세션 없음 상태");
			result.put("procInd", "E"); // 오류
			result.put("errorId", "NotLogin"); // 오류 종류
			result.put("errorMsg", "로그인 후 이용 하세요"); // 오류 메시지
			return result;
		}

		SessInfo sess = sessMgr.getSession(request);
		model.addAttribute("sess", sess);

		int pageSize = conConst.pageSize; // 페이지당 row 건수
		int pageNo = (paramVo.getPageNo() < 1 ? 1 : paramVo.getPageNo()); // 조회할 페이지 번호
		int sRowNum = ((pageNo - 1) * pageSize); // 조회할 row의 시작값

		log.info("cbStoreList {} ~ {}", sRowNum, pageSize);
		paramVo.setPageNo(sRowNum);
		paramVo.setListSize(pageSize);
		paramVo.setPartyCd(sess.getPartyCd());

		// 환경뉴스 목록
		List<CrbnStoreInfoModel> storeList = storeSvc.selectList(paramVo);

		result.put("storeList", storeList);
		log.info("cbStoreList End");

		return result;
	}

	/*
	 * 환경뉴스 상세
	 */
	@RequestMapping("/cbStoreDesc.do")
	public String cbStoreDesc(HttpServletRequest request, final CrbnStoreInfoModel paramVo, Model model)
			throws Exception {

		log.info("cbStoreDesc Start");
		// 1.세션검사
		if (!sessMgr.isSession(request)) {
			log.info("Viewhome 세션 없음 상태");
			return "redirect:" + conConst.lgnUrl;

		}

		SessInfo sess = sessMgr.getSession(request);
		model.addAttribute("sess", sess);

		// 읽음 건수 추가
		CrbnStoreInfoModel rtnModel = storeSvc.selectDesc(paramVo);

		model.addAttribute("pagenm", enMenuList.cbStoreDesc.getName());

		model.addAttribute("docview", rtnModel);
		// model.addAttribute("menuList", enMenuList);
		log.info("cbStoreDesc End");

		return "/mobile/store/storedesc";
	}

	/*
	 * 초기조회 밒 버튼 클릭
	 */
	@RequestMapping("/cbQrScanProc")
	public @ResponseBody HashMap cbQrScanProc(HttpServletRequest request, final CrbnStoreInfoModel paramVo, Model model)
			throws Exception {

		log.info("cbQrScanProc Start");
		HashMap result = new HashMap();

		// 1.세션검사
		if (!sessMgr.isSession(request)) {
			log.info("cbQrScanProc 세션 없음 상태");
			result.put("procInd", "E"); // 오류
			result.put("errorId", "NotLogin"); // 오류 종류
			result.put("errorMsg", "로그인 후 이용 하세요"); // 오류 메시지
			return result;
		}

		SessInfo sess = sessMgr.getSession(request);
		model.addAttribute("sess", sess);

		//1. QR 사용 등록 체크
		String qrStat = storeSvc.queryQrStat(paramVo.getUseQrCd());
		if (qrStat != null && !"U".equals(qrStat)) {
			result.put("procInd", "N"); // 오류
			result.put("errorId", qrStat); // 오류
			result.put("errorMsg", "QR 코드 사용 등록이 안되었습니다."); // 오류 메시지
			return result;
		}
		//2. QR 사용이력 저장
		paramVo.setStoreId(sess.getStoreId());
		storeSvc.insertQrUseHist(paramVo);
		
		result.put("procInd", "S"); // 성공
		log.info("cbQrScanProc End");

		return result;
	}

}