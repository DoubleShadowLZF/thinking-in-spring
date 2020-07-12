package org.dbl.study.thinking.in.spring.domain;

/**
 * ClassName: User <br>
 * Description: 用户类<br>
 * date: 2020/7/11 17:51<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class User {
  private Long id;
  private String name;

  public static User createUser() {
    User user = new User();
    user.setId(1L);
    user.setName("句号君");
    return user;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "User{" + "id=" + id + ", name='" + name + '\'' + '}';
  }
}
