package com.springbook.biz.board;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardServiceClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(BoardServiceClient.class);

	public static void main(String[] args) {
		// EntityManager 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-project");
		EntityManager em = emf.createEntityManager();

		// Transaction 생성
		EntityTransaction tx = em.getTransaction();

		try {
			// Transaction 시작
			tx.begin();

			Board board = new Board();
			board.setTitle("JPA 제목");
			board.setWriter("관리자");
			board.setContent("JPA 글 등록 잘 되네요.");

			// 글 등록
			em.persist(board);

			// 글 목록 조회
			String jpql = "SELECT b FROM Board b ORDER by b.seq DESC";
			List<Board> boardList = em.createQuery(jpql, Board.class).getResultList();

			boardList.forEach(b -> LOGGER.info("---> {}", b));
			
			// Transaction 커밋
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}

}
