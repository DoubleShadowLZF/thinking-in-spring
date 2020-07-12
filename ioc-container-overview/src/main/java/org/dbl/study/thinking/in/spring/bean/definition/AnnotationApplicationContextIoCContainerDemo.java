package org.dbl.study.thinking.in.spring.bean.definition;

import org.dbl.study.thinking.in.spring.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * ClassName: BeanFactoryIoCContainerDemo <br>
 *
 * <p>Description: 注解能力 {@link org.springframework.context.ApplicationContext} 作为IoC 容器示例< br>
 *
 * <p>date: 2020/7/12 11:03<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
@Configuration
public class AnnotationApplicationContextIoCContainerDemo {
  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext();
    // 将当前类 AnnotationApplicationContextIoCContainerDemo 作为配置类（Configuration Class）
    applicationContext.register(AnnotationApplicationContextIoCContainerDemo.class);
    //    启动应用上下文
    applicationContext.refresh();
    //    依赖查找集合对象
    lookupCollectionByType(applicationContext);
  }

  @Bean
  public User user() {
    User user = new User();
    user.setId(1L);
    user.setName("句号君");
    return user;
  }

  /** 通过类型查找集合对象 */
  private static void lookupCollectionByType(BeanFactory beanFactory) {
    if (beanFactory instanceof ListableBeanFactory) {
      ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
      Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
      System.out.println("查找到的所有的 User 集合对象：" + users);
    }
  }
}
