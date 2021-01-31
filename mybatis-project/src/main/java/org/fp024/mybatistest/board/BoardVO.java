package org.fp024.mybatistest.board;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO {
	private Integer seq;

	private String title;

	private String writer;

	private String content;

	private Date regDate;

	private Integer cnt;

	private String searchCondition;

	private String searchKeyword;

}