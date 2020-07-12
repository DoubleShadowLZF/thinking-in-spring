package org.dbl.study.thinking.in.spring.bean.definition;

import org.dbl.study.thinking.in.spring.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName: BeanInstantiationDemo <br>
 * Description: Bean 实例化示例 <br>
 * date: 2020/7/12 23:11<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class BeanInstantiationDemo {
  public static void main(String[] args) {
    // 配置 xml 配置文件
    // 启动 Spring 应用上下文
    BeanFactory beanFactory =
        new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");
    User user = beanFactory.getBean("user-by-static-method", User.class);
    System.out.println(user);

    User userByInstanceMethod = beanFactory.getBean("user-by-instance-method", User.class);
    System.out.println(userByInstanceMethod);

    System.out.println(user == userByInstanceMethod);

    User userByFactoryBean = beanFactory.getBean("user-by-factory-bean", User.class);
    System.out.println(userByFactoryBean);

    System.out.println(userByFactoryBean == userByInstanceMethod);
  }
}
