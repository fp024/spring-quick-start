package org.fp024.mybatistest.board.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.fp024.mybatistest.board.BoardVO;
import org.fp024.mybatistest.board.util.SqlSessionFactoryBean;

/**
 * myBatis Generator로 자동생성된 메퍼 인터페이스를 책 예제에 맞게 클래스로 수정.
 */
public class BoardDAO {
	private static final String NAMESPACE_PREFIX = BoardDAO.class.getCanonicalName() + ".";

	private SqlSession mybatis;

	public BoardDAO() {
		mybatis = SqlSessionFactoryBean.getSqlSessionInstance();
	}

	public void insert(BoardVO vo) {
		mybatis.insert(NAMESPACE_PREFIX + "insertBoard", vo);
		mybatis.commit();
	}

	public void updateBoard(BoardVO vo) {
		mybatis.update(NAMESPACE_PREFIX + "updateBoard", vo);
		mybatis.commit();
	}

	public void deleteBoard(Integer seq) {
		mybatis.delete(NAMESPACE_PREFIX + "deleteBoard", seq);
	}

	public BoardVO getBoard(Integer seq) {
		return mybatis.selectOne(NAMESPACE_PREFIX + "getBoard", seq);
	}

	public List<BoardVO> getBoardList(BoardVO vo) {
		return mybatis.selectList(NAMESPACE_PREFIX + "getBoardList", vo);
	}

}