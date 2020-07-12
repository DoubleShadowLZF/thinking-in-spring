package org.dbl.study.thinking.in.spring.bean.factory;

import org.dbl.study.thinking.in.spring.domain.User;

/**
 * ClassName: DefaultUserFactory <br>
 * Description: 默认 User 工厂类<br>
 * date: 2020/7/12 23:15<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class DefaultUserFactory implements UserFactory {
  @Override
  public User createUser() {
    return User.createUser();
  }
}
