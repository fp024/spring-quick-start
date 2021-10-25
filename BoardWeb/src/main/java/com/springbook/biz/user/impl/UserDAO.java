package com.springbook.biz.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.springbook.biz.common.JDBCUtil;
import com.springbook.biz.user.UserVO;

import lombok.extern.slf4j.Slf4j;

/** @author fp024 */
@Slf4j
@Repository
public class UserDAO {
  /** JDBC 관련 변수 */
  private Connection conn;

  private PreparedStatement stmt;
  private ResultSet rs;

  /** SQL 명령어들... */
  private static final String USER_GET = "SELECT * FROM users WHERE id = ? AND password = ? ";

  /** CRUD 기능의 메서드 구현 */
  public UserVO getUser(UserVO vo) {
    try {
      logger.info("===> JDBC로 getUser() 기능 처리");
      conn = JDBCUtil.getConnection();
      stmt = conn.prepareStatement(USER_GET);
      stmt.setString(1, vo.getId());
      stmt.setString(2, vo.getPassword());
      rs = stmt.executeQuery();

      if (rs.next()) {
        UserVO user = new UserVO();
        user.setId(rs.getString("ID"));
        user.setPassword(rs.getString("PASSWORD"));
        user.setName(rs.getString("NAME"));
        user.setRole(rs.getString("U_ROLE"));
        return user;
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      JDBCUtil.close(rs, stmt, conn);
    }
    return null;
  }
}
