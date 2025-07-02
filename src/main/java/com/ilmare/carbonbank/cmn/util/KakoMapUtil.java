package com.ilmare.carbonbank.cmn.util;


import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class KakoMapUtil {

    private static final String KAKAO_API_KEY = "7684b69926ddc0db177cfbb36c774a88";

    // kakao map 검색 api
	public JsonNode getStoreInfo(String keyword) {

		keyword = keyword.replace(" ", "");
        final String requestUrl = "https://dapi.kakao.com/v2/local/search/keyword.json?radius=5000&query="+ keyword;

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(requestUrl);

        get.setHeader("Authorization", "KakaoAK " + KAKAO_API_KEY);

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


    public static HashMap<String, String> getCoordinatesFromAddress(String address) {
		HashMap<String, String> result = new HashMap();
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            String url = "https://dapi.kakao.com/v2/local/search/address.json?query=" + java.net.URLEncoder.encode(address, "UTF-8");
            HttpGet request = new HttpGet(url);
            request.addHeader("Authorization", "KakaoAK " + KAKAO_API_KEY);

            try (CloseableHttpResponse response = client.execute(request)) {
                String json = EntityUtils.toString(response.getEntity());
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(json);

                JsonNode documents = root.path("documents");
                if (documents.isArray() && documents.size() > 0) {
                    JsonNode location = documents.get(0);
                    String lat = location.path("y").asText();
                    String lng = location.path("x").asText();
        			result.put("lat", location.path("y").asText());
        			result.put("lng", location.path("x").asText());
                } else {
                    System.out.println("주소 검색 결과가 없습니다.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

	
}