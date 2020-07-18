package org.dbl.study.thinking.in.spring.ioc.dependency.injection;

import org.dbl.study.thinking.in.spring.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;

/**
 * ClassName: LazyAnnotationDependencyInjectionDemo <br>
 * Description: {@link org.springframework.beans.factory.ObjectProvider} 实现延迟注入<br>
 * date: 2020/7/18 11:24<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class LazyAnnotationDependencyInjectionDemo {
  @Autowired private User user; // 实时注入

  @Autowired private ObjectProvider<User> userObjectProvider; // 延迟注入

  @Autowired private ObjectFactory<Collection<User>> userObjectFactory;

  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext();

    // 将当前类 BeanInitializationDemo 作为配置类（Configuration Class） -> Spring Bean
    applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

    XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

    // 使用 classpath:/META-INF/dependency-lookup-context.xml 不报错，虽然有两个 User 类型的 Bean ，但是设置了 Primary
    // 使用 classpath:/META-INF/dependency-setter-injection.xml 报错，XML 和 注解分别注入了两个 Bean， 抛出
    // NoUniqueBeanDefinitionException 异常
    String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
    // 加载 XML 资源，解析并且生成 BeanDefinition
    beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

    //    启动应用上下文
    applicationContext.refresh();

    LazyAnnotationDependencyInjectionDemo demo =
        applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);

    // 期待输出 superUser Bean
    System.out.println("demo.user = " + demo.user);
    // 期待输出 superUser Bean
    System.out.println("demo..userObjectProvider = " + demo.userObjectProvider.getObject());
    // 期待输出 superUser Bean
    System.out.println("demo.userObjectFactory = " + demo.userObjectFactory.getObject());

    demo.userObjectProvider.forEach(System.out::println);

    //    关闭应用上下文
    applicationContext.close();
  }
}
