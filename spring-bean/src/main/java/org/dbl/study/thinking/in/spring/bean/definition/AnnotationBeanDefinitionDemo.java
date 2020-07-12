package org.dbl.study.thinking.in.spring.bean.definition;

import org.dbl.study.thinking.in.spring.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * ClassName: AnnotationBeanDefinitionDemo <br>
 * Description: 注解 BeanDefinition 示例<br>
 * date: 2020/7/12 17:55<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
// 3、通过 @Import 来进行导入
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {
  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext();

    //    注册 Configuration Class (配置类)
    applicationContext.register(AnnotationBeanDefinitionDemo.class);
    //    1、通过 @Bean 方式定义
    //    2、通过 @Component 方式
    //    3、通过 @Import 来进行导入

    //    通过 BeanDefinition 注册 API 实现
    //    1、命名 Bean 的注册方式
    registerBeanDefinition(applicationContext, "target-user");
    registerBeanDefinition(applicationContext);

    //  启动 Spring
    applicationContext.refresh();
    //    按照类型依赖查找
    //    org.springframework.context.annotation.AnnotationBeanNameGenerator.generateBeanName
    System.out.println("Config  类型的所有 Beans :" + applicationContext.getBeansOfType(Config.class));
    System.out.println("User 类型的所有 Beans :" + applicationContext.getBeansOfType(User.class));

    //    显示关闭 Spring 应用上下文
    applicationContext.close();
  }

  public static void registerBeanDefinition(BeanDefinitionRegistry registry) {
    registerBeanDefinition(registry, null);
  }

  public static void registerBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
    BeanDefinitionBuilder beanDefinitionBuilder =
        BeanDefinitionBuilder.genericBeanDefinition(User.class);
    beanDefinitionBuilder.addPropertyValue("id", 1).addPropertyValue("name", "句号君");

    //    判断如果 beanName 参数存在时
    if (StringUtils.hasText(beanName)) {
      //    注册 BeanDefinition
      registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
    } else {
      //      org.springframework.beans.factory.support.BeanDefinitionReaderUtils.uniqueBeanName
      //      名称 ： 全类名#count[0-9+]
      BeanDefinitionReaderUtils.registerWithGeneratedName(
          beanDefinitionBuilder.getBeanDefinition(), registry);
    }
  }

  //  2、通过 @Component 方式
  @Component
  public static class Config {

    /**
     * 通过 Java注解的方式，定义了一个 Bean
     *
     * @return
     */
    //    1、通过 @Bean 方式定义
    @Bean(name = {"user", "dbl-user"})
    public User user() {
      User user = new User();
      user.setId(1L);
      user.setName("句号君");
      return user;
    }
  }
}
