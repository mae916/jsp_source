package com.snslogin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.core.DB;

import com.models.dto.Member;

public class NaverLogin extends SocialLogin {
	
	private static String clientId;
	private static String clientSecret;
	private static String callbackUrl;
	

	public static void init(String clientId, String clientSecret, String callbackUrl) throws UnsupportedEncodingException {
		NaverLogin.clientId = clientId;
		NaverLogin.clientSecret = clientSecret;
		NaverLogin.callbackUrl = callbackUrl = URLEncoder.encode(callbackUrl, "UTF-8");
	}
	
	public static void init(FilterConfig config) throws UnsupportedEncodingException {
		init(
		config.getInitParameter("NaverClientId"),
		config.getInitParameter("NaverClientSecret"),
		config.getInitParameter("NaverCallbackUrl")
		);
	}
	
	@Override
	public String getCodeUrl(HttpServletRequest request) {
		long state = System.currentTimeMillis();
		HttpSession session = request.getSession();
		session.setAttribute("state", state);
		
		StringBuilder sb = new StringBuilder();
		sb.append("https://nid.naver.com/oauth2.0/authorize?response_type=code");
		sb.append("&client_id=");
		sb.append(clientId);
		sb.append("&redirect_uri=");
		sb.append(callbackUrl);
		sb.append("&state=");
		sb.append(state);
		
		return sb.toString();
	}

	@Override
	public String getAccessToken(HttpServletRequest request, String code, String state) throws Exception, IOException, ParseException {
		HttpSession session = request.getSession();
		String state2 = String.valueOf((Long)session.getAttribute("state"));
		if (!state.equals(state2)) {
			throw new Exception("데이터 변조 되었습니다");
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("https://nid.naver.com/oauth2.0/token?grant_type=authorization_code");
		sb.append("&client_id=");
		sb.append(clientId);
		sb.append("&client_secret=");
		sb.append(clientSecret);
		sb.append("&code=");
		sb.append(code);
		sb.append("&state=");
		sb.append(state);
		
		String apiURL = sb.toString();
		JSONObject json = httpUrlRequest(apiURL);
		
		String accessToken = null;
		if (json.containsKey("access_token")) {
			accessToken = (String)json.get("access_token");
		}
		
		return accessToken;
	}
	
	public String getAccessToken(HttpServletRequest request) throws Exception, IOException, ParseException {
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		
		return getAccessToken(request, code, state);
		
	}

	@Override
	public HashMap<String, String> getMemberProfile(String accessToken) {
		String apiURL = "https://openapi.naver.com/v1/nid/me";
		HashMap<String, String> reqHeader = new HashMap<>();
		reqHeader.put("Authorization", "Bearer " + accessToken);
		HashMap<String, String> memberData = null;
		
		try {
			JSONObject result = httpUrlRequest(apiURL, reqHeader);
			String resultcode = (String)result.get("resultcode");
			if (resultcode.equals("00")) {
				memberData = new HashMap<String, String>();
				JSONObject response = (JSONObject)result.get("response");
				Iterator<String> ir = response.keySet().iterator();
				while(ir.hasNext()) {
					String key = ir.next();
					String value = (String)response.get(key);
					memberData.put(key, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return memberData;
		
	}

	@Override
	public boolean isJoin(HashMap<String, String> memberData, HttpServletRequest request) {
		if (memberData == null)
			return false;
		
		HttpSession session = request.getSession();
		session.setAttribute("naverMemberData", memberData);
		
		String id = memberData.get("id");
		String channel = "Naver";
		
		String sql = "SELECT COUNT(*) cnt FROM member WHERE socialChannel = ? AND socialId = ?";
		try (Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, channel);
			pstmt.setString(2, id);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int cnt = rs.getInt("cnt");
				if (cnt > 0) {
					return true;
				}
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean login(HttpServletRequest request) {
		HttpSession session = request.getSession();
		HashMap<String, String> memberData = (HashMap<String, String>)session.getAttribute("naverMemberData");
		
		String id = memberData.get("id");
		String channel = "Naver";
		
		String sql = "SELECT memNo FROM member WHERE socialChannel = ? AND socialId = ?";
		try (Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, channel);
			pstmt.setString(2, id);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int memNo = rs.getInt("memNo");
				session.setAttribute("memNo", memNo);
				clearSession(request);
				return true;
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		clearSession(request);
		return false;
	}
	
	@Override
	public Member getSnsMemberData(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member member = null;
		if (session.getAttribute("naverMemberData") != null) {
			HashMap<String, String> memberData = (HashMap<String, String>)session.getAttribute("naverMemberData");
			String memId = null;
			String email = memberData.get("email");
			if (email != null) {
				memId = email.substring(0, email.lastIndexOf("@"));
			} else {
				memId = String.valueOf(System.currentTimeMillis());
			}
						
			member = new Member(
				0,
				memId,
				null,
				memberData.get("name"),
				"Naver",
				memberData.get("id"),
				null
			);
		}
		
		return member;
	}
	
	@Override
	public void clearSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("naverMemberData");
	}

}
