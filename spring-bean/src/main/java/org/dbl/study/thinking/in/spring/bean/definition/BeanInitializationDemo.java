package org.dbl.study.thinking.in.spring.bean.definition;

import org.dbl.study.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.dbl.study.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * ClassName: BeanInitalizationDemo <br>
 * Description: Bean 初始化实现 <br>
 * date: 2020/7/12 23:51<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
@Configuration // Configuration Class
public class BeanInitializationDemo {
  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext();
    // 将当前类 BeanInitializationDemo 作为配置类（Configuration Class）
    applicationContext.register(BeanInitializationDemo.class);
    //    启动应用上下文
    applicationContext.refresh();
    //    在此处被设置
    // (1.0 版本)org.springframework.beans.factory.support.AbstractBeanDefinition.setInitMethodName
    // (5.1 版本)org.springframework.beans.factory.config.BeanDefinition.setInitMethodName

    // 非延迟初始化在 Spring 应用上下文启动完成后，被初始化
    System.out.println("Spring 应用上下文已启动 ...");

    //    // Instantiate all remaining (non-lazy-init) singletons.
    //       AbstractApplicationContext#finishBeanFactoryInitialization
    //      ->  // Instantiate all remaining (non-lazy-init) singletons.
    //              beanFactory.preInstantiateSingletons();

    UserFactory userFactory = applicationContext.getBean(UserFactory.class);
    System.out.println(userFactory);

    System.out.println("Spring 应用上下文准备销毁 ...");
    applicationContext.close();
    System.out.println("Spring 应用上下文已销毁 ...");
  }

  @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
  @Lazy
  public UserFactory userFactory() {
    return new DefaultUserFactory();
  }
}
