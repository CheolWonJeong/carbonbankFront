package com.ilmare.carbonbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ilmare.carbonbank.mapper.CrbnStoreInfoMapper;
import com.ilmare.carbonbank.model.CrbnStoreInfoModel;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class CrbnStoreInfoService {
	@Autowired
	public CrbnStoreInfoMapper mapper;
	

	public CrbnStoreInfoModel selectDesc(CrbnStoreInfoModel param){
		return mapper.selectDesc(param);
	}

	/*
	 * 관리자 리스트 조회
	 */
	public List<CrbnStoreInfoModel> selectList(CrbnStoreInfoModel param){
		return mapper.selectList(param);
	}
	
	public String  selectListCount(CrbnStoreInfoModel param){
		return mapper.selectListCount(param);
	}

	
	public String  storeNextVal(){
		return mapper.storeNextVal();
	}

	public int  selectCount(CrbnStoreInfoModel nModel){
		return mapper.selectCount(nModel);
	}
	public int  updatePasswd(CrbnStoreInfoModel param){
		return mapper.updatePasswd(param);
	}

	public List<CrbnStoreInfoModel> selectKakaoData(CrbnStoreInfoModel param){
		return mapper.selectKakaoData(param);
	}
	
	public List<CrbnStoreInfoModel> selectLatestList(String partyCd){
		return mapper.selectLatestList(partyCd);
	}
	
	
	public String queryQrStat(String useQrCd){
		return mapper.queryQrStat(useQrCd);
	}
	
	public void insertQrUseHist(CrbnStoreInfoModel param){
		mapper.insertQrUseHist(param);
	}
	
	
}
