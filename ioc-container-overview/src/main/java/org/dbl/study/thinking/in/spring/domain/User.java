package org.dbl.study.thinking.in.spring.domain;

import org.dbl.study.thinking.in.spring.enums.City;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: User <br>
 * Description: 用户类<br>
 * date: 2020/7/11 17:51<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class User implements BeanNameAware {
  private Long id;
  private String name;

  private City city;

  // 数组类型
  private City[] workCites;

  private List<City> lifeCities;

  private Resource configFileLocation;

  private transient String beanName;

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

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public City[] getWorkCites() {
    return workCites;
  }

  public void setWorkCites(City[] workCites) {
    this.workCites = workCites;
  }

  public List<City> getLifeCities() {
    return lifeCities;
  }

  public void setLifeCities(List<City> lifeCities) {
    this.lifeCities = lifeCities;
  }

  public Resource getConfigFileLocation() {
    return configFileLocation;
  }

  // 基础类型的注入
  public void setConfigFileLocation(Resource configFileLocation) {
    this.configFileLocation = configFileLocation;
  }

  @Override
  public String toString() {
    return "User{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", city="
        + city
        + ", workCites="
        + Arrays.toString(workCites)
        + ", lifeCities="
        + lifeCities
        + ", configFileLocation="
        + configFileLocation
        + '}';
  }

  @PostConstruct
  public void init() {
    System.out.println("用户 Bean [" + beanName + "] 初始化...");
  }

  @PreDestroy
  public void destroy() {
    System.out.println("用户 Bean [" + beanName + "] 销毁...");
  }

  public void setBeanName(String name) {
    this.beanName = name;
  }
}
