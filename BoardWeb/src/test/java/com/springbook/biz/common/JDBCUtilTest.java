package com.springbook.biz.common;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class JDBCUtilTest {

  @Test
  public void testGetDBProperties() {
    Properties prop = JDBCUtil.getDBProperties();

    assertEquals("org.h2.Driver", prop.get("jdbc.driver"));
    assertEquals("jdbc:h2:tcp://localhost/~/h2db-data/spring-quick-start;TRACE_LEVEL_SYSTEM_OUT=0", prop.get("jdbc.url"));
    assertEquals("sa", prop.get("jdbc.username"));
    assertEquals("sa", prop.get("jdbc.password"));
  }
}
