package com.ilmare.carbonbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ilmare.carbonbank.mapper.CrbnMbrInfoMapper;
import com.ilmare.carbonbank.model.CrbnMbrInfoModel;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class CrbnMbrInfoService {
	@Autowired
	public CrbnMbrInfoMapper mapper;
	

	public CrbnMbrInfoModel selectAdmDesc(CrbnMbrInfoModel param){
		return mapper.selectAdmDesc(param);
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

	
	public String  dgtQrCdNextVal(){
		return mapper.dgtQrCdNextVal();
	}

	public String  memberNextVal(){
		return mapper.memberNextVal();
	}

	public CrbnMbrInfoModel  selectLoginData(CrbnMbrInfoModel param){
		return mapper.selectLoginData(param);
	}

	public int  selectCount(CrbnMbrInfoModel nModel){
		return mapper.selectCount(nModel);
	}

	/*
	 * 회원 등록
	 */
	@Transactional
	public int insertMbr(CrbnMbrInfoModel nModel)
	{
		int rtn = mapper.insertMbr(nModel);
		rtn =  mapper.insertDgtImg(nModel);
		return rtn;
		
	}
	public int  updateMbrQrUse(CrbnMbrInfoModel param){
		return mapper.updateMbrQrUse(param);
	}

	/*
	 * 회원 로그인 처리
	 * 
	 */
	@Transactional
	public int loginProc(CrbnMbrInfoModel nModel)
	{
		int rtn =0;
		//회원로그인
		//가맹점 로그인
		if ( nModel.getMbrId() != null && !nModel.getMbrId().isEmpty()) {
			rtn = mapper.updateLgnHist(nModel);
			rtn =  mapper.insertLgnHist(nModel);
		}
		
		//가맹점 로그인
		if ( nModel.getRegStoreId() != null && !nModel.getRegStoreId().isEmpty()) {
			rtn = mapper.updateStoreLgnHist(nModel);
			rtn =  mapper.insertStoreLgnHist(nModel);
		}
		return rtn;
		
	}

	/*
	 * 회원 로그인 처리
	 * 
	 */
	@Transactional
	public int storeLoginProc(CrbnMbrInfoModel nModel)
	{
		//가맹점 로그인
		int rtn = mapper.updateStoreLgnHist(nModel);
		rtn =  mapper.insertStoreLgnHist(nModel);
		return rtn;
		
	}

	
	/*
	 * 패스워드 확인
	 * 
	 */
	public int compPasswd(CrbnMbrInfoModel nModel)
	{
		//가맹점 로그인
		return mapper.compPasswd(nModel);
		
	}

	/*
	 * 회원 비밀번호 변경 처리
	 * 
	 */
	@Transactional
	public int updatePasswd(CrbnMbrInfoModel nModel)
	{
		int rtn =0;
		//회원로그인
		//가맹점 로그인
		if ( nModel.getMbrId() != null && !nModel.getMbrId().isEmpty()) {
			rtn = mapper.updateMemberPasswd(nModel);
		}
		
		if ( rtn > 0)  return rtn;
		//가맹점 로그인
		if ( nModel.getRegStoreId() != null && !nModel.getRegStoreId().isEmpty()) {
			rtn = mapper.updateStorePasswd(nModel);
		}
		return rtn;
		
	}

	
}
