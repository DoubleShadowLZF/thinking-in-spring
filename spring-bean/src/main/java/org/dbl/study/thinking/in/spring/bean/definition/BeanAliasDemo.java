package org.dbl.study.thinking.in.spring.bean.definition;

import org.dbl.study.thinking.in.spring.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName: BeanAliasDemo <br>
 * Description: Bean 别名示例<br>
 * date: 2020/7/12 17:47<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class BeanAliasDemo {
  public static void main(String[] args) {
    // 配置 xml 配置文件
    // 启动 Spring 应用上下文
    BeanFactory beanFactory =
        new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definitions-context.xml");

    // 通过别名 dbl-ser 获取曾用名 user 的 bean
    User user = beanFactory.getBean("user", User.class);
    User dblUser = beanFactory.getBean("dbl-user", User.class);
    System.out.println("user 是否与 dblUser 相同：" + (user == dblUser));
  }
}
