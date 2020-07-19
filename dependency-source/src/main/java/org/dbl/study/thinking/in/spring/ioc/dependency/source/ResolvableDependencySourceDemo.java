package org.dbl.study.thinking.in.spring.ioc.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * ClassName: ResolvableDependencySourceDemo <br>
 * Description: ResolvableDependency 作为依赖来源 <br>
 * date: 2020/7/19 22:11<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class ResolvableDependencySourceDemo {

  @Autowired private String val;

  @PostConstruct
  public void init() {
    System.out.println(val);
  }

  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext();

    //    注册 Configuration Class (配置类)
    applicationContext.register(ResolvableDependencySourceDemo.class);

    applicationContext.addBeanFactoryPostProcessor(
        beanFactory -> {
          // 注册 Resolvable Dependency
          beanFactory.registerResolvableDependency(String.class, "Hello World");
        });

    //  启动 Spring
    applicationContext.refresh();

    //    显示关闭 Spring 应用上下文
    applicationContext.close();
  }
}
