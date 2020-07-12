package org.dbl.study.thinking.in.spring.bean.factory;

import org.dbl.study.thinking.in.spring.domain.User;

/**
 * ClassName: UserFactory <br>
 * Description: User 工厂<br>
 * date: 2020/7/12 23:17<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public interface UserFactory {
  default User createUser() {
    return User.createUser();
  }
}
