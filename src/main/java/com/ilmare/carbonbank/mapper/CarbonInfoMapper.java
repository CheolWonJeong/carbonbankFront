package com.ilmare.carbonbank.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ilmare.carbonbank.model.CarbonInfoModel;
import com.ilmare.carbonbank.model.CrbnMbrInfoModel;


@Repository
@Mapper
public interface CarbonInfoMapper {

	int getMbrUseTotal(String mbrId);
	String getMbrRanking(String mbrId);
	List<CarbonInfoModel> memberUseCntStatsYmd(String mbrId);
	List<CarbonInfoModel> memberUseCntStatsYm(String mbrId);

	int getStoreMemberCount(String storeId);
	List<CarbonInfoModel> storeSaleCntStatsYmd(String storeId);
	List<CarbonInfoModel> storeSaleCntStatsYm(String storeId);
	List<CarbonInfoModel> storeUseCntStatsYmd(String storeId);
	List<CarbonInfoModel> storeUseCntStatsYm(String storeId);
	
	CrbnMbrInfoModel  getDgtQrImg(String dgtQrCd);

}
