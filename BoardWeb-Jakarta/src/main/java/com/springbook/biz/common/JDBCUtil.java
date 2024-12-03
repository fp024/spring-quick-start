package com.springbook.biz.common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import org.apache.ibatis.io.Resources;

/**
 * page 117
 *
 * @author fp024
 *     <p>(참고) 임베디드 모드에서 다른 툴에서 이미 접속되어있으면 중복 접속 허용이 안되는 것 같다.
 */
public class JDBCUtil {
  private JDBCUtil() {
    // 유틸리티 객체 생성 방지
  }

  public static Properties getDBProperties() {
    Properties properties = new Properties();

    try (InputStream reader = Resources.getResourceAsStream("config/database.properties"); ) {
      properties.load(reader);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
    return properties;
  }

  public static Connection getConnection() {

    try {
      Properties dbProperties = getDBProperties();
      // JDBC 4.0 (JDK6)에서는 classpath 에서 이용가능한 드라이버들을 자동으로 로드해준다고 한다.
      // 그런데, Model 1 방식 jsp로 확인할 때는 아래 코드가 필요함.
      Class.forName(dbProperties.getProperty("jdbc.driver"));
      // DB생성은 윈도우 퀵런치 H2아이콘 오른 쪽 메뉴로 생성가능
      return DriverManager.getConnection(
          dbProperties.getProperty("jdbc.url"),
          dbProperties.getProperty("jdbc.username"),
          dbProperties.getProperty("jdbc.password")); // h2 기본 사용시 sa에 대한 사용자명을 반드시 넣어야함.
      /*
      	유저를 새로 만들 수도 있음.
      CREATE USER IF NOT EXISTS board PASSWORD 'board' ADMIN;
      */
    } catch (Exception e) {
      e.getStackTrace();
    }
    return null;
  }

  public static void close(PreparedStatement stmt, Connection conn) {
    if (stmt != null) {
      try {
        if (!stmt.isClosed()) {
          stmt.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    if (conn != null) {
      try {
        if (!conn.isClosed()) {
          conn.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
    if (rs != null) {
      try {
        if (!rs.isClosed()) {
          rs.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    close(stmt, conn);
  }
}
