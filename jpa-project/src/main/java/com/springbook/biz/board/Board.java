package com.springbook.biz.board;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity implementation class for Entity: Board
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "BOARD")
public class Board {
	@Id
	@GeneratedValue
	private Integer seq;

	private String title;

	private String writer;

	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	private Date regDate = new Date();

	private int cnt;
}
