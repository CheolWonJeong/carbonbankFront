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
	List<CarbonInfoModel> memberUseCntStatsYmd(CarbonInfoModel paramInfo);
	List<CarbonInfoModel> memberUseCntStatsYm(CarbonInfoModel paramInfo);

	int getStoreMemberCount(String storeId);
	List<CarbonInfoModel> storeSaleCntStatsYmd(CarbonInfoModel paramInfo);
	List<CarbonInfoModel> storeSaleCntStatsYm(CarbonInfoModel paramInfo);
	List<CarbonInfoModel> storeUseCntStatsYmd(CarbonInfoModel paramInfo);
	List<CarbonInfoModel> storeUseCntStatsYm(CarbonInfoModel paramInfo);
	
}
