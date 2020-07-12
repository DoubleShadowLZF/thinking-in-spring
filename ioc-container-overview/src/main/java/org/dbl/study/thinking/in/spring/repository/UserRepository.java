package org.dbl.study.thinking.in.spring.repository;

import org.dbl.study.thinking.in.spring.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

/**
 * ClassName: UserRepository <br>
 * Description: <br>
 * date: 2020/7/12 9:16<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class UserRepository {

  // 自定义 Bean
  private Collection<User> users;
  //  内建非 Bean 对象
  private BeanFactory beanFactory;

  public ObjectFactory<User> userObjectFactory;

  public ObjectFactory<ApplicationContext> contextObjectFactory;

  public ObjectFactory<ApplicationContext> getContextObjectFactory() {
    return contextObjectFactory;
  }

  public void setContextObjectFactory(ObjectFactory<ApplicationContext> contextObjectFactory) {
    this.contextObjectFactory = contextObjectFactory;
  }

  public ObjectFactory<User> getUserObjectFactory() {
    return userObjectFactory;
  }

  public void setUserObjectFactory(ObjectFactory<User> userObjectFactory) {
    this.userObjectFactory = userObjectFactory;
  }

  private Collection<User> getUsers() {
    return users;
  }

  public void setUsers(Collection<User> users) {
    this.users = users;
  }

  public BeanFactory getBeanFactory() {
    return beanFactory;
  }

  public void setBeanFactory(BeanFactory beanFactory) {
    this.beanFactory = beanFactory;
  }
}
