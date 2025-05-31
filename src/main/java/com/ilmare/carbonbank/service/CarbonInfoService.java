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
	
	public List<CarbonInfoModel>  memberUseCntStatsYmd(CarbonInfoModel paramInfo){
		return mapper.memberUseCntStatsYmd(paramInfo);
	}
	
	public List<CarbonInfoModel>  memberUseCntStatsYm(CarbonInfoModel paramInfo){
		return mapper.memberUseCntStatsYm(paramInfo);
	}
	
	public int getStoreMemberCount(String storeId){
		return mapper.getStoreMemberCount(storeId);
	}
	
	public List<CarbonInfoModel>  storeSaleCntStatsYmd(CarbonInfoModel paramInfo){
		return mapper.storeSaleCntStatsYmd(paramInfo);
	}
	
	public List<CarbonInfoModel>  storeSaleCntStatsYm(CarbonInfoModel paramInfo){
		return mapper.storeSaleCntStatsYm(paramInfo);
	}
	

	public List<CarbonInfoModel>  storeUseCntStatsYmd(CarbonInfoModel paramInfo){
		return mapper.storeUseCntStatsYmd(paramInfo);
	}
	
	public List<CarbonInfoModel>  storeUseCntStatsYm(CarbonInfoModel paramInfo){
		return mapper.storeUseCntStatsYm(paramInfo);
	}
	
}
