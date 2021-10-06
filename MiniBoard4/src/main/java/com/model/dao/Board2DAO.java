package com.model.dao;

import java.util.*;
import java.sql.*;
import com.core.*;
import com.model.dto.Board;



public class Board2DAO {
	
	public ArrayList<Board>getList() { //페이지가 없을때
	return getList(1,5);	
	}
	
	public ArrayList<Board>getList(int page) {
	return getList(page,5);	
	}
	
	/**
	 * 
	 * 
	 * @param page 페이지 번호
	 * 						예) 한 페이지당 글의 개수가 5개면
	 * 							1페이지 -> 1~5
	 * 							2페이지 -> 6~10
	 * 							3페이지 -> 11~15
	 * @param limit
	 * @return
	 */
	public ArrayList<Board>getList(int page, int limit) {
		ArrayList<Board>list = new ArrayList<>();
		
		/**
		 * LIMIT 숫자(시작지점), 숫자(투플의 갯수)
		 * LIMIT 숫자(투플의 갯수) == LIMIT 0, 숫자(투플의 갯수)
		 */
		/**
		 * 게시물 5개
		 * page = 1 --> 0부터 시작(인덱스 번호)
		 * page = 2 --> 5부터 시작
		 * 
		 */
		
		page = (page <= 0)?1:page;
		limit = (limit <= 0)?5:limit;
		int offset = (page -1) * limit;
		String sql = "SELECT * FROM board ORDER BY idx DESC LIMIT ?, ?";
		try(Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, offset);
			pstmt.setInt(2, limit);
			
			/**
			 * SELECT - executeQuery(); -> ResultSet을 반환
			 * DELETE, INSERT, UPDATE - executeUpdate(); -> int(반영된 투플의 갯수) 를 반환
			 */
			
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) { // 순회함.
			list.add(new Board(rs)); // 생성후 바로 넣어줌.
			
		}
			
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
}
