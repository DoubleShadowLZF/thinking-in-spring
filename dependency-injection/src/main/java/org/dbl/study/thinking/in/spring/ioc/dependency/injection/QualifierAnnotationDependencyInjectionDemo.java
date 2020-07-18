package org.dbl.study.thinking.in.spring.ioc.dependency.injection;

import org.dbl.study.thinking.in.spring.domain.User;
import org.dbl.study.thinking.in.spring.ioc.dependency.injection.annotation.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * ClassName: QualifierAnnotationDependencyInjectionDemo <br>
 * Description: {@link org.springframework.beans.factory.annotation.Qualifier} 示例<br>
 * date: 2020/7/18 11:00<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class QualifierAnnotationDependencyInjectionDemo {

  @Autowired private User user; // superUser -> primary true

  @Autowired
  @Qualifier("user") // 指定 Bean 名称或 ID
  private User nameUser;

  // 整天应用上下文存在 4 个 User 类型的 Bean
  // superUser
  // user
  // user1 -> @Qualifier
  // user2 -> @Qualifier

  // 不会装载 @Qualifier 注解标注的 Bean
  @Autowired private Collection<User> allUsers; // 2 Beans = superUser + user

  @Autowired @Qualifier
  private Collection<User>
      qualifiedUsers; // 2 Beans = user1 + user2 -> 4 Beans = user1 + user2 + user3 + user4

  @Autowired @UserGroup private Collection<User> groupsUsers; // 2 Beans = user3 + user4

  @Bean
  @Qualifier // 进行逻辑分组
  public User user1() {
    return createUser(7L);
  }

  @Bean
  @Qualifier
  public User user2() {
    return createUser(8L);
  }

  private User createUser(long l) {
    User user = new User();
    user.setId(l);
    return user;
  }

  @Bean
  @UserGroup
  public User user3() {
    return createUser(9L);
  }

  @Bean
  @UserGroup
  public User user4() {
    return createUser(10L);
  }

  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext();

    // 将当前类 BeanInitializationDemo 作为配置类（Configuration Class） -> Spring Bean
    applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

    XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

    // 使用 classpath:/META-INF/dependency-lookup-context.xml 不报错，虽然有两个 User 类型的 Bean ，但是设置了 Primary
    // 使用 classpath:/META-INF/dependency-setter-injection.xml 报错，XML 和 注解分别注入了两个 Bean， 抛出
    // NoUniqueBeanDefinitionException 异常
    String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
    // 加载 XML 资源，解析并且生成 BeanDefinition
    beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

    //    启动应用上下文
    applicationContext.refresh();

    QualifierAnnotationDependencyInjectionDemo demo =
        applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);
    // 期待输出 superUser Bean
    System.out.println("demo.user = " + demo.user);
    // 期待输出 user Bean
    System.out.println("demo.nameUser = " + demo.nameUser);
    // 期待输出 superUser user
    System.out.println("demo.allUsers = " + demo.allUsers);
    // 期待输出 user1 user2 ( user3 user 4)
    System.out.println("demo.qualifiedUsers = " + demo.qualifiedUsers);
    // 期待输出 user3 user4
    System.out.println("demo.groupsUsers = " + demo.groupsUsers);

    //    关闭应用上下文
    applicationContext.close();
  }
}
