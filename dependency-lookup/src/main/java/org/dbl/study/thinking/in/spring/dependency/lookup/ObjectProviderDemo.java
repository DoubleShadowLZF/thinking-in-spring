package org.dbl.study.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * ClassName: ObjectProviderDemo <br>
 * Description: 通过 {@link ObjectProvider} 进行依赖查找 <br>
 * date: 2020/7/13 22:15<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class ObjectProviderDemo { // @Configuration 是非必须的注解

  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext();

    //    注册 Configuration Class (配置类)
    applicationContext.register(ObjectProviderDemo.class);

    //  启动 Spring
    applicationContext.refresh();

    //    依赖查找集合对象
    lookupByObjectProvider(applicationContext);

    //    显示关闭 Spring 应用上下文
    applicationContext.close();
  }

  @Bean
  public String helloWorld() { // 方法名就是 Bean 名称 = "helloWorld"
    return "hello,world";
  }

  private static void lookupByObjectProvider(
      AnnotationConfigApplicationContext applicationContext) {
    ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
    System.out.println(beanProvider.getObject());
  }
}
