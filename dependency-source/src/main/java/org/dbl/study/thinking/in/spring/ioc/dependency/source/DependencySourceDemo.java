package org.dbl.study.thinking.in.spring.ioc.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * ClassName: DependencySourceDemo <br>
 * Description: 依赖来源示例<br>
 * date: 2020/7/19 16:46<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class DependencySourceDemo {

  // 注入在 postProcessProperties 方法执行，早于 setter注入，也早于 @PostConstruct
  @Autowired private BeanFactory beanFactory;

  @Autowired private ResourceLoader resourceLoader;

  @Autowired private ApplicationContext applicationContext;

  @Autowired private ApplicationEventPublisher applicationEventPublisher;

  @PostConstruct
  public void init() {
    System.out.println(
        "beanFactory == applicationContext : " + (beanFactory == applicationContext));
    System.out.println(
        "beanFactory == applicationContext.getAutowireCapableBeanFactory() : "
            + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
    System.out.println(
        "resourceLoader == applicationContext : " + (resourceLoader == applicationContext));
    System.out.println(
        "applicationEventPublisher == applicationContext : "
            + (applicationEventPublisher == applicationContext));
  }

  @PostConstruct
  public void initByLookup() {
    // 这四个对象为 非 Spring 容器管理对象
    getBean(BeanFactory.class);
    getBean(ApplicationContext.class);
    getBean(ResourceLoader.class);
    getBean(ApplicationContext.class);
  }

  private <T> T getBean(Class<T> beanType) {
    try {
      return beanFactory.getBean(beanType);
    } catch (NoSuchBeanDefinitionException e) {
      System.err.println("当前类型" + beanType.getName() + " 无法在 BeanFactory 中查找 !");
    }
    return null;
  }

  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext();

    //    注册 Configuration Class (配置类)
    applicationContext.register(DependencySourceDemo.class);

    //  启动 Spring
    applicationContext.refresh();

    //    显示关闭 Spring 应用上下文
    applicationContext.close();
  }
}
