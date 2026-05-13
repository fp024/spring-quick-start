package org.fp024.mybatistest.board;

import java.util.List;

import org.fp024.mybatistest.board.generated.BoardVO;
import org.fp024.mybatistest.board.impl.BoardDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardServiceClient {
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceClient.class);

	public static void main(String[] args) {
		BoardDAO boardDAO = new BoardDAO();

		BoardDTO dto = new BoardDTO();
		BoardVO vo = new BoardVO();
		vo.setTitle("myBatis 제목");
		vo.setWriter("털보가이");
		vo.setContent("myBatis 내용입니다. ...............");
		boardDAO.insert(vo);

		dto.setBoardVO(vo);
		dto.setSearchCondition("TITLE");
		dto.setSearchKeyword("myBatis");

		List<BoardVO> boardList = boardDAO.getBoardList(dto);

		boardList.forEach(b -> logger.info("---> {}", b));

	}
}
