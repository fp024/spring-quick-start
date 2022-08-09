package com.springbook.biz.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutCommon {
  @Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
  public void allPointcut() {
    // 포인트 컷 선언 메서드는 참조 메서드로만 지정.. 메서드 내용을 지정하지 않음.
  }

  @Pointcut("execution(* com.springbook.biz..*Impl.get*(..))")
  public void getPointcut() {
    // 포인트 컷 선언 메서드는 참조 메서드로만 지정.. 메서드 내용을 지정하지 않음.
  }
}
