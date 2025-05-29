package com.ilmare.carbonbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ilmare.carbonbank.mapper.CarbonInfoMapper;
import com.ilmare.carbonbank.model.CarbonInfoModel;
import com.ilmare.carbonbank.model.CrbnMbrInfoModel;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class CarbonInfoService {
	@Autowired
	public CarbonInfoMapper mapper;
	
	public int getMbrUseTotal(String mbrId){
		return mapper.getMbrUseTotal(mbrId);
	}

	public String getMbrRanking(String mbrId){
		return mapper.getMbrRanking(mbrId);
	}
	
	public List<CarbonInfoModel>  memberUseCntStatsYmd(String mbrId){
		return mapper.memberUseCntStatsYmd(mbrId);
	}
	
	public List<CarbonInfoModel>  memberUseCntStatsYm(String mbrId){
		return mapper.memberUseCntStatsYm(mbrId);
	}
	
	public int getStoreMemberCount(String storeId){
		return mapper.getStoreMemberCount(storeId);
	}
	
	public List<CarbonInfoModel>  storeSaleCntStatsYmd(String storeId){
		return mapper.storeSaleCntStatsYmd(storeId);
	}
	
	public List<CarbonInfoModel>  storeSaleCntStatsYm(String storeId){
		return mapper.storeSaleCntStatsYm(storeId);
	}
	

	public List<CarbonInfoModel>  storeUseCntStatsYmd(String storeId){
		return mapper.storeUseCntStatsYmd(storeId);
	}
	
	public List<CarbonInfoModel>  storeUseCntStatsYm(String storeId){
		return mapper.storeUseCntStatsYm(storeId);
	}
	
	public CrbnMbrInfoModel  getDgtQrImg(String dgtQrCd){
		return mapper.getDgtQrImg(dgtQrCd);
	}
	
}
