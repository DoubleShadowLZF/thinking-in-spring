package org.dbl.study.thinking.in.spring.bean.definition;

import org.dbl.study.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.dbl.study.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: BeanInitalizationDemo <br>
 * Description: Bean 初始化实现 <br>
 * date: 2020/7/12 23:51<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
@Configuration // Configuration Class
public class BeanInitializationDemo {
  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext();
    // 将当前类 BeanInitializationDemo 作为配置类（Configuration Class）
    applicationContext.register(BeanInitializationDemo.class);
    //    启动应用上下文
    applicationContext.refresh();
    //    在此处被设置
    // (1.0 版本)org.springframework.beans.factory.support.AbstractBeanDefinition.setInitMethodName
    // (5.1 版本)org.springframework.beans.factory.config.BeanDefinition.setInitMethodName
  }

  @Bean(initMethod = "initUserFactory")
  public UserFactory userFactory() {
    return new DefaultUserFactory();
  }
}
