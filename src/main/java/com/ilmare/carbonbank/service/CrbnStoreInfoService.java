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
	

	public CrbnStoreInfoModel selectAdmDesc(CrbnStoreInfoModel param){
		return mapper.selectAdmDesc(param);
	}

	/*
	 * 관리자 리스트 조회
	 */
	public List<CrbnStoreInfoModel> selectAdmList(CrbnStoreInfoModel param){
		return mapper.selectAdmList(param);
	}
	
	public String  selectAdmListCount(CrbnStoreInfoModel param){
		return mapper.selectAdmListCount(param);
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

	
	
}
