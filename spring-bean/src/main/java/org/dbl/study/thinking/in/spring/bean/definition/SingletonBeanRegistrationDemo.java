package org.dbl.study.thinking.in.spring.bean.definition;

import org.dbl.study.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.dbl.study.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * ClassName: SingletonBeanRegistrationDemo <br>
 * Description: 单体 Bean 注册示例 <br>
 * date: 2020/7/13 0:54<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class SingletonBeanRegistrationDemo {

  public static void main(String[] args) throws InterruptedException {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext();
    //    启动应用上下文
    applicationContext.refresh();
    ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
    UserFactory userFactory = new DefaultUserFactory();
    beanFactory.registerSingleton("userFactory", userFactory);
    UserFactory userFactoryWithGetBean = beanFactory.getBean("userFactory", UserFactory.class);
    System.out.println(
        "userFactory == userFactoryWithGetBean : " + (userFactory == userFactoryWithGetBean));

    //    关闭应用上下文
    applicationContext.close();
  }
}
