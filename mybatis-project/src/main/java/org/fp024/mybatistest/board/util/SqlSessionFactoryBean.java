package org.fp024.mybatistest.board.util;

import java.io.Reader;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SqlSessionFactoryBean {
  private static SqlSessionFactory sessionFactory;

  // 💡 static 블록에서 클래스 초기화 시 한 번 생성되므로, 정상 초기화 후에는 null 검사가 필요 없음
  static {
    try {
      Reader reader = Resources.getResourceAsReader("sql-map-config.xml");
      sessionFactory = new SqlSessionFactoryBuilder().build(reader);

    } catch (Exception e) {
      logger.error("Failed to initialize SqlSessionFactory", e);
    }
  }

  public static SqlSession getSqlSessionInstance() {
    return sessionFactory.openSession();
  }
}
