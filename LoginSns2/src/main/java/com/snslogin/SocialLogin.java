package com.snslogin;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public abstract class SocialLogin {
	
	// code 발급
	public abstract String getCodeURL(HttpServletRequest request);
	
	//access token 발급
	public abstract String getAccessToken(HttpServletRequest request);
	public abstract String getAccessToken(HttpServletRequest request, String code, String state);
	
	//회원 프로필 조회
	public abstract HashMap<String, String> getUserProfile(String accessToken);
}
