package org.dbl.study.thinking.in.spring.dependency.lookup;

import org.dbl.study.thinking.in.spring.annotation.Super;
import org.dbl.study.thinking.in.spring.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * ClassName: DependencyLookupDemo <br>
 * Description: 依赖查找示例<br>
 *
 * <p>1、通过名称的方式来查找<br>
 *
 * <p>2、通过类型的方式来查找<br>
 *
 * <p>3、通过注解的方式来查找<br>
 * date: 2020/7/11 17:50<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class DependencyLookupDemo {
  public static void main(String[] args) {
    // 配置 xml 配置文件
    // 启动 Spring 应用上下文
    BeanFactory beanFactory =
        new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");
    lookupByAnnotationType(beanFactory);

    //    lookupCollectionByType(beanFactory);
    //    lookupByType(beanFactory);
    //    lookupInRealTime(beanFactory);
    //    lookupInLazy(beanFactory);
  }

  /** 通过注解类型查找 */
  private static void lookupByAnnotationType(BeanFactory beanFactory) {
    if (beanFactory instanceof ListableBeanFactory) {
      ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
      Map<String, Object> users = listableBeanFactory.getBeansWithAnnotation(Super.class);
      System.out.println("查找标注 @Super 所有的 User 集合对象：" + users);
    }
  }

  /** 通过类型查找集合对象 */
  private static void lookupCollectionByType(BeanFactory beanFactory) {
    if (beanFactory instanceof ListableBeanFactory) {
      ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
      Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
      System.out.println("查找到的所有的 User 集合对象：" + users);
    }
  }

  /**
   * 通过类型查找
   *
   * @param beanFactory
   */
  private static void lookupByType(BeanFactory beanFactory) {
    User user = beanFactory.getBean(User.class);
    System.out.println("实时查找：" + user);
  }

  /**
   * 1、通过名称的方式来查找<br>
   * 延迟查找
   */
  private static void lookupInLazy(BeanFactory beanFactory) {
    // FactoryBean
    ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
    User user = objectFactory.getObject();
    System.out.println("延迟查找：" + user);
  }

  /**
   * 1、通过名称的方式来查找<br>
   * 实时查找
   */
  private static void lookupInRealTime(BeanFactory beanFactory) {
    User user = (User) beanFactory.getBean("user");

    System.out.println("实时查找：" + user);
  }
}
