package com.ilmare.carbonbank.service;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilmare.carbonbank.mapper.CrbnNoticeMapper;
import com.ilmare.carbonbank.model.CrbnNoticeModel;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class CrbnNoticeService {
	@Autowired
	public CrbnNoticeMapper mapper;
	
	/*
	 * 관리자 리스트 조회
	 */
	public List<CrbnNoticeModel> selectAdmList(CrbnNoticeModel commVo){
		return mapper.selectAdmList(commVo);
	}
	
	/*
	 * 관리자 상세 조회
	 */
	public CrbnNoticeModel selectAdmDesc(CrbnNoticeModel commVo){
		return mapper.selectAdmDesc(commVo);
	}
	
	/*
	 * 관리자 등록
	 */
	public int insert(CrbnNoticeModel nModel)
	{
		return mapper.insert(nModel);
		
	}
	
	/*
	 * 관리자 update
	 */
	public int update(CrbnNoticeModel nModel)
	{
		return mapper.update(nModel);
		
	}
	
	/*
	 * 관리자 배포 상태로 변경
	 */
	public int updateShowStat(CrbnNoticeModel nModel)
	{
		return mapper.updateShowStat(nModel);
		
	}
	
	/*
	 * 관리자 배포취소 상태 변경
	 */
	public int updateCancelStat(CrbnNoticeModel nModel)
	{
		return mapper.updateCancelStat(nModel);
		
	}
	
	/*
	 * 관리자 삭제상태 변경
	 */
	public int updateDelStat(CrbnNoticeModel nModel)
	{
		return mapper.updateDelStat(nModel);
		
	}
	
	/*
	 * front 읽음 건수 추가
	 */
	public int addReadCnt(CrbnNoticeModel nModel)
	{
		return mapper.addReadCnt(nModel);
		
	}
	
	/*
	 * 최근 3건 조회 리스트
	 */
	public List<CrbnNoticeModel> selectLatestList(String partyCd){
		return mapper.selectLatestList(partyCd);
	}
	
	/*
	 * 모바일의 리스트
	 */
	public List<CrbnNoticeModel> selectList(CrbnNoticeModel commVo){
		return mapper.selectList(commVo);
	}
	
	/*
	 * 모바일의 리스트의 건수 조회
	 */
	public int selectListCount(CrbnNoticeModel commVo){
		String  sTemp = mapper.selectListCount(commVo);
		if ( Strings.isBlank(sTemp) )
			return 0;
		else
			return Integer.parseInt(sTemp);
	}
	
	/*
	 * 모바일의 상세조회
	 */
	public CrbnNoticeModel selectDesc(CrbnNoticeModel commVo){
		return mapper.selectDesc(commVo);
	}
	
}
