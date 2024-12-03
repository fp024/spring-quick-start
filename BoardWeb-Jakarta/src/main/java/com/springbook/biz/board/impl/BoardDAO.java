package com.springbook.biz.board.impl;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * DAO (Data Access Object)
 *
 * @author fp024
 */
@Slf4j
@Repository("boardDAO")
public class BoardDAO {
  /** JDBC 관련 변수 */
  private Connection conn;

  private PreparedStatement stmt;
  private ResultSet rs;

  /** SQL 명령어 들... */
  private static final String BOARD_INSERT =
      "INSERT INTO BOARD (seq, title, writer, content) VALUES("
          + "(SELECT NVL(MAX(Seq),0)+1 FROM board) "
          + ",?,?,?)";

  private static final String BOARD_UPDATE =
      "UPDATE board " + "   SET title=? " + "     , content=? " + " WHERE seq=? ";

  private static final String BOARD_DELETE = "DELETE board " + " WHERE seq=?";

  private static final String BOARD_GET = "SELECT * " + "  FROM board" + " WHERE seq=?";

  private static final String BOARD_LIST_T =
      "SELECT * " + "  FROM board " + " WHERE title LIKE '%' || ? || '%' " + " ORDER BY seq DESC ";

  private static final String BOARD_LIST_C =
      "SELECT * "
          + "  FROM board "
          + " WHERE content LIKE '%' || ? || '%' "
          + " ORDER BY seq DESC ";

  /** CRUD 기능의 메서드 구현 */
  /** 글등록 */
  public void insertBoard(BoardVO vo) {
    logger.info("===> JDBC로 insertBoard() 기능 처리");

    try {
      conn = JDBCUtil.getConnection();
      stmt = conn.prepareStatement(BOARD_INSERT);
      stmt.setString(1, vo.getTitle());
      stmt.setString(2, vo.getWriter());
      stmt.setString(3, vo.getContent());
      stmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      JDBCUtil.close(stmt, conn);
    }
  }

  /** 글 수정 */
  public void updateBoard(BoardVO vo) {
    logger.info("===> JDBC로 updateBoard() 기능 처리");

    try {
      conn = JDBCUtil.getConnection();
      stmt = conn.prepareStatement(BOARD_UPDATE);
      stmt.setString(1, vo.getTitle());
      stmt.setString(2, vo.getContent());
      stmt.setInt(3, vo.getSeq());
      stmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      JDBCUtil.close(stmt, conn);
    }
  }

  /** 글 삭제 */
  public void deleteBoard(BoardVO vo) {
    logger.info("===> JDBC로 deleteBoard() 기능 처리");

    try {
      conn = JDBCUtil.getConnection();
      stmt = conn.prepareStatement(BOARD_DELETE);
      stmt.setInt(1, vo.getSeq());
      stmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      JDBCUtil.close(stmt, conn);
    }
  }

  /** 글 상세 조회 */
  public BoardVO getBoard(BoardVO vo) {
    logger.info("===> JDBC로 getBoard() 기능 처리");

    try {
      conn = JDBCUtil.getConnection();
      stmt = conn.prepareStatement(BOARD_GET);
      stmt.setInt(1, vo.getSeq());
      rs = stmt.executeQuery();

      if (rs.next()) {
        BoardVO board = new BoardVO();
        board.setSeq(rs.getInt("SEQ"));
        board.setTitle(rs.getString("TITLE"));
        board.setWriter(rs.getString("WRITER"));
        board.setContent(rs.getString("CONTENT"));
        board.setRegDate(
            rs.getTimestamp("REGDATE").toLocalDateTime()); // java.sql.Date는 java.util.Date의 서브타입이다.
        board.setCnt(rs.getInt("CNT"));
        return board;
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      JDBCUtil.close(rs, stmt, conn);
    }
    return null;
  }

  /** 글 목록 조회 */
  public List<BoardVO> getBoardList(BoardVO vo) {
    logger.info("===> JDBC로 getBoardList() 기능 처리");
    List<BoardVO> boardList = new ArrayList<>();

    try {

      conn = JDBCUtil.getConnection();

      if ("TITLE".equals(vo.getSearchCondition())) {
        stmt = conn.prepareStatement(BOARD_LIST_T);
      } else if ("CONTENT".equals(vo.getSearchCondition())) {
        stmt = conn.prepareStatement(BOARD_LIST_C);
      }
      stmt.setString(1, vo.getSearchKeyword());
      rs = stmt.executeQuery();

      while (rs.next()) {
        BoardVO board = new BoardVO();
        board.setSeq(rs.getInt("SEQ"));
        board.setTitle(rs.getString("TITLE"));
        board.setWriter(rs.getString("WRITER"));
        board.setContent(rs.getString("CONTENT"));
        board.setRegDate(
            rs.getTimestamp("REGDATE").toLocalDateTime()); // java.sql.Date는 java.util.Date의 서브타입이다.
        board.setCnt(rs.getInt("CNT"));
        boardList.add(board);
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      JDBCUtil.close(rs, stmt, conn);
    }
    return boardList;
  }
}
