package com.springbook.biz.board;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.web.multipart.MultipartFile;

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
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class BoardVO {
	@XmlAttribute
	private int seq;
	
	private String title;
	
	private String writer;
	
	private String content;
	
	private Date regDate;
	
	private int cnt;
	
	@XmlTransient
	private String searchCondition;
	
	@XmlTransient
	private String searchKeyword;
	
	@XmlTransient
	private MultipartFile uploadFile;	
}
