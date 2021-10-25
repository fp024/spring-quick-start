package com.springbook.biz.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("boardDAOSpring")
public class BoardDAOSpring {
  /** SQL 명령어 들... */
  @SuppressWarnings("unused")
  private static final String BOARD_INSERT =
      "INSERT INTO BOARD (seq, title, writer, content) VALUES("
          + "(SELECT NVL(MAX(Seq),0)+1 FROM board) "
          + ",?,?,?)";

  private static final String BOARD_INSERT2 =
      "INSERT INTO BOARD (seq, title, writer, content) VALUES("
          + "?"
          + ",?,?,?)";

  private static final String BOARD_UPDATE =
      "UPDATE board "
    + "   SET title=? "
    + "       content=? "
    + " WHERE seq=? ";

  private static final String BOARD_DELETE =
      "DELETE board "
    + " WHERE seq=?";

  private static final String BOARD_GET = 
      "SELECT * "
    + "  FROM board"
    + " WHERE seq=?";

  private static final String BOARD_LIST_T =
      "SELECT * "
    + "  FROM board "
    + " WHERE title LIKE '%' || ? || '%' "
    + " ORDER BY seq DESC ";

  private static final String BOARD_LIST_C =
      "SELECT * "
    + "  FROM board "
    + " WHERE content LIKE '%' || ? || '%' "
    + " ORDER BY seq DESC ";

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public BoardDAOSpring(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  /** CRUD 기능의 메서드 구현 */
  /** 글등록 */
  public void insertBoard(BoardVO vo) {
    logger.info("===> Spring JDBC로 insertBoard() 기능 처리");
    jdbcTemplate.update(BOARD_INSERT, vo.getTitle(), vo.getWriter(), vo.getContent());
  }

  /** 글 수정 */
  public void updateBoard(BoardVO vo) {
    logger.info("===> Spring JDBC로 updateBoard() 기능 처리");
    jdbcTemplate.update(BOARD_UPDATE, vo.getTitle(), vo.getContent(), vo.getSeq());
  }

  /** 글 삭제 */
  public void deleteBoard(BoardVO vo) {
    logger.info("===> Spring JDBC로 deleteBoard() 기능 처리");
    jdbcTemplate.update(BOARD_DELETE, vo.getSeq());
  }

  /** 글 상세 조회 */
  public BoardVO getBoard(BoardVO vo) {
    logger.info("===> Spring JDBC로 getBoard() 기능 처리");
    return jdbcTemplate.queryForObject(BOARD_GET, new BoardRowMapper(), vo.getSeq());
  }

  /** 글 목록 조회 */
  public List<BoardVO> getBoardList(BoardVO vo) {
    logger.info("===> Spring JDBC로 getBoardList() 기능 처리");
    if ("TITLE".equals(vo.getSearchCondition())) {
      return jdbcTemplate.query(BOARD_LIST_T, new BoardRowMapper(), vo.getSearchKeyword());
    } else if ("CONTENT".equals(vo.getSearchCondition())) {
      return jdbcTemplate.query(BOARD_LIST_C, new BoardRowMapper(), vo.getSearchKeyword());
    }
    return Collections.emptyList();
  }

  class BoardRowMapper implements RowMapper<BoardVO> {
    @Override
    public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
      BoardVO board = new BoardVO();
      board.setSeq(rs.getInt("SEQ"));
      board.setTitle(rs.getString("TITLE"));
      board.setWriter(rs.getString("WRITER"));
      board.setContent(rs.getString("CONTENT"));
      board.setRegDate(rs.getTimestamp("REGDATE").toLocalDateTime());
      board.setCnt(rs.getInt("CNT"));
      return board;
    }
  }
}
