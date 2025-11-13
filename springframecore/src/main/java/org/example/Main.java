package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(MyConfig.class);
    MyBean2 bean = container.getBean(MyBean2.class);
    bean.hi();


  }
}