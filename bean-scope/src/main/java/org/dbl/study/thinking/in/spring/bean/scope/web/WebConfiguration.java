package org.dbl.study.thinking.in.spring.bean.scope.web;

import org.dbl.study.thinking.in.spring.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * ClassName: WebConfiguration <br>
 * Description: <br>
 * date: 2020/7/22 22:24<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
@Configuration
@EnableWebMvc
public class WebConfiguration {
  @Bean
  //  @RequestScope
  @SessionScope
  public User user() {
    User user = User.createUser();
    System.out.println("User Bean 注入 Spring IOC ： " + user.hashCode());
    return User.createUser();
  }
}
