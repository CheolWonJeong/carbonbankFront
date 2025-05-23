package com.ilmare.carbonbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilmare.carbonbank.mapper.CrbnMbrInfoMapper;
import com.ilmare.carbonbank.model.CrbnMbrInfoModel;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class CrbnMbrInfoService {
	@Autowired
	public CrbnMbrInfoMapper mapper;
	

	public CrbnMbrInfoModel selectAdmDesc(CrbnMbrInfoModel param){
		return mapper.selectDesc(param);
	}

	/*
	 * 관리자 리스트 조회
	 */
	public List<CrbnMbrInfoModel> selectAdmList(CrbnMbrInfoModel param){
		return mapper.selectAdmList(param);
	}
	
	public String  selectAdmListCount(CrbnMbrInfoModel param){
		return mapper.selectAdmListCount(param);
	}

	
	public String  selectMaxQrCode(){
		return mapper.selectMaxQrCode();
	}

	public CrbnMbrInfoModel  selectDesc(CrbnMbrInfoModel param){
		return mapper.selectDesc(param);
	}

	/*
	 * 관리자 등록
	 */
	public int insertMbr(CrbnMbrInfoModel nModel)
	{
		int rtn = mapper.insertMbr(nModel);
		//rtn =  mapper.insertMbr(nModel);
		return rtn;
		
	}
	public int  update(CrbnMbrInfoModel param){
		return mapper.updateMbrQrUse(param);
	}


	
	
}
