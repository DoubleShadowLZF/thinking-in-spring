package org.dbl.study.thinking.in.spring.ioc.dependency.injection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * ClassName: AwareInterfaceDependencyInjectionDemo <br>
 * Description: 基于 {@link org.springframework.beans.factory.Aware} 接口回调的依赖注入示例<br>
 * date: 2020/7/17 23:35<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class AwareInterfaceDependencyInjectionDemo
    implements BeanFactoryAware, ApplicationContextAware {

  private static BeanFactory beanFactory;
  private static ApplicationContext applicationContext;

  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext();

    // 将当前类 AwareInterfaceDependencyInjectionDemo 作为配置类（Configuration Class） -> Spring Bean
    applicationContext.register(AwareInterfaceDependencyInjectionDemo.class);

    //    启动应用上下文
    applicationContext.refresh();

    System.out.println(beanFactory == applicationContext.getBeanFactory());
    System.out.println(
        applicationContext == AwareInterfaceDependencyInjectionDemo.applicationContext);

    //    关闭应用上下文
    applicationContext.close();
  }

  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    AwareInterfaceDependencyInjectionDemo.beanFactory = beanFactory;
  }

  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    AwareInterfaceDependencyInjectionDemo.applicationContext = applicationContext;
  }
}
