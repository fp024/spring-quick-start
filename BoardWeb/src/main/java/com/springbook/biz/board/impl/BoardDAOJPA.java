package com.springbook.biz.board.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.google.common.base.Strings;
import com.springbook.biz.board.BoardVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class BoardDAOJPA {
  @PersistenceContext private EntityManager em;

  public void insertBoard(BoardVO vo) {
    logger.info("===> JPA로 insertBoard() 기능처리");
    em.persist(vo);
  }

  /**
   * 책에서도 다른 처리 없이 merge만 하는데...
   * 일단 한번은 읽었던 상태라 이런 것인지.. 잘되긴 된다.
   */
  public void updateBoard(BoardVO vo) {
    logger.info("===> JPA로 updateBoard() 기능처리");
    em.merge(vo);
  }

  /** 아래부분에서, remove 안에 find하는 부분을 내가 누락했었다. */
  public void deleteBoard(BoardVO vo) {
    logger.info("===> JPA로 deleteBoard() 기능처리");
    em.remove(em.find(BoardVO.class, vo.getSeq()));
  }

  public BoardVO getBoard(BoardVO vo) {
    logger.info("===> JPA로 getBoard() 기능처리");
    return em.find(BoardVO.class, vo.getSeq());
  }

  /** TODO: JPQL 이나 QueryDSL 따로 공부할 때, 바꿔야겠다. */
  public List<BoardVO> getBoardList(BoardVO vo) {
    logger.info("===> JPA로 getBoardList() 기능처리");

    final String whereLikeStatement;

    if (Strings.isNullOrEmpty(vo.getSearchKeyword())) {
      whereLikeStatement = "";
    } else {
      whereLikeStatement =
          String.format(
              "WHERE b.%s LIKE %s",
              vo.getSearchCondition().toLowerCase(), "'%" + vo.getSearchKeyword() + "%'");
    }
    return em.createQuery(
            String.format("FROM BoardVO b %s ORDER BY b.seq DESC", whereLikeStatement),
            BoardVO.class)
        .getResultList();
  }
}
