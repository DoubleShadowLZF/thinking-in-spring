package org.dbl.study.thinking.in.spring.ioc.dependency.injection;

/**
 * ClassName: UserHolder <br>
 * Description: {@link org.dbl.study.thinking.in.spring.domain.User} 的 Holder 类<br>
 * date: 2020/7/17 0:26<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class UserHolder {
  private org.dbl.study.thinking.in.spring.domain.User User;

  public UserHolder() {}

  public UserHolder(org.dbl.study.thinking.in.spring.domain.User user) {
    User = user;
  }

  public void setUser(org.dbl.study.thinking.in.spring.domain.User user) {
    User = user;
  }

  public org.dbl.study.thinking.in.spring.domain.User getUser() {
    return User;
  }

  @Override
  public String toString() {
    return "UserHolder{" + "User=" + User + '}';
  }
}
