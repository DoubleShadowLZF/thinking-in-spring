package org.dbl.study.thinking.in.spring.bean.definition;

import org.dbl.study.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.dbl.study.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * ClassName: BeanInstantiationDemo <br>
 * Description: Bean 实例化示例 <br>
 * date: 2020/7/12 23:11<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class SpecialBeanInstantiationDemo {
  public static void main(String[] args) {
    // 配置 xml 配置文件
    // 启动 Spring 应用上下文
    ApplicationContext applicationContext =
        new ClassPathXmlApplicationContext(
            "classpath:/META-INF/special-bean-instantiation-context.xml");

    //    通过 applicationContext 获取 AutowireCapableBeanFactory
    AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();

    //    demoServiceLoader();

    ServiceLoader serviceLoader =
        beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);
    displayServiceLoader(serviceLoader);

    //    通过 AutowireCapableBeanFactory 创建 UserFactory 实例对象
    UserFactory userFactory = beanFactory.createBean(DefaultUserFactory.class);
    System.out.println(userFactory.createUser());
  }

  public static void demoServiceLoader() {
    ServiceLoader<UserFactory> serviceLoader =
        ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
    displayServiceLoader(serviceLoader);
  }

  private static void displayServiceLoader(ServiceLoader<UserFactory> serviceLoader) {
    Iterator<UserFactory> iterator = serviceLoader.iterator();
    while (iterator.hasNext()) {
      UserFactory userFactory = iterator.next();
      System.out.println(userFactory.createUser());
    }
  }
}
