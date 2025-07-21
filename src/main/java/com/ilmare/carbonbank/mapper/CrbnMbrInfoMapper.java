package com.ilmare.carbonbank.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ilmare.carbonbank.model.CrbnMbrInfoModel;


@Repository
@Mapper
public interface CrbnMbrInfoMapper {
	CrbnMbrInfoModel selectAdmDesc(CrbnMbrInfoModel nbrInfo);
	List<CrbnMbrInfoModel> selectAdmList(CrbnMbrInfoModel nbrInfo);
	String selectAdmListCount(CrbnMbrInfoModel nbrInfo);
	String dgtQrCdNextVal();
	String memberNextVal();
	CrbnMbrInfoModel selectLoginData(CrbnMbrInfoModel nbrInfo);
	CrbnMbrInfoModel autoLoginData(CrbnMbrInfoModel nbrInfo);
	CrbnMbrInfoModel chechJoin(CrbnMbrInfoModel nbrInfo);
	int selectCount(CrbnMbrInfoModel nbrInfo);
	int compPasswd(CrbnMbrInfoModel nbrInfo);
	int insertMbr(CrbnMbrInfoModel nbrInfo);
	int insertDgtImg(CrbnMbrInfoModel nbrInfo);
	int updateMbrQrUse(CrbnMbrInfoModel nbrInfo);
	int updateMemberPasswd(CrbnMbrInfoModel nbrInfo);
	int updateStorePasswd(CrbnMbrInfoModel nbrInfo);
	int updateLgnHist(CrbnMbrInfoModel nbrInfo);
	int insertLgnHist(CrbnMbrInfoModel nbrInfo);

	int updateMemberPushToken(CrbnMbrInfoModel nbrInfo);
	int updateStorePushToken(CrbnMbrInfoModel nbrInfo);

	int updateStoreLgnHist(CrbnMbrInfoModel nbrInfo);
	int insertStoreLgnHist(CrbnMbrInfoModel nbrInfo);
	
	int deleteMember(String mbrId);
	int deleteStore(String storeId);
}
