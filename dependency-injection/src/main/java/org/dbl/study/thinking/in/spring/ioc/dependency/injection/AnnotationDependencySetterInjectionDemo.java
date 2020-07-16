package org.dbl.study.thinking.in.spring.ioc.dependency.injection;

import org.dbl.study.thinking.in.spring.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * ClassName: XmlDependencySetterInjectionDemo <br>
 * Description: 基于 Java 注解资源的依赖 Setter 方法注入示例 <br>
 * date: 2020/7/17 0:20<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class AnnotationDependencySetterInjectionDemo {
  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext();

    // 将当前类 BeanInitializationDemo 作为配置类（Configuration Class）
    applicationContext.register(AnnotationDependencySetterInjectionDemo.class);

    XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

    // 使用 classpath:/META-INF/dependency-lookup-context.xml 不报错，虽然有两个 User 类型的 Bean ，但是设置了 Primary
    // 使用 classpath:/META-INF/dependency-setter-injection.xml 报错，XML 和 注解分别注入了两个 Bean， 抛出
    // NoUniqueBeanDefinitionException 异常
    String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
    // 加载 XML 资源，解析并且生成 BeanDefinition
    beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

    //    启动应用上下文
    applicationContext.refresh();

    // 依赖查找并且创建 Bean
    UserHolder userHolder = applicationContext.getBean(UserHolder.class);
    System.out.println(userHolder);

    //    关闭应用上下文
    applicationContext.close();
  }

  @Bean
  public UserHolder userHolder(User user) {
    return new UserHolder(user);
  }
}
