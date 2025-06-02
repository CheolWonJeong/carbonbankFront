package com.ilmare.carbonbank.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ilmare.carbonbank.model.CrbnQnaModel;


@Repository
@Mapper
public interface CrbnQnaMapper {
	CrbnQnaModel selectAdmDesc(CrbnQnaModel commVo);
	List<CrbnQnaModel> selectAdmList(CrbnQnaModel commVo);
	String selectAdmListCount(CrbnQnaModel commVo);
	List<CrbnQnaModel> selectList(CrbnQnaModel commVo);
	String selectListCount(CrbnQnaModel commVo);
	CrbnQnaModel selectDesc(CrbnQnaModel commVo);
	int insert(CrbnQnaModel crbnNotice);
	int update(CrbnQnaModel crbnNotice);
	int updateReply(CrbnQnaModel crbnNotice);
	int addReadCnt(CrbnQnaModel crbnNotice);
	
}
