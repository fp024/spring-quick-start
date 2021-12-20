package org.fp024.mybatistest.test;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/** DB 스키마와 데이터 입력 초기화 작업을 하는 JUnit 확장 */
public class DatabaseBeforeAllInit implements BeforeAllCallback {
  @Override
  public void beforeAll(ExtensionContext context) {
    DBUtil.runInitScript("sql/create_data.sql");
  }
}
