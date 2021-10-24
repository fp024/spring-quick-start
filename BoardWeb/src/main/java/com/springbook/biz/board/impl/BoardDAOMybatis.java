package com.springbook.biz.board.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class BoardDAOMybatis {

  private final SqlSessionTemplate sqlSessionTemplate;

  @Autowired
  public BoardDAOMybatis(SqlSessionTemplate sqlSessionTemplate) {
    this.sqlSessionTemplate = sqlSessionTemplate;
  }

  private static final String NAMESPACE_PREFIX = BoardDAOMybatis.class.getCanonicalName() + ".";

  public void insertBoard(BoardVO vo) {
    logger.info("===> Mybatis로 insertBoard() 기능처리");
    sqlSessionTemplate.insert(NAMESPACE_PREFIX + "insertBoard", vo);
  }

  public void updateBoard(BoardVO vo) {
    logger.info("===> Mybatis로 updateBoard() 기능처리");
    sqlSessionTemplate.update(NAMESPACE_PREFIX + "updateBoard", vo);
  }

  public void deleteBoard(BoardVO vo) {
    logger.info("===> Mybatis로 deleteBoard() 기능처리");
    sqlSessionTemplate.delete(NAMESPACE_PREFIX + "deleteBoard", vo);
  }

  public BoardVO getBoard(BoardVO vo) {
    logger.info("===> Mybatis로 getBoard() 기능처리");
    return sqlSessionTemplate.selectOne(NAMESPACE_PREFIX + "getBoard", vo);
  }

  public List<BoardVO> getBoardList(BoardVO vo) {
    logger.info("===> Mybatis로 getBoardList() 기능처리");
    return sqlSessionTemplate.selectList(NAMESPACE_PREFIX + "getBoardList", vo);
  }
}
