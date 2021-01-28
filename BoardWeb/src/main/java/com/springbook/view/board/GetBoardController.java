package com.springbook.view.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetBoardController implements Controller {
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		logger.info("글 상세 조회 처리");

		// 1. 검색할 게시글 번호 추출
		String seq = request.getParameter("seq");

		// 2. DB연동 처리
		BoardVO vo = new BoardVO();
		vo.setSeq(Integer.parseInt(seq));

		BoardDAO boardDAO = new BoardDAO();
		BoardVO board = boardDAO.getBoard(vo);

		// 3. 검색결과를 세션에 저장하고 목록 화면으로 이동한다.
		// HttpSession session = request.getSession();
		// session.setAttribute("board", board);

		// 3. 검색 결과의 화면 정보를 ModelAndView에 저장하여 리턴한다.
		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board);
		mav.setViewName("getBoard");

		return mav;
	}
}
