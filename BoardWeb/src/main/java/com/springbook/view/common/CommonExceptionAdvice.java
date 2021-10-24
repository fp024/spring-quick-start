package com.springbook.view.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice("com.springbook.view")
public class CommonExceptionAdvice {

  @ExceptionHandler(ArithmeticException.class)
  public ModelAndView handleArithmeticException(Exception e) {
    logger.error(e.getMessage(), e);
    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", e);
    mav.setViewName("/common/arithmeticError.jsp");
    return mav;
  }

  @ExceptionHandler(NullPointerException.class)
  public ModelAndView handleNullPointerException(Exception e) {
    logger.error(e.getMessage(), e);
    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", e);
    mav.setViewName("/common/nullPointerError.jsp");
    return mav;
  }

  @ExceptionHandler(Exception.class)
  public ModelAndView handleException(Exception e) {
    logger.error(e.getMessage(), e);
    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", e);
    mav.setViewName("/common/error.jsp");
    return mav;
  }
}
