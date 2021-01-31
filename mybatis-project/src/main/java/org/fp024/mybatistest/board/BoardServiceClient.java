package org.fp024.mybatistest.board;

import java.util.List;

import org.fp024.mybatistest.board.impl.BoardDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardServiceClient {
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceClient.class);

	public static void main(String[] args) {
		BoardDAO boardDAO = new BoardDAO();

		BoardVO vo = new BoardVO();
		vo.setTitle("myBatis 제목");
		vo.setWriter("터프가이");
		vo.setContent("myBatis 내용입니다. ...............");
		boardDAO.insert(vo);

		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");

		List<BoardVO> boardList = boardDAO.getBoardList(vo);

		boardList.forEach(b -> logger.info("---> {}", b));

	}
}
