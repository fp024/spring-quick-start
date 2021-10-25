package com.springbook.biz.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class UserVO {
  @Id
  private String id;
  private String password;
  private String name;
  @Column(name = "u_role")
  private String role;
}
