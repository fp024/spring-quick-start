package com.springbook.biz.board;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
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
@Entity
public class BoardVO {
	@XmlAttribute
	@Id
	@GeneratedValue
	private int seq;
	
	private String title;
	
	private String writer;
	
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date regDate;
	
	private int cnt;
	
	@Transient
	@XmlTransient
	private String searchCondition;
	
	@Transient
	@XmlTransient
	private String searchKeyword;
	
	@Transient
	@XmlTransient
	private MultipartFile uploadFile;	
}
