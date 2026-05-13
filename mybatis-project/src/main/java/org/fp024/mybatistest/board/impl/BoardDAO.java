package org.fp024.mybatistest.board.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.fp024.mybatistest.board.BoardDTO;
import org.fp024.mybatistest.board.generated.BoardVO;
import org.fp024.mybatistest.board.util.SqlSessionFactoryBean;

/** myBatis Generator로 자동생성된 메퍼 인터페이스를 책 예제에 맞게 클래스로 수정. */
public class BoardDAO {
  private static final String NAMESPACE_AUTO_GENERATED_PREFIX =
      namespace(org.fp024.mybatistest.board.impl.generated.BoardMapper.class);
  private static final String NAMESPACE_PREFIX = namespace(BoardDAO.class);

  private static String namespace(Class<?> mapperClass) {
    return mapperClass.getName() + ".";
  }

  private final SqlSession mybatis;

  public BoardDAO() {
    mybatis = SqlSessionFactoryBean.getSqlSessionInstance();
  }

  public void insert(BoardVO vo) {
    mybatis.insert(NAMESPACE_AUTO_GENERATED_PREFIX + "insert", vo);
    mybatis.commit();
  }

  public void updateBoard(BoardVO vo) {
    mybatis.update(NAMESPACE_AUTO_GENERATED_PREFIX + "update", vo);
    mybatis.commit();
  }

  public void deleteBoard(Integer seq) {
    mybatis.delete(NAMESPACE_AUTO_GENERATED_PREFIX + "deleteByPrimaryKey", seq);
  }

  public BoardVO getBoard(Integer seq) {
    return mybatis.selectOne(NAMESPACE_AUTO_GENERATED_PREFIX + "selectByPrimaryKey", seq);
  }

  public List<BoardVO> getBoardList(BoardDTO vo) {
    return mybatis.selectList(NAMESPACE_PREFIX + "getBoardList", vo);
  }
}
