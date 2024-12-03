package com.springbook.view.user;

import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class LogoutController {
  @RequestMapping("/logout.do")
  public String logout(HttpSession session) {
    logger.info("로그아웃 처리");
    session.invalidate();
    return "login.jsp";
  }
}
