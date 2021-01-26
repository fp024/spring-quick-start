package com.springbook.biz.board;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * VO(Value Object)
 * 
 * @author E5-2670x2
 */
@Getter
@Setter
@ToString
public class BoardVO {
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
	private int cnt;
}
