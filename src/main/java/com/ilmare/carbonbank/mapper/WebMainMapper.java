package com.ilmare.carbonbank.mapper;


import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ilmare.carbonbank.model.WebMainModel;



@Repository
@Mapper
public interface WebMainMapper {

	WebMainModel getCarbonData(String partyCd);
	
}
