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
	@PersistenceContext
	private EntityManager em;

	public void insertBoard(BoardVO vo) {
		logger.info("===> JPA로 insertBoard() 기능처리");
		em.persist(vo);
	}

	public void updateBoard(BoardVO vo) {
		logger.info("===> JPA로 updateBoard() 기능처리");
		em.merge(vo);
	}

	public void deleteBoard(BoardVO vo) {
		logger.info("===> JPA로 deleteBoard() 기능처리");
		em.remove(vo);
	}

	public BoardVO getBoard(BoardVO vo) {
		logger.info("===> JPA로 getBoard() 기능처리");
		return em.find(BoardVO.class, vo.getSeq());
	}

	
	/**
	 * TODO: JPQL 이나 QueryDSL 따로 공부할 때, 바꿔야겠다.
	 */
	public List<BoardVO> getBoardList(BoardVO vo) {
		logger.info("===> JPA로 getBoardList() 기능처리");

		final String whereLikeStatement;

		if (Strings.isNullOrEmpty(vo.getSearchKeyword())) {
			whereLikeStatement = "";
		} else {
			whereLikeStatement = String.format("WHERE b.%s LIKE %s", vo.getSearchCondition().toLowerCase(),
					"'%" + vo.getSearchKeyword() + "%'");
		}
		return em.createQuery(String.format("FROM BoardVO b %s ORDER BY b.seq DESC", whereLikeStatement), BoardVO.class)
				.getResultList();
	}
}
