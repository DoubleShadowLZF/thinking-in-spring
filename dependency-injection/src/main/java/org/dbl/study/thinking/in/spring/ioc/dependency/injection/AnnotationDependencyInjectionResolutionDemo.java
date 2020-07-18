package org.dbl.study.thinking.in.spring.ioc.dependency.injection;

import org.dbl.study.thinking.in.spring.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

/**
 * ClassName: AnnotationDependencyInjectionResolutionDemo <br>
 * Description: 注解驱动的依赖注入处理过程<br>
 * date: 2020/7/18 11:43<br>
 *
 * @see org.springframework.beans.factory.annotation.Qualifier
 * @author Double <br>
 * @since JDK 1.8
 */
public class AnnotationDependencyInjectionResolutionDemo {
  @Autowired // 通过类型（User.class）依赖查找（处理）
  private User user; // DependencyDescriptor ->
  // 必须(required = true)
  // 实时注入(eager = true)
  // 通过类型（User.class）
  // 字段名称（"user"）
  // 是否首要（primary = true）

  @Inject private User injectedUser;

  @Autowired // 集合类型依赖注入
  private Map<String, User> users; // user superUser

  @Autowired private Optional<User> optionalUser;

  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext();

    // 将当前类 BeanInitializationDemo 作为配置类（Configuration Class） -> Spring Bean
    applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

    XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

    // 使用 classpath:/META-INF/dependency-lookup-context.xml 不报错，虽然有两个 User 类型的 Bean ，但是设置了 Primary
    // 使用 classpath:/META-INF/dependency-setter-injection.xml 报错，XML 和 注解分别注入了两个 Bean， 抛出
    // NoUniqueBeanDefinitionException 异常
    String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
    // 加载 XML 资源，解析并且生成 BeanDefinition
    beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

    //    启动应用上下文
    applicationContext.refresh();

    AnnotationDependencyInjectionResolutionDemo demo =
        applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);

    // 期待输出 superUser Bean
    System.out.println("demo.user = " + demo.user);
    // 期待输出 superUser Bean
    System.out.println("demo.injectedUser = " + demo.injectedUser);
    // 期待输出 user superUser Bean
    System.out.println("demo.users = " + demo.users);
    // 期待输出 optionalUser Bean
    System.out.println("demo.optionalUser = " + demo.optionalUser);

    //    关闭应用上下文
    applicationContext.close();
  }
}
