package org.dbl.study.thinking.in.spring.ioc.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 * ClassName: ExternalConfigurationDependencySourceDemo <br>
 * Description: <br>
 * date: 2020/7/19 22:22<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
@Configuration
@PropertySource(
    value = "META-INF/default.properties",
    encoding = "GBK") // 此处的 encoding 需要看配置文件的编码格式
public class ExternalConfigurationDependencySourceDemo {

  @Value("${user.id:-1}")
  private Long id;

  @Value("${usr.name}")
  private String name;

  @Value("${user.resource://default.properties}")
  private Resource resource;

  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext();

    //    注册 Configuration Class (配置类)
    applicationContext.register(ExternalConfigurationDependencySourceDemo.class);

    //  启动 Spring
    applicationContext.refresh();

    ExternalConfigurationDependencySourceDemo demo =
        applicationContext.getBean(ExternalConfigurationDependencySourceDemo.class);

    System.out.println("demo.id = " + demo.id);
    System.out.println("demo.name = " + demo.name);
    System.out.println("demo.resource = " + demo.resource);

    //    显示关闭 Spring 应用上下文
    applicationContext.close();
  }
}
