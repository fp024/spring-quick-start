package com.springbook.view.board;

import com.google.common.base.Strings;
import com.springbook.biz.board.BoardListVO;
import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Slf4j
@RequiredArgsConstructor
@Controller
@SessionAttributes("board")
public class BoardController {

  private final BoardService boardService;

  @RequestMapping(value = "/dataTransform.do", produces = "application/json; charset=UTF-8")
  public @ResponseBody BoardListVO dataTransform(BoardVO vo) {
    logger.info("BoardVO: {}", vo);
    vo.setSearchCondition("TITLE");
    vo.setSearchKeyword("");
    List<BoardVO> boardList = boardService.getBoardList(vo);

    BoardListVO boardListVO = new BoardListVO();
    boardListVO.setBoardList(boardList);
    logger.info("BoardListVO: {}", boardListVO);
    return boardListVO;
  }

  @ModelAttribute("conditionMap")
  public Map<String, String> searchConditionMap() {
    Map<String, String> conditionMap = new LinkedHashMap<>();
    conditionMap.put("message.board.list.search.condition.title", "TITLE");
    conditionMap.put("message.board.list.search.condition.content", "CONTENT");
    return conditionMap;
  }

  @RequestMapping("/insertBoard.do")
  public String insertBoard(BoardVO vo) throws IOException {
    boardService.insertBoard(vo);

    if (vo.getUploadFile() != null && !vo.getUploadFile().isEmpty()) {
      String filename = vo.getUploadFile().getOriginalFilename();
      vo.getUploadFile().transferTo(new File("D:/Temp/BoardWeb/" + filename));
    }

    return "getBoardList.do";
  }

  @RequestMapping("/updateBoard.do")
  public String updateBoard(@ModelAttribute("board") BoardVO vo) {
    logger.warn("업데이트할 board 정보: {}", vo);
    boardService.updateBoard(vo);
    return "getBoardList.do";
  }

  @RequestMapping("/deleteBoard.do")
  public String deleteBoard(BoardVO vo) {
    boardService.deleteBoard(vo);
    return "getBoardList.do";
  }

  @RequestMapping("/getBoardList.do")
  public String getBoardList(BoardVO vo, Model model) {
    logger.info("getBoardList.do {}", vo);

    if (Strings.isNullOrEmpty(vo.getSearchCondition())) {
      vo.setSearchCondition("TITLE");
    }

    if (Strings.isNullOrEmpty(vo.getSearchKeyword())) {
      vo.setSearchKeyword("");
    }

    List<BoardVO> boardList = boardService.getBoardList(vo);
    model.addAttribute("boardList", boardList);
    return "getBoardList.jsp";
  }

  @RequestMapping("/getBoard.do")
  public String getBoard(BoardVO vo, Model model) {
    model.addAttribute("board", boardService.getBoard(vo)); // Model 정보 저장
    return "getBoard.jsp"; // View 이름 리턴
  }
}
