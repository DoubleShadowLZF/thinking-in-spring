package org.dbl.study.thinking.in.spring.dependency.lookup;

import org.dbl.study.thinking.in.spring.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * ClassName: TypeSafetyDependencyLookupDemo <br>
 * Description: 类型安全 依赖查找示例 <br>
 * date: 2020/7/15 0:32<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class TypeSafetyDependencyLookupDemo {
  public static void main(String[] args) {
    //
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext();

    //    注册 Configuration Class (配置类)
    applicationContext.register(TypeSafetyDependencyLookupDemo.class);

    //  启动 Spring
    applicationContext.refresh();

    //    演示 BeanFactory#getBean 方法的安全性
    displayBeanFactoryGetBean(applicationContext);
    //    演示 ObjectFactory#getObject 方法的安全性
    displayObjectFactoryGetObject(applicationContext);
    //    演示 ObjectFactory#getIfAvailable 方法的安全性
    displayObjectProviderIfAvailable(applicationContext);

    //    演示 ListableBeanFactory#getBeansOfType 方法的安全性
    displayListableBeanFactoryGetBeansOfType(applicationContext);
    //     演示 ObjectProvider#stream 方法的安全性
    displayObjectProviderStreamOps(applicationContext);

    //    显示关闭 Spring 应用上下文
    applicationContext.close();
  }

  private static void displayObjectProviderStreamOps(
      AnnotationConfigApplicationContext applicationContext) {
    ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
    printBeanException(
        "displayObjectProviderStreamOps", () -> beanProvider.forEach(System.out::println));
  }

  private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory beanFactory) {
    printBeanException(
        "displayListableBeanFactoryGetBeansOfType", () -> beanFactory.getBeansOfType(User.class));
  }

  private static void displayObjectProviderIfAvailable(
      AnnotationConfigApplicationContext applicationContext) {
    ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
    printBeanException(
        "displayObjectProviderIfAvailable", () -> userObjectProvider.getIfAvailable());
  }

  public static void displayObjectFactoryGetObject(
      AnnotationConfigApplicationContext applicationContext) {
    // ObjectProvider is ObjectFactory
    ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
    printBeanException("displayObjectFactoryGetObject", () -> userObjectProvider.getObject());
  }

  public static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
    printBeanException("displayBeanFactoryGetBean", () -> beanFactory.getBean(User.class));
  }

  public static void printBeanException(String resource, Runnable task) {
    System.out.println("====================================");
    System.out.println("Source from :" + resource);
    try {
      task.run();
    } catch (BeansException e) {
      e.printStackTrace();
    }
  }
}
