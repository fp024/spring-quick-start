package com.springbook.view.board;

import com.google.common.base.Strings;
import com.springbook.biz.board.BoardListVO;
import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.util.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {
  private final BoardService boardService;

  private final Path uploadPath = Path.of("C:/upload/BoardWeb/");

  @RequestMapping(value = "/dataTransform", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody BoardListVO dataTransform(BoardVO vo) {
    logger.info("vo: {}", vo);
    vo.setSearchCondition("TITLE");
    vo.setSearchKeyword("");
    List<BoardVO> boardList = boardService.getBoardList(vo);

    BoardListVO boardListVO = new BoardListVO();
    boardListVO.setBoardList(boardList);
    return boardListVO;
  }

  @ModelAttribute("conditionMap")
  public Map<String, String> searchConditionMap() {
    Map<String, String> conditionMap = new LinkedHashMap<>();
    conditionMap.put("message.board.list.search.condition.title", "TITLE");
    conditionMap.put("message.board.list.search.condition.content", "CONTENT");
    return conditionMap;
  }

  @GetMapping("/insertBoard")
  public void insertBoard() {}

  @PostMapping("/insertBoard")
  public String insertBoard(BoardVO vo) throws IOException {
    boardService.insertBoard(vo);

    logger.info("vo.getUploadFile(): {}", vo.getUploadFile());
    if (vo.getUploadFile() != null && !vo.getUploadFile().isEmpty()) {
      String filename = vo.getUploadFile().getOriginalFilename();
      FileUtils.mkdir(uploadPath.toFile(), true);
      vo.getUploadFile().transferTo(uploadPath.resolve(filename));
    }

    return "redirect:getBoardList";
  }

  @PostMapping("/updateBoard")
  public String updateBoard(
      @ModelAttribute("board") BoardVO vo, BoardDAO boardDAO, HttpServletResponse response) {
    logger.warn("업데이트할 board 정보: {}", vo);
    BoardVO board = boardService.getBoard(vo);
    if (board == null) {
      throw new EntityNotFoundException();
    }
    board.setTitle(vo.getTitle());
    board.setContent(vo.getContent());
    boardService.updateBoard(board);
    return "redirect:getBoardList";
  }

  @PostMapping("/deleteBoard")
  public String deleteBoard(BoardVO vo) {
    boardService.deleteBoard(vo);
    return "redirect:getBoardList";
  }

  @GetMapping("/getBoardList")
  public String getBoardList(BoardVO vo, Model model) {
    logger.info("getBoardList {}", vo);

    if (Strings.isNullOrEmpty(vo.getSearchCondition())) {
      vo.setSearchCondition("TITLE");
    }

    if (Strings.isNullOrEmpty(vo.getSearchKeyword())) {
      vo.setSearchKeyword("");
    }

    List<BoardVO> boardList = boardService.getBoardList(vo);
    model.addAttribute("boardList", boardList);
    return "getBoardList";
  }

  @GetMapping("/getBoard")
  public String getBoard(BoardVO vo, Model model) {
    model.addAttribute("board", boardService.getBoard(vo)); // Model 정보 저장
    return "getBoard"; // View 이름 리턴
  }
}
