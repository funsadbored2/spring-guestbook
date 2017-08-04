package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;
@Repository
public class GuestbookDao {

	public List<GuestbookVo> getList() {

		Connection conn = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		List<GuestbookVo> list = new ArrayList<GuestbookVo>();

		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("접속되었습니다.");

			// 3. SQL문 준비 / 바인딩 / 실행

			String query = "SELECT NO, " + "NAME, " + "CONTENT, " + "REG_DATE " + "FROM GUESTBOOK";
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			// 4.결과처리

			while (rs.next()) {

				GuestbookVo vo = new GuestbookVo();

				vo.setNo(rs.getInt("NO"));
				vo.setName(rs.getString("NAME"));
				vo.setContent(rs.getString("CONTENT"));
				vo.setRegdate(rs.getString("REG_DATE"));

				list.add(vo);
			}

		} catch (ClassNotFoundException e) {

			System.out.println("error: 드라이버 로딩 실패 - " + e);

		} catch (SQLException e) {

			System.out.println("error:" + e);

		} finally {

			// 5. 자원정리

			try {

				if (pstmt != null) {

					pstmt.close();

				}

				if (conn != null) {

					conn.close();

				}

			} catch (SQLException e) {

				System.out.println("error:" + e);

			}

		}

		return list;
	}

	public int add(GuestbookVo vo) {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("접속되었습니다.");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "INSERT INTO GUESTBOOK\r\n" + "VALUES(SEQ_BOOKGUEST1.NEXTVAL,?,?,?,SYSDATE)";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContent());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 처리");
		} catch (ClassNotFoundException e) {
			System.out.println("error:드라이브 로딩 실패" + e);
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error: " + e);
			}
			// 5. 자원정리

		}
		return count;
	}

	public void delete(String pw, int no) {

		Connection conn = null;

		PreparedStatement pstmt = null;

		int num = no;
		try {

			// 1. JDBC 드라이버 (Oracle) 로딩

			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("접속되었습니다.");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "DELETE FROM GUESTBOOK " + "WHERE NO = ?" + " AND PASSWORD = ?";
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, num);
			pstmt.setString(2, pw);
			int count = pstmt.executeUpdate();

			System.out.println(count + "건 처리");
			// 4.결과처리

		} catch (ClassNotFoundException e) {

			System.out.println("error: 드라이버 로딩 실패 - " + e);

		} catch (SQLException e) {

			System.out.println("error: " + e);

		} finally {

			// 5. 자원정리

			try {

				if (pstmt != null) {

					pstmt.close();

				}

				if (conn != null) {

					conn.close();

				}

			} catch (SQLException e) {

				System.out.println("error:" + e);

			}
		}

	}
	public int update(GuestbookVo vo) {
		
		Connection conn = null;

		PreparedStatement pstmt = null;

		int count = -1;

		int no = vo.getNo();
		String name = vo.getName();
		String pw = vo.getPassword();
		String con = vo.getContent();

		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("접속되었습니다");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "UPDATE GUESTBOOK " + "SET NAME = ? " + ",PASSWORD= ? " + ",CONTENT= ? WHERE NO = ?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, name);
			pstmt.setString(2, pw);
			pstmt.setString(3, con);
			pstmt.setInt(4, no);
			count = pstmt.executeUpdate();
			System.out.println(count + "건 처리");
			// 4.결과처리

		} catch (ClassNotFoundException e) {

			System.out.println("error: 드라이버 로딩 실패 - " + e);

		} catch (SQLException e) {

			System.out.println("error:" + e);

		} finally {

			// 5. 자원정리

			try {

				if (pstmt != null) {

					pstmt.close();

				}

				if (conn != null) {

					conn.close();

				}

			} catch (SQLException e) {

				System.out.println("error:" + e);

			}

		}

		return count;
		
	}

}
