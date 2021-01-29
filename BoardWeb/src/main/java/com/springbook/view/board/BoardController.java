package com.springbook.view.board;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	@RequestMapping("/insertBoard.do")
	public String insertBoard(BoardVO vo, BoardDAO boardDAO) {
		logger.info("글 등록 처리");
		boardDAO.insertBoard(vo);
		return "getBoardList.do";
	}
	
	@RequestMapping("/updateBoard.do")
	public String updateBoard(BoardVO vo, BoardDAO boardDAO) {
		logger.info("글 수정 처리");
		boardDAO.updateBoard(vo);
		return "getBoardList.do";
	}
	
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo, BoardDAO boardDAO) {
		logger.info("글 삭제 처리");
		boardDAO.deleteBoard(vo);
		return "getBoardList.do";
	}
	
	@RequestMapping("/getBoardList.do")
	public ModelAndView handleRequest(BoardVO vo, BoardDAO boardDAO, ModelAndView mav) {
		logger.info("글 목록 검색 처리");
		List<BoardVO> boardList = boardDAO.getBoardList(vo);
		mav.addObject("boardList", boardList);	// Model 정보 저장
		mav.setViewName("getBoardList.jsp");	// View 정보 저장
		return mav;
	}
	
	@RequestMapping("/getBoard.do")
	public ModelAndView getBoard(BoardVO vo, BoardDAO boardDAO, ModelAndView mav) {
		logger.info("글 상세 조회 처리");
		mav.addObject("board", boardDAO.getBoard(vo));  // Model 정보 저장
		mav.setViewName("getBoard.jsp");				// View 정보 저장 
		return mav;
	}
}
