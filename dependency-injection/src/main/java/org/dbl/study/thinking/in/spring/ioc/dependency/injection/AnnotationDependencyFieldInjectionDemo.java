package org.dbl.study.thinking.in.spring.ioc.dependency.injection;

import org.dbl.study.thinking.in.spring.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * ClassName: AnnotationDependencyFieldInjectionDemo <br>
 * Description: 基于 Java 注解资源的依赖 Field 方法注入示例 <br>
 * date: 2020/7/17 23:24<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class AnnotationDependencyFieldInjectionDemo {

  // @Autowired 会忽略静态字段
  @Autowired private UserHolder userHolder;

  @Resource private UserHolder userHolder2;

  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext();

    // 将当前类 BeanInitializationDemo 作为配置类（Configuration Class） -> Spring Bean
    applicationContext.register(AnnotationDependencyFieldInjectionDemo.class);

    XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

    // 使用 classpath:/META-INF/dependency-lookup-context.xml 不报错，虽然有两个 User 类型的 Bean ，但是设置了 Primary
    // 使用 classpath:/META-INF/dependency-setter-injection.xml 报错，XML 和 注解分别注入了两个 Bean， 抛出
    // NoUniqueBeanDefinitionException 异常
    String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
    // 加载 XML 资源，解析并且生成 BeanDefinition
    beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

    //    启动应用上下文
    applicationContext.refresh();

    AnnotationDependencyFieldInjectionDemo demo =
        applicationContext.getBean(AnnotationDependencyFieldInjectionDemo.class);

    // @Autowired 字段关联
    UserHolder userHolder = demo.userHolder;
    System.out.println(userHolder);
    System.out.println(demo.userHolder2);

    System.out.println(demo.userHolder == demo.userHolder2);

    //    关闭应用上下文
    applicationContext.close();
  }

  @Bean
  public UserHolder userHolder(User user) {
    UserHolder userHolder = new UserHolder();
    userHolder.setUser(user);
    return userHolder;
  }
}
