package com.model.dao;

import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class BlogDao {
	
	public int write(HttpServletRequest request) {
		int idx = 0;
		String sql = "INSERT INTO Blog (Writer, subject, content) VALUES(?,?,?)";
		try(Connection conn = DriverManager.getConnection(sql);
			PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
		}
		
		
		
	}
}
