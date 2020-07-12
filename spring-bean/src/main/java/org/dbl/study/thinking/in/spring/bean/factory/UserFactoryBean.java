package org.dbl.study.thinking.in.spring.bean.factory;

import org.dbl.study.thinking.in.spring.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * ClassName: UserFactoryBean <br>
 * Description: {@link User} Bean 的 {@link FactoryBean}实现<br>
 * date: 2020/7/12 23:22<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class UserFactoryBean implements FactoryBean {
  @Override
  public Object getObject() throws Exception {
    return User.createUser();
  }

  @Override
  public Class<?> getObjectType() {
    return User.class;
  }
}
