package org.dbl.study.thinking.in.spring.bean.factory;

import org.dbl.study.thinking.in.spring.domain.User;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * ClassName: DefaultUserFactory <br>
 * Description: 默认 User 工厂类<br>
 * date: 2020/7/12 23:15<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class DefaultUserFactory implements UserFactory, InitializingBean {
  @Override
  public User createUser() {
    return User.createUser();
  }

  /** 1、基于 @PostConstruct 注解 */
  @PostConstruct
  public void init() {
    System.out.println("@PostConstruct ： UserFactory 初始化中 .... ... ");
  }

  @Override
  public void initUserFactory() {
    System.out.println("自定义初始化方法 initUserFactory() : UserFactory 初始化中 ... ... ");
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("InitializingBean#afterPropertiesSet() : UserFactory 初始化中 ... ... ");
  }
}
