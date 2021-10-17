package com.snslogin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.models.dto.Member;

public abstract class SocialLogin {
	
	public abstract String getCodeUrl(HttpServletRequest request);
	
	public abstract String getAccessToken(HttpServletRequest request, String code, String state) throws Exception, IOException, ParseException;
	public abstract String getAccessToken(HttpServletRequest request) throws Exception, IOException, ParseException;
	
	public abstract HashMap<String, String> getMemberProfile(String accessToken);
	
	public abstract boolean isJoin(HashMap<String, String> memberData, HttpServletRequest request);
	
	public abstract boolean login(HttpServletRequest request);
	
	public abstract Member getSnsMemberData(HttpServletRequest request);
	
	public abstract void clearSession(HttpServletRequest request);
	
	//http url 요청
	public JSONObject httpUrlRequest(String apiURL, HashMap<String, String> reqHeader) throws IOException, ParseException {
		
		URL url = new URL(apiURL);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		
		if (reqHeader != null) {
			Iterator<String> ir = reqHeader.keySet().iterator();
			while(ir.hasNext()) {
				String key = ir.next();
				String value = reqHeader.get(key);
				conn.setRequestProperty(key, value);
			}
		}
		
		int statusCode = conn.getResponseCode();
		InputStream in;
		if (statusCode == HttpURLConnection.HTTP_OK) {
			
			in = conn.getInputStream();
		} else {
			
			in = conn.getErrorStream();
		}
		
		StringBuilder sb = new StringBuilder();
		InputStreamReader isr = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(isr);
		 
		String data;
		while((data = br.readLine()) != null) {
			sb.append(data);
		}
		
		br.close();
		isr.close();
		in.close();

		JSONObject json = (JSONObject)new JSONParser().parse(sb.toString());
		
		return json;
	}
	
	public JSONObject httpUrlRequest(String apiURL) throws IOException, ParseException {
		return httpUrlRequest(apiURL, null);
	}


	

	

	

	
	
	
	
	
}
