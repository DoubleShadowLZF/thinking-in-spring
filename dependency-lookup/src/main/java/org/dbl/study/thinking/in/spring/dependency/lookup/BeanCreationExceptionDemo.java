package org.dbl.study.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * ClassName: BeanCreationExceptionDemo <br>
 * Description: {@link org.springframework.beans.factory.BeanCreationException} 示例<br>
 * date: 2020/7/16 22:35<br>
 *
 * @author Double <br>
 * @since JDK 1.8
 */
public class BeanCreationExceptionDemo {
  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext();

    //    注册 BeanDefinition Bean Class 是一个 POJO 普通类，不过初始化方法回调时抛出异常
    BeanDefinitionBuilder beanDefinitionBuilder =
        BeanDefinitionBuilder.genericBeanDefinition(POJO.class);
    applicationContext.registerBeanDefinition(
        "errorBean", beanDefinitionBuilder.getBeanDefinition());

    //  启动 Spring
    applicationContext.refresh();

    //    显示关闭 Spring 应用上下文
    applicationContext.close();
  }

  // 注意此处需要静态类
  static class POJO implements InitializingBean {

    @PostConstruct
    public void init() throws Throwable {
      throw new Throwable("init() : To purposes ...");
    }

    @Override // CommonAnnotationBeanPostProcessor
    public void afterPropertiesSet() throws Exception {
      throw new Exception("afterPropertiesSet() : To purposes ...");
    }
  }
}
