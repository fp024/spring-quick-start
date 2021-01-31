package org.fp024.mybatistest.board.impl;

import java.util.List;
import org.fp024.mybatistest.board.BoardVO;

public interface BoardDAO {
    int deleteByPrimaryKey(Integer seq);

    int insert(BoardVO record);

    BoardVO selectByPrimaryKey(Integer seq);

    List<BoardVO> selectAll();

    int updateByPrimaryKey(BoardVO record);
}