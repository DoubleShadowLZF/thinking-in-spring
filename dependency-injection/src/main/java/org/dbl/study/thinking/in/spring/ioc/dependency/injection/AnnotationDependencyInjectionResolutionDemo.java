package org.dbl.study.thinking.in.spring.ioc.dependency.injection;

import org.dbl.study.thinking.in.spring.domain.User;
import org.dbl.study.thinking.in.spring.ioc.dependency.injection.annotation.InjectedUser;
import org.dbl.study.thinking.in.spring.ioc.dependency.injection.annotation.MyAutowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.*;

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

  @MyAutowired private Optional<User> optionalUser;

  @InjectedUser private User myInjectedUser;

  // static 关键字将 Bean 的初始化，提前到 AnnotationDependencyInjectionResolutionDemo 装载前
  //  @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
  //  public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
  //    AutowiredAnnotationBeanPostProcessor beanPostProcessor =
  //        new AutowiredAnnotationBeanPostProcessor();
  //    // @Autowired + @Injected + 新的注解 @InjectedUser
  //    Set<Class<? extends Annotation>> autowiredAnnotationTypes =
  //        new LinkedHashSet<>(Arrays.asList(Autowired.class, Inject.class, InjectedUser.class));
  //    beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
  //    return beanPostProcessor;
  //  }

  /**
   * 由于优先级比 {@link AutowiredAnnotationBeanPostProcessor} 低，且 Bean 名称不为 {@link
   * AnnotationConfigUtils#AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME} ，则 Spring 容器中同时存在两个
   * AutowiredAnnotationBeanPostProcessor，且自定义的 AutowiredAnnotationBeanPostProcessor 优先级比 Spring
   * 官方提供的 AutowiredAnnotationBeanPostProcessor 优先级高
   *
   * @see AutowiredAnnotationBeanPostProcessor#order
   * @see AnnotationConfigUtils#AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME
   */
  @Bean
  @Order(Ordered.LOWEST_PRECEDENCE - 3)
  public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
    AutowiredAnnotationBeanPostProcessor beanPostProcessor =
        new AutowiredAnnotationBeanPostProcessor();
    // @Autowired + @Injected + 新的注解 @InjectedUser
    Set<Class<? extends Annotation>> autowiredAnnotationTypes =
        new LinkedHashSet<>(Arrays.asList(Autowired.class, Inject.class, InjectedUser.class));
    beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
    return beanPostProcessor;
  }

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
    // 期待输出 superUser Bean
    System.out.println("demo.myInjectedUser = " + demo.myInjectedUser);

    //    关闭应用上下文
    applicationContext.close();
  }
}
