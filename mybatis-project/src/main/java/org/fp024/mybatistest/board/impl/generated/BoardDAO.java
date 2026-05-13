package org.fp024.mybatistest.board.impl.generated;

import java.util.List;
import org.fp024.mybatistest.board.generated.BoardVO;

public interface BoardDAO {
    int deleteByPrimaryKey(Integer seq);

    int insert(BoardVO row);

    BoardVO selectByPrimaryKey(Integer seq);

    List<BoardVO> selectAll();

    int updateByPrimaryKey(BoardVO row);
}