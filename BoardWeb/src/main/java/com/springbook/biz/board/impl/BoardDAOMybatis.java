package com.springbook.biz.board.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class BoardDAOMybatis extends SqlSessionDaoSupport {
	
	/**
	 * SqlSessionDaoSupport 의 요구 사항.
	 * 	Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required
	 */
	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	private static final String NAMESPACE_PREFIX = BoardDAOMybatis.class.getCanonicalName() + ".";

	public void insertBoard(BoardVO vo) {
		logger.info("===> Mybatis로 insertBoard() 기능처리");
		getSqlSession().insert(NAMESPACE_PREFIX + "insertBoard", vo);
	}

	public void updateBoard(BoardVO vo) {
		logger.info("===> Mybatis로 updateBoard() 기능처리");
		getSqlSession().update(NAMESPACE_PREFIX + "updateBoard", vo);
	}

	public void deleteBoard(BoardVO vo) {
		logger.info("===> Mybatis로 deleteBoard() 기능처리");
		getSqlSession().delete(NAMESPACE_PREFIX + "deleteBoard", vo);
	}

	public BoardVO getBoard(BoardVO vo) {
		logger.info("===> Mybatis로 getBoard() 기능처리");
		return getSqlSession().selectOne(NAMESPACE_PREFIX + "getBoard", vo);
	}

	public List<BoardVO> getBoardList(BoardVO vo) {
		logger.info("===> Mybatis로 getBoardList() 기능처리");
		return getSqlSession().selectList(NAMESPACE_PREFIX + "getBoardList", vo);
	}

}
