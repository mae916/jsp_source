package com.snslogin;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;

public class NaverLogin extends SocialLogin {
	
	private static String clientId;
	private static String clientSecret;
	private static String callbackURL;
	
	public static void init(FilterConfig config) throws UnsupportedEncodingException {
		init(
			config.getInitParameter("NaverClientId"),
			config.getInitParameter("NaverClientSecret"),
			config.getInitParameter("NaverCallbackURL")
		);
	}
	
	public static void init(String clientId, String clientSecret, String callbackUrl) {
		NaverLogin.clientId = clientId;
		NaverLogin.clientSecret = clientSecret;
		NaverLogin.callbackURL = callbackUrl;
	}

	@Override
	public String getCodeURL(HttpServletRequest request) {
		long state = System.currentTimeMillis();
		
		StringBuilder sb = new StringBuilder();
		sb.append("https://nid.naver.com/oauth2.0/authorize?");
		sb.append("response_type=code");
		sb.append("&client_id=");
		sb.append(clientId);
		sb.append("&redirect_uri=");
		sb.append(callbackURL);
		sb.append("state=");
		sb.append(state);
		
		return sb.toString();
	}

	@Override
	public String getAccessToken(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAccessToken(HttpServletRequest request, String code, String state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> getUserProfile(String accessToken) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
