package com.ilmare.carbonbank.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ilmare.carbonbank.model.CrbnNoticeModel;


@Repository
@Mapper
public interface CrbnNoticeMapper {
	CrbnNoticeModel selectAdmDesc(CrbnNoticeModel commVo);
	CrbnNoticeModel selectBeforeAfter(CrbnNoticeModel commVo);
	List<CrbnNoticeModel> selectAdmList(CrbnNoticeModel commVo);
	String selectAdmListCount(CrbnNoticeModel commVo);
	List<CrbnNoticeModel> selectList(CrbnNoticeModel commVo);
	String selectListCount(CrbnNoticeModel commVo);
	CrbnNoticeModel selectDesc(CrbnNoticeModel commVo);
	int insert(CrbnNoticeModel crbnNotice);
	int update(CrbnNoticeModel crbnNotice);
	int updateShowStat(CrbnNoticeModel crbnNotice);
	int updateCancelStat(CrbnNoticeModel crbnNotice);
	int updateDelStat(CrbnNoticeModel crbnNotice);
	int addReadCnt(CrbnNoticeModel crbnNotice);
	List<CrbnNoticeModel> selectLatestList();

	
	
	
}
