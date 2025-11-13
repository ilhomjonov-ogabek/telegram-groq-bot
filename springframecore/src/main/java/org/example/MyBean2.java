package org.example;

public class MyBean2 {

  private final MyBean myBean;

  public MyBean2(MyBean myBean) {
    this.myBean = myBean;
  }

  public void hi() {
    System.out.println("My bean 2");
    myBean.hi();

  }

}
