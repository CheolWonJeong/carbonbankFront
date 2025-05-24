package com.ilmare.carbonbank.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ilmare.carbonbank.model.CrbnMbrInfoModel;


@Repository
@Mapper
public interface CrbnMbrInfoMapper {
	CrbnMbrInfoModel selectAdmDesc(CrbnMbrInfoModel commVo);
	List<CrbnMbrInfoModel> selectAdmList(CrbnMbrInfoModel commVo);
	String selectAdmListCount(CrbnMbrInfoModel commVo);
	String dgtQrCdNextVal();
	String memberNextVal();
	CrbnMbrInfoModel selectDesc(CrbnMbrInfoModel commVo);
	int insertMbr(CrbnMbrInfoModel crbnNotice);
	int insertDgtImg(CrbnMbrInfoModel crbnNotice);
	int updateMbrQrUse(CrbnMbrInfoModel crbnNotice);
	int updatePasswd(CrbnMbrInfoModel crbnNotice);
	int updateLgnHist(CrbnMbrInfoModel crbnNotice);
	int insertLgnHist(CrbnMbrInfoModel crbnNotice);

	int updateStoreLgnHist(CrbnMbrInfoModel crbnNotice);
	int insertStoreLgnHist(CrbnMbrInfoModel crbnNotice);
	
}
