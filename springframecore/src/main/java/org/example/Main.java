package org.example;


import java.sql.SQLException;
import org.example.user.User;
import org.example.user.UserDao;
import org.example.user.UserDao2;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

  public static void main(String[] args) throws SQLException {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ioc-settings.xml");
    /*UserDao userDao = context.getBean(UserDao.class);User user = User.builder()
        .username("Vali")
        .password("cat")
        .age(20)
        .build();
    System.out.println(userDao.save2(user));





    for (User user1 : userDao.findAll()) {
      System.out.println(user1);
    }*/

    UserDao2 userDao2 = context.getBean(UserDao2.class);
    User user = User.builder()
        .username("Gani")
        .password("dog")
        .age(99)
        .build();
    userDao2.save(user);


  }
}