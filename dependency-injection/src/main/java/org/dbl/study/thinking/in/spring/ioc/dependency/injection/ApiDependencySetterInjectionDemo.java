package org.dbl.study.thinking.in.spring.ioc.dependency.injection;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * ClassName: XmlDependencySetterInjectionDemo <br>
 * Description: 基于 API 资源的依赖 Setter 方法注入示例 <br>
 * date: 2020/7/17 0:20<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class ApiDependencySetterInjectionDemo {
  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext();

    // 将当前类 BeanInitializationDemo 作为配置类（Configuration Class）
    //    applicationContext.register(ApiDependencySetterInjectionDemo.class);

    // 注册 UserHolder 的 BeanDefinition
    BeanDefinition userHolderBeanDefinition = createUserHolderBeanDefinition();
    applicationContext.registerBeanDefinition("userHolder", userHolderBeanDefinition);

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

  /**
   * 为 {@link UserHolder} 生成 {@link BeanDefinition}
   *
   * @return
   */
  private static BeanDefinition createUserHolderBeanDefinition() {
    BeanDefinitionBuilder definitionBuilder =
        BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
    // ref 实际上就是 xml 中 ref属性的底层实现
    definitionBuilder.addPropertyReference("user", "superUser");
    return definitionBuilder.getBeanDefinition();
  }

  //  @Bean
  //  public UserHolder userHolder(User user) {
  //    return new UserHolder(user);
  //  }
}
