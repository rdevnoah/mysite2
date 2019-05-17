package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.exception.UserDaoException;
import com.cafe24.mysite.vo.UserVo;


@Repository
public class UserDao {
	
	@Autowired
	private DataSource dataSource;

	public UserVo update(UserVo vo) {
		UserVo result = null;
		Connection conn = null;

		PreparedStatement pstmt = null;
		try {

			conn = dataSource.getConnection();

			String sql = "update user set name=?, gender=? where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getGender());
			pstmt.setLong(3, vo.getNo());
			
			

			pstmt.executeUpdate();
			result = vo;

		} catch (SQLException e) {
			throw new UserDaoException(e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
		
	}
	
	public UserVo get(Long no) {
		UserVo result = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {

			conn = dataSource.getConnection();
			String sql = "select no, name, email, gender from user where no=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			

			rs = pstmt.executeQuery();

			if (rs.next()) {
				Long userNo = rs.getLong(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String gender = rs.getString(4);
				result = new UserVo();
				result.setNo(userNo);
				result.setName(name);
				result.setEmail(email);
				result.setGender(gender);
				
				
			}

		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error : " + e);
			}
		}

		return result;
		
	}
	
	public UserVo get(UserVo userVo) throws UserDaoException {
		UserVo result = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {

			conn = dataSource.getConnection();
			String sql = "select no, name from user where email=? and password=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userVo.getEmail());
			pstmt.setString(2, userVo.getPassword());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				result = new UserVo();
				result.setNo(no);
				result.setName(name);
				
			}

		} catch (SQLException e) {
			throw new UserDaoException(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error : " + e);
			}
		}

		return result;
	}
	
	
	public Boolean insert(UserVo vo) {
		boolean result = false;
		Connection conn = null;

		PreparedStatement pstmt = null;
		try {

			conn = dataSource.getConnection();

			String sql = "insert into user values(null, ?, ?, ?, ?, now())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			
			

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	
	
}
