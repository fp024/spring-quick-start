package com.springbook.biz.board;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

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
@Table(name = "board")
public class BoardVO {
  @XmlAttribute @Id @GeneratedValue private int seq;

  private String title;

  private String writer;

  private String content;

  @Column(name = "regdate")
  private LocalDateTime regDate;

  private int cnt;

  @Transient @XmlTransient private String searchCondition;

  @Transient @XmlTransient private String searchKeyword;

  @Transient @XmlTransient private MultipartFile uploadFile;
}
