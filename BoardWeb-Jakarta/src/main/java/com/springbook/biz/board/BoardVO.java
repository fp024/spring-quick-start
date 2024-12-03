package com.springbook.biz.board;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlTransient;
import java.time.LocalDateTime;
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
  @XmlAttribute @Id @GeneratedValue private Integer seq;

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
