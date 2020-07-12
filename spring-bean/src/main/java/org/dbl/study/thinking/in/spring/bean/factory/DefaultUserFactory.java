package org.dbl.study.thinking.in.spring.bean.factory;

import org.dbl.study.thinking.in.spring.domain.User;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * ClassName: DefaultUserFactory <br>
 * Description: 默认 User 工厂类<br>
 * date: 2020/7/12 23:15<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {
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

  @PreDestroy
  public void preDestroy() {
    System.out.println("@PreDestroy ： UserFactory 销毁中 .... ... ");
  }

  @Override
  public void destroy() throws Exception {
    System.out.println("DisposableBean#destroy() : UserFactory 销毁中 ... ... ");
  }

  @Override
  public void doDestroy() {
    System.out.println("自定义 doDestroy() : UserFactory 销毁中 ... ... ");
  }

  @Override
  protected void finalize() throws Throwable {
    //    super.finalize();
    System.out.println("当前 DefaultUserFactory 对象正在被垃圾回收...");
  }
}
