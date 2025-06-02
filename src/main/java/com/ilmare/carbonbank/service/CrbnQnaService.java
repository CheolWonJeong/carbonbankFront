package com.ilmare.carbonbank.service;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilmare.carbonbank.mapper.CrbnQnaMapper;
import com.ilmare.carbonbank.model.CrbnQnaModel;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class CrbnQnaService {
	@Autowired
	public CrbnQnaMapper mapper;
	
	/*
	 * 관리자 상세 조회
	 */
	public CrbnQnaModel selectAdmDesc(CrbnQnaModel commVo){
		return mapper.selectAdmDesc(commVo);
	}
	
	/*
	 * 관리자 리스트 조회
	 */
	public List<CrbnQnaModel> selectAdmList(CrbnQnaModel commVo){
		return mapper.selectAdmList(commVo);
	}
	
	/*
	 * 관리자 리스트 건수
	 */
	public String selectAdmListCount(CrbnQnaModel nModel)
	{
		return mapper.selectAdmListCount(nModel);
		
	}
	
	/*
	 * 모바일의 리스트
	 */
	public List<CrbnQnaModel> selectList(CrbnQnaModel commVo){
		return mapper.selectList(commVo);
	}
	
	/*
	 * 모바일의 리스트의 건수 조회
	 */
	public int selectListCount(CrbnQnaModel commVo){
		String  sTemp = mapper.selectListCount(commVo);
		if ( Strings.isBlank(sTemp) )
			return 0;
		else
			return Integer.parseInt(sTemp);
	}
	
	/*
	 * 모바일의 상세조회
	 */
	public CrbnQnaModel selectDesc(CrbnQnaModel commVo){
		return mapper.selectDesc(commVo);
	}
	
	/*
	 * 관리자 등록
	 */
	public int insert(CrbnQnaModel nModel)
	{
		return mapper.insert(nModel);
		
	}
	
	/*
	 * 관리자 update
	 */
	public int update(CrbnQnaModel nModel)
	{
		return mapper.update(nModel);
		
	}
	
	/*
	 * 관리자 배포 상태로 변경
	 */
	public int updateReply(CrbnQnaModel nModel)
	{
		return mapper.updateReply(nModel);
		
	}
	
	/*
	 * front 읽음 건수 추가
	 */
	public int addReadCnt(CrbnQnaModel nModel)
	{
		return mapper.addReadCnt(nModel);
		
	}
	
	
}
