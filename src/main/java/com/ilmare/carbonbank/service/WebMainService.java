package com.ilmare.carbonbank.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilmare.carbonbank.model.WebMainModel;
import com.ilmare.carbonbank.mapper.WebMainMapper;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class WebMainService {
	@Autowired
	public WebMainMapper mapper;
	
	public WebMainModel getCarbonData(String parTy){
		return mapper.getCarbonData(parTy);
	}

}
