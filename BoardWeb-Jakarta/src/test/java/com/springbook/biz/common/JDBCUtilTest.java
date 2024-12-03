package com.springbook.biz.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Properties;
import org.junit.jupiter.api.Test;

class JDBCUtilTest {

  @Test
  void testGetDBProperties() {
    Properties prop = JDBCUtil.getDBProperties();

    assertEquals("org.h2.Driver", prop.get("jdbc.driver"));
    assertEquals(
        "jdbc:h2:mem:spring-boot-quick-start-jakarta;DB_CLOSE_DELAY=-1", prop.get("jdbc.url"));
    assertEquals("sa", prop.get("jdbc.username"));
    assertEquals("sa", prop.get("jdbc.password"));
  }
}
