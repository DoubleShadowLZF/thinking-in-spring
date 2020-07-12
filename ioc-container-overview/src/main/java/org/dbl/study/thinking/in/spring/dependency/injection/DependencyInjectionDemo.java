package org.dbl.study.thinking.in.spring.dependency.injection;

import org.dbl.study.thinking.in.spring.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName: DependencyLookupDemo <br>
 * Description: 依赖注入示例<br>
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
public class DependencyInjectionDemo {
  public static void main(String[] args) {
    // 配置 xml 配置文件
    // 启动 Spring 应用上下文
    BeanFactory beanFactory =
        new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

    UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);

    //    System.out.println(userRepository.getUsers());
    //    依赖注入
    System.out.println(userRepository.getBeanFactory());
    //    System.out.println(userRepository.getBeanFactory() == beanFactory);

    //    依赖查找
    //    System.out.println(beanFactory.getBean(BeanFactory.class));

    //    ObjectFactory<User> userObjectFactory = userRepository.getUserObjectFactory();
    //    System.out.println(userObjectFactory.getObject());

    ObjectFactory<ApplicationContext> contextObjectFactory =
        userRepository.getContextObjectFactory();
    System.out.println(contextObjectFactory.getObject());

    System.out.println(beanFactory == contextObjectFactory.getObject());
  }
}
