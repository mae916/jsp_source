package com.model.dao;

import javax.servlet.http.HttpServletRequest;
import com.core.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import com.model.dto.Blog;

public class BlogDAO {
	public int write(HttpServletRequest request) {
		int idx = 0;
		String sql = "INSERT INTO Blog (poster, subject, content) VALUES(?, ?, ?)";
		try(Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			request.setCharacterEncoding("UTF-8");
			String poster = request.getParameter("poster");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			pstmt.setString(1, poster);
			pstmt.setString(2, subject);
			pstmt.setString(3, content);
			
			int result = pstmt.executeUpdate();
			if (result > 0) {
				ResultSet rs = pstmt.getGeneratedKeys();

				if (rs.next()) {
					idx = rs.getInt(1);
				}
				
				rs.close();
			}
		} catch (IOException | SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			
		}
		return idx;
	}
	
	public Blog get(int idx) {
		Blog Blog = null;
		String sql = "SELECT * FROM Blog WHERE idx = ?";
		try (Connection conn = DB.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, idx);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Blog = new Blog(rs);
			}
			rs.close();
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return Blog;
	}
	
	public ArrayList<Blog> getList() {
		ArrayList<Blog> list = new ArrayList<>();
		
		String sql = "SELECT * FROM Blog ORDER BY idx DESC";
		try(Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) { 
				list.add(new Blog(rs));
			}
			rs.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public boolean delete(int idx) {
		
		String sql = "DELETE FROM Blog WHERE idx = ?";
		try (Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, idx);
			
			int rs = pstmt.executeUpdate();
			if (rs > 0) 
				return true;
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
