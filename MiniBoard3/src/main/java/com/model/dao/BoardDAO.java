package com.model.dao;

import javax.servlet.http.HttpServletRequest;
import com.core.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import com.model.dto.Board;

/**
 * 게시판 DAO
 *
 */
public class BoardDAO {
	/**
	 * 게시글 작성 
	 * 
	 * @param request
	 * @return 게시글 작성 성공시 등록된 게시글 번호(0이면 실패...)
	 */
	public int write(HttpServletRequest request) {
		int idx = 0;
		String sql = "INSERT INTO board (poster, subject, content) VALUES(?, ?, ?)";
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
			if (result > 0) { // 게시글 등록이 잘 된 경우 
				ResultSet rs = pstmt.getGeneratedKeys();
				/**
				 *  // .next() -> 다음 투플로 있으면 true, 이동
				 *  // .getXxx(속성 순서 번호(1~), 속성명)
				 */
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
	
	/**
	 * 게시글 수정
	 * 
	 * @param request
	 * @return
	 */
	public boolean edit(HttpServletRequest request) {
		
		if (request == null)
			return false;
		
		String sql = "UPDATE board SET poster = ?, subject = ?,  content = ? WHERE idx = ?";
		try (Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			request.setCharacterEncoding("UTF-8");
			int idx = Integer.parseInt(request.getParameter("idx"));
			pstmt.setString(1, request.getParameter("poster"));
			pstmt.setString(2, request.getParameter("subject"));
			pstmt.setString(3, request.getParameter("content"));
			pstmt.setInt(4, idx);
			
			int rs = pstmt.executeUpdate(); // rs 1 이상 -> 반영 성공
			if (rs > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * 게시글 조회 
	 * 
	 * @param idx
	 * @return
	 */
	public Board get(int idx) {
		Board board = null;
		String sql = "SELECT * FROM board WHERE idx = ?";
		try (Connection conn = DB.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, idx);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				board = new Board(rs);
			}
			rs.close();
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return board;
	}
	
	public ArrayList<Board> getList() {
		return getList(1, 5);
	}
	
	/**
	 * 게시글 목록
	 *  
	 * @return
	 * getList(1, 5);
	 * getList(2, 5);
	 * getList(3, 5);
	 * LIMIT 시작할 위치, 추출할 갯수 
	 * 0, 1, 2, 3, 4  - 1페이지 (1 - 1) * 5 -> 0  
	 * 5, 6, 7, 8, 9  - 2페이지 (2  - 1)* 5 -> 5
	 * 10, 11, 12, 13, 14 - 3페이지  (3 - 1) * 5 -> 10
	 */
	public ArrayList<Board> getList(int page, int limit) {
		ArrayList<Board> list = new ArrayList<>();
		
		page = (page == 0)?1:page;
		limit = (limit == 0)?5:limit;
		
		int offset = (page - 1) * limit; // 시작 지점 
		
		String sql = "SELECT * FROM board ORDER BY idx DESC LIMIT ?, ?";
		try(Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, offset);
			pstmt.setInt(2, limit);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) { // 다음 투플이 있으면 true -> 다음으로 이동
				list.add(new Board(rs));
			}
			rs.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 게시글 삭제 
	 * 
	 * @param idx 게시글 번호
	 * @return true - 삭제 성공, false - 삭제 실패
	 */
	public boolean delete(int idx) {
		
		String sql = "DELETE FROM board WHERE idx = ?";
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