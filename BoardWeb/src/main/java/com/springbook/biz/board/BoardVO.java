package com.springbook.biz.board;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * VO(Value Object)
 * 
 * @author fp024
 */
@Getter
@Setter
@JsonIgnoreProperties(value = {"searchCondition", "searchKeyword", "uploadFile"})
@ToString
public class BoardVO {
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
	private int cnt;
	private String searchCondition;
	private String searchKeyword;
	private MultipartFile uploadFile;	
}
