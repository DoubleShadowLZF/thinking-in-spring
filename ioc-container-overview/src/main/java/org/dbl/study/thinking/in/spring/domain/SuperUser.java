package org.dbl.study.thinking.in.spring.domain;

import org.dbl.study.thinking.in.spring.annotation.Super;

/**
 * ClassName: SuperUser <br>
 * Description: 超级用户<br>
 * date: 2020/7/11 18:38<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
@Super
public class SuperUser extends User {
  private String address;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "SuperUser{" + "address='" + address + '\'' + "} " + super.toString();
  }
}
