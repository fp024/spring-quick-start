package com.springbook.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Strings;
import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

@Controller
public class LoginController {
  @RequestMapping(value = "/login.do", method = RequestMethod.GET)
  public String loginView(UserVO vo) {
    vo.setId("test");
    vo.setPassword("test123");
    return "login.jsp";
  }

  @RequestMapping(value = "/login.do", method = RequestMethod.POST)
  public String login(UserVO vo, UserDAO userDAO, HttpSession session) {
    if (Strings.isNullOrEmpty(vo.getId())) {
      throw new IllegalArgumentException("아이디는 반드시 입력해야합니다.");
    }

    UserVO user = userDAO.getUser(vo);
    if (user != null) {
      session.setAttribute("userName", user.getName());
      return "getBoardList.do";
    } else {
      return "login.jsp";
    }
  }
}
