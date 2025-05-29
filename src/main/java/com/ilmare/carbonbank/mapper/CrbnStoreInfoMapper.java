package com.ilmare.carbonbank.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ilmare.carbonbank.model.CrbnStoreInfoModel;


@Repository
@Mapper
public interface CrbnStoreInfoMapper {
	CrbnStoreInfoModel selectAdmDesc(CrbnStoreInfoModel commVo);
	List<CrbnStoreInfoModel> selectAdmList(CrbnStoreInfoModel commVo);
	String selectAdmListCount(CrbnStoreInfoModel commVo);
	String storeNextVal();
	int selectCount(CrbnStoreInfoModel crbnNotice);
	int updatePasswd(CrbnStoreInfoModel crbnNotice);


}
