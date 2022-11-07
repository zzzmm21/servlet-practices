package com.bitacademy.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bitacademy.guestbook.vo.GuestbookVo;



public class GuestbookDao {

	public Boolean insert(GuestbookVo vo) {
		boolean result = false;
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			//1. JDBC Driver Class Loading
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. Statement 생성
			stmt = conn.createStatement();
			
			//4. SQL 실행
			String sql = 
				" insert" +
				"   into guestbook" +
				" values (null, '" + vo.getName() + "', '" + vo.getPassword() + "', '" + vo.getContents() +"', '"+ vo.getReg_date() +"')";
			
			int count = stmt.executeUpdate(sql);
			
			//5. 결과 처리
			result = count == 1;
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public List<GuestbookVo> findAll() {
		List<GuestbookVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = 
				"   select no, name, contents , date_format(reg_date, '%Y/%m/%d %H:%i:%s')"+"from guestbook";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			//6. 결과(ResultSet) 처리
			while(rs.next()) {
				int no = (int) rs.getLong(1);
				String name = rs.getString(2);
				String password = rs.getString(3);
				String contents = rs.getString(4);
				
				GuestbookVo vo = new GuestbookVo();
				vo.setName(name);
				vo.setPassword(password);
				vo.setContents(contents);
				vo.setNo(no);
				
				result.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
		return result;
	}
	public boolean updateStatus(long no ,String status) {
		boolean result = false;
		System.out.println(result ? "성공" : "실패");
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			
			conn = getConnection();
			
			//3. Statement 생성
			String sql = 
					"update book" +
					"   set stauts = ?" +
				    " where no = ?";
				
			pstmt = conn.prepareStatement(sql);
			
			//4. SQL 실행
			pstmt.setString(1,status);
			pstmt.setLong(2, no);
			
			int count = pstmt.executeUpdate();
			
			result = count == 1;
			

		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("Class Not Found:" + e);
		} 
		
		return conn;
	}

	
}