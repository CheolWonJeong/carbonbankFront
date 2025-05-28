package com.ilmare.carbonbank.cmn.util;


import java.io.IOException;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class KakoMapUtil {
	
	// kakao map 검색 api
	public JsonNode getStoreInfo(String keyword) {
        final String requestUrl = "https://dapi.kakao.com/v2/local/search/keyword.json?radius=5000&query="+ keyword;
        
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(requestUrl);
        
        get.setHeader("Authorization", "KakaoAK 7684b69926ddc0db177cfbb36c774a88");

        JsonNode returnNode = null;

        try {
            var response = client.execute(get);
            ObjectMapper mapper = new ObjectMapper();
            returnNode = mapper.readTree(response.getEntity().getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnNode;
    }

}
