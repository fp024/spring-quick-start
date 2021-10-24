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
   *  이전에 이부분 확인을 안했었나?
   *  수정과 삭제시, 이 시점에 BoardVO 영속화된 상태여야하는데,
   *  그게 아니여서, 동작을 하지 않고 있었다. 
   *  일단 대상을 찾아와서 영속화한 다음, 그것의 값을 바꿔주는 코드 추가 
   *  
   *  TODO: 책에서도 이랬었는지 봐야할 것 같다.
   */
  public void updateBoard(BoardVO vo) {
    logger.info("===> JPA로 updateBoard() 기능처리");
    BoardVO found = em.find(BoardVO.class, vo.getSeq());
    found.setTitle(vo.getTitle());
    found.setContent(vo.getContent());
    em.merge(found);
  }

  /**
   *  이전에 이부분 확인을 안했었나?
   *  수정과 삭제시, 이 시점에 BoardVO 영속화된 상태여야하는데,
   *  그게 아니여서, 동작을 하지 않고 있었다.
   *  일단 대상을 찾아와서 영속화한 다음, 그것을 삭제하는 코드 추가 
   *  
   *  TODO: 책에서도 이랬었는지 봐야할 것 같다.
   */  
  public void deleteBoard(BoardVO vo) {
    logger.info("===> JPA로 deleteBoard() 기능처리");
    BoardVO found = em.find(BoardVO.class, vo.getSeq());
    em.remove(found);
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
