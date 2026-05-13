package org.fp024.mybatistest.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.fp024.mybatistest.board.generated.BoardVO;

@Getter
@Setter
@ToString
public class BoardDTO {

  private BoardVO boardVO;

  private String searchCondition;

  private String searchKeyword;
}
