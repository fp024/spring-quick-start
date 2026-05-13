package org.fp024.mybatistest.board.impl.generated;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.ibatis.session.SqlSession;
import org.fp024.mybatistest.board.generated.BoardVO;
import org.fp024.mybatistest.board.util.SqlSessionFactoryBean;
import org.fp024.mybatistest.test.DatabaseBeforeEachInit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

@ExtendWith(DatabaseBeforeEachInit.class)
class BoardMapperTests {

  @Test
  void mapperTest() {
    try (SqlSession session = SqlSessionFactoryBean.getSqlSessionInstance()) {
      BoardMapper boardMapper = session.getMapper(BoardMapper.class);

      BoardVO vo = new BoardVO();
      vo.setTitle("Mapper 테스트 제목");
      vo.setWriter("Mapper 테스트 작성자");
      vo.setContent("Mapper 테스트 내용입니다. ...............");

      // 1. insert
      boardMapper.insert(vo);

      // 2. select all
      List<BoardVO> boardList = boardMapper.selectAll();
      assertFalse(boardList.isEmpty());

      // 3. select by primary key
      BoardVO resultVOAfterInsert = boardMapper.selectByPrimaryKey(vo.getSeq());
      assertEquals(vo.getSeq(), resultVOAfterInsert.getSeq());

      // 4. update by primary key
      resultVOAfterInsert.setContent("Mapper 테스트 내용 수정. ############");
      boardMapper.updateByPrimaryKey(resultVOAfterInsert);

      // 5. check updated value
      BoardVO resultVOAfterUpdate = boardMapper.selectByPrimaryKey(resultVOAfterInsert.getSeq());
      assertEquals(vo.getSeq(), resultVOAfterUpdate.getSeq());
      assertEquals("Mapper 테스트 내용 수정. ############", resultVOAfterUpdate.getContent());

      // 6. delete by primary key
      boardMapper.deleteByPrimaryKey(resultVOAfterUpdate.getSeq());

      BoardVO resultVOAfterDelete = boardMapper.selectByPrimaryKey(resultVOAfterUpdate.getSeq());
      assertNull(resultVOAfterDelete);
    }
  }
}
