package com.ilmare.carbonbank.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.ilmare.carbonbank.cmn.util.KakoMapUtil;
import com.ilmare.carbonbank.model.CrbnStoreInfoModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/main")
public class StoreController {
	
	@Autowired
	KakoMapUtil kakoMapUtil;

	
	@RequestMapping(value = "moveStoreList.do")
	public ModelAndView moveStoreMap(ModelAndView modelAndView,
			@RequestParam(name ="keyword", required = false) String keyword) {
		
		 
		modelAndView.setViewName("/mobile/main/storelist");
		return modelAndView;
	}
	
	@RequestMapping(value = "cdStoreList.do")
	@ResponseBody
	public Map<String, Object> getStroeMap(@RequestParam String keyword) {
		
		HashMap<String, Object> hashMap = new HashMap<>();
		
		log.info("컨트롤러 진입");
		
		// db에서 가맹점 찾아오기 (현재 스타벅스 연신내 지점 3개 기본세팅)
		ArrayList<CrbnStoreInfoModel> dbStroeList = new ArrayList<>();
		dbStroeList.add(new CrbnStoreInfoModel("840743372","스타벅스 연신내점", "126.91995670313499", "37.62023680993725"));
		dbStroeList.add(new CrbnStoreInfoModel("2045961313", "스타벅스 연신내역사거리점", "126.9217762570655", "37.6187604104358"));
		dbStroeList.add(new CrbnStoreInfoModel("268869607", "스타벅스 연신내역5번출구점", "126.92025914328745", "37.618110683796964"));
		
		JsonNode kakaoloc = kakoMapUtil.getStoreInfo(keyword);
		JsonNode kakaoStoreList = kakaoloc.get("documents");
		
		ArrayList<CrbnStoreInfoModel> selectStroeList = new ArrayList<>();
		
		if(kakaoStoreList != null && kakaoStoreList.isArray()) {
			log.info("저장 시작");
			kakaoStoreList.forEach( JsonNode -> {
				// 검색해온 각 장소의 id 값이 db에 저장되어 있는지 확인 후 리스트 저장
				for(CrbnStoreInfoModel model : dbStroeList) {
					log.info("db 확인 : "+model.getStoreUuid());
					log.info("json 확인 : "+JsonNode.get("id").toString());
					if(model.getStoreUuid().equals(JsonNode.get("id").asText())) {
						log.info("저장 "+model.getStoreUuid());	
					 selectStroeList.add(model);
					}
				}
			});
			hashMap.put("storeList", selectStroeList);
		}
		
		
		
		return hashMap;
		
	}
	
}
