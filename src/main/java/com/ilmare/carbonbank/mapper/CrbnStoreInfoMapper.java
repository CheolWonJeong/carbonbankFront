package com.ilmare.carbonbank.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ilmare.carbonbank.model.CrbnStoreInfoModel;


@Repository
@Mapper
public interface CrbnStoreInfoMapper {
	CrbnStoreInfoModel selectDesc(CrbnStoreInfoModel commVo);
	List<CrbnStoreInfoModel> selectList(CrbnStoreInfoModel commVo);
	String selectListCount(CrbnStoreInfoModel commVo);
	int selectCount(CrbnStoreInfoModel model);

	int countCellNum(CrbnStoreInfoModel commModel);	
	int countBisNum(CrbnStoreInfoModel commModel);	

	void insert(CrbnStoreInfoModel model);
	int updatePasswd(CrbnStoreInfoModel model);

	List<CrbnStoreInfoModel> selectKakaoData(CrbnStoreInfoModel commVo);
	List<CrbnStoreInfoModel> selectLatestList(String partyCd);

	String queryQrStat(String useQrCd);
	void insertQrUseHist(CrbnStoreInfoModel model);
	
}
