package com.springbook.view.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class InsertBoardController {
	@RequestMapping("/insertBoard.do")
	public void insertBoard(BoardVO vo) {
		logger.info("글 등록 처리");
		BoardDAO dao = new BoardDAO();
		dao.insertBoard(vo);
	}

}
