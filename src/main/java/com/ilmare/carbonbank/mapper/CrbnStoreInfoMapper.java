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
	String storeNextVal();
	int selectCount(CrbnStoreInfoModel crbnNotice);
	int updatePasswd(CrbnStoreInfoModel crbnNotice);

	List<CrbnStoreInfoModel> selectKakaoData(CrbnStoreInfoModel commVo);
	List<CrbnStoreInfoModel> selectLatestList(String partyCd);
	
}
