package com.springbook.biz.user.impl;

import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// 질문: 이거 이름안붙이면 못찾네.. 원래 인터페이스 이름으로 따라가지 않나..
// 확인: 확인시 userServiceImpl로 만들어지고 있다.
@RequiredArgsConstructor
@Service("userService")
public class UserServiceImpl implements UserService {
  private final UserDAO userDAO;

  @Override
  public UserVO getUser(UserVO vo) {
    return userDAO.getUser(vo);
  }
}
