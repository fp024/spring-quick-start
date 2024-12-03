package com.springbook.view.board;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.google.gson.Gson;
import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.fp024.util.GsonHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@Slf4j
@SpringJUnitWebConfig(
    locations = {
      "classpath:applicationContext.xml", //
      "file:src/main/webapp/WEB-INF/config/presentation-layer.xml"
    })
class BoardControllerTest {

  private static final Gson GSON = new Gson();

  @Autowired private BoardService service;

  // @Autowired private WebApplicationContext ctx;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    // this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    this.mockMvc =
        MockMvcBuilders.standaloneSetup(new BoardController(service))
            .setMessageConverters(new GsonHttpMessageConverter(GsonHelper.gson()))
            .build();
  }

  @Test
  void dataTransform() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/dataTransform")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
                .content(GSON.toJson(Map.of("title", "제목", "searchKeyword", "검색키워드")))
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  // XML AOP 설정으로 트랜젝션을 정의해놔서 @Transactional 을 사용할 수 없다.
  // 어플리케이션 컨텍스트가 초기화 될 때마다, 테이블 초기화하기 때문에...
  @Test
  void insertBoard() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/insertBoard") //
                .param("title", "제목")
                .param("writer", "작성자")
                .param("content", "본문"))
        .andExpect(status().isFound())
        .andExpect(redirectedUrl("getBoardList"))
        .andDo(print());
  }

  private Integer insertBoardData() {
    BoardVO boardVO = new BoardVO();
    boardVO.setTitle("제목");
    boardVO.setWriter("작성자");
    boardVO.setContent("본문");
    service.insertBoard(boardVO);
    return boardVO.getSeq();
  }

  @Test
  void updateBoard() throws Exception {
    Integer seq = insertBoardData();
    List<BoardVO> boards = service.getBoardList(new BoardVO());
    logger.info("### {}", boards);
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/updateBoard") //
                .param("seq", Integer.toString(seq))
                .param("title", "수정 제목")
                .param("content", "수정 본문"))
        .andExpect(status().isFound())
        .andExpect(redirectedUrl("getBoardList"))
        .andDo(print());
  }

  @Test
  void deleteBoard() throws Exception {
    // 엔티티 조회후 삭제를 해서 삭제할 데이터가 반드시 있어야한다.
    Integer seq = insertBoardData();

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/deleteBoard") //
                .param("seq", Integer.toString(seq)))
        .andExpect(status().isFound())
        .andExpect(redirectedUrl("getBoardList"))
        .andDo(print());
  }

  @Test
  void getBoardList() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/getBoardList/") //
                .param("searchCondition", "TITLE")
                .param("searchKeyword", "제목 검색 키워드"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("boardList"))
        // 게시물이 존재하지 않아도 반환 List 자체가 null이 아닌 빈 리스트 객체라서 모델이 있는 것을 간주한다.
        .andExpect(view().name("getBoardList"))
        .andDo(print());
  }

  @Test
  void getBoard() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/getBoard/").param("seq", "1"))
        .andExpect(status().isOk())
        // .andExpect(model().attributeExists("board")) // 게시물이 존재하여 값을 받아올 수 있으면 참이된다.
        .andExpect(view().name("getBoard"))
        .andDo(print());
  }
}
