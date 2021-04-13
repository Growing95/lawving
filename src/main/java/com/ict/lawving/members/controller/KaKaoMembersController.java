package com.ict.lawving.members.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KaKaoMembersController {
	
	
	@RequestMapping(value = "kakao_member.do",produces = "text/html; charset=utf-8")
	@ResponseBody
	private String loginChk(HttpSession session) {
		String access_token= (String)session.getAttribute("access_token");
		String header = "Bearer " + access_token;
		String apiURL = "https://kapi.kakao.com/v2/user/me";
		
		Map<String,String> requestHeaders=new HashMap<String, String>();
		requestHeaders.put("Authorization", header);
		String responseBody = get(apiURL,requestHeaders);
		session.setAttribute("kakaoMember", "kakao");
		return responseBody ;
	}
	
	public String get(String apiURL,Map<String,String> requestHeaders) {
		try {
			URL url = new URL(apiURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			for (Map.Entry<String, String> k : requestHeaders.entrySet()) {
				conn.setRequestProperty(k.getKey(), k.getValue());
			}
			/*
			 * int responseCode = conn.getResponseCode(); if (responseCode ==
			 * HttpURLConnection.HTTP_OK) { return readBody(conn.getInputStream()); }else {
			 * return readBody(conn.getErrorStream()); }
			 */
			//성공하면 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())) ;
			StringBuffer sb = new StringBuffer();
			String line ="";
			while ((line=br.readLine())!=null) {
				sb.append(line);
			}
			System.out.println(sb.toString());
			return sb.toString();
		} catch (Exception e) {
			System.out.println("익셉션에러"+ e);
		}
		return null;
	}
}
