package com.ilmare.carbonbank.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ilmare.carbonbank.model.CrbnQrCommonModel;


@Repository
@Mapper
public interface CrbnQrCommonMapper {
	int insertDgtQr(CrbnQrCommonModel crbnNotice);
	int updateMbrPprQrUse(CrbnQrCommonModel crbnNotice);
	
}
