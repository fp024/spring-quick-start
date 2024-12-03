package com.springbook.biz.board.impl;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("boardService")
public class BoardServiceImpl implements BoardService {
  private final BoardDAOJPA boardDAO;

  @Override
  public void insertBoard(BoardVO vo) {
    vo.setRegDate(LocalDateTime.now());
    boardDAO.insertBoard(vo);
  }

  @Override
  public void updateBoard(BoardVO vo) {
    boardDAO.updateBoard(vo);
  }

  @Override
  public void deleteBoard(BoardVO vo) {
    boardDAO.deleteBoard(vo);
  }

  @Override
  public BoardVO getBoard(BoardVO vo) {
    return boardDAO.getBoard(vo);
  }

  @Override
  public List<BoardVO> getBoardList(BoardVO vo) {
    return boardDAO.getBoardList(vo);
  }
}
