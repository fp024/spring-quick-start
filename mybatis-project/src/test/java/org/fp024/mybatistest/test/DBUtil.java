package org.fp024.mybatistest.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtil {
  private static final Logger LOGGER = LoggerFactory.getLogger(DBUtil.class);

  public static Properties getDBProperties() {
    Properties properties = new Properties();

    try (InputStream reader = Resources.getResourceAsStream("db.properties")) {
      properties.load(reader);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
    return properties;
  }

  public static Connection getConnection() {
    Properties dbProps = getDBProperties();

    Connection conn;
    try {
      Class.forName(dbProps.getProperty("jdbc.driverClassName"));
      String url = dbProps.getProperty("jdbc.url");
      conn =
          DriverManager.getConnection(
              url, dbProps.getProperty("jdbc.username"), dbProps.getProperty("jdbc.password"));
    } catch (Exception e) {
      throw new IllegalStateException("connection create fail.", e);
    }
    return conn;
  }

  public static void runInitScript(String resourcePath) {
    Connection conn = getConnection();
    ScriptRunner scriptRunner = new ScriptRunner(conn);
    scriptRunner.setAutoCommit(true);
    try {
      Resources.setCharset(StandardCharsets.UTF_8);
      scriptRunner.runScript(Resources.getResourceAsReader(resourcePath));

    } catch (IOException e) {
      throw new IllegalStateException(resourcePath + " execute fail.", e);
    } finally {
      try {
        conn.close();
      } catch (Exception e) {
        LOGGER.error("connection close fail {}", e.getMessage(), e);
      }
    }
  }
}
